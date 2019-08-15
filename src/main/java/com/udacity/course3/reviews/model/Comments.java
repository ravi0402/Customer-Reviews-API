package com.udacity.course3.reviews.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="review_id")
	//@JsonBackReference
    private Review review;

    @Column
    private String commentText;	

	public Integer getId() {
		return id;
	}
	
	public String getComment() {
		return commentText;
	}

	public void setComment(String commentText) {
		this.commentText = commentText;
	}
	
	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}
	
}