package in.fssa.fertagriboomi.model;

import javax.validation.constraints.NotNull;

public abstract class CategoryEntity {

	private int id;
	@NotNull(message = "Category name cannot be null or empty")
	private String name;
	private boolean isActive = true;
	private int categoryTypeId;

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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getCategoryTypeId() {
		return categoryTypeId;
	}

	public void setCategoryTypeId(int categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", isActive=" + isActive + ", category_type_id="
				+ categoryTypeId + "]";
	}

}
