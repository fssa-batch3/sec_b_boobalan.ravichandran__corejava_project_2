package in.fssa.fertagriboomi.model;

public class Orders {
	private int id;
	private boolean status;
	private int addressId;
	private String userEmail;
	private int productId;
	private int priceId;
	private int quantity;
	private java.sql.Timestamp orderDate;
	private java.sql.Timestamp deliveryDate;
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public java.sql.Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.sql.Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public java.sql.Timestamp getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(java.sql.Timestamp deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", status=" + status + ", addressId=" + addressId + ", userEmail=" + userEmail
				+ ", productId=" + productId + ", priceId=" + priceId + ", quantity=" + quantity + ", orderDate="
				+ orderDate + ", deliveryDate=" + deliveryDate + "]";
	}

}
