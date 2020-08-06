package co.com.codesa.socketcodesaoperadorexterno.utilidades;

import java.util.concurrent.ConcurrentHashMap;

public class AdminVentavirtual {
	
	
	public static AdminVentavirtual INSTANCIA;
	
    private ConcurrentHashMap<String, Object> chmventavirtual = null;
    
    public AdminVentavirtual(){
    	chmventavirtual = new ConcurrentHashMap<String, Object> ();
    }
    
    public static AdminVentavirtual getInstancia(){
    	if(INSTANCIA==null) INSTANCIA = new AdminVentavirtual();
    	return INSTANCIA;
    		
    }
    
    public boolean removeVentaVirtual(String key){
    	key = key.trim();
    	if(chmventavirtual.containsKey(key)){
    		chmventavirtual.remove(key);
    		return true;
    	}
    	return false;
    }
    
    public Object isAdminvirtual(String key){
    	key = key.trim();
    	Object obj = chmventavirtual.get(key);
    	
    	return obj;
    }
    
    public void addAdminvirtual(String key, String valor){
    	
    	key = key.trim();
    	valor = valor.trim();
    	chmventavirtual.put(key, valor);
    	
    	
    }
    
    public void setNullInstance (){
        INSTANCIA=null;
                
    }
    
    public void setclearchm(){
    	chmventavirtual.clear();
    }
    
    public void replacehm(String key, String valorold){
    	key = key.trim();
    	valorold = valorold.trim();
    	    	
    	chmventavirtual.replace(key, valorold);
    }
    

}
