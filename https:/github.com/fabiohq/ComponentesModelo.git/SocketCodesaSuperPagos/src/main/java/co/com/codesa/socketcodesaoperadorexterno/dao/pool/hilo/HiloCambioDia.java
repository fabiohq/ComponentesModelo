/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.codesa.socketcodesaoperadorexterno.dao.pool.hilo;

import java.util.logging.Level;

import co.com.codesa.socketcodesaoperadorexterno.dao.pool.ConnectionPool;
import co.com.codesa.socketcodesaoperadorexterno.manager.LogsManagerOperadorExterno;
import co.com.codesa.socketcodesaoperadorexterno.socket.ControlIdCase;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.AdminVentavirtual;
import co.com.codesa.socketcodesaoperadorexterno.xml.PathApplication;

public class HiloCambioDia extends Thread{

    boolean seguir=true;
    ConnectionPool pool= null;
    int intdiaActual=0;

    public HiloCambioDia(){
    	
    	ConnectionPool.getInstance();

    	LogsManagerOperadorExterno.getInstance( ).log( Level.INFO, "Obteniendo dia Acual");
        intdiaActual = ConnectionPool.getInstance().getDiaActualBD();
        LogsManagerOperadorExterno.getInstance( ).log( Level.INFO, "==>Dia Actual:"+intdiaActual);
        LogsManagerOperadorExterno.getInstance( ).log( Level.INFO, "==> PathApplication.getInstance(): "+PathApplication.getInstance()); 
    }
    
    @Override
    public void run( ){
    	LogsManagerOperadorExterno.getInstance( ).log( Level.INFO, "==>Revisando cambio de dia");

        while(seguir){

            try
            {
            	//Thread.sleep(3600000); // 60 minutos
            	Thread.sleep(300000);  // 5 minutos
             //   Thread.sleep(30000);   // 30 seg
            	LogsManagerOperadorExterno.getInstance( ).log( Level.INFO,  "Se activa validacion cambio dia ..." );

                if ( intdiaActual != ConnectionPool.getInstance().getDiaActualBD() )
                {
                	LogsManagerOperadorExterno.getInstance( ).info("antes de reinicio de conexiones Dia: "+intdiaActual );
                    ConnectionPool.getInstance().reiniciar_conexiones();

                    LogsManagerOperadorExterno.getInstance( ).log( Level.INFO, "Limpiando cHmadminventa de Dia: "+intdiaActual);
                    AdminVentavirtual.getInstancia().setclearchm();
                    
                    intdiaActual = ConnectionPool.getInstance().getDiaActualBD();
                    ControlIdCase.getInstancia().getInitCaseId();

                    LogsManagerOperadorExterno.getInstance( ).info("despues de reinicio de conexiones Dia: "+intdiaActual );
                }

            }catch( Exception e ){
                System.out.println( "Error en el run del HiloCambioDia, el error es: " + e.toString( ) );
                LogsManagerOperadorExterno.getInstance( ).log( Level.INFO, "Error en el run del HiloCambioDia, el error es: " + e.toString( ));
            }
        }
    }
    
    public static void main(String[] args) {
		
	}
  
}
