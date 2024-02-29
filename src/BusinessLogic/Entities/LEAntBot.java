package BusinessLogic.Entities;

public class LEAntBot extends JSIA {
    private LECircuitoAnillado leCircuitoAnillado;
    private LERadio leRadio;
    private LECircuitoCarga leCircuitoCarga;
    private PATransductorFlexion leTransductorFlexion;
    private PATenazaAgarre leTenazaAgarre;
    private LEProcesador leProcesador;
    private PASensorOpticoCamara leSensorOpticoCamara;
    private PAAntenaContacto leAntenaContacto;
    // corregir diagrama, hay una interface que ya tiene extremidades que podriamos
    // poner aquí

    private String leSerieAntBot;

    public LEAntBot(JSIA ia) {
        leCircuitoAnillado = new LECircuitoAnillado();
        leRadio = new LERadio();
        leCircuitoCarga = new LECircuitoCarga();
        leTransductorFlexion = new PATransductorFlexion();
        leTenazaAgarre = new PATenazaAgarre();
        leProcesador = new LEProcesador();
        leSensorOpticoCamara = new PASensorOpticoCamara();
        leAntenaContacto = new PAAntenaContacto();
    }

    public LEAntBot(JSIA ia, PAFuentePoder leFuentePoder, PAAla leAla) {
        leCircuitoAnillado = new LECircuitoAnillado();
        leRadio = new LERadio();
        leCircuitoCarga = new LECircuitoCarga();
        leTransductorFlexion = new PATransductorFlexion();
        leTenazaAgarre = new PATenazaAgarre();
        leProcesador = new LEProcesador();
        leSensorOpticoCamara = new PASensorOpticoCamara();
        leAntenaContacto = new PAAntenaContacto();
    }

    public void integrarHormiga(LEHormiga leHormiga) {
    }

    public void potenciarFuerza() {
    }

    public void potenciarHabilidad() {
    }

    public void potenciarMovimiento() {
    }

    public void volar(LEZangano leZangano) {
    }

}
