package SistemaEnvios.Dominio;

public class EnvioP extends Envio {
	private double peso;
	public EnvioP(String codigo,String rutO,String rutD,double peso) {
		super(codigo,rutO,rutD);
		this.peso=peso;
	}
	public double calcularValorEnvio() {
		return peso*1000;
	}
	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}
	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	
}
