package model;

public class Restaurant {
	
	String id;
	String name;
	String code;
	String password;
	String address;
	Dish[] dishes;
	String phone;
	
	public Restaurant(String code, String password) {
		
		this.code = code;
		this.password = password;
		
	}
	
}
