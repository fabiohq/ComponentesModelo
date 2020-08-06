package co.com.codesa.socketcodesaoperadorexterno.interfaces;

import java.sql.SQLException;

import co.com.codesa.socketcodesaoperadorexterno.bean.ConfigConexionTrama;
import co.com.codesa.socketcodesaoperadorexterno.bean.ResultadoBean;

public interface IOperadorExternoDAO {
	
	public ConfigConexionTrama registraReintento( ConfigConexionTrama configConex );	
	public ConfigConexionTrama registraReintentoEstado( ConfigConexionTrama configConex, String codEntidad, String codDatos );
	public ConfigConexionTrama registraErrorCom( ConfigConexionTrama configConex ) ;
	public ConfigConexionTrama recargaMovilRegistraRespuesta( ConfigConexionTrama configConex );
	public ConfigConexionTrama recargaMovilRegistraRespuesta2( ConfigConexionTrama configConex );	
	public ResultadoBean consultarParametroSistema( String strNombreParametro );
	public ConfigConexionTrama transferMovilRegistraRespuesta(ConfigConexionTrama configConex) throws Exception;
	public ConfigConexionTrama transferMovilRegistraRecarga(ConfigConexionTrama configConex) throws Exception;	
	public ConfigConexionTrama transferMovilGeneraTrama(ConfigConexionTrama configConex);
	public ConfigConexionTrama registraIntentoFallido(ConfigConexionTrama configConex);
	public ConfigConexionTrama registraIntentoFallidoRecarga(ConfigConexionTrama configConex);
	
	
}
