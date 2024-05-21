import java.util.List;
import java.util.Scanner;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JuegoNRaya extends NJuegos {
    private boolean terminado;
    private Jugador[] jugadores;
    private Tablero tablero;

    public JuegoNRaya() {
        // Constructor sin argumentos necesario para JAXB
    }

    public JuegoNRaya(List<Jugador> jugadores, int tamanoTablero) {
        terminado = false;
        this.jugadores = new Jugador[2];
        crearJugadores(jugadores);
        seleccionarPrimerJugador(jugadores.get(0).getNombre());
        this.tablero = new Tablero(tamanoTablero);
    }

    public void jugar() {
        colocarFicha(jugadorActual());
        tablero.mostrarTablero();
        terminado = tablero.verificarFinalizacion(jugadorActual());
    }

    public void cambiarTurno() {
        if (jugadores[0].isTurno()) {
            jugadores[0].setTurno(false);
            jugadores[1].setTurno(true);
        } else {
            jugadores[0].setTurno(true);
            jugadores[1].setTurno(false);
        }
    }

    public Jugador jugadorActual() {
        return jugadores[0].isTurno() ? jugadores[0] : jugadores[1];
    }

    public void seleccionarPrimerJugador(String nombrePrimerJugador) {
        if (nombrePrimerJugador.equals(jugadores[0].getNombre())) {
            jugadores[0].setTurno(true);
        } else {
            jugadores[1].setTurno(true);
        }
    }

    public void crearJugadores(List<Jugador> Njugadores) {
        for (int i = 0; i < jugadores.length; i++) {
            Jugador config = Njugadores.get(i);
            jugadores[i] = new Jugador(config.getNombre());
            jugadores[i].setFicha(config.getFicha());
            jugadores[i].setTurno(config.isTurno());
        }
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public void colocarFicha(Jugador jugadorActual) {
        int fila, columna;
        boolean jugadaValida = false;
        Scanner leer = new Scanner(System.in);
        while (!jugadaValida) {
            System.out.print("Jugador " + jugadorActual.getNombre() + ", ingresa la fila (1-" + tablero.getTamano() + "): ");
            fila = leer.nextInt() - 1;
            System.out.print("Jugador " + jugadorActual.getNombre() + ", ingresa la columna (1-" + tablero.getTamano() + "): ");
            columna = leer.nextInt() - 1;
            if (fila >= 0 && fila < tablero.getTamano() && columna >= 0 && columna < tablero.getTamano() && tablero.getPosicion(fila, columna).equals("*")) {
                tablero.setPosicion(fila, columna, jugadorActual.getFicha());
                jugadaValida = true;
            } else {
                System.out.println("Casilla ocupada o entrada inválida. Inténtalo de nuevo.");
            }
        }
    }
}

