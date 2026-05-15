package br.com.streaming.util;

public class FormataTempos {

    public static String formatarSegundos(int totalSegundos) {
        int min = totalSegundos / 60;
        int seg = totalSegundos % 60;
        return String.format("%d:%02d", min, seg);
    }

    public static String formatarDuracaoPlaylist(int totalSegundos) {
        int horas = totalSegundos / 3600;
        int min = (totalSegundos % 3600) / 60;
        int seg = totalSegundos % 60;
        if (horas > 0) {
            return String.format("%dh %02dmin %02ds", horas, min, seg);
        }
        return String.format("%dmin %02ds", min, seg);
    }
}
