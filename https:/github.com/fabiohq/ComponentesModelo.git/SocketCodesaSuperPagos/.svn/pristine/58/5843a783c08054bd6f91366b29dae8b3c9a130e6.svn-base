/*
 * DatosTrama.java
 *
 * Created on 29 de abril de 2004, 07:18 PM
 */
package co.com.codesa.socketcodesaoperadorexterno.dao.general;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.bean.ConfigConexionTrama;
import co.com.codesa.socketcodesaoperadorexterno.bean.ServicioBean;
import co.com.codesa.socketcodesaoperadorexterno.dao.OperadorExternoDAO;
import co.com.codesa.socketcodesaoperadorexterno.dao.ValidadorOtrosServicios;
import co.com.codesa.socketcodesaoperadorexterno.interfaces.IOperadorExternoDAO;
import co.com.codesa.socketcodesaoperadorexterno.interfaces.IValidadorOtrosServicios;
import co.com.codesa.socketcodesaoperadorexterno.manager.LogsManagerOperadorExterno;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.General;

/**
 * 
 * @autor: Jorge Hugo Ospina
 * @Modificado: Carlos Julio Hernandez
 * @Modificado: Isaias Lopez
 * @Modificado: Carlos Ruiz 15/05/2006 IDENTACION
 * @Modificado: Cristian Gomez Ruiz 17/05/2016 Migracion Web Service a socket
 * @Modificado:Fabio Hernandez 15/08/2017 Se unifican acciones consultaconexion, consultavendedor, y generatrama
 */
public class DatosTrama {
	
	public final static int TRANSFER_REGISTRA = 124;//
	public final static int TRANSFER_GENERA = 123;//PETICION DE TRANSFER
	public final static int CONSULTA_CONEXION = 121; // Consulta conexion del proveedor externo
	public final static int CONSULTAR_VALOR_NP = 105;
	public final static int REGISTRA_REINTENTO = 122; // registra el error segun el codigo de la transaccion
	public final static int CONSULTA_PARAM_SIST = 363;
	public final static int REGISTRA_INTENTO_FALLIDO = 125; // registra el error por timeout
	public final static int REGISTRA_INTENTO_FALLIDO_RECARGA = 126;
	
	/*Nueva accion para integrar en uno solo los 
		paso 2 (Consultar conexion)	 
		paso 3 (Consultar vendedor) 
		paso 4 (Obtener trama de envio operador movil)
	*/
	public final static int GENERA_TRAMA_PAQUETES = 1000;
	public final static int GENERA_TRAMA_RECARGAS = 1001;
	public final static int TRANSFER_REGISTRA_RECARGA = 1002;//
	private Object objeto;
	public String datoTramaServerOperMovil;
	private Logger logger = LogsManagerOperadorExterno.getInstance();

	public DatosTrama() {
		super();
	}

	// public synchronized Object action( int accion ) {
	public Object action(int accion) throws Exception,SQLException{
		
		Object objetoRetorna = null;
		switch (accion) {
		
		case GENERA_TRAMA_PAQUETES: {
			
			ConfigConexionTrama configConex = (ConfigConexionTrama) this.objeto;
			IValidadorOtrosServicios validadorOtrosServicios = new ValidadorOtrosServicios();
			objetoRetorna = (ConfigConexionTrama) validadorOtrosServicios.generaTramaPaquetes(configConex);										
			break;
			
		}case GENERA_TRAMA_RECARGAS: {
			
			ConfigConexionTrama configConex = (ConfigConexionTrama) this.objeto;
			IValidadorOtrosServicios validadorOtrosServicios = new ValidadorOtrosServicios();
			objetoRetorna = (ConfigConexionTrama) validadorOtrosServicios.generaTramaRecargas(configConex);										
			break;
			
		}case REGISTRA_INTENTO_FALLIDO: {

			ConfigConexionTrama configConex = (ConfigConexionTrama) this.objeto;
			IOperadorExternoDAO recargaMovil = new OperadorExternoDAO();
			objetoRetorna = (ConfigConexionTrama) recargaMovil.registraIntentoFallido(configConex);
			break;
			
		}case REGISTRA_INTENTO_FALLIDO_RECARGA: {

			ConfigConexionTrama configConex = (ConfigConexionTrama) this.objeto;
			IOperadorExternoDAO recargaMovil = new OperadorExternoDAO();
			objetoRetorna = (ConfigConexionTrama) recargaMovil.registraIntentoFallidoRecarga(configConex);
			break;
			
		}case TRANSFER_REGISTRA:{
 			
 			ConfigConexionTrama configConex = (ConfigConexionTrama) this.objeto;
 			IOperadorExternoDAO recargaMovil = new OperadorExternoDAO(); 			
			objetoRetorna = (ConfigConexionTrama) recargaMovil.transferMovilRegistraRespuesta(configConex);
 			break;

		}case TRANSFER_REGISTRA_RECARGA:{
 			
 			ConfigConexionTrama configConex = (ConfigConexionTrama) this.objeto;
 			IOperadorExternoDAO recargaMovil = new OperadorExternoDAO(); 			
			objetoRetorna = (ConfigConexionTrama) recargaMovil.transferMovilRegistraRecarga(configConex);
 			break;

		}case CONSULTA_PARAM_SIST: {
				
				String strParametro = (String) this.objeto;

				try {

					IOperadorExternoDAO recargaMovil = new OperadorExternoDAO();
					objetoRetorna = recargaMovil.consultarParametroSistema(strParametro);
				} 
				catch (Exception e) {
					objetoRetorna = "E0";
					logger.log(Level.SEVERE,": Problemas Consultado Parmetro Operativo " + e.toString());
				}

				break;
			}case REGISTRA_REINTENTO: {

				ConfigConexionTrama configConex = (ConfigConexionTrama) this.objeto;

				try {
					
					IOperadorExternoDAO recargaMovil = new OperadorExternoDAO();
					objetoRetorna = (ConfigConexionTrama) recargaMovil.registraReintento(configConex);
				} 
				catch (Exception e) {

				logger.log(Level.SEVERE,General.obtenerFechaHoraActual(true)
										+ "==>Error en funcion registraReintento, el error es: "
										+ e.toString() + " hilo actual "
										+ Thread.currentThread().getId());
				}

				break;
			}case CONSULTAR_VALOR_NP: {
				ServicioBean pxsb = (ServicioBean) this.objeto;

				try {
					IValidadorOtrosServicios valida = new ValidadorOtrosServicios();

					objetoRetorna = valida.consultaValorFactura(pxsb);

					pxsb = (ServicioBean) objetoRetorna;

				} catch (Exception e) {

					logger.log(
									Level.SEVERE,
									General.obtenerFechaHoraActual(true)
											+ "==>Error cargando los productos x servicios de datos trama, el error es: "
											+ e.toString());
				}

				break;				
			}
		}
		
		return objetoRetorna;
	}
	
	
	public String getDatoTramaServerOperMovil() {
		return datoTramaServerOperMovil;
	}

	public void setDatoTramaServerOperMovil(String datoTramaServerOperMovil) {
		this.datoTramaServerOperMovil = datoTramaServerOperMovil;
	}


	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}


	public DatosTrama(Object objeto) {
		this.objeto = objeto;

	}
}
