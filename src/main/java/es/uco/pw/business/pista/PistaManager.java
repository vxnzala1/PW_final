package es.uco.pw.business.pista;


import java.util.ArrayList;
import java.util.Scanner;
import es.uco.pw.business.enumeraciones.*;
import es.uco.pw.business.kart.KartDTO;
//import es.uco.pw.business.pista.PistaDTO;
import es.uco.pw.data.dao.kart.KartDAO;
import es.uco.pw.data.dao.pista.PistaDAO;

public class PistaManager{
	
    private PistaDAO pistaDAO;
    private KartDAO kartDAO;
    private PistaDTO pistaDTO;
    private KartDTO kartDTO;
    
    public PistaManager(){
        pistaDAO = new PistaDAO();
        kartDAO = new KartDAO();
        pistaDTO = new PistaDTO();
        kartDTO = new KartDTO();
    }

    public void BuscarPistaPorId(){
    	
        @SuppressWarnings("resource")
		Scanner lectura = new Scanner(System.in);

        System.out.println("Por favor, introduzca el id de la pista que quiere encontrar: ");
        int id = lectura.nextInt();
        lectura.nextLine();

        pistaDTO = pistaDAO.obtenerPista(id);
        int comprobacion = pistaDTO.getIdpista_();
        
        if(comprobacion == 0) { 	
        	System.err.println ("No se encontro una pista con ese ID");
		}else {
			System.out.println(pistaDTO.toString() + "\n");
        }
    }
    
    
    public void CrearPista(){
    	
        @SuppressWarnings("resource")
		Scanner lectura = new Scanner(System.in);
        
        System.out.println("Por favor, introduzca el id de la pista:");
        int idpista = lectura.nextInt();
        lectura.nextLine();
        pistaDTO.setIdpista_(idpista);
        
        System.out.println("Por favor, introduzca el nombre de la pista:");
        String nombre = lectura.nextLine();
        pistaDTO.setNombre_(nombre);
        
        System.out.println("Por favor, introduzca el estado de la pista (1 o 2):");
        boolean estado = (lectura.nextInt() == 1);
        lectura.nextLine();
        pistaDTO.setEstado_(estado);
        
        System.out.println("Por favor, introduzca la dificultad de la pista (ADULTOS, INFANTIL o FAMILIAR):");
        String dif = lectura.nextLine();
        Dificultad dificultad=Dificultad.ADULTOS;
        if(dif.equals("ADULTOS")){
            dificultad = Dificultad.ADULTOS;
        }else if(dif.equals("FAMILIAR")){
            dificultad = Dificultad.FAMILIAR;
        }else if(dif.equals("INFANTIL")){
            dificultad = Dificultad.INFANTIL;
        }
        pistaDTO.setDificultad_(dificultad);
        
        System.out.println("Por favor, introduzca el numero de karts disponibles:");
        int numeroKarts = lectura.nextInt();
        lectura.nextLine();
        pistaDTO.setNumeroKarts_(numeroKarts);
        
        boolean comprobacion = pistaDAO.insertarPista(pistaDTO);
        
        if(comprobacion == false) { 	
        	System.err.println ("No se ha podido insertar la pista");
		}else {
			System.out.println("Pista insertada correctamente");
        }     
    }
    
    public void ModificarPista(){
    	
    	@SuppressWarnings("resource")
		Scanner lectura = new Scanner(System.in);
        
        System.out.println("Por favor, introduzca el id de la pista:");
        int idpista = lectura.nextInt();
        lectura.nextLine();
        pistaDTO.setIdpista_(idpista);
        
        System.out.println("Por favor, introduzca el nombre de la pista:");
        String nombre = lectura.nextLine();
        pistaDTO.setNombre_(nombre);
        
        System.out.println("Por favor, introduzca el estado de la pista:");
        boolean estado = (lectura.nextInt() == 1);
        lectura.nextLine();
        pistaDTO.setEstado_(estado);
        
        System.out.println("Por favor, introduzca el estado de la pista:");
        String dif = lectura.nextLine();
        Dificultad dificultad=Dificultad.ADULTOS;
        if(dif.equals("ADULTOS")){
            dificultad = Dificultad.ADULTOS;
        }else if(dif.equals("FAMILIAR")){
            dificultad = Dificultad.FAMILIAR;
        }else if(dif.equals("INFANTIL")){
            dificultad = Dificultad.INFANTIL;
        }
        pistaDTO.setDificultad_(dificultad);
        
        System.out.println("Por favor, introduzca el numero de karts disponibles:");
        int numeroKarts = lectura.nextInt();
        lectura.nextLine();
        pistaDTO.setNumeroKarts_(numeroKarts);
        
        boolean comprobacion = pistaDAO.actualizarPista(pistaDTO);
        
        if(comprobacion == false) { 	
        	System.err.println ("No se ha podido modificar la pista");
		}else {
			System.out.println("Pista modificada correctamente");
        }       
    }
    
    public void EliminarPista(){
    	
        @SuppressWarnings("resource")
		Scanner lectura = new Scanner(System.in);
        
        System.out.println("Por favor, introduzca el id de la pista a borrar:");
        int id = lectura.nextInt();
        lectura.nextLine();
        
        boolean comprobacion = pistaDAO.eliminarPista(id);
        
        if(comprobacion == false) { 	
        	System.err.println ("No se ha podido eliminar la pista.");
		}else {
			System.out.println("Pista eliminada correctamente.");
        }
    }
    
    public void ListarPistas(){
        
    	ArrayList<PistaDTO> listOfTracks = pistaDAO.obtenerPistas();
		
		for (PistaDTO p: listOfTracks) {
				System.out.println(p.toString() + "\n");
			}	
    }

    public void ListarPistasMantenimiento(){
        
    	ArrayList<PistaDTO> listOfTracks = pistaDAO.obtenerPistas();
		
		for (PistaDTO p: listOfTracks) {
				if(p.getEstado_() == false) {
				System.out.println(p.toString() + "\n");
			}	
		}
    }
    
    public void BuscarKartPorID(){ 
    	
    	@SuppressWarnings("resource")
		Scanner lectura = new Scanner(System.in);
    	
    	System.out.println("Por favor, introduzca el id del kart que quiere buscar");
        int id = lectura.nextInt();
        lectura.nextLine();
        
        kartDTO = kartDAO.obtenerKart(id);
        int comprobacion = kartDTO.getId_();
        
        if(comprobacion == 0) { 	
        	System.err.println ("No se encontro un kart con ese ID");
		}else {
			System.out.println(kartDTO.toString() + "\n");
        }
    }
    
    public void CrearKart(){    	
    	
    	@SuppressWarnings("resource")
		Scanner lectura1 = new Scanner(System.in);
    	
    	System.out.println("Por favor, introduzca el id del kart:");
    	int id = lectura1.nextInt();
    	lectura1.nextLine();
    	kartDTO.setId_(id);
    	
    	System.out.println("Por favor, introduzca el estado del kart (1 o 2):");
    	boolean tipo = (lectura1.nextInt() == 1);
    	lectura1.nextLine();
    	kartDTO.setTipo_(tipo);
    	
    	System.out.println("Por favor, introduzca la condicion del kart (DISPONIBLE, MANTENIMIENTO o RESERVADO):");
    	String dif1 = lectura1.nextLine();
    	Estado condicion = Estado.DISPONIBLE;
    	if(dif1.equals("MANTENIMIENTO")){
            condicion = Estado.MANTENIMIENTO;
        }else if(dif1.equals("RESERVADO")){
            condicion = Estado.RESERVADO;
        }else {
        	condicion = Estado.DISPONIBLE;
        }
    	kartDTO.setCondicion_(condicion);
    	
    	System.out.println("Por favor, introduzca el idpista del kart:");
    	int idpista = lectura1.nextInt();
        lectura1.nextLine();
        kartDTO.setIdpista_(idpista);;
    			
        boolean comprobacion = kartDAO.insertarKart(kartDTO);
        
        if(comprobacion == false) { 	
        	System.err.println ("No se ha podido insertar el kart");
		}else {
			System.out.println("Kart insertado correctamente");
        }
    }
    
    public void ModificarKart(){
    	
    	@SuppressWarnings("resource")
		Scanner lectura1 = new Scanner(System.in);
    	
    	System.out.println("Por favor, introduzca el id del kart:");
    	int id = lectura1.nextInt();
    	lectura1.nextLine();
    	kartDTO.setId_(id);
    	
    	System.out.println("Por favor, introduzca el tipo del kart (1 o 2):");
    	boolean tipo = (lectura1.nextInt() == 1);
    	lectura1.nextLine();
    	kartDTO.setTipo_(tipo);
    	
    	System.out.println("Por favor, introduzca la condicion del kart (DISPONIBLE, MANTENIMIENTO o RESERVADO):");
    	String dif1 = lectura1.nextLine();
    	Estado condicion = Estado.DISPONIBLE;
    	if(dif1.equals("MANTENIMIENTO")){
            condicion = Estado.MANTENIMIENTO;
        }else if(dif1.equals("RESERVADO")){
            condicion = Estado.RESERVADO;
        }else {
        	condicion = Estado.DISPONIBLE;
        }
    	kartDTO.setCondicion_(condicion);
    	
    	System.out.println("Por favor, introduzca el idpista del kart:");
    	int idpista = lectura1.nextInt();
        lectura1.nextLine();
        kartDTO.setIdpista_(idpista);
    			
        boolean comprobacion = kartDAO.actualizarKart(kartDTO);
        
        if(comprobacion == false) { 	
        	System.err.println ("No se ha podido modificar el kart");
		}else {
			System.out.println("Kart modificado correctamente");
        }
    }
    
    public void EliminarKart(){
    	
    	@SuppressWarnings("resource")
		Scanner lectura1 = new Scanner(System.in);
        
        System.out.println("Por favor, introduzca el id del kart que quiera borrar:");
        int id = lectura1.nextInt();
        lectura1.nextLine();
        
        boolean comprobacion = kartDAO.eliminarKart(id);
        
        if(comprobacion == false) { 	
        	System.err.println ("No se ha podido eliminar el kart");
		}else {
			System.out.println("Kart eliminado correctamente");
        }
    }

	public void ListarKarts(){
		
		ArrayList<KartDTO> listOfKarts = kartDAO.obtenerKarts();
		
		for (KartDTO k: listOfKarts) {
				System.out.println(k.toString() + "\n");
			}
    }
}