package com.example.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReposytory extends JpaRepository<Product, Integer>{

	public Page<Product> findAllByCode(String code,Pageable pageable);
	
	public Product findById(Long id);
	
	@SuppressWarnings("unchecked")
	public Product save(Product product);
}