import java.util.ArrayList;

public class Usuario {
    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists;
    protected ArrayList<Musica> historicoReproducao;

    public Usuario(String nome, String email) {
        setNome(nome);
        setEmail(email);
        this.playlists = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        } else {
            this.nome = "Usuário Anônimo";
        }
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Playlist> getPlaylists() { return playlists; }

    public void criarPlaylist(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            playlists.add(new Playlist(nome));
            System.out.println("✅ Playlist criada!");
        }
    }

    public void reproduzirMusica(Musica musica) {
        System.out.println("🎵 Reproduzindo: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    public void reproduzirMusica(Playlist playlist) {
        System.out.println("▶️ Iniciando playlist: " + playlist.getNome());
        for (Musica m : playlist.getMusicas()) {
            reproduzirMusica(m); // Reutiliza o método acima
        }
    }

    public void exibirHistorico() {
        System.out.println("\n--- HISTÓRICO DE REPRODUÇÃO ---");
        if (historicoReproducao.isEmpty()) {
            System.out.println("Nenhuma música reproduzida ainda.");
        } else {
            for (Musica m : historicoReproducao) {
                // Usando getDadosFormatados() já existente na sua classe Musica
                System.out.println(m.getDadosFormatados()); 
            }
        }
    }
}