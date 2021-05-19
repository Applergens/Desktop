package controller;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Dish;
import util.CryptUtils;
import util.JsonUtils;
import util.RequestUtils;

public class ProfileController implements Initializable{
	
	double xOffset = 0;
	double yOffset = 0;
	
	@FXML
    private Button exitBtn;

    @FXML
    private Label nameLbl;

    @FXML
    private Label codeLbl;

    @FXML
    private TextField phoneTxtFld;

    @FXML
    private TextArea addressTxtArea;

    @FXML
    private Button modifyPhoneBtn;

    @FXML
    private Button modifyAddressBtn;
    
    @FXML
    private Button modifyPassBtn;

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;

    @FXML
    private Button acceptBtn;

    @FXML
    private PasswordField passFld1;

    @FXML
    private PasswordField passFld2;
    
    boolean isChanging;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	isChanging = false;

    	fillUpData();
    			
	}
    
    void fillUpData() {
    	nameLbl.setText(Main.restaurant.getName());
    	codeLbl.setText(Integer.toString(Main.restaurant.getCode()));
    	phoneTxtFld.setText(Main.restaurant.getPhone());
    	addressTxtArea.setText(Main.restaurant.getAddress());
    	disableChangePass();
    }
    
    void disableChangePass() {
    	double invisibleOpacity = 0;
    	passFld1.setText("");
    	passFld2.setText("");
    	lbl1.setOpacity(invisibleOpacity);
    	lbl2.setOpacity(invisibleOpacity);
    	passFld1.setOpacity(invisibleOpacity);
    	passFld2.setOpacity(invisibleOpacity);
    	acceptBtn.setOpacity(invisibleOpacity);
    }
    
    void enableChangePass() {
    	double visibleOpacity = 1;
    	passFld1.setText("");
    	passFld2.setText("");
    	lbl1.setOpacity(visibleOpacity);
    	lbl2.setOpacity(visibleOpacity);
    	passFld1.setOpacity(visibleOpacity);
    	passFld2.setOpacity(visibleOpacity);
    	acceptBtn.setOpacity(visibleOpacity);
    }
    
    @FXML
    void modifyAddress(ActionEvent event) throws IOException{
    	
    	if (Main.restaurant.getAddress().equals(addressTxtArea.getText())) {
    		Alert alert = new Alert(AlertType.ERROR, "Dirección no modificada");
    		alert.show();
    	} else if (addressTxtArea.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR, "Campo de la dirección vacio!");
    		alert.show();
    	} else {
    		
    		Main.restaurant.setAddress(addressTxtArea.getText());
    		
    		isChanging = true;
    		
    	}
    	
    }

    @FXML
    void modifyPhone(ActionEvent event) throws IOException{
    	if (Main.restaurant.getPhone().equals(phoneTxtFld.getText())) {
    		Alert alert = new Alert(AlertType.ERROR, "Telefono no modificado");
    		alert.show();
    	} else if (phoneTxtFld.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR, "Campo del telefono vacio!");
    		alert.show();
    	} else {
    		
    		Main.restaurant.setPhone(phoneTxtFld.getText());
    		
    		isChanging = true;
    		
    	}
    }
    
    @FXML
    void modifyPass(ActionEvent event) throws IOException{
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmación");
		alert.setContentText("¿Seguro que quieres modificar la contraseña?");
		Optional<ButtonType> action = alert.showAndWait();
		if(action.get() == ButtonType.OK) {
			enableChangePass();
		} else {
			disableChangePass();
			alert = new Alert(AlertType.CONFIRMATION, "Cancelado modificar contraseña");
		}
    }
    
    @FXML
    void acceptChangePass(ActionEvent event) throws IOException, NoSuchAlgorithmException{
    	
    	if(passFld1.getText().equals("") || passFld2.getText().equals("") || !passFld1.getText().equals(passFld2.getText())) {
    		
    		System.out.println("SoMETHING WRONG");
    		
    	} else {
    		
    		final MessageDigest digest = MessageDigest.getInstance("SHA-256");
    		
    		final byte[] hashbytes = digest.digest(passFld1.getText().getBytes(StandardCharsets.UTF_8));
    		
    		String password = CryptUtils.bytesToHex(hashbytes);
    		
    		Main.restaurant.setPassword(password);
    	   		
    		isChanging = true;
    		
    		disableChangePass();
    	}	
    }

    @FXML
    void exit(ActionEvent event) throws IOException, InterruptedException {
    	
    	RequestUtils.httpPostRequest("/restaurants/updateData", JsonUtils.updateRestaurantData());
    	
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
