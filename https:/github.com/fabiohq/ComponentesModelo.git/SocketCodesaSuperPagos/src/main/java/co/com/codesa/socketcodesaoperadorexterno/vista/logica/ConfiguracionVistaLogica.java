package co.com.codesa.socketcodesaoperadorexterno.vista.logica;

import java.util.logging.Logger;

import javax.swing.JFrame; 

import co.com.codesa.socketcodesaoperadorexterno.general.InformacionSession;
import co.com.codesa.socketcodesaoperadorexterno.general.LogsManager;
import co.com.codesa.socketcodesaoperadorexterno.socket.ServerWS;
import co.com.codesa.socketcodesaoperadorexterno.vista.formas.FormaConfiguracion;

/**
 * ****************************************************************.
 * @autor: Cesar Orlando Rendon Moreno
 * @fecha: 09/02/2014
 * @descripcion: Clase que represental la logica de la forma
 * @copyright: Copyright  1998-2015 Codesa, Todos los derechos reservados.
 * ****************************************************************
 */
public class ConfiguracionVistaLogica {

	private Logger logger = LogsManager.getInstance();
	private FormaConfiguracion frmPadre;
	private String mensaje;

	public ConfiguracionVistaLogica() {
		this.frmPadre = null;
	}
	public ConfiguracionVistaLogica(JFrame pFrmPadre) {
		this.frmPadre = (FormaConfiguracion) pFrmPadre;
	}

	public void ejecutarLoginVistaLogica(String ptoLocal, String usuario, String password, String nombre, String ip, String listener)
			throws Exception {
		try {
			mensaje="Servidor Inicializado!";
			logger.info(mensaje);
			frmPadre.getLblMensajeInfo().setText(mensaje);
			InformacionSession.getInstance().setMigracionPto(ptoLocal);
			InformacionSession.getInstance().setUsuarioBd(usuario);
			InformacionSession.getInstance().setPasswordBd(password);
			InformacionSession.getInstance().setBd(nombre);
			InformacionSession.getInstance().setIpBd(ip);
			InformacionSession.getInstance().setListenerBd(listener);
			ServerWS serverWS = new ServerWS();
			serverWS.subirServidor(Integer.parseInt(ptoLocal));
	
			
		} catch (Exception e) {	
			frmPadre.getLblMensajeInfo().setText("");			
			throw e;
		}
	}
	
	public void ejecutarLoginVistaLogicaConsola(String ptoLocal, String usuario, String password, String nombre, String ip, String listener)
			throws Exception {
		try {
			mensaje="*****Servidor Inicializado Por Consola!*****";
			logger.info(mensaje);
			InformacionSession.getInstance().setMigracionPto(ptoLocal);
			InformacionSession.getInstance().setUsuarioBd(usuario);
			InformacionSession.getInstance().setPasswordBd(password);
			InformacionSession.getInstance().setBd(nombre);
			InformacionSession.getInstance().setIpBd(ip);
			InformacionSession.getInstance().setListenerBd(listener);
			
			ServerWS serverWS = new ServerWS();			
			serverWS.subirServidor(Integer.parseInt(ptoLocal));
			
		} catch (Exception e) {	
			e.printStackTrace();
			throw e;
		}

	}

}
