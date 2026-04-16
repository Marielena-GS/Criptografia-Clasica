package ui;

import java.util.ArrayList;
import java.util.List;

public class Historial {

    private static List<String> historial = new ArrayList<>();

    public static void agregar(String registro) {
        historial.add(registro);
    }

    public static String obtenerHistorial() {
        StringBuilder sb = new StringBuilder();
        for (String s : historial) {
            sb.append(s).append("\n");
        }
        return sb.toString();
    }
}