package SistemaEnvios.Logica;

import java.io.IOException;

public interface SistemaEnvios {
	/**
	 * 
	 * @param ciudad
	 * @return
	 */
	public boolean isCiudad(String ciudad);
	/**
	 * 
	 * @param codigo
	 * @param rutO
	 * @param rutD
	 * @param altura
	 * @param ancho
	 * @param profundidad
	 */
	public void ingresarEnvioD(String codigo,String rutO,String rutD,double altura, double ancho,double profundidad);
	/**
	 * 
	 * @param codigo
	 * @param rutO
	 * @param rutD
	 * @param peso
	 */
	public void ingresarEnvioP(String codigo,String rutO,String rutD,double peso);
	/**
	 * 
	 * @param ciudad
	 */
	public void ingresarCiudad(String ciudad);
	/**
	 * 
	 * @param rut
	 * @param nombre
	 * @param apellido
	 * @param ciudad
	 */
	public void ingresarCliente(String rut,String nombre,String apellido,String ciudad);
	/**
	 * 
	 * @param codigo
	 * @param rutO
	 * @param rutD
	 * @param altura
	 * @param ancho
	 * @param profundidad
	 */
	public void RealizarEnvioD(String codigo,String rutO,String rutD,double altura, double ancho,double profundidad);
	
	/**
	 * 
	 * @param codigo
	 * @param rutO
	 * @param rutD
	 * @param peso
	 */
	public void RealizarEnvioP(String codigo,String rutO,String rutD,double peso);
	
	/**
	 * 
	 * @param rut
	 * @return
	 */
	public boolean VerificarRut(String rut);
	/**
	 * 
	 * @return String
	 */
	public String obtenerDatosCiudades1();
	
	/**
	 * 
	 * @param rut
	 * @return
	 */
	public String obtenerEnviosPersona(String rut);
	
	/**
	 * 
	 * @return
	 */
	public String ObtenerDatosEnvios();
	/**
	 * @throws IOException 
	 * 
	 */
	public void ActualizarDatos() throws IOException;
	

	
	
	
	
	
	

}
