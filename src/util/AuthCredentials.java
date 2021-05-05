package util;

import model.Restaurant;

public class AuthCredentials {
	
	Restaurant restaurant;
	
	public AuthCredentials(String code, String password) {
		
		this.restaurant = new Restaurant(code, password);
			
	}
	
}
