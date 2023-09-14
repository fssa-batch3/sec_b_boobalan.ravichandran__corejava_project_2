package in.fssa.fertagriboomi.model;

public class DeliveryAddresses {
	private int id;
	private String addressTitle;
	private String streetName;
	private String location;
	private String city;
	private int pincode;
	private String personName;
	private String state;
	private long mobileNumber;
	private String userEmail;
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	private boolean isActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddressTitle() {
		return addressTitle;
	}

	public void setAddressTitle(String addressTitle) {
		this.addressTitle = addressTitle;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DeliveryAddresses [id=" + id + ", addressTitle=" + addressTitle + ", streetName=" + streetName
				+ ", location=" + location + ", city=" + city + ", pincode=" + pincode + ", personName=" + personName
				+ ", state=" + state + ", mobileNumber=" + mobileNumber + ", userEmail=" + userEmail + ", isActive="
				+ isActive + "]";
	}
}
