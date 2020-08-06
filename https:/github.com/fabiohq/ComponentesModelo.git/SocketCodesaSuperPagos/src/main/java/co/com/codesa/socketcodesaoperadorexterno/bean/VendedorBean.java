/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.codesa.socketcodesaoperadorexterno.bean;

/**
 *
 * @author mauricio.lopez
 */
public class VendedorBean {
    
    String nombre = "";
    String apellidos = "";
    String fechaInicio = "";
    String grupoVentas = "";
    String centroCosto = "";
    String tipoCeco = "";
    String zona = "";
    String tipoZona = "";
    String loginUsr;
    Object object;
    
    boolean resultado;
    
    public VendedorBean()
    {
        
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getGrupoVentas() {
        return grupoVentas;
    }

    public void setGrupoVentas(String grupoVentas) {
        this.grupoVentas = grupoVentas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoCeco() {
        return tipoCeco;
    }

    public void setTipoCeco(String tipoCeco) {
        this.tipoCeco = tipoCeco;
    }

    public String getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(String tipoZona) {
        this.tipoZona = tipoZona;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getLoginUsr() {
        return loginUsr;
    }

    public void setLoginUsr(String loginUsr) {
        this.loginUsr = loginUsr;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
