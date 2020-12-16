package ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import users.Flight;


public class MainMenuAdmin extends Database {

	@FXML private javafx.scene.control.Button add;
	@FXML private javafx.scene.control.Button delete;
	@FXML private javafx.scene.control.Button addFlightButton;
	@FXML private javafx.scene.control.TextField searchField;
	@FXML private TextField flightNumberField;
	@FXML private TextField fromField;
	@FXML private TextField toField;
	@FXML private TextField departureTimeField;
	@FXML private TextField arrivalTimeField;
	
	
	@FXML private TableView<Flight> availableFlights;
	@FXML private TableView<Flight> customerFlights;
	
	@FXML private TableColumn<Flight, Integer> flightNumberColumn;
	@FXML private TableColumn<Flight, String> fromColumn;
	@FXML private TableColumn<Flight, String> toColumn;
	@FXML private TableColumn<Flight, SimpleDateFormat> departureDateColumn;
	@FXML private TableColumn<Flight, SimpleDateFormat> arrivalDateColumn;
	@FXML private TableColumn<Flight, String> numsOfPassengersColumn;
	
	static ObservableList<Flight> listOfFlights = FXCollections.observableArrayList();
	
	@FXML private TableColumn<Flight, Integer> myFlightNumberColumn;
	@FXML private TableColumn<Flight, String> myFromColumn;
	@FXML private TableColumn<Flight, String> myToColumn;
	@FXML private TableColumn<Flight, SimpleDateFormat> myDepartureDateColumn;
	@FXML private TableColumn<Flight, SimpleDateFormat> myArrivalDateColumn;
	@FXML private TableColumn<Flight, String> myNumsOfPassengersColumn;
	
	static ObservableList<Flight> myFlights = FXCollections.observableArrayList();
	
	@FXML
	private void initialize() throws Exception {
		Connection connection = getConnection();
		
		//The code below is for all of the available flights
		
		try { //This is for all flights available
			
			PreparedStatement statement = connection.prepareStatement("SELECT f.flightnumber, cityfrom, cityto, DATE_FORMAT(departure,'%M %e, %Y at %r'), DATE_FORMAT(arrival,'%M %e, %Y at %r'), COUNT(DISTINCT ssn) FROM Flight f inner join UsersInFlight uif on f.flightnumber = uif.flightnumber GROUP BY flightnumber;");
			ResultSet results = statement.executeQuery();
			
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
		
		try { // this is for all flights attached to the user Connection connection = //PreparedStatement insertStatement = connection.prepareStatement(String.format("INSERT INTO %s VALUES (%s);", table, query));
			
				PreparedStatement statement = connection.prepareStatement("SELECT f.flightnumber, f.cityfrom, f.cityto, DATE_FORMAT(f.departure, '%M %e, %Y at %r'), DATE_FORMAT(f.arrival, '%M %e, %Y at %r'), COUNT(DISTINCT ssn) from Flight f INNER JOIN UsersInFlight uif on f.flightnumber = uif.flightnumber WHERE ssn=" + user.getSsn() +  " group BY flightnumber;");
				ResultSet results = statement.executeQuery();
		  
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
	
	@Override
	protected void insertStatement(String table, String query, ActionEvent event) throws Exception {
		Connection connection = getConnection();
		
		try {
			PreparedStatement insertStatement = connection.prepareStatement(String.format("INSERT INTO %s VALUES (%s);", table, query));
			insertStatement.executeUpdate();
			
			// if successful
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Flight added!");
			
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
			month = "01"; break;
		case "February":
			month = "02"; break;
		case "March":
			month = "03"; break;
		case "April":
			month = "04"; break;
		case "May":
			month = "05"; break;
		case "June":
			month = "06"; break;
		case "July":
			month = "07"; break;
		case "August":
			month = "08"; break;
		case "September":
			month = "09"; break;
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
			if (Integer.parseInt(splittedTime[0]) >= 1 && Integer.parseInt(splittedTime[0]) < 12) {
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
		
		return String.format("%s-%s-%s %s", year, day, month, time);
	}
	
	private boolean isMaxCapacity(Flight flight) { // this methods checks if the flight is at max capacity
		System.out.println(flight.getNumberOfPassengers());
		return flight.getNumberOfPassengers() >= 5 ? true : false;
	}
	
	static boolean flightConflicts(Flight flight, ObservableList<Flight> ol) throws ParseException { // this method checks if the flight selected conflicts with other flights that you have reserved
		
		String parsed1 = parseStringDateIntoDBDate(flight.getDeparture()); //flight selected
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
		Date flightDeparture = formatter.parse(parsed1);
		System.out.println(parsed1);
		System.out.println(flightDeparture);
		
		String parsed2 = parseStringDateIntoDBDate(flight.getArrival()); //flight selected
		Date flightArrival = formatter.parse(parsed2);
		
		System.out.print(flightArrival);
		
		for (Flight element : ol) {
			
			String parsedDeparture = parseStringDateIntoDBDate(element.getDeparture());
			Date departure = formatter.parse(parsedDeparture);
			
			String parsedArrival = parseStringDateIntoDBDate(element.getArrival());
			Date arrival = formatter.parse(parsedArrival);
			
			
			  if (flightDeparture.before(arrival) && flightDeparture.after(departure) ||
					  flightArrival.before(arrival) && flightArrival.after(departure)) { 
				  return true; 
				  }
		}
		
		return false;
	}

	//create flight, has to be booked to yourself 
	@FXML
	private void bookFlight() throws Exception{
		 
		 Flight selectedFlight = availableFlights.getSelectionModel().getSelectedItem();
		 
		 Connection connection = getConnection();
		 
		 try {
			 
			 if (flightConflicts(selectedFlight, myFlights)) {
				 	Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Unsuccessful");
					alert.setHeaderText("You have a flight conflict");
					
					alert.showAndWait();
					return;
			 }
			 
			 if (isMaxCapacity(selectedFlight)) {
				 	Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Unsuccessful");
					alert.setHeaderText("Max Capacity");
					
					alert.showAndWait();
					return;
			 }
			 
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
	
	//book a new flight,
	private void bookFlight(Flight newFlight) throws Exception {
		 
		 Connection connection = getConnection();
		 
		 try {
				 PreparedStatement result = connection.prepareStatement(String.format("INSERT INTO UsersInFlight VALUES (%d, %d)", newFlight.getFlightNumber(), user.getSsn()));
				 result.executeUpdate();
				 
				 myFlights.add(newFlight);
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
	private void cancelFlight() throws Exception {
		
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
	private void addFlight(ActionEvent event) throws Exception {
		 Connection connection = getConnection();
		 
		 int flightNumber;
		 
			flightNumber = flightNumberField.getText().equals("") ? flightNumber = (int) (Math.random() * 9999) + 100 : Integer.parseInt(flightNumberField.getText());
			String from = fromField.getText();
			String to = toField.getText();
			String departure = departureTimeField.getText();
			String arrival = arrivalTimeField.getText();
			int numOfPassengers = 1;
			

		 
		 try {
			
			Flight newFlight = new Flight(
					flightNumber,
					from,
					to,
					departure,
					arrival,
					numOfPassengers
					);
			
			PreparedStatement result = connection.prepareStatement(String.format("INSERT INTO Flight VALUES (%d, '%s', '%s', '%s', '%s', %d)",
					flightNumber,
					from,
					to,
					departure,
					arrival,
					numOfPassengers));
			result.executeUpdate();
				 
			listOfFlights.add(newFlight);
			bookFlight(newFlight);
			
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			
			alert.setTitle("Successful");
			alert.setHeaderText("Flight added!");
			alert.showAndWait();
			
			flightNumberField.clear(); fromField.clear(); toField.clear(); departureTimeField.clear(); arrivalTimeField.clear();
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
	private void deleteFlight() throws Exception {
		
		Flight selectedFlight = availableFlights.getSelectionModel().getSelectedItem();
		
		Connection connection = getConnection();
		
		try {
			PreparedStatement result = connection.prepareStatement(String.format("DELETE FROM UsersInFlight WHERE flightnumber=%s", selectedFlight.getFlightNumber()));
			result.executeUpdate();
			
			PreparedStatement result2 = connection.prepareStatement(String.format("DELETE FROM Flight WHERE flightnumber=%s", selectedFlight.getFlightNumber()));
			result2.executeUpdate();
			
			listOfFlights.remove(selectedFlight);
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
		
		Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml")); //get FMXL file

		
		Scene scene = new Scene(root);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(scene);
		window.show();
	}
	

}
