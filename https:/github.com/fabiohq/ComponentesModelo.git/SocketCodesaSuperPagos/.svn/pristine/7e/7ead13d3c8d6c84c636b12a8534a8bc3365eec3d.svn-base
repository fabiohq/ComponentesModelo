package co.com.codesa.socketcodesaoperadorexterno.dao.pool;

import co.com.codesa.socketcodesaoperadorexterno.general.LogsManager;

import com.mchange.v2.c3p0.AbstractConnectionCustomizer;

import java.sql.*;
import java.util.logging.*;

/**
 *
 * @author user
 */
public class MyConnectionCustomizer extends AbstractConnectionCustomizer{

    public void onAcquire(Connection con, String parentDataSourceIdentityToken) throws java.lang.Exception {


     CallableStatement cstmt1 = null;
     CallableStatement cstmt2 = null;
     String pw = "";

     if(con != null){

           try{
              
                cstmt1 = con.prepareCall( "begin ? := gambledbv10_4.selpasswordgamble( ); end;" );

                cstmt1.registerOutParameter( 1, Types.VARCHAR );

                cstmt1.executeUpdate( );
                pw = cstmt1.getString( 1 );

            } catch (SQLException e1) {

               // if (con != null) con.close();

                LogsManager.getInstance( ).log( Level.INFO, "id[" + Thread.currentThread().getId() + "] Error ejecutando permisos en una Conexion Nueva: " + e1.toString());

                throw new SQLException( "ConnectionPool:createConnection::SQLException: Inconvenientes al consultar permisos de AUDITORIA2" + e1.toString( ) );

            }finally {
            //cierre de conexiones
             cstmt1.close();
            }

             try {
                    LogsManager.getInstance().info("Proceso de auditoria gambledbv10_4.validateonl");
                    cstmt2 = con.prepareCall( "{ CALL gambledbv10_4.validateonl( ?,  ? ) }" );

                    cstmt2.setString( 1, "GAMBLE" );
                    cstmt2.setString( 2, pw );

                    cstmt2.executeUpdate( );

              } catch (SQLException e) {

                 // if (con != null) con.close();

                    LogsManager.getInstance( ).log( Level.INFO, "id[" + Thread.currentThread().getId() + "] Error de permisos en una Conexion Nueva: " + e.toString());
                    throw new SQLException("ConnectionPool:permisosConexion::SQLException " + e.toString());

                }finally {
                    //cierre de conexiones
                     cstmt2.close();
                }

         }

     }
}

