package co.com.SuperPagos.modelo.dto;

import java.io.Serializable;

public class ParametrosRecragaResponse  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * La clase DatosRecargaResponse contiene el id de transaccion de punto red y un mensaje relacionado a descripcion de la respuesta
	 */
	DatosRecargaResponse datos;
	
	/*
	 * estado del servicio True o False
	 */
	private boolean estado;
	
	/*
	 * mensaje de respuesta del servicio
	 */
	private String mensaje;
	
	/*
	 * Codigo de respuesta 00 = OK
	 */
	private String codigo;

	public ParametrosRecragaResponse(){
		datos = new DatosRecargaResponse();
	}
	
	public DatosRecargaResponse getDatos() {
		return datos;
	}

	public void setDatos(DatosRecargaResponse datos) {
		this.datos = datos;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
		
}