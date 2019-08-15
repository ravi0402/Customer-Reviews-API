package com.udacity.course3.reviews;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.model.Comments;
import com.udacity.course3.reviews.repository.ReviewRepository;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewsApplicationTests {
	
	@Autowired private CommentRepository commentRepository;
	@Autowired private ReviewRepository reviewRepository;
	@Autowired private ProductRepository productRepository;	
	
	@Test
	public void nullTextCase(){
		assertThat(commentRepository).isNotNull();
		assertThat(reviewRepository).isNotNull();
		assertThat(productRepository).isNotNull();
	}
	
	@Test
	public void createProductTest() {
	    Product product = new Product();
		product.setProduct("Test product");
		productRepository.save(product);
	    assertThat(productRepository.findProductNameById(product.getId())).isNotNull();
	}
	
	@Test
	public void getProductsListAfterCreation() {
	    Product product = new Product();
		product.setProduct("Test product");
		productRepository.save(product);

		List<String> prouductsList = new ArrayList<>(); 
		for (Product p : productRepository.findAll())
			prouductsList.add(p.getProduct());
	    assertThat(prouductsList).isNotEmpty();
	}
	
	@Test
	public void saveReviewWithProduct() {
		Product product = new Product();
		product.setProduct("Test product");
		productRepository.save(product);
		Optional<Product>  o_product = productRepository.findById(product.getId());
		
	    Review review = new Review();
		review.setProduct(o_product.get());
		review.setReview("Test review");		
		reviewRepository.save(review);		
	
	    assertThat(reviewRepository.findReviewTextByProductId(product.getId())).isNotEmpty();
	}
	
	@Test
	public void saveReviewWithComment() {
		Product product = new Product();
		product.setProduct("Test product");
		
		productRepository.save(product);
		Optional<Product>  o_product = productRepository.findById(product.getId());
		
	    Review review = new Review();
		review.setProduct(o_product.get());
		review.setReview("Test review");		
		reviewRepository.save(review);
		Optional<Review>  o_review = reviewRepository.findById(review.getId());
		
		Comments comment = new Comments();
		comment.setReview(o_review.get());
		comment.setComment("Test comment");
		commentRepository.save(comment);
		
	    assertThat(commentRepository.findCommentTextByReviewId(review.getId())).isNotEmpty();
	}
	
	@DataMongoTest
	@ExtendWith(SpringExtension.class)
	public class MongoDbSpringIntegrationTest  {
		@Autowired MongoTemplate mongoTemplate;
		@Test
	    public void test() {
	        DBObject objectToSave = BasicDBObjectBuilder.start().add("x", "y").get();
	        mongoTemplate.save(objectToSave, "collection");
	        assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("x").containsOnly("y");	
	    }	

	}
}