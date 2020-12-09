package ui;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import users.Flight;


public class MainMenu2 extends Database implements Initializable  {

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
	
	ObservableList<Flight> listOfFlights = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL Location, ResourceBundle resources) {
		
		try {
			Connection connection = getConnection();
			
			ResultSet results = connection.createStatement().executeQuery("SELECT * FROM Flight");
			
			while (results.next()) {
				listOfFlights.add(new Flight(
						Integer.parseInt(results.getString("flightnumber")), 
						results.getString("cityfrom"), 
						results.getString("cityto"), 
						new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").parse(results.getString("departure")), 
						new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").parse(results.getString("arrival")), 
						Integer.parseInt(results.getString("numberofpassengers"))));
			}
		} 
		catch (Exception e) {
			System.out.print(e);
		}
		
		flightNumberColumn.setCellValueFactory(new PropertyValueFactory<> ("flightNumber"));
		fromColumn.setCellValueFactory(new PropertyValueFactory<> ("cityFrom"));
		toColumn.setCellValueFactory(new PropertyValueFactory<> ("cityTo"));
		departureDateColumn.setCellValueFactory(new PropertyValueFactory<> ("departure"));
		arrivalDateColumn.setCellValueFactory(new PropertyValueFactory<> ("arrival"));
		numsOfPassengersColumn.setCellValueFactory(new PropertyValueFactory<> ("numberOfPassengers"));
		
		availableFlights.setItems(listOfFlights);
	
	}
	
	@Override
	protected void insertStatement(String table, String query, ActionEvent event) throws Exception {
		System.out.println();
	}
	
	
}
