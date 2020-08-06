package co.com.SuperPagos.comandos;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.google.gson.Gson;

import co.com.SuperPagos.Request.RequestLogeo;
import co.com.SuperPagos.Response.ResponseLogeo;
import co.com.SuperPagos.wsREST.AbstractWsREST;



public class CmndLogeo extends AbstractWsREST{
	public String obtenerParametro(String url,String timeout,String usuario,String punto,String puntokey,String valip, String expiration,Logger logger, long idHilo)throws Exception{

		String trama = null;
		String json = null;
		try {
	        logger.info("id[" + idHilo + "] Se va a ejecutar operacion logeo con el operador SUPERPAGOS ");
			ResponseLogeo res = null;
			String compurl = "/auth/login";
			
			
			
			Gson gson = new Gson();
			RequestLogeo rq = new RequestLogeo();
			 
			 rq.setUsername(usuario);
			 rq.setPassword(punto);
			 rq.setSecretKey(puntokey);
			 rq.setValidateIp(valip);
			 rq.setWithExpiration(expiration);
			 
			 json = gson.toJson(rq);
			 int timeout2 = Integer.parseInt(timeout);
			 timeout2 = timeout2 * 1000;
			 logger.info("id[" + idHilo + "] Se va a ejecutar operacion logeo con el operador SUPERPAGOS " + json);

			 String respuesta = (String) venta(url+compurl, timeout2, json,"");
			 res = gson.fromJson(respuesta, ResponseLogeo.class);
			 
			 logger.info("id[" + idHilo + "] Se obtuve respuesta del logeo por parte del operador SUPERPAGOS " + respuesta);
			 
			 if(res.getStatus().equals("Ok")) {
				 trama = "0," +  res.getUser().getToken();
			 }else {
				 trama = "1,Error realizando el logeo contra operador SUPERPAGOS"; 
			 }
			 
			 logger.info("id[" + idHilo + "] Respuesta operacion logeo con el operador SUPERPAGOS " + trama);
			 
		} catch (Exception e) {
			trama = "1,Error realizando el logeo contra operador SUPERPAGOS " + json; 
			throw new Exception(e.getMessage()); 
		}
		return trama;
	}
	
	public  void main(String[] args) throws Exception {
		Logger logger = null;
		long idHilo = 0;
		try {
			CmndLogeo cmndLogeo = new CmndLogeo();
			/*String prueba = obtenerParametro("https://testing.refacil.co/api/v1", "600", "codesa", "123456",
					"GQUEKNWTDPJI3XLIO4SFC5BPHQ4W2L2N", "true", "false", logger, idHilo);*/
			String prueba = cmndLogeo.obtenerParametro("https://testing.refacil.co/api/v1", "300", "codesa", "123456", "GQUEKNWTDPJI3XLIO4SFC5BPHQ4W2L2N"
					, "true", "false", logger, idHilo);
			System.out.println(prueba);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		// System.out.println(prueba);

	}
	

}
