package model;

public class Ingredient {
	
	String id;
	String name;
	Allergen allergen;
	
	public Ingredient(String id, String name, Allergen allergen) {
		super();
		this.id = id;
		this.name = name;
		this.allergen = allergen;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Allergen getAllergen() {
		return allergen;
	}
	
}
