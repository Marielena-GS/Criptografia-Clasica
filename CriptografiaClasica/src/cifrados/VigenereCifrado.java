package cifrados;

public class VigenereCifrado implements Cifrado{
     private String clave;

    public VigenereCifrado(String clave) {
        this.clave = clave.toLowerCase();
    }

    @Override
    public String cifrar(String texto) {
        return procesar(texto, true);
    }

    @Override
    public String descifrar(String texto) {
        return procesar(texto, false);
    }

    private String procesar(String texto, boolean cifrar) {
        StringBuilder resultado = new StringBuilder();
        int j = 0;

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            if (Character.isLetter(c)) {
                int base = Character.isUpperCase(c) ? 'A' : 'a';
                int k = clave.charAt(j % clave.length()) - 'a';

                if (!cifrar) k = 26 - k;

                char nuevo = (char) ((c - base + k) % 26 + base);
                resultado.append(nuevo);
                j++;
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    public String getNombre() {
        return "Cifrado Vigenere";
    }

    public String getDescripcion() {
        return "Usa una palabra clave para desplazar letras de forma variable.";
    }
}