package co.com.codesa.socketcodesaoperadorexterno.comandos.funciones;

import co.com.codesa.socketcodesaoperadorexterno.bean.ConfigConexionTrama;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.Constantes;

/**
 * @author Fabio Hernandez
 * @since 08/06/2017
 * @descripcion: Comando que extiende de AbstractProcesoVentaPaquetes y ejecuta la logica para realizar la recarga
 * 				, Generar trama y enviarla al WS del operador y
 *               procesar la respuesta del operador movil en productos virtuales
 * 
 */
public class CmndOperadorExterno991 extends AbstractProcesoVentaPaquetes {

	private static final long serialVersionUID = 1L;
	
	public CmndOperadorExterno991(){
		super.configConex = new ConfigConexionTrama(Constantes.VALOR_N);
	}
	
	@Override
	public String getIdTrama() {
		return Constantes.TRAMA_ID_NORMAL;
	}

	@Override
	public String getNombreAction() throws Exception {
		return CmndOperadorExterno991.class.getCanonicalName();
	}

}
