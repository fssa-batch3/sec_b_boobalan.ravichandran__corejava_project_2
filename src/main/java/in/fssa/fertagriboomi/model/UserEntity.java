package in.fssa.fertagriboomi.model;



public abstract class UserEntity  implements Comparable<User>{

	private int id;
	private String name;
	private String email;
	private String password;
	private long phoneNumber;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", isActive=" + isActive + "]";
	}
	
	public int compareTo(User o) {
		if (this.id == o.getId()) {
			return 0;
		} else if (this.id > o.getId()) {
			return 1;
		} else {
			return -1;
		}
	}
	
	
}
