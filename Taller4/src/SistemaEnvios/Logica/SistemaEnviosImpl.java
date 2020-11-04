package SistemaEnvios.Logica;
import SistemaEnvios.Dominio.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ucn.ArchivoSalida;
import ucn.Registro;
public class SistemaEnviosImpl implements SistemaEnvios{
	private LinkedList<Persona> listaPersonas;
	private List<Ciudad> listaCiudades;
	private ListaEnvios listaEnvios;
	/**
	 * for format $1.000
	 */
	private static DecimalFormat F = new DecimalFormat("###,###.##"); 
	public SistemaEnviosImpl() {
		listaPersonas=new LinkedList<Persona>();
		listaCiudades=new ArrayList<Ciudad>();
		listaEnvios=new ListaEnvios();
	}
	
	public void ingresarCiudad(String ciudad) {
		Ciudad ciudadd=new Ciudad(ciudad);
		listaCiudades.add(ciudadd);
	}
	/**
	 * Check if the city is in the ArrayList<Ciudades>
	 */
	public boolean isCiudad(String ciudad) {
		for(Ciudad c:listaCiudades) {
			if(c.getNombre().equals(ciudad)) {
				return true;
			}
		}
		return false;
	}
	public void ingresarCliente(String rut,String nombre,String apellido,String ciudad) {
		Persona pe=new Persona(rut,nombre,apellido,ciudad);
		listaPersonas.add(pe);
	}
	/**
	 * The shipment by weight is entered, in addition the origin routing is associated with the shipment
       done and the destination routing with the shipment received
	 */
	public void ingresarEnvioP(String codigo,String rutO,String rutD,double peso) {
		Envio envioP= new EnvioP(codigo,rutO,rutD,peso);
		Persona remitente=null;
		Persona destinataria=null;
		for(Persona p:listaPersonas) {
			if(p.getRut().equals(rutO)) {
				remitente=p;
				break;
			}
		}
		for(Persona pi:listaPersonas) {
			if(pi.getRut().equals(rutD)) {
				destinataria=pi;
				break;
			}
		}
		remitente.getListaEnviosR().IngresarEnvio(envioP);
		destinataria.getListaEnviosD().IngresarEnvio(envioP);
		listaEnvios.IngresarEnvio(envioP);
	}
	/**
	 * The shipment is entered by dimensions, in addition the origin routine is associated with the
        shipment made and the destination routing with the shipment received
	 */
	public void ingresarEnvioD(String codigo,String rutO,String rutD,double altura, double ancho,double profundidad) {
		Envio envioD= new EnvioD(codigo,rutO,rutD,altura,ancho,profundidad);
		Persona remitente=null;
		Persona destinataria=null;
		for(Persona p:listaPersonas) {
			if(p.getRut().equals(rutO)) {
				remitente=p;
				break;
			}
		}
		for(Persona pi:listaPersonas) {
			if(pi.getRut().equals(rutD)) {
				destinataria=pi;
				break;
			}
		}
		remitente.getListaEnviosR().IngresarEnvio(envioD);
		destinataria.getListaEnviosD().IngresarEnvio(envioD);
		listaEnvios.IngresarEnvio(envioD);
	}
	/**
	 * The type is sent by dimensions, in addition the origin routine is associated with
        the shipment made and the destination routing with the shipment received
	 */
	public void RealizarEnvioD(String codigo,String rutO,String rutD,double altura, double ancho,double profundidad) {
		Persona remitente=null;
		Persona destinataria=null;
		for(Persona p:listaPersonas) {
			if(p.getRut().equals(rutO)) {
				remitente=p;
				break;
			}
		}
		for(Persona pe:listaPersonas) {
			if(pe.getRut().equals(rutD)) {
				destinataria=pe;
			}
		}
		if(remitente!=null) {//The sender already checked in app
			EnvioD envioD=new EnvioD(codigo,rutO,rutD,altura,ancho,profundidad);
			remitente.getListaEnviosR().IngresarEnvio(envioD);
			destinataria.getListaEnviosD().IngresarEnvio(envioD);
			listaEnvios.IngresarEnvio(envioD);
		}
		else {
			throw new NullPointerException("Envio no existe");
		}
		
	}
	/**
	 * The type is sent by weight, in addition the origin routine is associated with
        the shipment made and the destination routing with the shipment received
	 */
	public void RealizarEnvioP(String codigo,String rutO,String rutD,double peso) {
		Persona remitente=null; 
		Persona destinataria=null;
		for(Persona p:listaPersonas) {
			if(p.getRut().equals(rutO)) {
				remitente=p;
				break;
			}
		}
		for(Persona pi:listaPersonas) {
			if(pi.getRut().equals(rutD)) {
				destinataria=pi;
				break;
			}
		}
		if(destinataria !=null) {//The sender already checked in app
			Envio envioP=new EnvioP(codigo,rutO,rutD,peso);
			remitente.getListaEnviosR().IngresarEnvio(envioP);
			destinataria.getListaEnviosD().IngresarEnvio(envioP);
			listaEnvios.IngresarEnvio(envioP);
		}
		else {
			throw new NullPointerException("Rut destinatario no existe");
		}
	}
	/**
	 * check rut in the PeopleList
	 */
	public boolean VerificarRut(String rut) {
		for(Persona p:listaPersonas) {
			if(p.getRut().equals(rut)) {
				return true;
			}
		}
		return false;	
	}
	/**
	 * Obtain data depending on the type of shipment
	 */
	public String ObtenerDatosEnvios() {
		String r="";
		NodoEnvio current=listaEnvios.getFirst().getNext();
		while(current!=listaEnvios.getFirst()) {//iterate the list Send
			if(current.getEnvio() instanceof EnvioD) {//separate for type 
				EnvioD envioD=(EnvioD)current.getEnvio();
				r+="Codigo Envio:"+envioD.getCodigo()+" Tipo:Dimensional  ValorCobrado:$"+F.format((long)envioD.calcularValorEnvio())+"\n";
			}
			else {
				EnvioP envioP=(EnvioP)current.getEnvio();
				r+="Codigo Envio:"+envioP.getCodigo()+" Tipo:Peso  ValorCobrado:$"+F.format((long)envioP.calcularValorEnvio())+"\n";
			}
			current=current.getNext();
		}
		//for the first
		if(current.getEnvio() instanceof EnvioD) {
			EnvioD envioD=(EnvioD)current.getEnvio();
			r+="Codigo Envio:"+envioD.getCodigo()+" Tipo:Dimensional  ValorCobrado:$"+F.format((long)envioD.calcularValorEnvio())+"\n";
		}
		else {
			EnvioP envioP2=(EnvioP)current.getEnvio();
			r+="Codigo Envio:"+envioP2.getCodigo()+" Tipo:Peso ValorCobrado:$"+F.format((long)envioP2.calcularValorEnvio())+"\n";
			
		}
		return r;
				
	}
	/**
	 * Obtains all shipments associated with said person whether sent or received
	 */
	public String obtenerEnviosPersona(String rut) {
		String r="";
		Persona p=null;
		for(Persona per:listaPersonas) {//search persons by rut
			if(per.getRut().equals(rut)) {
				p=per;
				break;
			}
		}
		if(p!=null) {
			r+="Nombre:"+p.getNombre()+" Apellido:"+p.getApellido()+" rut:"+p.getRut()+"\n";
			r+="Lista de envios recibidos \n";
			
			NodoEnvio firts=p.getListaEnviosD().getFirst();
			if(firts!=null) {
				NodoEnvio current=firts.getNext();//
				while(current!=firts) {//iterate the local list
					Envio envio=current.getEnvio();
					if(envio instanceof EnvioD) {//separate for type
						EnvioD envioD=(EnvioD)envio;
						r+="Codigo:"+envioD.getCodigo()+" Tipo:Dimensional  ValorCobrado:$"+F.format((long)envioD.calcularValorEnvio())+"\n";
					}
					else {
						EnvioP envioP=(EnvioP)envio;
						r+="Codigo:"+envioP.getCodigo()+" Tipo:Peso  ValorCobrado:$"+F.format((long)envioP.calcularValorEnvio())+"\n";
					}
					current=current.getNext();
				}
				//for first
				Envio envioF=current.getEnvio();
				if(envioF instanceof EnvioD) {
					EnvioD envioDF=(EnvioD)envioF;
					r+="Codigo:"+envioDF.getCodigo()+" Tipo:Dimensional  ValorCobrado:$"+F.format((long)envioDF.calcularValorEnvio())+"\n";
				}
				else {
					EnvioP envioPF=(EnvioP)envioF;
					r+="Codigo:"+envioPF.getCodigo()+" Tipo:Peso  ValorCobrado:$"+F.format((long)envioPF.calcularValorEnvio())+"\n";
				}
			}
			else {
				r+="No hay envios \n";//if first is null
			}
			
			r+="Lista de envios Enviados \n";
			NodoEnvio firts2=p.getListaEnviosR().getFirst();
			if(firts2!=null) {
				NodoEnvio current2=firts2.getNext();// 
				while(current2!=firts2) {//iterate the local list
					Envio envio2=current2.getEnvio();
					if(envio2 instanceof EnvioD) {//separate for type
						EnvioD envioD2=(EnvioD)envio2;
						r+="Codigo:"+envioD2.getCodigo()+" Tipo:Dimensional  ValorCobrado:$"+F.format((long)envioD2.calcularValorEnvio())+"\n";
					}
					else {
						EnvioP envioP2=(EnvioP)envio2;
						r+="Codigo:"+envioP2.getCodigo()+" Tipo:Peso  ValorCobrado:$"+F.format((long)envioP2.calcularValorEnvio())+"\n";
					}
					current2=current2.getNext();
				}
				//for first 
				Envio envio2F=current2.getEnvio();
				if(envio2F instanceof EnvioD) {
					EnvioD envioD2=(EnvioD)envio2F;
					r+="Codigo:"+envioD2.getCodigo()+" Tipo:Dimensional  ValorCobrado:$"+F.format((long)envioD2.calcularValorEnvio())+"\n";
				}
				else {
					EnvioP envioP22=(EnvioP)envio2F;
					r+="Codigo:"+envioP22.getCodigo()+" Tipo:Peso ValorCobrado:$"+F.format((long)envioP22.calcularValorEnvio())+"\n";
				}
			}
			else {
				r+="No hay envios recibidos \n";
			}
		}
		else {
			throw new NullPointerException("Persona no encontrada");
		}
		return r;
	}
	/**
	 * Get the number of shipments and deliveries for each of the cities
	 */
	public String obtenerDatosCiudades1() {
		String r="";
		Iterator<Ciudad> it=listaCiudades.iterator();
		while(it.hasNext()) {
			Ciudad ciudad=(Ciudad)it.next();
			r+="Ciudad:"+ciudad.getNombre()+"\n";
			int contadorReci=0;
			int contadorEnviados=0;
			Iterator<Persona> it2=listaPersonas.iterator();
			while(it2.hasNext()) {
				Persona p=(Persona)it2.next();
				if(p.getCiudad().equals(ciudad.getNombre())) {
					contadorReci+=p.getListaEnviosD().getCantidadElemento();
					contadorEnviados+=p.getListaEnviosR().getCantidadElemento();
				}
			}
			String cR=String.valueOf(contadorReci);//Convert to String
			String cE=String.valueOf(contadorEnviados);
			r+="Envios recibidos:"+cR+"\n";
			r+="Envios enviados:"+cE+"\n";
		}
		return r;
	}
	/**
	 * update the information files
	 */
	public void ActualizarDatos() throws IOException {
		ArchivoSalida file=new ArchivoSalida("Ciudades.txt");
		for(Ciudad c:listaCiudades) {
			Registro register= new Registro(1);
			register.agregarCampo(c.getNombre());
			file.writeRegistro(register);
		}
		file.close();
		
		ArchivoSalida clientes=new ArchivoSalida("Clientes.txt");
		for(Persona p:listaPersonas) {
			Registro reg=new Registro(4);
			reg.agregarCampo(p.getRut());
			reg.agregarCampo(p.getNombre());
			reg.agregarCampo(p.getApellido());
			reg.agregarCampo(p.getCiudad());
			clientes.writeRegistro(reg);
		}
		clientes.close();
		
		ArchivoSalida Envios=new ArchivoSalida("Envios.txt");
		NodoEnvio firts=listaEnvios.getFirst();
		NodoEnvio current=firts.getNext();
		while(current!=firts) {
			Envio envio=current.getEnvio();
			if(envio instanceof EnvioD) {
				EnvioD envioD=(EnvioD)envio;
				Registro regD=new Registro(7);
				regD.agregarCampo(envioD.getCodigo());
				regD.agregarCampo("D");
				regD.agregarCampo(envioD.getRutO());
				regD.agregarCampo(envioD.getRutD());
				regD.agregarCampo(envioD.getAltura());
				regD.agregarCampo(envioD.getAncho());
				regD.agregarCampo(envioD.getProfundidad());
				Envios.writeRegistro(regD);
			}
			else {
				EnvioP envioP=(EnvioP)envio;
				Registro regP=new Registro(5);
				regP.agregarCampo(envioP.getCodigo());
				regP.agregarCampo("P");
				regP.agregarCampo(envioP.getRutO());
				regP.agregarCampo(envioP.getRutD());
				regP.agregarCampo(envioP.getPeso());
				Envios.writeRegistro(regP);
			}
			current=current.getNext();
		}
		//FOR THE FIRTS
		Envio envioF=current.getEnvio();
		if(envioF instanceof EnvioD) {
			EnvioD envioD=(EnvioD)envioF;
			Registro regD=new Registro(7);
			regD.agregarCampo(envioD.getCodigo());
			regD.agregarCampo("D");
			regD.agregarCampo(envioD.getRutO());
			regD.agregarCampo(envioD.getRutD());
			regD.agregarCampo(envioD.getAltura());
			regD.agregarCampo(envioD.getAncho());
			regD.agregarCampo(envioD.getProfundidad());
			Envios.writeRegistro(regD);
		}
		else {
			EnvioP envioP=(EnvioP)envioF;
			Registro regP=new Registro(5);
			regP.agregarCampo(envioP.getCodigo());
			regP.agregarCampo("P");
			regP.agregarCampo(envioP.getRutO());
			regP.agregarCampo(envioP.getRutD());
			regP.agregarCampo(envioP.getPeso());
			Envios.writeRegistro(regP);
		}
		Envios.close();
	}
}
