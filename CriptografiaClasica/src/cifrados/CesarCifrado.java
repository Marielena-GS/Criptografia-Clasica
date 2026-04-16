package cifrados;

public class CesarCifrado implements Cifrado{

    private int desplazamiento;

    public CesarCifrado(int desplazamiento){
        this.desplazamiento = desplazamiento;
    }    

    @Override
    public String cifrar(String texto){
        return transformar(texto, desplazamiento);
    }

    @Override
    public String descifrar(String texto){
        return transformar(texto, 26 - desplazamiento);
    }

    private String transformar(String texto, int desplazamiento) {
        StringBuilder resultado = new StringBuilder();

        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                resultado.append((char) ((c - base + desplazamiento) % 26 + base));
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    public String getNombre() {
        return "Cifrado César";
    }

    public String getDescripcion() {
        return "Desplaza las letras del alfabeto un número fijo de posiciones.";
    }
}
