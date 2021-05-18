package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import application.Main;

public class JsonUtils {
	
	public static String jsonUpdateRestaurantData() {
		
		JsonObject data = new JsonObject();
		
		data.addProperty("code", Main.restaurant.getCode());
		data.addProperty("password", Main.restaurant.getPassword());
		data.addProperty("phone", Main.restaurant.getPhone());
		data.addProperty("address", Main.restaurant.getAddress());
		
		String jsonString = new Gson().toJson(data);
		
		return jsonString;
		
	}

}
