package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.Product;
import com.example.product.ProductService;
import com.example.rate.Rate;

@RestController
public class Controller {

	private final Object monitor = new Object();

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Controller(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public Page<Product> allProduct(@RequestParam Optional<String> productCode, Pageable page) {
		return productService.findAll(productCode.orElse(""), page);
	}
	
	@GetMapping("/products/{productID}")
	public Product productById(@PathVariable(value = "productID") long productID){
		return productService.findById(productID);
	}
	
	@PutMapping("/products/{productID}")
	public HttpStatus updateProduct(@PathVariable(value = "productID") long productID,
			@RequestBody Product product) throws Exception {
		 productService.update(productID,product);
		 return HttpStatus.OK;
	}
	
	@PostMapping("/rate/{productID}")
	public HttpStatus saveRateForProduct(@PathVariable(value = "productID") long productID,
			@RequestBody Rate rating) {
		productService.save(rating,productID);
		 return HttpStatus.OK;
	}
	
	   @RequestMapping("/dumpDb")
	    @ResponseBody
	    public void dumpDb() throws IOException {
	        synchronized (this.monitor) {
	            File dump = new File("dump.sql");
	            if (dump.exists()) {
	                dump.delete();
	            }
	            this.jdbcTemplate.execute("script to'" + dump.getAbsolutePath() + "'");
	            System.out.println(dump.getAbsolutePath());
	        }
	    }
}