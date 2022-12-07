package es.uco.pw.data.dao.user;

import java.io.BufferedReader; 
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ArrayList;
import java.util.Properties;
import es.uco.pw.business.user.*;
import es.uco.pw.data.dao.common.DBConnection;

public class UserDAO {
						
	public boolean insertarUsuario(UserDTO user) {
		PreparedStatement stmt = null;
		java.sql.Date sqlDate1 = new java.sql.Date((user.getFechaNacimiento_()).getTime());
		java.sql.Date sqlDate2 = new java.sql.Date((user.getInscripcion_()).getTime());
		
		Properties propiedades = new Properties();
		String filename = "sql.properties";
		String sql = new String();
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			propiedades.load(reader);
			sql = propiedades.getProperty("INSERT_USER");
			
			stmt = connection.prepareStatement(sql); 
			stmt.setString(1, user.getNombre_());
			stmt.setString(2, user.getApellido1_());
			stmt.setString(3, user.getApellido2_());
			stmt.setDate(4, sqlDate1);
			stmt.setDate(5, sqlDate2);
			stmt.setString(6, user.getEmail_());
			stmt.executeUpdate();

			if (stmt != null){ 
				stmt.close(); 
			}
			
			dbConnection.closeConnection();
			
			return true;
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		} 
		
		return false;
	}


	public boolean actualizarUsuario(UserDTO user) {
		PreparedStatement stmt = null;
		java.sql.Date sqlDate1 = new java.sql.Date((user.getFechaNacimiento_()).getTime());
		
		Properties propiedades = new Properties();
		String filename = "sql.properties";
		String sql = new String();
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			propiedades.load(reader);
			sql = propiedades.getProperty("UPDATE_USER");
			
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getNombre_());
			stmt.setString(2, user.getApellido1_());
			stmt.setString(3, user.getApellido2_());
			stmt.setDate(4, sqlDate1);
			stmt.setString(5, user.getEmail_());
			stmt.executeUpdate();
			
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
			
			return true;
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return false;
	}

	
	public boolean eliminarUsuario(String email) {
		PreparedStatement stmt = null;
		
		Properties propiedades = new Properties();
		String filename = "sql.properties";
		String sql = new String();
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			propiedades.load(reader);
			sql = propiedades.getProperty("DELETE_USER");
			
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.executeUpdate();
			
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
			
			return true;
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return false;
	}


	
	public ArrayList<UserDTO> obtenerUsuarios(){
		ArrayList<UserDTO> listOfUsers = new ArrayList<UserDTO>();
		
		Properties propiedades = new Properties();
		String filename = "sql.properties";
		String sql = new String();
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			propiedades.load(reader);
			sql = propiedades.getProperty("GETALL_USER");
						
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);

			while (rs.next()) {
				String nombre_ = rs.getString("nombre");
				String apellido1_ = rs.getString("PrimerApe");
				String apellido2_ = rs.getString("SegundoApe");
				Date nacimiento_ = rs.getDate("nacimiento");
				Date inscripcion_ = rs.getDate("inscripcion");
				String email_ = rs.getString("email");
				listOfUsers.add(new UserDTO(nombre_, apellido1_, apellido2_, nacimiento_, inscripcion_, email_));
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
	
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return listOfUsers;
	} 



public UserDTO obtenerUsuarioEmail(String email) {
	
	UserDTO user = new UserDTO();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	Properties propiedades = new Properties();
	String filename = "sql.properties";
	String sql = new String();
	
	try {
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
		propiedades.load(reader);
		sql = propiedades.getProperty("GETONE_USER");
		
		stmt = connection.prepareStatement(sql);
		stmt.setString(1, email);
		rs = stmt.executeQuery();

		if (rs.next()) {
			
			user.setNombre_(rs.getString("nombre"));
			user.setApellido1_(rs.getString("PrimerApe"));
			user.setApellido2_(rs.getString("SegundoApe"));
			user.setFechaNacimiento_(rs.getDate("nacimiento"));
			user.setInscripcion_(rs.getDate("inscripcion"));
			user.setEmail_(rs.getString("email"));
			
		}

		if (stmt != null){ 
			stmt.close(); 
		}
		dbConnection.closeConnection();
		
		return user;
		
	} catch (Exception e){
		System.err.println(e);
		e.printStackTrace();
	}
	
	return user;
}

public UserDTO obtenerUsuarioNombre(String nombre, String apellido1, String apellido2) {
	
	UserDTO user = new UserDTO();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	Properties propiedades = new Properties();
	String filename = "sql.properties";
	String sql = new String();
	
	try {
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
		propiedades.load(reader);
		sql = propiedades.getProperty("GETONE_USER_NAME");
		
		stmt = connection.prepareStatement(sql);
		stmt.setString(1, nombre);
		stmt.setString(2, apellido1);
		stmt.setString(3, apellido2);
		rs = stmt.executeQuery();

		if (rs.next()) {
			
			user.setNombre_(rs.getString("nombre"));
			user.setApellido1_(rs.getString("PrimerApe"));
			user.setApellido2_(rs.getString("SegundoApe"));
			user.setFechaNacimiento_(rs.getDate("nacimiento"));
			user.setInscripcion_(rs.getDate("inscripcion"));
			user.setEmail_(rs.getString("email"));
			
		} 

		if (stmt != null){ 
			stmt.close(); 
		}
		dbConnection.closeConnection();
		
		return user; 
		
	} catch (Exception e){
		System.err.println(e);
		e.printStackTrace();
	}
	
	return user;
}



}