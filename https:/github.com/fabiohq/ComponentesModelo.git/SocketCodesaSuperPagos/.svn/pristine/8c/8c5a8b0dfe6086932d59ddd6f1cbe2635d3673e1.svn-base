/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesa.socketcodesaoperadorexterno.utilidades;

/**
 *
 * @author Erick Pulido
 * Clase que realiza la conexion para depurar paquetes en la base de datos.
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDebug {


    public void debug(Connection c, String ip,String puerto){
    
    CallableStatement cstmt1 = null;
    
    Logger.getLogger(getClass().getName()).log(
                        Level.INFO,  "VA A EJECUTAR: Operacion: [ CONEXIONDEBUG ]- PARAMETROS: IP: "+ip+" PUERTO: "+puerto);
    
    
        try {
             cstmt1 =c.prepareCall("{CALL DBMS_DEBUG_JDWP.CONNECT_TCP ( '"+ip+"' , '"+puerto+"')} ");
             //cstmt1 =c.prepareCall("{CALL DBMS_DEBUG_JDWP.CONNECT_TCP ( '"+ip+"' , '"+puerto+"',NULL,NULL,NULL,NULL,2 )} ");
             
             Logger.getLogger(getClass().getName()).log(
                        Level.INFO,  "VA A EJECUTAR: Operacion: [ CONEXIONDEBUG ] ");
            
            cstmt1.execute();
            
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(
                    Level.SEVERE, "Operacion: [ CONEXIONDEBUG ] Error es: " + e.toString());
        }
    
    }
}
