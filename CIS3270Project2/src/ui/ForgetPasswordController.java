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
	@FXML private javafx.scene.control.Label showPassword;
	
	@FXML
	private void securityCheck(ActionEvent event) {
		
		String u = userSecurity.getText();
		String ser = securityAnswer.getText();

		//test
		//will check database if user name is = to security question
		try {
			Connection connection = Database.getConnection();
			ResultSet result = connection.prepareStatement("SELECT password FROM Users WHERE username = '" + u + "' AND securityanswer = '" + ser + "' ").executeQuery();
		
			
			while (result.next()) {
				
				showPassword.setText(result.getString("password"));
				break;

			}
			
		}
		
		catch (Exception e) {
			System.out.print(e);
			
		}
		finally {
			
		}
		
	}

	@FXML
	private void switchToSignIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml")); //get FMXL file
	
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
	 
		window.setScene(scene);
		window.show();
	
	}
}
