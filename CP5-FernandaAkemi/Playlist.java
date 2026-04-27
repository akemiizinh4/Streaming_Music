import java.util.ArrayList;

public class Playlist {
    protected String nome;
    protected ArrayList<Musica> musicas;

    public Playlist(String nome) {
        setNome(nome);
        this.musicas = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        } else {
            this.nome = "Nova Playlist";
        }
    }

    public ArrayList<Musica> getMusicas() { return musicas; }

    public void adicionarMusica(Musica m) {
        if (m != null) {
            musicas.add(m);
        }
    }

    public void removerMusica(int index) {
        if (index >= 0 && index < musicas.size()) {
            musicas.remove(index);
        } else {
            System.out.println("⚠️ Índice inválido para remoção.");
        }
    }

    // Método que será alvo de Polimorfismo
    public void reproduzir() {
        System.out.println("▶️ Reproduzindo playlist: " + nome);
        for (Musica m : musicas) {
            System.out.println("  ▶ " + m.getTitulo());
        }
    }
}