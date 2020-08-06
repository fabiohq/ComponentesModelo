/*
 * General.java
 *
 * Created on 27 de abril de 2004, 08:52 PM
 */

package co.com.codesa.socketcodesaoperadorexterno.utilidades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Logger;




import co.com.codesa.socketcodesaoperadorexterno.manager.LogsManagerOperadorExterno;
import co.com.codesa.socketcodesaoperadorexterno.xml.LectorXML;
import co.com.codesa.socketcodesaoperadorexterno.xml.MyDOMParserBean;

/**
 * Clase que maneja funciones generales propietarias para agilizar algunas tareas(ej: formateos)
 * @author  Administrator
 */
public class General {
    
	   public static String obtenerFechaHoraActual(boolean separador){
	        String hora="";
	        String fecha="";
	        Calendar calendario=Calendar.getInstance();
	        
	        if (separador){
	            fecha=(calendario.get(Calendar.DAY_OF_MONTH)<10?"0":"")+calendario.get(Calendar.DAY_OF_MONTH)+"/"+((calendario.get(Calendar.MONTH)+1)<10?"0":"")+(calendario.get(Calendar.MONTH)+1)+"/"+calendario.get(Calendar.YEAR);
	            hora=(calendario.get(Calendar.HOUR_OF_DAY)<10?"0":"")+calendario.get(Calendar.HOUR_OF_DAY)+":"+(calendario.get(Calendar.MINUTE)<10?"0":"")+calendario.get(Calendar.MINUTE)+":"+(calendario.get(Calendar.SECOND)<10?"0":"")+calendario.get(Calendar.SECOND)+","+(calendario.get(Calendar.MILLISECOND)<10?"0":"")+calendario.get(Calendar.MILLISECOND);
	            return fecha+" "+hora;
	        }else{
	            fecha=(calendario.get(Calendar.DAY_OF_MONTH)<10?"0":"")+calendario.get(Calendar.DAY_OF_MONTH)+((calendario.get(Calendar.MONTH)+1)<10?"0":"")+(calendario.get(Calendar.MONTH)+1)+calendario.get(Calendar.YEAR);
	            hora=(calendario.get(Calendar.HOUR_OF_DAY)<10?"0":"")+calendario.get(Calendar.HOUR_OF_DAY);
	            return fecha+"_"+hora;
	        }
	        
	    }
	   
	   public static void registra_error_log_archivo( String strCadena){
	        
	        try{
	            
	            BufferedWriter out = new BufferedWriter(new FileWriter( "erroresadicionales.txt", true));
	            
	            out.write(strCadena.trim()+"\n");
	            
	            out.close ( );
	            
	        }catch (Exception e){
	            
	            e.printStackTrace();
	            
	        }
	        finally{
	        	
	        }
	    }
	   
	   
	    public static String obtenerTraza(Exception ex){
	        try {
	    
	            StackTraceElement[] sl = ex.getStackTrace();
	            StringBuffer trace = new StringBuffer();
	            for (int i =0; i < sl.length; i++) {
	                trace.append( sl[i] + "|");
	            }
	            return "Error "+ ex.toString()+ ":"+ trace.toString();
	        } catch (Exception e){
	            return "Error obteniendo traza";
	        }
	        
	    }
	    
		public static String retornarValorXML(String archivoXML, String nodoBuscar) {
//			String path = PathApplication.getInstance().getRuta();
			String path = "";
			org.w3c.dom.Document doc = null;
			String valor = "";
			
			try {
				path =  archivoXML.replace("//", "/");
				doc = MyDOMParserBean.getDocument(path);
				LectorXML lector = new LectorXML();
				lector.buscarPropiedadXML(doc, "", nodoBuscar);
				valor = lector.getValor();

			} catch (Exception e) {
				System.out.println("Error retornado el valorXML(1) en la clase General, el error es "+ e.toString());
			}
			return valor;
		}
		
		public static void main(String[] args) {
			retornarValorXML("config/configserver.xml", "log_path");
		}
		
		
		
		public static String obtenerFechaHoramilisegundos(boolean separador) {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.S");
			
			Date fechaentrada = new Date();
			String fecha = dateFormat.format(fechaentrada);
			System.out.println("---------------fecha:"+fecha);
			return fecha;

		}
		
		public static Date StringToDate(String fecha,String caracter,int op){ 
			String formatoHora="HH:mm:ss.S"; 
			String formato="yyyy"+caracter+"MM"+caracter+"dd"+" "+formatoHora; 
			if(op==1) // 
				formato="yyyy"+caracter+"dd"+caracter+"MM"+formatoHora; 
			else if(op==2) formato="MM"+caracter+"yyyy"+caracter+"dd"+formatoHora; 
			else if(op==3) formato="MM"+caracter+"dd"+caracter+"yyyy"+formatoHora;
			else if(op==4) formato="dd"+caracter+"yyyy"+caracter+"MM"+formatoHora; 
			else if(op==5) formato="dd"+caracter+"MM"+caracter+"yyyy"+formatoHora; 
			//SimpleDateFormat sdf = new SimpleDateFormat(formato, Locale.getDefault()); 
			SimpleDateFormat sdf = new SimpleDateFormat(formato); 
			Date fechaFormato=null; 
			try { 
				sdf.setLenient(false); 
				fechaFormato=sdf.parse(fecha); 
			} catch (ParseException ex) { 
				
			} 
				return fechaFormato;
		}
		
		public static long cantidadTotalSegundos(Calendar fechaInicial ,Calendar fechaFinal){
			Logger logger = LogsManagerOperadorExterno.getInstance();
			long totalsegundos=0;
			logger.info("fechainicial--> "+fechaInicial.getTimeInMillis()+", Hilo: "+Thread.currentThread().getId());
			logger.info("fechafinal--> "+fechaFinal.getTimeInMillis()+", Hilo: "+Thread.currentThread().getId());
			
			logger.info("segundo fecha inicial--> "+fechaInicial.get(Calendar.MINUTE)+":"+  fechaInicial.get(Calendar.SECOND)+", Hilo: "+Thread.currentThread().getId());
			logger.info("segundo fecha final--> "+fechaFinal.get(Calendar.MINUTE)+":"+fechaFinal.get(Calendar.SECOND)+", Hilo: "+Thread.currentThread().getId());
			totalsegundos=((fechaFinal.getTimeInMillis()-fechaInicial.getTimeInMillis())/1000);
			return totalsegundos;
		}
		
		  public static void modificarValorXML(String path,String nombreArchivo, String nombreNodo, String valor){
		        XML.modificarValorXML(path,nombreArchivo,nombreNodo,valor);
		    }
		  
		  
		  public static String retornarError(String resultado) {
				String error = null;
				if (resultado != null) {
					error = "";
					StringTokenizer st = new StringTokenizer(resultado, "=");
					if (st.hasMoreTokens()) {
						error = st.nextToken();
					}
				}
				return error;
			}
		  
		  public static String encriptar(String texto){
		        String textoEncriptado="";
		        try{
		            textoEncriptado=Base64.encode(texto);
		        }catch(Exception e){
		            System.out.println("Error encriptando, el error es: "+e.toString());
		        }
		        return textoEncriptado;
		    }
		    
		    public static String desencriptar(String texto){
		        String textoDesencriptado="";
		        try{
		            byte b[]=Base64.decode(texto);
		            textoDesencriptado=new String(b);
		        }catch(Exception e){
		            System.out.println("Error desencriptando, el error es: "+e.toString());
		        }
		        return textoDesencriptado;
		    }

}