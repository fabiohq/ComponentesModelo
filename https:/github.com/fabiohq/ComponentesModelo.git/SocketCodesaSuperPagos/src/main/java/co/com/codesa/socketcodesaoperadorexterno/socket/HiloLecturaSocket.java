/*
 * HiloLecturaSocket.java
 * 
 * Created on 26/11/2007, 04:16:26 PM
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.codesa.socketcodesaoperadorexterno.socket;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.manager.LogsManagerOperadorExterno;

/**
 *
 * @author Administrador
 */
public class HiloLecturaSocket extends Thread {
    
    Socket scliente;
    DataInputStream fromServer;
    private String tramaRecibida = "";
    int e=0;
    Thread hiloEspera = new Thread();
    SocketClienteWS sc;
    PoolSocketsRecarga pool;
    
    Logger logger = LogsManagerOperadorExterno.getInstance( );

    public HiloLecturaSocket(/*Socket scliente, DataInputStream fromServer*/ SocketClienteWS sc/*, PoolSocketsRecarga pool*/) 
    {
       this.scliente = sc.scliente;
       this.fromServer = sc.fromServer;
       this.sc = this.sc;
       //this.sc = sc;
       //this.pool = pool;
    }
    
 
   public void run()
   {
       boolean boContinuaLeyendo = true;
       logger.info("antes de entrar al while"+ " hilo actual "+Thread.currentThread().getId());
       
       logger.info(" Valor boContinuaLeyendo"+boContinuaLeyendo+ " hilo actual "+Thread.currentThread().getId());
       logger.info(" Tamano tramas pendientes"+Transacciones.getINSTANCIA().getTramasPendientes().size()+ " hilo actual "+Thread.currentThread().getId());
       
      
       //while( boContinuaLeyendo  && sc.tramasPendientes.size() > 0 )
       while( boContinuaLeyendo  && Transacciones.getINSTANCIA().getTramasPendientes().size() > 0 )
       {
            //logger.info("entro al while");
           
            try{
                
                //this.sleep(2000);

                if( this.fromServer == null )
                {
                    this.fromServer = new DataInputStream( this.scliente.getInputStream( ) );
                }

                int indice = 0;
                boolean fin = false;
                byte b[ ] = new byte[ 4048 ];

                // se leen bytes mietras el tamaño de lo
                while( !fin ) {

                    byte trama1 = this.fromServer.readByte( );  
                    //logger.info("bytes leidos"+trama1);
                    // a la variable tam se le resta 1 debido a que indice incia desde cero     
                    if(  trama1 == 13 || trama1 == 10 || trama1 == 3 ) { // 13 = \r    10 = \n    126 = vergulilla    3 = fin de texto
                        logger.info("llego al fin de lectura"+ " hilo actual "+Thread.currentThread().getId());
                        fin = true;  
                        indice = 0;

                    }else
                    {
                        b[ indice ] = trama1;                        
                    }

                    indice++;
                }


                tramaRecibida = new String( b  );

                //logger.info("tramaRecibida del Server de Operador Movil"+tramaRecibida.trim());
                
                String strTRamaRM = tramaRecibida.replace('|',',');                        
                String[] divideTramaRM = strTRamaRM.split(",");
                // Obtengo el estado de la trama que se retorna el server de recarga movil que se encuentar en la posicion 11

                String nroTransaccion = divideTramaRM[2];
                
                
                String idUnitec = divideTramaRM[1]; // actualmente es 200 envion y 210 para reenvio
                //logger.info("nro de transaccion "+nroTransaccion);
                //logger.info("idUnitec "+idUnitec);

                if(idUnitec.equals("201") || idUnitec.equals("211"))
                {
                                     
                   //sc.tramasRespuestas.put( nroTransaccion,tramaRecibida.trim() ); 
                   Transacciones.getINSTANCIA().getTramasRespuestas().put( nroTransaccion,tramaRecibida.trim() ); 
                   logger.info( "metida"+ e++ + "guardado "+tramaRecibida+ " hilo actual "+Thread.currentThread().getId());
                   //logger.info("valor  sc.contadorEnvios ANTES "+ sc.contadorEnvios);
                   
                   //sc.contadorEnvios = sc.contadorEnvios -1 ;
                   
                   //logger.info("valor  sc.contadorEnvios DESPUES "+ sc.contadorEnvios);
                   
                }
                
                //if( sc.tramasPendientes.size() == 0 )
                if( Transacciones.getINSTANCIA().getTramasPendientes().size() == 0 )
                {
                    sc.e =0;
                    break;
                }

                logger.log( Level.SEVERE,"==>trama recibida: " + tramaRecibida.trim()+ " hilo actual "+Thread.currentThread().getId());
                logger.log( Level.SEVERE, "==>tamano trama recibida: " + tramaRecibida.trim( ).length( )+ " hilo actual "+Thread.currentThread().getId());
               

           }catch( Exception e ){

                boContinuaLeyendo = false;
                e.printStackTrace();                   

                logger.log( Level.SEVERE, "Error conectando LEYENDO...: " + e.toString( ) + " hilo actual "+Thread.currentThread().getId());

                if( e.toString( ).indexOf( "Read timed out" ) != -1 || 
                    e.toString( ).indexOf( "Connection timed out" ) != -1 ) { 
                    //cerrarSocket( ); 
                }

                if( e.toString( ).indexOf( "java.io.EOFException" ) != -1 ) {
                    //cerrarSocket( );

                }    
                if( e.toString( ).indexOf( "java.net.SocketException" ) != -1 ) {
                    //cerrarSocket( );

                } 

                if( e.toString( ).indexOf( "Read timed out" ) != -1 || 
                    e.toString( ).indexOf( "Connection timed out" ) != -1 ||
                    e.toString( ).indexOf( "java.net.SocketTimeoutException" ) != -1 ) 
                { 
                      sc.e = 0;                      
                      System.out.println("entro"+e.getMessage());    
                      break;
                }
                //throw new Exception( e.toString( ) );
               
            }
       }
       
       logger.info("saliendo de la lectura por finalizacion de todas las lecturas  contadorEnvios "+sc.contadorEnvios + "  sc.e "+sc.e+ " hilo actual "+Thread.currentThread().getId());
       
       if( sc.contadorEnvios == 0 )
       {
           sc.e = 0;
           
       }
       
   }       
      

}
