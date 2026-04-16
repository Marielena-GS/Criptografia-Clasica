package cifrados;

public class AtbashCifrado implements Cifrado {

    @Override
    public String cifrar(String texto) {
        return transformar(texto);
    }

    @Override
    public String descifrar(String texto) {
        return transformar(texto);
    }

   private String transformar(String texto) {
        StringBuilder resultado = new StringBuilder();

        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    resultado.append((char) ('Z' - (c - 'A')));
                } else {
                    resultado.append((char) ('z' - (c - 'a')));
                }
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    public String getNombre() {
        return "Cifrado Atbash";
    }

    public String getDescripcion() {
        return "Sustituye cada letra por su opuesta en el alfabeto.";
    }
}