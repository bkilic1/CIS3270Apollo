package ui;

import database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField; 

public class Controller {
	
	@FXML private javafx.scene.control.Button login;
	@FXML private javafx.scene.control.PasswordField password;
	@FXML private javafx.scene.control.TextField username;
	
	@FXML

	private void loginUser() {
		String u = username.getText();
		String p = password.getText();
		
		try {
			Connection connection = Database.getConnection();
			ResultSet result = connection.prepareStatement("SELECT COUNT(1) FROM Users WHERE username = '" + u + "' AND password = '" + p + "' ").executeQuery();
			
			
			ArrayList<String> response = new ArrayList<String>();
			
			while (result.next()) {
				if (result.getInt(1) == 1) {
					System.out.println("logged in!");
				} else {
					System.out.println("try again..");
				}
			}
			
		}catch (Exception e) {
			System.out.print(e);
			
		}
		finally {
			
		}

		
	}

}
