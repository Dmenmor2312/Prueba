import java.util.*;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "tipo", "tablero", "rango", "jugadores", "terminado" })
@XmlSeeAlso({JuegoNRaya.class, JuegoAleatorio.class})
abstract class NJuegos {

    private boolean terminado;
    private String tipo;
    private Tablero tablero;
    private int rango;
    private List<Jugador> jugadores;

    public NJuegos(){

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public abstract void jugar();
    public abstract void cambiarTurno();
}