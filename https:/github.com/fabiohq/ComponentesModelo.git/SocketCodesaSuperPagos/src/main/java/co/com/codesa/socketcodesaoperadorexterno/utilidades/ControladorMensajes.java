package co.com.codesa.socketcodesaoperadorexterno.utilidades;
/**
 * Clase creada para darle manejo a los mensajes retornados por los paquetes los cuales traen el estado el tipo 
 * y esa parte no es necesario mostrarlo al cliente
 * 
 * Estos mensajes son los que se encuentran en la tabla ESTADOSPROVEEDORESRM
 * @author Fabio Hernandez
 *
 */
public class ControladorMensajes {

	private static final String TELEFONO_INCORRECTO = "Telefono incorrecto";
	
	
	public static String getMensaje(String mensaje){
		
		if(mensaje.contains(TELEFONO_INCORRECTO)){return TELEFONO_INCORRECTO;}
		
		return null;
	}
}
