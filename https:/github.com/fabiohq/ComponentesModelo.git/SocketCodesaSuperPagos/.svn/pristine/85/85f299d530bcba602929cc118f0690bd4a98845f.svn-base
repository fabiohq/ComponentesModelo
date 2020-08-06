/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.codesa.socketcodesaoperadorexterno.comandos;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import co.com.codesa.socketcodesaoperadorexterno.utilidades.ControladorEtiquetas;


/**
 * ****************************************************************.
 * @autor: Cesar Orlando Rendon Moreno
 * @fecha: 09/02/2014
 * @descripcion: Clase que maneja funciones genericas
 * @copyright: Copyright  1998-2015 Codesa, Todos los derechos reservados.
 * ****************************************************************
 */
public class CtrlUtilidad
{
	private static CtrlUtilidad INSTANCIA;

	private static SimpleDateFormat FORMATEADORFECHA;

	private static NumberFormat FORMATEADORNUMERO;

	private static NumberFormat FORMATEADORPORCENTAJE;

	public static String LENGUAJE = "es";

	public static String PAIS = "CO";

	public static String FORMATO = "dd-MM-yyyy";

	public static String FORMATOHORA = "hh24:mm:ss";

	public static String FORMATOCOMPLETO = "dd-MM-yyyy hh24:mm:ss";

	private static String ruta;
	
	public static String getInstancePath()
	{

		ruta = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "src"
				+ System.getProperty("file.separator") + "co"
				+ System.getProperty("file.separator") + "com"
				+ System.getProperty("file.separator") + "codesa"
				+ System.getProperty("file.separator") + "utils"
				+ System.getProperty("file.separator") + "LogManager";

		ruta = ruta + System.getProperty("file.separator");
		System.out.println("path listo:" + ruta);

		return ruta;
	}

	// Metodo que retorna la hora actual
	public static String obtenerFechaHoraActual(boolean separador)
	{
		String hora = "";
		String fecha = "";
		Calendar calendario = Calendar.getInstance();

		if (separador)
		{
			fecha = (calendario.get(Calendar.DAY_OF_MONTH) < 10 ? "0" : "")
					+ calendario.get(Calendar.DAY_OF_MONTH) + "/"
					+ ((calendario.get(Calendar.MONTH) + 1) < 10 ? "0" : "")
					+ (calendario.get(Calendar.MONTH) + 1) + "/"
					+ calendario.get(Calendar.YEAR);
			hora = (calendario.get(Calendar.HOUR_OF_DAY) < 10 ? "0" : "")
					+ calendario.get(Calendar.HOUR_OF_DAY) + ":"
					+ (calendario.get(Calendar.MINUTE) < 10 ? "0" : "")
					+ calendario.get(Calendar.MINUTE) + ":"
					+ (calendario.get(Calendar.SECOND) < 10 ? "0" : "")
					+ calendario.get(Calendar.SECOND);

			return fecha + " " + hora;
		}
		else
		{
			fecha = (calendario.get(Calendar.DAY_OF_MONTH) < 10 ? "0" : "")
					+ calendario.get(Calendar.DAY_OF_MONTH)
					+ ((calendario.get(Calendar.MONTH) + 1) < 10 ? "0" : "")
					+ (calendario.get(Calendar.MONTH) + 1)
					+ calendario.get(Calendar.YEAR);
			hora = (calendario.get(Calendar.HOUR_OF_DAY) < 10 ? "0" : "")
					+ calendario.get(Calendar.HOUR_OF_DAY);

			return fecha + "_" + hora;
		}

	}

	public CtrlUtilidad()
	{
		super();
		try
		{
			CtrlUtilidad.inicializarVariables();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static CtrlUtilidad getINSTANCIA()
	{
		if (INSTANCIA == null) INSTANCIA = new CtrlUtilidad();
		return INSTANCIA;
	}

	/**
	 * Imprime en la salida estandar el dato recibido
	 *
	 * @param strDato
	 */
	public static void debug(Object strDato)
	{
		System.out.println((String) strDato);
	}

	public static boolean isEnter(java.awt.event.KeyEvent evt)
	{
		try
		{
			char tecla = evt.getKeyChar();
			if (KeyEvent.VK_ENTER == tecla)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			System.out.println("Error validando el enter, el error es: "
					+ e.toString());
			return false;
		}
	}

	/**
	 * Inicializa las variables de Configuraci�n
	 *
	 * @throws 
	 */
	public static void inicializarVariables() throws Exception
	{
		try
		{
			// Crea el Formateador de Fecha
			if (LENGUAJE != null && !LENGUAJE.equals("") && PAIS != null
					&& !PAIS.equals(""))
			{
				Locale locale = Locale.ENGLISH;
				FORMATEADORFECHA = new SimpleDateFormat(FORMATO, locale);
				FORMATEADORNUMERO = NumberFormat.getIntegerInstance(Locale.ENGLISH);

				FORMATEADORPORCENTAJE = NumberFormat.getPercentInstance(locale);
			}
			else
			{
				FORMATEADORFECHA = new SimpleDateFormat(FORMATO);
				FORMATEADORNUMERO = NumberFormat.getInstance();
				FORMATEADORPORCENTAJE = NumberFormat.getPercentInstance();
			}
			// FORMATEADORNUMERO.setMaximumFractionDigits(10);
			// FORMATEADORNUMERO.setMinimumFractionDigits(2);
			FORMATEADORPORCENTAJE.setMaximumFractionDigits(10);
			FORMATEADORPORCENTAJE.setMinimumFractionDigits(2);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * Retorna un Class
	 *
	 * @param strclase
	 * @return
	 * @throws 
	 */
	public static Class<?> instanciarClass(String strClase) throws Exception
	{
		Class<?> clase = null;
		try
		{
			clase = Class.forName(strClase);

		}
		catch (ClassNotFoundException cnfe)
		{
			System.out
			.println("(CtrlUtilidad.instanciarClase(String)) Mensaje de Error: "
					+ cnfe.getMessage());
			throw new Exception();
		}
		return clase;

	}

	/**
	 *
	 * @param strclase
	 * @return
	 * @throws JKalyptraException
	 */
	public static Object instanciarClase(String strclase) throws Exception
	{
		Class<?> clase = null;
		Object obj = null;
		try
		{
			clase = Class.forName(strclase);
			obj = clase.newInstance();
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out
			.println("(CtrlUtilidad.instanciarClase(String)) Mensaje de Error: "
					+ cnfe.getMessage());
			throw new Exception();
		}
		catch (InstantiationException ie)
		{
			System.out
			.println("(CtrlUtilidad.instanciarClase(String)) Mensaje de Error: "
					+ ie.getMessage());
			throw new Exception();
		}
		catch (ClassCastException cce)
		{
			System.out
			.println("(CtrlUtilidad.instanciarClase(String)) Mensaje de Error: "
					+ cce.getMessage());
			throw new Exception();
		}
		catch (Exception e)
		{
			System.out
			.println("(CtrlUtilidad.instanciarClase(String)) Mensaje de Error: "
					+ e.getMessage());
			throw new Exception();
		}

		return obj;
	}

	/**
	 * Retorna una instancia del Formateador de Fecha
	 *
	 * @return java.text.SimpleDateFormat
	 */
	public static java.text.SimpleDateFormat getFormateadorFecha()
	{
		try
		{
			if (FORMATEADORFECHA == null) CtrlUtilidad.inicializarVariables();
		}
		catch (Exception e)
		{
			System.out
			.println("(CtrlUtilidad.getFormateadorFecha()) Mensaje de Error:"
					+ e.getMessage());
		}
		return FORMATEADORFECHA;
	}

	/**
	 * Retorna una instancia del Formateador de N�mero
	 *
	 * @return java.text.NumberFormat
	 */
	public static java.text.NumberFormat getFormateadorNumero()
	{
		try
		{
			if (FORMATEADORNUMERO == null) CtrlUtilidad.inicializarVariables();
		}
		catch (Exception e)
		{
			System.out
			.println("(CtrlUtilidad.getFormateadorNumero()) Mensaje de Error:"
					+ e.getMessage());
		}
		return FORMATEADORNUMERO;
	}

	/**
	 * Retorna la Instancia del Formateador de Porcentajes
	 *
	 * @return java.text.NumberFormat
	 */
	public static java.text.NumberFormat getFormateadorPorcentaje()
	{
		try
		{
			if (FORMATEADORPORCENTAJE == null)
				CtrlUtilidad.inicializarVariables();
		}
		catch (Exception e)
		{
			System.out
			.println("(CtrlUtilidad.getFormateadorPorcentaje()) Mensaje de Error:"
					+ e.getMessage());
		}
		return FORMATEADORPORCENTAJE;
	}

	/**
	 * Retorna si es AM (0) o PM (1) de la Fecha enviada por Parametro.
	 *
	 * @param newFecha
	 * @return
	 */
	public String getAMPM(java.util.Date newFecha)
	{
		java.util.Calendar calendario = FORMATEADORFECHA.getCalendar();
		calendario.setTime(newFecha);
		return "" + calendario.get(Calendar.AM_PM);
	}

	/**
	 * Retorna el Ano de la Fecha enviada por Parametro.
	 *
	 * @param newFecha
	 * @return
	 */
	public String getAno(String newFecha)
	{
		return newFecha.substring(6, 10);
	}

	/**
	 * Retorna el Ano de la Fecha enviada por Parametro.
	 *
	 * @param newFecha
	 * @return
	 */
	public String getAno(java.util.Date newFecha)
	{
		java.util.Calendar calendario = FORMATEADORFECHA.getCalendar();
		calendario.setTime(newFecha);
		return "" + calendario.get(Calendar.YEAR);
	}

	/**
	 * Retorna el Dia de la Fecha enviada por Parametro.
	 *
	 * @param newFecha
	 * @return
	 */
	public String getDiaMes(String newFecha)
	{
		return newFecha.substring(0, 2);
	}

	/**
	 * Retorna el Dia de la Fecha enviada por Parametro.
	 *
	 * @param newFecha
	 * @return
	 */
	public String getDiaMes(java.util.Date newFecha)
	{
		java.util.Calendar calendario = FORMATEADORFECHA.getCalendar();
		calendario.setTime(newFecha);
		int d = calendario.get(Calendar.DAY_OF_MONTH);
		return ((d < 10) ? "0" : "") + d;
	}

	/**
	 * Retorna el Numero del Dia de la Semana de la Fecha.
	 *
	 * @param newFecha
	 * @return
	 */
	public String getDiaSemana(java.util.Date newFecha)
	{
		java.util.Calendar calendario = FORMATEADORFECHA.getCalendar();
		calendario.setTime(newFecha);
		return "" + calendario.get(java.util.Calendar.DAY_OF_WEEK);
	}

	/**
	 * Retorna la Fecha en Clase Date enviada por Parametro.
	 *
	 * @param dia
	 * @param mes
	 * @param ano
	 * @return
	 */
	public java.util.Date getFecha(int dia, int mes, int ano)
	{
		java.util.Calendar calendario = FORMATEADORFECHA.getCalendar();
		calendario.set(ano, mes - 1, dia);
		return calendario.getTime();
	}

	/**
	 * Retorna la Fecha en Clase Date enviada por Parametro.
	 *
	 * @param dia
	 * @param mes
	 * @param ano
	 * @param hora
	 * @param minutos
	 * @return
	 */
	public java.util.Date getFecha(int dia, int mes, int ano, int hora,
			int minutos)
	{
		java.util.Calendar calendario = FORMATEADORFECHA.getCalendar();
		calendario.set(ano, mes - 1, dia, hora, minutos);
		return calendario.getTime();
	}

	/**
	 * Retorna la Fecha en Clase Date enviada por Parametro.
	 *
	 * @param dia
	 * @param mes
	 * @param ano
	 * @param hora
	 * @param minutos
	 * @param segundos
	 * @return
	 */
	public java.util.Date getFecha(int dia, int mes, int ano, int hora,
			int minutos, int segundos)
	{
		java.util.Calendar calendario = FORMATEADORFECHA.getCalendar();
		calendario.set(ano, mes - 1, dia, hora, minutos, segundos);
		return calendario.getTime();
	}

	/**
	 * Retorna la Fecha en Clase Date enviada por Parametro.
	 *
	 * @param strFecha
	 * @return Date
	 * @throws java.text.ParseException
	 */
	public java.util.Date getFecha(String strFecha)
			throws java.text.ParseException
			{
		java.util.Date fecha;
		fecha = FORMATEADORFECHA.parse(strFecha);
		return fecha;
			}

	/**
	 * Retorna la Fecha en una Clase Date con el formato enviado por parametro.
	 *
	 * @param strFecha
	 * @param strFormato
	 * @return java.util.Date
	 * @throws java.text.ParseException
	 */
	public java.util.Date getFecha(String strFecha, String strFormato)
			throws java.text.ParseException
			{
		java.util.Locale locale = new java.util.Locale(CtrlUtilidad.LENGUAJE,
				CtrlUtilidad.PAIS);
		SimpleDateFormat formateador = new SimpleDateFormat(strFormato, locale);
		java.util.Date fecha = formateador.parse(strFecha);
		return fecha;
			}

	/**
	 * Se encarga de dar Formato a la fecha enviado por Parametro.
	 *
	 * @param fecha
	 * @return String
	 */
	public String getFecha(java.util.Date fecha)
	{
		return FORMATEADORFECHA.format(fecha);
	}

	/**
	 * Se encarga de dar formato a la fecha con el formato enviado por parametro
	 *
	 * @param fecha
	 * @param strFormato
	 * @return String
	 */
	public String getFecha(java.util.Date fecha, String strFormato)
	{
		java.util.Locale locale = new java.util.Locale(CtrlUtilidad.LENGUAJE,
				CtrlUtilidad.PAIS);
		SimpleDateFormat formateador = new SimpleDateFormat(strFormato, locale);
		return formateador.format(fecha);
	}

	/**
	 * Retorna el Formato de Fechas Utilizado en el Sistema.
	 *
	 * @return java.lang.String
	 */
	public static java.lang.String getFormato()
	{
		return FORMATO;
	}

	/**
	 * Retorna la Hora de la Fecha enviada por Parametro.
	 *
	 * @param newFecha
	 * @return
	 */
	public String getHora(java.util.Date newFecha)
	{
		java.util.Calendar calendario = FORMATEADORFECHA.getCalendar();
		calendario.setTime(newFecha);
		int h = calendario.get(Calendar.HOUR);
		return ((h < 10) ? "0" : "") + h;
	}

	/**
	 * Retorna la Hora Completa de la Fecha enviada por Parametro.
	 *
	 * @param newFecha
	 * @return
	 */
	public String getHoraCompleta(java.util.Date newFecha)
	{
		Locale locale = new Locale(LENGUAJE, PAIS);

		SimpleDateFormat FORMATEADORFECHATMP = new SimpleDateFormat(
				FORMATOCOMPLETO, locale);

		Calendar calendario = FORMATEADORFECHATMP.getCalendar();
		calendario.setTime(newFecha);

		int h = calendario.get(Calendar.HOUR_OF_DAY);
		int m = calendario.get(Calendar.MINUTE);
		int s = calendario.get(Calendar.SECOND);

		String hora = (((h < 10) ? "0" : "") + h) + (((m < 10) ? "0" : "") + m)
				+ (((s < 10) ? "0" : "") + s);

		return hora;
	}

	/**
	 * Retorna la Hora del Dia de la Fecha enviada por Parametro.
	 *
	 * @param newFecha
	 * @return
	 */
	public String getHoraDia(java.util.Date newFecha)
	{
		java.util.Calendar calendario = FORMATEADORFECHA.getCalendar();
		calendario.setTime(newFecha);
		int h = calendario.get(Calendar.HOUR_OF_DAY);
		return ((h < 10) ? "0" : "") + h;
	}

	/**
	 * Retorna el Maximo Dia del Mes de la Fecha.
	 *
	 * @param newFecha
	 * @return
	 */
	public String getMaximoDiaMes(java.util.Date newFecha)
	{
		java.util.Calendar calendario = FORMATEADORFECHA.getCalendar();
		calendario.setTime(newFecha);
		return ""
		+ calendario.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	}

	/**
	 * @param anio
	 * @param mes
	 * @return
	 */
	public int getMaximoDiaMes(int anio, int mes)
	{
		java.util.Calendar calendario = FORMATEADORFECHA.getCalendar();
		calendario.set(java.util.Calendar.YEAR, anio);
		calendario.set(java.util.Calendar.MONTH, mes - 1);
		return calendario.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	}

	/**
	 * Retorna el Mes de la Fecha enviada por Parametro.
	 *
	 * @param newFecha
	 * @return
	 */
	public String getMes(String newFecha)
	{
		return newFecha.substring(3, 5);
	}

	/**
	 * Retorna el Mes de la Fecha enviada por Parametro.
	 *
	 * @param newFecha
	 * @return
	 */
	public String getMes(java.util.Date newFecha)
	{
		java.util.Calendar calendario = FORMATEADORFECHA.getCalendar();
		calendario.setTime(newFecha);
		int m = calendario.get(Calendar.MONTH) + 1;
		return ((m < 10) ? "0" : "") + m;
	}

	/**
	 * Retorna el nombre del mes de la fecha que se envia en el parametro
	 *
	 * @param newFecha
	 * @param strMascaraMes
	 * @return
	 */
	public String getNombreMes(Date newFecha, String strMascaraMes)
	{
		String laFecha = this.getFecha(newFecha, "dd-" + strMascaraMes
				+ "-yyyy");
		String[] partes = laFecha.split("-");
		return partes[1];
	}

	/**
	 * Retorna el nombre del mes segun el numero que se envia en el parametro
	 * desde 0 a 11
	 *
	 * @param mes
	 * @param strMascaraMes
	 * @return
	 */
	public String getNombreMes(int mes, String strMascaraMes)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(java.util.Calendar.MONTH, mes - 1);
		Date newFecha = calendar.getTime();
		String laFecha = this.getFecha(newFecha, "dd-" + strMascaraMes
				+ "-yyyy");
		String[] partes = laFecha.split("-");
		return partes[1];
	}

	/**
	 * Retorna los Minutos de la Fecha enviada por Parametro.
	 *
	 * @param newFecha
	 * @return
	 */
	public String getMinutos(java.util.Date newFecha)
	{
		java.util.Calendar calendario = FORMATEADORFECHA.getCalendar();
		calendario.setTime(newFecha);
		int m = calendario.get(Calendar.MINUTE);
		return ((m < 10) ? "0" : "") + m;
	}

	/**
	 * Retorna la Cadena con el formato especificado para el Numero.
	 *
	 * @param numero
	 * @return
	 */
	public String getNumero(double numero)
	{
		CtrlUtilidad.getFormateadorNumero();
		return FORMATEADORNUMERO.format(numero);
	}

	/**
	 * Retorna los Segundos de la Fecha enviada por Parametro.
	 *
	 * @param newFecha
	 * @return
	 */
	public String getSegundos(java.util.Date newFecha)
	{
		java.util.Calendar calendario = FORMATEADORFECHA.getCalendar();
		calendario.setTime(newFecha);
		int s = calendario.get(Calendar.SECOND);
		return ((s < 10) ? "0" : "") + s;
	}

	/**
	 * Regresa el numero del parametro en formato sin exponencial
	 *
	 * @param objNumber
	 * @return String
	 */
	public static String formatBigNumber(Object objNumber)
	{
		String strFormato = "0.0";
		try
		{
			CtrlUtilidad.getFormateadorNumero().setMinimumFractionDigits(2);
			CtrlUtilidad.getFormateadorNumero().setMaximumFractionDigits(2);
			if (objNumber instanceof Double)
			{
				Double dblNumber = (Double) objNumber;
				strFormato = FORMATEADORNUMERO.format(dblNumber.doubleValue());
			}
			else if (objNumber instanceof Long)
			{
				Long lngNumber = (Long) objNumber;
				strFormato = FORMATEADORNUMERO.format(lngNumber.longValue());
			}
			else if (objNumber instanceof BigDecimal)
			{
				BigDecimal bdeNumber = (BigDecimal) objNumber;
				strFormato = FORMATEADORNUMERO.format(bdeNumber.doubleValue());
			}
			else if (objNumber instanceof Integer)
			{
				Integer intNumber = (Integer) objNumber;
				strFormato = FORMATEADORNUMERO.format(intNumber.intValue());
			}
			else if (objNumber instanceof Float)
			{
				Float fltNumber = (Float) objNumber;
				strFormato = FORMATEADORNUMERO.format(fltNumber.floatValue());
			}
			else if (objNumber instanceof Short)
			{
				Short shtNumber = (Short) objNumber;
				strFormato = FORMATEADORNUMERO.format(shtNumber.shortValue());
			}
		}
		catch (Exception e)
		{
			System.out
			.println("CtrlUtilidad.formatBigNumber(Object) Mensaje de Error: "
					+ e.getMessage());
		}
		return strFormato;
	}

	/**
	 * Retorna el numero del parametro en formato sin exponencial con el n�mero
	 * de decimales especificado
	 *
	 * @param objNumber
	 * @param decimales
	 * @return String
	 */
	public static String formatBigNumber(Object objNumber, int decimales)
	{
		String strFormato = "0.0";
		try
		{
			CtrlUtilidad.getFormateadorNumero().setMinimumFractionDigits(
					decimales);
			CtrlUtilidad.getFormateadorNumero().setMaximumFractionDigits(
					decimales);
			if (objNumber instanceof Double)
			{
				Double dblNumber = (Double) objNumber;
				strFormato = FORMATEADORNUMERO.format(dblNumber.doubleValue());
			}
			else if (objNumber instanceof Long)
			{
				Long lngNumber = (Long) objNumber;
				strFormato = FORMATEADORNUMERO.format(lngNumber.longValue());
			}
			else if (objNumber instanceof BigDecimal)
			{
				BigDecimal bdeNumber = (BigDecimal) objNumber;
				strFormato = FORMATEADORNUMERO.format(bdeNumber.doubleValue());
			}
			else if (objNumber instanceof Integer)
			{
				Integer intNumber = (Integer) objNumber;
				strFormato = FORMATEADORNUMERO.format(intNumber.intValue());
			}
			else if (objNumber instanceof Float)
			{
				Float fltNumber = (Float) objNumber;
				strFormato = FORMATEADORNUMERO.format(fltNumber.floatValue());
			}
			else if (objNumber instanceof Byte)
			{
				Byte bytNumber = (Byte) objNumber;
				strFormato = FORMATEADORNUMERO.format(bytNumber.byteValue());
			}
			else if (objNumber instanceof Short)
			{
				Short shtNumber = (Short) objNumber;
				strFormato = FORMATEADORNUMERO.format(shtNumber.shortValue());
			}
		}
		catch (Exception e)
		{
			System.out
			.println("CtrlUtilidad.formatBigNumber(Object,int) Mensaje de Error: "
					+ e.getMessage());
		}
		return strFormato;
	}

	/**
	 * Retorna el valor ingresado en formato porcentual (%)
	 *
	 * @param objNumber
	 * @return String
	 */
	public static String formatPercent(Object objNumber)
	{
		String strFormato = "0.0";
		try
		{
			CtrlUtilidad.getFormateadorPorcentaje().setMinimumFractionDigits(2);
			CtrlUtilidad.getFormateadorPorcentaje().setMaximumFractionDigits(4);
			if (objNumber instanceof Double)
			{
				Double dblNumber = (Double) objNumber;
				strFormato = FORMATEADORPORCENTAJE.format(dblNumber
						.doubleValue() / 100);
			}
			else if (objNumber instanceof Long)
			{
				Long lngNumber = (Long) objNumber;
				strFormato = FORMATEADORPORCENTAJE
						.format(lngNumber.longValue() / 100);
			}
			else if (objNumber instanceof Integer)
			{
				Integer intNumber = (Integer) objNumber;
				strFormato = FORMATEADORPORCENTAJE
						.format(intNumber.intValue() / 100);
			}
			else if (objNumber instanceof Float)
			{
				Float fltNumber = (Float) objNumber;
				strFormato = FORMATEADORPORCENTAJE.format(fltNumber
						.floatValue() / 100);
			}
			else if (objNumber instanceof Short)
			{
				Short shtNumber = (Short) objNumber;
				strFormato = FORMATEADORPORCENTAJE.format(shtNumber
						.shortValue() / 100);
			}
		}
		catch (Exception e)
		{
			System.out
			.println("CtrlUtilidad.formatPercent(Object) Mensaje de Error: "
					+ e.getMessage());
		}
		return strFormato;
	}

	/**
	 * Analiza la cadena si tiene formato de numero
	 *
	 * @param clase
	 * @param strNumber
	 * @return Object
	 * @throws ParseException
	 */
	public static Object parseBigNumber(Class<?> clase, String strNumber,
			boolean valida) throws ParseException
			{
		Object objFormato = null;
		try
		{
			CtrlUtilidad.getFormateadorNumero();
			if (clase.getName().equals(Double.class.getName()))
			{
				objFormato = new Double(FORMATEADORNUMERO.parse(strNumber)
						.doubleValue());
			}
			else if (clase.getName().equals(Long.class.getName()))
			{
				objFormato = new Long(FORMATEADORNUMERO.parse(strNumber)
						.longValue());
			}
			else if (clase.getName().equals(BigDecimal.class.getName()))
			{
				objFormato = new BigDecimal(FORMATEADORNUMERO.parse(strNumber)
						.doubleValue());
			}
			else if (clase.getName().equals(Integer.class.getName()))
			{
				objFormato = new Integer(FORMATEADORNUMERO.parse(strNumber)
						.intValue());
			}
			else if (clase.getName().equals(Float.class.getName()))
			{
				objFormato = new Float(FORMATEADORNUMERO.parse(strNumber)
						.floatValue());
			}
			else if (clase.getName().equals(Byte.class.getName()))
			{
				objFormato = new Byte(FORMATEADORNUMERO.parse(strNumber)
						.byteValue());
			}
			else if (clase.getName().equals(Short.class.getName()))
			{
				objFormato = new Short(FORMATEADORNUMERO.parse(strNumber)
						.shortValue());
			}
		}
		catch (ParseException e)
		{
			System.out
			.println("(CtrlUtilidad.parseBigNumber(Class,String)) Mensaje de Error:"
					+ e.getMessage());
			throw e;

		}
		return objFormato;
			}

	/**
	 * Analiza la cadena si tiene formato porcentual
	 *
	 * @param clase
	 * @param strNumber
	 * @return Object
	 */
	public static Object parsePercent(Class<?> clase, String strNumber)
	{
		Object objFormato = null;
		try
		{
			CtrlUtilidad.getFormateadorPorcentaje();
			if (clase.getName().equals(Double.class.getName()))
			{
				objFormato = new Double(FORMATEADORPORCENTAJE.parse(strNumber)
						.doubleValue());
			}
			else if (clase.getName().equals(Long.class.getName()))
			{
				objFormato = new Long(FORMATEADORPORCENTAJE.parse(strNumber)
						.longValue());
			}
			else if (clase.getName().equals(BigDecimal.class.getName()))
			{
				objFormato = new BigDecimal(FORMATEADORPORCENTAJE.parse(
						strNumber).doubleValue());
			}
			else if (clase.getName().equals(Integer.class.getName()))
			{
				objFormato = new Integer(FORMATEADORPORCENTAJE.parse(strNumber)
						.intValue());
			}
			else if (clase.getName().equals(Float.class.getName()))
			{
				objFormato = new Float(FORMATEADORPORCENTAJE.parse(strNumber)
						.floatValue());
			}
			else if (clase.getName().equals(Short.class.getName()))
			{
				objFormato = new Short(FORMATEADORPORCENTAJE.parse(strNumber)
						.shortValue());
			}

		}
		catch (Exception e)
		{
			System.out
			.println("(CtrlUtilidad.parsePercent(Class,String)) Mensaje de Error:"
					+ e.getMessage());
		}
		return objFormato;
	}

	/**
	 * Adiciona un numero de a?os determinado a una fecha
	 *
	 * @param fecha
	 * @param anios
	 * @return java.util.Date
	 */
	public static Date addYears(Date fecha, int anios)
	{
		try
		{
			Calendar calendar = CtrlUtilidad.getFormateadorFecha()
					.getCalendar();
			calendar.setTime(fecha);
			calendar.add(Calendar.YEAR, anios);
			fecha = calendar.getTime();
		}
		catch (Exception e)
		{
			System.out
			.println("(CtrlUtilidad.addYears(Date,int)) Mensaje de Error: "
					+ e.getMessage());
		}
		return fecha;
	}

	/**
	 * Adiciona un n�mero de meses determinado a una fecha
	 *
	 * @param fecha
	 * @param meses
	 * @return java.util.Date
	 */
	public static Date addMonths(Date fecha, int meses)
	{
		try
		{
			Calendar calendar = CtrlUtilidad.getFormateadorFecha()
					.getCalendar();
			calendar.setTime(fecha);
			calendar.add(Calendar.MONTH, meses);
			fecha = calendar.getTime();
		}
		catch (Exception e)
		{
			System.out
			.println("(CtrlUtilidad.addMonths(Date,int)) Mensaje de Error: "
					+ e.getMessage());
		}
		return fecha;
	}

	/**
	 * Adiciona un n�mero de d�as determinado a una fecha
	 *
	 * @param fecha
	 * @param dias
	 * @return java.util.Date
	 */
	public static Date addDays(Date fecha, int dias)
	{
		try
		{
			Calendar calendar = CtrlUtilidad.getFormateadorFecha()
					.getCalendar();
			calendar.setTime(fecha);
			calendar.add(Calendar.DATE, dias);
			fecha = calendar.getTime();
		}
		catch (Exception e)
		{
			System.out
			.println("(CtrlUtilidad.addDays(Date,int)) Mensaje de Error: "
					+ e.getMessage());
		}
		return fecha;
	}

	/**
	 * Retorna el nombre del usuario que ha iniciado sesion en el sistema
	 * operativo del Servidor
	 *
	 * @return
	 * @throws JKalyptraException
	 *             String
	 */
	public String getUserSystem() throws Exception
	{
		String user = System.getProperty("user.name");
		if (user == null || user.length() < 1)
		{
			System.out
			.println("CtrlUtilidades.getUserSystem() Mensaje de Error: ");
			throw new Exception("jkse-00069");
		}
		return user;
	}

	/**
	 * Retorna la direccion IP del host Servidor
	 *
	 * @return String
	 * @throws JKalyptraException
	 */
	public String getHostAddress() throws Exception
	{
		try
		{
			return InetAddress.getLocalHost().getHostAddress();
		}
		catch (UnknownHostException e)
		{
			System.out
			.println("CtrlUtilidades.getHostAddress() Mensaje de Error: "
					+ e.getMessage());
			throw new Exception("jkse-00069");
		}
	}

	/**
	 * Retorna el nombre de la maquina Servidor
	 *
	 * @return String
	 * @throws JKalyptraException
	 */
	public String getHostName() throws Exception
	{
		try
		{
			return InetAddress.getLocalHost().getHostName();
		}
		catch (UnknownHostException e)
		{
			System.out
			.println("CtrlUtilidades.getHostName() Mensaje de Error: "
					+ e.getMessage());
			throw new Exception("jkse-00069");
		}
	}

	/**
	 * Retorna una conexi�n a la base de datos
	 *
	 * @param strUrl
	 * @param strUsuario
	 * @param strPassword
	 * @return Connection
	 * @throws JKalyptraException
	 */
	public static Connection getConnection(String strUrl, String strUsuario,
			String strPassword) throws Exception
			{
		Connection con = null;
		try
		{
			con = DriverManager.getConnection(strUrl, strUsuario, strPassword);
		}
		catch (Exception jke)
		{
			throw new Exception("jkin-00001");
		}
		return con;
			}

	/**
	 * Retorna un valor con formato si tiene Mascara
	 *
	 * @param objeto
	 * @param strMascara
	 * @return String
	 */
	public String formatValor(Object objeto, String strMascara)
	{
		String strValor = null;
		try
		{
			if (objeto instanceof java.sql.Timestamp)
			{
				if (strMascara != null && strMascara.length() > 0) strValor = CtrlUtilidad
						.getINSTANCIA()
						.getFecha(
								new java.util.Date(
										((java.sql.Timestamp) objeto).getTime()),
										strMascara);
				else strValor = CtrlUtilidad.getINSTANCIA().getFecha(
						new Date(((java.sql.Timestamp) objeto).getTime()));
			}
			else if (objeto instanceof java.util.Date)
			{
				if (strMascara != null && strMascara.length() > 0) strValor = CtrlUtilidad
						.getINSTANCIA().getFecha((java.util.Date) objeto,
								strMascara);
				else strValor = CtrlUtilidad.getINSTANCIA().getFecha(
						(Date) objeto);

			}
			else if (!(objeto instanceof String)
					&& !(objeto instanceof StringBuffer))
			{
				if (strMascara != null && strMascara.length() > 0) strValor = CtrlUtilidad
						.formatBigNumber(objeto, new Integer(strMascara)
						.intValue());
				else strValor = CtrlUtilidad.formatBigNumber(objeto);
			}
			else strValor = objeto.toString();
		}
		catch (Exception e)
		{
			System.out
			.println("(CtrlUtilidad.formatValor(Object,String)) Mensaje de Error:"
					+ e.getMessage());
		}
		return strValor;
	}

	public static String formatToBigDecimal(Double value)
	{
		String strValue = null;

		strValue = new BigDecimal(value.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();

		return strValue;
	}

	/**
	 * Coloca la primera letra de una palabra en mayuscula.
	 *
	 * @param node
	 * @return String
	 */
	public static String capitalize(String node)
	{
		String strInicial = String.valueOf(node.charAt(0));
		strInicial = strInicial.toUpperCase();
		return strInicial.concat(node.substring(1, node.length()));
	}

	/**
	 * retorna true si el A?o es bisiesto, de lo contrario retorna false
	 *
	 * @return boolean
	 */
	public static boolean isAnioBisiesto(Byte anio)
	{
		int intAnio = anio.intValue();
		return ((intAnio % 4 == 0 && intAnio % 100 != 0) || (intAnio % 400 == 0));
	}

	public static String converFecha(String fecha)
	{
		String dia = "";
		String mes = "";
		String ano = "";

		String strFecha = "";

		try
		{
			if (fecha.length() == 8)
			{
				dia = fecha.substring(0, 2) + "-";
				mes = fecha.substring(2, 4) + "-";
				ano = fecha.substring(4, 8);
				strFecha = dia + mes + ano;

			}
		}
		catch (NullPointerException e)
		{
		}

		return strFecha;
	}

	public static String converHora(String horaAll)
	{
		String hora = "";
		String min = "";
		String seg = "";

		String strHora = "";

		try
		{
			if (horaAll.length() > 1)
			{
				hora = horaAll.substring(0, 2) + ":";
				min = horaAll.substring(2, 4) + ":";
				seg = horaAll.substring(4, 6);

				strHora = hora + min + seg;
			}
		}
		catch (NullPointerException e)
		{
		}

		return strHora;
	}

	public static String getCodigoCombo(String labelCombo)
	{
		int posI = labelCombo.indexOf("(");
		int posF = labelCombo.indexOf(")");
		String codigoLabelCombo = labelCombo.substring(posI + 1, posF);
		return codigoLabelCombo;
	}

	/**
	 * CARL - Metodo que permite redondear alos decimales que se pasan como parametros.
	 *
	 * @param numero
	 * @param decimales
	 * @return
	 */
	public static double redondear( double numero, int decimales ) {
		return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
	}

	// Metodo que retorna un id partiendo de la hora actual
	public static String generaId(Long numId)
	{                
		Calendar calendario = Calendar.getInstance();
		Long time = (calendario.getTimeInMillis())+numId;
		String result = time.toString(); 
		String result2 = time.toString().substring(result.length()-10, result.length()); 
		return result2;
	}


	public static void main(String args[])
	{
		String fecha = converHora("123819");
		System.out.println(fecha);

		System.out.println(redondear(2.45631456, 4));

	}

	public static Hashtable<String, Integer> obtenerCoordenadasCentradas(int pAnchoComponente,int pAltoComponente){
		Hashtable<String, Integer> coordenas = new Hashtable<String,Integer>();
		Dimension dimensionPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Double coordenadaX =((dimensionPantalla.getWidth()/2)-(pAnchoComponente-(pAnchoComponente/2)));
		Double coordenadaY =((dimensionPantalla.getHeight()/2)-(pAltoComponente-(pAltoComponente/2)));
		coordenas.put("x", coordenadaX.intValue());
		coordenas.put("y", coordenadaY.intValue());
		return coordenas;
	}

	public static String obtenerSistemaOperativo(){
		return System.getProperty("os.name");
	}

	public static Properties cargarArchivoComandos() throws Exception{
		try {
			/**ESTE CODIGO CARGABA EL ARCHIVO DE COMANDO DE AFUERA EN LA MISMA CARPETA DONDE ESTABA EL JAR DE CODESASOCKETWRAPPER*/
			/*Propiedades pro = new Propiedades();
			String path = URLDecoder.decode(ControladorEtiquetas.class.getProtectionDomain().
					getCodeSource().getLocation().getPath(),"UTF-8");
			
			path = path.substring(0,path.lastIndexOf("/"));
			LogsManager.getInstance().info("Se cargara comandos de la ruta ==> "+path+"/comandos.properties");
			pro.load(new FileInputStream(path+"/comandos.properties"));	*/

			return convertResourceBundleToProperties(ControladorEtiquetas.comandos);			
		} catch (Exception e) {
			throw new Exception("Error cargando archivo comandos");
		}
	}
	
	 private static Properties convertResourceBundleToProperties(ResourceBundle resource) {
		 Properties properties = new Properties();

		 Enumeration<String> keys = resource.getKeys();
	     while (keys.hasMoreElements()) {
	    	 String key = (String) keys.nextElement();
	         properties.put(key, resource.getString(key));
	     }

	     return properties;
	}

	public static void validarPuerto(String pto , String label) throws Exception{
		if (pto == null || pto.equals("")) {
			throw new Exception(label+" "+ControladorEtiquetas.sistemaMsg.getString("error_obligatorio"));
		}else {
			try {
				Integer.parseInt(pto);
			} catch (Exception e) {
				throw new Exception(label+" "+ControladorEtiquetas.sistemaMsg.getString("error_numerico"));
			}
		}
	}
	
	public static void validarCadena(String valor, String label)  throws Exception{
		if (valor == null || valor.trim().isEmpty()) {
			throw new Exception(label+" "+ControladorEtiquetas.sistemaMsg.getString("error_obligatorio"));
		}
	}

	static private final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
	static private Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);

	public static void validarIp(String ip , String label)throws Exception{

		boolean isIp=false;
		if (ip == null || ip.equals("")) {
			throw new Exception(label+" "+ControladorEtiquetas.sistemaMsg.getString("error_obligatorio"));
		}else {
			isIp= IPV4_PATTERN.matcher(ip).matches();
			if (!isIp) {
				throw new Exception(label+" "+ControladorEtiquetas.sistemaMsg.getString("formato_ip"));				
			}				
		}

	}



}
