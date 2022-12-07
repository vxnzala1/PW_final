package es.uco.pw.business.kart;

import es.uco.pw.business.enumeraciones.Estado;

public class KartDTO {

	/*ATRIBUTOS*/
	private int id_;
	private boolean tipo_;
	private Estado condicion_;
	public int idpista_;
	
	/*CONSTRUCTOR VACIO*/
	public KartDTO() {
		
	}
	
	/*CONSTRUCTOR PARAMETRIZADO*/
	public KartDTO(int id, boolean tipo, Estado condicion, int idpista) {
		
		this.setId_(id);
		this.setTipo_(tipo);
		this.setCondicion_(condicion);
		this.idpista_= idpista;
	}
	
	public KartDTO(int id) {

	}

	/*GETTER Y SETTER*/
	public int getId_() {
		return id_;
	}
	public void setId_(int id) {
		this.id_ = id;
	}

	public boolean getTipo_() {
		return tipo_;
	}
	public void setTipo_(boolean tipo) {
		this.tipo_ = tipo;
	}

	public Estado getCondicion_() {
		return condicion_;
	}
	public void setCondicion_(Estado condicion) {
		this.condicion_ = condicion;
	}
	
	public int getIdpista_() {
		return idpista_;
	}
	public void setIdpista_(int idpista_) {
		this.idpista_ = idpista_;
	}
	
	/*OTROS METODOS*/
	public String toString(){
		String datos = "ID = " + id_ + ", tipo = " + tipo_ + ", condicion = " + condicion_ + ", idpista = " + idpista_;
		return datos;
	} 
}