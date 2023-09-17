package in.fssa.fertagriboomi.create;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.model.RatingsAndReviews;
import in.fssa.fertagriboomi.service.ReviewsAndRatingsService;

public class TestCreateRatingsAndReviews {

	@Test
	public void testCreateRatingsAndReviewsWithValidInput() {
		
		ReviewsAndRatingsService reviewService = new ReviewsAndRatingsService();
		
		RatingsAndReviews ratings = new RatingsAndReviews();
		ratings.setOrderItemId(2);
		ratings.setProductId(2);
		ratings.setRatings(4);
		ratings.setReviews("Very useful product ... Fast Delivery.. highly recommend");
		ratings.setUserName("Mohan Raj");
		assertDoesNotThrow(() -> {
			reviewService.createRatingsAndReviews(ratings);

		});

		
	}
	
	@Test
	public void testCreateRatingsAndReviewsWithInValidInput() {
		
		ReviewsAndRatingsService reviewService = new ReviewsAndRatingsService();
		
		Exception exception = assertThrows(Exception.class, () -> {
			reviewService.createRatingsAndReviews(null);

		});
		
		String expectedMessage = "Invalid Ratings and Reviews input";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
		
	}
	
	@Test
	public void testCreateRatingsAndReviewsWithNegativeOrderItemId() {
		
		ReviewsAndRatingsService reviewService = new ReviewsAndRatingsService();

		RatingsAndReviews ratings = new RatingsAndReviews();
		ratings.setOrderItemId(-2);
		ratings.setProductId(2);
		ratings.setRatings(4);
		ratings.setReviews("Very useful product ... Fast Delivery.. highly recommend");
		ratings.setUserName("Mohan Raj");
		Exception exception = assertThrows(Exception.class, () -> {
			reviewService.createRatingsAndReviews(ratings);
		});
		String expectedMessage = "Invalid Order Item Id";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	@Test
	public void testCreateRatingsAndReviewsWithNonExistenOrdertemId() {
		ReviewsAndRatingsService reviewService = new ReviewsAndRatingsService();
		RatingsAndReviews ratings = new RatingsAndReviews();
		ratings.setOrderItemId(150);
		ratings.setProductId(2);
		ratings.setRatings(4);
		ratings.setReviews("Very useful product ... Fast Delivery.. highly recommend");
		ratings.setUserName("Mohan Raj");
		Exception exception = assertThrows(Exception.class, () -> {
			reviewService.createRatingsAndReviews(ratings);
		});
		String expectedMessage = "This Order Item Id is not available";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	@Test
	public void testCreateRatingsAndReviewsWithNegativeProductId() {
		
		ReviewsAndRatingsService reviewService = new ReviewsAndRatingsService();

		RatingsAndReviews ratings = new RatingsAndReviews();
		ratings.setOrderItemId(2);
		ratings.setProductId(-2);
		ratings.setRatings(4);
		ratings.setReviews("Very useful product ... Fast Delivery.. highly recommend");
		ratings.setUserName("Mohan Raj");
		Exception exception = assertThrows(Exception.class, () -> {
			reviewService.createRatingsAndReviews(ratings);
		});
		String expectedMessage = "Invalid Product Id";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	@Test
	public void testCreateRatingsAndReviewsWithNonExistenProductId() {
		ReviewsAndRatingsService reviewService = new ReviewsAndRatingsService();
		RatingsAndReviews ratings = new RatingsAndReviews();
		ratings.setOrderItemId(2);
		ratings.setProductId(200);
		ratings.setRatings(4);
		ratings.setReviews("Very useful product ... Fast Delivery.. highly recommend");
		ratings.setUserName("Mohan Raj");
		Exception exception = assertThrows(Exception.class, () -> {
			reviewService.createRatingsAndReviews(ratings);
		});
		String expectedMessage = "The product is not listed among the available products";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRatingsAndReviewsWithInvalidReviews() {
		ReviewsAndRatingsService reviewService = new ReviewsAndRatingsService();
		RatingsAndReviews ratings = new RatingsAndReviews();
		ratings.setOrderItemId(2);
		ratings.setProductId(2);
		ratings.setRatings(5);
		ratings.setReviews("");
		ratings.setUserName("Mohan Raj");
		Exception exception = assertThrows(Exception.class, () -> {
			reviewService.createRatingsAndReviews(ratings);
		});
		String expectedMessage = "Please Enter the message about this product";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	@Test
	public void testCreateRatingsAndReviewsWithNullReviews() {
		ReviewsAndRatingsService reviewService = new ReviewsAndRatingsService();
		RatingsAndReviews ratings = new RatingsAndReviews();
		ratings.setOrderItemId(2);
		ratings.setProductId(200);
		ratings.setRatings(4);
		ratings.setReviews(null);
		ratings.setUserName("Mohan Raj");
		Exception exception = assertThrows(Exception.class, () -> {
			reviewService.createRatingsAndReviews(ratings);
		});
		String expectedMessage = "Please Enter the message about this product";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRatingsAndReviewsWithEmptyName() {
		
		ReviewsAndRatingsService reviewService = new ReviewsAndRatingsService();

		RatingsAndReviews ratings = new RatingsAndReviews();
		ratings.setOrderItemId(2);
		ratings.setProductId(2);
		ratings.setRatings(4);
		ratings.setReviews("Very useful product ... Fast Delivery.. highly recommend");
		ratings.setUserName("");
		Exception exception = assertThrows(Exception.class, () -> {
			reviewService.createRatingsAndReviews(ratings);
		});
		String expectedMessage = "Username Cannot be Empty";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	@Test
	public void testCreateRatingsAndReviewsWithNullUserName() {
		ReviewsAndRatingsService reviewService = new ReviewsAndRatingsService();

		RatingsAndReviews ratings = new RatingsAndReviews();
		ratings.setOrderItemId(2);
		ratings.setProductId(2);
		ratings.setRatings(4);
		ratings.setReviews("Very useful product ... Fast Delivery.. highly recommend");
		ratings.setUserName(null);
		Exception exception = assertThrows(Exception.class, () -> {
			reviewService.createRatingsAndReviews(ratings);
		});
		String expectedMessage = "Username Cannot be Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
}
