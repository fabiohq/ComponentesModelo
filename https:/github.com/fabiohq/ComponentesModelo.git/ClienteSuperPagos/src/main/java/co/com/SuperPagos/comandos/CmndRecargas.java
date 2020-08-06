package co.com.SuperPagos.comandos;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import co.com.SuperPagos.Request.RequestRecargas;
import co.com.SuperPagos.Response.ResponseRecargas;
import co.com.SuperPagos.wsREST.AbstractWsREST;

public class CmndRecargas extends AbstractWsREST{
	
	
	public String CmndRecargas(String trama,String claveHost,Logger logger, long idHilo)throws Exception{
		
		StringBuilder tramares = new StringBuilder();
		CmndLogeo CmndLogeo = new CmndLogeo();

		StringBuilder sb = new StringBuilder();
		sb.append("id[");
		sb.append(idHilo);
		sb.append("] Trama recibida desde Codesa SUPERPAGOS Recargas");
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
			ResponseRecargas res = null;
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
			String vca_refId = strResCodesa.nextToken();
			String vca_channel = strResCodesa.nextToken();
			String vca_amount = strResCodesa.nextToken();
			String vca_cellphone = strResCodesa.nextToken();

			if(strResCodesa.hasMoreTokens()){
				session = strResCodesa.nextToken();
			}else {
				logeo = CmndLogeo.obtenerParametro(url,timeout,usr,ancla,sk,valip,expiration,logger,idHilo);
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

			RequestRecargas rq = new RequestRecargas();
			
			
			rq.setProductId(codproducto);
			rq.setAmount(vca_amount);
			rq.set_channel(vca_channel);
			rq.set_refId(vca_refId);
			rq.getData().setCellphone(vca_cellphone);
			
			json = gson.toJson(rq);

			
			sb.append("id[");
			sb.append(idHilo);
			sb.append("] Enviando peticion de Recarga Operador SUPERPAGOS RECARGA");
			sb.append(json);
			pintaLog(logger,sb.toString());		
			sb.setLength(0);
			
			int timeout2 = Integer.parseInt(timeout);
			timeout2 = timeout2 * 1000;
			 respuesta = (String) venta(url+compurl, timeout2, json,session);
			 
			 if(codigo == 200){
				res = gson.fromJson(respuesta, ResponseRecargas.class);
				
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
					sb.append("] Se presento ERROR venta de Recarga, codigo de error ");
					sb.append(codigo);
					pintaLog(logger,sb.toString());		
					sb.setLength(0);
					
				 sb.append("id[");
					sb.append(idHilo);
					sb.append("] Request Recarga - ");
					sb.append(json);
					pintaLog(logger,sb.toString());		
					sb.setLength(0);
					
				 sb.append("id[");
					sb.append(idHilo);
					sb.append("] Response Recarga ");
					sb.append(respuesta);
					pintaLog(logger,sb.toString());		
					sb.setLength(0);
					
				 sb.append("id[");
					sb.append(idHilo);
					sb.append("] Se realizara nuevamente proceso de autenticacion y venta ");
					pintaLog(logger,sb.toString());		
					sb.setLength(0);
					
				 sb.append("id[");
					sb.append(idHilo);
					sb.append("] Intentando nuevamente proceso de autenticacion...");
					pintaLog(logger,sb.toString());		
					sb.setLength(0);
					
				 logeo = CmndLogeo.obtenerParametro(url,timeout,usr,ancla,sk,valip,expiration,logger,idHilo);
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
					sb.append("] Intentando nuevamente proceso de venta recarga.... ");
					sb.append(json);
					pintaLog(logger,sb.toString());		
					sb.setLength(0);
					respuesta = (String) venta(url+compurl, timeout2, json,session);
					
					if(codigo == 200){
						res = gson.fromJson(respuesta, ResponseRecargas.class);
						
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
						sb.append("] Se presento nuevamente ERROR Intentando proceso venta Recarga codigo de error ");
						sb.append(codigo);
						pintaLog(logger,sb.toString());		
						sb.setLength(0);
						
						sb.append("id[");
						sb.append(idHilo);
						sb.append("] Request Recarga ");
						sb.append(json);
						pintaLog(logger,sb.toString());		
						sb.setLength(0);
						
						sb.append("id[");
						sb.append(idHilo);
						sb.append("] Response Recarga ");
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
						tramares.append("|REQUEST: ");
						tramares.append(json);
						tramares.append("|RESPONSE: ");
						tramares.append(respuesta);
					}
				 
			 }
			 
			sb.append("id[");
			sb.append(idHilo);
			sb.append("] Respuesta obtenida del operador SUPERPAGOS RECARGA ");
			sb.append(tramares.toString());
			pintaLog(logger,sb.toString());		
			sb.setLength(0);
		
		} catch (Exception e) {
			
			sb.append("id[");
			sb.append(idHilo);
			sb.append("] Se presento ERROR proceso Venta Recarga ");
			sb.append(e.getMessage());
			pintaLog(logger,sb.toString());		
			sb.setLength(0);
			
			
			sb.append("id[");
			sb.append(idHilo);
			sb.append("] Request Recarga ");
			sb.append(json);
			pintaLog(logger,sb.toString());		
			sb.setLength(0);
			
			throw new Exception(e.getMessage()); 
		}
		
		sb.append("id[");
		sb.append(idHilo);
		sb.append("] Respuesta de recargas SUPERPAGOS RECARGAS ");
		sb.append(tramares.toString());
		pintaLog(logger,sb.toString());		
		sb.setLength(0);
		return tramares.toString();
	}
	public void pintaLog(Logger logger, String mensaje) {
		logger.log(Level.INFO, mensaje);
	}
	
	public  void main(String[] args) throws Exception {
		Logger logger = null;
		long idHilo = 0;
		try {
			String claveHost = "";
			Logger loggger = null;
			long idHil = 0;
			String trama = "https://testing.refacil.co/api/v1|600|codesa|123456|GQUEKNWTDPJI3XLIO4SFC5BPHQ4W2L2N"
					+ "|true|false|49|1|ws|1000|3115525951|";
			String prueba = CmndRecargas(trama, claveHost, loggger, idHil); //CmndRecargas(trama, claveHost, loggger, idHil);
			System.out.println(prueba);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		// System.out.println(prueba);

	}

}
