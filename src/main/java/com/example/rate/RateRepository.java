package com.example.rate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long>{

	@Query("SELECT AVG(r.value) from Rate r where r.productid = :productid")
	public Double avg(@Param("productid") Long productid);
	
	public List<Rate> findAllByProductid(Long productid);
}