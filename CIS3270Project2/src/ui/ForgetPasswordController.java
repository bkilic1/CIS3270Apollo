package ui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ForgetPasswordController {
	
	@FXML private javafx.scene.control.TextField userSecurity;
	@FXML private javafx.scene.control.Button backToSignIn;
	@FXML private javafx.scene.control.PasswordField securityAnswer;
	@FXML private javafx.scene.control.Label incorrectSecurity;
	
private void securityCheck(ActionEvent event) {
		
		String u = userSecurity.getText();
		String ser = securityAnswer.getText();

		
		//will check database if user name is = to security question
		try {
			Connection connection = Database.getConnection();
			ResultSet result = connection.prepareStatement("SELECT password FROM Users WHERE username = '" + u + "' AND securityanswer = '" + ser + "' ").executeQuery();
			
			while (result.next()) {
				if (result.getInt(1) == 1) { // if it's equal then go to main menu
					Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml")); //get FMXL file
					
					Scene scene = new Scene(root);
					Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
					
					window.setScene(scene);
					window.show();
					break;
					
				} else { // show invalid password
					
					incorrectSecurity.setVisible(true);
					
				}
			}
			
		}catch (Exception e) {
			System.out.print(e);
			
		}
		finally {
			
		}
		
	}

	@FXML
	private void switchToSignIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("UI.fxml")); //get FMXL file
	
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
	 
		window.setScene(scene);
		window.show();
	
	}
}
