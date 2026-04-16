package factory;

import cifrados.*;

public class CifradoFactory {

    public static Cifrado crearCifrado(int opcion, int clave, String palabraClave) {
        switch (opcion) {
            case 1:
                return new CesarCifrado(clave);
            case 2:
                return new AtbashCifrado();
            case 3:
                return new VigenereCifrado(palabraClave);
            case 4:
                return new RailFenceCifrado(clave);
            case 5:
                return new PlayfairCifrado(palabraClave);
            default:
                throw new IllegalArgumentException("Opción inválida");
        }
    }
}
