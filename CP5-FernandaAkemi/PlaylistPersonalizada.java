public final class PlaylistPersonalizada extends Playlist {

    public PlaylistPersonalizada(String nome) {
        super(nome);
    }

    @Override
    public void reproduzir() {
        System.out.println("🎧 [Personalizada] " + nome);
        super.reproduzir();
    }
}