package SistemaEnvios.Logica;

import SistemaEnvios.Dominio.Envio;

public class NodoEnvio {
	private Envio envio;
	private NodoEnvio next;
	private NodoEnvio previo;
	public NodoEnvio(Envio envio) {
		this.envio=envio;
		next=null;
		previo=null;
	}
	/**
	 * @return the envio
	 */
	public Envio getEnvio() {
		return envio;
	}
	/**
	 * @param envio the envio to set
	 */
	public void setEnvio(Envio envio) {
		this.envio = envio;
	}
	/**
	 * @return the next
	 */
	public NodoEnvio getNext() {
		return next;
	}
	/**
	 * @param next the next to set
	 */
	public void setNext(NodoEnvio next) {
		this.next = next;
	}
	/**
	 * @return the previo
	 */
	public NodoEnvio getPrevio() {
		return previo;
	}
	/**
	 * @param previo the previo to set
	 */
	public void setPrevio(NodoEnvio previo) {
		this.previo = previo;
	}
	
	

}
