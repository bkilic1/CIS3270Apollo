package uiOptional;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import users.Flight;


public class MainMenu2 implements Initializable  {
	
	

	@FXML private javafx.scene.control.Button add;
	@FXML private javafx.scene.control.Button delete;
	@FXML private javafx.scene.control.TextField searchField;
	
	
	@FXML private TableView<Flight> tableView;
	
	@FXML private TableColumn<Flight, String> flightNumberColumn;
	@FXML private TableColumn<Flight, String> departureCityColumn;
	@FXML private TableColumn<Flight, String> arrivalCityColumn;
	@FXML private TableColumn<Flight, LocalDate> departureDateColumn;
	@FXML private TableColumn<Flight, LocalDate> arrivalDateColumn;
	@FXML private TableColumn<Flight, String> numsOfPassengersColumn;
	
	

	@Override
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight, String> ("flightNumber"));
		flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight, String> ("cityFrom"));
		flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight, String> ("cityTo"));
		flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight, String> ("departure"));
		flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight, String> ("arrival"));
		flightNumberColumn.setCellValueFactory(new PropertyValueFactory<Flight, String> ("numberOfPassengers"));
		
		tableView.setItems(getFlights());
		
		
	}

	private ObservableList<Flight> getFlights() {
		
		return null;
	}



	
	


	
	

	
	
	
}
