import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JuegoAleatorio extends NJuegos {
    private boolean terminado;
    private Jugador[] jugadores;
    private int numeroAleatorio;
    Random random = new Random();

    public JuegoAleatorio(List<Jugador> jugadoresConfig, int rango) {
        terminado = false;
        this.jugadores = new Jugador[2];
        crearJugadores(jugadoresConfig);
        seleccionarPrimerJugador(jugadoresConfig.get(0).getNombre());
        this.numeroAleatorio = random.nextInt(rango);
    }

    public void jugar() {
        verificarFinalizacion(jugadorActual(), introducirNumero(jugadorActual()));
    }

    public boolean verificarFinalizacion(Jugador jugadorActual, int numeroIntroducido) {
        if (numeroIntroducido == numeroAleatorio) {
            terminado = true;
            System.out.println("El jugador " + jugadorActual.getNombre() + " ha ganado");
        } else {
            terminado = false;
        }
        return terminado;
    }

    public int introducirNumero(Jugador jugadorActual) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Introduce un número para adivinar el número aleatorio, " + jugadorActual.getNombre());
        return leer.nextInt();
    }

    public void crearJugadores(List<Jugador> jugadoresConfig) {
        for (int i = 0; i < jugadores.length; i++) {
            Jugador config = jugadoresConfig.get(i);
            jugadores[i] = new Jugador(config.getNombre());
            jugadores[i].setFicha(config.getFicha());
            jugadores[i].setTurno(config.isTurno());
        }
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
}

