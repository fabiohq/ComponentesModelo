package co.com.codesa.socketcodesaoperadorexterno.dao.pool.hilo;

import java.util.Calendar;

import co.com.codesa.socketcodesaoperadorexterno.bean.ResultadoBean;
import co.com.codesa.socketcodesaoperadorexterno.dao.general.DatosTrama;
import co.com.codesa.socketcodesaoperadorexterno.manager.LogsManagerOperadorExterno;
import co.com.codesa.socketcodesaoperadorexterno.socket.SocketClienteWSSincrono;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.ControladorEtiquetas;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.WebGambleConstants;

/**
 * 
 * @author Cristian Gomez Ruiz
 * @description: Hilo encargado de enviar la trama de conectividad con movistar
 * 				 segun la ISO_8583 la trama 0800 y la respuesta es la 0810.
 */
public class HiloConectividad extends Thread{
	
	public static String tramaConectividad = "?1"
			+ "0800"
	        + "8220000100000000"
	        + "0400000000000000"
			+ "?2"//hora de transmision MMDDHHMMSS
			+ "?3"
			+ "301";
	
	private String ip = "";
	private String puerto = "";
	private String timeout = "";
	
	private final String PARAMENCABEZADOISO = "ENCMOISO";
	
	private int tiempoEnvioTrama = 60000;
	boolean seguir=true;
	//variable que indica si existe conectividad con el operador de movistar
	public static boolean conectividad;
	private String echoTest;
	
	private DatosTrama datosTrama;
	
	public HiloConectividad() 
	{
		LogsManagerOperadorExterno.getInstance().info("Se instancia el hilo de conectividad con el operador movil");
		String encabezado = "";
		String codigoGestor = "";// = ControladorEtiquetas.conexion.getString("codigogr");
		
		try {
			
			LogsManagerOperadorExterno.getInstance().info("Consulando encabezado de la trama ISO configurado en la base de datos "+PARAMENCABEZADOISO);
			datosTrama = new DatosTrama(PARAMENCABEZADOISO);
			
			ResultadoBean resultadoBean = (ResultadoBean) datosTrama.action(DatosTrama.CONSULTA_PARAM_SIST);
			
			if(resultadoBean != null && resultadoBean.getResultado()){
				encabezado = String.valueOf(resultadoBean.getObject()).trim() ;
			}
			
			if ( encabezado != null || !encabezado.isEmpty() ){
				
				LogsManagerOperadorExterno.getInstance().info("El encabezado iso configurado para movistar es "+encabezado);
			}
			else{
				LogsManagerOperadorExterno.getInstance().warning("Se ha producido un error grave: El encabezado iso configurado para "
						+ "movistar no esta configurado en los parametros del sistema >> "+PARAMENCABEZADOISO);
			}
			
		} catch (Exception e) {
			LogsManagerOperadorExterno.getInstance().warning("Se ha producido un error grave: error consultando el encabezado iso configurado para "
					+ "movistar>> "+PARAMENCABEZADOISO + " Excepcion "+e.getMessage());
		}
		
		
		try {
		
			//tiempoEnvioTrama = Integer.parseInt(ControladorEtiquetas.conexion.getString("tiempoverificaconex")) ;
			LogsManagerOperadorExterno.getInstance().info("El tiempo configurado para enviar la trama de conectivdad es >> "+String.valueOf(tiempoEnvioTrama));
		
		} catch (Exception e) {
			
			if(e != null){
				LogsManagerOperadorExterno.getInstance().info("Problemas obteniendo el tiempo configurado para envio de trama de conectividad Error: "+ e.getMessage());
			}
		}
		
		ip = WebGambleConstants.getInstancia().getIpProveedor();
		puerto = WebGambleConstants.getInstancia().getPuertoProveedor();
	    timeout = WebGambleConstants.getInstancia().getTimeoutProveedor();
		
		LogsManagerOperadorExterno.getInstance().info("Obtenidendo datos del operador movil ... "+ip+":"+puerto +" timeout "+ timeout);
		
		if(encabezado == null || encabezado.isEmpty()){
			LogsManagerOperadorExterno.getInstance().warning("Se ha producido un error grave: El encabezado de la trama "
					+ "0800 no se ha configurado el parametro del sistema "+PARAMENCABEZADOISO);
		}
		else{
			if (encabezado.length() != 12) {
				
				LogsManagerOperadorExterno.getInstance().warning("Se ha producido un error grave: El encabezado de la trama "
						+ "0800 configurado en los parametro del sistema  no tiene 12 caracteres");
			}
			else{
				tramaConectividad = tramaConectividad.replace("?1", encabezado);
			}
		}
		
		if(codigoGestor == null || codigoGestor.isEmpty()){
			LogsManagerOperadorExterno.getInstance().warning("Se ha producido un error grave: El codigo de gestor de la trama "
					+ "0800 no se ha configurado en el archivo configproveedor.properties");
		}
		else{
			if (codigoGestor.length() != 11) {
				
				LogsManagerOperadorExterno.getInstance().warning("Se ha producido un error grave: El codigo de gestor de la trama "
						+ "0800 configurado en el archivo configproveedor.properties no tiene 11 caracteres");
			}
			else{
				tramaConectividad = tramaConectividad.replace("?3", codigoGestor);
			}
		}
	}
	
	@Override
	public void run() {
		
		LogsManagerOperadorExterno.getInstance().info("Empieza a validar conectividad con movistar");
		SocketClienteWSSincrono scSincrono = new SocketClienteWSSincrono();

		LogsManagerOperadorExterno.getInstance().info("URL de conexion con el operador "+ip+":"+puerto +" timeout "+ timeout);
		
		while(seguir){
		
			try {
				
				LogsManagerOperadorExterno.getInstance().info("Conectandose con el operador movil ... "+ip+":"+puerto +" timeout "+ timeout);
	        	Calendar calendar = Calendar.getInstance();
	        	
	        	String fecha = String.format("%02d%02d%02d%02d%02d", 
	                	calendar.get(Calendar.MONTH)+1,
	                	calendar.get(Calendar.DATE),
	                	calendar.get(Calendar.HOUR_OF_DAY),
	                	calendar.get(Calendar.MINUTE),
	                	calendar.get(Calendar.SECOND)
	        			);
	        	tramaConectividad = tramaConectividad.replace("?2", fecha);
	        	
				String tramaRecibida = scSincrono.escribe_lee_SocketClient(tramaConectividad, ip, puerto, timeout, 40);
			
				if( tramaRecibida != null || !tramaRecibida.isEmpty() ){
				
					LogsManagerOperadorExterno.getInstance().info("Trama de conectividad recibida "+tramaRecibida);
					echoTest = tramaRecibida.substring(tramaRecibida.length()-3, tramaRecibida.length());
					LogsManagerOperadorExterno.getInstance().info("El echo test de la conexion es "+echoTest);
				
					if( ( echoTest != null || !echoTest.isEmpty() ) && echoTest.equals("301")){ 
				
						LogsManagerOperadorExterno.getInstance().info("Se establece conexion con el operador de forma exitosa");
						conectividad = true;
					}
				
					else{
						LogsManagerOperadorExterno.getInstance().info("No se puede establecer conexion con el operador movil "+ip+":"+puerto);
						conectividad = false;
					}
				}
				else{
					LogsManagerOperadorExterno.getInstance().info("La trama de conectividad recibida esta vacia "+ip+":"+puerto);
					LogsManagerOperadorExterno.getInstance().info("No se puede establecer conexion con el operador movil "+ip+":"+puerto);
					conectividad = false;
				}
			} 
			
			catch (Exception e) {
				if(e != null){
					conectividad = false;
					LogsManagerOperadorExterno.getInstance().info("Problemas de conectividad con el operador "+ip+":"+puerto+" Excepcion "+e.getMessage() );
				}		
			}
			finally{
				try {
					
					Thread.sleep(tiempoEnvioTrama);// 1 minuto
				
				} catch (InterruptedException e) {
					conectividad = false;
					LogsManagerOperadorExterno.getInstance().info("Problemas con el hilo de conectividad "+ip+":"+puerto+" Excepcion "+e.getMessage() );
				}  
			}
		 }

	}

}
