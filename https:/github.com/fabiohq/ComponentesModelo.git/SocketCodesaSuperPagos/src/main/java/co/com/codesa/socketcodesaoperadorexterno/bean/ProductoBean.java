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

public class ProductoBean extends DatosBaseBean {
    
    private String strPuntoVenta = "";
    private String strCodProducto = "";
    private String strNitProveedor = "";
       
    public ProductoBean( ) { }
    
    public void setPtoVenta( String codigo )
    {
        strPuntoVenta = codigo;
    }
    
    public String getPtoVenta( )
    {
        return strPuntoVenta;
    }
    
    public void setCodProducto( String codigo )
    {
        strCodProducto = codigo;
    }
    
    public String getCodProducto( )
    {
        return strCodProducto;
    }
    
    public void setNitProveedor( String strnit )
    {
        strNitProveedor = strnit;
    }
    
    public String getNitProveedor( )
    {
        return strNitProveedor;
    }
    
    
}
