package model;

public class Restaurant {
	
	String address;
	String code;
	String name;
	String password;
	String phone;
	
	public Restaurant(String code, String password) {
		
		this.code = code;
		this.password = password;
		
	}

	public String getAddress() {
		return address;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
