package co.com.SuperPagos.comandos;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import co.com.SuperPagos.Request.RequestPines;
import co.com.SuperPagos.Response.ResponsePines;
import co.com.SuperPagos.wsREST.AbstractWsREST;

public class CmndPines extends AbstractWsREST{

	public String ejecutar(String trama,String claveHost,Logger logger, long idHilo)throws Exception{

		StringBuilder tramares = new StringBuilder();
		CmndLogeo cmndLogeo = new CmndLogeo();
		
		StringBuilder sb = new StringBuilder();
		sb.append("id[");
		sb.append(idHilo);
		sb.append("] Trama recibida desde Codesa SUPERPAGOS PINES: ");
		sb.append(trama);
		pintaLog(logger,sb.toString());		
		sb.setLength(0);
		
		String mensajeres = null;
		String transaidres = null;
		String session = null;
		String json = null;	
		String respuesta = null;
		try {
			String compurl = "/products/sell";
			ResponsePines res = null;
			String logeo = null;
			String error = "";
			Gson gson = new Gson();
	        
			StringTokenizer strResCodesa = new StringTokenizer(trama, "|");
			String url = strResCodesa.nextToken();
			String timeout = strResCodesa.nextToken();
			String usr = strResCodesa.nextToken();
			String ancla = strResCodesa.nextToken();
			String sk = strResCodesa.nextToken();
			String valip = strResCodesa.nextToken();
			String expiration = strResCodesa.nextToken();
			String codproducto = strResCodesa.nextToken();
			strResCodesa.nextToken();
			String vcaChannel = strResCodesa.nextToken();
			String email = strResCodesa.nextToken();
			String vcaCellphone = strResCodesa.nextToken();

			if(strResCodesa.hasMoreTokens()){
				session = strResCodesa.nextToken();
			}else {

				logeo = cmndLogeo.obtenerParametro(url,timeout,usr,ancla,sk,valip,expiration,logger,idHilo);
				StringTokenizer strlog = new StringTokenizer(logeo, ",");
				String valor = strlog.nextToken();
				if(valor.equals("0")){
					session = strlog.nextToken();
				}else {
					error = strlog.nextToken();
				}
				if(error != null && !error.equals("")){
					return error;
				}
			}

			RequestPines rq = new RequestPines();
			
			rq.setProductId(codproducto);
			rq.set_channel(vcaChannel);
			rq.getData().setCellphone(vcaCellphone);
			rq.getData().setEmail(email);
			
			json = gson.toJson(rq);

			sb.append("id[");
			sb.append(idHilo);
			sb.append("] Enviando peticion de Recarga Operador SUPERPAGOS ");
			sb.append(json);		
			pintaLog(logger,sb.toString());		
			sb.setLength(0);
			sb.setLength(0);
			
			int timeout2 = Integer.parseInt(timeout);
			timeout2 = timeout2 * 1000;
			 respuesta = (String) venta(url+compurl, timeout2, json,session);
			 
			 if(codigo == 200){
				res = gson.fromJson(respuesta, ResponsePines.class);
				
				mensajeres = res.getMessage();
				mensajeres=mensajeres.replace(",", "");
				transaidres = res.getData().getTransactionId();
				tramares.append(codigo);
				tramares.append("|");
				tramares.append(mensajeres);
				tramares.append("|");
				tramares.append(transaidres);
				tramares.append("|");
				tramares.append(session);
				tramares.append("|");
				tramares.append(json);
				tramares.append("|");
				tramares.append(respuesta);
			 }else {

				 sb.append("\n\n\n");						
				 pintaLog(logger,sb.toString());		
					sb.setLength(0);
					
				 sb.append("id[");
				 sb.append(idHilo);
				 sb.append("] Se presento ERROR venta de Pines, codigo de error: ");
				 sb.append(codigo);		
				 pintaLog(logger,sb.toString());		
				 sb.setLength(0);

				 sb.append("id[");
				 sb.append(idHilo);
				 sb.append("] Request Pines - ");
				 sb.append(json);		
				 pintaLog(logger,sb.toString());		
				 sb.setLength(0);

				 sb.append("id[");
				 sb.append(idHilo);
				 sb.append("] Response Pines -  ");
				 sb.append(respuesta);		
				 pintaLog(logger,sb.toString());		
				 sb.setLength(0);
				 				 
				 sb.append("id[");
				 sb.append(idHilo);
				 sb.append("] Se realizara nuevamente proceso de autenticacion y venta -  ");
				 sb.append(respuesta);		
				 pintaLog(logger,sb.toString());		
				 sb.setLength(0);
					
				 sb.append("id[");
				 sb.append(idHilo);
				 sb.append("] Intentando proceso nuevamente de autenticacion...");	
				 pintaLog(logger,sb.toString());		
				 sb.setLength(0);
				 
				 logeo = cmndLogeo.obtenerParametro(url,timeout,usr,ancla,sk,valip,expiration,logger,idHilo);
					StringTokenizer strlog = new StringTokenizer(logeo, ",");
					String valor = strlog.nextToken();
					if(valor.equals("0")){
						session = strlog.nextToken();
					}else {
						error = strlog.nextToken();
					}
					
					if(!error.equals("") && error != null){
						return error;
					}
					
					 sb.append("id[");
					 sb.append(idHilo);
					 sb.append("] Intentando proceso nuevamente de venta Pines...");	
					 sb.append(json);
					 pintaLog(logger,sb.toString());		
					 sb.setLength(0);
					 
					respuesta = (String) venta(url+compurl, timeout2, json,session);
					
					if(codigo == 200){
						res = gson.fromJson(respuesta, ResponsePines.class);
						
						mensajeres = res.getMessage();
						mensajeres=mensajeres.replace(",", "");
						transaidres = res.getData().getTransactionId();
						tramares.append(codigo);
						tramares.append("|");
						tramares.append(mensajeres);
						tramares.append("|");
						tramares.append(transaidres);
						tramares.append("|");
						tramares.append(session);
						tramares.append("|");
						tramares.append(json);
						tramares.append("|");
						tramares.append(respuesta);
					}else {
						
						sb.append("id[");
						sb.append(idHilo);
						sb.append("] Se presento nuevamente ERROR Intentando proceso venta Pines codigo de error -  ");
						sb.append(codigo);		
						pintaLog(logger,sb.toString());		
						sb.setLength(0);
												
						sb.append("id[");
						sb.append(idHilo);
						sb.append("] Request Pines -  ");
						sb.append(json);		
						pintaLog(logger,sb.toString());		
						sb.setLength(0);

						sb.append("id[");
						sb.append(idHilo);
						sb.append("] Response Pines -  ");
						sb.append(respuesta);		
						pintaLog(logger,sb.toString());		
						sb.setLength(0);
						
						tramares.append(codigo);
						tramares.append("|");
						tramares.append("problemas de conexion del operador SUPERPAGOS");
						tramares.append("|");
						tramares.append(transaidres);
						tramares.append("|");
						tramares.append(session);
						tramares.append("|");
						tramares.append(json);
						tramares.append("|");
						tramares.append(respuesta);
					}
				 
			 }

			sb.append("id[");
			sb.append(idHilo);
			sb.append("] Respuesta obtenida del operador SUPERPAGOS PINES -  ");
			sb.append(tramares);		
			pintaLog(logger,sb.toString());		
			sb.setLength(0);
		
		} catch (Exception e) {

			sb.append("id[");
			sb.append(idHilo);
			sb.append("] Se presento ERROR en proceso venta Pines -  ");
			sb.append(e.getMessage());		
			pintaLog(logger,sb.toString());		
			sb.setLength(0);
			
			sb.append("id[");
			sb.append(idHilo);
			sb.append("] Request Pines -  ");
			sb.append(json);		
			pintaLog(logger,sb.toString());		
			sb.setLength(0);
			throw new Exception(e.getMessage()); 
		}

		sb.append("id[");
		sb.append(idHilo);
		sb.append("] Respuesta de Pines SUPERPAGOS -  ");
		sb.append(tramares.toString());		
		pintaLog(logger,sb.toString());		
		sb.setLength(0);
		
		return tramares.toString();
	}
	
	public void pintaLog(Logger logger, String mensaje) {
		logger.log(Level.INFO, mensaje);
	}
	
	public static void main(String  args []) {
		CmndPines obj = new CmndPines();
		Logger logger = null;
		long idHilo = 0;
		try {
			String claveHost = "";
			Logger loggger = null;
			long idHil = 0;
			logger = Logger.getLogger(CmndPines.class.getSimpleName());
			String trama = "https://testing.refacil.co/api/v1|35|codesa|123456|GQUEKNWTDPJI3XLIO4SFC5BPHQ4W2L2N|true|false|9|808855|WS|fabio12hq@hotmail.com|3041000003|";
			//0|35|codesa|123456|GQUEKNWTDPJI3XLIO4SFC5BPHQ4W2L2N|true|false|9|808852|WS|fabio12hq@hotmail.com|3041000003|
			             //https://testing.refacil.co/api/v1|35|codesa|123456|GQUEKNWTDPJI3XLIO4SFC5BPHQ4W2L2N|true|false|9|808853|WS|fabio12hq@hotmail.com|3041000003|
			String prueba = obj.ejecutar(trama, claveHost, logger, idHil); 
			System.out.println(prueba);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
