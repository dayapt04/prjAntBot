import BusinessLogic.Entities.JSExpertoIngles;
import BusinessLogic.Entities.JSExpertoRuso;
import BusinessLogic.Entities.JSIA;

public class Hormiguero {
    public void iniciar() throws InterruptedException {
        JSIA e = new JSIA();
        JSExpertoRuso jsexpertoRuso = new JSExpertoRuso("Francisco");
        JSExpertoIngles jsexpertoIngles = new JSExpertoIngles("Carlos");
        System.out.println("\n---------------- R U S O ----------------\n" );
        System.out.println(" -> Trasfiriendo a la IA conocimientos de Ruso," + " soy el maestro "
                + jsexpertoRuso.getNombre());
        System.out.println(e.jsAprenderFoneticaRuso());
        System.out.println(e.jsAprenderGramaticaRuso());
        System.out.println(e.jsAprenderLexicoRuso());
        System.out.println( "\n [] Conocimientos: \n" );
        e.jsConocimientoRuso();

        System.out.println("\n------------------- I N G L É S -----------------\n" );
        System.out.println(" -> Trasfiriendo a la IA conocimientos de inglés," + " soy el maestro "
                + jsexpertoIngles.getNombre());
        System.out.println(e.jsAprenderFoneticaIngles());
        System.out.println(e.jsAprenderGramaticaIngles());
        System.out.println(e.jsAprenderLexicoIngles());
        System.out.println( "\n [] Conocimientos: \n" );
        e.jsConocimientoIngles();
        System.out.println();
        e.jsComunicacionHormigas();

        System.out.println("\n------------ M A N I P  U L A C I Ó N   H O R M I G A S -----------------\n" );
        System.out.println(e.jsManipularHormiga());
        System.out.println(e.jsUsarAntena());
        System.out.println(e.jsUsarRadio());
        System.out.println(e.jsUsarSensor());

}

}

