package in.fssa.fertagriboomi.model;

public class RatingsAndReviews {

	private int id;
	private int productId;
	private int orderItemId;
	private int ratings;
	private String reviews;
	private String userName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "RatingAndReviews [id=" + id + ", productId=" + productId + ", orderItemId=" + orderItemId + ", ratings="
				+ ratings + ", reviews=" + reviews + ", userName=" + userName + "]";
	}
}
