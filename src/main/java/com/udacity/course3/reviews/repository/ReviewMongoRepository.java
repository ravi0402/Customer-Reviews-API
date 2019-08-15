package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.document.NestedComments;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewMongoRepository extends MongoRepository<NestedComments, String> {
	public NestedComments findById(int id);	
}
