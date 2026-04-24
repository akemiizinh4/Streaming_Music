public class UsuarioFree extends Usuario {
    private static final int MAX_PLAYLISTS = 3;
    private int contadorReproducoes;

    public UsuarioFree(String nome, String email) {
        super(nome, email); // 🎯 super: Chama o construtor da classe base (Usuario)
        this.contadorReproducoes = 0;
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        contadorReproducoes++;
        
        // A cada 3 músicas, exibir anúncio
        if (contadorReproducoes % 3 == 0) {
            exibirAnuncio();
        }
        
        super.reproduzirMusica(musica); // 🎯 super: Executa a lógica original de reprodução
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

    private void exibirAnuncio() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📢 ANÚNCIO: Assine Premium e ouça sem interrupções!");
        System.out.println("=".repeat(50) + "\n");
    }
}