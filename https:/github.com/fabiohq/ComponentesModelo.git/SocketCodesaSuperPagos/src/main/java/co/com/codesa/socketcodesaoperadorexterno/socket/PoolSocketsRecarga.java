/*
 * poolSocketsRecarga.java
 * 
 * Created on 26/10/2007, 01:08:34 PM
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.codesa.socketcodesaoperadorexterno.socket;

import java.util.Hashtable;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.manager.LogsManagerOperadorExterno;

/**
 *
 * @author Administrador
 */
public class PoolSocketsRecarga {
    
    Logger logger = LogsManagerOperadorExterno.getInstance( ); 
    
    public Hashtable htPoolSocketsDisponibles; 
    //private Hashtable htPoolSocketsUsados;
    
    Thread hilo;
   
    
    private static PoolSocketsRecarga INSTANCIA;
    
    // manejra el nro de reintentos generales
    public static int nroReintentoGeneral;

    public SocketClienteWS scWS ;
    
    public static int nroHilos = 0;
    
   

    public static PoolSocketsRecarga getINSTANCIA() {
        
	if ( INSTANCIA == null ){
	     INSTANCIA = new PoolSocketsRecarga();
             nroReintentoGeneral = 0;
        }     
	return INSTANCIA;
        
       
    }
    
    public PoolSocketsRecarga() {        
        super();
        htPoolSocketsDisponibles = new Hashtable();
    }
    
    
    public synchronized SocketClienteWS getSocketOperador( String codigoOperador, int puerto, String direccionIP)throws Exception 
    {      
        
        this.scWS = ( SocketClienteWS )htPoolSocketsDisponibles.get( codigoOperador );

        // Si el objeto no es null es porque esta disponible, pero no quiere decir que este conectado al socket server del operador movil
        if( this.scWS != null )
        {
            // Si esta conectado al server socket se retorna la referencia del socket cliente(verifica que no se haya caido la conexion)
            //if( scWS.scliente.isConnected() )
            
            if( this.scWS.scliente != null && this.scWS.scliente.isConnected() )
            {
                
                return this.scWS;
                
            }else
            {
                // Si esta caida la conexion se intenta recueperar
                try{      
                    logger.info("INICIANDO RE...CONEXION");
                    // Si el obj. esta en la coleccion, pero esta desconectado  se debe remover o colocar nuevamnte el socket cliente conectado
                    //htPoolSocketsDisponibles.remove(codigoOperador);
                    this.scWS = new SocketClienteWS(); 
                    // Se crea la conexion del socket cliente
                    this.scWS.connectSocketClienteWS( direccionIP, puerto);  
                    // se aidiciona el socket cliente a la coleccion
                    htPoolSocketsDisponibles.put( codigoOperador, this.scWS); 
                    
                    return this.scWS;
                    
                }catch( Exception e )
                {
                    //htPoolSocketsDisponibles.remove( codigoOperador);
                    throw new Exception( e.toString() );
                }      
            }
            
        }else
        {
           
            try
            {
                logger.info("INICIANDO CONEXION POR PRIMERA VEZ");
                this.scWS = new SocketClienteWS(); 
                // Se crea el cliente
                this.scWS.connectSocketClienteWS( direccionIP, puerto);
                htPoolSocketsDisponibles.put(codigoOperador, this.scWS);
                // Se adiciona el cliente a la coleccion de sockets usados
                //htPoolSocketsUsados.put( codigoOperador, scWS);
                //se retorna el socket para que sea usado                       
                return this.scWS;

            }catch( Exception e )
            {
                //htPoolSocketsDisponibles.remove( codigoOperador);
                throw new Exception( e.toString() );
            }    
           
        }  
     
     
    }
    
    
    public  void liberarUsoDeSocket(String nitOperadorRM)
    {   
        try{
            
             
            //scWS.scliente.close();
            //this.scWS = ( SocketClienteWS )htPoolSocketsDisponibles.get( nitOperadorRM );
            
            //logger.info(" tamaño tramas pendientes antes "+scWS.tramasPendientes.size());
            logger.info(" tamaño tramas pendientes antes "+Transacciones.getINSTANCIA().getTramasPendientes().size());
            logger.info(" LIBERANDO SOCKET");
            //this.scWS.scliente = null;
            
            //htPoolSocketsDisponibles.put(nitOperadorRM, this.scWS);
            
            htPoolSocketsDisponibles.remove(nitOperadorRM);
            
            //logger.info(" tamaño tramas pendientes despues "+scWS.tramasPendientes.size());
            logger.info(" tamaño tramas pendientes despues "+Transacciones.getINSTANCIA().getTramasPendientes().size());
            
        }catch(Exception e)
        {
            logger.info("PROBLEMAS REINICIANDO EL SOCKET EN PoolSocketsRecarga "+e.getMessage());
        }
        
        //this.htPoolSocketsDisponibles = null;  
        //this.htPoolSocketsDisponibles.remove(nitOperadorRM);
        //this.htPoolSocketsDisponibles = new Hashtable();
    }
    
    
    
}
   


