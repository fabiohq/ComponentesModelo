/*
 * MyDOMParserBean.java
 *
 * Created on 30 de marzo de 2004, 9:35
 */

package co.com.codesa.socketcodesaoperadorexterno.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class MyDOMParserBean 
implements java.io.Serializable {
    
    
    
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public MyDOMParserBean() {
   }

   public static Document 
   getDocument(String file) throws Exception {
         
    // Step 1: create a DocumentBuilderFactory
     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    // Step 2: create a DocumentBuilder
     DocumentBuilder db = dbf.newDocumentBuilder();

    // Step 3: parse the input file to geta Document object
     Document doc = db.parse(new File(file));
     return doc;
   }    
   
   
   
}
