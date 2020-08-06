package co.com.codesa.socketcodesaoperadorexterno.vista.eventos;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import co.com.codesa.socketcodesaoperadorexterno.comandos.CtrlUtilidad;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.Constantes;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.General;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.TeclasEnum;
import co.com.codesa.socketcodesaoperadorexterno.vista.formas.FormaConfiguracion;
import co.com.codesa.socketcodesaoperadorexterno.vista.logica.ConfiguracionVistaLogica;

/**
 * ****************************************************************.
 * @autor: Cesar Orlando Rendon Moreno
 * @fecha: 09/02/2014
 * @descripcion: Clase que controla los evento de la forma
 * @copyright: Copyright  1998-2015 Codesa, Todos los derechos reservados.
 * ****************************************************************
 */
public class EventoFormaConfiguracion implements KeyListener, ActionListener {

	private FormaConfiguracion formaConfiguracion;
	private ConfiguracionVistaLogica configuracionVistaLogica;

	public EventoFormaConfiguracion(FormaConfiguracion pFormaConfigu) {
		this.formaConfiguracion = pFormaConfigu;
	}

	public void actionPerformed(ActionEvent e) {
		Component component = (Component) e.getSource();
		configuracionVistaLogica = new ConfiguracionVistaLogica(formaConfiguracion);

		formaConfiguracion.getLblMensaje().setText("");
		formaConfiguracion.getLblMensajeInfo().setText("");

		if (component.getName().equals(FormaConfiguracion.BTN_CONECTAR)) {
			
			formaConfiguracion.getTxtPtoLocal().setEnabled(false);
			formaConfiguracion.getTxtUsuarioBd().setEnabled(false);
			formaConfiguracion.getTxtPasswordBd().setEnabled(false);
			formaConfiguracion.getTxtNombreBd().setEnabled(false);
			formaConfiguracion.getTxtIpBd().setEnabled(false);
			formaConfiguracion.getTxtListener().setEnabled(false);
			formaConfiguracion.getBtnConectar().setEnabled(false);

			new Thread(new Runnable() {
				public void run() {
					try {
						String strPtoLocal = formaConfiguracion.getTxtPtoLocal().getText();
						String strUsuarioBD = formaConfiguracion.getTxtUsuarioBd().getText();
						String strPasswordBd = new String(formaConfiguracion.getTxtPasswordBd().getPassword());
						String strNombreBd = formaConfiguracion.getTxtNombreBd().getText();
						String strIPBd = formaConfiguracion.getTxtIpBd().getText();		
						String strListenerBd = formaConfiguracion.getTxtListener().getText();		
						
						CtrlUtilidad.validarPuerto(strPtoLocal, FormaConfiguracion.ETIQUETA_PTOLOCAL);
						CtrlUtilidad.validarCadena(strUsuarioBD, FormaConfiguracion.ETIQUETA_USUARIOBD);
						CtrlUtilidad.validarCadena(strPasswordBd, FormaConfiguracion.ETIQUETA_PASSWORDBD);
						CtrlUtilidad.validarCadena(strNombreBd, FormaConfiguracion.ETIQUETA_NOMBREBD);
						CtrlUtilidad.validarIp(strIPBd, FormaConfiguracion.ETIQUETA_IPBD);
						CtrlUtilidad.validarPuerto(strListenerBd, FormaConfiguracion.ETIQUETA_LISTENER);

						General.modificarValorXML( "", Constantes.Propiedades.PATH_CONFIGSERVER, Constantes.Propiedades.NODO_CONFIGSERVER_PUERTO, formaConfiguracion.getTxtPtoLocal().getText( ) );
						General.modificarValorXML( "", Constantes.Propiedades.PATH_CONFIGSERVER, Constantes.Propiedades.NODO_CONFIGSERVER_USUARIO, formaConfiguracion.getTxtUsuarioBd().getText( ) );
						General.modificarValorXML( "", Constantes.Propiedades.PATH_CONFIGSERVER, Constantes.Propiedades.NODO_CONFIGSERVER_DATABASE, formaConfiguracion.getTxtNombreBd().getText( ) );
						General.modificarValorXML( "", Constantes.Propiedades.PATH_CONFIGSERVER, Constantes.Propiedades.NODO_CONFIGSERVER_IP, formaConfiguracion.getTxtIpBd().getText( ) );
						General.modificarValorXML( "", Constantes.Propiedades.PATH_CONFIGSERVER, Constantes.Propiedades.NODO_CONFIGSERVER_LISTENER, formaConfiguracion.getTxtListener().getText( ) );
						
						configuracionVistaLogica.ejecutarLoginVistaLogica(strPtoLocal, strUsuarioBD, 
								strPasswordBd, strNombreBd, strIPBd, strListenerBd);						

					} catch (Exception e) {
						formaConfiguracion.getTxtPtoLocal().setEnabled(true);
						formaConfiguracion.getTxtUsuarioBd().setEnabled(true);
						formaConfiguracion.getTxtPasswordBd().setEnabled(true);
						formaConfiguracion.getTxtNombreBd().setEnabled(true);
						formaConfiguracion.getTxtIpBd().setEnabled(true);
						formaConfiguracion.getTxtListener().setEnabled(true);
						formaConfiguracion.getBtnConectar().setEnabled(true);
						formaConfiguracion.getLblMensaje().setText(e.getMessage());
						formaConfiguracion.getTxtPtoLocal().requestFocus();
					}
				}
			}).start();
		}else if (component.getName().equals(FormaConfiguracion.BTN_SALIR)) {
			System.exit( 0 );
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		Component component = (Component) e.getSource();
		configuracionVistaLogica = new ConfiguracionVistaLogica(formaConfiguracion);
		
		formaConfiguracion.getLblMensaje().setText("");
		formaConfiguracion.getLblMensajeInfo().setText("");

		if (component.getName().equals(FormaConfiguracion.TXT_PTOLOCAL) && e.getKeyCode() == TeclasEnum.ENTER.getCodigoTecla()) {
			formaConfiguracion.getTxtUsuarioBd().requestFocus();
		}
		else if (component.getName().equals(FormaConfiguracion.TXT_USUARIOBD) && e.getKeyCode() == TeclasEnum.ENTER.getCodigoTecla()) {
			formaConfiguracion.getTxtPasswordBd().requestFocus();
		}
		else if (component.getName().equals(FormaConfiguracion.TXT_PASSWORD) && e.getKeyCode() == TeclasEnum.ENTER.getCodigoTecla()) {
			formaConfiguracion.getTxtNombreBd().requestFocus();
		}
		else if (component.getName().equals(FormaConfiguracion.TXT_NOMBREBD) && e.getKeyCode() == TeclasEnum.ENTER.getCodigoTecla()) {
			formaConfiguracion.getTxtIpBd().requestFocus();
		}
		else if (component.getName().equals(FormaConfiguracion.TXT_IPBD) && e.getKeyCode() == TeclasEnum.ENTER.getCodigoTecla()) {
			formaConfiguracion.getTxtListener().requestFocus();
		}
		else if (component.getName().equals(FormaConfiguracion.TXT_LISTENER) && e.getKeyCode() == TeclasEnum.ENTER.getCodigoTecla()) {
			formaConfiguracion.getBtnConectar().requestFocus();
		}
		else if (component.getName().equals(FormaConfiguracion.BTN_CONECTAR) && e.getKeyCode() == TeclasEnum.ENTER.getCodigoTecla()) {
			
			new Thread(new Runnable() {
				public void run() {
					try {
						String strPtoLocal = formaConfiguracion.getTxtPtoLocal().getText();
						String strUsuarioBD = formaConfiguracion.getTxtUsuarioBd().getText();
						String strPasswordBd = new String(formaConfiguracion.getTxtPasswordBd().getPassword());
						String strNombreBd = formaConfiguracion.getTxtNombreBd().getText();
						String strIPBd = formaConfiguracion.getTxtIpBd().getText();		
						String strListenerBd = formaConfiguracion.getTxtListener().getText();	
						
						CtrlUtilidad.validarPuerto(strPtoLocal, FormaConfiguracion.ETIQUETA_PTOLOCAL);
						CtrlUtilidad.validarCadena(strUsuarioBD, FormaConfiguracion.ETIQUETA_USUARIOBD);
						CtrlUtilidad.validarCadena(strPasswordBd, FormaConfiguracion.ETIQUETA_PASSWORDBD);
						CtrlUtilidad.validarCadena(strNombreBd, FormaConfiguracion.ETIQUETA_NOMBREBD);
						CtrlUtilidad.validarIp(strIPBd, FormaConfiguracion.ETIQUETA_IPBD);
						CtrlUtilidad.validarPuerto(strListenerBd, FormaConfiguracion.ETIQUETA_LISTENER);

						formaConfiguracion.getBtnConectar().setEnabled(false);
						configuracionVistaLogica.ejecutarLoginVistaLogica(strPtoLocal, strUsuarioBD, 
								strPasswordBd, strNombreBd, strIPBd, strListenerBd);		

					} catch (Exception e) {
						formaConfiguracion.getTxtUsuarioBd().setEnabled(true);
						formaConfiguracion.getTxtPasswordBd().setEnabled(true);
						formaConfiguracion.getTxtNombreBd().setEnabled(true);
						formaConfiguracion.getTxtIpBd().setEnabled(true);
						formaConfiguracion.getTxtListener().setEnabled(true);
						formaConfiguracion.getBtnConectar().setEnabled(true);
						formaConfiguracion.getLblMensaje().setText(e.getMessage());
						formaConfiguracion.getTxtPtoLocal().requestFocus();
					}
				}
			}).start();
		}else if (component.getName().equals(FormaConfiguracion.BTN_SALIR) && e.getKeyCode() == TeclasEnum.ENTER.getCodigoTecla()) {
			System.exit( 0 );
		}
	}

	public void keyReleased(KeyEvent e) {
	}

}
