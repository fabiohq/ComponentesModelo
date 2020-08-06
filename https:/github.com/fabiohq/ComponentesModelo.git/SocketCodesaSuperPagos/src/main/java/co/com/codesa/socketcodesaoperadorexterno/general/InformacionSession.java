package co.com.codesa.socketcodesaoperadorexterno.general;

import java.awt.Color;
import java.awt.Font;

import co.com.codesa.socketcodesaoperadorexterno.comandos.CtrlUtilidad;

/**
 * ****************************************************************.
 * @autor: Cesar Orlando Rendon Moreno
 * @fecha: 09/02/2014
 * @descripcion: Clase que maneja valores durante la ejecucion del app
 * @copyright: Copyright  1998-2015 Codesa, Todos los derechos reservados.
 * ****************************************************************
 */
public final class InformacionSession {
	
	private static InformacionSession instance = new InformacionSession();
	
	private String migracionIp;
	private String migracionPto;
	
	private String usuarioBd;
	private String passwordBd;
	private String bd;
	private String ipBd;
	private String listenerBd;

	public static final int ANCHOTEXTFIELD;
	public static final int ALTOTEXTFIELD;
	public static final int POSICIONX;
	public static final int ALTO_LABEL;
	public static final Font TIPO_LETRA_ETIQUETAS;
	public static final Font TIPO_LETRA_CAJA_TEXTO;
	public static final Font TIPO_LETRA_ETIQUETA_PEQUENA;
	public static final Font TIPO_LETRA_MENSAJES;
	public static final Color COLOR_MENSAJES;
	
	static {
		ANCHOTEXTFIELD = 135;
		ALTOTEXTFIELD = 20;
		POSICIONX = 10;
		ALTO_LABEL = 20;
		TIPO_LETRA_ETIQUETAS = obtenerTipoLetraEtiquetas();
		TIPO_LETRA_CAJA_TEXTO = obtenerTipoLetraCajaTexto();
		TIPO_LETRA_ETIQUETA_PEQUENA = obtenerTipoLetraEtiquetasPequeno();
		TIPO_LETRA_MENSAJES=new Font("Arial",Font.PLAIN,14);
		COLOR_MENSAJES=Color.red;
	}
	
	private InformacionSession() {

	}

	public static InformacionSession getInstance() {
		return instance;

	}

	/**
	 * ****************************************************************.
	 * @metodo: obtenerTipoLetraEtiquetas
	 * @descripcion: metodo que permite obtener la fuente que debera ser
	 * usado para quellas etiquetas que deben ser grandes y en negrilla.
	 * @autor: Andres Cardenas
	 * @return Font
	 *****************************************************************/
	private static Font obtenerTipoLetraEtiquetas (){
		String sistemaOperativo = CtrlUtilidad.obtenerSistemaOperativo();
		Font tipoLetraEtiquetas; 

		if(sistemaOperativo.contains("nix") || sistemaOperativo.contains("nux")|| sistemaOperativo.contains("aix")){
			tipoLetraEtiquetas = new Font("Serif", Font.BOLD, 12);
		}else{
			tipoLetraEtiquetas = new Font("Arial", Font.BOLD, 13);
		}

		return tipoLetraEtiquetas;
	}

	/**
	 * ****************************************************************.
	 * @metodo: obtenerTipoLetraCajaTexto
	 * @descripcion: metodo que permite obtener la fuente para aquellos campos
	 * de entrada de los usuarios.
	 * @autor: Andres Cardenas
	 * @return Font
	 *****************************************************************/
	private static Font obtenerTipoLetraCajaTexto(){
		String sistemaOperativo = CtrlUtilidad.obtenerSistemaOperativo();
		Font tipoLetraCajaTexto;

		if(sistemaOperativo.contains("nix") || sistemaOperativo.contains("nux")|| sistemaOperativo.contains("aix")){
			tipoLetraCajaTexto = new Font("Serif", Font.PLAIN, 12);
		}else{
			tipoLetraCajaTexto = new Font("Arial", Font.PLAIN, 13);
		}

		return tipoLetraCajaTexto;
	}

	/**
	 * ****************************************************************.
	 * @metodo: obtenerTipoLetraEtiquetasPequeno
	 * @descripcion: metodo que permite obtener la fuente que debera ser
	 * usado para quellas etiquetas que deben ser peque�as y en negrilla.
	 * @autor: Andres Cardenas
	 * @return Font
	 *****************************************************************/
	private static Font obtenerTipoLetraEtiquetasPequeno(){
		String sistemaOperativo = CtrlUtilidad.obtenerSistemaOperativo();
		Font tipoLetraCajaTexto;

		if(sistemaOperativo.contains("nix") || sistemaOperativo.contains("nux")|| sistemaOperativo.contains("aix")){
			tipoLetraCajaTexto = new Font("Arial", Font.BOLD, 11);
		}else{
			tipoLetraCajaTexto = new Font("Arial", Font.BOLD, 12);
		}

		return tipoLetraCajaTexto;
	}

	public String getMigracionIp() {
		return migracionIp;
	}

	public void setMigracionIp(String migracionIp) {
		this.migracionIp = migracionIp;
	}

	public String getMigracionPto() {
		return migracionPto;
	}

	public void setMigracionPto(String migracionPto) {
		this.migracionPto = migracionPto;
	}

	public String getUsuarioBd() {
		return usuarioBd;
	}

	public void setUsuarioBd(String usuarioBd) {
		this.usuarioBd = usuarioBd;
	}

	public String getPasswordBd() {
		return passwordBd;
	}

	public void setPasswordBd(String passwordBd) {
		this.passwordBd = passwordBd;
	}

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	public String getIpBd() {
		return ipBd;
	}

	public void setIpBd(String ipBd) {
		this.ipBd = ipBd;
	}
	
	public String getListenerBd() {
		return listenerBd;
	}
	
	public void setListenerBd(String listenerBd) {
		this.listenerBd = listenerBd;
	}
}
