package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.document.NestedComments;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.model.Comments;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // TODO: Wire JPA repositories here
	@Autowired // This means to get the bean called ReviewRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private ReviewRepository reviewRepository;
	@Autowired // This means to get the bean called CommentRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private CommentRepository commentRepository;
	
	@Autowired
	private ReviewMongoRepository reviewMongoRepository;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     **/
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId, @RequestBody String reviewComment) {
		Optional<Review>  review = reviewRepository.findById(reviewId);
	
		if (!review.isPresent())
			//return 404 not found error 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND"); 
		
		// save reviewComment in Comments for a review
		Comments comment = new Comments();
		comment.setReview(review.get());
		comment.setComment(reviewComment);
		commentRepository.save(comment);

		// save reviewComment in the nested comments 	
		NestedComments reviewNestedWithComments = reviewMongoRepository.findById(review.get().getId());
		if( reviewNestedWithComments != null ){
			reviewNestedWithComments.addComment(reviewComment);
			reviewMongoRepository.save(reviewNestedWithComments);	
			
		}

		//return success status
		return ResponseEntity.status(HttpStatus.CREATED).body("Comment was succesfully saved");
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<String> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
    	List<String> reviewComment = new ArrayList<>();
    	
    	Optional<Review>  review = reviewRepository.findById(reviewId);		 
		if (!review.isPresent()){
			reviewComment.add("NOT_FOUND");
			return reviewComment;
		}
			
        List<Comments> commentsList = new ArrayList<>();	   
	    commentsList = commentRepository.findCommentTextByReviewId(reviewId);	   
	    if (commentsList.isEmpty()){
			return reviewComment;
	    }
		
		for(Comments c:commentsList)
			reviewComment.add(c.getComment());
	    return reviewComment;
	}
}