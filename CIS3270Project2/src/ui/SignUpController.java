package ui;
import database.Database;
import ui.Controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class SignUpController extends Database{

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
	private void signUp(ActionEvent event) {
		
		try {
			String employee = isEmployee.getSelectedToggle().toString();
			int answer = employee.replaceAll("R.*]", "").substring(1, 3).toString().contains("Ye") ? 1 : 0;
			
			String query = String.format("%s, '%s', '%s', '%s', '%s', %s, '%s', '%s', '%s', '%s', %s"
					,ssnField.getText(), firstNameField.getText(), lastNameField.getText(), emailField.getText(), addressField.getText(),
					zipField.getText(), stateField.getText(), usernameField.getText(), passwordField.getText(), securityField.getText(), answer);
			
			insertStatement("Users", query, event);
			
			
		}
		catch (Exception error) {
			System.out.println("error");
		}
	}
	
	@Override
	protected void insertStatement(String table, String query, ActionEvent event) throws Exception {
		Connection connection = getConnection();
		
		try {
			PreparedStatement insertStatement = connection.prepareStatement(String.format("INSERT INTO %s VALUES (%s);", table, query));
			insertStatement.executeUpdate();
			
			// if successful
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Sign In Now!");
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				switchToSignIn(event);
			}
			
			
		}
		catch (Exception e) { // if not successful
			Alert alert = new Alert(Alert.AlertType.ERROR);
			
			alert.setTitle("Unsuccessful");
			alert.setHeaderText("Information rejected.");
			alert.setContentText("Expand the dialog below to see why...");
			
			TextArea area = new TextArea(e.toString());
			alert.getDialogPane().setExpandableContent(area);
			alert.showAndWait();
			
		}
		finally {
			connection.close();
		}
	}
	
}
