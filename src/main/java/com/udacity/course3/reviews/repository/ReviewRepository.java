package com.udacity.course3.reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.udacity.course3.reviews.model.Review;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
    public List<Review> findReviewTextByProductId(Integer id);	
}