package ui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignUpController {

	@FXML private javafx.scene.control.Button backToSignIn;
	@FXML private javafx.scene.control.TextField firstNameField;
	@FXML private javafx.scene.control.TextField lastNameField;
	@FXML private javafx.scene.control.TextField emailField;
	@FXML private javafx.scene.control.TextField addressField;
	@FXML private javafx.scene.control.PasswordField ssnField;
	@FXML private javafx.scene.control.TextField cityField;
	@FXML private javafx.scene.control.TextField stateField;
	@FXML private javafx.scene.control.TextField zipField;
	@FXML private javafx.scene.control.TextField usernameField;
	@FXML private javafx.scene.control.PasswordField passwordField;
	@FXML private javafx.scene.control.TextField securityField;
	@FXML private javafx.scene.control.ToggleGroup isEmployee;
	
	@FXML
	private void switchToSignIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("UI.fxml")); //get FMXL file
		
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(scene);
		window.show();
		
	}
	
	@FXML
	private void signUp() {
		System.out.println("Hello!");
		
		try {

			String employee = isEmployee.getSelectedToggle().toString();
			int answer = employee.replaceAll("R.*]", "").substring(1, 3).toString().contains("Ye") ? 1 : 0;
			Connection connection = Database.getConnection();
			PreparedStatement insertStatement = connection.prepareStatement(String.format("INSERT INTO Users VALUES (%s, '%s', '%s', '%s', '%s', %s, '%s', '%s', '%s', '%s', %s);"
					,ssnField.getText(), firstNameField.getText(), lastNameField.getText(), emailField.getText(), addressField.getText(),
					zipField.getText(), stateField.getText(), usernameField.getText(), passwordField.getText(), securityField.getText(), answer));
			
			insertStatement.executeUpdate();
			
			
		}
		catch (Exception e) {
			System.out.println(e);
			
		}
		finally {
			
		}
	}
	
	
}
