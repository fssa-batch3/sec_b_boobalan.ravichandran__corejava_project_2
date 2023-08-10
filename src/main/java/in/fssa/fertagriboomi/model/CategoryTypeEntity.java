package in.fssa.fertagriboomi.model;

public abstract class CategoryTypeEntity implements Comparable<CategoryType> {

	private int id;
	private String name;
	private boolean isActive = true;

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

	@Override
	public String toString() {
		return "Category Type [id=" + id + ", name=" + name + ", isActive=" + isActive + "]";
	}

	public int compareTo(CategoryType o) {
		if (this.id == o.getId()) {
			return 0;
		} else if (this.id > o.getId()) {
			return 1;
		} else {
			return -1;
		}
	}

}
