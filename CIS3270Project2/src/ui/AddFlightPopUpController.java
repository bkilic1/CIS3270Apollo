package ui;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import users.Flight;

public class AddFlightPopUpController extends MainMenuAdmin {
	
	@FXML private TextField flightNumberField;
	@FXML private TextField fromField;
	@FXML private TextField toField;
	@FXML private TextField departureTimeField;
	@FXML private TextField arrivalTimeField;
	
	@FXML
	public void addFlight() throws Exception {
		 Connection connection = getConnection();
		 
			int flightNumber = Integer.parseInt(flightNumberField.getText());
			String from = fromField.getText();
			String to = toField.getText();
			String departure = departureTimeField.getText();
			String arrival = arrivalTimeField.getText();
			int numOfPassengers = 10;
		 
		 try {
			
			Flight newFlight = new Flight(
					flightNumber,
					from,
					to,
					departure,
					arrival,
					numOfPassengers
					);
			
			PreparedStatement result = connection.prepareStatement(String.format("INSERT INTO Flight VALUES (%d, %s, %s, %s, %s, %d)",
					flightNumber,
					from,
					to,
					departure,
					arrival,
					numOfPassengers));
			result.executeUpdate();
				 
			myFlights.add(newFlight);
			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			
			alert.setTitle("Successful");
			alert.setHeaderText("Flight added!");

		 }
		 catch (Exception e) {
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
	
	@FXML
	public void test() {
		System.out.println("yerr");
	}
}
