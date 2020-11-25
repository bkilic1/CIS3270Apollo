package users;

import java.util.Date;

public class Flight {
	
	private int flightNumber;
	private String cityFrom;
	private String cityTo;
	private Date departure;
	private Date arrival;
	private int numberOfPassengers;
	
	
	public Flight(int flightNumber, String cityFrom, String cityTo, Date departure, Date arrival, int numberOfPassengers) {
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


	public Date getDeparture() {
		return departure;
	}


	public void setDeparture(Date departure) {
		this.departure = departure;
	}


	public Date getArrival() {
		return arrival;
	}


	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}


	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}


	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
	
	
	
}