package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.model.Comments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments, Integer> { 
    public List<Comments> findCommentTextByReviewId(Integer ReviewId);
	
}