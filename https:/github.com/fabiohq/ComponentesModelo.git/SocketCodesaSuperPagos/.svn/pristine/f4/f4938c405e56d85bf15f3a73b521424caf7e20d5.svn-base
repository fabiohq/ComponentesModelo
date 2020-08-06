package co.com.codesa.socketcodesaoperadorexterno.vista.formas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import co.com.codesa.socketcodesaoperadorexterno.comandos.CtrlUtilidad;
import co.com.codesa.socketcodesaoperadorexterno.general.InformacionSession;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.Constantes;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.ControladorEtiquetas;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.General;
import co.com.codesa.socketcodesaoperadorexterno.vista.eventos.EventoFormaConfiguracion;

/**
 * ****************************************************************.
 * @autor: Cesar Orlando Rendon Moreno
 * @fecha: 09/02/2014
 * @descripcion: Clase que representa el punto de entrada a la aplicacion
 * @copyright: Copyright  1998-2015 Codesa, Todos los derechos reservados.
 * ****************************************************************
 */
public final class FormaConfiguracion extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JLabel lblMensaje;
	private JLabel lblMensajeInfo;
	private JLabel lblPtoLocal;	
	private JLabel lblUsuarioBd;
	private JLabel lblPasswordBd;
	private JLabel lblNombreBd;
	private JLabel lblIpBd;
	private JLabel lblListenerBd;
	private JLabel lblVersion;	
	private TextField txtPtoLocal;	
	private TextField txtUsuarioBd;
	private JPasswordField txtPasswordBd;
	private TextField txtNombreBd;
	private TextField txtIpBd;
	private TextField txtListener;	
	private JPanel pane1;	
	private JButton btnConectar;	
	private JButton btnSalir;
	
	public static final String ETIQUETA_PTOLOCAL;	
	public static final String ETIQUETA_USUARIOBD;
	public static final String ETIQUETA_PASSWORDBD;
	public static final String ETIQUETA_NOMBREBD;
	public static final String ETIQUETA_IPBD;
	public static final String ETIQUETA_LISTENER;
	public static final String ETIQUETA_CONECTAR;
	public static final String ETIQUETA_SALIR;
	public static final String ETIQUETA_VERSION;
	
	public static final String TXT_PTOLOCAL;
	public static final String TXT_USUARIOBD;
	public static final String TXT_PASSWORD;
	public static final String TXT_NOMBREBD;
	public static final String TXT_IPBD;
	public static final String TXT_LISTENER;
	public static final String BTN_CONECTAR;
	public static final String BTN_SALIR;
		
	private Container container;	
	private KeyListener eventoTeclado;
	private ActionListener eventoMuse;
		
	private static final int ANCHO_PANTALLA;
	private static final int ALTO_PANTALLA;	
	private static final int INICIO_LABEL;
	private static final int LARGO_LABEL;
	private static final int INICIO_TEXT;
	private static final int LARGO_TEXT;
	
	static {

		ETIQUETA_PTOLOCAL = ControladorEtiquetas.etiquetasMsg.getString("puerto_local");
		ETIQUETA_USUARIOBD=ControladorEtiquetas.etiquetasMsg.getString("general_usuario_bd");
		ETIQUETA_PASSWORDBD=ControladorEtiquetas.etiquetasMsg.getString("general_password_bd");
		ETIQUETA_NOMBREBD=ControladorEtiquetas.etiquetasMsg.getString("general_basedatos");
		ETIQUETA_IPBD=ControladorEtiquetas.etiquetasMsg.getString("general_ip_bd");
		ETIQUETA_LISTENER=ControladorEtiquetas.etiquetasMsg.getString("general_listener_bd");
		
		ETIQUETA_CONECTAR=ControladorEtiquetas.etiquetasMsg.getString("general_conectar");
		ETIQUETA_SALIR=ControladorEtiquetas.etiquetasMsg.getString("general_salir");	
		ETIQUETA_VERSION=ControladorEtiquetas.etiquetasMsg.getString("general_version");		
				
		ANCHO_PANTALLA =360;
		ALTO_PANTALLA=400;		
		INICIO_LABEL=5;
		LARGO_LABEL=150;		
		INICIO_TEXT=160;
		LARGO_TEXT=130;
		
		TXT_PTOLOCAL = "txtPtoLocal";
		TXT_USUARIOBD = "txtUsuarioBd";
		TXT_PASSWORD = "txtPasswordBd";
		TXT_NOMBREBD = "txtNombreBd";
		TXT_IPBD = "txtIpBd";
		TXT_LISTENER = "txtlistenerBd";
		BTN_CONECTAR = "btnConectar";
		BTN_SALIR = "btnSalir";
	}

	public FormaConfiguracion() {
		try {		

			Hashtable<String, Integer> coordenadas = CtrlUtilidad.obtenerCoordenadasCentradas(ANCHO_PANTALLA,ALTO_PANTALLA);
			this.setTitle(ControladorEtiquetas.etiquetasMsg.getString("titulo_principal"));
			this.setLayout(null);
			this.setBounds(coordenadas.get("x"), coordenadas.get("y"), ANCHO_PANTALLA, ALTO_PANTALLA);
			this.setResizable(false);
			this.setBackground(Color.gray);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						
			pane1 = new JPanel();
			pane1.setLayout(null);
			pane1.setBounds(0,0, ANCHO_PANTALLA-6, ALTO_PANTALLA-29);
			pane1.setBorder(BorderFactory.createBevelBorder(0, Color.blue, Color.blue));
			
			container = this.getContentPane();
			eventoTeclado = new EventoFormaConfiguracion(this);
			eventoMuse = new EventoFormaConfiguracion(this);
			
			lblMensaje = new JLabel();
			lblMensaje.setFont(new Font("Arial", Font.BOLD, 14));
			lblMensaje.setBounds(5, 0, 345, 40);
			lblMensaje.setForeground(Color.red);
										
			lblPtoLocal = new JLabel(ETIQUETA_PTOLOCAL, JLabel.RIGHT);	
			lblPtoLocal.setFont(InformacionSession.TIPO_LETRA_ETIQUETAS);
			lblPtoLocal.setBounds(INICIO_LABEL, 50, LARGO_LABEL, InformacionSession.ALTO_LABEL);
			
			txtPtoLocal = new TextField();
			txtPtoLocal.setName(TXT_PTOLOCAL);
			txtPtoLocal.setBounds(INICIO_TEXT, 50, LARGO_TEXT, InformacionSession.ALTOTEXTFIELD);
			
			lblUsuarioBd = new JLabel(ETIQUETA_USUARIOBD, JLabel.RIGHT);
			lblUsuarioBd.setFont(InformacionSession.TIPO_LETRA_ETIQUETAS);
			lblUsuarioBd.setBounds(INICIO_LABEL, 80, LARGO_LABEL, InformacionSession.ALTO_LABEL);
			
			txtUsuarioBd = new TextField();
			txtUsuarioBd.setName(TXT_USUARIOBD);
			txtUsuarioBd.setBounds(INICIO_TEXT, 80, LARGO_TEXT, InformacionSession.ALTOTEXTFIELD);

			lblPasswordBd = new JLabel(ETIQUETA_PASSWORDBD, JLabel.RIGHT);
			lblPasswordBd.setFont(InformacionSession.TIPO_LETRA_ETIQUETAS);
			lblPasswordBd.setBounds(INICIO_LABEL, 110, LARGO_LABEL, InformacionSession.ALTO_LABEL);
			
			txtPasswordBd = new JPasswordField();
			txtPasswordBd.setName(TXT_PASSWORD);
			txtPasswordBd.setBounds(INICIO_TEXT, 110, LARGO_TEXT, InformacionSession.ALTOTEXTFIELD);
			
			lblNombreBd = new JLabel(ETIQUETA_NOMBREBD, JLabel.RIGHT);
			lblNombreBd.setFont(InformacionSession.TIPO_LETRA_ETIQUETAS);
			lblNombreBd.setBounds(INICIO_LABEL, 140, LARGO_LABEL, InformacionSession.ALTO_LABEL);
			
			txtNombreBd = new TextField();
			txtNombreBd.setName(TXT_NOMBREBD);
			txtNombreBd.setBounds(INICIO_TEXT, 140, LARGO_TEXT, InformacionSession.ALTOTEXTFIELD);
			
			lblIpBd = new JLabel(ETIQUETA_IPBD, JLabel.RIGHT);
			lblIpBd.setFont(InformacionSession.TIPO_LETRA_ETIQUETAS);
			lblIpBd.setBounds(INICIO_LABEL, 170, LARGO_LABEL, InformacionSession.ALTO_LABEL);
			
			txtIpBd = new TextField();
			txtIpBd.setName(TXT_IPBD);
			txtIpBd.setBounds(INICIO_TEXT, 170, LARGO_TEXT, InformacionSession.ALTOTEXTFIELD);
			
			lblListenerBd = new JLabel(ETIQUETA_LISTENER, JLabel.RIGHT);
			lblListenerBd.setFont(InformacionSession.TIPO_LETRA_ETIQUETAS);
			lblListenerBd.setBounds(INICIO_LABEL, 200, LARGO_LABEL, InformacionSession.ALTO_LABEL);
			
			txtListener = new TextField();
			txtListener.setName(TXT_LISTENER);
			txtListener.setBounds(INICIO_TEXT, 200, LARGO_TEXT, InformacionSession.ALTOTEXTFIELD);
					
			btnConectar = new JButton(ETIQUETA_CONECTAR);
			btnConectar.setBounds(50, 230, 100, 30);
			btnConectar.addActionListener(eventoMuse);
			btnConectar.addKeyListener(eventoTeclado);
			btnConectar.setName(FormaConfiguracion.BTN_CONECTAR);
			btnConectar.setFont(InformacionSession.TIPO_LETRA_ETIQUETAS);
						
			btnSalir = new JButton(ETIQUETA_SALIR);
			btnSalir.setBounds(180, 230, 100, 30);
			btnSalir.addActionListener(eventoMuse);
			btnSalir.addKeyListener(eventoTeclado);
			btnSalir.setName(FormaConfiguracion.BTN_SALIR);
			btnSalir.setFont(InformacionSession.TIPO_LETRA_ETIQUETAS);
			
			lblMensajeInfo = new JLabel();
			lblMensajeInfo.setFont(new Font("Arial", Font.BOLD, 14));
			lblMensajeInfo.setBounds(InformacionSession.POSICIONX, 330, 200, InformacionSession.ALTO_LABEL);
			lblMensajeInfo.setForeground(Color.blue);
			
			lblVersion=new JLabel(ETIQUETA_VERSION, JLabel.CENTER);
			lblVersion.setFont(InformacionSession.TIPO_LETRA_ETIQUETAS);
			lblVersion.setBounds(30, 280, 320, InformacionSession.ALTO_LABEL);
			lblVersion.setOpaque(true);
			lblVersion.setForeground(Color.WHITE);
			lblVersion.setBackground(new Color(187,46,42));
			lblVersion.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.black));			
			
			pane1.add(lblMensaje);
			pane1.add(lblMensajeInfo);			
			pane1.add(lblPtoLocal);
			pane1.add(txtPtoLocal);	
			pane1.add(lblUsuarioBd);
			pane1.add(txtUsuarioBd);			
			pane1.add(lblPasswordBd);
			pane1.add(txtPasswordBd);			
			pane1.add(lblNombreBd);
			pane1.add(txtNombreBd);			
			pane1.add(lblIpBd);
			pane1.add(txtIpBd);			
			pane1.add(lblListenerBd);
			pane1.add(txtListener);
			pane1.add(btnConectar);
			pane1.add(btnSalir);			
			pane1.add(lblVersion);			
			container.add(pane1);
			
			
		    txtPtoLocal.setText( General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_PUERTO ));
		    txtUsuarioBd.setText( General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_USUARIO ));
		    txtNombreBd.setText( General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_DATABASE ));
		    txtIpBd.setText( General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_IP ));
		    txtListener.setText( General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_LISTENER ));
			
			this.setVisible(true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static void main(String[] args) {
		new FormaConfiguracion();
	}
	
	public TextField getTxtPtoLocal() {
		return txtPtoLocal;
	}

	public void setTxtPtoLocal(TextField txtPtoLocal) {
		this.txtPtoLocal = txtPtoLocal;
	}

	public TextField getTxtUsuarioBd() {
		return txtUsuarioBd;
	}
	
	public void setTxtUsuarioBd(TextField txtUsuarioBd) {
		this.txtUsuarioBd = txtUsuarioBd;
	}
	
	
	public JPasswordField getTxtPasswordBd() {
		return txtPasswordBd;
	}
	
	public void setTxtPasswordBd(JPasswordField txtPasswordBd) {
		this.txtPasswordBd = txtPasswordBd;
	}
	
	public TextField getTxtNombreBd() {
		return txtNombreBd;
	}
	
	public void setTxtNombreBd(TextField txtNombreBd) {
		this.txtNombreBd = txtNombreBd;
	}
	
	public TextField getTxtIpBd() {
		return txtIpBd;
	}
	
	public void setTxtIpBd(TextField txtIpBd) {
		this.txtIpBd = txtIpBd;
	}
	
	public TextField getTxtListener() {
		return txtListener;
	}
	
	public void setTxtListener(TextField txtListener) {
		this.txtListener = txtListener;
	}
	
	public JButton getBtnConectar() {
		return btnConectar;
	}

	public void setBtnConectar(JButton btnConectar) {
		this.btnConectar = btnConectar;
	}

	public JLabel getLblMensaje() {
		return lblMensaje;
	}

	public void setLblMensaje(JLabel lblMensaje) {
		this.lblMensaje = lblMensaje;
	}

	public JLabel getLblMensajeInfo() {
		return lblMensajeInfo;
	}

	public void setLblMensajeInfo(JLabel lblMensajeInfo) {
		this.lblMensajeInfo = lblMensajeInfo;
	}

}
