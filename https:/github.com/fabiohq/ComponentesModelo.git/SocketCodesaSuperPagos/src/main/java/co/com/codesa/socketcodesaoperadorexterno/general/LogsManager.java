/*
 * IpManager.java
 *
 * Created on 3 de julio de 2004, 10:05 PM
 */

package co.com.codesa.socketcodesaoperadorexterno.general;

import java.net.URLDecoder;
import java.util.Hashtable;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.utilidades.Constantes;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.ControladorEtiquetas;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.General;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.WebGambleConstants;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.XML;


public class LogsManager {

	private static Logger logger = null;
	private static Hashtable<String, Logger> hLoggers=new Hashtable<String, Logger>();

	/** Creates a new instance of IpManager */
	public LogsManager() {

	}
	
	public static Logger getLogger() {
		return logger;
	}

	public static Logger getInstance(){
		logger = (Logger)hLoggers.get(General.obtenerFechaHoraActual(false));
		
		if (logger == null){
			try{
				logger = Logger.getLogger(LogsManager.class.getSimpleName());

				String pathPropertiesLog =  Constantes.Propiedades.PATH_LOG_PROPERTIES;
				LogManager.getLogManager().readConfiguration(ClassLoader.getSystemResourceAsStream(pathPropertiesLog));
				String pathConfigServer = Constantes.Propiedades.PATH_CONFIGSERVER;
				String url =  XML.leerValorXML("",pathConfigServer, Constantes.Propiedades.NODO_CONFIGSERVER_PATH_LOG);
				String path = URLDecoder.decode(url,"UTF-8");
				
				WebGambleConstants.getInstancia().setPathLogs(path);
				logger.addHandler(new java.util.logging.FileHandler(path+General.obtenerFechaHoraActual(false)+".log",true));
				hLoggers.put(General.obtenerFechaHoraActual(false), logger);
			}
			catch(Exception e){
				System.out.println(ControladorEtiquetas.sistemaMsg.getString("error_log")+e.toString());
			}
			logger.info(Constantes.Etiquetas.VERSION_GENERAL_LOG+" : "+Constantes.Etiquetas.VERSION_GENERAL);
		 }

		return logger;
	}
}

