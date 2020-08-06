/*
 * ResultadoBean.java
 *
 * Created on 30 de marzo de 2004, 10:41
 */

package co.com.codesa.socketcodesaoperadorexterno.xml;

/**
 *
 * @author  Administrador
 */
public class DatosXMLBean {
    
    private String nombreNodo;
    private String valorNodo;
    
    /** Creates a new instance of ResultadoBean */
    public DatosXMLBean() {
    }
 
    public void setNombreNodo(String nombreNodo){
       this.nombreNodo=nombreNodo;
   }
   
   public void setValorNodo(String valorNodo){
       this.valorNodo=valorNodo;
   }
   
   public String getNombreNodo(){
    return this.nombreNodo;
   }
   
   public String getValorNodo(){
    return this.valorNodo;
   }
}
