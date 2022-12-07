package es.uco.pw.data.dao.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection{
	protected Connection connection = null;
	Properties propiedades = new Properties();
	String filename = "config.properties";
	String url = new String();
	String user = new String();
	String password = new String();
	
	public Connection getConnection() throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			propiedades.load(reader);
			url = propiedades.getProperty("url");
			user = propiedades.getProperty("user");
			password = propiedades.getProperty("password");
			
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = (Connection)DriverManager.getConnection(url, user, password);
			System.out.println("Database connection successfully opened!");
		}
		catch(SQLException e){
			System.err.println("Connection to MySQL has failed!");
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			System.err.println("JDBC Driver not found.");
			e.printStackTrace();
		}
		return this.connection;
	}
	
	/*Funcion que cierra la conexion con la base de datos*/
	public void closeConnection() {
		try {
			if(this.connection != null && !this.connection.isClosed()) {
				this.connection.close();
				System.out.println("Database connection successfully closed!");
			}
		} catch (SQLException e) {
			System.err.println("Error while trying to close the connection.");
			e.printStackTrace();
		}
	}
}	