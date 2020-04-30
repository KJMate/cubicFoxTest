package com.example.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.rate.Rate;
import com.example.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Size(max = 6, min = 6)
	private String code;
	
	@NotNull
	private String name;

	private String description;
	
	@NotNull
	private double price;
	
	@Transient
	private Double avgRating;

	@Transient
	private List<Rate> ratings;
	
	@ManyToOne
    private User user;
	
	public Product() {
	}
	
	public Product(@Size(max = 6, min = 6) String code, @NotNull String name, String description,
			@NotNull double price) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@JsonInclude(Include.NON_NULL)
	public List<Rate> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rate> ratings) {
		this.ratings = ratings;
	}

	@JsonInclude(Include.NON_NULL)
	public Double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}

	@JsonIgnore
	public User getUser() {
		return user;
	}

	@JsonProperty
	public void setUser(User user) {
		this.user = user;
	}
}