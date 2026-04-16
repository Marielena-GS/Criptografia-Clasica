package cifrados;

public interface Cifrado {
    
    String cifrar(String texto);
    String descifrar(String texto);
    String getNombre();
    String getDescripcion();

}
