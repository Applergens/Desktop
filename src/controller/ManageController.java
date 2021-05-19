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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Dish;
import model.Ingredient;
import util.JsonUtils;
import util.RequestUtils;

public class ManageController implements Initializable{
	
	double xOffset = 0;
	double yOffset = 0;
	
	@FXML
    private Button btnExit;

    @FXML
    private Button editDish;

    @FXML
    private Button createDish;

    @FXML
    private Button deleteDishBtn;
    
    @FXML
    private ListView<Dish> dishList;
    

    public static Dish editableDish;
    
    Dish dish;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for (Dish d : Main.restaurant.getDishes()) {
			
			dishList.getItems().add(d);
		
		}
		
	}
	
	void changeScene(String nextScene) throws IOException {
    	
    	Stage stage = new Stage();
    	
    	Parent root = FXMLLoader.load(EditController.class.getResource("../view/" + nextScene));
    	
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
    	
		btnExit.getScene().getWindow().hide();
    }
	
	@FXML
	void edit() throws IOException {
		
		Alert alert;
		
		if(dishList.getSelectionModel().getSelectedIndex() == -1) {
			alert = new Alert(AlertType.ERROR, "No se ha seleccionado ningun plato");
		} else {
			int selectedDish = dishList.getSelectionModel().getSelectedIndex();
			editableDish = Main.restaurant.getDishes().get(selectedDish);
			
			changeScene("EditView.fxml");
		}
	}
	
	@FXML
	void create() throws IOException {
		changeScene("CreateView.fxml");
	}
	
	@FXML
	void deleteDish() throws IOException, InterruptedException{
		
		Alert alert;
		
		if(dishList.getSelectionModel().getSelectedIndex() == -1) {
			alert = new Alert(AlertType.ERROR, "No se ha seleccionado ningun plato");
		} else {
			int selectedDish = dishList.getSelectionModel().getSelectedIndex();
			
			alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setTitle("Confirmación");
			alert.setContentText("¿Seguro que quieres eliminar el plato?");
			Optional<ButtonType> action = alert.showAndWait();
			
			if(action.get() == ButtonType.OK) {
				
				String dishName = dishList.getItems().get(selectedDish).getName();
				
				dishList.getItems().remove(selectedDish);
				Main.restaurant.getDishes().remove(selectedDish);
				
				String requestBody = JsonUtils.deleteDishData(dishName);
				
				RequestUtils.httpPostRequest("/restaurants/deleteDish", requestBody);
				
				alert = new Alert(AlertType.INFORMATION, "Plato borrado correctamente");
				
				alert.showAndWait();
				
			} else {
				alert = new Alert(AlertType.ERROR, "Plato no eliminado");
				alert.show();
			}
		}
	}
	
	@FXML
    void exit(ActionEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	
    	Parent root = FXMLLoader.load(Main.class.getResource("../view/MenuView.fxml"));
		
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
		
		editDish.getScene().getWindow().hide();

    }
    
}
