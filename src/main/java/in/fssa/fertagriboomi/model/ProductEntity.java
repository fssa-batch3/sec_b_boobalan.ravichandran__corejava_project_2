package in.fssa.fertagriboomi.model;

public abstract class ProductEntity {

	private int id;
	private String name;
	private String productWeight;
	private String description;
	private String application;
	private String benefits;
	private String manufacture;
	private boolean isActive = true;
	private int categoryId;
	private int productPrice;
	private int productDiscount;
	private String imageURL;
	public int getStockCount() {
		return stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}

	private int stockCount;

	public int getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(int productDiscount) {
		this.productDiscount = productDiscount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(String productWeight) {
		this.productWeight = productWeight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getBenefits() {
		return benefits;
	}

	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;

	}

	public int getPrice() {
		return productPrice;
	}

	public void setPrice(int price) {
		productPrice = price;
	}

	public int getDiscount() {
		return productDiscount;
	}

	public void setDiscount(int discount) {
		productDiscount = discount;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", name=" + name + ", productWeight=" + productWeight + ", description="
				+ description + ", application=" + application + ", benefits=" + benefits + ", manufacture="
				+ manufacture + ", isActive=" + isActive + ", categoryId=" + categoryId + ", productPrice="
				+ productPrice + ", productDiscount=" + productDiscount + ", imageURL=" + imageURL + ", stockCount="
				+ stockCount + "]";
	}

}
