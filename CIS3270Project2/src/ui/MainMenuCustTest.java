package ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import users.Flight;

public class MainMenuCustTest extends Database {
	@FXML private javafx.scene.control.Button bookFlightButton;
	@FXML private javafx.scene.control.Button cancelFlightButton;
	@FXML private javafx.scene.control.TextField searchField;
	
	
	@FXML private TableView<Flight> availableFlights;
	@FXML private TableView<Flight> customerFlights;
	
	@FXML private TableColumn<Flight, Integer> flightNumberColumn;
	@FXML private TableColumn<Flight, String> fromColumn;
	@FXML private TableColumn<Flight, String> toColumn;
	@FXML private TableColumn<Flight, SimpleDateFormat> departureDateColumn;
	@FXML private TableColumn<Flight, SimpleDateFormat> arrivalDateColumn;
	@FXML private TableColumn<Flight, String> numsOfPassengersColumn;
	
	ObservableList<Flight> listOfFlights = FXCollections.observableArrayList();
	
	@FXML private TableColumn<Flight, Integer> myFlightNumberColumn;
	@FXML private TableColumn<Flight, String> myFromColumn;
	@FXML private TableColumn<Flight, String> myToColumn;
	@FXML private TableColumn<Flight, SimpleDateFormat> myDepartureDateColumn;
	@FXML private TableColumn<Flight, SimpleDateFormat> myArrivalDateColumn;
	@FXML private TableColumn<Flight, String> myNumsOfPassengersColumn;
	
	ObservableList<Flight> myFlights = FXCollections.observableArrayList();
	
	@FXML
	private void initialize() throws Exception {
		Connection connection = getConnection();
		
		//The code below is for all of the available flights
		
		try { //This is for all flights available

			ResultSet results = connection.createStatement().executeQuery("SELECT f.flightnumber, cityfrom, cityto, DATE_FORMAT(departure,'%M %e, %Y at %r'), DATE_FORMAT(arrival,'%M %e, %Y at %r'), COUNT(DISTINCT ssn) FROM Flight f inner join UsersInFlight uif on f.flightnumber = uif.flightnumber GROUP BY flightnumber;");
			
			while (results.next()) {
				listOfFlights.add(new Flight(
						Integer.parseInt(results.getString("flightnumber")), 
						results.getString("cityfrom"), 
						results.getString("cityto"), 
						results.getString("DATE_FORMAT(departure,'%M %e, %Y at %r')"), 
						results.getString("DATE_FORMAT(arrival,'%M %e, %Y at %r')"), 
						Integer.parseInt(results.getString("COUNT(DISTINCT ssn)"))));
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
		
		

		/************************************************************************************************************************************/
		
		//The code below is for the flights that the user has reserved
		
		try { // this is for all flights attached to the user Connection connection =
			  ResultSet results = connection.createStatement().executeQuery("SELECT f.flightnumber, f.cityfrom, f.cityto, DATE_FORMAT(f.departure, '%M %e, %Y at %r'), DATE_FORMAT(f.arrival, '%M %e, %Y at %r'), COUNT(DISTINCT ssn) from Flight f INNER JOIN UsersInFlight uif on f.flightnumber = uif.flightnumber WHERE ssn=" + user.getSsn() +  " group BY flightnumber;");
		  
			  while (results.next()) { 
				  myFlights.add(new Flight(
				  Integer.parseInt(results.getString("flightnumber")),
				  results.getString("cityfrom"), 
				  results.getString("cityto"),
				  results.getString("DATE_FORMAT(f.departure, '%M %e, %Y at %r')"),
				  results.getString("DATE_FORMAT(f.arrival, '%M %e, %Y at %r')"),
				  Integer.parseInt(results.getString("COUNT(DISTINCT ssn)")))); 
			  }
		  
		  } 
		  catch (Exception e) {
			  System.out.print(e);
		  }
		  
		  finally {
			  connection.close();
		  }
		
		myFlightNumberColumn.setCellValueFactory(new PropertyValueFactory<>("flightNumber")); // these are the variables from the class
		myFromColumn.setCellValueFactory(new PropertyValueFactory<>("cityFrom"));
		myToColumn.setCellValueFactory(new PropertyValueFactory<>("cityTo"));
		myDepartureDateColumn.setCellValueFactory(new PropertyValueFactory<>("departure"));
		myArrivalDateColumn.setCellValueFactory(new PropertyValueFactory<>("arrival"));
		myNumsOfPassengersColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfPassengers"));
		
		customerFlights.setItems(myFlights);
		
		FilteredList<Flight> filtered = new FilteredList<>(listOfFlights, b -> true);
		
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filtered.setPredicate(flight -> {
				
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowercaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(flight.getFlightNumber()).indexOf(lowercaseFilter) != -1) {
					return true;
				}
				else if (flight.getDeparture().toLowerCase().indexOf(lowercaseFilter) != -1) {
					return true;
				}
				else if (flight.getArrival().toLowerCase().indexOf(lowercaseFilter) != -1) {
					return true;
				}
				else if (flight.getCityFrom().toLowerCase().indexOf(lowercaseFilter) != -1) {
					return true;
				}
				else if (flight.getCityTo().toLowerCase().indexOf(lowercaseFilter) != -1) {
					return true;
				}
				else {
					return false;
				}

				
			});
		});
		
		SortedList<Flight> sortedData = new SortedList<>(filtered);
		sortedData.comparatorProperty().bind(availableFlights.comparatorProperty());
		availableFlights.setItems(sortedData);
		
	}
	
	@FXML
	private void switchToSignIn(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("UI.fxml")); //get FMXL file
		
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(scene);
		window.show();
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
			alert.setHeaderText("Table added!");
			
			alert.showAndWait();
			
			
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
	
	private static String parseStringDateIntoDBDate(String str) { // this has to be called when making a query from the table, because the table has a different format than the database
		String[] splittedString = str.split(" ");
		
		String year = splittedString[2];
		String month = null;
		
		switch(splittedString[0]) {
		case "January":
			month = "1"; break;
		case "Feburary":
			month = "2"; break;
		case "March":
			month = "3"; break;
		case "April":
			month = "4"; break;
		case "May":
			month = "5"; break;
		case "June":
			month = "6"; break;
		case "July":
			month = "7"; break;
		case "August":
			month = "8"; break;
		case "September":
			month = "9"; break;
		case "October":
			month = "10"; break;
		case "November":
			month = "11"; break;
		case "December":
			month = "12"; break;
		}
		
		String day = splittedString[1].replaceAll("[^0-9]", "");
		
		String time = null;
		
		if (splittedString[5].equals("PM")) {
			String[] splittedTime = splittedString[4].split(":");
			if (Integer.parseInt(splittedTime[0]) >= 1) {
				int temp = Integer.parseInt(splittedTime[0]) + 12;
				
				time = Integer.toString(temp) + ":" + splittedTime[1] + ":" + splittedTime[2];
			}
			else {
				time = splittedString[4];
			}
		}
		else {
			time = splittedString[4];
		}
		
		return String.format("%s-%s-%s %s", year, month, day, time);
	}
	
	public void bookFlight() throws Exception{
		 
		 Flight selectedFlight = availableFlights.getSelectionModel().getSelectedItem();
		 
		 Connection connection = getConnection();
		 
		 try {
			 
			 if (myFlights.contains(selectedFlight)) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					
					alert.setTitle("Unsuccessful");
					alert.setHeaderText("Information rejected.");
					alert.setContentText("Expand the dialog below to see why...");
					
					TextArea area = new TextArea("You already have this flight selected.");
					alert.getDialogPane().setExpandableContent(area);
					alert.showAndWait();
			 }
			 else {
				 PreparedStatement result = connection.prepareStatement(String.format("INSERT INTO UsersInFlight VALUES (%d, %d)", selectedFlight.getFlightNumber(), user.getSsn()));
				 result.executeUpdate();
				 
				 
				 myFlights.add(selectedFlight);
				 
				 
			 }
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
	
	public void cancelFlight() throws Exception {
		
		Flight selectedFlight = customerFlights.getSelectionModel().getSelectedItem();
		
		Connection connection = getConnection();
		
		try {
			PreparedStatement result = connection.prepareStatement(String.format("DELETE FROM UsersInFlight WHERE flightnumber=%d and ssn=%d", selectedFlight.getFlightNumber(), user.getSsn()));
			result.executeUpdate();
			
			myFlights.remove(selectedFlight);
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
		
		finally {
			connection.close();
		}
	}
	
	@FXML
	private void logOut(ActionEvent event) throws Exception {
		
		listOfFlights.clear();
		
		Parent root = FXMLLoader.load(getClass().getResource("UI.fxml")); //get FMXL file

		
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(scene);
		window.show();
	}
	
}
