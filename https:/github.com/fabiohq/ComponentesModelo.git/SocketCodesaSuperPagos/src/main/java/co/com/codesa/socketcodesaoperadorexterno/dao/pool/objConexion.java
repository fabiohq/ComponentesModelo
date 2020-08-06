package co.com.codesa.socketcodesaoperadorexterno.dao.pool;

/**
 * Clase que representa bean de objConexion
 * @author Administrator
 */
import java.sql.Connection;

public class objConexion {
    
    /** Creates a new instance of objConexion */
    public objConexion( ) {
    }
    
    private String Id;
    private Connection Con;
    private long lTime;
    
    public Connection getConnection( )
    {
        return Con;
    }
    
    public void setConnection( Connection con )
    {
        Con = con;
    }
    
    public String getId( )
    {
        return Id;
    }
    
    public void setId( String id)
    {
        Id = id; 
    }
    
    public long getTime( )
    {
        return lTime;
    }
    
    public void setTime(  long ltime )
    {
        lTime = ltime;
    }
}
