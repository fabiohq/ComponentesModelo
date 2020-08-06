/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.codesa.socketcodesaoperadorexterno.comandos;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.general.LogsManager;


/**
 * 
 * @author mauricio.lopez
 */
/**
 * ****************************************************************.
 * @autor: Cesar Orlando Rendon Moreno
 * @fecha: 09/02/2014
 * @descripcion: Clase que se encarga de crear  las clases-
 * comandos que se encuentran en el properties
 * @copyright: Copyright  1998-2015 Codesa, Todos los derechos reservados.
 * ****************************************************************
 */
public class ComandoService
{

	private static ComandoService INSTANCIA;
	private Logger logger = LogsManager.getInstance();


	private ComandoService()
	{
		super();
	}


	public synchronized static ComandoService getINSTANCIA()
	{
		if (INSTANCIA == null)
		{
			INSTANCIA = new ComandoService();
		}
		return INSTANCIA;
	}


	public List<Comando> findAll() throws Exception{
		List<Comando> listaObjetos = new LinkedList<Comando>();
		try
		{
			String valor;
			Object objKey;
			Comando comando;
			Properties comandos=CtrlUtilidad.cargarArchivoComandos();
			Enumeration<?> key =  comandos.keys();

			while(key.hasMoreElements()){

				objKey = key.nextElement();
				valor=comandos.getProperty(objKey.toString());	

				comando = new Comando();
				comando.setCodigo(objKey.toString());
				comando.setCodApp("");
				comando.setDescripcion("");
				comando.setClase(valor);

				listaObjetos.add(comando);			
			}	
		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE,
					" Llamando, Operacion: [ ComandoService-findAll ] Error es: "
							+ e.toString());
		}

		return listaObjetos;
	}
	
	
}
