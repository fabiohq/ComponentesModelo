package co.com.codesa.socketcodesaoperadorexterno.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.bean.ConfigConexionTrama;
import co.com.codesa.socketcodesaoperadorexterno.bean.ResultadoBean;
import co.com.codesa.socketcodesaoperadorexterno.dao.pool.ConnectionPool;
import co.com.codesa.socketcodesaoperadorexterno.dao.pool.objConexion;
import co.com.codesa.socketcodesaoperadorexterno.interfaces.IOperadorExternoDAO;
import co.com.codesa.socketcodesaoperadorexterno.manager.LogsManagerOperadorExterno;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.ConexionDebug;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.Constantes;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.ControladorMensajes;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.General;

/**
 * Clase encargada de realizar la logica de acceso a bases de datos
 * para realizar las operaciones de recarga de paquete 
 * @author Cristian Gomez Ruiza
 * @since 31/05/2016
 */
public class OperadorExternoDAO implements IOperadorExternoDAO, Serializable{

	private static final long serialVersionUID = 1L;
	
	//transferMovilRegistraRecarga
	public ConfigConexionTrama transferMovilRegistraRespuesta(ConfigConexionTrama configConex) throws Exception{

		long hiloActual = Thread.currentThread().getId();
		String login = configConex.getV_loginUser();
		// Objetos para la conexion a la BD
		Connection c = null;
		ConnectionPool pool = null;
		objConexion objCon = null;
		CallableStatement cstmt1 = null;
		Logger logger = LogsManagerOperadorExterno.getInstance( );
		String operacion = null;
		if (configConex.getValidaTAT().equalsIgnoreCase(Constantes.VALOR_N)) {
			operacion = "PKGN_BNET_SUPERPAGOS_PAQUETES.prdb_registrarventa";
		}else{
			operacion = "PKGN_BNET_SUPERPAGOS_PAQUETES.prdb_registrarventatat";
		}
		configConex.setV_resultado( "E0" );  

		logger.info("\n==> INICIA PROCESO ["+operacion+"] PARAMETROS"
				+"\n=> TRAMA CODESA: "+configConex.getV_trama()
				+"\n=> TRAMA OPERADOR: "+configConex.getV_trama_operador()
				+"\n=> ID TRANSACCION: "+configConex.getV_numtransaccion()
				+"\n=> HILO ACTUAL: "+hiloActual+"-"+login
				+"\n");
		
        try{
 
			pool = ConnectionPool.getInstance( );
			objCon = pool.getConnection();
			c = objCon.getConnection();    

			if (!configConex.getValidaTAT().equalsIgnoreCase(Constantes.VALOR_S)) {
				// Procedimiento que permite registrar la respuesta y obtener la trama de salida y un resultado
				// Algunos parametros de salida del procedimiento ejecutado anteriormente, se usan para la entrada de este.
				cstmt1 = c.prepareCall( "{ CALL PKGN_BNET_SUPERPAGOS_PAQUETES.prdb_registrarventa(?,?,?,?,?)}" );				

			}else{
				// Procedimiento que permite registrar la respuesta y obtener la trama de salida y un resultado
				// Algunos parametros de salida del procedimiento ejecutado anteriormente, se usan para la entrada de este procedimiento
				
				//ConexionDebug cd = new ConexionDebug();
				//cd.debug(c, "10.11.6.52", "4000");
				cstmt1 = c.prepareCall( "{ CALL PKGN_BNET_SUPERPAGOS_PAQUETES.prdb_registrarventatat(?,?,?,?,?)}" );				
			}
						
			// Se pasa al procedimento (registrarespuestarm) los parametros que retorno el procedimiento anterior (gambledbv10_otrosservicios.generatramarm)
			cstmt1.setString( 1, configConex.getV_trama());
			cstmt1.setString( 2, configConex.getV_trama_operador());						
			cstmt1.setString( 3, configConex.getV_numtransaccion()); 

			// Parametros de Salida
			cstmt1.registerOutParameter( 4, Types.VARCHAR );//TRAMA OUT
			cstmt1.registerOutParameter( 5, Types.VARCHAR );//RESULTADO
			
			/*if (configConex.getValidaTAT().equalsIgnoreCase(Constantes.VALOR_S)) {				
				cstmt1.setString( 9, configConex.getNumidentificador());//pca_numid
				cstmt1.setString( 10, configConex.getDocVendedor());//pca_docuvendedor
			}*/
			
			logger.info( "VA A EJECUTAR OPERACION: [ REGISTRO DE VENTA ]  "+Constantes.NOMBRE_OPERADOR+". PARAMETROS: " + login + "PARAMETROS DE ENTRADA: Trama "+ configConex.getV_trama()+","+configConex.getV_numtransaccion()+","+configConex.getV_tipotrama()+","+configConex.getV_trama_operador()+" - Usuario : " + login + " hilo actual "+hiloActual);
			
			cstmt1.execute( );
			
			//Obtengo los valores de los parametros de salida
			configConex.setV_trama(cstmt1.getString(4));
			configConex.setV_resultado( cstmt1.getString(5)); // Resultado
			
			logger.info("\n==> SE EJECUTO: PROCESO: ["+operacion+"] PARAMETROS"
					+"\n=> ID TRANSACCION CODESA: "+configConex.getV_numtransaccion() 
					+"\n=> USUARIO:" + login 
					+"\n=> TRAMA: "+ configConex.getV_trama()
					+"\n=> RESULTADO: "+ configConex.getV_resultado()
					+"\n=> HILO ACTUAL: "+hiloActual
					+"\n");
			
			// Genera log en caso de que el procedimiento no retorne trama de respuesta al cliente
			if( configConex.getV_trama() ==  null || configConex.getV_trama().equals("")){
				logger.info("usuario: "+login +" NO HAY TRAMA RESPUESTA PARA TRANSFER DEL WEB SERVICE"+" - Usuario : " + login + " hilo actual "+hiloActual);
			}
			
        }catch( Exception e ){
			logger.log( Level.SEVERE,  login + ":ERROR "+operacion+" : " + e.toString( ) + " hilo actual "+Thread.currentThread().getId()+"-"+login);
		}
		
        finally{
			ConnectionPool.cerrarConexion(pool,objCon, cstmt1);
		}
        
        logger.info(  "ID TRANSACCION CODESA: <<"+configConex.getV_numtransaccion()+">> - hilo actual: "+hiloActual+"-"+login);
		return configConex;
	}
	
	public ConfigConexionTrama transferMovilRegistraRecarga(ConfigConexionTrama configConex) throws Exception{

		long hiloActual = Thread.currentThread().getId();
		String login = configConex.getV_loginUser();
		// Objetos para la conexion a la BD
		Connection c = null;
		ConnectionPool pool = null;
		objConexion objCon = null;
		CallableStatement cstmt1 = null;
		Logger logger = LogsManagerOperadorExterno.getInstance( );
		String operacion = null;
		if (configConex.getValidaTAT().equalsIgnoreCase(Constantes.VALOR_N)) {
			operacion = "PKGN_BNET_SUPERPAGOS_RECARGAS.prdb_registrarventa";
		}else{
			operacion = "PKGN_BNET_SUPERPAGOS_RECARGAS.prdb_registrarventatat";
		}
		configConex.setV_resultado( "E0" );  

		logger.info("\n==> INICIA PROCESO ["+operacion+"] PARAMETROS"
				+"\n=> TRAMA CODESA: "+configConex.getV_trama()
				+"\n=> TRAMA OPERADOR: "+configConex.getV_trama_operador()
				+"\n=> ID TRANSACCION: "+configConex.getV_numtransaccion()
				+"\n=> HILO ACTUAL: "+hiloActual+"-"+login
				+"\n");
        try{
 
			pool = ConnectionPool.getInstance( );
			objCon = pool.getConnection();
			c = objCon.getConnection();    

			if (!configConex.getValidaTAT().equalsIgnoreCase(Constantes.VALOR_S)) {
				// Procedimiento que permite registrar la respuesta y obtener la trama de salida y un resultado
				// Algunos parametros de salida del procedimiento ejecutado anteriormente, se usan para la entrada de este.
				cstmt1 = c.prepareCall( "{ CALL PKGN_BNET_SUPERPAGOS_RECARGAS.prdb_registrarventa(?,?,?,?,?)}" );
			}else{
				// Procedimiento que permite registrar la respuesta y obtener la trama de salida y un resultado
				// Algunos parametros de salida del procedimiento ejecutado anteriormente, se usan para la entrada de este procedimiento
				
				//ConexionDebug cd = new ConexionDebug();
				//cd.debug(c, "10.11.6.52", "4000");
				cstmt1 = c.prepareCall( "{ CALL PKGN_BNET_SUPERPAGOS_RECARGAS.prdb_registrarventatat(?,?,?,?,?)}" );
			}
						
			// Se pasa al procedimento (registrarespuestarm) los parametros que retorno el procedimiento anterior (gambledbv10_otrosservicios.generatramarm)
			cstmt1.setString( 1, configConex.getV_trama());
			cstmt1.setString( 2, configConex.getV_trama_operador());						
			cstmt1.setString( 3, configConex.getV_numtransaccion()); 

			// Parametros de Salida
			cstmt1.registerOutParameter( 4, Types.VARCHAR );//TRAMA OUT
			cstmt1.registerOutParameter( 5, Types.VARCHAR );//RESULTADO

			cstmt1.execute( );
			
			//Obtengo los valores de los parametros de salida
			configConex.setV_trama(cstmt1.getString(4));
			configConex.setV_resultado( cstmt1.getString(5)); // Resultado
						
			logger.info("\n==> SE EJECUTO: PROCESO: ["+operacion+"] PARAMETROS"
					+"\n=> ID TRANSACCION CODESA: "+configConex.getV_numtransaccion() 
					+"\n=> USUARIO:" + login 
					+"\n=> TRAMA: "+ configConex.getV_trama()
					+"\n=> RESULTADO: "+ configConex.getV_resultado()
					+"\n=> HILO ACTUAL: "+hiloActual
					+"\n");
						
			// Genera log en caso de que el procedimiento no retorne trama de respuesta al cliente
			if( configConex.getV_trama() ==  null || configConex.getV_trama().equals("")){
				logger.info("usuario: "+login +" NO HAY TRAMA RESPUESTA PARA TRANSFER DEL WEB SERVICE"+" - Usuario : " + login + " hilo actual "+hiloActual);
			}
			
        }catch( Exception e ){
			logger.log( Level.SEVERE,  login + ":ERROR "+operacion+" : " + e.toString( ) + " hilo actual "+Thread.currentThread().getId()+"-"+login);
		}
		
        finally{
			ConnectionPool.cerrarConexion(pool,objCon, cstmt1);
		}
        
		return configConex;
	}
	
	public ConfigConexionTrama transferMovilGeneraTrama(ConfigConexionTrama configConex){

		String resultado = "";
		String login = configConex.getV_loginUser();
		Connection c = null;
		ConnectionPool pool = null;
		objConexion objCon = null;
		CallableStatement cstmt1 = null;
		Logger logger = LogsManagerOperadorExterno.getInstance( );

		// Trama de resultado Final
		String tramaFinalSalida = "";

		configConex.setV_resultado( "E0" );  
		try{
			logger.info( "Antes de conexion a DB - Operacion: [ GENERATRAMARM] - Usuario : " + login + " hilo actual "+Thread.currentThread().getId());

			pool = ConnectionPool.getInstance( );
			objCon = pool.getConnection();
			c = objCon.getConnection();         

			logger.info(  "Despues de conexion a DB - Operacion: [ GENERATRAMARM ] - Usuario : " + login + " hilo actual "+Thread.currentThread().getId());

			
			if (!configConex.getValidaTAT().equalsIgnoreCase("S")) {
				// Procedimiento que retorna como datos principales, el IP y PUERTO del Socket del Proveedor al que se va a Conectar y la trama que se le debe enviar
				cstmt1 = c.prepareCall( "{ CALL GAMBLEDBV10_SERVICIOSVIRGIN.prdb_generatrama_paqVirgin(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}" );

			}else {
				// Procedimiento que retorna como datos principales, el IP y PUERTO del Socket del Proveedor al que se va a Conectar y la trama que se le debe enviar
				cstmt1 = c.prepareCall( "{ CALL pkgn_businessnetotrosservicios.prdb_generatrama_paqVirgin(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}" );

				
			}
			
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
			cstmt1.registerOutParameter( 15, Types.VARCHAR );

			try{

				logger.info( "VA A EJECUTAR: Operacion: [ GENERATRAMARM ] PARAMETROS: " + login +" PARAMETRO ENTRADA "+ configConex.getV_trama()  + " hilo actual "+Thread.currentThread().getId());

				// Ejecuta procedimiento
				cstmt1.execute( );

				//Obtiene parametros de salida para ejecutar sgte procedimiento
				configConex.setV_ip( cstmt1.getString( 2 ));
				configConex.setV_login( cstmt1.getString( 3 ));
				configConex.setV_clave( cstmt1.getString( 4 ) );
				configConex.setV_puerto( cstmt1.getString( 5 ) );                
				configConex.setV_time_out( cstmt1.getString( 6 ) );                
				configConex.setV_nitproveedor( cstmt1.getString( 7 ) );
				configConex.setV_serviciocodigo( cstmt1.getString( 8 ) );  
				configConex.setV_otroproductocodigo( cstmt1.getString( 9 ));
				configConex.setV_numtransaccion( cstmt1.getString( 10 ) );
				configConex.setV_tipotrama( cstmt1.getString( 11 ) );       
				configConex.setV_trama_operador( cstmt1.getString( 12 ) );  
				configConex.setV_trama_reintento( cstmt1.getString( 13 ) );  
				configConex.setV_resultado( cstmt1.getString( 14 ) );
				configConex.setV_codOperador( cstmt1.getString( 15 ) );

				logger.info( "EJECUTO: Operacion: [ GENERATRAMARM ] PARAMETROS: " + login +" PARAMETROS DE SALIDA "+ configConex.getV_ip()+"-"+
						configConex.getV_resultado()+ " hilo actual "+Thread.currentThread().getId()+"-"+configConex.getV_codOperador());
				logger.info("trama recibida de generar trama:"+configConex.getV_trama_operador());

			}catch( Exception e ){

				logger.log( Level.SEVERE,  login + ": Llamando, Operacion: [ GENERATRAMARM ] Error es: " + e.toString( )+ " hilo actual "+Thread.currentThread().getId());
			}



		}catch( Exception e ){

			logger.log( Level.SEVERE,  login + ": Llamando, Operacion: [ GENERATRAMARM ] Error es: " + e.toString( ) + " hilo actual "+Thread.currentThread().getId());

		}finally{
			ConnectionPool.cerrarConexion( pool,objCon, cstmt1 );
		}

		return configConex;
	}

	
	public ConfigConexionTrama registraReintento( ConfigConexionTrama configConex ) {

		Connection c = null;
		ConnectionPool pool = null;
		CallableStatement cstmt1 = null;
		objConexion objCon = null;
		Logger logger = LogsManagerOperadorExterno.getInstance( );
		String login = configConex.getV_loginUser();

		configConex.setV_resultado( "E0" );  

		try{
			logger.info(  "Antes de conexion a DB - Operacion: [ REGISTRAREINTENTO ] - Usuario : " + login + " hilo actual "+Thread.currentThread().getId());

			pool = ConnectionPool.getInstance( );
			objCon = pool.getConnection();
			c = objCon.getConnection();

			logger.info( "Despues de conexion a DB - Operacion: [ REGISTRAREINTENTO ] - Usuario : " + login + " hilo actual "+Thread.currentThread().getId());

			// Procedimiento que retorna como datos principales, el IP y PUERTO del Socket del Proveedor al que se va a Conectar 
			//y la trama que se le debe enviar
			cstmt1 = c.prepareCall( "{ CALL gambledbv10_otrosservicios.registrareintento(?,?)}" );

			// Se pasa al procedimento el codigo de la transaccion, que esta en la primera posicion de la cadena tokenizada
			cstmt1.setString( 1, configConex.getV_numtransaccion() );           

			//Parametros de Salida (resultado)
			cstmt1.registerOutParameter( 2, Types.VARCHAR );           

			try{
				logger.info(  "VA A EJECUTAR: Operacion: [ REGISTRAREINTENTO ] PARAMETROS: " + login  +"PARAMETRO ENTRADA: "+ configConex.getV_numtransaccion()  + " hilo actual "+Thread.currentThread().getId());

				// Ejecuta procedimiento
				cstmt1.execute( );

				//Obtiene parametros de salida para ejecutar sgte procedimiento
				configConex.setV_resultado( cstmt1.getString( 2 ));    

				logger.info( "EJECUTO: Operacion: [ REGISTRAREINTENTO ] PARAMETROS: " + login +" PARAMETROS DE SALIDA "+configConex.getV_resultado() + " hilo actual "+Thread.currentThread().getId());

			}catch( Exception e ){
				logger.log( Level.SEVERE,  login + ": Llamando, Operacion: [ REGISTRAREINTENTO ] Error es: " + e.toString( ) + " hilo actual "+Thread.currentThread().getId());
			}

		}catch( Exception e ){
			logger.log( Level.SEVERE,  login + ": Llamando, Operacion: [ REGISTRAREINTENTO ] Error es: " + e.toString( ) + " hilo actual "+Thread.currentThread().getId());
		
		}finally{

			ConnectionPool.cerrarConexion( pool, objCon, cstmt1);
		}
		return configConex;
	}
	
	public ConfigConexionTrama registraIntentoFallido( ConfigConexionTrama configConex) {

		long hiloActual = Thread.currentThread().getId();
		Connection c = null;
		ConnectionPool pool = null;
		CallableStatement cstmt1 = null;
		objConexion objCon = null;
		Logger logger = LogsManagerOperadorExterno.getInstance( );
		String login = configConex.getV_loginUser();
		  
		try{
			
			if (configConex.getV_resultado() == null){
				configConex.setV_resultado( "E0" );
			}
			
            logger.info("=> INICA REGISTRO DE INTENTO FALLIDO EN CODESA "+Constantes.NOMBRE_OPERADOR+": hilo actual: "+hiloActual+" - "+configConex.getV_loginUser());
			logger.info("=> ID TRANSACCION CODESA: <<<< "+configConex.getV_numtransaccion()+" >>>> - hilo actual: "+hiloActual+"-"+configConex.getV_loginUser());
			logger.info("=> COD ENTIDAD: <<<< "+configConex.getCodEntidad()+" >>>> - hilo actual: "+hiloActual+"-"+configConex.getV_loginUser());
			logger.info("=> COD DATOS: <<<< "+configConex.getCodDatos()+" >>>> - hilo actual: "+hiloActual+"-"+configConex.getV_loginUser());
			logger.info("=> OPERACION: <<<< PKGN_BNET_SUPERPAGOS_PAQUETES.prdb_registra_intento_fallido  >>>> - hilo actual: "+hiloActual+"-"+configConex.getV_loginUser());
			
			pool = ConnectionPool.getInstance( );
			objCon = pool.getConnection();
			c = objCon.getConnection();

			cstmt1 = c.prepareCall( "{ CALL PKGN_BNET_SUPERPAGOS_PAQUETES.prdb_registra_intento_fallido(?,?,?,?,?)}" );

			cstmt1.setString( 1, configConex.getV_numtransaccion() );           
			cstmt1.setString( 2, configConex.getCodEntidad() );           
			cstmt1.setString( 3, configConex.getCodDatos() );
			cstmt1.setString( 4, configConex.getV_resultado() );

			//Parametros de Salida (resultado)
			cstmt1.registerOutParameter( 5, Types.VARCHAR );           

			//Ejecuta procedimiento
			cstmt1.execute( );

			//Obtiene parametros de salida para ejecutar sgte procedimiento
			configConex.setV_resultado( cstmt1.getString( 5 ));    

		}catch( Exception e ){

			logger.log( Level.SEVERE,  login + ": ERROR Llamando Operacion: [ PKGN_BNET_SUPERPAGOS_PAQUETES.prdb_registra_intento_fallido ] Error es: " + e.toString( )+" - Usuario : " + login+" Hilo actual: "+hiloActual );             

		}finally{

			ConnectionPool.cerrarConexion( pool, objCon, cstmt1);

		}
		
		logger.info("=> FINALIZA REGISTRO INTENTO FALLIDO "+Constantes.NOMBRE_OPERADOR+": hilo actual "+hiloActual+"-"+login);
		logger.info("=> RESULTADO: " + configConex.getV_resultado()+ " hilo actual "+hiloActual+"-"+login);
		logger.info("=> ID TRANSACCION CODESA: <<<< "+configConex.getV_numtransaccion()+" >>>> - hilo actual: "+hiloActual+"-"+login);        
        logger.info("=> TRAMA : " + configConex.getV_trama()+ " hilo actual "+hiloActual+"-"+login);
        
		return configConex;
	}
	
	public ConfigConexionTrama registraIntentoFallidoRecarga( ConfigConexionTrama configConex) {

		long hiloActual = Thread.currentThread().getId();
		Connection c = null;
		ConnectionPool pool = null;
		CallableStatement cstmt1 = null;
		objConexion objCon = null;
		Logger logger = LogsManagerOperadorExterno.getInstance( );
		String login = configConex.getV_loginUser();
		  
		try{
			
			if (configConex.getV_resultado() == null){
				configConex.setV_resultado( "E0" );
			}
			
            logger.info("=> INICA REGISTRO DE INTENTO FALLIDO EN CODESA "+Constantes.NOMBRE_OPERADOR+": hilo actual: "+hiloActual+" - "+configConex.getV_loginUser());
			logger.info("=> ID TRANSACCION CODESA: <<<< "+configConex.getV_numtransaccion()+" >>>> - hilo actual: "+hiloActual+"-"+configConex.getV_loginUser());
			logger.info("=> COD ENTIDAD: <<<< "+configConex.getCodEntidad()+" >>>> - hilo actual: "+hiloActual+"-"+configConex.getV_loginUser());
			logger.info("=> COD DATOS: <<<< "+configConex.getCodDatos()+" >>>> - hilo actual: "+hiloActual+"-"+configConex.getV_loginUser());
			logger.info("=> OPERACION: <<<< PKGN_BNET_SUPERPAGOS_RECARGAS.prdb_registra_intento_fallido  >>>> - hilo actual: "+hiloActual+"-"+configConex.getV_loginUser());
			
			pool = ConnectionPool.getInstance( );
			objCon = pool.getConnection();
			c = objCon.getConnection();

			cstmt1 = c.prepareCall( "{ CALL PKGN_BNET_SUPERPAGOS_RECARGAS.prdb_registra_intento_fallido(?,?,?,?,?)}" );

			cstmt1.setString( 1, configConex.getV_numtransaccion() );           
			cstmt1.setString( 2, configConex.getCodEntidad() );           
			cstmt1.setString( 3, configConex.getCodDatos() );
			cstmt1.setString( 4, configConex.getV_resultado() );

			//Parametros de Salida (resultado)
			cstmt1.registerOutParameter( 5, Types.VARCHAR );           

			//Ejecuta procedimiento
			cstmt1.execute( );

			//Obtiene parametros de salida para ejecutar sgte procedimiento
			configConex.setV_resultado( cstmt1.getString( 5 ));    

		}catch( Exception e ){

			logger.log( Level.SEVERE,  login + ": ERROR Llamando Operacion: [ PKGN_BNET_SUPERPAGOS_RECARGAS.prdb_registra_intento_fallido ] Error es: " + e.toString( )+" - Usuario : " + login+" Hilo actual: "+hiloActual );             

		}finally{

			ConnectionPool.cerrarConexion( pool, objCon, cstmt1);

		}
		
		logger.info("=> FINALIZA REGISTRO INTENTO FALLIDO "+Constantes.NOMBRE_OPERADOR+": hilo actual "+hiloActual+"-"+login);
		logger.info("=> RESULTADO: " + configConex.getV_resultado()+ " hilo actual "+hiloActual+"-"+login);
		logger.info("=> ID TRANSACCION CODESA: <<<< "+configConex.getV_numtransaccion()+" >>>> - hilo actual: "+hiloActual+"-"+login);        
        logger.info("=> TRAMA : " + configConex.getV_trama()+ " hilo actual "+hiloActual+"-"+login);
        
		return configConex;
	}
	
	/** 
    *Autor: Mauricio Lopez Castillo
    *Este metodo registra un error de comunicacion deacuerdo al nro de transaccion que se procese
	*/
	public ConfigConexionTrama registraReintentoEstado( ConfigConexionTrama configConex, String codEntidad, String codDatos ) {

		Connection c = null;
		ConnectionPool pool = null;
		CallableStatement cstmt1 = null;
		objConexion objCon = null;
		Logger logger = LogsManagerOperadorExterno.getInstance( );
		String login = configConex.getV_loginUser();

		configConex.setV_resultado( "E0" );  

		try{
			logger.info(  "Antes de conexion a DB - Operacion: [ REGISTRAREINTENTO ] - Usuario : " + login );          

			pool = ConnectionPool.getInstance( );
			objCon = pool.getConnection();
			c = objCon.getConnection();

			logger.info( "Despues de conexion a DB - Operacion: [ REGISTRAREINTENTO ] - Usuario : " + login );

			// Procedimiento que retorna como datos principales, el IP y PUERTO del Socket del Proveedor al que se va a Conectar 
			//y la trama que se le debe enviar
			cstmt1 = c.prepareCall( "{ CALL gambledbv10_otrosservicios.registrareintentoestado(?,?,?,?)}" );

			// Se pasa al procedimento el codigo de la transaccion, que esta en la primera posicion de la cadena tokenizada
			cstmt1.setString( 1, configConex.getV_numtransaccion() );           
			cstmt1.setString( 2, codEntidad );           
			cstmt1.setString( 3, codDatos );           

			//Parametros de Salida (resultado)
			cstmt1.registerOutParameter( 4, Types.VARCHAR );           

			try{
				logger.info(  "VA A EJECUTAR: Operacion: [ REGISTRAREINTENTO ] PARAMETROS: " + login  +"PARAMETRO ENTRADA: "+ configConex.getV_numtransaccion()  ); 
				// Ejecuta procedimiento
				cstmt1.execute( );

				//Obtiene parametros de salida para ejecutar sgte procedimiento
				configConex.setV_resultado( cstmt1.getString( 4 ));   
				logger.info( "EJECUTO: Operacion: [ REGISTRAREINTENTO ] PARAMETROS: " + login +" PARAMETROS DE SALIDA "+configConex.getV_resultado() );

			}catch( Exception e ){

				logger.log( Level.SEVERE,  login + ": Llamando, Operacion: [ REGISTRAREINTENTO ] Error es: " + e.toString( ) );             
			}

		}catch( Exception e ){

			logger.log( Level.SEVERE,  login + ": Llamando, Operacion: [ REGISTRAREINTENTO ] Error es: " + e.toString( ) );             
		}finally{

			ConnectionPool.cerrarConexion( pool, objCon, cstmt1);
		}

		return configConex;
	}
	
	/** 
    *Autor: Mauricio Lopez Castillo
    *Este metodo registra un error de comunicacion deacuerdo al nro de transaccion que se procese
	*/
	public ConfigConexionTrama registraErrorCom( ConfigConexionTrama configConex ) {

		Connection c = null;
		ConnectionPool pool = null;
		objConexion objCon = null;
		CallableStatement cstmt1 = null;
		Logger logger = LogsManagerOperadorExterno.getInstance( );
		String login = configConex.getV_loginUser();

		configConex.setV_resultado( "E0" );  

		try{
			logger.info(  "Antes de conexion a DB - Operacion: [ REGISTRAERRORCOM ] - Usuario : " + login );

			// Se tokeniza la cadena para obtener el nro de la transacion 
			// y enviarlo al paquete para que registre el erro en la aplicacion
			StringTokenizer str = new StringTokenizer( configConex.getV_trama(),"," );
			String nroTransaccion = str.nextToken();

			pool = ConnectionPool.getInstance( );
			objCon = pool.getConnection();
			c = objCon.getConnection();

			logger.info( "Despues de conexion a DB - Operacion: [ REGISTRAERRORCOM ] - Usuario : " + login );
			// Procedimiento que retorna como datos principales, el IP y PUERTO del Socket del Proveedor al que se va a Conectar 
			//y la trama que se le debe enviar
			cstmt1 = c.prepareCall( "{ CALL gambledbv10_otrosservicios.registrarerrorcom(?,?)}" );

			// Se pasa al procedimento el codigo de la transaccion, que esta en la primera posicion de la cadena tokenizada
			cstmt1.setString( 1, nroTransaccion );           

			//Parametros de Salida (resultado)
			cstmt1.registerOutParameter( 2, Types.VARCHAR );           

			try{
				logger.info(  "VA A EJECUTAR: Operacion: [ REGISTRAERRORCOM ] PARAMETROS: " + login  +"PARAMETRO ENTRADA: "+ nroTransaccion  ); 

				// Ejecuta procedimiento
				cstmt1.execute( );

				//Obtiene parametros de salida para ejecutar sgte procedimiento
				configConex.setV_resultado( cstmt1.getString( 2 ));    

				logger.info( "EJECUTO: Operacion: [ REGISTRAERRORCOM ] PARAMETROS: " + login +" PARAMETROS DE SALIDA "+configConex.getV_resultado() );

			}catch( Exception e ){
			
				logger.log( Level.SEVERE,  login + ": Llamando, Operacion: [ REGISTRAERRORCOM ] Error es: " + e.toString( ) );             
			}

		}catch( Exception e ){

			logger.log( Level.SEVERE,  login + ": Llamando, Operacion: [ REGISTRAERRORCOM ] Error es: " + e.toString( ) );             
		}finally{

			ConnectionPool.cerrarConexion( pool, objCon, cstmt1);
		}
		return configConex;
	}
	

	/** Autor: Mauricio Lopez Castillo
	 * Funcion que llama al procedimiento de BD registrarespuestarm con la trama que retorna el 
	 * Server Socket y los parametros que retorno el procedimiento de BD generatramarm
	 */
	public ConfigConexionTrama recargaMovilRegistraRespuesta( ConfigConexionTrama configConex ) {

		String login = configConex.getV_loginUser();
		Connection c = null;
		ConnectionPool pool = null;
		objConexion objcon = null;
		CallableStatement cstmt1 = null;
		Logger logger = LogsManagerOperadorExterno.getInstance( );

		configConex.setV_resultado( "E0" );  

		// Configuracion que retorna el prcedimoento registrarespuestarm
		ConfigConexionTrama configConexRegistraRespuesta = new ConfigConexionTrama();  

		logger.info( "Parametros que entran a registrarTrama "+General.obtenerFechaHoraActual( true ) + " " +  
				configConex.getV_trama(  )+"-"+    
				configConex.getV_nitproveedor( )+"-"+
				configConex.getV_serviciocodigo( )+"-"+ 
				configConex.getV_otroproductocodigo( )+"-"+
				configConex.getV_numtransaccion(  )+"-"+
				configConex.getV_tipotrama(  )+ " hilo actual "+Thread.currentThread().getId()+"-"+login);
		try{          

			logger.info(  "Antes de conexion a DB - Operacion: [ REGISTRATRAMARM ] - Usuario : " + login + " hilo actual "+Thread.currentThread().getId()+"-"+login);

			pool = ConnectionPool.getInstance( );
			objcon = pool.getConnection();
			c = objcon.getConnection();

			logger.info( "Despues de conexion a DB - Operacion: [ REGISTRATRAMARM ] - Usuario : " + login + " hilo actual "+Thread.currentThread().getId()+"-"+login);

			// Procedimiento que permite registrar la respuesta y obtener la trama de salida y un resultado
			// Algunos parametros de salida del procedimiento ejecutado anteriormente, se usan para la entrada de este.
			cstmt1 = c.prepareCall( "{ CALL gambledbv10_otrosservicios.registrarespuestarm(?,?,?,?,?,?,?,?)}" );

			// Se pasa al procedimento (registrarespuestarm) los parametros que retorno el procedimiento anterior (gambledbv10_otrosservicios.generatramarm)
			cstmt1.setString( 1, configConex.getV_trama() );    
			cstmt1.setString( 2, configConex.getV_nitproveedor() ); 
			cstmt1.setString( 3, configConex.getV_serviciocodigo() ); 
			cstmt1.setString( 4, configConex.getV_otroproductocodigo() ); 
			cstmt1.setString( 5, configConex.getV_numtransaccion() ); 
			cstmt1.setString( 6, configConex.getV_tipotrama() ); 

			// Parametros de Salida
			cstmt1.registerOutParameter( 7, Types.VARCHAR );
			cstmt1.registerOutParameter( 8, Types.VARCHAR ); 

			try{

				logger.info( "VA A EJECUTAR: Operacion: [ REGISTRATRAMARM ] PARAMETROS: " + login + "PARAMETROS DE ENTRADA: Trama "+ configConex.getV_trama()+" - "+
						"Nit Proveedor "+ configConex.getV_nitproveedor()+" - "+
						"Servicio "+ configConex.getV_serviciocodigo()+" - "+
						"Producto "+ configConex.getV_otroproductocodigo()+" - "+
						"Nro transaccion "+ configConex.getV_numtransaccion()+" - "+
						"Tipo trama "+ configConex.getV_tipotrama()+ " hilo actual "+Thread.currentThread().getId()+"-"+login);

				// Ejecuta procedimiento gambledbv10_otrosservicios.registrarespuestarm
				cstmt1.execute( );

				// Obtengo los valores de los parametros de salida
				configConexRegistraRespuesta.setV_trama( ( cstmt1.getString( 7 )==null ? "" : (cstmt1.getString( 7 ) ) ) );  
				configConexRegistraRespuesta.setV_resultado( cstmt1.getString( 8 ) ); // Resultado

				logger.info("EJECUTO: Operacion: [ REGISTRATRAMARM ] PARAMETROS: " + login +" PARAMETROS DE SALIDA Trama: " + ( configConexRegistraRespuesta.getV_trama() == null ? "" : configConexRegistraRespuesta.getV_trama() )+
						"Resultado: " + configConexRegistraRespuesta.getV_resultado()+ " hilo actual "+Thread.currentThread().getId()+"-"+login);

				logger.info(" Valor trama "+ configConexRegistraRespuesta.getV_trama() + " hilo actual "+Thread.currentThread().getId()+"-"+login);
				logger.info(" Valor resultado "+ configConexRegistraRespuesta.getV_resultado() + " hilo actual "+Thread.currentThread().getId()+"-"+login);

				// Genera log en caso de que el procedimiento no retorne trama de respuesta al cliente
				if( configConexRegistraRespuesta.getV_trama().equals("") || configConexRegistraRespuesta.getV_trama() ==  null )
				{
					try{

						logger.info("usuario: "+login +" NO HAY TRAMA RESPUESTA DEL WEB SERVICE"+ " hilo actual "+Thread.currentThread().getId()+"-"+login);

					}catch(Exception e){

						System.out.println( "Error generando Log en Web Service "+e.getMessage() );
					}
				}
			}catch( Exception e ){
				logger.log( Level.SEVERE,  login + ": Llamando, Operacion1: [ REGISTRATRAMARM ] Error es: " + e.toString( ) + " hilo actual "+Thread.currentThread().getId()+"-"+login);
			}

		}catch( Exception e ){

			logger.log( Level.SEVERE,  login + ": Llamando, Operacion2: [ REGISTRATRAMARM ] Error es: " + e.toString( ) + " hilo actual "+Thread.currentThread().getId()+"-"+login);

		}finally{
			ConnectionPool.cerrarConexion( pool, objcon, cstmt1);
		}
		return configConexRegistraRespuesta;
	}
	
	/* Autor: Mauricio Lopez Castillo
	 * Funcion que llama al procedimiento de BD registrarespuestarm con la trama que retorna el 
	 * Server Socket y los parametros que retorno el procedimiento de BD generatramarm
	 */
	public ConfigConexionTrama recargaMovilRegistraRespuesta2( ConfigConexionTrama configConex ) {

		String login = configConex.getV_loginUser();
		Connection c = null;
		objConexion objCon = null;
		ConnectionPool pool = null;
		CallableStatement cstmt1 = null;
		Logger logger = LogsManagerOperadorExterno.getInstance( );
		configConex.setV_resultado( "E0" );  

		// Configuracion que retorna el prcedimoento registrarespuestarm
		ConfigConexionTrama configConexRegistraRespuesta = new ConfigConexionTrama();  

		logger.info( "Parametros que entran a registrarTrama "+General.obtenerFechaHoraActual( true ) + " " +  
				configConex.getV_trama(  )+"-"+    
				configConex.getV_nitproveedor( )+"-"+
				configConex.getV_serviciocodigo( )+"-"+ 
				configConex.getV_otroproductocodigo( )+"-"+
				configConex.getV_numtransaccion(  )+"-"+
				configConex.getV_tipotrama(  )+ " hilo actual "+Thread.currentThread().getId()+"-"+login);
		try{          

			logger.info(  "Antes de conexion a DB - Operacion: [ REGISTRATRAMARM ] - Usuario : " + login + " hilo actual "+Thread.currentThread().getId()+"-"+login);

			pool = ConnectionPool.getInstance( );
			objCon = pool.getConnection();
			c = objCon.getConnection();

			logger.info( "Despues de conexion a DB - Operacion: [ REGISTRATRAMARM ] - Usuario : " + login + " hilo actual "+Thread.currentThread().getId()+"-"+login);

			String validTAT = configConex.getValidaTAT();

			if (validTAT==null ||validTAT.equalsIgnoreCase("N")){
				// Procedimiento que permite registrar la respuesta y obtener la trama de salida y un resultado
				// Algunos parametros de salida del procedimiento ejecutado anteriormente, se usan para la entrada de este.
				cstmt1 = c.prepareCall( "{ CALL gambledbv10_otrosservicios.registrarespuestarm2(?,?,?,?,?,?,?,?)}" );				
			}else{
				// Procedimiento que permite registrar la respuesta y obtener la trama de salida y un resultado
				// Algunos parametros de salida del procedimiento ejecutado anteriormente, se usan para la entrada de este.
				cstmt1 = c.prepareCall( "{ CALL PKGN_BUSINESSNETOTROSSERVICIOS.registrarespuestarm2(?,?,?,?,?,?,?,?,?,?)}" );
			}

			// Se pasa al procedimento (registrarespuestarm) los parametros que retorno el procedimiento anterior (gambledbv10_otrosservicios.generatramarm)
			cstmt1.setString( 1, configConex.getV_trama() );    
			cstmt1.setString( 2, configConex.getV_nitproveedor() ); 
			cstmt1.setString( 3, configConex.getV_serviciocodigo() ); 
			cstmt1.setString( 4, configConex.getV_otroproductocodigo() ); 
			cstmt1.setString( 5, configConex.getV_numtransaccion() ); 
			cstmt1.setString( 6, configConex.getV_tipotrama() ); 

			if (validTAT==null ||validTAT.equalsIgnoreCase("N")){
				cstmt1.registerOutParameter( 7, Types.VARCHAR );
				cstmt1.registerOutParameter( 8, Types.VARCHAR ); 

			}else{
				cstmt1.setString( 7, configConex.getNumidentificador() ); 
				cstmt1.setString( 8, configConex.getDocVendedor() ); 
				cstmt1.registerOutParameter( 9, Types.VARCHAR );
				cstmt1.registerOutParameter( 10, Types.VARCHAR ); 				
			}

			try{

				logger.info( "VA A EJECUTAR: Operacion: [ REGISTRATRAMARM ] PARAMETROS: " + login + "PARAMETROS DE ENTRADA: Trama "+ configConex.getV_trama()+" - "+
						"Nit Proveedor "+ configConex.getV_nitproveedor()+" - "+
						"Servicio "+ configConex.getV_serviciocodigo()+" - "+
						"Producto "+ configConex.getV_otroproductocodigo()+" - "+
						"Nro transaccion "+ configConex.getV_numtransaccion()+" - "+
						"Tipo trama "+ configConex.getV_tipotrama()+ " hilo actual "+Thread.currentThread().getId()+"-"+login);

				// Ejecuta procedimiento gambledbv10_otrosservicios.registrarespuestarm
				//cstmt1.setQueryTimeout(5);
				cstmt1.execute( );

				if (validTAT==null ||validTAT.equalsIgnoreCase("N")){
					// Obtengo los valores de los parametros de salida
					configConexRegistraRespuesta.setV_trama( ( cstmt1.getString( 7 )==null ? "" : (cstmt1.getString( 7 ) ) ) );  
					configConexRegistraRespuesta.setV_resultado( cstmt1.getString( 8 ) ); // Resultado

				}else{
					// Obtengo los valores de los parametros de salida
					configConexRegistraRespuesta.setV_trama( ( cstmt1.getString( 9 )==null ? "" : (cstmt1.getString( 9 ) ) ) );  
					configConexRegistraRespuesta.setV_resultado( cstmt1.getString( 10 ) ); // Resultado
				}
				
				logger.info("EJECUTO: Operacion: [ REGISTRATRAMARM ] PARAMETROS: " + login +" PARAMETROS DE SALIDA Trama: " + ( configConexRegistraRespuesta.getV_trama() == null ? "" : configConexRegistraRespuesta.getV_trama() )+
						"Resultado: " + configConexRegistraRespuesta.getV_resultado()+ " hilo actual "+Thread.currentThread().getId()+"-"+login);

				logger.info(" Valor trama "+ configConexRegistraRespuesta.getV_trama() + " hilo actual "+Thread.currentThread().getId()+"-"+login);
				logger.info(" Valor resultado "+ configConexRegistraRespuesta.getV_resultado() + " hilo actual "+Thread.currentThread().getId()+"-"+login);

				//Fabio Hernandez
				//Se modifica mensaje de alguna incidencia retornado desde el paquete para no mostrar el estado ni el tipo al cliente solo la descripcion
				if(configConexRegistraRespuesta != null && configConexRegistraRespuesta.getV_resultado() != null){
				
					configConexRegistraRespuesta.setV_resultado(ControladorMensajes.getMensaje(configConexRegistraRespuesta.getV_resultado()));
				}
				
				// Genera log en caso de que el procedimiento no retorne trama de respuesta al cliente
				if( configConexRegistraRespuesta.getV_trama().equals("") || configConexRegistraRespuesta.getV_trama() ==  null )
				{
					try{

						logger.info("usuario: "+login +" NO HAY TRAMA RESPUESTA DEL WEB SERVICE"+ " hilo actual "+Thread.currentThread().getId()+"-"+login);

					}catch(Exception e){

						System.out.println( "Error generando Log en Web Service "+e.getMessage() );
					}
				}

			}catch( Exception e ){
				logger.log( Level.SEVERE,  login + ": Llamando, Operacion1: [ REGISTRATRAMARM ] Error es: " + e.toString( ) + " hilo actual "+Thread.currentThread().getId()+"-"+login);
			}

		}catch( Exception e ){

			logger.log( Level.SEVERE,  login + ": Llamando, Operacion2: [ REGISTRATRAMARM ] Error es: " + e.toString( ) + " hilo actual "+Thread.currentThread().getId()+"-"+login);

		}finally{
			ConnectionPool.cerrarConexion( pool, objCon, cstmt1 );
		}

		return configConexRegistraRespuesta;
	}
	
	/*
	 * Autor : Mauricio Lopez Castillo
	 * Descripcion: Metodo para consulta de valores de parametros operativos del sistema
	 * Paramrtro: ingresa el nombre del parametro operativo
	 */
	public ResultadoBean consultarParametroSistema(String strNombreParametro) {

		ResultadoBean resultado=new ResultadoBean();        
		Connection c = null;
		ConnectionPool pool = null;
		objConexion objCon = null;
		CallableStatement cstmt1 = null;      
		Logger logger = LogsManagerOperadorExterno.getInstance( );

		try{            
			logger.info( General.obtenerFechaHoraActual( true ) + " " + "==>Vamos a Consultar Parametro.... "+strNombreParametro);

			pool=ConnectionPool.getInstance( );
			objCon = pool.getConnection();
			c= objCon.getConnection();

			cstmt1 = c.prepareCall( "begin ? := gambledbv10_5.funbuscpara( ? ); end;" );
			cstmt1.registerOutParameter( 1, Types.VARCHAR );            
			cstmt1.setString( 2, strNombreParametro );            

			try{

				cstmt1.execute( );                
				resultado.setObject( cstmt1.getString( 1 ) );

				if( resultado.getObject() != null)
				{     
					resultado.setResultado(true);
					resultado.conMensaje(false);

				}else
				{                   
					resultado.setResultado(false);
					resultado.setMensaje("Parametro de Sistema no existe" );
					resultado.conMensaje(true); 

				}

			}catch( Exception e ){
				resultado.setResultado(false);
				resultado.setMensaje("Problemas consultado parametro" );
				logger.log( Level.SEVERE, General.obtenerFechaHoraActual( true ) + " " + "==>FormularioBean: NO es posible llamar a la funcion funbuscpara, Error " + e.toString( ) );

			}

			logger.info( General.obtenerFechaHoraActual( true ) +  "==>RecargaMovil: se ejecuto el procedimiento funbuscpara" );

		}catch( Exception e ){
			e.printStackTrace(System.out);
			resultado.setResultado(false);
			resultado.setMensaje("Problemas consultado parametro" );
			logger.log( Level.SEVERE, General.obtenerFechaHoraActual( true ) + " " + "==>error en la clase RecargaMovil, funbuscpara, Mensaje:" + e.toString( ) );

		}finally{
			ConnectionPool.cerrarConexion( pool, objCon, cstmt1);

		}

		return resultado;

	}

}