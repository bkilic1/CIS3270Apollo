package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		
		
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

}
