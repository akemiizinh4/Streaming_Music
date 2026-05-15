package br.com.streaming.modelo;

import br.com.streaming.servico.Baixavel;
import java.util.ArrayList;

public class UsuarioPremium extends Usuario implements Baixavel {
    private String plano;
    private ArrayList<Musica> musicasBaixadas;

    public static int reproducoesPremium = 0;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
        this.musicasBaixadas = new ArrayList<>();
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        reproducoesPremium++;
        System.out.println("🎵 Reproduzindo em ALTA QUALIDADE: " + musica.getTitulo());
        super.reproduzirMusica(musica);
    }

    // ================= Implementação de Baixavel =================

    @Override
    public void baixar(Musica musica) {
        if (!musicasBaixadas.contains(musica)) {
            musicasBaixadas.add(musica);
            System.out.println("⬇️ Música baixada: " + musica.getTitulo());
        } else {
            System.out.println("ℹ️ A música já está baixada!");
        }
    }

    @Override
    public void removerDownload(Musica musica) {
        if (musicasBaixadas.remove(musica)) {
            System.out.println("🗑️ Download removido: " + musica.getTitulo());
        } else {
            System.out.println("⚠️ Música não encontrada nos downloads.");
        }
    }

    @Override
    public boolean estaBaixado(Musica musica) {
        return musicasBaixadas.contains(musica);
    }

    @Override
    public int getTamanhoBaixados() {
        return musicasBaixadas.size();
    }

    public void baixarMusica(Musica musica) {
        baixar(musica);
    }

    public void listarMusicasBaixadas() {
        System.out.println("\n--- MÚSICAS BAIXADAS ---");
        if (musicasBaixadas.isEmpty()) {
            System.out.println("Nenhuma música baixada.");
            return;
        }
        for (Musica m : musicasBaixadas) {
            System.out.println(m.getDadosFormatados());
        }
    }

    public String getPlano() { return plano; }
}
