package in.fssa.fertagriboomi.model;

public abstract class ProductEntity implements Comparable<Product> {

	private int id;
	private String name;
	private String productWeight;
	private String description;
	private String application;
	private String benefits;
	private String manufacture;
	private boolean isActive = true;
	private int categoryId;
	private Price price;

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
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

	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", name=" + name + ", productWeight=" + productWeight + ", description="
				+ description + ", application=" + application + ", benefits=" + benefits + ", manufacture="
				+ manufacture + ", isActive=" + isActive + ", categoryId=" + categoryId + "]";
	}

	@Override
	public int compareTo(Product o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
