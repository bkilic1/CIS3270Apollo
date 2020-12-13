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
import users.User;


public class UIController extends Database {
	
	@FXML private javafx.scene.control.Button login;
	@FXML private javafx.scene.control.PasswordField password;
	@FXML private javafx.scene.control.TextField username;
	@FXML private javafx.scene.control.Label incorrectPassword;
	@FXML private javafx.scene.control.Button signUpButton;
	@FXML private javafx.scene.control.Button forgotPassbutton;
	
	@FXML private javafx.scene.control.Button loginEmployee;

	@FXML
	private void loginUser(ActionEvent event) throws Exception {
		String u = username.getText();
		String p = password.getText();
		Connection connection = Database.getConnection();
		
		try {
			
			ResultSet result = connection.prepareStatement("SELECT * FROM Users WHERE username = '" + u + "' AND password = '" + p + "' ").executeQuery();

			if (result.isBeforeFirst() == false) { // show invalid password
				incorrectPassword.setVisible(true);
			}
			else {
				while (result.next()) {
					User currentUser = new User(
								result.getInt("ssn"), // make the User object of whoever is logged in 
								result.getString("firstname"),
								result.getString("lastname"),
								result.getString("email"),
								result.getString("streetadress"),
								result.getInt("zip"),
								result.getString("statecode"),
								result.getString("username"),
								result.getString("password"),
								result.getString("securityanswer"),
								result.getBoolean("isemployee")
								);
						setUser(currentUser);
						
						if (currentUser.isEmployee() == true) {
							Parent root = FXMLLoader.load(getClass().getResource("MainMenuAdmin.fxml")); //get FMXL file

							
							Scene scene = new Scene(root);
							Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
							
							window.setScene(scene);
							window.show();
							
						}
						
						  else { 
							  Parent root = FXMLLoader.load(getClass().getResource("MainMenuCustTest.fxml")); //get FMXL file
							  
							  
							  Scene scene = new Scene(root); 
							  Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
							  
							  window.setScene(scene); window.show();
						  
						  }
				}
				
			}
				
		}
		catch (Exception e) {
			System.out.print(e);
			
		}
		finally {
			connection.close();
		}
	}
	
	@FXML
	private void loginEmployee(ActionEvent event) {
		String u = username.getText();
		String p = password.getText();
		
		try {
			Connection connection = Database.getConnection();
			ResultSet result = connection.prepareStatement("SELECT COUNT(1) FROM Users WHERE username = '" + u + "' AND password = '" + p + "' ").executeQuery();
			
			while (result.next()) {
				if (result.getInt(1) == 1 && u.contains("employee")) { // if it's equal then go to main menu admin
					Parent root = FXMLLoader.load(getClass().getResource("MainMenuCust.fxml")); //get FMXL file
					
					Scene scene = new Scene(root);
					Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
					
					window.setScene(scene);
					window.show();
					
				
					
					
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
	
	@Override
	protected void insertStatement(String table, String query, ActionEvent event) throws Exception {
		return;
		
	}
	

}
