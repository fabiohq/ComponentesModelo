package co.com.codesa.socketcodesaoperadorexterno.utilidades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author  Administrator
 */

public class Archivo implements Runnable{
    
    String nombreArchivo;
    String texto;
    String extension;
    
    /** Creates a new instance of Archivo */
    public Archivo(String nombreArchivo,String texto) {
        this.nombreArchivo=nombreArchivo;
        this.texto=texto;
        this.extension="txt";
    }
    
    public Archivo(String nombreArchivo,String extension,String texto) {
        this.nombreArchivo=nombreArchivo;
        this.texto=texto;
        this.extension=extension;
    }
    
    public synchronized void guardarArchivo(){
        PrintWriter salidaArchivo=null;
        try{
            String path=General.retornarValorXML("configserver.xml", "path_log");
            String historial=path+this.nombreArchivo+"."+extension;
            //System.out.println("Archivo "+historial);
            salidaArchivo = new PrintWriter(new BufferedWriter
            (new FileWriter(historial,true)));
            salidaArchivo.println(this.texto);
            salidaArchivo.close();
        }catch (Exception e){
            System.out.println("Error almacenando en el archivo, el error es "+e.toString());
        }finally{
            salidaArchivo.close();
        }
    }
    
    public void run(){
         try{
            this.guardarArchivo();
        }catch (Exception e){
            System.out.println("Error en el run de guardar el archivo, el error es "+e.toString());
        }finally{
            
        }
    }

    
}
