package in.fssa.fertagriboomi.service;

import java.util.List;

import in.fssa.fertagriboomi.dao.OrdersDAO;
import in.fssa.fertagriboomi.dao.RatingsAndReviewsDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ServiceException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.Orders;
import in.fssa.fertagriboomi.model.RatingsAndReviews;
import in.fssa.fertagriboomi.validator.OrdersValidator;
import in.fssa.fertagriboomi.validator.RatingsAndReviewsValidator;

public class ReviewsAndRatingsService {

	public void createRatingsAndReviews(RatingsAndReviews ratings) throws ServiceException, ValidationException {
		 RatingsAndReviewsDAO reviewDAO = new RatingsAndReviewsDAO();
		RatingsAndReviewsValidator.validateCreate(ratings);
	      
		try {
			reviewDAO.create(ratings);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	
	public List<RatingsAndReviews> findAllRatings() throws ServiceException, ValidationException {
		 RatingsAndReviewsDAO reviewDAO = new RatingsAndReviewsDAO();

	      
		try {
			return reviewDAO.findAll();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}


	public List<RatingsAndReviews> findAllRatingsByProductId(int productId) throws ValidationException, ServiceException {
		 RatingsAndReviewsDAO reviewDAO = new RatingsAndReviewsDAO();

		 RatingsAndReviewsValidator.validateProductId(productId);
		 try {
			 return reviewDAO.findAllReviewsByProductId(productId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		 
		
	}
	
	public boolean findRatingAndReiewsByOrderItemId(int orderItemId) throws ValidationException, ServiceException {
		RatingsAndReviewsDAO reviewDAO = new RatingsAndReviewsDAO();

		 RatingsAndReviewsValidator.validateOrderItemId(orderItemId);
		 
		 try {
			return reviewDAO.findReviewByOrderItemId(orderItemId);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	
	
	
	
}
