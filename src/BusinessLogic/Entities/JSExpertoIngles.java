package BusinessLogic.Entities;
public class JSExpertoIngles extends JSHumano {

    public JSExpertoIngles(String jsNombre) {
        super(jsNombre);
    }

    public void jsTrasferirLexicoIngles(JSITecnicoIngles jsAntBot) {
        jsAntBot.jsAprenderLexicoIngles();
    }

    public void jsTrasferirGramaticaIngles(JSITecnicoIngles jsAntBot) {
        jsAntBot.jsAprenderGramaticaIngles();
    }

    public void jsTrasferirFoneticaIngles(JSITecnicoIngles jsAntBot) {
        jsAntBot.jsAprenderFoneticaIngles();

    }

    public String getNombre() {
        return getJsNombre();
    }
}
