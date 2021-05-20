package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class MenuController  implements Initializable{
	
	double xOffset = 0;
	double yOffset = 0;

	 @FXML
    private Label lblMenu;

    @FXML
    private Button btnExit;

    @FXML
    private Button consultDishesBtn;

    @FXML
    private Button btn1;

    @FXML
    private Button profileBtn;

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
		
		btnExit.getScene().getWindow().hide();
    	
    }
    
    @FXML
    void manageDishes(ActionEvent event) throws IOException {
    	    	
    	changeScene("ManageView.fxml");
    }
    
    @FXML
    void consultIngredients(ActionEvent event) throws IOException{
    	changeScene("ConsultIngredientsView.fxml");
    }
    
    @FXML
    void profile(ActionEvent event) throws IOException{
    	changeScene("ProfileView.fxml");
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		lblMenu.setText("Gestor " + '"' + Main.restaurant.getName() + '"');
		
	}

}
