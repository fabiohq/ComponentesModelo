/*
 * Transacciones.java
 * 
 * Created on 10/01/2008, 11:59:23 AM
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.codesa.socketcodesaoperadorexterno.socket;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.manager.LogsManagerOperadorExterno;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.WebGambleConstants;

/**
 *
 * @author Administrador
 */
public class Transacciones {

    private Hashtable<String,Object> tramasRespuestas;   
    private Hashtable<String,Object> tramasPendientes;
    
    private static String ipconexionadmin;
    private static String puertoconexionadmin;
    private static String usuarioconexionadmin;
    private static String passwordconexionadmin;
    private static String realm;
    
    private static Transacciones INSTANCIA ;

    private  Transacciones() {
        
    	Logger logger = LogsManagerOperadorExterno.getInstance( );
    	
    	try{ 		
    		
        
	    	String rutaPropClientGateway="";
	    	rutaPropClientGateway=(new StringBuilder()).append(WebGambleConstants.getInstancia().getPathPropClientGateway()).toString();
	    	File file=new File(rutaPropClientGateway);
	    	Properties p = new Properties();
	    	
	    	
	    	
	    	if(file.exists())
            {
	    		FileInputStream fInput = new FileInputStream(file);
                p.load( fInput );
	    		
	    		 ipconexionadmin=p.getProperty("ipconexionadmin");
                 puertoconexionadmin=p.getProperty("puertoconexionadmin");
                 usuarioconexionadmin=p.getProperty("usuarioconexionadmin");
                 passwordconexionadmin=p.getProperty("passwordconexionadmin");
                 realm = p.getProperty("realm");
            }
	    	
        }catch(Exception e)
	    {
	            logger.info("Error inicando java.io.File: "+e.getMessage() );
	    } 
    	
    	
    }
    
    
    
    public Hashtable<String,Object> getTramasRespuestas() {
        if(tramasRespuestas == null)
        {
            tramasRespuestas = new Hashtable<String,Object>();
        }
        return tramasRespuestas;
    }

    public void setTramasRespuestas(Hashtable<String,Object> tramasRespuestas) {
        
        this.tramasRespuestas = tramasRespuestas;
    }

    public Hashtable<String,Object> getTramasPendientes() {
        if( tramasPendientes == null)
        {
            tramasPendientes = new Hashtable<String,Object>();
        }
        return tramasPendientes;
    }

    public void setTramasPendientes(Hashtable<String,Object> tramasPendientes) {
        this.tramasPendientes = tramasPendientes;
    }

    public static Transacciones getINSTANCIA() {
        
        if(INSTANCIA == null)
            INSTANCIA = new Transacciones();
        return INSTANCIA;
    }

	public static String getIpconexionadmin() {
		return ipconexionadmin;
	}

	public static void setIpconexionadmin(String ipconexionadmin) {
		Transacciones.ipconexionadmin = ipconexionadmin;
	}

	public static String getPuertoconexionadmin() {
		return puertoconexionadmin;
	}

	public static void setPuertoconexionadmin(String puertoconexionadmin) {
		Transacciones.puertoconexionadmin = puertoconexionadmin;
	}

	public static String getUsuarioconexionadmin() {
		return usuarioconexionadmin;
	}

	public static void setUsuarioconexionadmin(String usuarioconexionadmin) {
		Transacciones.usuarioconexionadmin = usuarioconexionadmin;
	}

	public static String getPasswordconexionadmin() {
		return passwordconexionadmin;
	}

	public static void setPasswordconexionadmin(String passwordconexionadmin) {
		Transacciones.passwordconexionadmin = passwordconexionadmin;
	}

	public static String getRealm() {
		return realm;
	}

	public static void setRealm(String realm) {
		Transacciones.realm = realm;
	}

	
	public static void main(String[] args) {
		Transacciones.getINSTANCIA();
	}
}
