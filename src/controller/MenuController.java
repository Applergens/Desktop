package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Allergen;
import model.Ingredient;
import util.RequestUtils;

public class MenuController  implements Initializable{
	
	double xOffset = 0;
	double yOffset = 0;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btn6;

    @FXML
    private Label lblMenu;

    @FXML
    private Button btnExit;

    @FXML
    void exit(ActionEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	
    	Parent root = FXMLLoader.load(Main.class.getResource("../view/LoginView.fxml"));
		
		Scene scene = new Scene(root);
		
		stage.initStyle(StageStyle.DECORATED.UNDECORATED);

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
		
		btnExit.getScene().getWindow().hide();

    }
    
    void changeScene(String nextScene) throws IOException {
    	
    	Stage stage = new Stage();
		
		Parent root = FXMLLoader.load(LoginController.class.getResource("../view/" + nextScene));
		
		Scene scene = new Scene(root);
	
		stage.setScene(scene);
		stage.show();
		
		btn1.getScene().getWindow().hide();
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			
			Gson gson = new Gson(); 
			
			String responseBody = RequestUtils.getAllRequest("/ingredients");			
			 
			Type ingredientListType = new TypeToken<ArrayList<Ingredient>>(){}.getType();
			 
			ArrayList<Ingredient> ingredientList = gson.fromJson(responseBody, ingredientListType);
			
			for (Ingredient i : ingredientList) {
				
				System.out.println(i.getName());
				
			}
			
			responseBody = RequestUtils.getAllRequest("/allergens");			
			 
			Type allergenListType = new TypeToken<ArrayList<Allergen>>(){}.getType();
			 
			ArrayList<Allergen> allergenList = gson.fromJson(responseBody, allergenListType);
			
			for (Allergen a : allergenList) {
				
				System.out.println(a.getName());
				
			}
			
			responseBody = RequestUtils.getByIdRequest("/allergens" , "6092a36190ee20b75353870a");
			
			Allergen allergen = gson.fromJson(responseBody, Allergen.class);
			
			responseBody = RequestUtils.getByIdRequest("/ingredients" , "6092cd1e90ee20b75353870b");
		
			Ingredient ingredient = gson.fromJson(responseBody, Ingredient.class);
			
			System.out.println(allergen.getName());
			System.out.println(ingredient.getName());
			
		} catch (IOException | InterruptedException e) {
			
			e.printStackTrace();
			
		}
		
	}

}
