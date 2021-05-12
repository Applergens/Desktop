package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import com.google.gson.Gson;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Restaurant;
import util.AuthCredentials;
import util.JsonUtils;
import util.ObjectUtils;
import util.RequestUtils;

public class LoginController {
	
	static String endPoint = "/login/restaurant";

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private TextField fieldCode;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnExit;

    @FXML
    private void exit() {
    	
    	System.exit(0);	

    }
    
    @FXML
    public void login(ActionEvent event) throws IOException, InterruptedException {
    	
    	if (fieldCode.getText().equalsIgnoreCase("") || fieldPassword.getText().equalsIgnoreCase("")) {
    		
    		Alert alert = new Alert(AlertType.ERROR, "There are empty fields");
    		
    	} else {
    		
    		AuthCredentials authCredentials = new AuthCredentials(fieldCode.getText(), fieldPassword.getText());
        	
        	String requestBody = new Gson().toJson(authCredentials);
        	
        	String responseBody = RequestUtils.httpPostRequest(endPoint, requestBody);
        	
        	if (responseBody == "Invalid") {
        		
        		Alert alert = new Alert(AlertType.ERROR, "Invalid credentials");
        		
        		alert.show();
        		
        	} else {
        		
        		ObjectUtils.generateAllergens();
        		ObjectUtils.generateIngredients();
        		
        		ObjectUtils.createRestaurant(responseBody);
        		
        		System.out.println("Mi restaurante = " + Main.restaurant.getName());
        		
        		changeScene("MenuView.fxml");
        		
        	
        	}
    		
    	}

  	}
    
    void changeScene(String nextScene) throws IOException {
    	
    	Stage stage = new Stage();
		
		Parent root = FXMLLoader.load(LoginController.class.getResource("../view/" + nextScene));
		
		Scene scene = new Scene(root);
	
		stage.setScene(scene);
		stage.show();
		
		btnLogin.getScene().getWindow().hide();
    	
    }


}
