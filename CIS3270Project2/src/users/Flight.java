package users;

import java.util.Date;

public class Flight {
	
	private int flightNumber;
	private String cityFrom;
	private String cityTo;
	private String departure;
	private String arrival;
	private int numberOfPassengers;
	
	
	public Flight(int flightNumber, String cityFrom, String cityTo, String departure, String arrival, int numberOfPassengers) {
		this.flightNumber = flightNumber;
		this.cityFrom = cityFrom;
		this.cityTo = cityTo;
		this.departure = departure;
		this.arrival = arrival;
		this.numberOfPassengers = numberOfPassengers;
	}


	public int getFlightNumber() {
		return flightNumber;
	}


	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}


	public String getCityFrom() {
		return cityFrom;
	}


	public void setCityFrom(String cityFrom) {
		this.cityFrom = cityFrom;
	}


	public String getCityTo() {
		return cityTo;
	}


	public void setCityTo(String cityTo) {
		this.cityTo = cityTo;
	}


	public String getDeparture() {
		return departure;
	}


	public void setDeparture(String departure) {
		this.departure = departure;
	}


	public String getArrival() {
		return arrival;
	}


	public void setArrival(String arrival) {
		this.arrival = arrival;
	}


	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}


	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
	
	
	
}