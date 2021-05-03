package application;

import java.io.IOException;

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
    	
    	if (fieldCode.getText().equals("1234") && fieldPassword.getText().equals("4321")) {
    		
    		System.out.println("Login correct");
    		
    		Stage stage = new Stage();
    		
    		Parent root = FXMLLoader.load(Main.class.getResource("MenuView.fxml"));
			
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
			stage.setScene(scene);
			stage.show();
			
			btnLogin.getScene().getWindow().hide();
    		
    	} else {
    		
    		System.out.println("Login failed");
    	}

  	}


}
