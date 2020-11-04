package SistemaEnvios.Dominio;
import SistemaEnvios.Logica.ListaEnvios;
import java.util.ArrayList;
import java.util.Iterator;

public class Persona {
		private String rut;
	    private String nombre;
	    private String apellido;
	    private String ciudad;
	    private ListaEnvios listaEnviosR;
	    private ListaEnvios listaEnviosD;
	
	
	   public Persona(String rut,String nombre, String apellido , String ciudad) {
	        this.rut = rut;
	        this.nombre = nombre;
	        this.apellido = apellido;
	        this.ciudad = ciudad;
	        listaEnviosR= new ListaEnvios();
	        listaEnviosD = new ListaEnvios();
	   }
	
	
	/**
	 * @return the rut
	 */
	public String getRut() {
		return rut;
	}
	
	
	/**
	 * @param rut the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	
	
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	
	
	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
	/**
	 * @return the listaEnviosR
	 */
	public ListaEnvios getListaEnviosR() {
		return listaEnviosR;
	}
	
	
	/**
	 * @param listaEnviosR the listaEnviosR to set
	 */
	public void setListaEnviosR(ListaEnvios listaEnviosR) {
		this.listaEnviosR = listaEnviosR;
	}
	
	
	/**
	 * @return the listaEnviosD
	 */
	public ListaEnvios getListaEnviosD() {
		return listaEnviosD;
	}
	
	
	/**
	 * @param listaEnviosD the listaEnviosD to set
	 */
	public void setListaEnviosD(ListaEnvios listaEnviosD) {
		this.listaEnviosD = listaEnviosD;
	}
	   
}




