package in.fssa.fertagriboomi.model;

public class Stocks {


	private int id;
	private int productId;
	private int stockCount;
	private boolean status;
	
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
	public int getStockCount() {
		return stockCount;
	}
	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Stocks [id=" + id + ", productId=" + productId + ", stockCount=" + stockCount + ", status=" + status
				+ "]";
	}
}
