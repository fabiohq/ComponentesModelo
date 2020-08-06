package co.com.SuperPagos.wsREST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import co.com.SuperPagos.utilidades.OmitirCertificado;
import co.com.SuperPagos.utilidades.Utilidades;
public abstract class AbstractWsREST {
	protected  int codigo = 0;
	protected  Object venta(String URL,int timeOut,String jSon, String key)throws Exception{
		StringBuffer respuesta = new StringBuffer();
		HttpURLConnection conn = null;
		try {
			
			
			OmitirCertificado.omitir();
			
			
			
			URL url = new URL(URL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(timeOut);
			conn.setReadTimeout(timeOut);
	        conn.setRequestMethod(Utilidades.METODO_POST);
	        conn.setRequestProperty(Utilidades.CONTENT_TYPE, Utilidades.APPLICATION_JSON);
	        if(!key.equals("")){
	        	String basicAuth = "Bearer " + key;
	        	conn.setRequestProperty("Authorization", basicAuth);
	        }
	        
	        OutputStream os = conn.getOutputStream();
	        os.write(jSon.getBytes());
	        os.flush();
	        

	        if (conn.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED){
	            codigo = conn.getResponseCode();
	            String aut = "Problemas autenticando con el operador SUPERPAGOS";
	            return aut;
	        }
	        
	        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK){
	            throw new Exception("ERROR NO SE OBTUVO RESPUESTA DE PLATAFORMA SUPERPAGOS: HTTP error code : "+conn.getResponseCode() +""+ jSon);
	        }

	        BufferedReader br = new BufferedReader(new InputStreamReader(
	            (conn.getInputStream())));

	        String output;
	        
	        while ((output = br.readLine()) != null){
	        	respuesta.append(output);
	        }        
	        codigo = conn.getResponseCode();
	        conn.disconnect();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}finally {
			if(conn != null){
				conn.disconnect();
			}			
		}
								
		return respuesta.toString();
	}		
	
	
	
	/*
	 * public void main(String[] args) throws Exception {
	 * 
	 * RequestLogeo rq = new RequestLogeo(); Gson gson = new Gson(); String json =
	 * "";
	 * 
	 * rq.setUsername("codesa"); rq.setPassword("123456");
	 * rq.setSecretKey("GQUEKNWTDPJI3XLIO4SFC5BPHQ4W2L2N");
	 * rq.setValidateIp("true"); rq.setWithExpiration("false"); json =
	 * gson.toJson(rq);
	 * 
	 * String prueba = (String)
	 * venta("https://testing.refacil.co/api/v1/auth/login", 60000, json,"");
	 * 
	 * System.out.println(prueba);
	 * 
	 * 
	 * }
	 */
	
	
}