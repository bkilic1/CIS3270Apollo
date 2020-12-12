package ui;

import database.Database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class UIController {
	
	@FXML private javafx.scene.control.Button login;
	@FXML private javafx.scene.control.PasswordField password;
	@FXML private javafx.scene.control.TextField username;
	@FXML private javafx.scene.control.Label incorrectPassword;
	@FXML private javafx.scene.control.Button signUpButton;
	@FXML private javafx.scene.control.Button forgotPassbutton;

	@FXML
	private void loginUser(ActionEvent event) {
		String u = username.getText();
		String p = password.getText();
		
		try {
			Connection connection = Database.getConnection();
			ResultSet result = connection.prepareStatement("SELECT COUNT(1) FROM Users WHERE username = '" + u + "' AND password = '" + p + "' ").executeQuery();
			
			while (result.next()) {
				if (result.getInt(1) == 1) { // if it's equal then go to main menu
					Parent root = FXMLLoader.load(getClass().getResource("MainMenuAdmin.fxml")); //get FMXL file
					
					Scene scene = new Scene(root);
					Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
					
					window.setScene(scene);
					window.show();
					break;
					
					
				} else { // show invalid password
					incorrectPassword.setVisible(true);
				}
			}
			
		}catch (Exception e) {
			System.out.print(e);
			
		}
		finally {
			
		}
		
		
	}
	
	@FXML
	private void switchToSignUp(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml")); //get FMXL file
		
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(scene);
		window.show();
		
	}
	
	@FXML
	private void forgotPassword(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("ForgotPasswordScreen.fxml")); //get FMXL file
		
		
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(scene);
		window.show();
		
	}
	
	@FXML
	private void test() throws IOException {
		System.out.print("hello");
	}
	
	

}
