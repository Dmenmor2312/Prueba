import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Tablero {

    private int tamano;
    private String[][] tablero;

    public Tablero() {
        // Constructor sin argumentos necesario para JAXB
    }

    public Tablero(int tamano) {
        this.tamano = tamano;
        tablero = new String[tamano][tamano];
        inicializarTablero();
    }

    public int getTamano() {
        return tamano;
    }

    public String[][] getTablero() {
        return tablero;
    }

    public String getPosicion(int fila, int columna) {
        return tablero[fila][columna];
    }

    public void setPosicion(int fila, int columna, String ficha) {
        tablero[fila][columna] = ficha;
    }

    public void inicializarTablero() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                tablero[i][j] = "*";
            }
        }
    }

    public void mostrarTablero() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean verificarFinalizacion(Jugador jugadorActual) {
        String ficha = jugadorActual.getFicha();
        // Verificar filas y columnas
        for (int i = 0; i < tamano; i++) {
            if (verificarLinea(tablero[i], ficha) || verificarLinea(columnaAArray(i), ficha)) {
                return true;
            }
        }
        // Verificar diagonales
        return verificarLinea(diagonalPrincipalAArray(), ficha) || verificarLinea(diagonalSecundariaAArray(), ficha);
    }

    private boolean verificarLinea(String[] linea, String ficha) {
        for (String s : linea) {
            if (!s.equals(ficha)) {
                return false;
            }
        }
        return true;
    }

    private String[] columnaAArray(int columna) {
        String[] colArray = new String[tamano];
        for (int i = 0; i < tamano; i++) {
            colArray[i] = tablero[i][columna];
        }
        return colArray;
    }

    private String[] diagonalPrincipalAArray() {
        String[] diagArray = new String[tamano];
        for (int i = 0; i < tamano; i++) {
            diagArray[i] = tablero[i][i];
        }
        return diagArray;
    }

    private String[] diagonalSecundariaAArray() {
        String[] diagArray = new String[tamano];
        for (int i = 0; i < tamano; i++) {
            diagArray[i] = tablero[i][tamano - i - 1];
        }
        return diagArray;
    }
}
