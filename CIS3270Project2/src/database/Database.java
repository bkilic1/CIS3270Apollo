package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javafx.event.ActionEvent;
import users.User;

public abstract class Database {
	
	protected static User user; //since user will change when people log in and log out, this will keep track of who's signed in
	
	public static void createTable() throws Exception { //This table has already been ran, keeping this for reference
		Connection connection = getConnection();
		try {
			
			PreparedStatement create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS User(ssn INT NOT NULL AUTO_INCREMENT, firstname VARCHAR(255) NOT NULL, lastname VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL"
					+ ", streetadress VARCHAR(255), zip INT, statecode VARCHAR(2), username VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, securityquestion VARCHAR(255) NOT NULL, PRIMARY KEY(ssn));");
			create.executeUpdate();
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			connection.close();
			System.out.println("Function complete");
		}
	}
	
	
	
	public static Connection getConnection() throws Exception{ // this function is to establish a connection with google cloud
		
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://34.75.245.47:3306/AirlineDB"; //IP address will have to change in DB beaver and here if it changes on google cloud
			String username = "root";
			String password = "avKcMdCF7n81tPak";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected!");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	protected abstract void insertStatement(String table, String query, ActionEvent event) throws Exception; //insert statement may vary within each class, made it abstract

	public User getUser() {
		return user;
	}

	public void setUser(User user) { 
		Database.user = user;
	}	

}
