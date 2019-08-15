package com.udacity.course3.reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.udacity.course3.reviews.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	  
	public Product findProductNameById(Integer id);
	
	public List<Product> findAll();
}