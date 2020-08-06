/*
 * SocketClienteWSSincrono.java
 * 
 * Created on 15/11/2007, 03:02:47 PM
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.codesa.socketcodesaoperadorexterno.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Calendar;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.manager.LogsManagerOperadorExterno;

/**
 *
 * @author Administrador
 */
public class SocketClienteWSSincrono {

    Socket scliente ;
    private DataOutputStream toServer;
    private DataInputStream fromServer;
    String tramaRecibida = null;
    
    Logger logger = LogsManagerOperadorExterno.getInstance( );
    
    public SocketClienteWSSincrono() {
    }
    
    
    public String escribe_lee_SocketClient( String trama, String ip, String puerto, String timeOut, long tiempollegada ) throws Exception
    {       
        try
        {   
            String ipGenerica = ip;
            String puertoGenerico = puerto;
//            long tiempoantes;
            
            scliente = new Socket();
            
            trama = trama+"  ";//Se le añaden 2 espacios en blanco para indicar salto de linea en el operador
            
            logger.info("Tiempo antes de conexion con trama: "+trama + " tiempo Inicio"+ Calendar.getInstance().getTimeInMillis()+ " hilo actual "+Thread.currentThread().getId());
            //scliente = new Socket( ipGenerica, Integer.parseInt( puertoGenerico ) ); 
            // Aplica un tiempo de 10 segundo de time out para esperar conexion
            //scliente.connect( scliente.getLocalSocketAddress() , 10000);
            
//            long paramTime = Long.valueOf(WebGambleConstants.getInstancia().getTimeRecargaOperador());
//            tiempoantes = System.currentTimeMillis();
//            logger.info("---------- Validacion de la diferencia tiempo llegada - tiempo salida -----------------"+ System.currentTimeMillis()+ " hilo actual "+Thread.currentThread().getId());
//            logger.info("tiempos para realizar la diferencia: Tiempo Llegada = "+tiempollegada+"Tiempo Salida= "+tiempoantes);
//            
//            long resultadoDif = Math.abs(tiempoantes - tiempollegada);
//            logger.info("Valor del resultado de la diferencia : "+resultadoDif);
//            paramTime = paramTime*1000;
//            logger.info("Valor del parametro en milisegundos : "+paramTime);
//            
//            if( resultadoDif > paramTime){
//            	String errordiferencia = null;
//            	logger.info("La diferencia del tiempo es mayor al parametro: "+paramTime +" para la trama : "+trama+" "+ Calendar.getInstance().getTimeInMillis()+ " hilo actual "+Thread.currentThread().getId());
//            	return errordiferencia;
//            }
           // lognovedades.info("La diferencia del tiempo es menor al parametro: "+paramTime +"para la trama : "+trama+" "+ Calendar.getInstance().getTimeInMillis()+ " hilo actual "+Thread.currentThread().getId());
//            logger.info("---------- Termina Validacion de la diferencia tiempo llegada - tiempo salida -----------------"+ Calendar.getInstance().getTimeInMillis()+ " hilo actual "+Thread.currentThread().getId());
            
            int timeoutmil = Integer.parseInt(timeOut) * 1000;
            scliente.connect( new InetSocketAddress(ipGenerica, Integer.parseInt( puertoGenerico )) ,timeoutmil );
            
            logger.info("Tiempo despues de conexion con trama: "+trama +" tiempo Fin"+ Calendar.getInstance().getTimeInMillis()+ " hilo actual "+Thread.currentThread().getId());
            scliente.setSoTimeout( timeoutmil );
            logger.info("Timeout del cliente socket codesa movistar que se intenta conectar al socket del operador : "+scliente.getSoTimeout());
            
            try{                
                toServer = new DataOutputStream( scliente.getOutputStream( ) );
                fromServer = new DataInputStream( scliente.getInputStream() );
                
                logger.info("trama a enviar al SOCKET del operador: "+trama+ " hilo actual "+Thread.currentThread().getId());
                //Enviando                
                toServer.writeUTF(trama);
                logger.info("bytes enviados "+ trama.length()+ " hilo actual "+Thread.currentThread().getId());
                
                
                int indice = 0;
                boolean fin = false;                
                byte b[ ] = new byte[ 4048 ];
                
                byte bTamano1[ ] = new byte[ 1 ];
                
                int caracter = fromServer.read();
                
                // Se lee el primer byte
                bTamano1[0] = this.fromServer.readByte( );                  
                
                logger.info("bytes enviados "+ trama.length()+ " hilo actual "+Thread.currentThread().getId());
                  
               /* Se obtiene el tamano de bytes el cual pueden ser leidos
                */                
                int tam =  fromServer.available();
                
                logger.info("tamano de la trama"+tam+ " hilo actual "+Thread.currentThread().getId());

                // se leen bytes mietras el tamaño de lo
                while( !fin ) {

                    byte trama1 = this.fromServer.readByte( );  
                    
                    // a la variable tam se le resta 1 debido a que indice incia desde cero     
                    if( indice  == tam - 1  ) { // 13 = \r    10 = \n    126 = vergulilla    3 = fin de texto
                    	
                        fin = true;                        
                        b[ indice ] = trama1;
                        indice = 0;
                        
                    }else
                    {
                        b[ indice ] = trama1;                        
                    }

                    indice++;
                }
                
                tramaRecibida = new String( b );
                tramaRecibida = tramaRecibida.trim();
                
                logger.info("trama recibida en el socket "+tramaRecibida+ " hilo actual "+Thread.currentThread().getId());
                System.out.println(tramaRecibida);
                
            }catch( Exception e ){
                e.printStackTrace();                
                
                if( e.toString( ).indexOf( "Read timed out" ) != -1 || e.toString( ).indexOf( "Connection timed out" ) != -1 ) 
                {
                    logger.info("problemas con Time Out del socket con trama: "+ trama + "Tiempo Transcurrido "+ Calendar.getInstance().getTimeInMillis()+" Excepcion es: "+ e.getMessage()+ " hilo actual "+Thread.currentThread().getId());
                    cerrarSocket( );
                   
                }
                
                if( e.toString( ).indexOf( "java.io.EOFException" ) != -1 ) {
                    logger.info("problemas con time out del socket con trama: "+ trama + "Tiempo Transcurrido "+ Calendar.getInstance().getTimeInMillis() +" Excepcion es: "+ e.getMessage()+ " hilo actual "+Thread.currentThread().getId());
                    cerrarSocket( );
                }else
                {
                    logger.info(" excepcion Otro tipo con trama : "+ trama + "Tiempo Transcurrido "+ Calendar.getInstance().getTimeInMillis() +" Excepcion es: "+ e.getMessage()+ " hilo actual "+Thread.currentThread().getId());
                    cerrarSocket( );
                }
                
                throw new Exception( e.toString( ) );
            }             
            
        }catch( Exception e ){
            
            e.printStackTrace();
            cerrarSocket( );
            
             throw new Exception( e.toString( ) );
            
        }finally{
            cerrarSocket( );
        }
        
        return tramaRecibida.trim();
    }
    
    public void cerrarSocket( ) {
        
        try{
        
            scliente.close( );
        }catch( Exception e ){
           logger.info("problemas cerrando el socket "+e.getMessage());
        }
    }
    
    
    public static void main(String args[])
    {
        try{
//            char var = 03;
//            SocketClienteWSSincrono sc = new SocketClienteWSSincrono();
//         
//            String trama = "ISO_GRCODESA0800822000010000000004000000000000000222113651"
//                    + "00000003067"
//                  + "301  ";
//            
//            sc.escribe_lee_SocketClient(trama, "190.13.96.103", "9090", "40",30); 
        	
        	
        	//MMDDHHMMSS
        	
        	String tramaConectividad = "ISO_GRCODESA"
        			+ "0800"
        	        + "8220000100000000"
        	        + "0400000000000000"
        			+ "?1"//hora de transmision MMDDHHMMSS
        			+ "00000003067"
        			+ "301";
        	
        	Calendar calendar = Calendar.getInstance();
        	
        	
        	String fecha = String.format("%02d%02d%02d%02d%02d", 
                	calendar.get(Calendar.MONTH)+1,
                	calendar.get(Calendar.DATE),
                	calendar.get(Calendar.HOUR_OF_DAY),
                	calendar.get(Calendar.MINUTE),
                	calendar.get(Calendar.SECOND)
        			);
        	
        	tramaConectividad = tramaConectividad.replace("?1", fecha);
        	
        	System.out.println("trama conectividad "+tramaConectividad);
        	
        	System.out.println("Fecha "+fecha);
        	
        	System.out.println(
                	calendar.get(Calendar.MONTH));
        	
        	System.out.println(
                	calendar.get(Calendar.DATE));
        	
        	System.out.println(
                	calendar.get(Calendar.HOUR_OF_DAY));
        	
        	System.out.println(
                	calendar.get(Calendar.MINUTE));
        	
        	System.out.println(
                	calendar.get(Calendar.SECOND));
        }catch(Exception e)
        {
            System.out.println("problemas: "+e.getMessage());
        }
    }
}
    
    









