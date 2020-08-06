/*
 * SocketClienteWS.java
 * 
 * Created on 6/09/2007, 04:35:25 PM
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
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.manager.LogsManagerOperadorExterno;
/**
 *
 * @author Administrador
 */
public class SocketClienteWS{
    
    public Socket scliente; 
    //public  Hashtable tramasRespuestas;   
    //public  Hashtable tramasPendientes; 
    
    private DataOutputStream toServer;
    public DataInputStream fromServer;    
    private String tramaRecibida = "";
    public int e;
    
    Thread hiloEspera = new Thread();
    
    Logger logger = LogsManagerOperadorExterno.getInstance( );
    
    //ConsolaCliente consola = null;

    public String getTramaRecibida() {
        return tramaRecibida;
    }

    public void setTramaRecibida(String tramaRecibida) {
        this.tramaRecibida = tramaRecibida;
    }

    public SocketClienteWS() {
         this.e=0;
    }
    
    
    
    public void connectSocketClienteWS( String direccionIp , int puerto/*, String timeOut*/  )throws Exception {
        try{
            logger.info(" CONECTANDO SOCKET SOCKET");
                        
            //this.scliente = new Socket( direccionIp , puerto ); 
            logger.info("antes de conexion a socket Asincrono"+Calendar.getInstance().getTimeInMillis());
            
            this.scliente = new Socket( ); 
            this.scliente.connect( new InetSocketAddress(direccionIp , puerto) , 10000 );
            
            logger.info("despues de conexion a socket Asincrono"+Calendar.getInstance().getTimeInMillis());
                        
        }catch( Exception e ){
            
            logger.log( Level.SEVERE, "Error " + e.toString( ) );           
            
            cerrarSocket( );
            
            if( e.toString( ).indexOf( "Connection refused" ) != -1 ) {
                
                logger.log( Level.SEVERE, "No se pudo conectar al servidor");                
            }
            
            if( e.toString( ).indexOf( "Connection timed out" ) != -1 ) {                
                
                logger.log( Level.SEVERE, "Error recibiendo el socket por timeout");                
            }
            
            if( e.toString( ).indexOf( "java.net.ConnectException" ) != -1 ) {                
                
                logger.log( Level.SEVERE, "Error recibiendo el socket por timeout");                
            }
            
            throw new Exception( e.toString() );
            //throw new Exception( "Model.GAMBLE: NO es posible Conectarse al Server Socket del Operador Movil:  IP "+ direccionIp +" Puerto: "+ puerto +" Exception "+ e.toString( ) );
            
        }
        
    }
    
    // Variable que controla el inico del hilo de lectura de respuestas
    
    public int numMetida = 0;
    public static int contadorEnvios = 0;
    
    public void read_write_socket( String trama, String timeOut, String nroTransaccion)throws Exception{
 
           
            try{                
                // El tiempo de reintento lo define la configuracion del proveedor de Recarga Movil                         
                //scliente.setSoTimeout( Integer.parseInt( timeOut ) *1000 );
                
                logger.info(" IP remoto del CLIENTE "+ scliente.getPort()+ " hilo actual "+Thread.currentThread().getId());
                
                // Cada ves que se escribe se debe incrementrar esta variable
                // y cada ves que se recibe respuesta de decrementa para que 
                // tener un control y poder salir del ciclo de lectura
                

                try{
                    //Enviando

                    if( this.toServer == null )
                    {
                        this.toServer = new DataOutputStream( this.scliente.getOutputStream( ) );
                    
                    }
                    
                    /*if( this.tramasRespuestas == null )
                    {
                        this.tramasRespuestas = new Hashtable();
                    }  
                    
                    if( this.tramasPendientes == null )
                    {
                        this.tramasPendientes = new Hashtable();
                    }*/
                    
                    logger.info("ANTES HORA DE ENVIO TRAMA "+trama+ " hilo actual "+Thread.currentThread().getId());
                    this.toServer.write( trama.getBytes( ) ); 
                    //contadorEnvios ++;
                    //this.tramasPendientes.put( nroTransaccion, trama );
                    Transacciones.getINSTANCIA().getTramasPendientes().put( nroTransaccion, trama );
                    
                    
                    
                    
                    logger.info("BYTES ENVIADOS: "+trama.getBytes( ).length+ " hilo actual "+Thread.currentThread().getId());
                    logger.info("DESPUES HORA DE ENVIO TRAMA "+trama+ " hilo actual "+Thread.currentThread().getId());

                     // Se encarga de la lectura de la respuesta
                     //lectura();
                    
                    
                    logger.info("valor e para inicio de lectura "+ e+ " hilo actual "+Thread.currentThread().getId());
                    
                    if( e == 0 )
                    {     
                        logger.info("inicializando lectura"+ " hilo actual "+Thread.currentThread().getId());
                        e++;
                        HiloLecturaSocket pls = new HiloLecturaSocket( this );
                        pls.start();
                        
                    }
                    

                }catch( Exception e ){

                    e.printStackTrace();                   
                    
                    logger.log( Level.SEVERE, "Error ESCRIBIENDO conectando...: " + e.toString( ) + " hilo actual "+Thread.currentThread().getId());

                    if( e.toString( ).indexOf( "Read timed out" ) != -1 || e.toString( ).indexOf( "Connection timed out" ) != -1 ) { 
                        //cerrarSocket( ); 
                    }

                    if( e.toString( ).indexOf( "java.io.EOFException" ) != -1 ) {
                        //cerrarSocket( );

                    }    
                    if( e.toString( ).indexOf( "java.net.SocketException" ) != -1 ) {
                        //cerrarSocket( );

                    } 
                    throw new Exception( e.toString( ) );
                }  

            }catch( Exception e ){

                logger.log( Level.SEVERE, "Error ESCRIBIENDO enviando y recibiendo, el error es: " + e.toString( ) + " hilo actual "+Thread.currentThread().getId());

                if( e.toString( ).indexOf( "Read timed out" ) != -1 ) {

                    //cerrarSocket();                    
                    logger.log( Level.SEVERE, "Error recibiendo el socket por timeout"  + " hilo actual "+Thread.currentThread().getId());                    

                }

                if( e.toString( ).indexOf( "java.io.EOFException" ) != -1 ) {

                    //cerrarSocket();                    
                    logger.log( Level.SEVERE, "Error de entrada/salida"  + " hilo actual "+Thread.currentThread().getId());              
                    
                }


                if( e.toString( ).indexOf( "Connection refused" ) != -1 ) {

                    //cerrarSocket();
                    logger.log( Level.SEVERE, "No se pudo conectar al servidor"+ " hilo actual "+Thread.currentThread().getId());
                    
                }

                if( e.toString( ).indexOf( "Connection timed out" ) != -1 ) {

                    //cerrarSocket( );
                    logger.log( Level.SEVERE, "Error recibiendo el socket por timeout"+ " hilo actual "+Thread.currentThread().getId());
                    
                }

                throw new Exception( e.toString( ) );
            }
      }
    
    
    public /*synchronized*/ void lectura()throws Exception
    {      
            
        try{               


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

                // a la variable tam se le resta 1 debido a que indice incia desde cero     
                if(  trama1 == 13 || trama1 == 10 || trama1 == 3 ) { // 13 = \r    10 = \n    126 = vergulilla    3 = fin de texto

                    fin = true;  
                    indice = 0;

                }else
                {
                    b[ indice ] = trama1;                        
                }

                indice++;
            }


            tramaRecibida = new String( b  );

            String strTRamaRM = tramaRecibida.replace('|',',');                        
            String[] divideTramaRM = strTRamaRM.split(",");
            // Obtengo el estado de la trama que se retorna el server de recarga movil que se encuentar en la posicion 11

            String nroTransaccion = divideTramaRM[2];
            String idUnitec = divideTramaRM[1]; // actualmente es 200 envion y 210 para reenvio


            if(idUnitec.equals("201") || idUnitec.equals("211"))
            {
               logger.info( "metida"+ numMetida++ + "guardado "+tramaRecibida);
               // clave = nro transaccion y como valor la trama.
                Thread.sleep(2000);
               //tramasRespuestas.put( nroTransaccion,tramaRecibida.trim() );    
               Transacciones.getINSTANCIA().getTramasRespuestas().put( nroTransaccion,tramaRecibida.trim() );     
            }

            logger.log( Level.SEVERE,"==>trama recibida: " + tramaRecibida.trim());
            logger.log( Level.SEVERE, "==>tama�o trama recibida: " + tramaRecibida.trim( ).length( ));

       }catch( Exception e ){

            e.printStackTrace();                   

            logger.log( Level.SEVERE, "Error conectando LEYENDO...: " + e.toString( ) );

            if( e.toString( ).indexOf( "Read timed out" ) != -1 || e.toString( ).indexOf( "Connection timed out" ) != -1 ) { 
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

                  System.out.println("entro"+e.getMessage());    
            }
            //throw new Exception( e.toString( ) );
        }
        
    }
    
    public void cerrarSocket( ) {
        try{
           
            scliente.close( );
        }catch( Exception e ){
            logger.info("problemas cerrando el socket");
        }
    }   
  
    
    public static void main(String args[])
    {
        
        
  
        /*Thread hilo = new Thread();
        long currentMillis = Calendar.getInstance( ).getTimeInMillis( );
        
        try{
          hilo.start();  
          hilo.sleep(7000);
        }catch(Exception e)
        {
            
        }  
        
        
        long currentMillis2 = Calendar.getInstance( ).getTimeInMillis( );
        
        long dif = currentMillis2 -  currentMillis;*/
        
        /*for( int i=0;i<2;i++){
            long horainicio =  Calendar.getInstance( ).getTimeInMillis( );
            while(true)
            {
                long horaActual =  Calendar.getInstance( ).getTimeInMillis( );
                
                if(horaActual-horainicio > 10000)
                {
                    break;   
                }
            }
        }    
        
        long val = 20000;
        
        if( val > Integer.parseInt("30")*1000 )
        {
            long v = val- (Integer.parseInt("30")*1000);
            System.out.println("valor es: "+  v);
               
        }*/
        
        
  
        
//        new Thread(){
//            
//            
//          public void run()
//          {
//            SocketClienteWS sc = new SocketClienteWS();  
//            try{               
//                //sc.start();
//                sc.connectSocketClienteWS( "10.10.1.149", 9000);        
//                //String trama = sc.getTramaRecibida();
//            }catch( Exception e )
//            {
//                System.out.println( e.getMessage());
//            }
//
//            long horaInicioEjecucion=0;
//
//            try{
//                 horaInicioEjecucion = Calendar.getInstance( ).getTimeInMillis( );
//                sc.read_write_socket("100|100|42|12345|1082|gamble|3162377419|2000|20071027|100115|1|"+"\n","100", "2");        
//                //String tramaRecibe = sc.getTramaRecibida();
//
//            }catch( Exception e )
//            {
//                System.out.println( e.getMessage());
//
//            }
//            try{
//                while( sc.tramasRespuestas.size() >0  ||   ((Calendar.getInstance( ).getTimeInMillis( ) - horaInicioEjecucion )  <  15000)  
//                       /*(Integer.parseInt( "30" ) * 1000)*/  )
//                {
//                    Thread.sleep(1000);
//                    long dif = Calendar.getInstance( ).getTimeInMillis( ) -horaInicioEjecucion;
//                    System.out.println("diferencia "+dif);
//                    try{
//                       //Thread.sleep(2000);
//                       String tramaRecibida = (String)sc.tramasRespuestas.get( "42" );
//
//                       long horaActual =  Calendar.getInstance( ).getTimeInMillis( );
//
//                       System.out.println("problemas");
//
//                       //tramaRecibida = "";
//
//                       if(  tramaRecibida != null) // ! tramaRecibida.equals("") ||
//                       {
//                           break ;
//                       }
//                    }catch(Exception e)
//                    {
//                        System.out.println("catch");
//                    }
//                    
//                } 
//            }catch(Exception e)
//            {
//                
//            }    
//        
//            System.out.println("va para reintento");
//          }
//        }.start();
        
       
//        while ( 1==1 || 1==2)
//        {
//            System.out.println("problemas");
//        }
        
        //System.out.println("saleeee");
        
        /*String cadena = "\r";
        System.out.println((int) cadena.charAt(0));
        
        //System.out.println((int) cadena.charAt(0));

        
        
        //Lo de arriba devuelve tu 104...

        int c = 0;
        for (int i = 0; i < cadena.length(); i++) {
            c += (int)cadena.charAt(i);
        }
        System.out.println(c);
        
        String cadena2 = "";
       for (int i = 0; i < cadena.length(); i++) {
            c += (int)cadena.charAt(i);
        }*/
        


        /*String frase1 = "hola|mundo||222"; 
        
        String[] prueba = frase1.split("|");

        System.out.println("Se han encontrado: " + frase1.split("_").length + " palabras"); 
        System.out.println(" - Palabra 1: " + frase1.split("_")[0]); 
        System.out.println(" - Palabra 2: " + frase1.split("_")[1]); 

        String frase2 = "hola.mundo"; 

        System.out.println("Se han encontrado: " + frase2.split(".").length + " palabras"); 
        System.out.println(" - Palabra 1: " + frase2.split(".")[0]); 
        System.out.println(" - Palabra 2: " + frase2.split(".")[1]); */
 

   
    }
    
    
    

}
