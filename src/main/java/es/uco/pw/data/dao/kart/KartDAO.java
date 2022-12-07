package es.uco.pw.data.dao.kart;

import java.io.BufferedReader; 
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;	
import es.uco.pw.business.enumeraciones.Estado;
import es.uco.pw.business.kart.KartDTO;
import es.uco.pw.data.dao.common.DBConnection;

public class KartDAO {
	
	public boolean insertarKart(KartDTO kart) {
		PreparedStatement stmt = null;
		
		Properties propiedades = new Properties();
		String filename = "sql.properties";
		String sql = new String();
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			propiedades.load(reader);
			sql = propiedades.getProperty("INSERT_KART");
			
			stmt = connection.prepareStatement(sql); 
			stmt.setInt(1, kart.getId_());
			stmt.setBoolean(2, kart.getTipo_());
			stmt.setString(3, kart.getCondicion_().toString());
			stmt.setInt(4, kart.getIdpista_());
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


	public boolean actualizarKart(KartDTO kart) {
		PreparedStatement stmt = null;
		
		Properties propiedades = new Properties();
		String filename = "sql.properties";
		String sql = new String();
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			propiedades.load(reader);
			sql = propiedades.getProperty("UPDATE_KART");
			
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, kart.getId_());
			stmt.setBoolean(2, kart.getTipo_());
			stmt.setString(3, kart.getCondicion_().toString());
			stmt.setInt(4, kart.getIdpista_());
			stmt.setInt(5, kart.getId_());
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

	
	public boolean eliminarKart(int id) {
		PreparedStatement stmt = null;
		
		Properties propiedades = new Properties();
		String filename = "sql.properties";
		String sql = new String();
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			propiedades.load(reader);
			sql = propiedades.getProperty("DELETE_KART");
			
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



	public ArrayList<KartDTO> obtenerKarts() {
		ArrayList<KartDTO> listOfKarts = new ArrayList<KartDTO>();
		
		Properties propiedades = new Properties();
		String filename = "sql.properties";
		String sql = new String();
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			propiedades.load(reader);
			sql = propiedades.getProperty("GETALL_KART");
			Estado condicion_ = null;
			
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);

			while (rs.next()) {
				int id_ = rs.getInt("id");
				boolean tipo_ = rs.getBoolean("tipo");
				String aux = rs.getString("estado");
				int idpista_ = rs.getInt("idpista");
				
				if(aux.equals(Estado.DISPONIBLE.toString())) { 
					condicion_ = Estado.DISPONIBLE;
				}else if(aux.equals(Estado.MANTENIMIENTO.toString())) {	
					condicion_ = Estado.MANTENIMIENTO;
				}else if(aux.equals(Estado.RESERVADO.toString())) {	
					condicion_ = Estado.RESERVADO;
				}
				
				listOfKarts.add(new KartDTO(id_, tipo_, condicion_, idpista_));
				}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listOfKarts;
	}
	

	public KartDTO obtenerKart(int id) {
		
		KartDTO kart = new KartDTO();
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
			sql = propiedades.getProperty("GETONE_KART");
			Estado condicion_ = null;
			
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				
				kart.setId_(rs.getInt("id"));
				kart.setTipo_(rs.getBoolean("tipo"));
				kart.setIdpista_(rs.getInt("idpista"));
				
				String aux = rs.getString("estado");
				
				if(aux.equals(Estado.DISPONIBLE.toString())) { 
					condicion_ = Estado.DISPONIBLE;
				}else if(aux.equals(Estado.MANTENIMIENTO.toString())) {	
					condicion_ = Estado.MANTENIMIENTO;
				}else if(aux.equals(Estado.RESERVADO.toString())) {	
					condicion_ = Estado.RESERVADO;
				}
				
				kart.setCondicion_(condicion_);
			
			}
				
			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
			
			return kart;
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return kart;
	}


}