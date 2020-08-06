package co.com.codesa.socketcodesaoperadorexterno.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.general.LogsManager;

public class ThreadServidor implements Runnable{

	Socket s;
	private DataOutputStream outTransac;
	private DataInputStream inTransac;
	Logger logger = LogsManager.getInstance( );

	public ThreadServidor( Socket socket ) throws IOException{
		s = socket;
		inTransac = new DataInputStream( s.getInputStream( ) );
		logger.info( "Socket: " + s + " ABIERTO ");
	}

	public int responder( String respuesta) {
		try{   
			outTransac = new DataOutputStream(s.getOutputStream( ));
			respuesta = respuesta.replace("\n", "");
			respuesta += "\n";
			logger.info( " Socket: " + s + " ==> Trama de respuesta: " + respuesta + " Tamano: " + respuesta.length( ));

			byte b[ ] = respuesta.getBytes( );
			outTransac.write( b );
			outTransac.flush( );
		}catch( Exception e ){
			logger.log( Level.SEVERE, " Socket " + s + "Error respondiendo, el error es: " + e.toString( ) );
			cerrarSocket();
			return -1;
		}finally{
			cerrarSocket();
		}
		return 0;
	}


	public void cerrarSocket() {
		if( s != null ) {
			try{
				s.close( );                
				logger.info( "Socket: "+ s + " CERRADO " );                
				s = null; 
				inTransac=null;
				outTransac=null;
			}catch( Exception e ){
				logger.log( Level.SEVERE, s + " Error cerrando el socket, el error es: " + e.toString( ) );
			}
		}
	}

	@Override
	public void run() {
		try{	
			int indice = 0;
			boolean fin = false;            
			String trama = null;    
			byte b[ ] = new byte[ 2024 ];
			s.setSoTimeout( 60000 );

			while( !fin ) {

				byte trama1 = inTransac.readByte( );

				if( trama1 == 13 || trama1 == 10 ){
					fin = true;
				}else{
					b[ indice ]=trama1;
				}

				trama = new String( b,0,indice ).trim();
				indice++;
			}

			trama = new String( b,0,indice ).trim();
			b = null;

			ServidorProcesa servidorProcesa = new ServidorProcesa();
			Calendar mili = Calendar.getInstance();
			Long milisegun = mili.getTimeInMillis();

			logger.info("Entra proceso--> "+mili.get(Calendar.HOUR)+":"+mili.get(Calendar.MINUTE)+":"+  mili.get(Calendar.SECOND)+"."+mili.get(Calendar.MILLISECOND)+" , Hilo: "+Thread.currentThread().getId());

			String tramaRespuesta = servidorProcesa.procesarTramaTCP(trama, s);
			servidorProcesa = null;

			Calendar mili2 = Calendar.getInstance();
			Long valor =  (mili2.getTimeInMillis()- milisegun)/1000;
			logger.info("segundo fecha inicial--> "+mili.get(Calendar.HOUR)+":"+mili.get(Calendar.MINUTE)+":"+  mili.get(Calendar.SECOND));
			logger.info("segundo fecha final--> "+mili2.get(Calendar.HOUR)+":"+mili2.get(Calendar.MINUTE)+":"+mili2.get(Calendar.SECOND));
			logger.info("Hora termina proceso, diferencia de segundos:"+ valor+" , Hilo: "+Thread.currentThread().getId());

			responder( tramaRespuesta+ "\n");
		}catch( Exception e ){
			e.printStackTrace();
		}finally {
			cerrarSocket();
		}

	}

}

