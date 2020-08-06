package co.com.codesa.socketcodesaoperadorexterno.vista.formas;

import co.com.codesa.socketcodesaoperadorexterno.comandos.CtrlUtilidad;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.Constantes;
import co.com.codesa.socketcodesaoperadorexterno.utilidades.General;
import co.com.codesa.socketcodesaoperadorexterno.vista.logica.ConfiguracionVistaLogica;

public class EjecucionConsola {
	public static void main(String[] args) {

		try {
            
            if( args.length > 0 ) {
            
                String strPtoLocal = "";
//                String strSocketIp = args[1];
//                String strSocketPto= args[2];
                String strUsuarioBD = "";
                String strPasswordBd = "";
                String strNombreBd = "";
                String strIPBd = "";    
                String strListenerBd = "";
                
                
                strPtoLocal = General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_PUERTO );
                strUsuarioBD = General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_USUARIO );
                strNombreBd = General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_DATABASE );
                strIPBd = General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_IP );
                strPasswordBd = General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", "usuario1" );
                //desencriptando contrasena
                strPasswordBd = General.desencriptar(strPasswordBd);
                strListenerBd = General.retornarValorXML( Constantes.Propiedades.PATH_CONFIGSERVER+".xml", Constantes.Propiedades.NODO_CONFIGSERVER_LISTENER );
                
                
                CtrlUtilidad.validarPuerto(strPtoLocal, FormaConfiguracion.ETIQUETA_PTOLOCAL);
                CtrlUtilidad.validarCadena(strUsuarioBD, FormaConfiguracion.ETIQUETA_USUARIOBD);
                CtrlUtilidad.validarCadena(strPasswordBd, FormaConfiguracion.ETIQUETA_PASSWORDBD);
                CtrlUtilidad.validarCadena(strNombreBd, FormaConfiguracion.ETIQUETA_NOMBREBD);
                CtrlUtilidad.validarIp(strIPBd, FormaConfiguracion.ETIQUETA_IPBD);
                CtrlUtilidad.validarPuerto(strListenerBd, FormaConfiguracion.ETIQUETA_LISTENER);
                
                ConfiguracionVistaLogica configuracionVistaLogica = new ConfiguracionVistaLogica();
                configuracionVistaLogica.ejecutarLoginVistaLogicaConsola(strPtoLocal, strUsuarioBD, 
                        strPasswordBd, strNombreBd, strIPBd, strListenerBd);
            }
            else{
                  new FormaConfiguracion();
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
