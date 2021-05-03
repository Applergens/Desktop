package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Main extends Application {
	
	Stage stage;
	
	private double xOffset = 0;
	private double yOffset = 0;
	
	@Override
	public void start(Stage stage) {
		
		this.stage = stage;

		
		try {
			
			launchLogin();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
    public void stop() {
    	
		try {
			
			launchLogin();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	
    }
	
	public void launchLogin() throws IOException {
		
		Parent root = FXMLLoader.load(Main.class.getResource("LoginView.fxml"));
		
		Scene scene = new Scene(root);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
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
