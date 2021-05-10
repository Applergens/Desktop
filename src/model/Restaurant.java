package model;

import java.util.ArrayList;

public class Restaurant {
	
	String address;
	int code;
	String name;
	String password;
	String phone;
	ArrayList<Dish> dishes;
	
	public Restaurant(String code, String password) {
		
		this.code = Integer.valueOf(code);
		this.password = password;
		
	}

	public String getAddress() {
		return address;
	}

	public int getCode() {
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

	public ArrayList<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(ArrayList<Dish> dishes) {
		this.dishes = dishes;
	}
	
	public void addDish(Dish dish) {
		
		dishes.add(dish);
		
	}
	
}
