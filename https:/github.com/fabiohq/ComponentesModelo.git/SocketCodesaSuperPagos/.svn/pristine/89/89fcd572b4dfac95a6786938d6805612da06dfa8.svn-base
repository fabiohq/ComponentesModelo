package co.com.codesa.socketcodesaoperadorexterno.comandos;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import co.com.codesa.socketcodesaoperadorexterno.general.LogsManager;

/**
 * ****************************************************************.
 * @autor: Cesar Orlando Rendon Moreno
 * @fecha: 09/02/2014
 * @descripcion: Clase que maneja Instancia unica de clase CtrlComandos 
 * que contiene coleccion de objetos comando
 * @copyright: Copyright  1998-2015 Codesa, Todos los derechos reservados.
 * ****************************************************************
 */
public class CtrlComandos{

	private static CtrlComandos INSTANCIA = new CtrlComandos();
	public static ResourceBundle comandos;
	private Hashtable<String, Action> hashComandos;
	private Logger logger = LogsManager.getInstance();

	private CtrlComandos(){	
		try {	
			hashComandos = new Hashtable<String, Action>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static CtrlComandos getINSTANCIA() throws Exception{
		return INSTANCIA;
	}

	public void iniciarComandos() throws Exception	{

		Iterator<Comando> iterator = null;
		Comando comando = null;
		Action actComando = null;

		try
		{
			List<Comando> coleccion = ComandoService.getINSTANCIA().findAll();
			iterator = coleccion.iterator();
			comando = null;
			actComando = null;

		}catch (Exception e){
			logger.severe("(CtrlComandos.getINSTANCIA()) Mensaje de Error: "+ e.getMessage());
		}

		while (iterator.hasNext())
		{
			comando = (Comando) iterator.next();
			try
			{
				actComando = (Action) CtrlUtilidad.instanciarClase(comando.getClase());				
				INSTANCIA.hashComandos.put(comando.getCodigo(),actComando);
				logger.info("***Se cargo comando "+comando.getClase());

			}catch (Exception sme){							
				logger.severe("(CtrlComandos.getINSTANCIA()) Se presento problema cargando comando" + comando.getCodigo() +" Error Message: " + sme.getMessage());
			}
		}
	}


	public Action execComando(String pComando) throws Exception	{
		Action comando = null;
		try{
			LogsManager.getInstance().info("Nro comando: " + pComando);
			comando = hashComandos.get(pComando);

		}
		catch (Exception e){
			logger.severe("(CtrlComandos.getINSTANCIA()) Mensaje de Error: "+ e.getMessage());
			throw new Exception(e.getMessage());
		}
		return comando;
	}

}

