package com.udacity.course3.reviews.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity 
public class Review {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String review_text;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
	//@JsonBackReference
    private Product product;
	
	@OneToMany(mappedBy="review",fetch = FetchType.LAZY)
	@JsonManagedReference
    private Set<Comments> comments;
	
	public Review(){
		
	}
	
	public Review(Integer id,String review_text,Product product){
		this.id = id ;
		this.review_text = review_text;
		this.product = product;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getReview() {
		return review_text;
	}

	public void setReview(String review_text) {
		this.review_text = review_text;
	}
		
	public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    } 
	
	public Set<Comments> getComments() {
        return comments;
    }

    public void setComments(Set<Comments> comments) {
         this.comments = comments;
    } 	
}
