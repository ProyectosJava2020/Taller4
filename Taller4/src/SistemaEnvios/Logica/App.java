/**
 * 
 */
package SistemaEnvios.Logica;

import java.io.IOException;

import ucn.ArchivoEntrada;
import ucn.Registro;
import ucn.StdIn;

/**
 * @author alexi
 *
 */
public class App {
	
	public static void leerCiudades(SistemaEnvios sistema)throws IOException{
		ArchivoEntrada file=new ArchivoEntrada("Ciudades.txt");
		while(!file.isEndFile()) {
			Registro register=file.getRegistro();
			String ciudad=register.getString();
			sistema.ingresarCiudad(ciudad);
		}
		file.close();
	}
	public static void leerClientes(SistemaEnvios sistema)throws IOException{
		ArchivoEntrada file=new ArchivoEntrada("Clientes.txt");
		while(!file.isEndFile()) {
			Registro reg=file.getRegistro();   
	        String rut = reg.getString();
	        String nombre = reg.getString();
	        String apellido = reg.getString();
	        String ciudad = reg.getString();
	        if(sistema.isCiudad(ciudad)) {
	        	sistema.ingresarCliente(rut, nombre, apellido, ciudad);
	        }
		}
		file.close();
	}
	public static void leerEnvios(SistemaEnvios sistema)throws IOException{
		ArchivoEntrada file=new ArchivoEntrada("Envios.txt");
		while(!file.isEndFile()) {
			Registro register=file.getRegistro();
			String codigo=register.getString();
			String tipo=register.getString();
			if(tipo.equals("D")) {
				String rutR=register.getString();
				String rutDes=register.getString();
				double altura=register.getDouble();
				double ancho=register.getDouble();
				double profundidad=register.getDouble();
				if(altura<=60 && ancho<=60 && profundidad<=60) {
					sistema.ingresarEnvioD(codigo, rutR, rutDes, altura, ancho, profundidad);
				}
				else {
					System.out.println("Medicion o mediciones mayor a 60 cm");
				}
			}
			else {
				String rutR=register.getString();
				String rutDes=register.getString();
				double peso=register.getDouble();
				if(peso<=20000) {
					sistema.ingresarEnvioP(codigo, rutR, rutDes, peso);
					
				}
				else {
					System.out.println("Peso supera los 20kg");
				}
			}
		}
		file.close();
		
	}
	/**
	 * Main Menu(Sign in)
	 */
	public static void MenuP() {
		System.out.println("Bienvenidos");
		System.out.println("1)Realizar Envio");
		System.out.println("2)Reporte Envios");
		System.out.println("3)Cerrar Sistema");
	}
	/**
	 * Second Menu for the send
	 */
	public static void Menu2() {
		System.out.println("1)Envios por Tipos");
		System.out.println("2)Envios por Persona");
		System.out.println("3)Envios por Ciudad");
	}
	/**
	 * Menu for realize send
	 */
	public static void Menu3() {
		System.out.println("1)Envios por Dimensiones");
		System.out.println("2)Envios por Peso");
	}
	
		
	/**
	 * Register People in the People list
	 * @param sistema
	 * @param rut
	 * @throws IOException
	 */
	public static void RegistrarCliente(SistemaEnvios sistema,String rut)throws IOException {
		System.out.print("ingrese nombre: ");
		String nombre=StdIn.readString();
		System.out.print("ingrese apellido: ");
		String apellido=StdIn.readString();
		System.out.print("ingrese ciudad: ");
		String ciudad=StdIn.readString();
		while(!sistema.isCiudad(ciudad)) {
			System.out.println("ciudad no registrada ingresela de nuevo");
			System.out.print("ingrese ciudad: ");
			ciudad=StdIn.readString();	
	    }
		sistema.ingresarCliente(rut, nombre, apellido, ciudad);
		System.out.println("Registro exitoso");
	}
	public static void Informacion(SistemaEnvios sistema)throws IOException {
		MenuP();
		System.out.print("ingrese opcion: ");
		int opcion=StdIn.readInt();
		while(opcion!=3) {
			if(opcion==1) {
				Menu3();
				System.out.print("ingrese opcion: ");
				int op=StdIn.readInt();
				if(op==1) {
					System.out.print("ingrese codigo: ");
					String codigo=StdIn.readString();
					System.out.print("ingrese altura (cm): ");
					double altura=StdIn.readDouble();
					System.out.print("ingrese ancho (cm): ");
					double ancho=StdIn.readDouble();
					System.out.print("ingrese profundidad (cm): ");
					double profundidad=StdIn.readDouble();
					if(altura<=60 && ancho<=60 && profundidad<=60) {
						System.out.print("Ingrese Rut Remitente:");
						String rut=StdIn.readString();
						if(!sistema.VerificarRut(rut)) {
							System.out.println("rut no registrado en el sistema");
							System.out.print("Desea registrarse (si/no): ");
							String p=StdIn.readString();
							while(!p.equalsIgnoreCase("si")) {
								System.out.println("Debe registrarse para poder realizar un envio");
								System.out.print("Desea registrarse (si/no): ");
								 p=StdIn.readString();
							}
							RegistrarCliente(sistema,rut);
							System.out.print("Ingrese Rut Destinatario:");
							String rutD=StdIn.readString();
							if(sistema.VerificarRut(rutD)) {
								try {
									sistema.RealizarEnvioD(codigo, rut, rutD, altura, ancho, profundidad);
									System.out.println("Envio exitoso");
								}catch(NullPointerException ex){
									System.out.println(ex.getMessage());
								}
							}
						}
						else {//if users is registered
							System.out.print("Ingrese Rut Destinatario:");
							String rutD=StdIn.readString();
							if(sistema.VerificarRut(rutD)) {
								try {
									sistema.RealizarEnvioD(codigo, rut, rutD, altura, ancho, profundidad);
									System.out.println("Envio exitoso");
								}catch(NullPointerException ex){
									System.out.println(ex.getMessage());
								}
							}
							else {
								System.out.println("Envio Anulado");
							}
						}
					}
					else {
						System.out.println("Algunas de las mediciones exceden los 60 cm");
						
					}
				}
				else {//for weight Send
					System.out.print("ingrese codigo: ");
					String codigo=StdIn.readString();
					System.out.println("Ingrese peso (gramos): ");
					double peso=StdIn.readDouble();
					if(peso<=20000) {
						System.out.print("Ingrese Rut Remitente:");
						String rut=StdIn.readString();
						if(!sistema.VerificarRut(rut)) {
							System.out.println("rut no registrado en el sistema");
							System.out.print("Desea registrarse (si/no): ");
							String p=StdIn.readString();
							while(!p.equalsIgnoreCase("si")) {
								System.out.println("Debe registrarse para realizar un envio");
								System.out.print("Desea registrarse (si/no): ");
								p=StdIn.readString();
							}
							RegistrarCliente(sistema,rut);
							System.out.print("Ingrese Rut Destinatario:");
							String rutD=StdIn.readString();
							if(sistema.VerificarRut(rutD)) {
								try {
									sistema.RealizarEnvioP(codigo, rut, rutD, peso);
									System.out.println("Envio exitoso");
								}catch(NullPointerException ex) {
									System.out.println(ex.getMessage());
								}
							}
							else {
								System.out.println("Envio Anulado rut Destinatario no se encuentra");
							}
						}
						
						else {//if users is registered
							System.out.print("Ingrese Rut Destinatario:");
							String rutD=StdIn.readString();
							if(sistema.VerificarRut(rutD)) {
								try {
									sistema.RealizarEnvioP(codigo, rut, rutD, peso);
									System.out.println("Envio exitoso");
								}catch(NullPointerException ex){
									System.out.println(ex.getMessage());
								}
							}
							else {
								System.out.println("Envio Anulado");
							}
						}
					}
					else {
						System.out.println("peso invalido");
					}
				}
			}
			if(opcion==2) {
				Menu2();
				System.out.println("ingrese opcion: ");
				int opcione=StdIn.readInt();
				if(opcione==1) {
					System.out.println(sistema.ObtenerDatosEnvios());
				}
				if(opcione==2) {
					System.out.print("Ingrese rut: ");
					String rut=StdIn.readString();
					try {
						System.out.println(sistema.obtenerEnviosPersona(rut));
					}catch(NullPointerException ex) {
						System.out.println(ex.getMessage());
					}
				}
				if(opcione==3) {
					System.out.println(sistema.obtenerDatosCiudades1());
				}
			
			}
			MenuP();
			System.out.print("ingrese opcion: ");
			opcion=StdIn.readInt();
		}
		sistema.ActualizarDatos();//update information if system is closed
		
	}
		

	/**
	 * @param args
	 */
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		SistemaEnvios sistema=new SistemaEnviosImpl();
		leerCiudades(sistema);
		leerClientes(sistema);
		leerEnvios(sistema);
		Informacion(sistema);
	}

}
