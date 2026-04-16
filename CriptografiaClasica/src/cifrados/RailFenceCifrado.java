package cifrados;

public class RailFenceCifrado implements Cifrado {

    private int filas;

    public RailFenceCifrado(int filas) {
        this.filas = filas;
    }

    @Override
    public String cifrar(String texto) {
        if (filas <= 1)
            return texto;

        StringBuilder[] rail = new StringBuilder[filas];
        for (int i = 0; i < filas; i++) {
            rail[i] = new StringBuilder();
        }

        int fila = 0;
        int direccion = 1; // 1 = baja, -1 = sube

        for (int i = 0; i < texto.length(); i++) {
            rail[fila].append(texto.charAt(i));

            // CAMBIAR DIRECCIÓN ANTES DE SALIR
            if (fila == 0) {
                direccion = 1;
            } else if (fila == filas - 1) {
                direccion = -1;
            }

            fila += direccion;
        }

        StringBuilder resultado = new StringBuilder();
        for (StringBuilder r : rail) {
            resultado.append(r);
        }

        return resultado.toString();
    }

    @Override
    public String descifrar(String texto) {
        if (filas <= 1)
            return texto;

        boolean[][] marca = new boolean[filas][texto.length()];
        int dir = 1, fila = 0;

        for (int i = 0; i < texto.length(); i++) {
            marca[fila][i] = true;
            fila += dir;
            if (fila == 0 || fila == filas - 1)
                dir *= -1;
        }

        char[][] rail = new char[filas][texto.length()];
        int index = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < texto.length(); j++) {
                if (marca[i][j]) {
                    rail[i][j] = texto.charAt(index++);
                }
            }
        }

        StringBuilder resultado = new StringBuilder();
        fila = 0;
        dir = 1;

        for (int i = 0; i < texto.length(); i++) {
            resultado.append(rail[fila][i]);
            fila += dir;
            if (fila == 0 || fila == filas - 1)
                dir *= -1;
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