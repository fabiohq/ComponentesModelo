/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.codesa.socketcodesaoperadorexterno.socket;

/**
 *
 * @author McDeveloper
 */
public class ControlIdCase {

    private static ControlIdCase INSTANCIA;

    private long caseId;

    public static ControlIdCase getInstancia()
	{
		if (INSTANCIA == null) INSTANCIA = new ControlIdCase();
		return INSTANCIA;
	}

     private ControlIdCase()
	{
		super();

        caseId = new Long("0").longValue();
	}

     public long getNextCaseId(){

         caseId = caseId+1;

         return caseId;
     }

     public void getInitCaseId(){

         caseId = new Long("0").longValue();
     }
}
