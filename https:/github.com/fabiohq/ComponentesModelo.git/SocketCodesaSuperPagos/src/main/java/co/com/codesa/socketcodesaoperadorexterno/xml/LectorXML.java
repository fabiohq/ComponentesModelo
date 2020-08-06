/*
 * EjemploXML.java
 *
 * Created on 30 de marzo de 2004, 9:37
 */

package co.com.codesa.socketcodesaoperadorexterno.xml;

import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 *
 * @author  Administrador
 */
public class LectorXML {
    /*MyDOMParserBean domparser;
    Document doc;*/
    Vector vDatos;
    private String valor;
    /** Creates a new instance of EjemploXML */
    public LectorXML() {
        this.valor="";
        /*try{
           MyDOMParserBean domparser=new MyDOMParserBean();
           java.net.URL path=getClass().getResource("/tomcat406/webapps/impuestosWeb/WEB-INF/classes/co/com/impuestos/temp/pool.xml");
           System.out.println(path.toString());
           String path1=path.toExternalForm();
           Document doc = domparser.getDocument("tomcat406\\webapps\\impuestosWeb\\WEB-INF\\classes\\co\\com\\impuestos\\temp\\pool.xml");
            this.buscarPropiedadXML(doc,"","driver");
            
        }catch (Exception e){
            System.out.println("Error, el error es "+e.toString());
        }*/
    }
    
    public Vector retornarDatos(Node node){
        vDatos=new Vector();
        try{
            this.traverseTree(node,"");
        }catch (Exception e){
            System.out.println("Error, el error es "+e.toString());
        }
        return vDatos;
    }
    
    private void traverseTree(Node node,String nombreNodo) throws Exception {
       
        DatosXMLBean datos=null;
        
        if(node == null) {
           return;
        }
        int type = node.getNodeType();
        //System.out.println("type "+node.getNodeName());
        
        switch (type) {
           // handle document nodes
           case Node.DOCUMENT_NODE: {
             traverseTree(((Document)node).getDocumentElement(),node.getNodeName());
             break;
          }
          // handle element nodes
          case Node.ELEMENT_NODE: {
              
            String elementName = node.getNodeName();
             if(elementName.equals("motor")) {
              // System.out.println("</tr><tr>");
             }
             NodeList childNodes = node.getChildNodes();      
             if(childNodes != null) {
                int length = childNodes.getLength();
                for (int loopIndex = 0; loopIndex < 
                length ; loopIndex++)
                {
                   traverseTree
                   (childNodes.item(loopIndex),node.getNodeName());
                 }
              }
              break;
           }
           // handle text nodes
           case Node.TEXT_NODE: {
               
              String data = 
              node.getNodeValue().trim();
              if((data.indexOf("\n")  <0) && (data.length() > 0)) {
                datos=new DatosXMLBean();
                datos.setNombreNodo(nombreNodo);
                datos.setValorNodo(data);
                vDatos.add(datos);
              
              }
            }
         }
    }
    
    /*
     *Busca en un xml determinada propiedad y me retorna su valor
     * @param PAsa como parametro el nodo a leer, inicialmente se pasa un tipo Document y el leera el archivo recursivamente
     * @param nombreNodo Inicialmente se pasa vacio, ya que en sus llamados recursivos el asigna este valor enviando el nombre del nodo que acaba de leer
     * @param nombreBuscar Se pasa como parametro el nombre del tag que se desea buscar para que me retorne su valor
     */
    public void buscarPropiedadXML(Node node,String nombreNodo,String nombreBuscar) throws Exception {
       
        DatosXMLBean datos=null;
        if(node == null) {
           return ;
        }
        int type = node.getNodeType();
        //System.out.println("type "+node.getNodeName());
        
        switch (type) {
           // handle document nodes
           case Node.DOCUMENT_NODE: {
             buscarPropiedadXML(((Document)node).getDocumentElement(),node.getNodeName(),nombreBuscar);
             break;
          }
          // handle element nodes
          case Node.ELEMENT_NODE: {
              String elementName = node.getNodeName();
            
             NodeList childNodes = node.getChildNodes();      
             if(childNodes != null) {
                int length = childNodes.getLength();
                for (int loopIndex = 0; loopIndex < 
                length ; loopIndex++)
                {
                    buscarPropiedadXML(childNodes.item(loopIndex),node.getNodeName(),nombreBuscar);
                 }
              }
              break;
           }
           // handle text nodes
           case Node.TEXT_NODE: {
               
              String data = 
              node.getNodeValue().trim();
              if((data.indexOf("\n")  <0) && (data.length() > 0)) {
                 if (nombreBuscar.equals(nombreNodo)){
                    this.valor=data;
                    
                 }
              }
              break;
            }
         }
        
    }

    /*
     *Busca en un xml ciertas propiedaes pasadas como parametro y me devuelve la concatenacion de sus valores
     * @param PAsa como parametro el nodo a leer, inicialmente se pasa un tipo Document y el leera el archivo recursivamente
     * @param nombreNodo Inicialmente se pasa vacio, ya que en sus llamados recursivos el asigna este valor enviando el nombre del nodo que acaba de leer
     * @param nombreBuscar Se pasa como parametro el nombre del tag que se desea buscar para que me retorne su valor
     */
    public void buscarPropiedadXML(Node node,String nombreNodo,String[] nombresBuscar) throws Exception {
       
        DatosXMLBean datos=null;
        if(node == null) {
           return ;
        }
        int type = node.getNodeType();
        //System.out.println("type "+node.getNodeName());
        
        switch (type) {
           // handle document nodes
           case Node.DOCUMENT_NODE: {
             buscarPropiedadXML(((Document)node).getDocumentElement(),node.getNodeName(),nombresBuscar);
             break;
          }
          // handle element nodes
          case Node.ELEMENT_NODE: {
              String elementName = node.getNodeName();
            
             NodeList childNodes = node.getChildNodes();      
             if(childNodes != null) {
                int length = childNodes.getLength();
                for (int loopIndex = 0; loopIndex < 
                length ; loopIndex++)
                {
                    buscarPropiedadXML(childNodes.item(loopIndex),node.getNodeName(),nombresBuscar);
                 }
              }
              break;
           }
           // handle text nodes
           case Node.TEXT_NODE: {
               
              String data = 
              node.getNodeValue().trim();
              if((data.indexOf("\n")  <0) && (data.length() > 0)) {
                 for (int i=0;i<nombresBuscar.length;i++){
                    if (nombresBuscar[i].equals(nombreNodo)){
                        this.valor=this.valor+" "+data;
                    }
                 }
              }
              break;
            }
         }
        
    }
    
    /*
     *Retorna el valor correspondiene a un determinado nodo.
     *Antes de hacer este get se debe de haber llenado el valor
     *con el metodo buscarPropiedadXML
     */
    public String getValor(){
        return this.valor;
    }
    
    /**
     * @param args the command line arguments
     */
  /*  public static void main(String[] args) {
        try{
            LectorXML ejemplo=new LectorXML();
            

        }catch (Exception e){
            System.out.println("Error, el error es "+e.toString());
        }
    }
    */
}
