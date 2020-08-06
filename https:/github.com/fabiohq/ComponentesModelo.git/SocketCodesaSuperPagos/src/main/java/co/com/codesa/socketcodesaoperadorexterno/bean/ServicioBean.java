/*
 * ProductosxSeriviciosBean.java
 *
 * Created on 25 de agosto de 2006, 10:05 PM
 */

package co.com.codesa.socketcodesaoperadorexterno.bean;

/**
 *
 * @author  Carlos Julio Hernandez Cuesta
 */

public class ServicioBean extends ProductoBean{
    private String strSerial = "";
    private String strValor = "0";
    private String strValorMin = "0";
    private String strCodServicio = "";
    
       
    public ServicioBean( ) { }
    
    public void setCodServicio( String strcodigo )
    {
        strCodServicio= strcodigo;
    }
    
    public String getCodServicio( )
    {
        return strCodServicio;
    }
    
    public void setSerial( String strserial )
    {
        strSerial= strserial;
    }
    
    public String getSerial( )
    {
        return strSerial;
    }
    
    public void setValor( String strvalor )
    {
        strValor= strvalor;
    }
    
    public String getValor( )
    {
        return strValor;
    }
    
    public void setValorMin( String strvalormin )
    {
        strValorMin= strvalormin;
    }
    
    public String getValorMin( )
    {
        return strValorMin;
    }
}
