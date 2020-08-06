/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.codesa.socketcodesaoperadorexterno.utilidades;

import java.util.StringTokenizer;

/**
 *
 * @author mauricio.lopez
 */
public class TransformStandardXml {

    public TransformStandardXml()
    {
        
    }


    /*
     * Trama venta microseguros
      * EJ: 661,0,CV199912,1116,07/09/2009,14:44:55,2,0000000002,4159201,ASEGURADORA COLSEGUROS S.A.,860.026.182-5,10000.0
      * EJ: 661,1,CV199912,11,Error de Calculo en Valor Prima
      *
     */
    private static String transformaEstandar661(  String encabezadoA,
                                                 String encabezadoB,
                                                 String encabezadoC,
                                                 String encabezadoD,
                                                 String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String certificadoIndividual = "";
        String fechaExpedicion = "";
        String horaExpedicion = "";
        String vigenciaSeguro = "";
        String nroSerieOtrosServNuevo = "";
        String nroPolizaMatriz = "";
        String razonSocialCia = "";
        String nitCiaSeguros = "";
        String deducibleGenerales = "";
        String consolidado = "";



        String codError = "";
        String mensajeError = "";

        String tramaXml = "";

        if( resultado.equals("0") )
        {
             certificadoIndividual = strTrama.nextToken();
             fechaExpedicion = strTrama.nextToken();
             horaExpedicion = strTrama.nextToken();
             vigenciaSeguro = strTrama.nextToken();
             nroSerieOtrosServNuevo = strTrama.nextToken();
             nroPolizaMatriz = strTrama.nextToken();
             razonSocialCia = strTrama.nextToken();
             nitCiaSeguros = strTrama.nextToken();
             deducibleGenerales = strTrama.nextToken();
             consolidado = strTrama.nextToken();

             //661,0,CV199912,1116,07/09/2009,14:44:55,2,0000000002,4159201,ASEGURADORA COLSEGUROS S.A.,860.026.182-5,10000.0
             tramaXml = "<root>"+  //1,4,2,88,121,122,123,43,124,125,126,127,65
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"88\">"+certificadoIndividual+"</c>"+
                            "<c p=\"121\">"+fechaExpedicion+"</c>"+
                            "<c p=\"122\">"+horaExpedicion+"</c>"+
                            "<c p=\"123\">"+vigenciaSeguro+"</c>"+
                            "<c p=\"43\">"+nroSerieOtrosServNuevo+"</c>"+
                            "<c p=\"124\">"+nroPolizaMatriz+"</c>"+
                            "<c p=\"125\">"+razonSocialCia+"</c>"+
                            "<c p=\"126\">"+nitCiaSeguros+"</c>"+
                            "<c p=\"127\">"+deducibleGenerales+"</c>"+
                            "<c p=\"65\">"+consolidado+"</c>"+
                        "</root>";


        }else
        {
                codError = strTrama.nextToken();
                mensajeError = strTrama.nextToken();
                tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                    "<c p=\"5\">"+codError+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";
        }

        return tramaXml;

    }




    /*

     respConsulVlrAsegurado = "666,0,"+loginUsr+","+
                                                              seguroBean.getModuloSeguro().getPlanSeguro().getValoAsegurado();


     respConsulVlrAsegurado = "666,1,"+loginUsr
                             +",99,"+ seguroBean.getResultado();

     *
     * Consulta Valor Asegurado
      * EJ: 666,0,CV199912,280000.0
      * EJ: 661,1,CV199912,25,Paramatrizacion no encontrada
     */
    private static String transformaEstandar666(  String encabezadoA,
                                                 String encabezadoB,
                                                 String encabezadoC,
                                                 String encabezadoD,
                                                 String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String vlrAsegurado = "";

        String codError = "";
        String mensajeError = "";

        String tramaXml = "";

        if( resultado.equals("0") )
        {
             vlrAsegurado = strTrama.nextToken();


             //661,0,CV199912,1116,07/09/2009,14:44:55,2,0000000002,4159201,ASEGURADORA COLSEGUROS S.A.,860.026.182-5,10000.0
             tramaXml = "<root>"+  //1,4,2,88,121,122,123,43,124,125,126,127,65
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"114\">"+vlrAsegurado+"</c>"+
                        "</root>";


        }else
        {
                codError = strTrama.nextToken();
                mensajeError = strTrama.nextToken();
                tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                    "<c p=\"5\">"+codError+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";
        }

        return tramaXml;

    }




    /*
     *   espConsulCertVigen = "665,0,"+loginUsr+","+
                                                           siniestro.getNombre()+","+
                                                           siniestro.getApellidos()+","+
                                                           siniestro.getCadenaCertificados();


        respConsulCertVigen = "665,1,"+loginUsr
                                 +","+ siniestro.getCodError()
                                 +","+ siniestro.getDescError();
     *
     *
     * Consulta Certificados Vigentes
      * EJ: 665,0,CV199912,Juan Pablo,Sanchez,1116;1;3;620000
      * EJ: 665,1,CV199912,25,Paramatrizacion no encontrada
     */
    private static String transformaEstandar665(  String encabezadoA,
                                                 String encabezadoB,
                                                 String encabezadoC,
                                                 String encabezadoD,
                                                 String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String nombres = "";
        String apellidos = "";
        String cadenaCertificados = "";


        String codError = "";
        String mensajeError = "";

        String tramaXml = "";

        if( resultado.equals("0") )
        {

             nombres = strTrama.nextToken();;
             apellidos = strTrama.nextToken();;
             cadenaCertificados = strTrama.nextToken();

             tramaXml = "<root>"+  //1,4,2,106,107,130
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"106\">"+nombres+"</c>"+
                            "<c p=\"107\">"+apellidos+"</c>"+
                            "<c p=\"130\">"+cadenaCertificados+"</c>"+
                        "</root>";


        }else
        {
                codError = strTrama.nextToken();
                mensajeError = strTrama.nextToken();
                tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                    "<c p=\"5\">"+codError+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";
        }

        return tramaXml;

    }



    /*
     *  respAvisoSiniestro = "662,0,"+loginUsr;

        respAvisoSiniestro = "662,1,"+loginUsr
                                 +","+ siniestro.getCodError()
                                 +","+ siniestro.getDescError();
     *
     *
     * Aviso de Siniestro
      * EJ: 662,0,CV199912
      * EJ: 662,1,CV199912,25,Fallas en el sistema
     */
    private static String transformaEstandar662(  String encabezadoA,
                                                 String encabezadoB,
                                                 String encabezadoC,
                                                 String encabezadoD,
                                                 String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String codError = "";
        String mensajeError = "";

        String tramaXml = "";

        if( resultado.equals("0") )
        {

             tramaXml = "<root>"+  //1,4,2,106,107,130
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                        "</root>";


        }else
        {
                codError = strTrama.nextToken();
                mensajeError = strTrama.nextToken();
                tramaXml = "<root>"+
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                    "<c p=\"5\">"+codError+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";
        }

        return tramaXml;

    }


    /*
     *  respConsultaSiniestro = "663,0,"+loginUsr
                                                        +","+  siniestro.getNombre()
                                                        +","+  siniestro.getApellidos()
                                                        +","+  siniestro.getCadenaSiniestros();


        respConsultaSiniestro = "663,1,"+loginUsr
                                 +","+ siniestro.getCodError()
                                 +","+ siniestro.getDescError()+"~";
     *
     *
     * Consulta de Siniestros
      * EJ: 663,0,CV199912,pedro,pablo,1046;3;4;3;APROBADO;1517241;1500000;26/05/2009;18:01:0;&1047;3;4;3;APROBADO;1603448;1603000;26/05/2009;18:01:0;
      * EJ: 663,1,CV199912,25,Fallas en el sistema
     */
    private static String transformaEstandar663(  String encabezadoA,
                                                 String encabezadoB,
                                                 String encabezadoC,
                                                 String encabezadoD,
                                                 String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String cadenaSiniestro = "";

        String codError = "";
        String mensajeError = "";

        String tramaXml = "";

        if( resultado.equals("0") )
        {
             cadenaSiniestro = strTrama.nextToken();

             tramaXml = "<root>"+  //1,4,2,106,107,130
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                        "</root>";


        }else
        {
                codError = strTrama.nextToken();
                mensajeError = strTrama.nextToken();
                tramaXml = "<root>"+
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                    "<c p=\"5\">"+codError+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";
        }

        return tramaXml;

    }


    /*
     *  respPagoSiniestro = "664,0,"+loginUsr +","+ siniestro.getDocumentoVendedor()
                                                                      +","+ siniestro.getCedula()
                                                                      +","+ siniestro.getFechaPago()
                                                                      +","+ siniestro.getHoraPago()
                                                                      +","+ siniestro.getCodServicio();

        respPagoSiniestro = "664,1,"+loginUsr+",99,"+  siniestro.getResultado();

     *
     *
     * Consulta de Siniestros
      * EJ: 663,0,CV199912,pedro,pablo,1046;3;4;3;APROBADO;1517241;1500000;26/05/2009;18:01:0;&1047;3;4;3;APROBADO;1603448;1603000;26/05/2009;18:01:0;
      * EJ: 663,1,CV199912,25,Fallas en el sistema
     */
    private static String transformaEstandar664(  String encabezadoA,
                                                 String encabezadoB,
                                                 String encabezadoC,
                                                 String encabezadoD,
                                                 String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String documentoVendedor = "";
        String cedulaAsegurado = "";
        String fechaPago = "";
        String horaPago = "";
        String codServicio = "";

        String codError = "";
        String mensajeError = "";

        String tramaXml = "";

        if( resultado.equals("0") )
        {
             documentoVendedor = strTrama.nextToken();
             cedulaAsegurado = strTrama.nextToken();
             fechaPago = strTrama.nextToken();
             horaPago = strTrama.nextToken();
             codServicio = strTrama.nextToken();

             tramaXml = "<root>"+  //1,4,2,115,105,134,135,45
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"115\">"+documentoVendedor+"</c>"+
                            "<c p=\"105\">"+cedulaAsegurado+"</c>"+
                            "<c p=\"134\">"+fechaPago+"</c>"+
                            "<c p=\"135\">"+horaPago+"</c>"+
                            "<c p=\"45\">"+codServicio+"</c>"+
                        "</root>";


        }else
        {
                codError = strTrama.nextToken();
                mensajeError = strTrama.nextToken();
                tramaXml = "<root>"+
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                    "<c p=\"5\">"+codError+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";
        }

        return tramaXml;

    }



    /*
     * Trama consulta usario asegurar
      * EJ: 222,0,Juan Pablo,Sanchez ,33,5554466,Cra 67 -34,02/07/1986
      * EJ: 222,1,CV199912,55,Usuario no existe
     */
    private static String transformaEstandar222(  String encabezadoA,
                                                 String encabezadoB,
                                                 String encabezadoC,
                                                 String encabezadoD,
                                                 String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();


        String nombre = "";
        String apellido1 = "";
        String apellido2 = "";
        String sexo = "";
        String telefono = "";
        String direccion = "";
        String fechaNacimiento = "";
        String login = "";

        String codError = "";
        String mensajeError = "";

        String tramaXml = "";

        if( resultado.equals("0") )
        {
             nombre = strTrama.nextToken();
             apellido1 = strTrama.nextToken();
             apellido2 = strTrama.nextToken();
             sexo = strTrama.nextToken();
             telefono = strTrama.nextToken();
             direccion = strTrama.nextToken();
             fechaNacimiento = strTrama.nextToken();
             login = strTrama.nextToken();

             //222,0,Juan Pablo,Sanchez ,33,5554466,Cra 67 -34,02/07/1986
             tramaXml = "<root>"+  //1,4,106,107,137,53,110,109,108,19
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"106\">"+nombre+"</c>"+
                            "<c p=\"107\">"+apellido1+"</c>"+
                            "<c p=\"137\">"+apellido2+"</c>"+
                            "<c p=\"53\">"+sexo+"</c>"+
                            "<c p=\"110\">"+telefono+"</c>"+
                            "<c p=\"109\">"+direccion+"</c>"+
                            "<c p=\"108\">"+fechaNacimiento+"</c>"+
                            "<c p=\"19\">"+login+"</c>"+
                        "</root>";


        }else
        {
                codError = strTrama.nextToken();
                mensajeError = strTrama.nextToken();
                login = strTrama.nextToken();

                tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"5\">"+codError+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                "</root>";
        }

        return tramaXml;

    }


    /*
     * Trama peticion: idTrama=1,login=2,serie=42,nroSerie=43
     * respuestas = "66,0," + formularioChance.getFormulario() + "," +
                                               formularioChance.getDetalleFormulario() +"," +
                                               formularioChance.getLoginExterno()+"," +
                                               formularioChance.getLogin( ) + "\n";

       respuestas = "66,1," + formularioChance.getResultado() + "," +
                                               formularioChance.getLogin( ) + "\n";
     *
     * Trama Solicitud de Proveedores por vendedor
     * EJ: 30,0,CV12345,1234567890;MICROSEGUR|222222222-2;UNITEC|717171717-1;SOLIDDA|801001001-1;COMCEL|900030255-4;RECARGA|909009009-9;TIGO|987654321;LOTCAUCA|,0000000001
     * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar66(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();

        String cadenaFrm = "";
        String detalleFrm = "";
        String login = "";
        String loginExterno = "";
        
        String mensajeError = "";

        String tramaXml = "";

        if( resultado.equals("0") )
        {
             cadenaFrm = strTrama.nextToken();
             detalleFrm = strTrama.nextToken();
             loginExterno = strTrama.nextToken();
             login = strTrama.nextToken();

             tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"155\">"+cadenaFrm+"</c>"+
                            "<c p=\"156\">"+detalleFrm+"</c>"+
                            "<c p=\"19\">"+loginExterno+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();
                tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+

                                "</root>";
        }

        return tramaXml;

    }



    /*Trama peticion idTrama=1,login=2,serie=42,nro serie virtual=43,NroSerie2=36

     * 13,+="0,OK," + anulaCobra.getSerie( ) + "," + anulaCobra.getNumeroSerieInicial( ) +
                            "," + anulaCobra.getLogin( ) + "\n";

       13,+="1," + anulaCobra.getResultado( ) + "," + anulaCobra.getLogin( )
     *
     * Trama Solicitud de Proveedores por vendedor
     * EJ: 30,0,CV12345,1234567890;MICROSEGUR|222222222-2;UNITEC|717171717-1;SOLIDDA|801001001-1;COMCEL|900030255-4;RECARGA|909009009-9;TIGO|987654321;LOTCAUCA|,0000000001
     * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar13(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();

        String msgOk = "";
        String serie = "";
        String nroSerieInicial = "";
        String login = "";

        String mensajeError = "";
        String tramaXml = "";

        if( resultado.equals("0") )
        {
             msgOk = strTrama.nextToken();
             serie = strTrama.nextToken();
             nroSerieInicial = strTrama.nextToken();
             login = strTrama.nextToken();

             tramaXml = "<root>"+   //
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"18\">"+msgOk+"</c>"+
                            "<c p=\"10\">"+serie+"</c>"+
                            "<c p=\"11\">"+nroSerieInicial+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();
                tramaXml = "<root>"+
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                "</root>";
        }

        return tramaXml;

    }


    /*
     * TODO Logueo Codesa 111 PENDIENTE POR CORREGIR
     */
    /*
     * Trama peticion   idTrama=1,login=2
     * respuesta = "111,1," + usuarioBean.getResultado( ).replace( ',', '?' ) + "," + login;

       respuesta = "111,2," + usuarioBean.getResultado( ) + "," + login ;

       respuesta+="111,0," + login;
     *
     * Trama Solicitud de Proveedores por vendedor
     * EJ: 30,0,CV12345,1234567890;MICROSEGUR|222222222-2;UNITEC|717171717-1;SOLIDDA|801001001-1;COMCEL|900030255-4;RECARGA|909009009-9;TIGO|987654321;LOTCAUCA|,0000000001
     * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar111(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();

        String login = "";
        String mensajeError = "";
        String tramaXml = "";

        if( resultado.equals("0") )
        {
             login = strTrama.nextToken();

             tramaXml = "<root>"+   //
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();
                tramaXml = "<root>"+
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                "</root>";
        }

        return tramaXml;

    }


    /*
     * TODO Logueo Codesa 111 PENDIENTE POR CORREGIR
     */
    /*
     * Trama peticion   idTrama=1,login=2,promocion=177,documento=13,cuantos=178,nitExterno=38,tipoJuego=154,serieEmpresaCliente=10,nroSerieEmpresaCliente=11
     *
       respuesta = 6,Trama Impresion= 174  A7?1?8?0?5?2?2?*?*?IBM?2381?15:16?78?522?0?13/07/2009?59E328~3~4?111?200?4?222?200?4?333?200~!|*|A7?1?8?0?5?2?2?*?*?IBM?2382?15:16?78?522?0?13/07/2009?615230~3~4?111?200?4?222?200?4?333?200~!|*|A7?1?8?0?5?2?2?*?*?IBM?2383?15:16?78?522?0?13/07/2009?615308~3~4?111?200?4?222?200?4?333?200~!|*|A7?1?8?0?5?2?2?*?*?IBM?2384?15:16?78?522?0?13/07/2009?6153E0~3~4?111?200?4?222?200?4?333?200~!|*,
                   IBM=10,0000002385=11,CV20000=19
     *
     *

       respuesta = 6?1?null|CodigoError,serie,#serie,login
     *
     *
     * Trama Impresion
     * EJ: 30,0,CV12345,1234567890;MICROSEGUR|222222222-2;UNITEC|717171717-1;SOLIDDA|801001001-1;COMCEL|900030255-4;RECARGA|909009009-9;TIGO|987654321;LOTCAUCA|,0000000001
     * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    public static String transformaEstandar6(  String encabezadoA,
                                               String encabezadoB,
                                               String encabezadoC,
                                               String encabezadoD,
                                               String tramaOriginal )
    {
        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,"?");
        String idTrama = strTrama.nextToken();
        //Se obtiene la segunda posicion para evaluar si es una trama con error o sin error(error = null|CodigoError )
        String resultado = strTrama.nextToken();
        String tramaXml = "";

        if(resultado.equals("0"))
        {
            tramaXml = "<root>"+   //
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"174\">"+tramaOriginal+"</c>"+
                        "</root>";


        }else
        {
            tramaXml = "<root>"+   //
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"174\">"+tramaOriginal+"</c>"+
                        "</root>";
        }

        return tramaXml;

    }

    
    /*
     * Trama peticion: idTrama=1,login=2
     * 
       respuesta = "15," + resultado[ 0 ] + "," + resultado[ 1 ];
     *
     * Trama Borrar Juego
     * EJ: 30,0,CV12345,1234567890;MICROSEGUR|222222222-2;UNITEC|717171717-1;SOLIDDA|801001001-1;COMCEL|900030255-4;RECARGA|909009009-9;TIGO|987654321;LOTCAUCA|,0000000001
     * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar15(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String login = strTrama.nextToken();
        String mensajeError = strTrama.nextToken();
        String resultado = "";

        if( mensajeError == null || mensajeError.equals("") || mensajeError.equals("null") )
        {
            resultado = "1";
        }else
        {
            resultado = "0";
        }


        String tramaXml = "<root>"+
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                           "</root>";

        return tramaXml;

    }



    //    apuesta.setLogin( st.nextToken( ).trim( ) );
    //    apuesta.setVendedor( st.nextToken( ).trim( ) );
    //    apuesta.setSeparador( st.nextToken( ).trim( ) );
    //    apuesta.setNumero( st.nextToken( ).trim( ) );
    //    apuesta.setValorTotal( Long.parseLong( st.nextToken( ).trim( ) ) );
    //    apuesta.setUnitarioTotal( st.nextToken( ).trim( ) );
    //    apuesta.setIncentivo( st.nextToken( ).trim( ) );
    //    apuesta.setLoteria( st.nextToken( ).trim( ) );
    //    apuesta.setProducto( st.nextToken( ).trim( ) );
    //    apuesta.setNitExterno( st.nextToken( ).trim( ) );
    //    apuesta.setTipoJuego( st.nextToken( ).trim( ) );
    /*
     * TODO PENDIENTE Trama 4
     */

    /*Trama peticion: idTrama=1,login=2,vendedor=13,separador=21,numero=22,vlrTotal=175,vlrUnitario=176,incentivo=25,loteria=26,producto=27,nitExterno=38,tipoJuego=154
     *
     * respuesta+="4,1," + resultado.getResultado( ) + "," + apuesta.getLogin( );

       respuesta+="4,0," + apuesta.getLogin( )   + "," + resultado.getTotalLoteria( ) + "," +
                        resultado.getTotalProducto( )  + "," + resultado.getTotalCobrar( )  + "," +
                        resultado.getValorIncentivo( ) + "," +
                        ( resultado.getValorModificado( ).trim( ).equals( "0" ) ? "0" : "1" )
                        ;


     *
     * Trama Solicitud de Proveedores por vendedor
     * EJ: 30,0,CV12345,1234567890;MICROSEGUR|222222222-2;UNITEC|717171717-1;SOLIDDA|801001001-1;COMCEL|900030255-4;RECARGA|909009009-9;TIGO|987654321;LOTCAUCA|,0000000001
     * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar4(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();

        String login = "";
        String totalLoteria = "";
        String totalProducto = "";
        String totalCobrar = "";
        String vlrIncentivo = "";
        String vlrModificado = "";

        String mensajeError ="";

        String tramaXml = "";


        if( resultado.equals("0") )
        {
             login = strTrama.nextToken();
             totalLoteria = strTrama.nextToken();
             totalProducto = strTrama.nextToken();
             totalCobrar = strTrama.nextToken();
             vlrIncentivo = strTrama.nextToken();
             vlrModificado = strTrama.nextToken();

             tramaXml = "<root>"+   
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"28\">"+totalLoteria+"</c>"+
                            "<c p=\"29\">"+totalProducto+"</c>"+
                            "<c p=\"30\">"+totalCobrar+"</c>"+
                            "<c p=\"31\">"+vlrIncentivo+"</c>"+
                            "<c p=\"32\">"+vlrModificado+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();
                login = strTrama.nextToken();
                tramaXml = "<root>"+
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                "</root>";
        }

        return tramaXml;

    }

    /*TODO PENDIENTE Trama 44  */  
    /* Trama peticion: idTrama=1,login=2,vendedor=13,separador=21,numero=22,vlrTotal=175,vlrUnitario=176,incentivo=25,loteria=26,producto=27,nitExterno=38,tipoJuego=154,serieEmpresa=10,nroSerieEmpresa=11
     * respuesta+="4,1," + resultado.getResultado( ) + "," + apuesta.getLogin( );

       respuesta+="4,0," + apuesta.getLogin( )   + "," + resultado.getTotalLoteria( ) + "," +
                        resultado.getTotalProducto( )  + "," + resultado.getTotalCobrar( )  + "," +
                        resultado.getValorIncentivo( ) + "," +
                        ( resultado.getValorModificado( ).trim( ).equals( "0" ) ? "0" : "1" )
                        ;


     *
     * Trama Solicitud de Proveedores por vendedor
     * EJ: 30,0,CV12345,1234567890;MICROSEGUR|222222222-2;UNITEC|717171717-1;SOLIDDA|801001001-1;COMCEL|900030255-4;RECARGA|909009009-9;TIGO|987654321;LOTCAUCA|,0000000001
     * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    public static String transformaEstandar44(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,"?");
        String idTrama = strTrama.nextToken();
        //Se obtiene la segunda posicion para evaluar si es una trama con error o sin error(error = null|CodigoError )
        String resultado = strTrama.nextToken();
        String tramaXml = "";
        
        if(resultado.equals("0"))
        {           
            tramaXml = "<root>"+   //
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"174\">"+tramaOriginal+"</c>"+
                        "</root>";


        }else
        {
            tramaXml = "<root>"+   //
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"174\">"+tramaOriginal+"</c>"+
                        "</root>";
        }
        
        return tramaXml;

    }


    /*
     * Trama Recarga de celular
     * EJ: 999,0,CV12345,1000,11/09/2009,11:19:54,9380,3113333333,15D9B5D,0,801001001-1;30;11/09/2009;2000;2000;0;460;300;0;1700;1;11/09/2009;;A;0;40|,11/10/2009,0000000002,AHJ,0000042757,*,*,*,*,*,*,*,*,*,12345,I_RC
     * EJ: 999,1,CV12345,No exiete Equivalencia Pto venta
     */
    private static String transformaEstandar999(  String encabezadoA,
                                                 String encabezadoB,
                                                 String encabezadoC,
                                                 String encabezadoD,
                                                 String tramaOriginal)
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();


        String vlrRecarga = "";
        String fecha = "";
        String hora = "";
        String transaccion = "";
        String nroCel = "";
        String frmId = "";
        String iva = "";
        String consolidado = "";
        String fechaVta = "";
        String nroSerieOtroServ = "";
        String serieEmpr = "";
        String nroSerieEmpr = "";
        String info1 = "";
        String info2 = "";
        String info3 = "";
        String info4 = "";
        String info5 = "";
        String info6 = "";
        String info7 = "";
        String info8 = "";
        String info9 = "";
        String nitExtr = "";
        String isActiReglaChance = "";

        String mensajeError = "";

        String tramaXml = "";


        //ej: 999,0,CV12345,1000,10/09/2009,20:08:18,9379,3117777777,5950C5,0,1234567890;200;10/09/2009;500;500;0;20;0;5;500;1;10/09/2009;;A;0;10|801001001-1;30;10/09/2009;1000;1000;0;230;150;0;850;1;10/09/2009;;A;0;20|987654321;25;10/09/2009;10000;10000;0;2300;0;0;10000;4;10/09/2009;;A;0;200|,10/10/2009,0000000003,AHJ,0000042757,*,*,*,*,*,*,*,*,*,12345,I_RC
        if( resultado.equals("0") )
        {

             vlrRecarga = strTrama.nextToken();
             fecha = strTrama.nextToken();
             hora = strTrama.nextToken();
             transaccion = strTrama.nextToken();
             nroCel = strTrama.nextToken();
             frmId = strTrama.nextToken();
             iva = strTrama.nextToken();
             consolidado = strTrama.nextToken();
             fechaVta = strTrama.nextToken();
             nroSerieOtroServ = strTrama.nextToken();
             serieEmpr = strTrama.nextToken();
             nroSerieEmpr = strTrama.nextToken();
             info1 = strTrama.nextToken();
             info2 = strTrama.nextToken();
             info3 = strTrama.nextToken();
             info4 = strTrama.nextToken();
             info5 = strTrama.nextToken();
             info6 = strTrama.nextToken();
             info7 = strTrama.nextToken();
             info8 = strTrama.nextToken();
             info9 = strTrama.nextToken();
             nitExtr = strTrama.nextToken();
             isActiReglaChance = strTrama.nextToken();

             tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                    "<c p=\"48\">"+vlrRecarga+"</c>"+
                                    "<c p=\"12\">"+fecha+"</c>"+
                                    "<c p=\"61\">"+hora+"</c>"+
                                    "<c p=\"62\">"+transaccion+"</c>"+
                                    "<c p=\"47\">"+nroCel+"</c>"+
                                    "<c p=\"63\">"+frmId+"</c>"+
                                    "<c p=\"64\">"+iva+"</c>"+
                                    "<c p=\"65\">"+consolidado+"</c>"+
                                    "<c p=\"90\">"+fechaVta+"</c>"+
                                    "<c p=\"43\">"+nroSerieOtroServ+"</c>"+
                                    "<c p=\"10\">"+serieEmpr+"</c>"+
                                    "<c p=\"11\">"+nroSerieEmpr+"</c>" +
                                    "<c p=\"49\">"+info1+"</c>"+
                                    "<c p=\"91\">"+info2+"</c>"+
                                    "<c p=\"92\">"+info3+"</c>"+
                                    "<c p=\"93\">"+info4+"</c>"+
                                    "<c p=\"94\">"+info5+"</c>"+
                                    "<c p=\"95\">"+info6+"</c>"+
                                    "<c p=\"96\">"+info7+"</c>"+
                                    "<c p=\"97\">"+info8+"</c>"+
                                    "<c p=\"98\">"+info9+"</c>"+
                                    "<c p=\"99\">"+nitExtr+"</c>"+
                                    "<c p=\"100\">"+isActiReglaChance+"</c>"+
                                "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();
                tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                                "</root>";
        }


        return tramaXml;
    }


     /*
      * Trama Solicitud de Proveedores por vendedor
      * EJ: 30,0,CV12345,1234567890;MICROSEGUR|222222222-2;UNITEC|717171717-1;SOLIDDA|801001001-1;COMCEL|900030255-4;RECARGA|909009009-9;TIGO|987654321;LOTCAUCA|,0000000001
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar30(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String cadenaProveedores = "";
        String nroSerieOtroServ = "";
        

        String mensajeError = "";

        String tramaXml = "";

        if( resultado.equals("0") )
        {

             cadenaProveedores = strTrama.nextToken();
             nroSerieOtroServ = strTrama.nextToken();

             tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"39\">"+cadenaProveedores+"</c>"+
                            "<c p=\"43\">"+nroSerieOtroServ+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();
                tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                                "</root>";
        }

        return tramaXml;
        
    }



    /*
     * Trama Solicitud de Proveedores por vendedor
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar31(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String cadenaServProductos = "";

        String mensajeError = "";

        String tramaXml = "";


        if( resultado.equals("0") )
        {

             cadenaServProductos = strTrama.nextToken();

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"40\"><![CDATA["+cadenaServProductos.trim()+"]]></c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";
        }

        return tramaXml;

    }



    




    /* Consulta valor factura
     * Peticion idTrama=1,login=2,nitProv=44,codServicio=45,codProducto=46,serial=62,nitEmpresa(externo)=38
     * 
     * respuesta =  "32,0," + login + "," + pxsb.getValor( ) + "," + pxsb.getValorMin( );
     * respuesta =  "32,1," + login + "," + General.retornarError( pxsb.getResultado( ) );
     *
     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar32(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String valor = "";
        String valorMinimo = "";

        String mensajeError = "";

        String tramaXml = "";


        if( resultado.equals("0") )
        {

             valor = strTrama.nextToken();
             valorMinimo = strTrama.nextToken();

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"157\">"+valor+"</c>"+
                            "<c p=\"158\">"+valorMinimo+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";
        }

        return tramaXml;

    }




    /* Peticion idTrama=1,login=2,nitProv=44,codServicio=45,codProducto=46,serial=62,valor=157,
                serie=42,nserie=43,ptoVenta=7,nitEmpresa=38,documento=13
     *
     * respuesta = String.valueOf(operacion)+",0," + login + "," + pxsb.getValor( )  + "," + pxsb.getFecha( )  + ","
                            + pxsb.getHora( )  + "," + pxsb.getSerial2( )  + "," + pxsb.getClave( )  + ","
                            + pxsb.getFrmID( ) + "," + pxsb.getIVA( ) + "," + pxsb.getCadena( );
     * 
     * respuesta =  String.valueOf(operacion)+",1," + login + "," + General.retornarError( pxsb.getResultado( ) );
     *
     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar33_41(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String valor = "";
        String fecha = "";
        String hora = "";
        String serial = "";
        String clave = "";
        String frmId = "";
        String iva = "";
        String consolidado = "";
        String fechaVenta = "";
        String nroSorteo = "";
        String serieFranccion = "";
        String nroSerieVirtual = "";
        //fecha,nro sorteo,serie-fraccion- ,nroSerieOtrosServ

        String mensajeError = "";

        String tramaXml = "";


        if( resultado.equals("0") )
        {

            valor = strTrama.nextToken();
            fecha = strTrama.nextToken();
            hora = strTrama.nextToken();
            serial = strTrama.nextToken();
            clave = strTrama.nextToken();
            frmId = strTrama.nextToken();
            iva = strTrama.nextToken();
            consolidado = strTrama.nextToken();
            fechaVenta = strTrama.nextToken();
            nroSorteo = strTrama.nextToken();
            serieFranccion = strTrama.nextToken();
            nroSerieVirtual = strTrama.nextToken();
             

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"157\">"+valor+"</c>"+
                            "<c p=\"12\">"+fecha+"</c>"+
                            "<c p=\"61\">"+hora+"</c>"+
                            "<c p=\"62\">"+serial+"</c>"+
                            "<c p=\"3\">"+clave+"</c>"+
                            "<c p=\"63\">"+frmId+"</c>"+
                            "<c p=\"64\">"+iva+"</c>"+
                            "<c p=\"65\">"+consolidado+"</c>"+
                            "<c p=\"12\">"+fechaVenta+"</c>"+
                            "<c p=\"179\">"+nroSorteo+"</c>"+
                            "<c p=\"180\">"+serieFranccion+"</c>"+
                            "<c p=\"43\">"+nroSerieVirtual+"</c>"+
                        "</root>";
          


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";
        }

        return tramaXml;

    }



    /*
     * TODO
     * Trama 6 PENDIENTE ************************************************************     *
     */


    /* Peticion idTrama=1,login=2,nitProv=44,codServicio=45,codProducto=46,serial=62,valor=157,serie=42,
                    nserie=43,serieChance=10,nroSerieChance=11,docuCliente=49,nombCliente=50
                    apellido1=51 ,apellido2=52 ,sexo=53,fechaNaciCli=54,telefonoCli=55,ptoVenta=7,
                    nitEmpresa=38,documento=13
     
      respuesta = String.valueOf(operacion)+",0," + login + "," + pxsb.getValor( )  + "," + pxsb.getFecha( )  + ","
                    + pxsb.getHora( )  + "," + pxsb.getSerial2( )  + "," + pxsb.getClave( )  + ","
                    + pxsb.getFrmID( ) + "," + pxsb.getIVA( ) + "," + pxsb.getCadena( )+ "," + pxsb.getSerieChance() + ","
                    + pxsb.getNroSerieChance() + "," + pxsb.getDocuCliente() + "," + pxsb.getTipoRegla()+ ","
                    + pxsb.getMensajeRegla() + "," + pxsb.getCadenaPromocion() + "," + pxsb.getPuntos()+ ","
                    + pxsb.getCodigoLoteria() + "," + pxsb.getCodigoProducto() + "," + pxsb.getNumeroApostado()+ ","
                    + pxsb.getValorApostado()+ "," + pxsb.getNitCodesa()+ "," + "I_RC";
     
      respuesta =  String.valueOf(operacion)+",1," + login + "," + General.retornarError( pxsb.getResultado( ) );
     
     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar333(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String valor = "";
        String fecha = "";
        String hora = "";
        String serial = "";
        String clave = "";
        String frmId = "";
        String iva = "";
        String cadena = "";
        String serieChance = "";
        String nroSerieChance = "";
        String docuCliente = "";
        String tipoRegla = "";
        String msgRegla = "";
        String cadenaPromocion = "";
        String puntos = "";
        String codLoteria = "";
        String codProducto = "";
        String nroApostado = "";
        String vlrApostado = "";
        String nitCodesa = "";
        String reglaChance = "";

        String mensajeError = "";

        String tramaXml = "";


        if( resultado.equals("0") )
        {

             valor = strTrama.nextToken();
             fecha = strTrama.nextToken();
             hora = strTrama.nextToken();
             serial = strTrama.nextToken();
             clave = strTrama.nextToken();
             frmId = strTrama.nextToken();
             iva = strTrama.nextToken();
             cadena = strTrama.nextToken();
             serieChance = strTrama.nextToken();
             nroSerieChance = strTrama.nextToken();
             docuCliente = strTrama.nextToken();
             tipoRegla = strTrama.nextToken();
             msgRegla = "";
             cadenaPromocion = "";
             puntos = "";
             codLoteria = "";
             codProducto = "";
             nroApostado = "";
             vlrApostado = "";
             nitCodesa = "";
             reglaChance =

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"157\">"+valor+"</c>"+
                            "<c p=\"12\">"+fecha+"</c>"+
                            "<c p=\"61\">"+hora+"</c>"+
                            "<c p=\"62\">"+serial+"</c>"+
                            "<c p=\"3\">"+clave+"</c>"+
                            "<c p=\"63\">"+frmId+"</c>"+
                            "<c p=\"64\">"+iva+"</c>"+
                            "<c p=\"65\">"+cadena+"</c>"+ // consolidado
                            "<c p=\"10\">"+serieChance+"</c>"+
                            "<c p=\"11\">"+nroSerieChance+"</c>"+
                            "<c p=\"49\">"+docuCliente+"</c>"+
                            "<c p=\"91\">"+tipoRegla+"</c>"+
                            "<c p=\"92\">"+msgRegla+"</c>"+
                            "<c p=\"93\">"+cadenaPromocion+"</c>"+
                            "<c p=\"94\">"+puntos+"</c>"+
                            "<c p=\"95\">"+codLoteria+"</c>"+
                            "<c p=\"96\">"+codProducto+"</c>"+
                            "<c p=\"97\">"+nroApostado+"</c>"+
                            "<c p=\"98\">"+vlrApostado+"</c>"+
                            "<c p=\"99\">"+nitCodesa+"</c>"+
                            "<c p=\"66\">"+reglaChance+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";
                
        }

        return tramaXml;

    }




    /* Consulta Consolidado
     
     Peticion idTrama=1,login=2,codigoPtoVenta=7,nitEmpresa=38,documento =13

      respuesta = "34,0," + login + "," + ( String )rs.getObject( );

      respuesta =  "34,1," + login + "," + General.retornarError( ( String ) rs.getMensaje( ) );


      respuesta =  String.valueOf(operacion)+",1," + login + "," + General.retornarError( pxsb.getResultado( ) );

     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar34(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String fecha = "";
        String hora = "";
        String consolidado = "";

        String mensajeError = "";

        String tramaXml = "";


        if( resultado.equals("0") )
        {
             fecha = strTrama.nextToken();
             hora = strTrama.nextToken();
             consolidado = strTrama.nextToken();             

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"12\">"+fecha+"</c>"+
                            "<c p=\"149\">"+hora+"</c>"+
                            "<c p=\"65\">"+consolidado+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+   
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";

        }

        return tramaXml;

    }




    /*Cierre Otros Productos
     Peticion idTrama=1,login=2,codigoPtoVenta=7,nitEmpresa=38,documento =13

      respuesta = "35,0," + login + "," + ( String )rs.getObject( ) + "," + ( String )rs.getMensaje( );

      respuesta =  "35,1," + login + "," + General.retornarError( ( String ) rs.getMensaje( ) );


      respuesta =  String.valueOf(operacion)+",1," + login + "," + General.retornarError( pxsb.getResultado( ) );

     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar35(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String consolidado = "";
        String vtaLocal = "";

        String mensajeError = "";

        String tramaXml = "";


        if( resultado.equals("0") )
        {
             consolidado = strTrama.nextToken();
             vtaLocal = strTrama.nextToken();

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"65\">"+consolidado+"</c>"+
                            "<c p=\"159\">"+vtaLocal+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";

        }

        return tramaXml;

    }



    /*Reimpresion
     Peticion idTrama=1,login=2,serie=42,nroSerie=43,codigoPtoVenta=7,nitEmpresa=38,documento =13

      respuesta = "36,0," + login + "," + General.retornarError( ( String )rs.getObject( ) );

      respuesta =  "36,1," + login + "," + General.retornarError( ( String ) rs.getMensaje( ) );


      respuesta =  String.valueOf(operacion)+",1," + login + "," + General.retornarError( pxsb.getResultado( ) );

     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar36(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String tramaReimpresion = "";
        String nroSerieVirtual = "";
        String fecha = "";        

        String mensajeError = "";

        String tramaXml = "";


        if( resultado.equals("0") )
        {
             tramaReimpresion = strTrama.nextToken();             
             nroSerieVirtual = strTrama.nextToken();

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"160\">"+tramaReimpresion+"</c>"+
                            "<c p=\"43\">"+nroSerieVirtual+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";

        }

        return tramaXml;

    }

//    login          = st.nextToken( );                                //Saca el login
//    codigoPtoVenta = st.nextToken( ).trim( );
//    nitEmpresa     = st.nextToken( ).trim( );
//    documento      = st.nextToken( ).trim( );

    /*Consulta consolidado
     Peticion idTrama=1,login=2,codigoPtoVenta=7,nitEmpresa=38,documento =13

      respuesta = "38,0," + login + "," + ( String )rs.getObject( );

      respuesta =  "38,1," + login + "," + General.retornarError( ( String ) rs.getMensaje( ) );


      respuesta =  String.valueOf(operacion)+",1," + login + "," + General.retornarError( pxsb.getResultado( ) );

     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar38(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String consolidado = "";

        String mensajeError = "";

        String tramaXml = "";


        if( resultado.equals("0") )
        {
             consolidado = strTrama.nextToken();

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"65\">"+consolidado+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";

        }

        return tramaXml;

    }


    


    /*Cierre Otros Productos
     Peticion idTrama=1,login=2,nitProv=44,codServicio=45,codProducto=46,CadenaBusqueda=161,codigoPtoVenta=7,nitEmpresa=38

      respuesta = "40,0," + login + "," + pxsb.getResultado( ) ;

      respuesta =  "40,1," + login + "," + General.retornarError( pxsb.getResultado( ) );


      respuesta =  String.valueOf(operacion)+",1," + login + "," + General.retornarError( pxsb.getResultado( ) );

     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar40(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String serie = "";
        String valor = "";

        String mensajeError = "";

        String tramaXml = "";


        if( resultado.equals("0") )
        {
             serie = strTrama.nextToken();
             valor = strTrama.nextToken();

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"10\">"+serie+"</c>"+
                            "<c p=\"157\">"+valor+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";

        }

        return tramaXml;

    }



    /*Consulta trama premios
      Peticion idTrama=1,login=2,SerieNumero=36

      respuesta = "42,0," + login + "," + (String)resultado.getObject( ) ;

      respuesta =  "42,1," + login + "," + (String)resultado.getObject( );

     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar42(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String tramaPremios = "";
        
        String mensajeError = "";

        String tramaXml = "";


        if( resultado.equals("0") )
        {
             tramaPremios = strTrama.nextToken();

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"162\">"+tramaPremios+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";

        }

        return tramaXml;

    }




    /*Consulta datos persona
     Peticion idTrama=1,login=2,documento=13

      respuesta = "43,0," + login + "," + (String)resultado.getObject( ) ;

      respuesta =  "43,1," + login + "," + (String)resultado.getObject( );


     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar43(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String datoPersona = "";

        String mensajeError = "";

        String tramaXml = "";


        if( resultado.equals("0") )
        {
             datoPersona = strTrama.nextToken();

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"162\">"+datoPersona+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";

        }

        return tramaXml;

    }


    /*Consulta formulario
     Peticion idTrama=1,login=2,documento=13

      respuesta = "47,0," + login + "," +formulario.toString() ;

      respuesta =  "47,1," + login + "," + formulario.getResultado()+",";


     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar47(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String vendedor = "";
        String hora = "";
        String valor = "";
        String fechaVenta = "";
        String fechaSorteo = "";
        String nroApostado = "";
        String proveedor = "";
        String servicio = "";
        String producto = "";

        String mensajeError = "";

        String tramaXml = "";

        //getVendedor()+","+getHora()+","+getValor()+","+getFechaVenta()+","+getFechaSorteo()+","+getNoApostado()+","+getNombreProveedor()+","+getNombreServicio()+","+getNombreProducto();
        if( resultado.equals("0") )
        {
             vendedor = strTrama.nextToken();
             hora = strTrama.nextToken();
             valor = strTrama.nextToken();
             fechaVenta = strTrama.nextToken();
             fechaSorteo = strTrama.nextToken();
             nroApostado = strTrama.nextToken();
             proveedor = strTrama.nextToken();
             servicio = strTrama.nextToken();
             producto = strTrama.nextToken();

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"13\">"+vendedor+"</c>"+
                            "<c p=\"61\">"+hora+"</c>"+
                            "<c p=\"157\">"+valor+"</c>"+
                            "<c p=\"12\">"+fechaVenta+"</c>"+
                            "<c p=\"163\">"+fechaSorteo+"</c>"+
                            "<c p=\"22\">"+nroApostado+"</c>"+
                            "<c p=\"44\">"+proveedor+"</c>"+
                            "<c p=\"45\">"+servicio+"</c>"+
                            "<c p=\"46\">"+producto+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";

        }

        return tramaXml;

    }







    /*Anular formulario
     Peticion idTrama=1,login=2,serie=42,nroSerie=43,nit=38

      respuesta = "48,0," + login + "," +resultado.getObject() ;

      respuesta =  "48,1," + login + "," + resultado.getObject()+",";


     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar48(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String consolidado = "";

        String mensajeError = "";

        String tramaXml = "";

        //getVendedor()+","+getHora()+","+getValor()+","+getFechaVenta()+","+getFechaSorteo()+","+getNoApostado()+","+getNombreProveedor()+","+getNombreServicio()+","+getNombreProducto();
        if( resultado.equals("0") )
        {
             consolidado = strTrama.nextToken();

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"65\">"+consolidado+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";

        }

        return tramaXml;

    }


    /*Pago premios codesa
     Peticion idTrama=1,login=2,strTramaPagoPremio=164

      respuesta = "45,0," + login + "," + "null" ;

      respuesta =  "45,1," + login + "," + resultado;


     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar45(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String strNull = "";

        String mensajeError = "";

        String tramaXml = "";

        if( resultado.equals("0") )
        {
             strNull = strTrama.nextToken();

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"65\">"+strNull+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";

        }

        return tramaXml;

    }




    /*Consulta ultimo formulario reimpresion
     Peticion idTrama=1,login=2,codigoPtoVenta=7,nitEmpresa=38,documento=13

      respuesta = "115,0," + login + "," + ( String )rs.getObject( );

      respuesta =  "115,1," + login + "," + General.retornarError( ( String ) rs.getMensaje( ) );


     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    private static String transformaEstandar115( String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String proveedor = "";
        String servicio = "";
        String producto = "";
        String serie = "";
        String nroSerie = "";
        String hora = "";
        String valor = "";
        String fechaVenta = "";
        String fechaCaducidad = "";
        String serial = "";
        String clave = "";

        String mensajeError = "";

        String tramaXml = "";

        if( resultado.equals("0") )
        {
             proveedor = strTrama.nextToken();
             servicio = strTrama.nextToken();
             producto = strTrama.nextToken();
             serie = strTrama.nextToken();
             nroSerie = strTrama.nextToken();
             hora = strTrama.nextToken();
             valor = strTrama.nextToken();
             fechaVenta = strTrama.nextToken();
             fechaCaducidad = strTrama.nextToken();
             serial = strTrama.nextToken();
             clave = strTrama.nextToken();

             tramaXml = "<root>"+
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"44\">"+proveedor+"</c>"+
                            "<c p=\"45\">"+servicio+"</c>"+
                            "<c p=\"46\">"+producto+"</c>"+
                            "<c p=\"42\">"+serie+"</c>"+
                            "<c p=\"43\">"+nroSerie+"</c>"+
                            "<c p=\"61\">"+hora+"</c>"+
                            "<c p=\"157\">"+valor+"</c>"+
                            "<c p=\"12\">"+fechaVenta+"</c>"+
                            "<c p=\"90\">"+fechaCaducidad+"</c>"+
                            "<c p=\"62\">"+serial+"</c>"+
                            "<c p=\"3\">"+clave+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";

        }

        return tramaXml;

    }


    /*Reimpresion automatica microseguro
     Peticion idTrama=1,login=2,serie=42,nroSerie=43,codigoPtoVenta=7,nitEmpresa=38,documento =13

      respuesta = "116,0," + login + "," + ( String )rs.getObject( )+ "," + rs.getRequiereClave() // A= Seguro; N= Pines; M= Recarga; Otros
                  + "," + seguro.getModuloSeguro().getCodModulo()
                  + "," + seguro.getModuloSeguro().getNombreModulo()
                  + "," + seguro.getNroPolizaMatriz()
                  + "," + seguro.getNroCertificado()
                  + "," + seguro.getRazonSocialCia()
                  + "," + seguro.getNitCiaSeguros()
                  + "," + seguro.getCliente().getDocumento()
                  + "," + seguro.getCliente().getApellido1() +" "+ seguro.getCliente().getApellido2()
                  + "," + seguro.getCliente().getNombre()
                  + "," + seguro.getCliente().getDireccion()
                  + "," + seguro.getBeneficiario().getApellido()
                  + "," + seguro.getBeneficiario().getNombre()
                  + "," + seguro.getFechaExpedicion()
                  + "," + seguro.getHoraExpedicion()
                  + "," + seguro.getVigenciaSeguro()
                  + "," + seguro.getModuloSeguro().getPlanSeguro().getValorPrima()
                  + "," + seguro.getModuloSeguro().getPlanSeguro().getValoAsegurado()
                  + "," + seguro.getDeducibleGenerales();

      respuesta = "116,0," + login + "," + ( String )rs.getObject( ) + "," + rs.getRequiereClave(); // A= Seguro; N= Pines; M= Recarga; Otros
     
      respuesta = "116,1," + login + "," + General.retornarError( ( String ) rs.getMensaje( ) );


     * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */
    public static String transformaEstandar116( String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();

        String tramaReimpresion = "";
        String nroSerieVirtual = "";
        String lineaProducto = "";// Requiere clave = N= Pines, M= Recargas , etc
        String codModulo = "";
        String nombreModulo = "";
        String nroPolizaMatriz = "";
        String nroCertificado = "";
        String razonSocial = "";
        String nitCiaSeguros = "";
        String documentoCliente = "";
        String apellidosCliente = "";
        String nombreCliente = "";
        String direccion = "";
        String apellidosBeneficiario = "";
        String nombreBeneficiario = "";
        String fechaExpedicion = "";
        String horaExpedicion = "";
        String vigenciaSeguro = "";
        String prima = "";
        String vlrAsegurado = "";
        String deducibleGenerales = "";
        
        String mensajeError = "";

        String tramaXml = "";

        // Si es un seguro la trama se descompone de otra forma
        if( resultado.equals("0") )
        {
            tramaReimpresion = strTrama.nextToken();
            nroSerieVirtual = strTrama.nextToken();
            lineaProducto = strTrama.nextToken();

            System.out.println("tramaReimpresion: "+tramaReimpresion);
            System.out.println("lineaProducto: "+lineaProducto);

            if(lineaProducto.equals("A"))
            {
                 
                 codModulo = strTrama.nextToken();
                 nombreModulo = strTrama.nextToken();
                 nroPolizaMatriz = strTrama.nextToken();
                 nroCertificado = strTrama.nextToken();
                 razonSocial = strTrama.nextToken();
                 nitCiaSeguros = strTrama.nextToken();
                 documentoCliente = strTrama.nextToken();
                 apellidosCliente = strTrama.nextToken();
                 nombreCliente = strTrama.nextToken();
                 direccion = strTrama.nextToken();
                 apellidosBeneficiario = strTrama.nextToken();
                 nombreBeneficiario = strTrama.nextToken();
                 fechaExpedicion = strTrama.nextToken();
                 horaExpedicion = strTrama.nextToken();
                 vigenciaSeguro = strTrama.nextToken();
                 prima = strTrama.nextToken();
                 vlrAsegurado = strTrama.nextToken();
                 deducibleGenerales = strTrama.nextToken();
                 
                 tramaXml = "<root>"+
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"160\">"+tramaReimpresion+"</c>"+
                                "<c p=\"43\">"+nroSerieVirtual+"</c>"+
                                "<c p=\"166\">"+lineaProducto+"</c>"+
                                "<c p=\"111\">"+codModulo+"</c>"+
                                "<c p=\"167\">"+nombreModulo+"</c>"+
                                "<c p=\"124\">"+nroPolizaMatriz+"</c>"+
                                "<c p=\"131\">"+nroCertificado+"</c>"+
                                "<c p=\"125\">"+razonSocial+"</c>"+
                                "<c p=\"126\">"+nitCiaSeguros+"</c>"+
                                "<c p=\"105\">"+documentoCliente+"</c>"+
                                "<c p=\"107\">"+apellidosCliente+"</c>"+
                                "<c p=\"106\">"+nombreCliente+"</c>"+
                                "<c p=\"109\">"+direccion+"</c>"+
                                "<c p=\"104\">"+apellidosBeneficiario+"</c>"+
                                "<c p=\"103\">"+nombreBeneficiario+"</c>"+
                                "<c p=\"121\">"+fechaExpedicion+"</c>"+
                                "<c p=\"122\">"+horaExpedicion+"</c>"+
                                "<c p=\"123\">"+vigenciaSeguro+"</c>"+
                                "<c p=\"113\">"+prima+"</c>"+
                                "<c p=\"114\">"+vlrAsegurado+"</c>"+
                                "<c p=\"127\">"+deducibleGenerales+"</c>"+
                            "</root>";

            // Si la trama no es de seguros y se contesto positivamente
            }else if( ! lineaProducto.equals("A")  )
            { 
                    tramaXml = "<root>"+
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                    "<c p=\"160\">"+tramaReimpresion+"</c>"+
                                    "<c p=\"166\">"+lineaProducto+"</c>"+
                                "</root>";
            }

        }else
        {
                mensajeError = strTrama.nextToken();

                tramaXml = "<root>"+
                                "<c p=\"A\">"+encabezadoA+"</c>"+
                                "<c p=\"B\">"+encabezadoB+"</c>"+
                                "<c p=\"C\">"+encabezadoC+"</c>"+
                                "<c p=\"D\">"+encabezadoD+"</c>"+
                                "<c p=\"1\">"+idTrama+"</c>"+
                                "<c p=\"4\">"+resultado+"</c>"+
                                "<c p=\"2\">"+login+"</c>"+
                                "<c p=\"6\">"+mensajeError+"</c>"+
                            "</root>";

        }

        return tramaXml;

    }




    /* Consulta formulario actual
       Peticion idTrama=1,login=2,codigoPtoVenta=7,nitEmpresa=38,documento =13

       respuesta = "117,0," + login + "," + ( String )rs.getObject( );

       respuesta =  "117,1," + login + "," + General.retornarError( ( String ) rs.getMensaje( ) );
     
      * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */

    private static String transformaEstandar117(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();
        String login = strTrama.nextToken();


        String nroSerieOtroServ = "";


        String mensajeError = "";

        String tramaXml = "";

        if( resultado.equals("0") )
        {
             nroSerieOtroServ = strTrama.nextToken();

             tramaXml = "<root>"+   
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"2\">"+login+"</c>"+
                            "<c p=\"43\">"+nroSerieOtroServ+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();
                tramaXml = "<root>"+   
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"2\">"+login+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                                "</root>";
        }

        return tramaXml;

    }


    /* Consultar Doble Chance
       Peticion idTrama=1,tipo=169,nit(documento)=13,fecha=12,hora =149

       respuesta = "118,0," + ( String )rs.getObject( );

       respuesta = "118,1," + ( String ) rs.getMensaje( ) ;

      * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */

    private static String transformaEstandar118(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();        

        String cantFrmImpr = "";
        String totalfrmImpr = "";
        String cantFrmAnulados = "";
        String totalFrmAnulados = "";

        String mensajeError = "";

        String tramaXml = "";

        if( resultado.equals("0") )
        {
             cantFrmImpr = strTrama.nextToken();
             totalfrmImpr = strTrama.nextToken();
             cantFrmAnulados = strTrama.nextToken();
             totalFrmAnulados = strTrama.nextToken();

             tramaXml = "<root>"+   
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"170\">"+cantFrmImpr+"</c>"+
                            "<c p=\"171\">"+totalfrmImpr+"</c>"+
                            "<c p=\"172\">"+cantFrmAnulados+"</c>"+
                            "<c p=\"173\">"+totalFrmAnulados+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();
                tramaXml = "<root>"+   
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                                "</root>";
        }

        return tramaXml;

    }




    /* Consultar Beneficencia
       Peticion idTrama=1,tipo=169,nit(documento)=13,fecha=12,hora =149

       respuesta = "119,0," + ( String )rs.getObject( );

       respuesta = "119,1," + ( String ) rs.getMensaje( ) ;

      * Consulta Valor Factura
      * EJ: 31,0,CV199912,30;RECMOVIL;M;MOVISTAR|!GRACIAS POR UTILIZAR EL SERVICIO|DE RECARGAS|!~34;RECMOVIST;10;500;1000000;500?&
      * EJ: 30,1,CV12345,Proveedores no econtrados
     */

    private static String transformaEstandar119(  String encabezadoA,
                                                String encabezadoB,
                                                String encabezadoC,
                                                String encabezadoD,
                                                String tramaOriginal )
    {

        StringTokenizer strTrama = new StringTokenizer(tramaOriginal,",");
        String idTrama = strTrama.nextToken();
        String resultado = strTrama.nextToken();

        String cantFrmImpr = "";
        String totalfrmImpr = "";
        String cantFrmAnulados = "";
        String totalFrmAnulados = "";


        String mensajeError = "";

        String tramaXml = "";

        if( resultado.equals("0") )
        {
             cantFrmImpr = strTrama.nextToken();
             totalfrmImpr = strTrama.nextToken();
             cantFrmAnulados = strTrama.nextToken();
             totalFrmAnulados = strTrama.nextToken();

             tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                            "<c p=\"A\">"+encabezadoA+"</c>"+
                            "<c p=\"B\">"+encabezadoB+"</c>"+
                            "<c p=\"C\">"+encabezadoC+"</c>"+
                            "<c p=\"D\">"+encabezadoD+"</c>"+
                            "<c p=\"1\">"+idTrama+"</c>"+
                            "<c p=\"4\">"+resultado+"</c>"+
                            "<c p=\"170\">"+cantFrmImpr+"</c>"+
                            "<c p=\"171\">"+totalfrmImpr+"</c>"+
                            "<c p=\"172\">"+cantFrmAnulados+"</c>"+
                            "<c p=\"173\">"+totalFrmAnulados+"</c>"+
                        "</root>";


        }else
        {
                mensajeError = strTrama.nextToken();
                tramaXml = "<root>"+   //1,4,2,48,12,61,62,47,63,64,65,90,43,10,11,49,91,92,93,94,95,96,97,98,99,100
                                    "<c p=\"A\">"+encabezadoA+"</c>"+
                                    "<c p=\"B\">"+encabezadoB+"</c>"+
                                    "<c p=\"C\">"+encabezadoC+"</c>"+
                                    "<c p=\"D\">"+encabezadoD+"</c>"+
                                    "<c p=\"1\">"+idTrama+"</c>"+
                                    "<c p=\"4\">"+resultado+"</c>"+                                   
                                    "<c p=\"6\">"+mensajeError+"</c>"+
                                "</root>";
        }

        return tramaXml;

    }

    
//    public static void main( String args [] )
//    {
//        TransformStandardXml tr = new TransformStandardXml();
//        tr.transformaEstandar222("A","B","C","D","222,0,Juan Pablo,Sanchez,null,33,5554466,Cra 67 -34,02/07/1986");
//
//
//    }

}
