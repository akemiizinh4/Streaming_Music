package br.com.streaming.modelo;

import br.com.streaming.servico.Reproduzivel;
import br.com.streaming.util.FormataTempos;
import java.util.Arrays;
import java.util.List;

public class Musica implements Reproduzivel {
    private String titulo;
    private String artista;
    private int duracao;
    private String genero;

    public Musica(String titulo, String artista, int duracao, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo.trim();
        } else {
            this.titulo = "Título Indefinido";
        }
    }

    public String getArtista() { return artista; }
    public void setArtista(String artista) {
        if (artista != null && !artista.trim().isEmpty()) {
            this.artista = artista.trim();
        } else {
            this.artista = "Artista Indefinido";
        }
    }

    public int getDuracao() { return duracao; }
    public void setDuracao(int duracao) {
        if (duracao > 0 && duracao < 3600) {
            this.duracao = duracao;
        } else {
            this.duracao = 1;
        }
    }

    public String getGenero() { return genero; }
    public void setGenero(String genero) {
        List<String> validos = Arrays.asList("Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica");
        boolean encontrado = false;
        for (String g : validos) {
            if (g.equalsIgnoreCase(genero)) {
                this.genero = g;
                encontrado = true;
                break;
            }
        }
        if (!encontrado) this.genero = "Indefinido";
    }

    public String getDadosFormatados() {
        return String.format("%s - %s (%s) [%s]", titulo, artista, FormataTempos.formatarSegundos(duracao), genero);
    }

    // ================= Implementação de Reproduzivel =================

    @Override
    public void reproduzir() {
        System.out.println("▶️ Reproduzindo música: " + titulo + " - " + artista);
    }

    @Override
    public void pausar() {
        System.out.println("⏸️ Pausando: " + titulo);
    }

    @Override
    public void parar() {
        System.out.println("⏹️ Parando: " + titulo);
    }

    @Override
    public int getDuracaoTotal() {
        return duracao;
    }
}
