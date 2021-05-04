package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Restaurant;
import util.RequestUtils;

public class LoginController {

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
    public void login(ActionEvent event) throws IOException {
    	
    	Boolean login = false;
    	
    	String data = RequestUtils.getJSON("https://apilergens.herokuapp.com/restaurants/getByCode?code="+fieldCode.getText());
    	
    	System.out.println(data);
        	
    	if (login) {
    		    		
    		changeScene();
    		
    	} else {
    		
    		System.out.println("Login failed");
    	}

  	}
    
    void changeScene() throws IOException {
    	
    	Stage stage = new Stage();
		
		Parent root = FXMLLoader.load(Main.class.getResource("MenuView.fxml"));
		
		Scene scene = new Scene(root);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	
		stage.setScene(scene);
		stage.show();
		
		btnLogin.getScene().getWindow().hide();
    	
    }


}
