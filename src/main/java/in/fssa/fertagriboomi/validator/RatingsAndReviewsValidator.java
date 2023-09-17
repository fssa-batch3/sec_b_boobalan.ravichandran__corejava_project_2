package in.fssa.fertagriboomi.validator;

import in.fssa.fertagriboomi.dao.RatingsAndReviewsDAO;
import in.fssa.fertagriboomi.exception.DAOException;
import in.fssa.fertagriboomi.exception.ValidationException;
import in.fssa.fertagriboomi.model.RatingsAndReviews;

public class RatingsAndReviewsValidator {

	public static void validateCreate(RatingsAndReviews ratings) throws ValidationException {
            if(ratings == null) {
            	throw new ValidationException("Invalid Ratings and Reviews input");
            }
            
            if (ratings.getReviews() == null || "".equals(ratings.getReviews().trim())) {
    			throw new ValidationException("Please Enter the message about this product");
    		}
            if (ratings.getUserName() == null || "".equals(ratings.getUserName().trim())) {
    			throw new ValidationException("Username Cannot be Empty");
    		}
            if (ratings.getOrderItemId() <=0 ) {
    			throw new ValidationException("Invalid Order Item Id");
    		}
            if (ratings.getProductId() <=0 ) {
    			throw new ValidationException("Invalid Product Id");
    		}
            
            RatingsAndReviewsDAO reviewDAO = null;
           
            try {
            	reviewDAO = new RatingsAndReviewsDAO();
				boolean isOrderItemExists = reviewDAO.findOrderItemByOrderItemId(ratings.getOrderItemId());
				if(!isOrderItemExists){
					throw new ValidationException("This Order Item Id is not available");
				}
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ValidationException(e.getMessage());
			}
            
            
            try {
            	reviewDAO = new RatingsAndReviewsDAO();
				boolean isProductExists = reviewDAO.findProductByProductId(ratings.getProductId());
				if(!isProductExists) {
					throw new ValidationException("The product is not listed among the available products");
				}
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ValidationException(e.getMessage());
			}
	}

	public static void validateProductId(int productId) throws ValidationException {
		  RatingsAndReviewsDAO reviewDAO = null;
		  if (productId <=0 ) {
  			throw new ValidationException("Invalid Product Id");
  		}
          
		  
		 try {
			 
         	reviewDAO = new RatingsAndReviewsDAO();
				boolean isProductExists = reviewDAO.findProductByProductId(productId);
				if(!isProductExists) {
					throw new ValidationException("The product is not listed among the available products");
				}
			} catch (DAOException e) {
				e.printStackTrace();
				throw new ValidationException(e.getMessage());
			}
		
	}

	public static void validateOrderItemId(int orderItemId) throws ValidationException {
		if (orderItemId <=0 ) {
			throw new ValidationException("Invalid Order Item Id");
		}
		
		 
        RatingsAndReviewsDAO reviewDAO = null;
       
        try {
        	reviewDAO = new RatingsAndReviewsDAO();
			boolean isOrderItemExists = reviewDAO.findOrderItemByOrderItemId(orderItemId);
			if(!isOrderItemExists){
				throw new ValidationException("This Order Item Id is not available");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
		
	}

	
}
