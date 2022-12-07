package es.uco.pw.business.pista;

import java.util.ArrayList; 

import es.uco.pw.business.enumeraciones.Dificultad;
import es.uco.pw.business.kart.*;

public class PistaDTO {
	
	/*ATRIBUTOS*/
	private int idpista_;
	private String nombre_; 
	private boolean estado_; //true si esta disponible
	private Dificultad dificultad_;
	private int numeroKarts_;
	private ArrayList<KartDTO> disp_ =  new ArrayList<KartDTO> ();
	private ArrayList<PistaDTO> mantenimiento_ =  new ArrayList<PistaDTO> ();
	
	/*CONSTRUCTOR VACIO*/
	public PistaDTO() {
		
	}
	
	/*CONSTRUCTORES PARAMETRIZADOS*/
	public PistaDTO(int idpista, String nombre, boolean estado, Dificultad dificultad, int numeroKarts) {
		
		idpista_ = idpista;
		nombre_ = nombre;
		estado_ = estado;
		dificultad_ = dificultad;
		numeroKarts_ = numeroKarts;
	}

	public PistaDTO(int idpista){
		idpista_ = idpista;
	}
	
	/*GETTER Y SETTER*/
	public String getNombre_() {
		return nombre_;
	}
	public void setNombre_(String nombre_) {
		this.nombre_ = nombre_;
	}

	public boolean getEstado_() {
		return estado_;
	}
	public void setEstado_(boolean estado_) {
		this.estado_ = estado_;
	}

	public Dificultad getDificultad_() {
		return dificultad_;
	}
	public void setDificultad_(Dificultad dificultad_) {
		this.dificultad_ = dificultad_;
	}

	public int getNumeroKarts_() {
		return numeroKarts_;
	}
	public void setNumeroKarts_(int numeroKarts_) {
		this.numeroKarts_ = numeroKarts_;
	}

	public int getIdpista_() {
		return idpista_;
	}
	public void setIdpista_(int idpista_) {
		this.idpista_ = idpista_;
	}

	public ArrayList<PistaDTO> getMantenimiento_() {
		return mantenimiento_;
	}
	public ArrayList<KartDTO> getDisp_() {
		return disp_;
	}

	public void setDisp_(ArrayList<KartDTO> disp_) {
		this.disp_ = disp_;
	} 
	public void setMantenimiento_(ArrayList<PistaDTO> mantenimiento_) {
		this.mantenimiento_ = mantenimiento_;
	}
	
	/*OTROS METODOS*/
	public String toString(){
		String datos = "Nombre = " + nombre_ + ", estado = " + estado_ + ", dificultad = " + dificultad_ + ", numero de karts = " + numeroKarts_;
		return datos;
	}
}