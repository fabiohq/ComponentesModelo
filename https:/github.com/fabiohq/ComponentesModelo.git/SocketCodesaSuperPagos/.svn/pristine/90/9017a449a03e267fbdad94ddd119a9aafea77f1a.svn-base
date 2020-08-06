package co.com.codesa.socketcodesaoperadorexterno.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.bean.ConfigConexionTrama;
import co.com.codesa.socketcodesaoperadorexterno.bean.DatosBaseBean;
import co.com.codesa.socketcodesaoperadorexterno.bean.ServicioBean;
import co.com.codesa.socketcodesaoperadorexterno.dao.pool.ConnectionPool;
import co.com.codesa.socketcodesaoperadorexterno.dao.pool.objConexion;
import co.com.codesa.socketcodesaoperadorexterno.interfaces.IValidadorOtrosServicios;
import co.com.codesa.socketcodesaoperadorexterno.manager.LogsManagerOperadorExterno;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.Constantes;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.General;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.WebGambleConstants;

/**
 * @author Fabio Hernandez
 * @since  22/08/2017
 * @descripcion Clase para realizar las respectivas validaciones de productos virtuales
 * @copyright: Copyright 1998-2016 Codesa, Todos los derechos reservados.
 */
public class ValidadorOtrosServicios implements IValidadorOtrosServicios, Serializable{

	private static final long serialVersionUID = 1L;
	private Logger logger = LogsManagerOperadorExterno.getInstance();

	/**
	 * @descripcion:Metodo que realiza todo el proceso de validacion de recargas en Codesa
	 * 				como lo es consulta de conexion externa, consulta datos del vendendor y 
	 * 				genera trama de recarga movil para el operador movil
	 * 
	 * Internamente invoca al procedimiento almacenado validaparametrosotrosserv
	 * El objetivo principal de este metodo es obtener los parametros de conexion con el socket del opeador movil
	 * se invoca el procedimiento almacenado GAMBLEDBV10_OTROSSERVICIOS.validaparametrosotrosserv 
	 * o PKGN_BUSINESSNETOTROSSERVICIOS.validaparametrosotrosserv si es TAT
	 * ,Este procedimiento internamente realiza las siguientes validaciones:
	 * 			paso1: Valida el punto de venta
	 * 			paso2: Valida el contrato de la persona
	 * 			paso3: Valida si hay horario activo
	 * 			paso4: Obtiene el tipo de clave que requiere
	 * 			paso5: Valida la comision del centro de costo y el vendedor
	 * 			paso6: Valida que no tenga mas de una comision activa
	 * 			paso7: Valida la comision del usuario
	 * 
	 * Tambien invoca a generatramarm Funcion que recibe la trama que envia el server socket para retornar 
	 * por medio de procedimiento (generatramarm) la configuracion del server
	 * socket al cual se va a conectar para para enviar la trama
	 */
	 
	@Override
	public ConfigConexionTrama generaTramaPaquetes( ConfigConexionTrama configConex ) throws Exception{
		
		long hiloActual = Thread.currentThread().getId();
		String nombrePrdbBd = null; 
		String validTAT = configConex.getValidaTAT();
		String login = configConex.getV_loginUser();
		Connection c = null;
		ConnectionPool pool = null;
        objConexion objcon = null;
		CallableStatement cstmt1 = null;

		//parametros de configuracion
		boolean isError = false;
		String smsError = null;
		String operacion = null;
		
		if(validTAT==null ||validTAT.equalsIgnoreCase(Constantes.VALOR_N)){			
			nombrePrdbBd = "PKGN_BNET_SUPERPAGOS_PAQUETES.prdb_generatrama".toUpperCase();
			operacion = Constantes.TRAMA_ID_NORMAL;
		}else{			
			nombrePrdbBd = "PKGN_BNET_SUPERPAGOS_PAQUETES.prdb_generatramatat".toUpperCase();
			operacion = Constantes.TRAMA_ID_TAT;
		}
		
		logger.info("==> INICIA CONSULTA A PROCEDIMIENTO "+nombrePrdbBd+" de ["+ValidadorOtrosServicios.class.getCanonicalName()+"] - Usuario: "+login+" Hilo actual: "+hiloActual+"\nTRAMA ENVIADA "+nombrePrdbBd+" - "+configConex.getV_trama());		
		
		int timeoutbd = Integer.parseInt(WebGambleConstants.getInstancia().getTimeoutbd());
		
		//logger.info("Time out de base de datos:"+timeoutbd+" - Usuario: "+login+" Hilo actual: "+hiloActual);

		configConex.setV_resultado("E0");
	
		try {

			if(isError){
				logger.info("==> " +configConex.getV_resultado()+" Usuario: "+login+" Hilo actual: "+hiloActual  );				
				smsError = String.valueOf(operacion) + ",1," + login + ","+ configConex.getV_resultado();				
				throw new Exception(smsError);
				
			}//Fin validaciones de conexion
						
			pool= ConnectionPool.getInstance();
			objcon = pool.getConnection();
//			TODO: probar pool.getConnection().getConnection();
	        c = objcon.getConnection();

			if (validTAT==null ||validTAT.equalsIgnoreCase(Constantes.VALOR_N)){
				
				logger.info(  "VENDEDOR NORMAL - USUARIO : " + login  );
				cstmt1 = c.prepareCall( "{ CALL PKGN_BNET_SUPERPAGOS_PAQUETES.prdb_generatrama(?,?,?,?,?,?)}");
				
			}else {
				
				logger.info(  "VENDEDOR TAT - USUARIO : " + login  );
				cstmt1 = c.prepareCall( "{ CALL PKGN_BNET_SUPERPAGOS_PAQUETES.prdb_generatramatat(?,?,?,?,?,?)}");
			}			

			// Se pasa al procedimento la trama
			cstmt1.setString(1,configConex.getV_trama());  //pca_trama			
			
			//Parametros de Salida
			cstmt1.registerOutParameter( 2, Types.VARCHAR );//credencial1
			cstmt1.registerOutParameter( 3, Types.VARCHAR );//Credencial2
			cstmt1.registerOutParameter( 4, Types.VARCHAR );//Id de transaccion codesa
			cstmt1.registerOutParameter( 5, Types.VARCHAR );//pca_tramaOUT
			cstmt1.registerOutParameter( 6, Types.VARCHAR );//pca_resultado

			cstmt1.setQueryTimeout(timeoutbd);			
			// Ejecuta procedimiento
			logger.info("==> TIEMPOS ANTES DE LLAMADO A PROCEDIMIENTO "+nombrePrdbBd+": "+General.obtenerFechaHoraActual( true )+" - Usuario: "+login+" Hilo actual: "+hiloActual);
			cstmt1.execute( );   
			logger.info("==> TIEMPOS DESPUES DE LLAMADO A PROCEDIMIENTO "+nombrePrdbBd+": "+General.obtenerFechaHoraActual( true )+" - Usuario: "+login+" Hilo actual: "+hiloActual);				
			//Valida la respuesta de base de datos
			configConex.setV_resultado(cstmt1.getString(6));
			
			if(configConex.getV_resultado() != null){
				logger.info("==> " +configConex.getV_resultado()+" Usuario: "+login+" Hilo actual: "+hiloActual  );
				smsError = operacion+",1," + login + ","+ configConex.getV_resultado();				
				//lanza la respuesta en caso de error
				throw new Exception(smsError);
			}
			
			configConex.setLoginOperador(cstmt1.getString(2));
			configConex.setClaveOperador(cstmt1.getString(3));
			configConex.setV_numtransaccion(cstmt1.getString(4));
			configConex.setV_trama_operador(cstmt1.getString(5));

			this.logger.info("==> RESPUESTA PROCEDIMIENTO "+nombrePrdbBd+" - Usuario: "+login+" Hilo actual: "+hiloActual+"\n"+configConex.getV_trama_operador());

		}catch( Exception e ){
							
			logger.log(Level.SEVERE,  login + ": Llamando, Operacion: [ "+nombrePrdbBd+" ] Error es: " + e.toString( )+" - Usuario: "+login+" Hilo actual: "+hiloActual);
			
			//Si smsError es diferente de nulo es por que la excepcion se presento en las validaciones realizadas a nivel de paquete
			if(smsError != null){
				throw new Exception(e.getMessage());
			}
			configConex.clean();
			e.printStackTrace();
			throw new Exception(String.valueOf(operacion) + ",1," + login+ "," + configConex.getV_resultado());

		}finally{ 		
			if(pool != null || objcon != null || cstmt1 != null){				
				ConnectionPool.cerrarConexion(pool, objcon, cstmt1);
			}			
		}		
		return configConex;
	}
	@Override
	public ConfigConexionTrama generaTramaRecargas( ConfigConexionTrama configConex ) throws Exception{
		
		long hiloActual = Thread.currentThread().getId();
		String nombrePrdbBd = null; 
		String validTAT = configConex.getValidaTAT();
		String login = configConex.getV_loginUser();
		Connection c = null;
		ConnectionPool pool = null;
        objConexion objcon = null;
		CallableStatement cstmt1 = null;

		//parametros de configuracion
		boolean isError = false;
		String smsError = null;
		String operacion = null;
		
		if(validTAT==null ||validTAT.equalsIgnoreCase(Constantes.VALOR_N)){			
			nombrePrdbBd = "PKGN_BNET_SUPERPAGOS_RECARGAS.prdb_generatrama".toUpperCase();
			operacion = Constantes.TRAMA_ID_NORMAL_RECARGA;
		}else{			
			nombrePrdbBd = "PKGN_BNET_SUPERPAGOS_RECARGAS.prdb_generatramatat".toUpperCase();
			operacion = Constantes.TRAMA_ID_TAT_RECRAGA;
		}
		
		logger.info("==> INICIA CONSULTA A PROCEDIMIENTO "+nombrePrdbBd+" de ["+ValidadorOtrosServicios.class.getCanonicalName()+"] - Usuario: "+login+" Hilo actual: "+hiloActual+"\nTRAMA ENVIADA "+nombrePrdbBd+" - "+configConex.getV_trama());
		
		int timeoutbd = Integer.parseInt(WebGambleConstants.getInstancia().getTimeoutbd());
		
		//logger.info("Time out de base de datos:"+timeoutbd+" - Usuario: "+login+" Hilo actual: "+hiloActual);

		configConex.setV_resultado("E0");
	
		try {

			if(isError){
				logger.info("==> " +configConex.getV_resultado()+" Usuario: "+login+" Hilo actual: "+hiloActual  );				
				smsError = String.valueOf(operacion) + ",1," + login + ","+ configConex.getV_resultado();				
				throw new Exception(smsError);
				
			}//Fin validaciones de conexion
						
			pool= ConnectionPool.getInstance();
			objcon = pool.getConnection();
//			TODO: probar pool.getConnection().getConnection();
	        c = objcon.getConnection();

			if (validTAT==null ||validTAT.equalsIgnoreCase(Constantes.VALOR_N)){
				
				logger.info(  "VENDEDOR NORMAL - USUARIO : " + login  );
				cstmt1 = c.prepareCall( "{ CALL PKGN_BNET_SUPERPAGOS_RECARGAS.prdb_generatrama(?,?,?,?,?,?)}");
				
			}else {
				
				logger.info(  "VENDEDOR TAT - USUARIO : " + login  );
				cstmt1 = c.prepareCall( "{ CALL PKGN_BNET_SUPERPAGOS_RECARGAS.prdb_generatramatat(?,?,?,?,?,?)}");
			}			

			// Se pasa al procedimento la trama
			cstmt1.setString(1,configConex.getV_trama());  //pca_trama			
			
			//Parametros de Salida
			cstmt1.registerOutParameter( 2, Types.VARCHAR );//credencial1
			cstmt1.registerOutParameter( 3, Types.VARCHAR );//Credencial2
			cstmt1.registerOutParameter( 4, Types.VARCHAR );//Id de transaccion codesa
			cstmt1.registerOutParameter( 5, Types.VARCHAR );
			cstmt1.registerOutParameter( 6, Types.VARCHAR );//pca_resultado

			cstmt1.setQueryTimeout(timeoutbd);			
			// Ejecuta procedimiento
			logger.info("==> TIEMPOS ANTES DE LLAMADO A PROCEDIMIENTO "+nombrePrdbBd+": "+General.obtenerFechaHoraActual( true )+" - Usuario: "+login+" Hilo actual: "+hiloActual);			
			cstmt1.execute( );   
			logger.info("==> TIEMPOS DESPUES DE LLAMADO A PROCEDIMIENTO "+nombrePrdbBd+": "+General.obtenerFechaHoraActual( true )+" - Usuario: "+login+" Hilo actual: "+hiloActual);				
			//Valida la respuesta de base de datos
			configConex.setV_resultado(cstmt1.getString(6));
			
			if(configConex.getV_resultado() != null){
				logger.info("==> " +configConex.getV_resultado()+" Usuario: "+login+" Hilo actual: "+hiloActual  );
				smsError = operacion+",1," + login + ","+ configConex.getV_resultado();				
				//lanza la respuesta en caso de error
				throw new Exception(smsError);
			}
			
			configConex.setLoginOperador(cstmt1.getString(2));
			configConex.setClaveOperador(cstmt1.getString(3));
			configConex.setV_numtransaccion(cstmt1.getString(4));
			configConex.setV_trama_operador(cstmt1.getString(5));

			this.logger.info("==> RESPUESTA PROCEDIMIENTO "+nombrePrdbBd+" - Usuario: "+login+" Hilo actual: "+hiloActual+"\n PARAMETROS DEL OPERADOR: "+configConex.getV_trama_operador());
		}catch( Exception e ){
							
			logger.log(Level.SEVERE,  login + ": Llamando, Operacion: [ "+nombrePrdbBd+" ] Error es: " + e.toString( )+" - Usuario: "+login+" Hilo actual: "+hiloActual);
			
			//Si smsError es diferente de nulo es por que la excepcion se presento en las validaciones realizadas a nivel de paquete
			if(smsError != null){
				throw new Exception(e.getMessage());
			}
			configConex.clean();
			e.printStackTrace();
			throw new Exception(String.valueOf(operacion) + ",1," + login+ "," + configConex.getV_resultado());

		}finally{ 		
			if(pool != null || objcon != null || cstmt1 != null){				
				ConnectionPool.cerrarConexion(pool, objcon, cstmt1);
			}			
		}		
		return configConex;
	}
	/*
	 * Funcion que recibe la trama del cliente y de acuerdo a esta retorna la configuracion 
	 * del server socket por medio de procedimiento gambledbv10_otrosservicios.consultaconexionrm
	 */
	public ConfigConexionTrama consultaConexion( ConfigConexionTrama configConex ) {

		//String resultado = "";
		String login = configConex.getV_loginUser();
		Connection c = null;
		ConnectionPool pool = null;
		objConexion objcon = null;
		CallableStatement cstmt1 = null;
		Logger logger = LogsManagerOperadorExterno.getInstance( );
		int timeoutbd = Integer.parseInt(WebGambleConstants.getInstancia().getTimeoutbd());
		logger.info("Time out de base de datos:"+timeoutbd);

		// Trama de resultado Final
		//String tramaFinalSalida = "";

		configConex.setV_resultado("E0");

		try{
			
			logger.info(  "Intentando conexion con la base de datos - Usuario: " + login );
			pool = ConnectionPool.getInstance();
			objcon = pool.getConnection();  
			c=objcon.getConnection();
			logger.info(  "Conexion exitosa Intentando - Operacion: [ CONSULTARCONEXIONRM] - Usuario: " + login );
			
			/* Procedimiento que retorna como datos principales, el IP, PUERTO y time_out del Socket del Proveedor de Recarga Movil
               al que se va a Conectar y la trama que se le debe enviar
			 */
			String validTAT = configConex.getValidaTAT();

			if (validTAT==null ||validTAT.equalsIgnoreCase("N")){
				cstmt1 = c.prepareCall( "{ CALL gambledbv10_otrosservicios.consultaconexionrm(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}" );
			}else {
				cstmt1 = c.prepareCall( "{ CALL PKGN_BUSINESSNETOTROSSERVICIOS.consultaconexionrm(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}" );        
			}	      

			logger.info(  "Trama enviada consultaconexionrm "+configConex.getV_trama() );

			// Se pasa al procedimento la trama
			cstmt1.setString( 1, configConex.getV_trama() );  

			//Parametros de Salida
			cstmt1.registerOutParameter( 2, Types.VARCHAR );
			cstmt1.registerOutParameter( 3, Types.VARCHAR );
			cstmt1.registerOutParameter( 4, Types.VARCHAR );
			cstmt1.registerOutParameter( 5, Types.VARCHAR );
			cstmt1.registerOutParameter( 6, Types.VARCHAR );
			cstmt1.registerOutParameter( 7, Types.VARCHAR );
			cstmt1.registerOutParameter( 8, Types.VARCHAR );
			cstmt1.registerOutParameter( 9, Types.VARCHAR );
			cstmt1.registerOutParameter( 10, Types.VARCHAR );
			cstmt1.registerOutParameter( 11, Types.VARCHAR );
			cstmt1.registerOutParameter( 12, Types.VARCHAR );
			cstmt1.registerOutParameter( 13, Types.VARCHAR );
			cstmt1.registerOutParameter( 14, Types.VARCHAR );

			
			try
			{
				cstmt1.setQueryTimeout(timeoutbd);
				logger.info(  "VA A EJECUTAR: Operacion: [ CONSULTARCONEXIONRM ] PARAMETROS: " + login + " Parametro entrada trama: "+ configConex.getV_trama());
				// Ejecuta procedimiento
				logger.info("Tiempo antes de ejecutar el llamado a base de datos CONSULTARCONEXIONRM: "+General.obtenerFechaHoraActual( true ));
				cstmt1.execute( );   
				logger.info("Tiempo Despues de ejecutar el llamado a base de datos CONSULTARCONEXIONRM: "+General.obtenerFechaHoraActual( true ));

				configConex.setV_ip( cstmt1.getString( 2 ));
				configConex.setV_login( cstmt1.getString( 3 ));
				configConex.setV_clave( cstmt1.getString( 4 ) );
				configConex.setV_puerto( cstmt1.getString( 5 ) );
				configConex.setV_time_out( cstmt1.getString( 6 ) );                       
				configConex.setV_is_persistente( cstmt1.getString( 7 ) ); 
				configConex.setV_numero_reintento( cstmt1.getString( 8 ) ); 
				configConex.setV_tiempo_reintento( cstmt1.getString( 9 ) ); 
				configConex.setV_estado_reintento( cstmt1.getString( 10 ) ); 
				configConex.setV_posicion_reintento( cstmt1.getString( 11 ) ); 
				configConex.setV_separador_trama( cstmt1.getString( 12 ) ); 
				configConex.setV_num_reintento_general( cstmt1.getString( 13 ) ); 
				configConex.setV_resultado( cstmt1.getString( 14 ) ); 

				logger.info(  "EJECUTO: Operacion: [ CONSULTARCONEXIONRM ] PARAMETROS: " + login  +" Parametros Salida "+ configConex.getV_ip( )+"-"+
						configConex.getV_login( )+"-"+
						configConex.getV_clave(  )+"-"+
						configConex.getV_puerto(  )+"-"+
						configConex.getV_time_out()+"-"+  
						configConex.getV_is_persistente(  )+"-"+  
						configConex.getV_numero_reintento( )+"-"+   
						configConex.getV_tiempo_reintento( )+"-"+   
						configConex.getV_estado_reintento( )+"-"+  
						configConex.getV_posicion_reintento( )+"-"+  
						configConex.getV_separador_trama(  )+"-"+  
						configConex.getV_resultado(  ) );



			}catch( Exception e ){

				logger.log( Level.SEVERE,  login + ": Llamando, Operacion: [ CONSULTARCONEXIONRM ] Error es: " + e.toString( ) );
			}

		}catch( Exception e ){

			logger.log( Level.SEVERE,  login + ": Llamando, Operacion: [ CONSULTARCONEXIONRM ] Error es: " + e.toString( ) );             

		}finally{

			ConnectionPool.cerrarConexion(pool, objcon, cstmt1);
		}

		return configConex;

	}
	


	@Override
	public DatosBaseBean consultaValorFactura(ServicioBean pxsb) {
		 String strValor = "";
	        String strValorM = "";
	        String resultado = "";

	        Connection c = null;
	        ConnectionPool pool = null;
	        objConexion objcon = null;
	        CallableStatement cstmt1 = null;
	        Logger logger = LogsManagerOperadorExterno.getInstance( );

	        ServicioBean pxsbr = pxsb;

	        pxsbr.setResultado( "E0" );

	        try{
	        	
	        	logger.info( General.obtenerFechaHoraActual( true ) + " " + pxsbr.getLogin() + "==>Consultando productos para el proveedor.... " + pxsbr.getNitProveedor( ) );
        
	        	pool= ConnectionPool.getInstance();
				objcon = pool.getConnection();
	            c = objcon.getConnection();
	            
	            logger.info( General.obtenerFechaHoraActual( true ) + " " + pxsbr.getLogin() + "==>Model.GAMBLE: va a ejecutar gambledbv10_otrosservicios.consultavalorfacturaDBJ" );
	            logger.info( General.obtenerFechaHoraActual( true ) + " " + pxsbr.getLogin() + "==>Model.GAMBLE: nitEmpresa: "+ pxsbr.getNitEmp( ) + " proveedor: " + pxsbr.getNitProveedor( ) );
	            
	            cstmt1 = c.prepareCall( "{ CALL gambledbv10_otrosservicios.consultavalorfacturaDBJ(?,?,?,?,?,?,?)}" );
	            cstmt1.setString( 1, pxsbr.getNitProveedor( ) );
	            cstmt1.setString( 2, pxsbr.getCodServicio( )  );
	            cstmt1.setString( 3, pxsbr.getCodProducto( ) );
	            cstmt1.setString( 4, pxsbr.getSerial( ) );
	            cstmt1.registerOutParameter( 5, Types.VARCHAR );
	            cstmt1.registerOutParameter( 6, Types.VARCHAR );
	            cstmt1.registerOutParameter( 7, Types.VARCHAR );                             //Resultado

	            try{
	                cstmt1.execute( );

	                strValor = cstmt1.getString( 5 );
	                strValorM = cstmt1.getString( 6 );
	                resultado = cstmt1.getString( 7 );

	                if( resultado == null ) {
	                    pxsbr.setEstado( true );
	                    pxsbr.setValor( strValor );
	                    pxsbr.setValorMin( strValorM );

	                }else{

	                    pxsbr.setEstado( false );
	                    pxsbr.setResultado( resultado );
	                }

	            }catch( Exception e ){

	                logger.log( Level.SEVERE, General.obtenerFechaHoraActual( true ) + " " + pxsbr.getLogin() + "==>Model.GAMBLE: NO es posible llamar a la funcion gambledbv10_otrosservicios.consultavalorfacturaDBJ, Error " + e.toString( ) );

	            }

	            logger.info( pxsbr.getLogin() + "==>Model.GAMBLE: se ejecuto el procedimiento gambledbv10_otrosservicios.consultavalorfacturaDBJ, y el resultado es:" +
	                    resultado + " Valor " + strValor + " ValorMinimo: " + strValorM );

	        }catch( Exception e ){

	            logger.log( Level.SEVERE, General.obtenerFechaHoraActual( true ) + " " + pxsbr.getLogin() + "==>error en la clase Usuario, gambledbv10_otrosservicios.consultavalorfacturaDBJ, Mensaje:" + e.toString( ) );

	        }finally{
	       
	            ConnectionPool.cerrarConexion(pool, objcon, cstmt1);
	        }

	        return pxsbr;

	    }

}
