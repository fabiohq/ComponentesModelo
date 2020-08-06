package co.com.codesa.socketcodesaoperadorexterno.comandos.funciones;

import co.com.codesa.socketcodesaoperadorexterno.bean.ConfigConexionTrama;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.Constantes;

/**
 * @author Fabio Hernandez
 * @since 22/06/2017
 * @descripcion: Comando que extiende de AbstractProcesoVentaPaquetes y ejecuta la logica para realizar la recarga de
 *               paquetes para TAT Generar trama de envio al WS del operador,
 *               enviarla y procesar la respuesta del operador movil en
 *               productos virtuales
 */
public class CmndOperadorExternoTAT9990 extends AbstractProcesoVentaRecargas {

	private static final long serialVersionUID = 1L;
	
	public CmndOperadorExternoTAT9990(){
		super.configConex = new ConfigConexionTrama(Constantes.VALOR_S);
	}
	
	@Override
	public String getIdTrama() {
		return Constantes.TRAMA_ID_TAT_RECRAGA;
	}

	@Override
	public String getNombreAction() throws Exception {
		return CmndOperadorExternoTAT9990.class.getCanonicalName();
	}
		
}