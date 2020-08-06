/*
 * PathAplication.java
 *
 * Created on 30 de marzo de 2004, 14:40
 */

package co.com.codesa.socketcodesaoperadorexterno.xml;
import java.io.File;

/**
 *
 * @author  Administrador
 */
public class PathApplication{
  
    /** Creates a new instance of PathAplication */
	private static PathApplication path=new PathApplication();
	private String ruta;

	/** Creates a new instance of PathAplication */
	private PathApplication() {
	}

	public static PathApplication getInstance() {
		if(path == null){
			path = new PathApplication();
		}
		return path;
	}

	public String getRuta() {
		if (ruta == null){

			ruta = System.getProperty("user.dir") + System.getProperty("file.separator");
//			ruta = System.getProperty("java.class.path");
//			String Separator = System.getProperty("path.separator");
//			StringTokenizer st  = new StringTokenizer(ruta, Separator );

//			while ( st.hasMoreElements() ){
//				ruta = (String)st.nextElement();
//			}

			File tempf=(new File(ruta));

			if (tempf.isFile()){
				int index=ruta.lastIndexOf(System.getProperty("file.separator"));

				if (index>0)
					ruta=ruta.substring(0,index);
				else{
					ruta= "";
					System.out.println("path listo:"+ruta);
					return ruta;
				}
			}
			//ruta=ruta+System.getProperty("file.separator");
			System.out.println("path listo:"+ruta);
		}
		return ruta;
	}
}
