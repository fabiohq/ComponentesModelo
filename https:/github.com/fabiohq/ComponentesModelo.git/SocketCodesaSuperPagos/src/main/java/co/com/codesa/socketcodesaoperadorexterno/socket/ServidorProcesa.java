
package co.com.codesa.socketcodesaoperadorexterno.socket;

import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.comandos.Action;
import co.com.codesa.socketcodesaoperadorexterno.comandos.CtrlComandos;
import co.com.codesa.socketcodesaoperadorexterno.general.LogsManager;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.Constantes;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.General;


public class ServidorProcesa {

	private Logger logger = LogsManager.getInstance();

	public ServidorProcesa() {
		logger.info("Iniciando: Transformacion de Trama.");
	}

	public String procesarTramaTCP(String trama, Socket socket){
		String respuesta = "";
		if(socket != null){
			respuesta = procesaTrama(trama);
		}
		return respuesta;
	}

	public String procesaTrama(String trama) {	
		String respuesta = null;
		try {
			if (trama == null || !trama.contains(",")) {
				
				respuesta = "1,E0,La trama no llega en el formato indicado";
				throw new Exception("La trama no llega en el formato indicado");
			}
			else{
				
				if (trama.startsWith(",")) {
					trama=trama.substring(1);				
				}
				
				String[] objSpl = trama.split(",");
				String CodigoTrama=objSpl[0];
				logger.info("Se buscara Comando codigo " + CodigoTrama);

				LogsManager.getInstance().info( "1. Antes de obtener comando. id hilo actual[" + Thread.currentThread().getId() + "]" );
				Action action = CtrlComandos.getINSTANCIA().execComando(CodigoTrama);
				
				if(action == null){
					logger.log(Level.SEVERE,"No se encontro el comando con el codigo de trama : "+CodigoTrama);
					throw new Exception("No se encontro el comando con el codigo de trama : "+CodigoTrama);
				}
				
				LogsManager.getInstance().info( "1. Despues de obtener comando. id hilo actual[" + Thread.currentThread().getId() + "]" );

				LogsManager.getInstance().info("2. Antes de ejecutar comandos. id hilo actual [" + Thread.currentThread().getId() + "]");
				
				LogsManager.getInstance().info("Se empieza a ejecutar el comando: "+ action.getNombreAction());
				LogsManager.getInstance().info("Entra a "+CodigoTrama);
				
				Object respuestaObj = action.execute(trama);
				LogsManager.getInstance().info(" 2. Despues de ejecutar comando.");

				LogsManager.getInstance().info(" 3. Antes de evaluar Trama.");
				respuesta = String.valueOf(respuestaObj);
				LogsManager.getInstance().info(" 3. Despues de evaluar Trama.");

				LogsManager.getInstance().info(" 4. Antes de responder");				
			}
		}catch (Exception e) {	
			
			//Si el mensaje de error contiene el id de la trama es por que el el error fue en las validaciones creadas en la logica, de lo contrario es un error del sistema
			if(e.getMessage()!=null && (e.getMessage().contains("99") || e.getMessage().contains("32"))){//el 99 es con el que inician todas las tramas 990 9990 991 9991
				respuesta = e.getMessage();				
			}else{
				e.printStackTrace();
				respuesta = "1,E0,Se presento un problema inesperado en Codesa consultar con el administrador del sistema";				
			}
			
			//logger.log(Level.SEVERE, "id hilo actual [" + Thread.currentThread().getId() + "] Traza Error " + General.obtenerTraza(e));
		}
		
		logger.info("=> FINALIZA PROCESO: "+Constantes.NOMBRE_OPERADOR+": hilo actual "+Thread.currentThread().getId());
    	logger.info("=> RESPUESTA: <<<< "+respuesta+" >>>> - hilo actual: "+Thread.currentThread().getId());
		return respuesta;
	}

}
