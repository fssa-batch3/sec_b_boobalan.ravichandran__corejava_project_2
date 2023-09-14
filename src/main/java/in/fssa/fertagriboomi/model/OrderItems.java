package in.fssa.fertagriboomi.model;

import com.google.protobuf.Timestamp;

public class OrderItems {
	private int id;
	private int orderId;
	private int productId;
	private int priceId;
	private int quantity;
	private java.sql.Timestamp orderDate;
	private java.sql.Timestamp deliveryDate;

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
		return "OrderItems [orderId=" + orderId + ", id=" + id + ", productId=" + productId + ", priceId=" + priceId
				+ ", quantity=" + quantity + ", orderDate=" + orderDate + ", deliveryDate=" + deliveryDate + "]";
	}

}
