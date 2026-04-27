import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Musica> bancoDeMusicas = new ArrayList<>();
    private static Usuario usuarioLogado; // Tipo base - O tipo real (Free/Premium) será definido na execução

    public static void main(String[] args) {
        popularDadosTeste();
        
        System.out.println("=== BEM-VINDO AO STREAMING ===");
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();

        System.out.println("\nEscolha o tipo de conta:");
        System.out.println("1. Free (Gratuito)");
        System.out.println("2. Premium (Pago)");
        System.out.print("Escolha: ");
        int tipoConta = lerOpcao();

        if (tipoConta == 2) {
            System.out.println("\nEscolha o plano Premium:");
            System.out.println("1. Mensal (R$ 19,90)");
            System.out.println("2. Anual (R$ 199,00)");
            System.out.println("3. Familiar (R$ 29,90)");
            System.out.print("Escolha: ");
            int opPlano = lerOpcao();
            String plano = opPlano == 2 ? "Anual" : opPlano == 3 ? "Familiar" : "Mensal";
            
            // Instanciando subclasse
            usuarioLogado = new UsuarioPremium(nome, email, plano);
            System.out.println("\n✅ Conta Premium criada com sucesso!");
        } else {
            // Instanciando subclasse
            usuarioLogado = new UsuarioFree(nome, email);
            System.out.println("\n✅ Conta Free criada com sucesso!");
        }

        int opcao;
        do {
            exibirMenuAdaptado();
            opcao = lerOpcao();
            processarMenu(opcao);
        } while (opcao != 0);

        System.out.println("🎵 Saindo do sistema...");
    }

    private static void exibirMenuAdaptado() {
        if (usuarioLogado instanceof UsuarioFree) {
            System.out.println("\n--- MENU FREE ---");
            System.out.println("1. Reproduzir música");
            System.out.println("2. Ver histórico");
            System.out.println("3. Criar playlist (máx. 3)");
            System.out.println("4. 💎 Fazer upgrade para Premium");
            System.out.println("0. Sair");
        } else if (usuarioLogado instanceof UsuarioPremium) {
            System.out.println("\n--- MENU PREMIUM ---");
            System.out.println("1. Reproduzir música (Alta Qualidade)");
            System.out.println("2. Ver histórico");
            System.out.println("3. Criar playlist (ilimitado)");
            System.out.println("4. Baixar música");
            System.out.println("5. Ver músicas baixadas");
            System.out.println("0. Sair");
        }
        System.out.print("Escolha: ");
    }

    private static void processarMenu(int opcao) {
        if (usuarioLogado instanceof UsuarioFree) {
            switch (opcao) {
                case 1 -> reproduzirDoBanco();
                case 2 -> usuarioLogado.exibirHistorico();
                case 3 -> criarNovaPlaylist();
                case 4 -> fazerUpgrade();
                case 0 -> {}
                default -> System.out.println("⚠️ Opção inválida.");
            }
        } else if (usuarioLogado instanceof UsuarioPremium) {
            UsuarioPremium userPremium = (UsuarioPremium) usuarioLogado;
            switch (opcao) {
                case 1 -> reproduzirDoBanco();
                case 2 -> userPremium.exibirHistorico();
                case 3 -> criarNovaPlaylist();
                case 4 -> baixarMusicaSistema(userPremium);
                case 5 -> userPremium.listarMusicasBaixadas();
                case 0 -> {}
                default -> System.out.println("⚠️ Opção inválida.");
            }
        }
    }

    private static void reproduzirDoBanco() {
        System.out.println("\n--- MÚSICAS DISPONÍVEIS ---");
        for (int i = 0; i < bancoDeMusicas.size(); i++) {
            System.out.println((i + 1) + ". " + bancoDeMusicas.get(i).getDadosFormatados());
        }
        System.out.print("ID da música para reproduzir: ");
        int id = lerOpcao() - 1;
        if (id >= 0 && id < bancoDeMusicas.size()) {
            usuarioLogado.reproduzirMusica(bancoDeMusicas.get(id));
        } else {
            System.out.println("⚠️ Música não encontrada.");
        }
    }

    private static void criarNovaPlaylist() {
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();
        usuarioLogado.criarPlaylist(nome);
    }

    private static void baixarMusicaSistema(UsuarioPremium userPremium) {
        System.out.println("\n--- MÚSICAS DISPONÍVEIS PARA DOWNLOAD ---");
        for (int i = 0; i < bancoDeMusicas.size(); i++) {
            System.out.println((i + 1) + ". " + bancoDeMusicas.get(i).getDadosFormatados());
        }
        System.out.print("ID da música para baixar: ");
        int id = lerOpcao() - 1;
        if (id >= 0 && id < bancoDeMusicas.size()) {
            userPremium.baixarMusica(bancoDeMusicas.get(id));
        } else {
            System.out.println("⚠️ Música não encontrada.");
        }
    }

    private static void fazerUpgrade() {
        System.out.println("\nEscolha o plano Premium:");
        System.out.println("1. Mensal (R$ 19,90)");
        System.out.println("2. Anual (R$ 199,00)");
        System.out.println("3. Familiar (R$ 29,90)");
        System.out.print("Escolha: ");
        int opPlano = lerOpcao();
        String plano = opPlano == 2 ? "Anual" : opPlano == 3 ? "Familiar" : "Mensal";
        
        UsuarioPremium novoUsuario = new UsuarioPremium(usuarioLogado.getNome(), usuarioLogado.getEmail(), plano);
        novoUsuario.getPlaylists().addAll(usuarioLogado.getPlaylists());
        
        usuarioLogado = novoUsuario;
        System.out.println("✅ Upgrade concluído! Bem-vindo ao Premium.");
    }

    private static int lerOpcao() {
        try { return Integer.parseInt(scanner.nextLine().trim()); } 
        catch (Exception e) { return -1; }
    }

    private static void popularDadosTeste() {
        bancoDeMusicas.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        bancoDeMusicas.add(new Musica("Billie Jean", "Michael Jackson", 293, "Pop"));
        bancoDeMusicas.add(new Musica("Take Five", "Dave Brubeck", 324, "Jazz"));
        bancoDeMusicas.add(new Musica("Strobe", "deadmau5", 637, "Eletrônica"));
    }
}