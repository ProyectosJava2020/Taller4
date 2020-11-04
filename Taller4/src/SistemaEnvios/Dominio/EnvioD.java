package SistemaEnvios.Dominio;

public class EnvioD extends Envio{
	private double altura;
	private double ancho;
	private double profundidad;
	public EnvioD(String codigo, String rutO, String rutD, double altura, double ancho, double profundidad) {
		super(codigo, rutO, rutD);
		this.altura = altura;
		this.ancho = ancho;
		this.profundidad = profundidad;
	}
	public double calcularValorEnvio() {
		return (altura+ancho+profundidad)*50;
	}
	/**
	 * @return the altura
	 */
	public double getAltura() {
		return altura;
	}
	/**
	 * @param altura the altura to set
	 */
	public void setAltura(double altura) {
		this.altura = altura;
	}
	/**
	 * @return the ancho
	 */
	public double getAncho() {
		return ancho;
	}
	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(double ancho) {
		this.ancho = ancho;
	}
	/**
	 * @return the profundidad
	 */
	public double getProfundidad() {
		return profundidad;
	}
	/**
	 * @param profundidad the profundidad to set
	 */
	public void setProfundidad(double profundidad) {
		this.profundidad = profundidad;
	}
	
	
	
	

}
