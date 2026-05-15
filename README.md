# 🎵 Sistema de Streaming de Música

Sistema de gerenciamento de streaming de música desenvolvido em Java, aplicando os quatro pilares da Programação Orientada a Objetos.

---

## 📋 Funcionalidades

- Cadastro e gerenciamento de músicas
- Sistema de playlists (personalizadas e automáticas)
- Múltiplos tipos de usuários (Free e Premium)
- Sistema de reprodução completo
- Buscas e filtros por gênero
- Estatísticas e relatórios do sistema
- Sistema de downloads (Premium)
- Histórico de reprodução


### Conceitos de POO Aplicados

| Pilar | Aplicação |
|-------|-----------|
| **Encapsulamento** | Atributos privados com getters/setters e validações em todas as classes |
| **Herança** | `UsuarioFree` e `UsuarioPremium` herdam de `Usuario`; `PlaylistAutomatica` e `PlaylistPersonalizada` herdam de `Playlist` |
| **Polimorfismo** | Método `reproduzirMusica()` e `reproduzir()` sobrescritos com comportamentos distintos; uso de `instanceof` para tratamento dinâmico |
| **Abstração** | Interfaces `Reproduzivel` e `Baixavel` definem contratos de comportamento implementados pelas classes correspondentes |

### Interfaces

**`Reproduzivel`** — implementada por `Musica` e `Playlist`:
- `reproduzir()`, `pausar()`, `parar()`, `getDuracaoTotal()`

**`Baixavel`** — implementada por `UsuarioPremium`:
- `baixar(Musica)`, `removerDownload(Musica)`, `estaBaixado(Musica)`, `getTamanhoBaixados()`

---

## 👤 Autor

- **Nome:** Fernanda Akemi Martins Sanpei
- **RA:** 43931251

---