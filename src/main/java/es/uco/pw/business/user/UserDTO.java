package es.uco.pw.business.user;


import java.util.Date; 

public class UserDTO {

	/*ATRIBUTOS*/
	private String nombre_;
	private String apellido1_;
	private String apellido2_;
	private Date fechaNacimiento_;
	private Date inscripcion_;
	private String email_;
	
	/*CONTRUCTOR VACIO*/
	public UserDTO(){
		
	}
	
	/*CONTRUCTOR PARAMETRIZADO CON ALGUNOS ATRIBUTOS*/
	public UserDTO(String usuario, String apellido1, String apellido2) {
		nombre_ = usuario;
		apellido1_ = apellido1;
		apellido2_ = apellido2;
	}
	/*CONTRUCTOR PARAMETRIZADO CON TODOS LOS ATRIBUTOS*/
	public UserDTO(String usuario, String apellido1, String apellido2, Date fechaNacimiento, Date inscripcion, String email) {
		super();
		nombre_ = usuario;
		apellido1_ = apellido1;
		apellido2_ = apellido2;
		fechaNacimiento_ = fechaNacimiento;
		inscripcion_ =  inscripcion; //?
		email_ = email;
	}


	/*GETTER Y SETTER*/
	public String getNombre_() {
		return nombre_;
	}
	public void setNombre_(String nombre_) {
		this.nombre_ = nombre_;
	}

	public String getApellido1_() {
		return apellido1_;
	}
	public void setApellido1_(String apellido1_) {
		this.apellido1_ = apellido1_;
	}

	public String getApellido2_() {
		return apellido2_;
	}
	public void setApellido2_(String apellido2_) {
		this.apellido2_ = apellido2_;
	}

	public Date getFechaNacimiento_() {
		return fechaNacimiento_;
	}
	public void setFechaNacimiento_(Date fechaNacimiento_) {
		this.fechaNacimiento_ = fechaNacimiento_;
	}
	
	public Date getInscripcion_() {
		return inscripcion_;
	}
	public void setInscripcion_(Date inscripcion_) {
		this.inscripcion_ = inscripcion_;
	}

	public String getEmail_() {
		return email_;
	}
	public void setEmail_(String email_) {
		this.email_ = email_;
	}
	
	/*OTROS METODOS*/
	public String toString(){
		String datos ="Nombre = " + nombre_ + ", apellido 1 = " + apellido1_ + ", apellido 2 = " + apellido2_ + ", email = " + email_ + ", fecha de nacimiento = " + fechaNacimiento_ + ", inscripcion = " + inscripcion_;
		return datos;
	} 
	
}