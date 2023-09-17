package in.fssa.fertagriboomi.find;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.fertagriboomi.service.ReviewsAndRatingsService;

public class TestReviewsFind {

	@Test
	public void testFindAllReviews() {
		ReviewsAndRatingsService reviewService = new ReviewsAndRatingsService();
		
		assertDoesNotThrow(() -> {
			System.out.println(reviewService.findAllRatings());
		});
	}
	
	@Test
	public void testFindAllReviewsByProductId() {
		ReviewsAndRatingsService reviewService = new ReviewsAndRatingsService();
		
		assertDoesNotThrow(() -> {
			System.out.println(reviewService.findAllRatingsByProductId(2));
		});
	}
	
	@Test
	public void testFindReviewByOrderItemId() {
		ReviewsAndRatingsService reviewService = new ReviewsAndRatingsService();
		
		assertDoesNotThrow(() -> {
			System.out.println(reviewService.findRatingAndReiewsByOrderItemId(5));
		});
	}
}
