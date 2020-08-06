package co.com.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;

import co.com.codesa.socketcodesaoperadorexterno.bean.ConfigConexionTrama;
import co.com.codesa.socketcodesaoperadorexterno.dao.general.DatosTrama;

public class TestConfiguracion {

	final String HOST = "10.11.6.9";
	//final String HOST = "10.11.5.3";
	final int PUERTO=9002;
	Socket sc;
	DataOutputStream mensaje;
	DataInputStream entrada;
	private static boolean blControlarTramas = false;
	
	public void initClient(){


        BufferedReader in = null;
        PrintWriter out = null;
        String respuesta = null;        
        Socket socket = null;
        try {
        	int numSerie = 00000400102;
        	for(int i = 0; i< 100; i++){
        		
        		//numCerie = 0000000000+i;
        		
        		
        		//990,CV14796525,800227487-3,19,26,3008705802,1000,BBB,0000000001,FFF,0000000000,*,*,*,*,*,*,*,3180,800249355-1,14796525
            	//990,CV14796525,800227487-3,19,26,3008705802,1000,BBB,0000000001,FFF,0000000000,*,*,*,*,*,*,*,3180,800249355-1,14796525
            	String tramaEnvioCodesa ="991,CV14796525,800800900-8,9701,9801,3336106634,1,BBB,"+numSerie+",FFF,0000000000,*,*,*,*,*,*,*,3180,800249355-1,14796525";
            	tramaEnvioCodesa ="9991,CV14796525,800800900-8,9701,9801,3336106634,1,BBB,"+numSerie+",FFF,0000000000,*,*,*,*,*,*,*,3180,800249355-1,14796525,145698,14796524";
            	tramaEnvioCodesa ="990,CV12347,800800990-5,852,854,3041000003,1000,BBB,0000000001,FFF,0000000000,*,*,*,*,*,*,*,3180,800249355-1,12347";
            	//tramaEnvioCodesa ="9990,CV14796525,800800900-5,9707,9821,3008705802,2000,BBB,0000000001,FYU,0000000000,*,*,*,*,*,*,*,3180,800249355-1,14796525,145698,14796524";                
            	socket = new Socket(HOST, PUERTO);
                socket.setSoTimeout(35000);
                out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
              //tramaClaro = "990,CV199912,800153993-7,8,34," + numeroCelular + ",5000,BBB,0,KGH,0000003339,*,*,*,*,*,*,*,1083,12345,199912";
                out.println(tramaEnvioCodesa);
                out.flush();

                respuesta = in.readLine();
                try {
                	Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
                
        	}
        	
        	
                       
        }
        catch (SocketTimeoutException ex){
            ex.printStackTrace();
        }
        catch (IOException ex) {
        	System.out.println(""+ex.getMessage());
        }

        finally {

            try {
                
                 if (socket != null) {

                    socket.close();
                }
                in.close();
                out.close();

                
            } catch (IOException ex) {
                 
            }catch(NullPointerException ex){
                
            }

        }

	}
	
	
	
	
	public void initClient(String HOST, int PUERTO){


        BufferedReader in = null;
        PrintWriter out = null;
        String respuesta = null;

        

         Socket socket = null;
        try {
        	int numSerie = 00000200100;
        	//for(int i = 0; i< 50; i++){
        		
        		//numCerie = 0000000000+i;
        		
        		
        		//990,CV14796525,800227487-3,19,26,3008705802,1000,BBB,0000000001,FFF,0000000000,*,*,*,*,*,*,*,3180,800249355-1,14796525
            	//990,CV14796525,800227487-3,19,26,3008705802,1000,BBB,0000000001,FFF,0000000000,*,*,*,*,*,*,*,3180,800249355-1,14796525
            	String tramaEnvioCodesa ="9990,CV14796524;,800227487-3,393,393,3008705802,1000,BBB,0000000003,FFF,0000000000,*|@34-64-A9-33-27-D9";
            	//tramaEnvioCodesa ="9990,CV14796525,900333888-8,551,551,5555555002,10000,BBB,"+numSerie+",FFF,0000000000,*,*,*,*,*,*,*,3180,800249355-1,14796525,145698,14796524,3181";
            	//tramaEnvioCodesa ="990,CV1638,900800700,1927,1927,3001551,5000,BBB,0000000001,DFN,0000000567,*,*,*,*,*,*,*,1307,860026182-5,1638";
            	//tramaEnvioCodesa ="9990,CV300123,900800700,1927,1927,5555555002,5000,BBB,0803962410,FYU,0000000020,*,*,*,*,*,*,*,1099,860026182-5,300123,0803962410,300123";
                socket = new Socket(HOST, PUERTO);
                socket.setSoTimeout(3000);
                out = new PrintWriter(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
              //tramaClaro = "990,CV199912,800153993-7,8,34," + numeroCelular + ",5000,BBB,0,KGH,0000003339,*,*,*,*,*,*,*,1083,12345,199912";
                out.println(tramaEnvioCodesa);
                out.flush();

                respuesta = in.readLine();
                try {
                	Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
                
        	//}
        	
        	
                       
        }
        catch (SocketTimeoutException ex){
            ex.printStackTrace();
        }
        catch (IOException ex) {
        	System.out.println(""+ex.getMessage());
        }

        finally {

            try {
                
                 if (socket != null) {

                    socket.close();
                }
                in.close();
                out.close();

                
            } catch (IOException ex) {
                 
            }catch(NullPointerException ex){
                
            }

        }

	}
	
	
	public static String sendSocketClient(String trama) throws Exception{

		Vector<Byte> vecByte;
		byte arrayBytes[];
		DataOutputStream dataOutputStream;
		DataInputStream dataInputStream;
		Byte b;
		String tramaRecibida;
		int tiempo;
		boolean fin;

		
		Socket socket = null;
		String ipSocket = "10.11.6.52";
		String puertoSocket = "9001";
		Integer timeOut = null;  //valor en segundos	
		try {

			// El socket requiere que la trama finalice con un enter
			trama += "\n";
			tiempo = 0;

			// se convierte a milisegundos
			if (timeOut == null) {
				tiempo = 60000; // por defecto 1 minuto
			}
			else{
				tiempo = timeOut.intValue();
				tiempo = tiempo * 1000;
			}
			
			socket= new Socket(ipSocket, Integer.parseInt(puertoSocket));
			socket.setSoTimeout(tiempo);
			
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			String tramaEnvio = encriptarContrasenaTramas(trama);
			
			
			
			dataOutputStream.write(trama.getBytes());
			dataInputStream = new DataInputStream(socket.getInputStream());

			fin = false;
			tramaRecibida = null;

			vecByte = new Vector<Byte>();

			while (!fin) {
				byte trama1 = dataInputStream.readByte();
				if (trama1 == 13 || trama1 == 10) {
					fin = true;
				} else {
					vecByte.addElement(trama1);
				}
			}

			arrayBytes = new byte[vecByte.size()];

			for (int i = 0; i < vecByte.size(); i++) {
				b = vecByte.elementAt(i);
				arrayBytes[i] = new Byte(b.toString()).byteValue();
			}

			tramaRecibida = new String(arrayBytes, 0, (arrayBytes.length));
			
			/** Pintar el log **/
			
			return tramaRecibida;

		} catch (ConnectException connectException) {
			

			throw new Exception("NO SE PUEDE OBTENER CONEXION AL SOCKET IP "
					+ ipSocket + " PUERTO " + puertoSocket);
		} catch (Exception e) {
			
			throw e;
		} finally { /** NC 12769 **/
			if(socket != null){
				socket.close();
				socket = null;
			}
			/** Ticket 433767 - reiniciar la variable **/
			blControlarTramas = false;
		}/** Fin NC 12769 **/
	}

	private static String encriptarContrasenaTramas(String trama){
		
		String tramaRetorno = trama;
		
		try{
		
			String login = "";
	        String macnew = "";
	        int posicion = trama.indexOf("|@");
	        String mac = trama.substring( (posicion<0)?trama.length():posicion);
	        trama = trama.substring( 0 , (posicion<0)?trama.length():posicion);
	        
	        if(!mac.equals("")){
	             macnew = mac.substring(2);
	        }
	
	        StringTokenizer st = new StringTokenizer( trama, "," );
	
	        int codTrama = Integer.parseInt( st.nextToken( ).trim( ) );
	
	        if(codTrama == 112) {
	            String clave = "";
	            String password = "";
	            String codigoPtoVenta = "";
	
	            if( st.hasMoreElements( ) )
	                login    = st.nextToken( ).trim( );
	            if( st.hasMoreElements( ) )
	                password = st.nextToken( ).trim( );
	
	            password = "***";
	            if( st.hasMoreElements( ) )
	                clave    = st.nextToken( ).trim( );
	            if( st.hasMoreElements( ) )
	                codigoPtoVenta = st.nextToken( ).trim( );
	             trama = codTrama + "," + login + "," + password + "," + clave + "," + codigoPtoVenta + mac;
	             tramaRetorno = trama;
	             
	        }
	        else if(codTrama == 50){
	
	            String password = "";
	            String serial = "";
	            String resto = "";
	            
	            if( st.hasMoreElements( ) )
	                login    = st.nextToken( ).trim( );
	            if( st.hasMoreElements( ) )
	                password = st.nextToken( ).trim( );
	            if( st.hasMoreElements( ) )
	                serial = st.nextToken( ).trim( );
	
	            while(st.hasMoreElements())
	                resto += "," + st.nextToken().trim();
	
	            password = "***";
	            trama = codTrama + "," + login + "," + password + "," + serial + mac + resto;
	            tramaRetorno = trama;
	            
	        }  else if(codTrama == 903){
	        	
	          	 String password = "";
	               String serial = "";
	               String resto = "";
	               
	               if( st.hasMoreElements( ) )
	                   login    = st.nextToken( ).trim( );
	               if( st.hasMoreElements( ) )
	                   password = st.nextToken( ).trim( );
	               if( st.hasMoreElements( ) )
	                   serial = st.nextToken( ).trim( );

	               while(st.hasMoreElements())
	                   resto += "," + st.nextToken().trim();

        	       password = "***";
	               trama = codTrama + "," + login + "," + password + "," + serial + resto + mac;
	               tramaRetorno = trama;
			} else if (codTrama == 9124) {

				String claveCanal = "";
				String resto = "";

				if (st.hasMoreElements())
					login = st.nextToken().trim();

				if (st.hasMoreElements())
					claveCanal = st.nextToken().trim();

				while (st.hasMoreElements())
					resto += "," + st.nextToken().trim();

				claveCanal = "***";
				trama = codTrama + "," + login + "," + claveCanal + resto + mac;
				tramaRetorno = trama;
			} else if (codTrama == 9121) {
				tramaRetorno = protegerDatosTrama(trama, ",", "*", 2);
			}
	        
		}catch(Exception e){
			
			
		}
        
        return tramaRetorno;
	}

	public static String protegerDatosTrama(String pStrTramaSimple,
			String pStrDelimitador, String pStrReemplazarX,
			Integer... pIntPosDatosProtegidos)// throws Exception
	{
		List<Integer> lstIntPosDatosProtegidos;
		String[] arrStrTrama;
		String strTramaProtegida;
		String strDatoTrama;

		lstIntPosDatosProtegidos = Arrays.asList(pIntPosDatosProtegidos);

		arrStrTrama = pStrTramaSimple.split("\\" + pStrDelimitador);

		strTramaProtegida = "";

		for (int i = 0; i < arrStrTrama.length; i++) {
			strDatoTrama = arrStrTrama[i];

			if (!lstIntPosDatosProtegidos.isEmpty()
					&& lstIntPosDatosProtegidos.contains(i)) {
				strDatoTrama = String.format(
						"%1$-" + strDatoTrama.length() + "s", "").replace(" ",
						pStrReemplazarX);
			}

			strTramaProtegida += strDatoTrama;

			if (i != arrStrTrama.length - 1) {
				strTramaProtegida += pStrDelimitador;
			}
		}

		return strTramaProtegida;
	}

	public static void main(String argas[]){
		String trama =null;
		//LLAMADO A SOCKET OPERADOR
		TestConfiguracion obj = new TestConfiguracion();
		obj.initClient();
		
		//LLAMADO A SOCKET SERVER
		/*try {
			//trama = "990,CV5000132;,830114921-1,13,14,3001234567,2000,BBB,0000000007,LRU,0000000012,*|@DC-4A-3E-47-48-20";
			trama = "9990,CV14796524;,900333888-8,551,551,5555555002,6000,BBB,0000000005,FFF,0000000000,*|@A0-D3-C1-3F-4F-A1";
			//trama = "990,CV14796524;,900333888-8,551,551,5555555002,6000,BBB,0000000013,FFF,0000000000,*|@A0-D3-C1-3F-4F-A1";
			TestConfiguracion.sendSocketClient(trama);
								   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
}
