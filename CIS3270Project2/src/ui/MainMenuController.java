package ui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import users.Flight;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;
import java.util.ResourceBundle;
import database.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController extends Database implements Initializable {

	@FXML private javafx.scene.control.Button logoutButton;
	@FXML private javafx.scene.control.TableView<Flight> availableFlights;
	@FXML private javafx.scene.control.TableColumn<Flight, Integer> col_flightNumber;
	@FXML private javafx.scene.control.TableColumn<Flight, String> col_from;
	@FXML private javafx.scene.control.TableColumn<Flight, String> col_to;
	@FXML private javafx.scene.control.TableColumn<Flight, SimpleDateFormat> col_departure;
	@FXML private javafx.scene.control.TableColumn<Flight, SimpleDateFormat> col_arrival;
	@FXML private javafx.scene.control.TableColumn<Flight, Integer> col_passengers;
	
	ObservableList<Flight> listOfFlights = FXCollections.observableArrayList();
	
	
	
	@Override
	public void initialize (URL Location, ResourceBundle resources) {
		
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
		
		col_flightNumber.setCellValueFactory(new PropertyValueFactory<>("flightNumber")); // these are the variables from the class
		col_from.setCellValueFactory(new PropertyValueFactory<>("cityFrom"));
		col_to.setCellValueFactory(new PropertyValueFactory<>("cityTo"));
		col_departure.setCellValueFactory(new PropertyValueFactory<>("departure"));
		col_arrival.setCellValueFactory(new PropertyValueFactory<>("arrival"));
		col_passengers.setCellValueFactory(new PropertyValueFactory<>("numberOfPassengers"));
		
		availableFlights.setItems(listOfFlights);
		
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
	
	
	

}
