package co.com.SuperPagos.modelo.dto;

import java.io.Serializable;

public class ParametrosRecragaRequest  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ParametrosRecragaRequest(){
		datos = new DatosRecarga();
	}
	/*
	 * Codigo de proceso suministrado por punto red
	 */
	private String proceso;
	
	/*
	 * Usuario unico para el host suministrado por punto red
	 */
	private String usuarioHost;
	
	/*
	 * Clave unico para el host suministrado por punto red. Se debe enviar cifrada en sha256
	 */
	private String claveHost;
	
	/*
	 * Identificacion del comercio suministrada por punto red
	 */
	private String comercio;
	
	/*
	 * Identificaicon del punto de venta suministrado por punto red 
	 */
	private String puntoVenta;
	
	/*
	 * DatosRecarga de la recarga
	 */
	private DatosRecarga datos;
	
	
	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getUsuarioHost() {
		return usuarioHost;
	}

	public void setUsuarioHost(String usuarioHost) {
		this.usuarioHost = usuarioHost;
	}

	public String getClaveHost() {
		return claveHost;
	}

	public void setClaveHost(String claveHost) {
		this.claveHost = claveHost;
	}

	public String getComercio() {
		return comercio;
	}

	public void setComercio(String comercio) {
		this.comercio = comercio;
	}

	public String getPuntoVenta() {
		return puntoVenta;
	}

	public void setPuntoVenta(String puntoVenta) {
		this.puntoVenta = puntoVenta;
	}

	public DatosRecarga getDatos() {
		return datos;
	}

	public void setDatos(DatosRecarga datos) {
		this.datos = datos;
	}	

}
