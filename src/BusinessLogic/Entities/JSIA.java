package BusinessLogic.Entities;

import java.security.SecureRandom;
import java.util.Hashtable;


public class JSIA implements JSITecnicoRuso, JSITecnicoIngles, JSIManipulacionHormiga {
    private static String jsNombre;
    private static JSIA jsInstancia;
    Hashtable<Integer, String> jsConocimientoI = new Hashtable<>();
    Hashtable<Integer, String> jsConocimientoR = new Hashtable<>();
    public char[] jsComunicacionHormigas;

    public JSIA() {
    }

    // metodo para crear una sola clase papa, para varias clases hijos
    protected JSIA(JSIA ia) {
        if (ia != null) {
            jsInstancia = ia;
        }
    }

    // Metodo para inicializar una sola vez el constructor de la clase.
    public static JSIA getjsInstancia(String jsNombre) {
        if (jsInstancia == null) {
            jsInstancia = new JSIA();
            JSIA.jsNombre = jsNombre;
        }
        return jsInstancia;
    }

    public String getjsNombre() {
        return jsNombre;
    }

    @Override
    public String jsAprenderLexicoIngles() {
        jsConocimientoI.put(1, " Se ha aprendido correctamente el léxico en inglés.");
        return " Se está recibiendo la transferencia de conocimientos de lexico del inglés.";
    }

    @Override
    public String jsAprenderGramaticaIngles() {
        jsConocimientoI.put(2, " Se ha aprendido correctamente la gramática en inglés.");
        return " Se está recibiendo la transferencia de conocimientos de gramática del inglés.";
    }

    @Override
    public String jsAprenderFoneticaIngles() {
        jsConocimientoI.put(3, " Se ha aprendido correctamente la fonética en inglés.");
        return " Se está recibiendo la transferencia de conocimientos de fonética del inglés.";
    }

    public void jsConocimientoIngles() {
        System.out.println(" Se ha logrado recibir la transferencia de conocimientos en inglés:");
        jsConocimientoI.forEach((k, v) -> {
            System.out.println(v);
        });
    }

    @Override
    public String jsAprenderLexicoRuso() {
        jsConocimientoR.put(1, " Se ha aprendido correctamente el léxico en ruso.");
        return " Se está recibiendo la transferencia de conocimientos de léxico del ruso.";
    }

    @Override
    public String jsAprenderGramaticaRuso() {
        jsConocimientoR.put(2, " Se ha aprendido correctamente la gramática en ruso.");
        return " Se está recibiendo la transferencia de conocimientos de gramática del ruso.";
    }

    @Override
    public String jsAprenderFoneticaRuso() {
        jsConocimientoR.put(3, " Se ha aprendido correctamente la fonética en ruso.");
        return " Se está recibiendo la transferencia de conocimientos de fonética del ruso.";
    }

    public void jsConocimientoRuso() {
        System.out.println(" Se ha logrado recibir la transferencia de conocimientos en ruso:");
        jsConocimientoR.forEach((k, v) -> {
            System.out.println(v);
        });
    }

    public void jsComunicacionHormigas(){
        System.out.println(" Hormigas Comunicándose en Inglés y Ruso. Por la Exitosa Transferencia de Idiomas.");
    }

    @Override
    public String jsManipularHormiga(){
        return" Hormigas cumpliendo instruccion";
    }

    @Override
    public String jsUsarSensor(){
        return" Hormigas usuando los sensores";
    }

    @Override
    public String jsUsarAntena(){
        return" Hormigas usando la antena";
    }

    @Override
    public String jsUsarRadio(){
        return" Hormigas usando la radio de comunicacion";
    }

    public String PAgenerarCodigoInicializacion() {
        SecureRandom idAntBot = new SecureRandom();
        String caracteresPosibles = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder builder = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            int pos = idAntBot.nextInt(caracteresPosibles.length());
            builder.append(caracteresPosibles.charAt(pos));
        }
        return builder.toString().toUpperCase();
    }
}
