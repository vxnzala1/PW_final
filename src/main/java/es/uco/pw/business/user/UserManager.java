package es.uco.pw.business.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
//import es.uco.pw.business.user.UserDTO;
import es.uco.pw.data.dao.user.UserDAO;

public class UserManager {
	
	    private UserDAO userDAO;
	    private UserDTO userDTO;
	    
	    public UserManager(){
	        userDAO = new UserDAO();
	        userDTO = new UserDTO();
	    }
	
	
	public Date insertarFechaTeclado() {
		@SuppressWarnings("resource")
		Scanner lectura = new Scanner(System.in);
		String fecha = lectura.nextLine();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date testDate = null;
        String date = fecha;
        try{
            testDate = df.parse(date);
        } catch (Exception e){ System.out.println("invalid format");}
 
        if (!df.format(testDate).equals(date)){
            System.out.println("Fecha incorrecta.");
        } else {
            System.out.println("Fecha correcta.");
        }
        return testDate;
	}
	
	public void BuscarUsuarioPorNombre(){
	
		@SuppressWarnings("resource")
		Scanner lectura = new Scanner(System.in);
		
        System.out.println ("Por favor, introduzca el nombre del usuario que quiere buscar:");
        String entrada1 = lectura.nextLine();
        System.out.println ("Por favor, introduzca el primer apellido del usuario que quiere buscar:");
        String entrada2 = lectura.nextLine();
        System.out.println ("Por favor, introduzca el segundo apellido del usuario que quiere buscar:");
        String entrada3 = lectura.nextLine();

        userDTO = userDAO.obtenerUsuarioNombre(entrada1, entrada2, entrada3);
       String comprobacion = userDTO.getEmail_();

        
        if(comprobacion == null) { 	
        	System.err.println ("No se encontro un usuario con ese nombre");
		}else {
			System.out.println(userDTO.toString() + "\n");
       }
        
	}
	
	public void BuscarUsuarioPorEmail(){
		
		@SuppressWarnings("resource")
		Scanner lectura = new Scanner(System.in);

        System.out.println ("Por favor, introduzca el email del usuario que quiere buscar:");
        String email = lectura.nextLine();
        
        userDTO = userDAO.obtenerUsuarioEmail(email);
        String comprobacion = userDTO.getEmail_();
        
        if(comprobacion == null) { 	
        	System.err.println ("No se encontro un usuario con ese email");
		}else {
			System.out.println(userDTO.toString() + "\n");
        }
        
	}
	
	
	public void DarDeAltaUsuario() {
		
		@SuppressWarnings("resource")
		Scanner lectura = new Scanner(System.in);
        
        System.out.println ("Por favor, introduzca el nombre del usuario que quiere crear:");
        String nombre = lectura.nextLine();
        userDTO.setNombre_(nombre);
        
        System.out.println ("Por favor, introduzca el primer apellido del usuario que quiere crear:");
        String apellido1 = lectura.nextLine();
        userDTO.setApellido1_(apellido1);
        
        System.out.println ("Por favor, introduzca el segundo apellido del usuario que quiere crear:");
        String apellido2 = lectura.nextLine();
        userDTO.setApellido2_(apellido2);
        
        System.out.println ("Por favor, introduzca el email del usuario que quiere crear:");
        String email = lectura.nextLine();
        userDTO.setEmail_(email);
        
        System.out.println ("Por favor, introduzca la fecha de nacimiento del usuario que quiere crear (formato dd/MM/yyyy):");
        Date fechaNacimiento = insertarFechaTeclado();
        userDTO.setFechaNacimiento_(fechaNacimiento);
        
        //La fecha de inscripci�n se crea auotmaticamente
        //System.out.println ("Por favor, introduzca la fecha de la inscripcion que quiere crear (formato dd/MM/yyyy):");
        Date inscripcion = new Date();
        userDTO.setInscripcion_(inscripcion);
        
        boolean comprobacion = userDAO.insertarUsuario(userDTO);
        
        if(comprobacion == false) { 	
        	System.err.println ("No se ha podido insertar el usuario");
		}else {
			System.out.println("Usuario insertado correctamente");
        }
	}
		
	
	public void ModificarUsuario() {
		
		@SuppressWarnings("resource")
		Scanner lectura = new Scanner(System.in);
		
        System.out.println ("Por favor, introduzca el email del usuario que quiere modificar:");
        String email = lectura.nextLine();
        userDTO.setEmail_(email);
		
        System.out.println ("Por favor, introduzca el nuevo nombre del usuario:");
        String nombre = lectura.nextLine();
        userDTO.setNombre_(nombre);
        
        System.out.println ("Por favor, introduzca el nuevo primer apellido del usuario:");
        String apellido1 = lectura.nextLine();
        userDTO.setApellido1_(apellido1);
        
        System.out.println ("Por favor, introduzca el nuevo segundo apellido del usuario:");
        String apellido2 = lectura.nextLine();
        userDTO.setApellido2_(apellido2); 
        
        System.out.println ("Por favor, introduzca la nueva fecha de nacimiento del usuario(formato dd/MM/yyyy):");
        Date fechaNacimiento = insertarFechaTeclado();
        userDTO.setFechaNacimiento_(fechaNacimiento);
        
        //La fecha de inscripci�n no puede modificarse ya que es inalterable
       /*
        System.out.println ("Por favor, introduzca la fecha de la inscripcion que quiere crear (formato dd/MM/yyyy):");
        Date inscripcion = insertarFechaTeclado();
        userDTO.setInscripcion_(inscripcion);
       */
        
        boolean comprobacion = userDAO.actualizarUsuario(userDTO);
        
        if(comprobacion == false) { 	
        	System.err.println ("No se ha podido modificar el usuario");
		}else {
			System.out.println("Usuario modificado correctamente");
        }
	}
	
	public void EliminarUsuario() {
		
		@SuppressWarnings("resource")
		Scanner lectura = new Scanner(System.in);
		
        System.out.println ("Por favor, introduzca el email del usuario que quiere eliminar:");
        String email = lectura.nextLine();
        lectura.nextLine();
        
        boolean comprobacion = userDAO.eliminarUsuario(email);
        
        if(comprobacion == false) { 	
        	System.err.println ("No se ha podido eliminar el usuario");
		}else {
			System.out.println("Usuario eliminado correctamente");
        }
	}
	
	
	public void ListarUsuarios() {
		
		ArrayList<UserDTO> listOfUsers = userDAO.obtenerUsuarios();
		
		for (UserDTO u: listOfUsers) {
				System.out.println(u.toString() + "\n");
			}
		
		}
}