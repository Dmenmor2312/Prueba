import javax.xml.bind.annotation.*;

@XmlRootElement
public class Jugador {
    private String nombre;
    private String ficha;
    private boolean turno;

    // Constructor sin argumentos necesario para JAXB
    public Jugador() {
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement
    public String getFicha() {
        return ficha;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    @XmlElement
    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }
}
