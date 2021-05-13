package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Dish;
import model.Ingredient;

public class EditController implements Initializable{
	
	Dish dish;

    @FXML
    private TextField dishNameTxrField;

    @FXML
    private ListView<Ingredient> allIngredientsLV;

    @FXML
    private ListView<Ingredient> dishIngredientsLV;

    @FXML
    private Button quitAllIngredientsBtn;

    @FXML
    private Button quitIngredientBtn;

    @FXML
    private Button addIngredientBtn;

    @FXML
    private Button saveDish;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println(dish.getName());
		
	}
	
	void setDish(Dish dish) {
		this.dish = dish;
	}

}