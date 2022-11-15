package br.ufc.dspersist.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufc.dspersist.controllers.AtorController;
import br.ufc.dspersist.controllers.FilmeController;
import br.ufc.dspersist.entity.Ator;
import br.ufc.dspersist.entity.Filme;

public class AtorMenu {
    public static FilmeController filmeController = new FilmeController();
    public static AtorController atorController = new AtorController();

    public static void obterAtor(Ator ator) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");

        String nome = JOptionPane.showInputDialog("Nome: ", ator.getNome());
        String dataNascimento;

        if (ator.getId() == null) {
            dataNascimento = JOptionPane.showInputDialog(null,
                    "Data de Nascimento (yyyy/MM/dd): ", ator.getDataNascimento());

        } else {
            dataNascimento = JOptionPane.showInputDialog(null,
                    "Data de Nascimento (yyyy/MM/dd): ",
                    ator.getDataNascimento().format(formatter));
        }

        LocalDate date = LocalDate.parse(dataNascimento, formatter);
        ator.setNome(nome);
        ator.setDataNascimento(date);
    }

    public static void exibirAtor(Ator ator) {
        StringBuilder exibir = new StringBuilder();

        exibir.append(ator.getNome() + " - ( " + ator.getDataNascimento() + " )\n");
        exibir.append("Filmografia:\n");

        for (Filme filme : ator.getFilmes()) {
            exibir.append(filme.getTitulo() + " - " + filme.getAnoLancamento()).append("\n");
        }
        JOptionPane.showMessageDialog(null, exibir,
                "Perfil de " + ator.getNome(),
                3);
    }

    public static void listarAtores(List<Ator> atores, boolean showQuantidade) throws Exception {
        StringBuilder exibir = new StringBuilder();

        if (showQuantidade)
            exibir.append("Total de atores cadastrados: " + atores.size() + "\n");
        for (Ator ator : atores) {
            exibir.append(ator.getId() + " - " + ator.getNome() + " - ( "
                    + ator.getDataNascimento()).append(" )\n");
        }
        JOptionPane.showMessageDialog(null, exibir, "Atores", 3);
    }

    public static void listarAtoresByFilme(int filmeId) throws Exception {
        Filme filme = filmeController.findFilmeByIdWithAtores(filmeId);
        List<Ator> atores = atorController.listAtoresByFilme(filmeId);

        StringBuilder exibir = new StringBuilder();

        exibir.append(filme.getTitulo() + " - ( " + filme.getAnoLancamento() + " )\n");
        exibir.append("Elenco:\n");

        for (Ator ator : atores) {
            exibir.append(ator.getNome()).append("\n");
        }
        JOptionPane.showMessageDialog(null, exibir,
                "Elenco de " + filme.getTitulo(),
                3);
    }

    public static void menuAtor() {
        String menu = "Escolha uma opção:\n1 - Novo Ator\n2 - Exibir Ator\n3 - Listar Por Filme"
                + "\n4 - Listar Por Ano de Nascimento" + "\n5 - Listar Todos" + "\n6 - Atualizar Ator"
                + "\n7 - Remover Ator" + "\n8 - Voltar";

        char opcao;
        do {
            opcao = JOptionPane.showInputDialog(null, menu, "Menu Ator", 3).charAt(0);

            int filmeId;
            int atorId;
            List<Ator> atores = new ArrayList<Ator>();
            Ator ator;
            try {
                switch (opcao) {
                    case '1': // Novo Ator
                        ator = new Ator();
                        obterAtor(ator);
                        atorController.saveAtor(ator);
                        JOptionPane.showMessageDialog(null, "Ator salvo.");
                        break;
                    case '2': // Exibir Ator
                        atorId = Integer
                                .parseInt(JOptionPane.showInputDialog(null,
                                        "ID Ator: ",
                                        "Exibir Ator", 3));
                        ator = atorController.findAtorByIdWithFilmes(atorId);
                        exibirAtor(ator);
                        break;
                    case '3': // Listar ator por filme
                        filmeId = Integer
                                .parseInt(JOptionPane.showInputDialog(null,
                                        "ID Filme: ",
                                        "Listar Elenco do Filme", 3));
                        listarAtoresByFilme(filmeId);
                        break;
                    case '4': // Listar por ano de nascimento.
                        int anoNascimento = Integer
                                .parseInt(JOptionPane.showInputDialog(null,
                                        "Ano de Nascimento: ",
                                        "Listar Por Ano de Nascimento", 3));
                        atores = atorController.listAtoresByAnoNascimento(anoNascimento);
                        listarAtores(atores, false);
                        break;
                    case '5': // Listar todos atores
                        atores = atorController.listAllAtores();
                        listarAtores(atores, true);
                        break;
                    case '6': // Atualizar ator
                        atorId = Integer
                                .parseInt(JOptionPane.showInputDialog(null,
                                        "ID ator: ",
                                        "Atualizar Ator", 3));
                        ator = atorController.findAtorByIdWithFilmes(atorId);
                        obterAtor(ator);
                        atorController.saveAtor(ator);
                        JOptionPane.showMessageDialog(null, "Ator atualizado.");
                        break;
                    case '7': // Remover ator
                        atorId = Integer
                                .parseInt(JOptionPane.showInputDialog(null,
                                        "ID Ator: ",
                                        "Remover Ator", JOptionPane.WARNING_MESSAGE));
                        ator = atorController.findAtorByIdWithFilmes(atorId);

                        int input = JOptionPane.showConfirmDialog(null,
                                "Deseja realmente deletar este ator? ",
                                "Deletar Ator",
                                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                        if (input == 0)
                            atorController.removerAtor(ator);
                        break;
                    case '8': // Exit
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        } while (opcao != '8');
    }
}
