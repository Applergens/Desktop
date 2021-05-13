package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Dish;

public class ManageController implements Initializable{
	
	double xOffset = 0;
	double yOffset = 0;

    @FXML
    private Button editDish;

    @FXML
    private Button createDish;

    @FXML
    private Button deleteDish;
    
    @FXML
    private ListView<Dish> dishList;
    
    Main main = new Main();
    
    ArrayList<Dish> dishes = main.restaurant.getDishes();
    
    Dish dish;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for (Dish d : dishes) {
			
			dishList.getItems().add(d);
		
		}
		
	}
	
	void changeScene(String nextScene) throws IOException {
    	
    	Stage stage = new Stage();
    	System.out.println("1111111111111");
    	FXMLLoader loader = FXMLLoader.load(EditController.class.getResource("../view/" + nextScene));
    	System.out.println("2222222222222");
		EditController controller = new EditController();
	    controller.setDish(dish);
    	System.out.println("3333333333333");

	    loader.setController(controller);
    	System.out.println("44444444444444444");

	    Parent root = loader.load();
    	System.out.println("5555555555555555");

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
		
		Scene scene = new Scene(root);
	
		stage.setScene(scene);
		stage.show();
    	
    }
	
	@FXML
	void edit() throws IOException {
		
		int selectedDish = dishList.getSelectionModel().getSelectedIndex();
		dish = dishes.get(selectedDish);
		
		changeScene("EditView.fxml");
	}
    
}
