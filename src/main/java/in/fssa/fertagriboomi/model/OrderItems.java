package in.fssa.fertagriboomi.model;

import com.google.protobuf.Timestamp;

public class OrderItems {
	private int id;
	private int orderId;
	private int productId;
	private int priceId;
	private int quantity;
	private String productName;
	private String productImage;
	private int price;
	private int discount;
	private java.sql.Timestamp orderDate;
	private java.sql.Timestamp deliveryDate;

	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}



	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

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

	@Override
	public String toString() {
		return "OrderItems [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", priceId=" + priceId
				+ ", quantity=" + quantity + ", productName=" + productName + ", productImage=" + productImage
				+ ", price=" + price + ", discount=" + discount + ", orderDate=" + orderDate + ", deliveryDate="
				+ deliveryDate + "]";
	}

}
