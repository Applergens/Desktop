package util;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import application.Main;
import model.Allergen;
import model.Dish;
import model.Ingredient;
import model.Restaurant;

public class ObjectUtils {
	
	
	public static void generateAllergens() throws IOException, InterruptedException {
		
		Gson gson = new Gson(); 
		
		String responseBody = RequestUtils.getAllRequest("/allergens");			
			 
		JsonParser jsonParser = new JsonParser();
		
		JsonElement jsonTree = jsonParser.parse(responseBody);
		
		JsonArray allergenList = jsonTree.getAsJsonArray();
		
		for (JsonElement obj : allergenList) {
			
			JsonObject allergen = obj.getAsJsonObject();
			
			String allergenId = allergen.get("_id").getAsString();
			String allergenName = allergen.get("name").getAsString();
			
			Main.allergenList.add(new Allergen(allergenId, allergenName));
			
		}
		
	}
	
	public static void generateIngredients() throws IOException, InterruptedException {
		
		Gson gson = new Gson(); 
		
		String responseBody = RequestUtils.getAllRequest("/ingredients");
		
		JsonParser jsonParser = new JsonParser();
		
		JsonElement jsonTree = jsonParser.parse(responseBody);
		
		JsonArray ingredientList = jsonTree.getAsJsonArray();
		
		for (JsonElement obj : ingredientList) {
			
			JsonObject ingredient = obj.getAsJsonObject();
			
			String ingredientId = ingredient.get("_id").getAsString();
			String ingredientName = ingredient.get("name").getAsString();
			Allergen ingredientAllergen = getAllergenByID(ingredient.get("allergen").getAsString());
			
			Main.ingredientList.add(new Ingredient(ingredientId, ingredientName, ingredientAllergen));
			
		}
				
	}
	
	public static Allergen getAllergenByID(String objectId) {
		
		for (Allergen allergen : Main.allergenList) {
			
			if (allergen.getId().equals(objectId)) {
				
				return allergen;
				
			}
			
		}
		
		return null;
		
	}
	
	public static void createRestaurant(String jsonString) {
		
		JsonParser jsonParser = new JsonParser();
		
		JsonElement jsonTree = jsonParser.parse(jsonString);
		
		JsonObject jsonObject = jsonTree.getAsJsonObject();
		
		String address = jsonObject.get("address").getAsString();
		String name = jsonObject.get("name").getAsString();
		String password = jsonObject.get("password").getAsString();
		String phone = jsonObject.get("phone").getAsString();
		int code = jsonObject.get("code").getAsInt();
		
		Main.restaurant = new Restaurant(address, code, name, password, phone);
		
		JsonElement dishes = jsonObject.get("dishes");
		JsonArray dishesList = dishes.getAsJsonArray();
		
		for (JsonElement obj : dishesList) {
			
			JsonObject dish = obj.getAsJsonObject();
			
			String dishName = dish.get("name").getAsString();
			
			Dish d = new Dish(dishName);
			
			JsonArray dishIngredients = dish.get("ingredients").getAsJsonArray();
			
			for (JsonElement ingredient : dishIngredients) {
				
				d.addIngredient(getIngredientByID(ingredient.getAsString()));			
				
			}
			
			Main.restaurant.addDish(d);
			
		}
		
	}
	
	public static Ingredient getIngredientByID(String objectId) {
		
		for (Ingredient ingredient : Main.ingredientList) {
			
			if (ingredient.getId().equals(objectId)) {
				
				return ingredient;
				
			}
			
		}
		
		return null;
		
	}

}
