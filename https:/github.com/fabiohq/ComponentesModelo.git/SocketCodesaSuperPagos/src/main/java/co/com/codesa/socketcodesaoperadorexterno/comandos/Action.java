

package co.com.codesa.socketcodesaoperadorexterno.comandos;

/**
 * ****************************************************************.
 * @autor: Cesar Orlando Rendon Moreno
 * @fecha: 09/02/2014
 * @descripcion: Clase interface que contine los metodos necesarios de un
 *  comando
 * @copyright: Copyright  1998-2015 Codesa, Todos los derechos reservados.
 * ****************************************************************
 */
public interface Action {
	
	public Object execute(Object o) throws Exception;
	public String getNombreAction() throws Exception;
}
