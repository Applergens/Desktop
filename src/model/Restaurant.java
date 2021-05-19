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
	
	public Restaurant(String address, int code, String name, String password, String phone) {
		this.address = address;
		this.code = code;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.dishes = new ArrayList<Dish>();
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
	
	public void removeDish(Dish dish) {
		
		dishes.add(dish);
		
	}

	public void setPassword(String text) {
		
		this.password = text;
		
	}
	
}
