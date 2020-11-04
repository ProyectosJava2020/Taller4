package SistemaEnvios.Dominio;

public abstract class Envio {
	private String codigo;
	private String rutO;
	private String rutD;
	public Envio(String codigo, String rutO, String rutD) {
		this.codigo = codigo;
		this.rutO = rutO;
		this.rutD = rutD;
	}
	public abstract double calcularValorEnvio();
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the rutO
	 */
	public String getRutO() {
		return rutO;
	}
	/**
	 * @param rutO the rutO to set
	 */
	public void setRutO(String rutO) {
		this.rutO = rutO;
	}
	/**
	 * @return the rutD
	 */
	public String getRutD() {
		return rutD;
	}
	/**
	 * @param rutD the rutD to set
	 */
	public void setRutD(String rutD) {
		this.rutD = rutD;
	}
	
	
	
	
	
}
