package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Dish;
import model.Ingredient;

public class EditController implements Initializable{
	
	double xOffset = 0;
	double yOffset = 0;
	
	@FXML
    private Button btnExit;

    @FXML
    private TextField dishNameTxtField;

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
		
		ArrayList<Ingredient> ingredientsDish = ManageController.editableDish.getIngredients();
		
		dishNameTxtField.setText(ManageController.editableDish.getName());
		
		for (Ingredient ing : Main.ingredientList) {
			
			allIngredientsLV.getItems().add(ing);
			
			for (Ingredient ingDish : ingredientsDish) {
				
				if(ing.getName().equals(ingDish.getName())) {
					
					allIngredientsLV.getItems().remove(ing);
					dishIngredientsLV.getItems().add(ingDish);
					
				}
			}
		}
		
	}
	
	@FXML
	void addIng() throws IOException{
		
		int selectedIng = allIngredientsLV.getSelectionModel().getSelectedIndex();
		
		if(selectedIng == -1) {
			Alert alert = new Alert(AlertType.ERROR, "No se ha seleccionado ningun ingrediente!");
			alert.show();
		} else {
			Ingredient ingredient = allIngredientsLV.getItems().get(selectedIng);
			allIngredientsLV.getItems().remove(selectedIng);
			dishIngredientsLV.getItems().add(ingredient);
		}
		
	}
	
	@FXML
	void quitIng() throws IOException{
		int selectedIng = dishIngredientsLV.getSelectionModel().getSelectedIndex();
		
		if(selectedIng != -1) {
			Ingredient ingredient = dishIngredientsLV.getItems().get(selectedIng);
			dishIngredientsLV.getItems().remove(selectedIng);
			allIngredientsLV.getItems().add(ingredient);
		} else {
			Alert alert = new Alert(AlertType.ERROR, "No se ha seleccionado ningun ingrediente!");
			alert.show();
		}
	}
	
	@FXML
	void quitAllIng() throws IOException{
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmación");
		alert.setContentText("¿Seguro que quieres mover todos los ingredientes?");
		Optional<ButtonType> action = alert.showAndWait();
		
		if(action.get() == ButtonType.OK) {
			for (int i = dishIngredientsLV.getItems().size()-1; i >= 0; i--) { 
				Ingredient ingredient = dishIngredientsLV.getItems().get(i);
				dishIngredientsLV.getItems().remove(i);
				allIngredientsLV.getItems().add(ingredient);
			}
		} else {
			alert = new Alert(AlertType.ERROR, "Ingredientes no movidos");
			alert.show();
		}
		
	}
	
	@FXML
	void saveDish() throws IOException{
		
		Dish d;
		
		if(dishIngredientsLV.getItems().size() != 0 && !dishNameTxtField.getText().equals("")) {
			
			d = new Dish(dishNameTxtField.getText());
			
			for (Ingredient i : dishIngredientsLV.getItems()) {
				
				d.addIngredient(i);
				
			}
			
			Main.restaurant.addDish(d);
			
			Main.restaurant.removeDish(ManageController.editableDish);
			
			Alert alert = new Alert(AlertType.INFORMATION, "Plato editado correctamente");
			
			alert.showAndWait();
			
			// Aqui hay que hacer dos llamadas a la api, deleteDish (eliminar el editableDish de la clase ManageController) y 			createDish para crear el nuevo plato editado
			// También habria que mostrar mensaje de confirmación y cerrar ventana/vaciar campos
			
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Introducir nombre y ingredientes");
			alert.show();
		}
		
		
	}
	
	@FXML
    void exit(ActionEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	
    	Parent root = FXMLLoader.load(Main.class.getResource("../view/ManageView.fxml"));
		
		Scene scene = new Scene(root);
		
		stage.initStyle(StageStyle.UNDECORATED);

		root.setOnMousePressed(new EventHandler<MouseEvent>()  {

			@Override
			public void handle(MouseEvent event) {

				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
				
			}
		});
		
		root.setOnMouseDragged(new EventHandler<MouseEvent>()  {

			@Override
			public void handle(MouseEvent event) {

				stage.setX(event.getScreenX() - xOffset);
				stage.setY(event.getScreenY() - yOffset);
				
			}
		});
		
		stage.setScene(scene);
		stage.show();
		
		addIngredientBtn.getScene().getWindow().hide();

    }

}