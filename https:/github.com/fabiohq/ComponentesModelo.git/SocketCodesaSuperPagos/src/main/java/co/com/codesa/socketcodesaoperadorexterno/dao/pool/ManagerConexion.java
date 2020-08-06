package co.com.codesa.socketcodesaoperadorexterno.dao.pool;

import java.sql.Connection;

/**
 *
 * @author  tomas
 */
public class ManagerConexion {
    
    Connection conexion;
    boolean estado;
    int indice;
    
    public ManagerConexion() {
    }
    
    public void setConexion(Connection c){
        this.conexion=c;
    }
    
    public void setEstado(boolean estado){
        this.estado=estado;
    }
    
    public void setIndice(int indice){
        this.indice=indice;
    }
    
    public Connection getConexion(){
        return this.conexion;
    }
    
    public boolean getEstado(){
        return this.estado;
    }
    
    public int getIndice(){
        return this.indice;
    }
}
