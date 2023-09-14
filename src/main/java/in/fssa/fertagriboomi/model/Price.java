package in.fssa.fertagriboomi.model;

public class Price {

	private int price;
	private int discount;

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Price [price=" + price + ", discount=" + discount + "]";
	}
}
