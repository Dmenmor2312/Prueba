import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "juego")
public class Juego {

    private ListaDeJuegos listaDeJuegos;

    @XmlElement(name = "ListaDeJuegos")
    public ListaDeJuegos getListaDeJuegos() {
        return listaDeJuegos;
    }

    public void setListaDeJuegos(ListaDeJuegos listaDeJuegos) {
        this.listaDeJuegos = listaDeJuegos;
    }

    public static void main(String[] args) {
        BufferedReader leer = null;
        try {
            leer = new BufferedReader(new FileReader("C:\\Users\\pc´sgaming\\Desktop\\Pruebecita\\Juego.xml"));
            // Crear el contexto JAXB y el Unmarshaller
            JAXBContext context = JAXBContext.newInstance(Juego.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Deserializar el archivo XML
            Juego juego = (Juego) unmarshaller.unmarshal(leer);

            // Verificar si se pudo leer el juego y si la lista de juegos no es nula o vacía
            if (juego != null && juego.getListaDeJuegos() != null && !juego.getListaDeJuegos().getNJuegos().isEmpty()) {
                // Crear la lista de juegos y jugar
                List<NJuegos> listaJuegos = crearListaJuegos(juego.getListaDeJuegos().getNJuegos());
                jugarNJuegos(listaJuegos);
            } else {
                System.out.println("No se encontraron juegos en el archivo XML.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: No se pudo encontrar el archivo XML. Asegúrate de que el archivo existe en la ruta especificada.");
            e.printStackTrace();
        } catch (JAXBException e) {
            System.err.println("Error al procesar el archivo XML. Asegúrate de que el archivo esté bien formado y coincida con la estructura esperada.");
            e.printStackTrace();
        } finally {
            if (leer != null) {
                try {
                    leer.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar el recurso de lectura.");
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<NJuegos> crearListaJuegos(List<NJuegos> juegos) {
        List<NJuegos> listaJuegos = new ArrayList<>();
        if (juegos != null) {
            for (int i = 0; i < juegos.size(); i++) {
                NJuegos config = juegos.get(i);
                NJuegos juego = null;
                if (config.getTipo().equals("NRaya")) {
                    juego = new JuegoNRaya(config.getJugadores(), config.getTablero().getTamano());
                } else if (config.getTipo().equals("Aleatorio")) {
                    juego = new JuegoAleatorio(config.getJugadores(), config.getRango());
                }
                if (juego != null) {
                    juego.jugar();
                    listaJuegos.add(juego);
                }
            }
        }
        return listaJuegos;
    }

    public static void jugarNJuegos(List<NJuegos> listaJuegos) {
        if (listaJuegos != null) {
            boolean algunJuegoNoTerminado;
            do {
                algunJuegoNoTerminado = false;
                for (int i = 0; i < listaJuegos.size(); i++) {
                    NJuegos juego = listaJuegos.get(i);
                    if (!juego.isTerminado()) {
                        juego.jugar();
                        algunJuegoNoTerminado = true;
                    } else {
                        juego.setTerminado(true);
                    }
                    juego.cambiarTurno();
                }
            } while (algunJuegoNoTerminado);
        } else {
            System.out.println("La lista de juegos es nula.");
        }
    }
}
