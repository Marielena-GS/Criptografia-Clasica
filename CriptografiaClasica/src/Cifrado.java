import java.util.Scanner;

public class Cifrado {
    public static String cifradoCesar(String texto, int desplazamiento){

        StringBuilder resultado = new StringBuilder();
        for(int i = 0; i<texto.length(); i++){
            char caracter = texto.charAt(i);

            if(Character.isLetter(caracter)){
                char base = Character.isUpperCase(caracter) ? 'A' : 'a';
                char cifrado = (char)((caracter - base + desplazamiento) % 26 + base);
                resultado.append(cifrado);
            }else {
                resultado.append(caracter);
            }
        }
        return resultado.toString();
    }

    public static String descifradoCesar(String texto, int desplazamiento) {
        return cifradoCesar(texto, 26 - desplazamiento);
    }

    public static String cifradoAtbash(String texto) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    resultado.append((char) ('Z' - (c - 'A')));
                } else {
                    resultado.append((char) ('Z' - (c - 'a')));
                }
            }else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.println("Cifrado Clasico");
        System.out.println("1. Cifrado César");
        System.out.println("2. Cifrado Atbash");
        System.out.print("Seleccione una opción: ");
        int opcion = scan.nextInt();
        scan.nextLine();

        if(opcion == 1){
            System.out.print("Texto a cifrar: ");
            String texto = scan.nextLine();

            System.out.print("Ingrese el desplazamiento: ");
            int clave = scan.nextInt();

            String cifrado = cifradoCesar(texto, clave);
            System.out.println("Texto a cifrar: " + cifrado);

            String descifrado = descifradoCesar(cifrado, clave);
            System.out.print("Texto a descifrar: " + descifrado);
            
        }else if(opcion == 2){
            System.out.print("Texto a cifrar: ");
            String texto = scan.nextLine();

            String cifrado = cifradoAtbash(texto);
            System.out.println("Texto a cifrar: " + cifrado);

            String descifrado = cifradoAtbash(cifrado);
            System.out.println("Texto a descifrar: " + descifrado);
        }
    }
}
