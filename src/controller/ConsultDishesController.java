package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Allergen;
import model.Ingredient;

public class ConsultDishesController implements Initializable{
	
	double xOffset = 0;
	double yOffset = 0;
	
	@FXML
    private Button exitBtn;
	
	@FXML
    private TableView<tableData> ingInfoTable;

    @FXML
    private TableColumn<Ingredient, String> ingColumn;

    @FXML
    private TableColumn<Allergen, String> allergenColumn;
    
    final ObservableList<tableData> data = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for (Ingredient ingredient : Main.ingredientList) {
			data.add(new tableData(ingredient.getName(), ingredient.getAllergen().getName()));
		}
		
		ingColumn.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("ingName"));
		allergenColumn.setCellValueFactory(new PropertyValueFactory<Allergen, String>("allergenName"));
		
		ingInfoTable.setItems(data);
	}
	
	public static class tableData {
		
		private SimpleStringProperty ingName;
		private SimpleStringProperty allergenName;
		
		public tableData(String ingredientName, String allergName) {
			this.ingName = new SimpleStringProperty(ingredientName);
			this.allergenName = new SimpleStringProperty(allergName);
		}

		public String getIngName() {
			return ingName.get();
		}

		public String getAllergenName() {
			return allergenName.get();
		}
		
	}
	
	@FXML
    void exit(ActionEvent event) throws IOException {
    	
    	Stage stage = new Stage();
    	
    	Parent root = FXMLLoader.load(Main.class.getResource("../view/MenuView.fxml"));
		
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
		
		exitBtn.getScene().getWindow().hide();

    }

}
