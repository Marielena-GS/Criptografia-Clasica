package cifrados;

import java.util.*;

public class PlayfairCifrado implements Cifrado {

    private char[][] matriz = new char[5][5];
    private String clave;

    public PlayfairCifrado(String clave) {
        this.clave = clave.toUpperCase().replace("J", "I");
        generarMatriz();
    }

    private void generarMatriz() {
        Set<Character> usados = new LinkedHashSet<>();

        for (char c : clave.toCharArray()) {
            if (Character.isLetter(c)) usados.add(c);
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J') usados.add(c);
        }

        Iterator<Character> it = usados.iterator();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = it.next();
            }
        }
    }

    private int[] buscar(char c) {
        if (c == 'J') c = 'I';

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matriz[i][j] == c) return new int[]{i, j};
            }
        }
        return null;
    }

    // PREPARAR TEXTO (SOLO PARA CIFRAR)
    private String prepararTexto(String texto) {
        texto = texto.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");

        StringBuilder preparado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char a = texto.charAt(i);
            preparado.append(a);

            if (i + 1 < texto.length()) {
                char b = texto.charAt(i + 1);

                if (a == b) {
                    preparado.append('X');
                } else {
                    preparado.append(b);
                    i++;
                }
            }
        }

        if (preparado.length() % 2 != 0) {
            preparado.append('X');
        }

        return preparado.toString();
    }

    @Override
    public String cifrar(String texto) {
        texto = prepararTexto(texto);
        return procesar(texto, true);
    }

    @Override
    public String descifrar(String texto) {
        texto = texto.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
        return procesar(texto, false);
    }

    private String procesar(String texto, boolean cifrar) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < texto.length(); i += 2) {
            char a = texto.charAt(i);
            char b = texto.charAt(i + 1);

            int[] posA = buscar(a);
            int[] posB = buscar(b);

            if (posA[0] == posB[0]) {
                int shift = cifrar ? 1 : 4;
                resultado.append(matriz[posA[0]][(posA[1] + shift) % 5]);
                resultado.append(matriz[posB[0]][(posB[1] + shift) % 5]);

            } else if (posA[1] == posB[1]) {
                int shift = cifrar ? 1 : 4;
                resultado.append(matriz[(posA[0] + shift) % 5][posA[1]]);
                resultado.append(matriz[(posB[0] + shift) % 5][posB[1]]);

            } else {
                resultado.append(matriz[posA[0]][posB[1]]);
                resultado.append(matriz[posB[0]][posA[1]]);
            }
        }

        return resultado.toString();
    }

    public String getNombre() {
        return "Cifrado Playfair";
    }

    public String getDescripcion() {
        return "Cifra pares de letras usando una matriz 5x5 basada en una clave.";
    }
}