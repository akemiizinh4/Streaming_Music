public class UsuarioFree extends Usuario {
    private static final int MAX_PLAYLISTS = 3;
    private int contadorReproducoes;
    
    public static int reproducoesFree = 0;
    public static int anunciosExibidos = 0;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
        this.contadorReproducoes = 0;
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        contadorReproducoes++;
        reproducoesFree++;
        
        if (contadorReproducoes % 3 == 0) {
            exibirAnuncio();
        }
        
        super.reproduzirMusica(musica);
    }

    @Override
    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("❌ Limite de playlists atingido!");
            System.out.println("💎 Assine Premium para playlists ilimitadas!");
            return;
        }
        super.criarPlaylist(nome); 
    }

    private final void exibirAnuncio() {
        anunciosExibidos++;
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📢 ANÚNCIO: Assine Premium e ouça sem interrupções!");
        System.out.println("=".repeat(50) + "\n");
    }
}