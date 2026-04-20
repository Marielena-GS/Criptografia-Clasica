package cifrados;

import java.util.ArrayList;
import java.util.List;

public class RailFenceCifrado implements Cifrado {

    private int filas;

    public RailFenceCifrado(int filas) {
        this.filas = filas;
    }

    @Override
    public String cifrar(String texto) {
        if (filas <= 1) return texto;

        // Guardar posiciones de espacios
        List<Integer> espacios = new ArrayList<>();
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == ' ') {
                espacios.add(i);
            }
        }

        // Quitar espacios
        String limpio = texto.replaceAll("\\s+", "");

        StringBuilder[] rail = new StringBuilder[filas];
        for (int i = 0; i < filas; i++) {
            rail[i] = new StringBuilder();
        }

        int fila = 0;
        int dir = 1;

        for (int i = 0; i < limpio.length(); i++) {
            rail[fila].append(limpio.charAt(i));

            if (fila == 0) {
                dir = 1;
            } else if (fila == filas - 1) {
                dir = -1;
            }

            fila += dir;
        }

        // Unir resultado
        StringBuilder resultado = new StringBuilder();
        for (StringBuilder r : rail) {
            resultado.append(r);
        }

        // Reinsertar espacios
        for (int pos : espacios) {
            resultado.insert(pos, ' ');
        }

        return resultado.toString();
    }

    @Override
    public String descifrar(String texto) {
        if (filas <= 1) return texto;

        // Guardar posiciones de espacios
        List<Integer> espacios = new ArrayList<>();
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == ' ') {
                espacios.add(i);
            }
        }

        // Quitar espacios
        String limpio = texto.replaceAll("\\s+", "");

        boolean[][] marca = new boolean[filas][limpio.length()];

        int fila = 0;
        int dir = 1;

        // Marcar zig-zag
        for (int i = 0; i < limpio.length(); i++) {
            marca[fila][i] = true;

            if (fila == 0) {
                dir = 1;
            } else if (fila == filas - 1) {
                dir = -1;
            }

            fila += dir;
        }

        // Llenar matriz
        char[][] rail = new char[filas][limpio.length()];
        int index = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < limpio.length(); j++) {
                if (marca[i][j]) {
                    rail[i][j] = limpio.charAt(index++);
                }
            }
        }

        // Reconstruir mensaje
        StringBuilder resultado = new StringBuilder();
        fila = 0;
        dir = 1;

        for (int i = 0; i < limpio.length(); i++) {
            resultado.append(rail[fila][i]);

            if (fila == 0) {
                dir = 1;
            } else if (fila == filas - 1) {
                dir = -1;
            }

            fila += dir;
        }

        // Reinsertar espacios
        for (int pos : espacios) {
            resultado.insert(pos, ' ');
        }

        return resultado.toString();
    }

    public String getNombre() {
        return "Cifrado Rail Fence";
    }

    public String getDescripcion() {
        return "Organiza el texto en zigzag y lo lee por filas.";
    }
}