package es.uco.pw.data.dao.pista;

import java.io.BufferedReader; 
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import es.uco.pw.business.pista.*;
import es.uco.pw.business.enumeraciones.Dificultad;
import es.uco.pw.data.dao.common.DBConnection;

public class PistaDAO {
	
	public boolean insertarPista(PistaDTO pista) {
		PreparedStatement stmt = null;
		
		Properties propiedades = new Properties();
		String filename = "sql.properties";
		String sql = new String();
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			propiedades.load(reader);
			sql = propiedades.getProperty("INSERT_PISTA");
			
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, pista.getIdpista_());
			stmt.setString(2, pista.getNombre_());
			stmt.setBoolean(3, pista.getEstado_());
			stmt.setString(4, pista.getDificultad_().toString());
			stmt.setInt(5, pista.getNumeroKarts_());
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


	public boolean actualizarPista(PistaDTO pista) {
		PreparedStatement stmt = null;
		
		Properties propiedades = new Properties();
		String filename = "sql.properties";
		String sql = new String();
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			propiedades.load(reader);
			sql = propiedades.getProperty("UPDATE_PISTA");
			
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, pista.getIdpista_());
			stmt.setString(2, pista.getNombre_());
			stmt.setBoolean(3, pista.getEstado_());
			stmt.setString(4, pista.getDificultad_().toString());
			stmt.setInt(5, pista.getNumeroKarts_());
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

	
	public boolean eliminarPista(int id) {
		PreparedStatement stmt = null;
		
		Properties propiedades = new Properties();
		String filename = "sql.properties";
		String sql = new String();
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			propiedades.load(reader);
			sql = propiedades.getProperty("DELETE_PISTA");
			
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
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



	public ArrayList<PistaDTO> obtenerPistas() {
		ArrayList<PistaDTO> listOfTracks = new ArrayList<PistaDTO>();

		Properties propiedades = new Properties();
		String filename = "sql.properties";
		String sql = new String();
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			propiedades.load(reader);
			sql = propiedades.getProperty("GETALL_PISTA");
			Dificultad dificultad_ = null;
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);

			while (rs.next()) {
				int idpista_ = rs.getInt("id");
				String nombre_ = rs.getString("nombre");
				boolean estado_ = rs.getBoolean("estado");
				String aux = rs.getString("dificultad");
				int numeroKarts_ = rs.getInt("numeroKarts");
				
				if(aux.equals(Dificultad.INFANTIL.toString())) { 					
					dificultad_ = Dificultad.INFANTIL;
				}else if(aux.equals(Dificultad.FAMILIAR.toString())) {	
					dificultad_ = Dificultad.FAMILIAR;
				}else if(aux.equals(Dificultad.ADULTOS.toString())) {	
					dificultad_ = Dificultad.ADULTOS;
				}
				
				listOfTracks.add(new PistaDTO(idpista_, nombre_, estado_, dificultad_, numeroKarts_));
				}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listOfTracks;
	}
	

	public PistaDTO obtenerPista(int id) {

		PistaDTO pista = new PistaDTO();
		//PreparedStatement stmt = null;
		//ResultSet rs = null;
		
		Properties propiedades = new Properties();
		String filename = "sql.properties";
		String sql = new String();
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			propiedades.load(reader);
			sql = propiedades.getProperty("GETONE_PISTA");
			Dificultad dificultad_ = Dificultad.INFANTIL;
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				
				pista.setIdpista_(rs.getInt("id"));
				pista.setNombre_(rs.getString("nombre"));
				pista.setEstado_(rs.getBoolean("estado"));
				pista.setNumeroKarts_(rs.getInt("numeroKarts"));
				
				String aux = rs.getString("dificultad");
				
				if(aux.equals(Dificultad.INFANTIL.toString())) { 
					
					dificultad_ = Dificultad.INFANTIL;
				}
				
				if(aux.equals(Dificultad.FAMILIAR.toString())) {
					
					dificultad_ = Dificultad.FAMILIAR;
				}
				
				if(aux.equals(Dificultad.ADULTOS.toString())) {
					
					dificultad_ = Dificultad.ADULTOS;
				}
				
				pista.setDificultad_(dificultad_);
				
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
			
			return pista;
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return pista;
	}
		
}
