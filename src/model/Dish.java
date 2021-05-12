package model;

import java.util.ArrayList;

public class Dish {
	
	String name;
	ArrayList<Ingredient> ingredients;
		
	public Dish(String name) {
		super();
		this.name = name;
		this.ingredients = new ArrayList<Ingredient>();
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
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
	
	public void removeIngredient(Ingredient ingredient) {
		
		ingredients.remove(ingredient);
		
	}
	

}
