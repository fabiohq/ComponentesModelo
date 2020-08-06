package co.com.codesa.socketcodesaoperadorexterno.dao.pool;

import java.util.List;
import java.util.Hashtable;
import java.util.Enumeration;
import java.sql.*;
import java.util.logging.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.Collections;
import java.sql.SQLException;
import java.sql.DriverManager;

import co.com.codesa.socketcodesaoperadorexterno.general.LogsManager;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.Constantes;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.General;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;



/**
 * Clase que administra el pool de conexiones de BD
 * @author mauricio.lopez
 */
public class ConnectionPool{
    
	private String url = null;
    private String user = null;
    private String driver = null;
    private String password = null;
    private String checkQuery = null;
    private List<Object> usedConnections;
    private List<Object> availableConnections;
    private Hashtable<String,Object> htusedConnections;
    private Hashtable<String,Object> htavailableConnections;

    private int minConnections = -1;
    private int totalConnections = -1;

    private boolean boInicializado = false;

    private static ConnectionPool connectionPool = null;
    private static ConnectionPool connectionPoolBackup1 = null;
    private static ConnectionPool connectionPoolBackup2 = null;

    private static long TIEMPO_USADAS_INACTIVAS = 60000;
    private static long TIEMPO_DISPONIBLES_INACTIVAS = 60000;
    
    private ComboPooledDataSource c3poDataSource;


    public ConnectionPool( ){
     
    	usedConnections = Collections.synchronizedList( new ArrayList<Object>() );
        availableConnections = Collections.synchronizedList( new ArrayList<Object>( ) );
        htavailableConnections = new Hashtable<String,Object>( );
        htusedConnections = new Hashtable<String,Object>( );
    }

    private void obtenerCantidadConecciones( )  throws Exception{
        String min = "";
        String max = "";

        if( minConnections < 0){
            try {

                min = General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_MINCONNECTIONS );
                minConnections = Integer.parseInt(min);
            } catch (Exception e) {
                LogsManager.getInstance( ).log(Level.SEVERE, "id[" + Thread.currentThread().getId() + "] La configuracion de el numero de conexiones es errada o no existe. " +", minConnections[" + minConnections + "]" );
                throw new SQLException( "No se pudo leer el numero minimo de conexiones" );

            }
        }

        if( totalConnections < 0){

            try {

            	max = 	General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_TOTALCONNECTIONS );
                totalConnections = Integer.parseInt( max );
            } catch (Exception e) {
                LogsManager.getInstance( ).log(Level.SEVERE, "id[" + Thread.currentThread().getId() + "] La configuracion de el numero de conexiones es errada o no existe. " +
                        ", totalConnections[" + totalConnections + "]");
                throw new SQLException( "No se pudo leer el numero maximo de conexiones" );

            }
        }

        LogsManager.getInstance( ).info( "id[" + Thread.currentThread().getId() + "] Minimo numero de conexiones: [" + minConnections + "] - Maximo numero de conexiones [" + totalConnections + "]" );
    }

    public static void cerrarConexion( ConnectionPool pool, objConexion objcon,
                                       CallableStatement cstm) {

        try {
        	
            Connection con=objcon.getConnection();
            
            if (cstm != null) {
                cstm.clearParameters();
                cstm.close();
                cstm=null;
            }

            if (pool != null && con != null) {
                pool.freeConnection(objcon);
            }

        } catch (Exception e) {
            LogsManager.getInstance( )
                    .log(Level.SEVERE, "id[" + Thread.currentThread().getId() + "] "+General.obtenerFechaHoraActual(true) + "Error cerrando pool de conexiones " +
                    e.toString()+ "\nTraza de error " + General.obtenerTraza(e));
            e.printStackTrace();
        }
    }
    
    public static void cerrarConexion( ConnectionPool pool, objConexion objcon,
            CallableStatement cstm, Connection connection) {
    	
    	try {
           connection.close();
           cerrarConexion(pool, objcon, cstm);
           
        } catch (Exception e) {
            LogsManager.getInstance().log(Level.SEVERE, "id[" + Thread.currentThread().getId() + "] "+General.obtenerFechaHoraActual(true) + "Error cerrando estatement " +
                    e.toString() + "\nTraza de error " + General.obtenerTraza(e));
        }
    }
    


    public static void cerrarConexion( ConnectionPool pool, objConexion objcon) {
        Connection con=objcon.getConnection();


        try {
            if (pool != null && con != null) {
                pool.freeConnection(objcon); //MLC
            }

        } catch (Exception e) {
            LogsManager.getInstance( )
                    .log(Level.SEVERE, "id[" + Thread.currentThread().getId() + "] "+General.obtenerFechaHoraActual(true) + "Error cerrando pool de conexiones " +
                    e.toString()+ "\nTraza de error " + General.obtenerTraza(e));
            e.printStackTrace();
        }

    }

    public synchronized void init(String url, String user, String password,
            String driver, int totalConnections,
            int minConnections,
            String checkQuery) throws Exception{
        obtenerCantidadConecciones( );
        this.url = url;
        this.user = user;
        this.driver = driver;
        this.password = password;
        this.checkQuery = checkQuery;
        this.minConnections = minConnections;
        this.totalConnections = totalConnections;

        this.init();
    }

    public synchronized void init(String driver, String url, String user,
            String password) throws Exception{
        obtenerCantidadConecciones( );
        this.url = url;
        this.user = user;
        this.driver = driver;
        this.checkQuery = "S";
        this.password = password;
        this.init(); //MLC COMENTADO POR MAURICIO PARA IMPLEMENTACION DE SMART POOL
    }

    public synchronized void init(String driver, String url, String user,
            String password, String ip, String listener,
            String database) throws SQLException,
            ClassNotFoundException {
        this.url = url;
        this.user = user;
        this.driver = driver;
        this.checkQuery = "S";
        this.password = password;
        this.init();
    }

    public static ConnectionPool getInstance(){

        if (connectionPool == null)
            connectionPool = new ConnectionPool();

        return connectionPool;

    }

    public static ConnectionPool getInstance(int iPool) {
        if (iPool == 1) {
            if (connectionPoolBackup1 == null)
                connectionPoolBackup1 = new ConnectionPool();

            return connectionPoolBackup1;

        }

        if (iPool == 2) {
            if (connectionPoolBackup2 == null)
                connectionPoolBackup2 = new ConnectionPool();

            return connectionPoolBackup2;

        }

        if (connectionPool == null)
            connectionPool = new ConnectionPool();

        //System.out.println("Se ENTREGA la instancia del POOL de conexiones.");
        return connectionPool;

    }

    public boolean estaInit() {
        return boInicializado;
    }

    public synchronized void reiniciarCol() throws Exception
    {
        this.htavailableConnections.clear();
        this.htusedConnections.clear();
        this.c3poDataSource.close();
        boInicializado = false;
        this.init();

    }



    protected void init() throws SQLException {

    	if (boInicializado == false) {
            
    		try {
                //Map extension = new Hashtable();

                c3poDataSource = new ComboPooledDataSource();
                c3poDataSource.setDriverClass(driver);
                c3poDataSource.setJdbcUrl(url);
                c3poDataSource.setUser(user);
                c3poDataSource.setPassword(password);

                String minConnections =  General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_MINCONNECTIONS );
                c3poDataSource.setMinPoolSize(new Integer(minConnections));
                
                String totalConnections = General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_TOTALCONNECTIONS );
                c3poDataSource.setMaxPoolSize(new Integer(totalConnections));
                
                String incrementoConexiones = General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_INCREMENTOCONEXIONES );
                c3poDataSource.setAcquireIncrement(new Integer(incrementoConexiones));

                String maximoPrepareStatements = General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_MAXIMOPREPARESTATEMENTS );
                c3poDataSource.setMaxStatements(new Integer(maximoPrepareStatements));
                
                String maxIdleTime = General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_MAXIDLETIME );
                c3poDataSource.setMaxIdleTime(new Integer(maxIdleTime));
                
                String testConnectionOnCheckin = General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_TESTCONNECTIONONCHEKIN );
                c3poDataSource.setTestConnectionOnCheckin(Boolean.valueOf(testConnectionOnCheckin));
                
                String idleConnectionTestPeriod = General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_IDLECONNECTIONTESTPERIOD );
                c3poDataSource.setIdleConnectionTestPeriod(new Integer(idleConnectionTestPeriod));
                
                String maxIdleTimeExcessConnections = General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_MAXIDLETIMEEXCESSCONNECTIONS );
                c3poDataSource.setMaxIdleTimeExcessConnections(new Integer(maxIdleTimeExcessConnections));
                c3poDataSource.setPreferredTestQuery("select 'X' from dual");
              //
                c3poDataSource.setConnectionCustomizerClassName(MyConnectionCustomizer.class.getCanonicalName());
                
                Integer acquireRetryAttempts = Integer.parseInt( General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_ACQUIRERETRYATTEMPS ) );  
                c3poDataSource.setAcquireRetryAttempts(acquireRetryAttempts); //2
                
                Integer acquireRetryDelay = Integer.parseInt( General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_ACQUIRERETRYDELAY ) );  
                
                c3poDataSource.setAcquireRetryDelay(acquireRetryDelay); //1000	
                //extension.put("initSql", "begin gambledbv10_4.validateonl( 'GAMBLE',  gambledbv10_4.selpasswordgamble( ) ); end;");
                //c3poDataSource.setExtensions(extension);

                // con la configuracion anterior C3PO ya crea el pool.
                boInicializado = true;
            }
             catch(Exception e){
                throw new SQLException("ConnectionPool:init(): C3PO "+e.getMessage());
            }
        }
    }

    public void inicializar() throws SQLException {

        if (boInicializado == false) {
        	
            init();
        }
    }

    /*
    public synchronized Connection getConnection2( ) throws SQLException {
        Connection con = null;

        if( htavailableConnections.size( ) > 0 ) {
            con = ( Connection )availableConnections.get(0);
            usedConnections.add( con );
            availableConnections.remove( 0 );
            try {
                con = checkConnection(con);
            } catch (SQLException e) {
                usedConnections.remove(0);;
                throw new SQLException("ConnectionPool:getConnection()::Connection Problem -- SQLException: " +
                        e.toString());
            }
        } else {

            if ((availableConnections.size() + usedConnections.size()) <
                    totalConnections) {
                try {
                    LogsManager.getInstance( ).log(Level.INFO, "id[" + Thread.currentThread().getId() + "] "+General.obtenerFechaHoraActual(true) + "Creo nueva conexion, usadas[" + usedConnections.size() + "]");
                    con = createConnection();
                    usedConnections.add(con);
                } catch (SQLException se) {
                    throw new SQLException("ConnectionPool:getConnection()::Cannot create new connection -- Exception:" +
                            se.toString());
                }
            } else {
                throw new SQLException("No se pudo obtener conexi???n, por favor int???ntelo de nuevo mas tarde.");

            }
        }

        return con;

    }*/

    // Se debe quita el sincronize para implementacion de C3PO
    public objConexion getConnection( ) throws SQLException {

        objConexion objcon = new objConexion();
        Connection con;
        try{
            con =  c3poDataSource.getConnection();
            //con = this.permisosConexion(con);  //permisos para la auditoria
            objcon.setTime( System.currentTimeMillis( ) );
            objcon.setId( String.valueOf(con.hashCode()) );
            objcon.setConnection(con);

            int numDisponibles = this.c3poDataSource.getNumConnections() - this.c3poDataSource.getNumBusyConnections();

            LogsManager.getInstance( ).log( Level.INFO, "id[" + Thread.currentThread().getId() + "] Conexion id= " + objcon.getId()
                    + " Asignada. Conexiones usadas[" + c3poDataSource.getNumBusyConnections() + "]"
                    + " Conexiones Disponibles[" + numDisponibles + "]");

        }catch(SQLException sqle){

            throw new SQLException("ConnectionPool:getConnection()::Cannot create new connection -- SQLException:" +
                            sqle.toString());
        }
        
        return objcon;
    }

    //Ya lo suple C3PO
    public void Revisar_inactividad_usadas(){

        objConexion objcon = null;

        Enumeration<?> e = htusedConnections.keys( );

        long tiempo_actual = System.currentTimeMillis( );
        long tiempo_inactividad = 0;

        while( e.hasMoreElements( ) ) {
            String strKey = ( String )e.nextElement( );

            objcon = ( objConexion )this.htusedConnections.get( strKey );
            tiempo_inactividad=tiempo_actual - objcon.getTime( ) ;

            if( (tiempo_inactividad ) > TIEMPO_USADAS_INACTIVAS ) {
                LogsManager.getInstance( ).info( "id[" + Thread.currentThread().getId() + "] Conexion Usada " + strKey  + " sera cerrada por Tiempo de Inactividad " + tiempo_inactividad);

                try{
                    objcon.getConnection( ).close( );

                }catch( Exception e1 ){
                        LogsManager.getInstance( ).log( Level.SEVERE, "id[" + Thread.currentThread().getId() + "] Conexion Usada " + strKey  + " No se pudo cerrar. Error es: " + e1.toString( ) );
                }

                htusedConnections.remove( strKey );
                objcon = null;
            }
        }

    }

    //Ya lo suple C3PO
    public void Revisar_inactividad_disponibles(){

        objConexion objcon = null;

        int intConexionesCerrar=htavailableConnections.size()-this.minConnections;

        Enumeration<?> e = this.htavailableConnections.keys( );

        long tiempo_actual = System.currentTimeMillis( );
        long tiempo_inactividad = 0;
        int contador=0;
//        logger.log( Level.INFO, "==>se ENTRA A Revisar_inactividad_disponibles ");
//        logger.log( Level.INFO, "tiempo_inactividad="+tiempo_inactividad+" tiempo_actual="+tiempo_actual + " NUMERO DISPONIBLES" +htavailableConnections.size());
//        logger.log( Level.INFO, "intConexionesCerrar="+intConexionesCerrar);
//        logger.log( Level.INFO, "tiempo_actual="+tiempo_actual);
//
        while( e.hasMoreElements( ) ) {
            contador++;
            String strKey = ( String )e.nextElement( );
            objcon = ( objConexion )this.htavailableConnections.get( strKey );
            tiempo_inactividad = tiempo_actual - objcon.getTime( ) ;

            if (contador>intConexionesCerrar)
                break;

            if( (tiempo_inactividad ) > TIEMPO_DISPONIBLES_INACTIVAS ) {
                LogsManager.getInstance( ).info( "id[" + Thread.currentThread().getId() + "] Conexion Disponible " + strKey  + " sera cerrada por Tiempo de Inactividad " + tiempo_inactividad);

                try{
                    objcon.getConnection( ).close( );

                }catch( Exception e1 ){
                    LogsManager.getInstance( ).log( Level.SEVERE, "id[" + Thread.currentThread().getId() + "] No se pudo cerrar conexion. Error es: " + e1.toString( ) );
                }

                htavailableConnections.remove( strKey );
                objcon = null;
            }
        }
    }


    public synchronized void freeConnection( objConexion objcon ) {
        
        try{
            if (objcon.getConnection() == null){

                try{
                    LogsManager.getInstance( ).log(Level.WARNING, "id[" + Thread.currentThread().getId() + "] No se encontro el objeto de conexion  id="+objcon.getId()+" en la tabla de Usadas. Reporte al equipo de desarrollo");
                }catch(Exception e){
                    ;
                }

            }else{

                objcon.getConnection().close();
                int numDisponibles = this.c3poDataSource.getNumConnections() - this.c3poDataSource.getNumBusyConnections();

                LogsManager.getInstance( ).log( Level.INFO, "id[" + Thread.currentThread().getId() + "] Conexion id="+objcon.getId() 
                        + " liberada. Conexiones usadas[" + this.c3poDataSource.getNumBusyConnections() + "]"
                        + " Conexiones Disponibles[" + numDisponibles + "]");
            }
        }catch(Exception ex){
            LogsManager.getInstance( ).log( Level.SEVERE, "id[" + Thread.currentThread().getId() + "] Error en freeConnection liberando conexion "
                    + "id = " + objcon.getId() + ", Error es: " + ex.toString() + "");
        }
    }

    /*
     * Autor: MLC
     * Descripcion: Obtiene el dia actual de la BD.     *
     */
    public int getDiaActualBD()
    {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = null;
            objConexion objCon = null;
            Statement stmt = null;
            Logger logger = LogsManager.getInstance( );
            int diaActual = 0;

            try
            {
                logger.info( "id[" + Thread.currentThread().getId() + "] Antes de conexion a DB - Operacion: [ Cambio Dia ] "  );
                objCon = pool.getConnection();
                conn = objCon.getConnection();
                logger.info( "id[" + Thread.currentThread().getId() + "] Despues de conexion a DB - Operacion: [ Cambio Dia ] ");

                String strConsulta = "select to_char(sysdate,'DD') from dual";
                stmt = conn.createStatement();

                logger.info( "id[" + Thread.currentThread().getId() + "] VA A EJECUTAR: Query: [ Consulta Dia Actual ] ");
                ResultSet resultado = stmt.executeQuery(strConsulta);
                logger.info( "id[" + Thread.currentThread().getId() + "] EJECUTO: Query: [ Consulta Dia Actual ] ");


                if( resultado.next() )
                {
                    diaActual = Integer.parseInt(resultado.getString(1));
                }

                resultado.close();
                stmt.close();

            } catch (SQLException e)
            {
                    logger.log( Level.SEVERE, "id[" + Thread.currentThread().getId() + "] Llamando, Operacion: [ Cambio Dia Actual ] Error es: " + e.toString( ) );
                    e.printStackTrace();
            } finally
            {
                //ConnectionPool.cerrarConexion(pool, objCon, stmt);
                ConnectionPool.cerrarConexion( pool, objCon);

            }
            return diaActual;
    }


    //esto lo suple C3PO
    protected Connection createConnection() throws SQLException {
        Connection con = null;
        CallableStatement cstmt1 = null;
        String pw = "";

        try{

            LogsManager.getInstance( ).log( Level.INFO, "id[" + Thread.currentThread().getId() + "] <CODESA> Creando conexion de base de datos");

            Properties propiedadesBD = new Properties();
            propiedadesBD.setProperty("user", this.user);
            propiedadesBD.setProperty("password", this.password);
            propiedadesBD.setProperty("v$session.program", "SOCKETSERVER");

            // Forma antigua crear la conexion
            //con =  DriverManager.getConnection(this.url, this.user, this.password);

            // Forma nueva de crear la conexion para identificar en la columna v$session.program , las conexiones del socket
            con =  DriverManager.getConnection(this.url,propiedadesBD);


            try{

                cstmt1 = con.prepareCall( "begin ? := gambledbv10_4.selpasswordgamble( ); end;" );

                cstmt1.registerOutParameter( 1, Types.VARCHAR );

                cstmt1.executeUpdate( );

            } catch (SQLException e1) {

                LogsManager.getInstance( ).log( Level.INFO, "id[" + Thread.currentThread().getId() + "] Error Creando una Conexion Nueva");

                throw new SQLException( "ConnectionPool:createConnection::SQLException: No hay conexion por password de AUDITORIA" + e1.toString( ) );

            }
            pw = cstmt1.getString( 1 );

            cstmt1 = con.prepareCall( "{ CALL gambledbv10_4.validateonl( ?,  ? ) }" );
            cstmt1.setString( 1, "GAMBLE" );
            cstmt1.setString( 2, pw );
            cstmt1.executeUpdate( );
            LogsManager.getInstance( ).log( Level.INFO, "id[" + Thread.currentThread().getId() + "] <CODESA> Termina Creacion conexion de base de datos");

        } catch (SQLException e) {
            if (con != null) con.close();
            throw new SQLException("ConnectionPool:createConnection::SQLException " +
                    e.toString());

        }

        return con;
    }

    /**
     * Pide los permisos de auditoria para la conexion de la BD
     * @param con
     * @return
     * @throws SQLException
     */
    private Connection permisosConexion(Connection con) throws SQLException {

        CallableStatement cstmt1 = null;
        CallableStatement cstmt2 = null;
        String pw = "";

        if(con != null){

            try{

                //LogsManager.getInstance( ).log( Level.INFO, "id[" + Thread.currentThread().getId() + "] <CODESA> Permisos conexion de base de datos");

                Properties propiedadesBD = new Properties();
                propiedadesBD.setProperty("user", this.user);
                propiedadesBD.setProperty("password", this.password);
                propiedadesBD.setProperty("v$session.program", "SOCKETSERVER");

                // Forma antigua crear la conexion
                //con =  DriverManager.getConnection(this.url, this.user, this.password);

                // Forma nueva de crear la conexion para identificar en la columna v$session.program , las conexiones del socket

                //******************* OJO  **********************************
                //con =  DriverManager.getConnection(this.url,propiedadesBD);

                try{
                    cstmt1 = con.prepareCall( "begin ? := gambledbv10_4.selpasswordgamble( ); end;" );

                    cstmt1.registerOutParameter( 1, Types.VARCHAR );

                    cstmt1.executeUpdate( );
                    
                    pw = cstmt1.getString( 1 );

                } catch (SQLException e1) {

                    LogsManager.getInstance( ).log( Level.INFO, "id[" + Thread.currentThread().getId() + "] Error ejecutando permisos en una Conexion Nueva: " + e1.toString());

                    throw new SQLException( "ConnectionPool:createConnection::SQLException: No hay conexion por password de AUDITORIA" + e1.toString( ) );

                }finally{
                	//cierre de conexiones
                    cstmt1.close();
                }
                
                cstmt2 = con.prepareCall( "{ CALL gambledbv10_4.validateonl( ?,  ? ) }" );
                cstmt2.setString( 1, "GAMBLE" );
                cstmt2.setString( 2, pw );
                cstmt2.executeUpdate( );
                //LogsManager.getInstance( ).log( Level.INFO, "id[" + Thread.currentThread().getId() + "] <CODESA> Termina Creacion conexion de base de datos");

            } catch (SQLException e) {
                if (con != null) con.close();
                LogsManager.getInstance( ).log( Level.INFO, "id[" + Thread.currentThread().getId() + "] Error de permisos en una Conexion Nueva: " + e.toString());
                throw new SQLException("ConnectionPool:permisosConexion::SQLException " +
                        e.toString());

            }finally{
            	cstmt2.close();
            }
        }
        return con;
    }


    public Connection verificaConexion() throws SQLException {
        return this.getConnection().getConnection();
    }

    protected Connection checkConnection(Connection con) throws SQLException {
        if (!isNull(this.checkQuery)) {
            PreparedStatement st = null;
            try {
                st = con.prepareStatement(this.checkQuery);
            } catch (SQLException sqle) {
                try {
                    LogsManager.getInstance( ).log(Level.INFO, "id[" + Thread.currentThread().getId() + "] Recupero conexion de base de datos");
                    con.close();
                    con = null;
                    return (this.createConnection());
                } catch (Exception e) {
                    LogsManager.getInstance( ).log(Level.INFO, "id[" + Thread.currentThread().getId() + "] Error intentando recuperar conexion de base de datos");
                    throw new SQLException("ConnectionPool:checkConnection()::BAD CONNECTION COULD NOT BE FIXED");
                }
            } finally {
                try {
                    if (st != null) {
                        st.close();
                    }
                } catch (Exception ex) {
                    LogsManager.getInstance( ).log(Level.SEVERE, "id[" + Thread.currentThread().getId() + "] Error cerrando statement de chequeo, consulte al equipo de desarrollo");
                }
            }
        }
        return con;
    }

    public void reiniciar_conexiones(){
        LogsManager.getInstance( ).log(Level.INFO, "id[" + Thread.currentThread().getId() + "] "+General.obtenerFechaHoraActual(true) + " Se inicia el proceso de reinicio de conexiones");

        try{
            c3poDataSource.close();
            boInicializado=false;

            init();
            
        }catch(Exception error){
         
        	LogsManager.getInstance( ).log(Level.SEVERE, "id[" + Thread.currentThread().getId() + "] "+General.obtenerFechaHoraActual(true) + " No se pudo iniciar el pool Error: " + error.toString());
        }
    }

    public synchronized boolean checkUsedConnections() throws SQLException {
        boolean ret = false;

        try {
            for (int i = 0; i < usedConnections.size(); i++) {
                Connection con = (Connection)usedConnections.get(i);

                if (con.isClosed()) {
                    con = createConnection();

                    availableConnections.add(con);

                    usedConnections.remove(i);

                    ret = true;
                }
            }
        } catch (SQLException e) {

            throw new SQLException("ConnectionPool:checkUsedConnections::Exception " +
                    e.toString());
        }

        return ret;
    }

    public synchronized void closeUsedConnections() {
        closeConnections(usedConnections);
    }

    public synchronized void closeAllConnections() {
        closeConnections(availableConnections);
        closeConnections(usedConnections);
    }

    public synchronized void destroy() {
        this.c3poDataSource.close();

    }

    protected void closeConnections(List<Object> aList) {
        for (int i = 0; i < aList.size(); i++) {
            Connection con = (Connection)aList.get(i);

            try {
                con.close();

            } catch (Exception e) {


            }
        }
    }

    protected boolean isNull(String s) {
        if (s == null || s.equals("") || s.length() < 1) {
            return true;
        }

        return false;
    }

    @Override
    protected void finalize() {
        destroy();
        try {
            super.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUsedConnections(List<Object> usedConnections) {
        this.usedConnections = usedConnections;
    }

    public Hashtable<String,Object> getUsedConnections() {
        return htusedConnections;
    }

    public void setAvailableConnections(List<Object> availableConnections) {
        this.availableConnections = availableConnections;
    }

    public Hashtable<String,Object> getAvailableConnections() {
        return htavailableConnections;
    }

    public static void main(String[] args) {
        
    	ConnectionPool pool = ConnectionPool.getInstance();
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@10.18.1.15:1521:bndes3g";
        String password = "GAMBLE";
        String usuario = "GAMBLE";
        Connection c = null;
        
        try {

                pool = ConnectionPool.getInstance();

                if (!pool.estaInit()){

                	pool.init(driver, url, usuario, password);
                
                }

                pool = ConnectionPool.getInstance();
                
                objConexion objcon = null;
                ResultSet rst = null; 
            	PreparedStatement pst = null;
                
                pool = ConnectionPool.getInstance();
                objcon = pool.getConnection();
                c = objcon.getConnection();
                
                pst = c.prepareStatement("Select * from FalloRecargas");
                rst = pst.executeQuery();
                
                while (rst.next()) {
    				System.out.println(rst.getString(8));
    			}
                
//                System.out.println("usedConnections " + pool.getUsedConnections().size());
//                System.out.println("AvailableConnections " + pool.getAvailableConnections().size());
//                c.close();
                
//                pool.freeConnection(c);
//                System.out.println("usedConnections " + pool.getUsedConnections().size());
                
                System.out.println("AvailableConnections " + pool.getAvailableConnections().size());

            } catch (SQLException e) {
                System.out.println(" SQLException = " + e);
            } catch (Exception e) {
                System.out.println(" Exception = " + e);
            }

        
    }

}

