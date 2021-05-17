package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import model.Allergen;
import model.Ingredient;

public class CreateController implements Initializable{
	
	double xOffset = 0;
	double yOffset = 0;
	
	@FXML
    private TextField dishNameTxtFld;

	@FXML
    private ListView<Ingredient> allIngredientLV;

    @FXML
    private ListView<Ingredient> addIngredientLV;
    @FXML
    private Button exitBtn;

    @FXML
    private Button addIngBtn;
    
    @FXML
    private Button quitIngBtn;

    @FXML
    private Button quitAllBtn;

    @FXML
    private Button saveDishBtn;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ArrayList<Ingredient> ingredientList = Main.ingredientList;
		
		for (Ingredient ingredient : ingredientList) {
			allIngredientLV.getItems().add(ingredient);
		}
		
	}
	
	@FXML
	void addIng() throws IOException{
		
		int selectedIng = allIngredientLV.getSelectionModel().getSelectedIndex();
		
		if(selectedIng == -1) {
			Alert alert = new Alert(AlertType.ERROR, "No se ha seleccionado ningun ingrediente!");
			alert.show();
		} else {
			Ingredient ingredient = allIngredientLV.getItems().get(selectedIng);
			allIngredientLV.getItems().remove(selectedIng);
			addIngredientLV.getItems().add(ingredient);
		}
		
	}
	
	@FXML
	void quitIng() throws IOException{
		int selectedIng = addIngredientLV.getSelectionModel().getSelectedIndex();
		
		if(selectedIng != -1) {
			Ingredient ingredient = addIngredientLV.getItems().get(selectedIng);
			addIngredientLV.getItems().remove(selectedIng);
			allIngredientLV.getItems().add(ingredient);
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
			for (int i = addIngredientLV.getItems().size()-1; i >= 0; i--) { 
				Ingredient ingredient = addIngredientLV.getItems().get(i);
				addIngredientLV.getItems().remove(i);
				allIngredientLV.getItems().add(ingredient);
			}
		} else {
			alert = new Alert(AlertType.ERROR, "Ingredientes no movidos");
			alert.show();
		}
		
	}
	
	@FXML
    void saveDish(ActionEvent event) {

		boolean save = checkDish();
		if (!save) {
			Alert alert = new Alert(AlertType.ERROR, "Rellenar campos nombre y ingrediente obligatoriamente");
			alert.show();
		} else {
			// AÑADIR ACCION DE GUARDAR PLATO
		}
    }
	
	boolean checkDish() {
		if (dishNameTxtFld.getText().equals("") || addIngredientLV.getItems().size() == 0) {
			return false;
		} else {
			return true;
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
		
		dishNameTxtFld.getScene().getWindow().hide();

    }
	
}
