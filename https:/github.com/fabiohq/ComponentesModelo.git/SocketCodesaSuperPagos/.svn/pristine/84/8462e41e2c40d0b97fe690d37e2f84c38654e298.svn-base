package co.com.codesa.socketcodesaoperadorexterno.comandos.funciones;

import java.io.Serializable;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.bean.ConfigConexionTrama;
import co.com.codesa.socketcodesaoperadorexterno.bean.ResultadoBean;
import co.com.codesa.socketcodesaoperadorexterno.bean.ServicioBean;
import co.com.codesa.socketcodesaoperadorexterno.comandos.Action;
import co.com.codesa.socketcodesaoperadorexterno.dao.general.DatosTrama;
import co.com.codesa.socketcodesaoperadorexterno.general.LogsManager;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.General;


public class CmndValorProducto32 implements Action, Serializable {

	private static final long serialVersionUID = 1L;

	private Logger logger = LogsManager.getInstance();
	private ConfigConexionTrama configConexionTramaInstance = new ConfigConexionTrama();

	private long tiempollegada;

	public static final String CODIGO_COMANDO = "32";

	@Override
	public Object execute(Object o) throws Exception {

		String trama = (String) o;
		logger.info("Invoca Trama: 32");
		logger.info("SOCKET TRAMA RECIBIDA: " + trama + " hilo actual "
				+ Thread.currentThread().getId());

		tiempollegada = System.currentTimeMillis();
		logger.info("Tiempo de llegada en milisegundos: " + tiempollegada
				+ ", Hilo :" + Thread.currentThread().getId());
		
		int operacion = 0;
		String login = "";
		String serial = "";
		String nitProv = "";
		String nitEmpresa = "";
		String codServicio = "";
		String codProducto = "";
		String respuesta = "";

		ServicioBean pxsb = new ServicioBean();
		ResultadoBean rxsb;

		StringTokenizer st = new StringTokenizer(trama, ",");
		DatosTrama datos = null;
		
		if (st.hasMoreTokens()) {
			try {
				operacion = Integer.parseInt(st.nextToken().trim());

			} catch (Exception e) {
				operacion = -1;
				System.out.println(General.obtenerFechaHoraActual(true)+ " Error es:" + e.toString() + " ==>trama recibida: "+ trama);
			}
		}

		if (st.hasMoreElements()) {
			login = st.nextToken().trim();
			nitProv = st.nextToken().trim();
			codServicio = st.nextToken().trim();
			codProducto = st.nextToken().trim();
			serial = st.nextToken().trim();

			/*
			 * Manuel Velez: al no ser usada la variable "nitEmpresa" se hace
			 * este condicional para que no falle y prosiga la ejecución
			 */
			if (st.hasMoreTokens()) {
				nitEmpresa = st.nextToken().trim();
			}
		}

		pxsb.setLogin(login);
		pxsb.setSerial(serial);
		pxsb.setNitProveedor(nitProv);
		pxsb.setCodServicio(codServicio);
		pxsb.setCodProducto(codProducto);
		datos = new DatosTrama(pxsb);

		pxsb = (ServicioBean) datos.action(DatosTrama.CONSULTAR_VALOR_NP);

		if (pxsb.getEstado()) {
			respuesta = "32,0," + login + "," + pxsb.getValor() + ","
					+ pxsb.getValorMin();

		} else {
			respuesta = "32,1," + login + ","
					+ General.retornarError(pxsb.getResultado());
		}

		logger.info("usuario: " + login + " TRAMA RESPUESTA: " + respuesta);

		
		return respuesta;

	}

	@Override
	public String getNombreAction() throws Exception {
		return CmndValorProducto32.class.getCanonicalName();
	}

}
