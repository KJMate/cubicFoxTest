package com.example.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.rate.Rate;
import com.example.rate.RateRepository;
import com.example.exception.ProductDetailFormatException;
import com.example.exception.ProductNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductReposytory productReposytory;
	
	@Autowired
	private RateRepository rateRepository;
					
	public Page<Product> findAll(String productCode, Pageable page) {
		Page<Product> tmp ;
		if(!productCode.equals("")) {
			tmp = productReposytory.findAllByCode(productCode, page);
			if(tmp.isEmpty()) {
				throw new ProductNotFoundException("Product not found by productCode: " + productCode);
			}
		}else {
			tmp = productReposytory.findAll(page);
		}
		for (Product product : tmp) {
			if(rateRepository.avg(product.getId()) != null) {
				product.setAvgRating(rateRepository.avg(product.getId()));
			}else {
				product.setAvgRating(0.0);
			}
		}
		return tmp;
	}
	
	public Product findById(long id){
		Product product = productReposytory.findById(id);
		if(product == null) {
			throw new ProductNotFoundException("Product not found by id: " + id);
		}
		product.setAvgRating(rateRepository.avg(product.getId()));
		product.setRatings(rateRepository.findAllByProductid(product.getId()));
		return product;
	}
	
	public void update(long id,Product updatedProduct) {
		Product product = productReposytory.findById(id);
		if(product == null) {
			throw new ProductNotFoundException("Product not found by id: " + id);
		}
		if(isNumeric(updatedProduct.getCode()) && updatedProduct.getCode().length() != 6) {
			throw new ProductDetailFormatException("Product code length must be 6 character, your length is: " + updatedProduct.getCode().length());
		}
		if(updatedProduct.getName().equals("")) {
			throw new ProductDetailFormatException("Product name is empty");
		}
		if(updatedProduct.getPrice() == 0 || updatedProduct.getPrice() < 0 ) {
			throw new ProductDetailFormatException("Product price must be grather than 0");
		}
		if(updatedProduct.getUser().getId() != product.getUser().getId() ) {
			throw new ProductDetailFormatException("You have no authority to perform the operation");
		}
		product.setDescription(updatedProduct.getDescription());
		product.setCode(updatedProduct.getCode());
		product.setName(updatedProduct.getName());
		product.setPrice(updatedProduct.getPrice());						
		productReposytory.save(product);
	}

	public void save(Rate newRate, long id) {
		Product product = productReposytory.findById(id);
		if(product == null) {
			throw new ProductNotFoundException("Product not found by id: " + id);
		}
		if(newRate.getValue() <1 || newRate.getValue() >10) {
			throw new ProductDetailFormatException("Rate value must be between 1 and 10");
		}
		newRate.setProductid(product.getId());
		rateRepository.save(newRate);
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	    	throw new ProductDetailFormatException("Contains non-numeric characters");
	    }
	    return true;
	}
}