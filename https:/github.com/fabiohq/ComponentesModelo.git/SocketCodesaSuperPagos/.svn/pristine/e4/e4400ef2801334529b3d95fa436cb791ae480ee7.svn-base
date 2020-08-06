package co.com.codesa.socketcodesaoperadorexterno.socket;

import java.io.DataInputStream; 
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.comandos.CtrlComandos;
import co.com.codesa.socketcodesaoperadorexterno.dao.pool.ConnectionPool;
import co.com.codesa.socketcodesaoperadorexterno.dao.pool.hilo.HiloCambioDia;
import co.com.codesa.socketcodesaoperadorexterno.general.InformacionSession;
import co.com.codesa.socketcodesaoperadorexterno.general.LogsManager;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.Constantes;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.ControladorEtiquetas;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.General;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.WebGambleConstants;


public class ServerWS{

	private static long  cantidadThreads = 0;	
	private Logger logger = LogsManager.getInstance( );
	
	public  ServerWS(){

	}

	DataInputStream inTransac;
	ServerSocket servidor;
	Socket socket=null;
	

	public void subirServidor( int puerto ) throws Exception {
		logger.info( " Empieza Validacion de conexion con base de datos ... ");
		try {

			conectarBaseDatos();
			
		} catch (Exception e) {
			logger.severe("Error: No se logro conectar a la base de datos: "+e.getMessage()); 
			throw new Exception(ControladorEtiquetas.sistemaMsg.getString("error_credenciales_bd"));
		}
		
		try{

			servidor = new ServerSocket( puerto, 1000 );  
			logger.log( Level.INFO,General.obtenerFechaHoraActual( true ) + "==>Se subio el servicio por el puerto: " + puerto + " cola: 1000" );

			logger.info( "  Memoria en el JVM: " + Runtime.getRuntime( ).totalMemory( ) + " " +
					"   Memoria libre: " + Runtime.getRuntime( ).freeMemory( )  + " " +
					" Memoria maxima en el JVM: " + Runtime.getRuntime( ).maxMemory( ) );

			logger.info( " Cargando en memoria los comandos ... ");
			CtrlComandos.getINSTANCIA().iniciarComandos();
			logger.info( " Finalizado proceso de carga de comandos en memoria... ");
			
			String password = InformacionSession.getInstance().getPasswordBd();
            
	        password = General.encriptar(password);
	        General.modificarValorXML( "", Constantes.Propiedades.PATH_CONFIGSERVER, "usuario1", password);
				
			logger.info( " Cargando parametros de conexion en memoria ... ");
			
			WebGambleConstants.getInstancia().setTimeRecarga(General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_TIMERECARGA ));
			WebGambleConstants.getInstancia().setTimeoutbd(General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_TIMEOUTBD ));

			//String timeOut = ControladorEtiquetas.conexion.getString(Constantes.LLAVE_TIME_OUT);
			String indicativo = ControladorEtiquetas.conexion.getString(Constantes.INDICATIVO);
			
			//Parametros configurados en archivo de propiedades
			logger.info("Configuracion "+Constantes.NOMBRE_OPERADOR+". --INDICATIVO: "+indicativo);
			System.out.println("Configuracion Operador Externo al "+Constantes.NOMBRE_OPERADOR+". --INDICATIVO: "+indicativo);

			
			WebGambleConstants.getInstancia().setIndicativo(indicativo);
			
			logger.info(" Timeout database "+WebGambleConstants.getInstancia().getTimeoutbd()
					+" Time recarga "+WebGambleConstants.getInstancia().getTimeRecarga());
			
			WebGambleConstants.getInstancia().setTimeRecargaOperador(General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_PARAMRECARGA ));
			logger.info("Parametro de validacion de envio de recarga al operador: "+WebGambleConstants.getInstancia().getTimeRecargaOperador());
				
			logger.info( " Se inicia el hilo Cambio dia ...");
	        HiloCambioDia hiloCDia = new HiloCambioDia();
	       
	        hiloCDia.start();
	        logger.info( " Se inicia hilo conectividad con operador externo ...");
	      
			while( true ) {
				try{
					if( ( cantidadThreads%500 ) == 0 && cantidadThreads > 0 ) {
						logger.info( "  Memoria en el JVM: " + Runtime.getRuntime( ).totalMemory( ) + " " +
								"            Memoria libre: " + Runtime.getRuntime( ).freeMemory( )  + " " +
								" Memoria maxima en el JVM: " + Runtime.getRuntime( ).maxMemory( ) );

					}

					//Se valida que el componente clienteVirginMovileRest se encuentre entre las dependencias del socketCodesaVirgin y sea  reconocido ya que es un componente externo
				//	logger.info(Utilidades.VALIDA_CLIENTE);
					logger.info( "*****Socket preparado para recibir peticiones por la ip: " + InetAddress.getLocalHost().getHostAddress()+" puerto: "+puerto+"*****");
					Socket miSocket = servidor.accept( );

					ThreadServidor servidor= new ThreadServidor( miSocket );
					new Thread( servidor, "Numero " + cantidadThreads ).start( );

					miSocket = null;
					logger.log( Level.INFO, "catidad de sockets que han hecho llamadas al servidor= " + ( cantidadThreads++ ) );

				}
				catch(Exception e){
					logger.severe(e.getMessage());
					General.registra_error_log_archivo(General.obtenerFechaHoraActual( true ) + "==>Traza error (interno): "+ General.obtenerTraza(e));
					e.printStackTrace();
				}
			}
		}
		catch(Exception e){
			logger.severe(e.getMessage());
			General.registra_error_log_archivo(General.obtenerFechaHoraActual( true ) + "==>Traza error (interno): "+ General.obtenerTraza(e));
			throw new Exception(ControladorEtiquetas.sistemaMsg.getString("error_inicio"));
		}
	}
	
	
	private void conectarBaseDatos() throws Exception{
		
		String driver = General.retornarValorXML(Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_DRIVER);
		
		String bd = InformacionSession.getInstance().getBd();
		String ip = InformacionSession.getInstance().getIpBd();
		String listener = InformacionSession.getInstance().getListenerBd();
	    String url = "jdbc:oracle:thin:@"+ip+":"+listener+":"+bd;
	    String password = InformacionSession.getInstance().getPasswordBd();
	    String usuario = InformacionSession.getInstance().getUsuarioBd();
	
	    ConnectionPool pool = null;
		
	    logger.info("id[" + Thread.currentThread().getId() + "] Estableciendo conexion a base de datos");    
		logger.info("id[" + Thread.currentThread().getId() + "] Antes de conexion a DB");
		            
		pool = ConnectionPool.getInstance();

		logger.info("1. El pool esta inicializado? "+pool.estaInit());
		            
		if (!pool.estaInit()){
			pool.init(driver, url, usuario, password);
		}
		    
		logger.info("id[" + Thread.currentThread().getId() + "] Despues de conexion a DB");
		logger.info("2. El pool esta inicializado? "+pool.estaInit());
	}
}




