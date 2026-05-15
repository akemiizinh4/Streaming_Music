package br.com.streaming.servico;

import br.com.streaming.modelo.Musica;

public interface Baixavel {
    void baixar(Musica musica);
    void removerDownload(Musica musica);
    boolean estaBaixado(Musica musica);
    int getTamanhoBaixados();
}
