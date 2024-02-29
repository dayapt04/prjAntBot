package BusinessLogic.Entities;

public class JSExpertoRuso extends JSHumano {

    public JSExpertoRuso(String jsNombre) {
        super(jsNombre);
    }

    public void jsTrasferirLexicoRuso(JSITecnicoRuso jsAntBot) {
        jsAntBot.jsAprenderLexicoRuso();
    }

    public void jsTrasferirGramaticaRuso(JSITecnicoRuso jsAntBot) {
        jsAntBot.jsAprenderGramaticaRuso();
    }

    public void jsTrasferirFoneticaRuso(JSITecnicoRuso jsAntBot) {
        jsAntBot.jsAprenderFoneticaRuso();

    }

    public String getNombre() {
        return getJsNombre();

    }
}