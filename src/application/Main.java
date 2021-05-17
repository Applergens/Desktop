package application;
	
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Allergen;
import model.Ingredient;
import model.Restaurant;

public class Main extends Application {
	
	public static Restaurant restaurant;
	
	public static ArrayList<Allergen> allergenList;
	public static ArrayList<Ingredient> ingredientList;
	
	Stage stage;
	
	private double xOffset = 0;
	private double yOffset = 0;
	
	@Override
	public void start(Stage stage) {
		
		this.stage = stage;

		try {
			
			initializeStructures();
			launchLogin();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		
		launch(args);
		
	}
	
	private void initializeStructures() {

		allergenList = new ArrayList<Allergen>();
		ingredientList = new ArrayList<Ingredient>();
		
	}
	
	public void launchLogin() throws IOException {
		
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
		
	}
}
