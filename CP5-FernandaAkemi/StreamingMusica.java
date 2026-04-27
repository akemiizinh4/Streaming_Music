import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Musica> bancoDeMusicas = new ArrayList<>();
    
    private static final ArrayList<Usuario> usuarios = new ArrayList<>();
    private static Usuario usuarioLogado = null;

    public static void main(String[] args) {
        popularDadosTeste();
        int opcao;

        do {
            if (usuarioLogado == null) {
                exibirMenuPrincipal();
                opcao = lerOpcao();
                processarMenuPrincipal(opcao);
            } else {
                exibirMenuUsuario();
                opcao = lerOpcao();
                processarMenuUsuario(opcao);
            }
        } while (opcao != 0 || usuarioLogado != null); 

        System.out.println("🎵 Sistema encerrado.");
    }

    // ================= MENUS =================

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- SISTEMA DE STREAMING ---");
        System.out.println("1. Criar novo usuário");
        System.out.println("2. Login");
        System.out.println("3. Listar usuários");
        System.out.println("4. Estatísticas do Sistema");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    private static void exibirMenuUsuario() {
        if (usuarioLogado instanceof UsuarioFree) {
            System.out.println("\n--- MENU FREE (" + usuarioLogado.getNome() + ") ---");
            System.out.println("1. Reproduzir música");
            System.out.println("2. Ver histórico");
            System.out.println("3. Criar playlist personalizada (máx. 3)");
            System.out.println("4. Gerar Playlist Automática");
            System.out.println("5. Reproduzir Playlist");
            System.out.println("6. 💎 Fazer upgrade para Premium");
            System.out.println("0. Logout");
        } else if (usuarioLogado instanceof UsuarioPremium) {
            System.out.println("\n--- MENU PREMIUM (" + usuarioLogado.getNome() + ") ---");
            System.out.println("1. Reproduzir música (Alta Qualidade)");
            System.out.println("2. Ver histórico");
            System.out.println("3. Criar playlist personalizada (ilimitado)");
            System.out.println("4. Gerar Playlist Automática");
            System.out.println("5. Reproduzir Playlist");
            System.out.println("6. Baixar música");
            System.out.println("7. Ver músicas baixadas");
            System.out.println("0. Logout");
        }
        System.out.print("Escolha: ");
    }

    // ================= PROCESSAMENTO =================

    private static void processarMenuPrincipal(int opcao) {
        switch (opcao) {
            case 1 -> criarNovoUsuario();
            case 2 -> fazerLogin();
            case 3 -> listarUsuarios();
            case 4 -> exibirEstatisticas();
            case 0 -> usuarioLogado = null; 
            default -> System.out.println("⚠️ Opção inválida.");
        }
    }

    private static void processarMenuUsuario(int opcao) {
        if (usuarioLogado instanceof UsuarioFree) {
            switch (opcao) {
                case 1 -> reproduzirDoBanco();
                case 2 -> usuarioLogado.exibirHistorico();
                case 3 -> criarNovaPlaylist();
                case 4 -> gerarPlaylistAutomatica();
                case 5 -> reproduzirPlaylist();
                case 6 -> fazerUpgrade();
                case 0 -> usuarioLogado = null; 
                default -> System.out.println("⚠️ Opção inválida.");
            }
        } else if (usuarioLogado instanceof UsuarioPremium) {
            // DOWNCASTING demonstrado aqui
            UsuarioPremium userPremium = (UsuarioPremium) usuarioLogado;
            switch (opcao) {
                case 1 -> reproduzirDoBanco();
                case 2 -> userPremium.exibirHistorico();
                case 3 -> criarNovaPlaylist();
                case 4 -> gerarPlaylistAutomatica();
                case 5 -> reproduzirPlaylist();
                case 6 -> baixarMusicaSistema(userPremium);
                case 7 -> userPremium.listarMusicasBaixadas();
                case 0 -> usuarioLogado = null; // Faz o logout
                default -> System.out.println("⚠️ Opção inválida.");
            }
        }
    }

    // ================= MÉTODOS DO SISTEMA PRINCIPAL =================

    private static void criarNovoUsuario() {
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
            
            usuarios.add(new UsuarioPremium(nome, email, plano));
            System.out.println("✅ Conta Premium criada com sucesso!");
        } else {
            usuarios.add(new UsuarioFree(nome, email));
            System.out.println("✅ Conta Free criada com sucesso!");
        }
    }

    private static void fazerLogin() {
        if (usuarios.isEmpty()) {
            System.out.println("⚠️ Nenhum usuário cadastrado.");
            return;
        }
        listarUsuarios();
        System.out.print("\nEscolha o ID do usuário para login: ");
        int id = lerOpcao() - 1;
        
        if (id >= 0 && id < usuarios.size()) {
            usuarioLogado = usuarios.get(id);
            String tipo = (usuarioLogado instanceof UsuarioPremium) ? "Premium" : "Free";
            System.out.println("✅ Login realizado: " + usuarioLogado.getNome() + " (" + tipo + ")");
        } else {
            System.out.println("⚠️ Usuário inválido.");
        }
    }

    private static void listarUsuarios() {
        System.out.println("\nUsuários cadastrados:");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            String tipo = (u instanceof UsuarioPremium) ? "Premium" : "Free";
            System.out.println((i + 1) + ". " + u.getNome() + " (" + tipo + ")");
        }
    }

    private static void exibirEstatisticas() {
        int countFree = 0;
        int countPremium = 0;

        for (Usuario u : usuarios) {
            if (u instanceof UsuarioFree) countFree++;
            if (u instanceof UsuarioPremium) countPremium++;
        }

        System.out.println("\n--- ESTATÍSTICAS DO SISTEMA ---");
        System.out.println("Total de usuários: " + usuarios.size());
        System.out.println("- Free: " + countFree + " usuários");
        System.out.println("- Premium: " + countPremium + " usuários\n");

        System.out.println("Reproduções totais: " + Usuario.totalReproducoesGerais);
        
        int rFree = UsuarioFree.reproducoesFree;
        int rPrem = UsuarioPremium.reproducoesPremium;
        int total = rFree + rPrem;
        
        if (total > 0) {
            System.out.println(String.format("- Free: %d reproduções (%d%%)", rFree, (rFree * 100 / total)));
            System.out.println(String.format("- Premium: %d reproduções (%d%%)", rPrem, (rPrem * 100 / total)));
        } else {
            System.out.println("- Nenhuma reprodução registrada.");
        }

        System.out.println("\nAnúncios exibidos: " + UsuarioFree.anunciosExibidos);
    }

    // ================= MÉTODOS DO USUÁRIO =================

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

    private static void gerarPlaylistAutomatica() {
        System.out.println("\n--- PLAYLISTS AUTOMÁTICAS ---");
        System.out.println("1. Top 10 Mais Tocadas");
        System.out.println("2. Recomendadas para Você");
        System.out.println("3. Adicionadas Recentemente");
        System.out.print("Escolha: ");
        int op = lerOpcao();
        
        String nome = op == 1 ? "Top 10 Mais Tocadas" : op == 2 ? "Recomendadas para Você" : "Adicionadas Recentemente";
        System.out.println("🤖 Gerando playlist \"" + nome + "\"...");
        
        PlaylistAutomatica auto = new PlaylistAutomatica(nome, nome);
        for(Musica m : bancoDeMusicas) {
            auto.adicionarMusica(m);
        }
        
        usuarioLogado.getPlaylists().add(auto);
        System.out.println("✅ Playlist gerada com " + auto.getMusicas().size() + " músicas!");
    }

    private static void reproduzirPlaylist() {
        ArrayList<Playlist> playlists = usuarioLogado.getPlaylists();
        if (playlists.isEmpty()) {
            System.out.println("⚠️ Você não possui playlists.");
            return;
        }

        System.out.println("\n--- SUAS PLAYLISTS ---");
        for (int i = 0; i < playlists.size(); i++) {
            Playlist p = playlists.get(i);
            String icone = (p instanceof PlaylistAutomatica) ? "🤖 " : "🎧 ";
            System.out.println((i + 1) + ". " + icone + p.getNome() + " (" + p.getMusicas().size() + " músicas)");
        }
        
        System.out.print("ID da playlist para reproduzir: ");
        int id = lerOpcao() - 1;
        
        if (id >= 0 && id < playlists.size()) {
            playlists.get(id).reproduzir();
        } else {
            System.out.println("⚠️ Playlist não encontrada.");
        }
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
        
        // Substitui na lista de usuários
        int index = usuarios.indexOf(usuarioLogado);
        if (index != -1) {
            usuarios.set(index, novoUsuario);
        }
        
        usuarioLogado = novoUsuario;
        System.out.println("✅ Upgrade concluído! Bem-vindo ao Premium.");
    }

    // ================= UTILITÁRIOS =================

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