import java.util.ArrayList;

public class UsuarioPremium extends Usuario {
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

    public void baixarMusica(Musica musica) {
        if (!musicasBaixadas.contains(musica)) {
            musicasBaixadas.add(musica);
            System.out.println("⬇️ Música baixada: " + musica.getTitulo());
        } else {
            System.out.println("ℹ️ A música já está baixada!");
        }
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
