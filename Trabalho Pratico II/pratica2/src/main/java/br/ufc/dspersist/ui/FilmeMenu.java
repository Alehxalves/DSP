package br.ufc.dspersist.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufc.dspersist.controllers.AtorController;
import br.ufc.dspersist.controllers.FilmeController;
import br.ufc.dspersist.entity.Ator;
import br.ufc.dspersist.entity.Filme;

/**
 * FilmeMenu
 */
public class FilmeMenu {
    public static FilmeController filmeController = new FilmeController();
    public static AtorController atorController = new AtorController();

    public static void obterFilme(Filme filme) {
        String titulo = JOptionPane.showInputDialog("Título: ", filme.getTitulo());
        int anoLancamento = Integer
                .parseInt(JOptionPane.showInputDialog("Ano de Lançamento: ", filme.getAnoLancamento()));

        filme.setTitulo(titulo);
        filme.setAnoLancamento(anoLancamento);
    }

    public static void addAtorAoFilme(Filme filme) throws Exception {
        String titulo = "Adicionar Elenco em " + filme.getTitulo();

        int atorId = Integer
                .parseInt(JOptionPane.showInputDialog(null, "ID Ator: ",
                        titulo, 3));

        Ator ator = atorController.findAtorByIdWithFilmes(atorId);
        filme.addAtor(ator);

        int input = JOptionPane.showConfirmDialog(null,
                "Adicionar outro ator?", titulo,
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (input == 0)
            addAtorAoFilme(filme);
        else {
            filmeController.saveFilme(filme);
        }
    }

    public static void exibirFilme(Filme filme) {
        StringBuilder exibir = new StringBuilder();

        exibir.append(filme.getTitulo() + " - " + filme.getAnoLancamento() + "\n");
        exibir.append("Elenco:\n");

        for (Ator ator : filme.getAtores()) {
            exibir.append(ator.getNome()).append("\n");
        }
        JOptionPane.showMessageDialog(null, exibir, "Descrição do Filme", 3);
    }

    public static void listarFilmes(List<Filme> filmes, boolean showQuantidade) throws Exception {
        StringBuilder exibir = new StringBuilder();

        if (showQuantidade)
            exibir.append("Total de Filmes Cadastrados: " + filmes.size() + "\n");
        for (Filme filme : filmes) {
            exibir.append(filme.getId() + " - " + filme.getTitulo() + " - ( "
                    + filme.getAnoLancamento()).append(" )\n");
        }
        JOptionPane.showMessageDialog(null, exibir, "Filmes", 3);
    }

    public static void listFilmesByAtor(int atorId) throws Exception {
        Ator ator = atorController.findAtorByIdWithFilmes(atorId);
        List<Filme> filmes = filmeController.listFilmesByAtor(atorId);

        StringBuilder exibir = new StringBuilder();

        exibir.append(ator.getNome() + " - " + ator.getDataNascimento() + "\n");
        exibir.append("Filmografia:\n");

        for (Filme filme : filmes) {
            exibir.append(filme.getTitulo()).append("\n");
        }
        JOptionPane.showMessageDialog(null, exibir, "Filmes de " + ator.getNome(),
                3);
    }

    public static void menuFilme() {
        String menu = "Escolha uma opção:\n1 - Novo Filme\n2 - Adicionar Atores ao Filme\n3 - Exibir filme"
                + "\n4 - Listar Por Ator" + "\n5 - Listar Por Ano de Lançamento" + "\n6 - Listar Por Título"
                + "\n7 - Listar Todos" + "\n8 - Atualizar" + "\n9 - Remover Filme" + "\n0 - Voltar";

        char opcao;
        do {
            opcao = JOptionPane.showInputDialog(null, menu,
                    "Menu Filme", 3).charAt(0);

            int filmeId;
            int atorId;
            List<Filme> filmes = new ArrayList<Filme>();
            Filme filme;
            try {
                switch (opcao) {
                    case '1': // Novo filme
                        filme = new Filme();
                        obterFilme(filme);
                        filmeController.saveFilme(filme);
                        JOptionPane.showMessageDialog(null, "Filme salvo.");
                        break;
                    case '2': // Adicionar Atores ao filme.
                        filmeId = Integer
                                .parseInt(JOptionPane.showInputDialog(null,
                                        "ID Filme: ",
                                        "Adicionar Atores", 3));
                        filme = filmeController.findFilmeByIdWithAtores(filmeId);
                        addAtorAoFilme(filme);
                        break;
                    case '3': // Exibir Filme
                        filmeId = Integer
                                .parseInt(JOptionPane.showInputDialog(null,
                                        "ID Filme: ",
                                        "Exibir Filme", 3));
                        filme = filmeController.findFilmeByIdWithAtores(filmeId);
                        exibirFilme(filme);
                        break;
                    case '4': // Listar filmes por ator
                        atorId = Integer
                                .parseInt(JOptionPane.showInputDialog(null,
                                        "ID Ator: ",
                                        "Listar Filmes Por Ator", 3));
                        listFilmesByAtor(atorId);
                        break;
                    case '5': // Listar filmes por ano de lançamento
                        int anoLancamento = Integer
                                .parseInt(JOptionPane.showInputDialog(null,
                                        "Ano de lançamento: ",
                                        "Listar Por Ano de Lançamento", 3));
                        filmes = filmeController.listFilmesByAnoLancamento(anoLancamento);
                        listarFilmes(filmes, false);
                        break;
                    case '6': // Listar filmes por titulo
                        String titulo = JOptionPane.showInputDialog(null,
                                "Titulo do Filme: ",
                                "Listar Por Título", 3);

                        filmes = filmeController.listFilmesByTitulo(titulo);
                        listarFilmes(filmes, false);
                        break;
                    case '7': // Listar todos
                        filmes = filmeController.listAllFilmes();
                        listarFilmes(filmes, true);
                        break;
                    case '8': // Atualizar filme
                        filmeId = Integer
                                .parseInt(JOptionPane.showInputDialog(null,
                                        "ID Filme: ",
                                        "Atualizar Filme", 3));
                        filme = filmeController.findFilmeByIdWithAtores(filmeId);
                        obterFilme(filme);
                        filmeController.saveFilme(filme);
                        JOptionPane.showMessageDialog(null, "Filme atualizado.");
                        break;
                    case '9': // Remover filme
                        filmeId = Integer
                                .parseInt(JOptionPane.showInputDialog(null,
                                        "ID Filme: ",
                                        "Remover Filme", JOptionPane.WARNING_MESSAGE));
                        filme = filmeController.findFilmeByIdWithAtores(filmeId);

                        int input = JOptionPane.showConfirmDialog(null,
                                "Deseja realmente deletar este filme? ",
                                "Deletar Filme",
                                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                        if (input == 0)
                            filmeController.removerFilme(filme);
                        break;
                    case '0': // Exit
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        } while (opcao != '0');
    }
}