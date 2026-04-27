public final class PlaylistAutomatica extends Playlist {
    private String criterio;

    public PlaylistAutomatica(String nome, String criterio) {
        super(nome);
        this.criterio = criterio;
    }

    @Override
    public void reproduzir() {
        System.out.println("🤖 [Playlist Automática: " + nome + "]");
        System.out.println("📊 Critério: " + criterio);
        super.reproduzir();
    }
}