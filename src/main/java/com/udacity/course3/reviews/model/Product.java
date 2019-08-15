package com.udacity.course3.reviews.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Set;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Review> reviews;

	@Column(name = "product_name", columnDefinition = "LONGVARBINARY")
	private String name;

	public Integer getId() {
		return id;
	}

	public String getProduct() {
		return name;
	}

	public void setProduct(String name) {
		this.name = name;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
}