package br.com.streaming.servico;

import br.com.streaming.modelo.Musica;
import br.com.streaming.modelo.PlaylistAutomatica;
import java.util.ArrayList;

public class GeradorRecomendacoes {

    public static PlaylistAutomatica gerarPorGenero(String genero, ArrayList<Musica> banco) {
        PlaylistAutomatica playlist = new PlaylistAutomatica("Recomendadas - " + genero, "Gênero: " + genero);
        for (Musica m : banco) {
            if (m.getGenero().equalsIgnoreCase(genero)) {
                playlist.adicionarMusica(m);
            }
        }
        return playlist;
    }

    public static PlaylistAutomatica gerarTopMaisTocadas(ArrayList<Musica> banco) {
        PlaylistAutomatica playlist = new PlaylistAutomatica("Top 10 Mais Tocadas", "Mais tocadas");
        int limite = Math.min(10, banco.size());
        for (int i = 0; i < limite; i++) {
            playlist.adicionarMusica(banco.get(i));
        }
        return playlist;
    }

    public static PlaylistAutomatica gerarAdicionadasRecentemente(ArrayList<Musica> banco) {
        PlaylistAutomatica playlist = new PlaylistAutomatica("Adicionadas Recentemente", "Recentes");
        int inicio = Math.max(0, banco.size() - 5);
        for (int i = inicio; i < banco.size(); i++) {
            playlist.adicionarMusica(banco.get(i));
        }
        return playlist;
    }
}
