package model;

public class Allergen {
	
	String id;
	String name;
		
	public Allergen(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

}
