/*
 * DatosBaseBean.java
 *
 * Created on 25 de agosto de 2006, 10:05 PM
 */

package co.com.codesa.socketcodesaoperadorexterno.bean;

/**
 *
 * @author  Carlos Julio Hernandez Cuesta
 */
public class DatosBaseBean {
    
    private String strLogin = "";
    private String strMensaje = "";
    private String strPtoVenta = "";
    private String strDocumento = "";
    private String strResultado = "";
    private String strNitEmpresa = "";
        
    private boolean boEstado = false;
        
        
    public DatosBaseBean( ){ }
   
    public void setNitEmp( String strnitemp )
    {
        strNitEmpresa = strnitemp;
    }
    
    public String getNitEmp( )
    {
        return strNitEmpresa;
    }
    
    public void setResultado( String strresultado )
    {
        strResultado = strresultado;
    }
    
    public String getResultado( )
    {
        return strResultado;
    }
    
    public void setEstado( boolean boestado )
    {
        boEstado = boestado;
    }
    
    public boolean getEstado( )
    {
        return boEstado;
    }
    
    public void setMensaje( String strmensaje )
    {
        strMensaje = strmensaje;
    }
    
    public String getMensaje( )
    {
        return strMensaje;
    }
    
    public void setLogin( String strlogin ){
        strLogin = strlogin;
    }
    
     public String getLogin(  ){
        return strLogin;
    }
     
    public void setDocumento( String strdcto ){
        strDocumento = strdcto;
    }
    
     public String getDocumento(  ){
        return strDocumento;
    } 
}
