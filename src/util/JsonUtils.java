package util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import application.Main;
import model.Dish;
import model.Ingredient;

public class JsonUtils {
	
	public static String updateRestaurantData() {
		
		JsonObject data = new JsonObject();
		
		data.addProperty("code", Main.restaurant.getCode());
		data.addProperty("password", Main.restaurant.getPassword());
		data.addProperty("phone", Main.restaurant.getPhone());
		data.addProperty("address", Main.restaurant.getAddress());
		
		String jsonString = new Gson().toJson(data);
		
		return jsonString;
		
	}
	
	public static String createDishData(Dish d) {
		
		JsonObject data = new JsonObject();
		
		data.addProperty("code", Main.restaurant.getCode());
		
		JsonObject dish = new JsonObject();
		
		dish.addProperty("name", d.getName());

		JsonArray ingredientsList = new JsonArray();
		
		for (Ingredient i : d.getIngredients()) {
			
			ingredientsList.add(i.getId());
			
		}
		
		dish.add("ingredients", ingredientsList);
		
		data.add("dish", dish);
		
		String jsonString = new Gson().toJson(data);
		
		return jsonString;
		
	}
	
	public static String updateDishData(Dish d, String oldDishName) {
		
		JsonObject data = new JsonObject();
		
		data.addProperty("code", Main.restaurant.getCode());
		
		JsonObject dish = new JsonObject();
		
		dish.addProperty("name", d.getName());

		JsonArray ingredientsList = new JsonArray();
		
		for (Ingredient i : d.getIngredients()) {
			
			ingredientsList.add(i.getId());
			
		}
		
		dish.add("ingredients", ingredientsList);
		
		data.add("dish", dish);
		
		data.addProperty("oldName", oldDishName);
		
		String jsonString = new Gson().toJson(data);
		
		return jsonString;
		
	}

	public static String deleteDishData(String dishName) {
		
		JsonObject data = new JsonObject();
		
		data.addProperty("code", Main.restaurant.getCode());
		
		data.addProperty("name", dishName);
		
		String jsonString = new Gson().toJson(data);
		
		return jsonString;
		
	}

}
