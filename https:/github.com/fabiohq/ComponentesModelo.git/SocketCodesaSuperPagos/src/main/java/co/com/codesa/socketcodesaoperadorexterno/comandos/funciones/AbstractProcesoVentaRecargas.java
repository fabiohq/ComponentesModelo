package co.com.codesa.socketcodesaoperadorexterno.comandos.funciones;

import java.io.Serializable;
import java.util.StringTokenizer;
import java.util.logging.Logger;
/*
import co.com.SuperPagos.comandos.CmndLogeo;
import co.com.SuperPagos.comandos.CmndRecargas;
*/
import co.com.codesa.socketcodesaoperadorexterno.bean.ConfigConexionTrama;
import co.com.codesa.socketcodesaoperadorexterno.comandos.Action;
import co.com.codesa.socketcodesaoperadorexterno.dao.general.DatosTrama;
import co.com.codesa.socketcodesaoperadorexterno.general.LogsManager;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.Constantes;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.General;


/**
 * @author Fabio Hernandez 12/08/2017 
 * Clase abstracta que funciona como controlador en el proceso de una venta
 */
public abstract class AbstractProcesoVentaRecargas implements Action,Serializable{

	protected static final long serialVersionUID = 1L;
	private Logger logger = LogsManager.getInstance();
	private String tiempollegada;
	ConfigConexionTrama configConex;
	
	public abstract String getIdTrama();

	@Override
	public Object execute(Object o) throws Exception {
		ConfigConexionTrama configConex = new ConfigConexionTrama();
		configConex.setValidaTAT(this.configConex.getValidaTAT());

		String trama = (String) o;
		logger.info("\n");
		logger.info("==> CODESA INICIA PROCESO VENTA DE RECARGAS PARA EL "+Constantes.NOMBRE_OPERADOR
				+"\n=> TRAMA RECIBIDA: " + trama
				+"\n=> HILO ACTUAL: " + Thread.currentThread().getId()
				+"\n=> ID TRAMA: " + getIdTrama());
		tiempollegada = General.obtenerFechaHoraActual( true );
		
		int operacion = 0;
		String login = "";
		String telCelular = "";
		String valorRecargar = "";
		String tramaOut = null;
		String tramaResponse = null;
		
		if (trama.contains("@")) {
			trama = trama.replace("@", ",");
		}else {
			trama = trama.concat(",").concat("-1");
		}
		
		StringTokenizer st = new StringTokenizer(trama, ",");
		DatosTrama datos = null;

		if (st.hasMoreElements()) {
			operacion = Integer.parseInt(st.nextToken().trim());
			login = st.nextToken();
			st.nextToken();
			st.nextToken();//SE USAN PARA QUE AVANCE EL Tokenizer
			st.nextToken();//SE USAN PARA QUE AVANCE EL Tokenizer
			telCelular = st.nextToken();
			valorRecargar = st.nextToken();
		}

		// INICIO: CONSULTA DE CONFIGURACION
		configConex.setV_loginUser(login);
		configConex.setTelefonoCelular(telCelular);
		configConex.setVlrRecarga(valorRecargar);
		configConex.setV_trama(trama);

		//Se crea obj datos trama pasando al constructor el obj configConex
		datos = new DatosTrama(configConex);
		configConex = (ConfigConexionTrama) datos.action(DatosTrama.GENERA_TRAMA_RECARGAS);
				
		//TRY CATCH PARA MANEJO DE EXCEPCIONES AL MOMENTO DE EJECUPAR OPERACION DE VENTA CON EL OPERADOR EXTERNO
		try {
			
			//ENVIO VENTA AL OPERADOR			
			//EjecutarOperacionRecarga procesoOperadorExterno = new EjecutarOperacionRecarga();
			//tramaResponse = (String) procesoOperadorExterno.clientePuntoRedRecargas(configConex.getV_trama_operador(), configConex.getClaveOperador(), logger, Thread.currentThread().getId());
			/*
			CmndRecargas cmndprincipal = new CmndRecargas();
			tramaResponse = (String) cmndprincipal.CmndRecargas(configConex.getV_trama_operador(), configConex.getClaveOperador(), logger, Thread.currentThread().getId());
			*/
			int idOperador =(int) (Math.random()*99+0);
			String strIdOperador= "12345"+idOperador;
			tramaResponse ="200|Ok|"+strIdOperador+"|123456|Trama response";
			configConex.setV_trama_operador(tramaResponse);
			
    	    //REGISTRO VENTA EXITOSA EN CODESA
            datos.setObjeto(configConex);
            configConex = (ConfigConexionTrama) datos.action( DatosTrama.TRANSFER_REGISTRA_RECARGA );
		
		} catch (Exception e) {
			
			//SE MANEJAN POSIBLES ERRORES QUE SE PRESENTAN AL INTENTAR REALIZAR UNA VENTA CON EL OPERADOR        	
        	String smsError = Constantes.SMS_ERROR_INESPERADO;
        	logger.info("=> ERROR: <<<< "+e.getMessage()+" >>>> - hilo actual: "+Thread.currentThread().getId()+"-"+login);
        	logger.info("=> ID TRANSACCION CODESA: <<<< "+configConex.getV_numtransaccion()+" >>>> - hilo actual: "+Thread.currentThread().getId()+"-"+login);
        	
        	//450: CODIGO INTENTO FALLIDO
        	configConex.setCodEntidad(Constantes.COD_450);               
        	
        	if(e.toString( ).contains( "Read timed out" ) || 
               e.toString( ).contains( "Connection timed out" )||
               e.toString( ).contains( "java.net.SocketTimeoutException")||
               (e.getMessage() != null && e.getMessage().contains( "timed out"))){
        		
            	this.logger.info("=> TIME OUT CONFIGURADO[ "+ configConex.getV_time_out()+ " ]  hilo actual " + Thread.currentThread().getId());        		        		
            	
            	//326: CODIGO TIMEOUT                   
                configConex.setCodDatos(Constantes.COD_DATO_TIME_OUT_326);                
                smsError = Constantes.SMS_ERROR_CONEXION;
                               
        	}else{            	           	   
        		//327: CODIGO EXCEPTION
        	   configConex.setCodDatos(Constantes.COD_DATO_EXCEPTION_327);                
            }
        	
        	//REGISTRO VENTA FALLIDA EN CODESA
        	configConex.setV_resultado(e.getMessage());
            datos.setObjeto(configConex);
            configConex  = ( ConfigConexionTrama )datos.action( DatosTrama.REGISTRA_INTENTO_FALLIDO_RECARGA);
            
            //Se lanza excepcion
            throw new Exception(String.valueOf(operacion)+",1," + login + "," +  smsError);    
		}finally{
			logger.info("\n==>CODESA FINALIZA PROCESO DE RECARAGA PARA EL "+Constantes.NOMBRE_OPERADOR+" - Hilo :" + Thread.currentThread().getId()
					+"\n=> TIEMPO EN EL QUE INICIÓ   TODO EL PROCESO DE VENTA DE RECARGAS: "+tiempollegada
					+"\n=> TIEMPO EN EL QUE FINALIZÓ TODO EL PROCESO DE VENTA DE RECARGAS: "+General.obtenerFechaHoraActual(true)
					+"\n=> ID TRANSACCION CODESA: "+configConex.getV_numtransaccion()
					+"\n");
			
			tramaOut = configConex.getV_trama();
			configConex.clean();
		}
		
		return tramaOut;
	}
}