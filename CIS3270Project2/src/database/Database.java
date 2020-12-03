package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		getConnection(); // you can call this method to test
		System.out.println(getZip());
		
	}
	
	public static void createTable() throws Exception { //This table has already been ran, keeping this for reference
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS User(ssn INT NOT NULL AUTO_INCREMENT, firstname VARCHAR(255) NOT NULL, lastname VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL"
					+ ", streetadress VARCHAR(255), zip INT, statecode VARCHAR(2), username VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, securityquestion VARCHAR(255) NOT NULL, PRIMARY KEY(ssn));");
			create.executeUpdate();
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			System.out.println("Function complete");
		}
	}
	
	public static int getZip() throws Exception { //this is to receive something from database;
		
		int zip = 0;
		try {
			Connection conn = getConnection();
			ResultSet result = conn.prepareStatement("SELECT zip FROM Users WHERE ssn=123121234;").executeQuery();
			
			while (result.next()) {
				zip = Integer.parseInt(result.getString("zip"));
			}
			return zip;
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	
	}
	
	public static Connection getConnection() throws Exception{
		
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
		finally {
			System.out.println("Connection closed!");
		}
		
		return null;
	}
	//pushing cloud

}
