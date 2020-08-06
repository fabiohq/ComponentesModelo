package co.com.SuperPagos.modelo.dto;

import java.io.Serializable;
import java.util.logging.Logger;

public class ParametroRecargasDTO  implements Serializable{

		
	ParametrosRecragaRequest parametrosRecragaRequest;
	
	ParametrosRecragaResponse parametrosRecragaResponse;
		
	public ParametroRecargasDTO(){
		parametrosRecragaRequest = new ParametrosRecragaRequest();
		parametrosRecragaResponse = new ParametrosRecragaResponse();
		//datosRecarga = new DatosRecarga();
		
	}
	/*
	 * obj logger enviado desde el componente que contenga el cliente
	 */
	private Logger logger;
	
	/*
	 * Tiempo maximo definido por codesa para una transaccion
	 */
	private int timeOut;
	/*
	 * end point punto red
	 */
	private String URL;
	
	/*
	 * Id de hilo con el que inicia la transaccion en el componente que contiene el cliente
	 */
	private long idHilo;
	
	/*
	 * Request de tipo JSON que se envia al proveedor
	 */
	private String requestJson;
	
	/*
	 * Response de tipo JSON que se resibe del proveedor
	 */
	private String responseJson;
	
	/*
	 * Trama retornada al componente que contiene el cliente
	 */
	private String tramaResponse;

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public long getIdHilo() {
		return idHilo;
	}

	public void setIdHilo(long idHilo) {
		this.idHilo = idHilo;
	}

	public String getTramaResponse() {
		return tramaResponse;
	}

	public void setTramaResponse(String tramaResponse) {
		this.tramaResponse = tramaResponse;
	}

	public ParametrosRecragaRequest getParametrosRecragaRequest() {
		return parametrosRecragaRequest;
	}

	public void setParametrosRecragaRequest(ParametrosRecragaRequest parametrosRecragaRequest) {
		this.parametrosRecragaRequest = parametrosRecragaRequest;
	}

	public ParametrosRecragaResponse getParametrosRecragaResponse() {
		return parametrosRecragaResponse;
	}

	public void setParametrosRecragaResponse(ParametrosRecragaResponse parametrosRecragaResponse) {
		this.parametrosRecragaResponse = parametrosRecragaResponse;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getRequestJson() {
		return requestJson;
	}

	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}

	public String getResponseJson() {
		return responseJson;
	}

	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = new Integer(timeOut)*1000;
	}
		
}