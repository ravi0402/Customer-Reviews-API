package com.udacity.course3.reviews.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class NestedComments {
	@Id
	private Integer id;
	private String review;
	private List<String> Comments;

	public NestedComments(final Integer id, final String review) {
		this.id = id;
		this.review = review;
		this.Comments = new ArrayList<>();
	}

	public void addComment(String comment) {
		this.Comments.add(comment);
	}
	
	public void setReview(String review) {
		this.review = review;
	}

	public String getReview() {
		return review;
	}
}