package uiOptional;

import java.net.URL;
import java.util.Random;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import users.Flight;


public abstract class MainMenuCust extends Database implements Initializable  {
	
	

	@FXML private javafx.scene.control.Button add;
	@FXML private javafx.scene.control.Button delete;
	@FXML private javafx.scene.control.TextField searchField;
	
	
	@FXML private TableView<Flight> availableFlights;
	
	@FXML private TableColumn<Flight, Integer> flightNumberColumn;
	@FXML private TableColumn<Flight, String> fromColumn;
	@FXML private TableColumn<Flight, String> toColumn;
	@FXML private TableColumn<Flight, SimpleDateFormat> departureDateColumn;
	@FXML private TableColumn<Flight, SimpleDateFormat> arrivalDateColumn;
	@FXML private TableColumn<Flight, String> numsOfPassengersColumn;
	
	
	//beginning of userFlight tableview
	ObservableList<Flight> listOfFlights = FXCollections.observableArrayList();
	
	@FXML private TableView<Flight> userSelectedFlight;
	
	@FXML private TableColumn<Flight, Integer> flightNumberColumn2;
	@FXML private TableColumn<Flight, String> fromColumn2;
	@FXML private TableColumn<Flight, String> toColumn2;
	@FXML private TableColumn<Flight, SimpleDateFormat> departureDateColumn2;
	@FXML private TableColumn<Flight, SimpleDateFormat> arrivalDateColumn2;
	@FXML private TableColumn<Flight, String> numsOfPassengersColumn2;

	
	ObservableList<Flight> userFlight = FXCollections.observableArrayList();
	
	int confirm;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			Connection connection = getConnection();
			
			ResultSet results = connection.createStatement().executeQuery("SELECT flightnumber, cityfrom, cityto, DATE_FORMAT(departure,'%M %e, %Y at %r'), DATE_FORMAT(arrival,'%M %e, %Y at %r'), numberofpassengers FROM Flight;");
			
			while (results.next()) {
				listOfFlights.add(new Flight(
						Integer.parseInt(results.getString("flightnumber")), 
						results.getString("cityfrom"), 
						results.getString("cityto"), 
						results.getString("DATE_FORMAT(departure,'%M %e, %Y at %r')"), 
						results.getString("DATE_FORMAT(arrival,'%M %e, %Y at %r')"), 
						Integer.parseInt(results.getString("numberofpassengers"))));
			}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		
		flightNumberColumn.setCellValueFactory(new PropertyValueFactory<>("flightNumber")); // these are the variables from the class
		fromColumn.setCellValueFactory(new PropertyValueFactory<>("cityFrom"));
		toColumn.setCellValueFactory(new PropertyValueFactory<>("cityTo"));
		departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));
		arrivalDateColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
		numsOfPassengersColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfPassengers"));
		
		availableFlights.setItems(listOfFlights);
		
		
	}

	private void handleButtonAction (ActionEvent event) {
		
		ObservableList userFlight = ((TableView<Flight>) listOfFlights).getSelectionModel().getSelectedItems();
		
		
		
	}
	
	
		
	@Override
	protected void insertStatement(String table, String query, ActionEvent event) throws Exception {
			Connection connection = getConnection();
			
			try {
				PreparedStatement insertStatement = connection.prepareStatement(String.format("INSERT INTO BOOKED FLIGHTS (confirmation_number, flightnumber, ssn) values (confirm, flightNumber, ssn)"));
				insertStatement.executeUpdate();
				
				// if successful
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Sign In Now!");
				
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					//switchToSignIn(event);
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
		
		
		
		
	
	
	public int increaser(int n) {
		
		confirm = confirm + 1;
		return confirm;
		
	}
	
	



	
	


	
	

	
	
	
}
