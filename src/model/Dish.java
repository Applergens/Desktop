package model;

import java.util.ArrayList;

public class Dish {
	
	String id;
	String name;
	ArrayList<Ingredient> ingredients;
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void addIngredient(Ingredient ingredient) {
		
		ingredients.add(ingredient);
		
	}
	

}
