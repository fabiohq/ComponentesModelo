/*
 * XML.java
 *
 * Created on December 20, 2006, 3:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.codesa.socketcodesaoperadorexterno.utilidades;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import co.com.codesa.socketcodesaoperadorexterno.general.LogsManager;

/**
 *
 * @author Nelson Villamizar
 */
public class XML {

	private static String strPathArchivoXML = "";
	private static String strArchivoDTD = "";
	private static Logger logger;

	/** Creates a new instance of XML */
	public XML() {

		if(LogsManager.getLogger() == null){
			logger = LogsManager.getInstance();
		}
	}

	public static void guardar(String fileName, Document xmlDocument,
			String strArchivoDTD) {

		try {
			Source source = new DOMSource(xmlDocument);
			File transactions = new File(fileName);
			Result result = new StreamResult(transactions);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer xformer = factory.newTransformer();

			xformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, strArchivoDTD);
			xformer.transform(source, result);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());

		}

	}

	public static void modificarValorXML(String path, String nombreArchivo,
			String nombreNodo, String valor) {
		// String path=PathApplication.getInstance();

//		if (path.trim().equals("")) {
//			path = PathApplication.getInstance().getRuta();
//		}

		strPathArchivoXML = nombreArchivo + ".xml";

//		strArchivoDTD = (new StringBuilder()).append(nombreArchivo)
//				.append(".dtd").toString();
		strArchivoDTD = strPathArchivoXML.replace(".xml", ".dtd");

		// System.out.println(strPathArchivoXML);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(true);

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
		//	builder.setEntityResolver(new DtdResolver());
		//	System.out.println(strPathArchivoXML);
			Document doc = builder.parse(new File(strPathArchivoXML));
			Node configuracion = doc.getFirstChild();

			NodeList lstParametros = doc.getElementsByTagName(nombreNodo);

			if (lstParametros.getLength() == 1) {
				Node parametro = lstParametros.item(0);
				if (parametro.hasChildNodes()) {
					parametro.getFirstChild().setNodeValue(valor);
				} else {
					Node temp = doc.createTextNode(valor);
					parametro.appendChild(temp);
					// parametro.setNodeValue(valor);
					// parametro.c
				}
			} else {
				Node newnodo = doc.createElement(nombreNodo);
				newnodo.setNodeValue(valor);
				configuracion.appendChild(newnodo);
			}

			guardar(strPathArchivoXML, doc, strArchivoDTD);

		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	public static String retornarValorXML(String path, String nombreArchivo,
			String nombreNodo) {
		return leerValorXML(path, nombreArchivo, nombreNodo);

	}

	public static String leerValorXML(String path, String nombreArchivo,
			String nombreNodo) {

		String respuesta = "";
		int i = 0;
//		if (path.trim().equals("")) {
//			path = PathApplication.getInstance().getRuta();
//		}

		strPathArchivoXML = path + nombreArchivo + ".xml";
		System.out.println(strPathArchivoXML);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		while (i < 2) {// intentar dos veces

			if (i > 0) {
				System.out.println("Reintentando lectura XML Nodo: "
						+ nombreNodo + " del archivo " + strPathArchivoXML);
				logger.log(Level.INFO, "Reintentando lectura XML Nodo: "
						+ nombreNodo + " del archivo " + strPathArchivoXML);
			}
			try {
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(new File(strPathArchivoXML));
				NodeList doc = document.getElementsByTagName(nombreNodo);
				if (doc.getLength() > 0) {
					Element Etemp1 = (Element) doc.item(0);
					// System.out.println("Valor " +nombreNodo+
					// " :"+Etemp1.getFirstChild().getNodeValue());
					respuesta = Etemp1.getFirstChild().getNodeValue();
				}
				break;

			} catch (ParserConfigurationException e) {
				logger.log(
						Level.SEVERE,
						"No se pudo leer el nodo :"
								+ nombreNodo
								+ " del archivo "
								+ strPathArchivoXML
								+ ". No se ha podido crear una instancia de DocumentBuilder. Error: "
								+ e.toString());

			} catch (SAXException e) {
				// System.err.println("Error SAX al parsear el archivo");
				logger.log(Level.SEVERE,
						"No se pudo leer el nodo :" + nombreNodo
								+ " del archivo " + strPathArchivoXML
								+ ". Error SAX al parsear el archivo. Error: "
								+ e.toString());
			} catch (IOException e) {
				logger.log(
						Level.SEVERE,
						"No se pudo leer el nodo :"
								+ nombreNodo
								+ " del archivo "
								+ strPathArchivoXML
								+ ". Se ha producido un error de entrada salida. Error: "
								+ e.toString());
			} catch (Exception e) {
				logger.log(Level.SEVERE, "No se pudo leer el nodo :"
						+ nombreNodo + " del archivo " + strPathArchivoXML
						+ ". Error inesperado. Error: " + e.toString());
			}
			i++;
		}

		return respuesta;

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		try {
			// modificarValorXML( "", "config", "listener_db", "1520" );
			System.out.println(leerValorXML("",
					Constantes.Propiedades.PATH_CONFIGSERVER, Constantes.Propiedades.NODO_CONFIGSERVER_IP));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class DtdResolver implements EntityResolver {

	public InputSource resolveEntity(String publicId, String systemId) {
		String strFileName = systemId.substring(systemId.lastIndexOf('/') + 1);
		return new InputSource(getClass().getResourceAsStream(strFileName));
	}

}