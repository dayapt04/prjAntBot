package DataAccess.DTO;

import java.sql.Timestamp;

public class LZHormigaDTO {
    private Integer idHormiga;
    private Integer idHormigaClasificacion;
    private String nombre;
    private String estado;
    private Timestamp fechaCrea;
    private Timestamp fechaModifica;

    public LZHormigaDTO() {}

    public LZHormigaDTO(Integer idHormigaClasificacion, String nombre) {
        this.idHormigaClasificacion = idHormigaClasificacion;
        this.nombre = nombre;
    }

    public LZHormigaDTO(Integer idHormiga, Integer idHormigaClasificacion, String nombre, String estado, Timestamp fechaCrea, Timestamp fechaModifica) {
        this.idHormiga = idHormiga;
        this.idHormigaClasificacion = idHormigaClasificacion;
        this.nombre = nombre;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getIdHormiga() {
        return idHormiga;
    }

    public void setIdHormiga(Integer idHormiga) {
        this.idHormiga = idHormiga;
    }

    public Integer getIdHormigaClasificacion() {
        return idHormigaClasificacion;
    }

    public void setIdHormigaClasificacion(Integer idHormigaClasificacion) {
        this.idHormigaClasificacion = idHormigaClasificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Timestamp fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public Timestamp getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(Timestamp fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    @Override
    public String toString() {
        return getClass().getName()
            + "\n IdHormiga:                " + getIdHormiga()
            + "\n IdHormigaClasificacion:  " + getIdHormigaClasificacion()
            + "\n Nombre:                   " + getNombre()
            + "\n Estado:                   " + getEstado()
            + "\n FechaCrea:                " + getFechaCrea()
            + "\n FechaModifica:            " + getFechaModifica();
    }
}
