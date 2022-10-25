package com.ufc.dspesist.lab9.ui;

import java.util.List;

import javax.swing.JOptionPane;

import com.ufc.dspesist.lab9.controllers.TurmaController;
import com.ufc.dspesist.lab9.controllers.AlunoTurmaController;
import com.ufc.dspesist.lab9.entity.Turma;

public class TurmaMenu {
    public static TurmaController turmaController = new TurmaController();
    public static AlunoTurmaController alunoTurmaController = new AlunoTurmaController();

    public static void obterTurma(Turma turma) {
        int codTurma = Integer.parseInt(JOptionPane.showInputDialog("Cod Turma", turma.getCodTurma()));
        String disciplina = JOptionPane.showInputDialog("Disciplina", turma.getDisciplina());
        String periodo = JOptionPane.showInputDialog("Periodo", turma.getPeriodo());

        turma.setCodTurma(codTurma);
        turma.setDisciplina(disciplina);
        turma.setPeriodo(periodo);
    }

    public static void listarTurmas(List<Turma> turmas) {
        StringBuilder listagem = new StringBuilder();
        for (Turma turma : turmas) {
            listagem.append(turma).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhuma Turma encontrada." : listagem);
    }

    public static void mostrarTurma(Turma turma) {
        JOptionPane.showMessageDialog(null, turma == null ? "Turma não encontrada." : turma);
    }

    public static void crudTurma() {
        String menuAluno = "Escolha uma opção:\n1 - Nova Turma\n2 - Alunos da turma\n3 - Atualizar Turma\n"
                + "4 - Remover Turma\n"
                + "5 - Mostrar Turma\n6 - Mostrar Todas as Turmas\n"
                + "7 - Voltar";
        char opcao;
        do {
            Turma turma;
            int turmaId;
            opcao = JOptionPane.showInputDialog(menuAluno).charAt(0);
            try {
                switch (opcao) {
                    case '1': // Inserir Aluno
                        turma = new Turma();
                        obterTurma(turma);
                        turmaController.saveTurma(turma);
                        break;
                    case '2':
                        turmaId = Integer
                                .parseInt(JOptionPane.showInputDialog("Digite o ID da turma:"));
                        turma = new Turma();
                        turma = turmaController.findById(turmaId);

                        listarString(alunoTurmaController.alunosDaTurma(turma));
                        break;
                    case '3': // Atualizar por id
                        turmaId = Integer
                                .parseInt(JOptionPane.showInputDialog("Digite o ID da turma a ser alterada:"));
                        turma = turmaController.findById(turmaId);
                        obterTurma(turma);
                        turmaController.saveTurma(turma);
                        break;
                    case '4': // Remover por id
                        turmaId = Integer
                                .parseInt(JOptionPane.showInputDialog("Digite o ID da turma a ser Deletada:"));
                        turmaController.deleteTurmaById(turmaId);
                        break;
                    case '5': // Exibir por id
                        turmaId = Integer
                                .parseInt(JOptionPane.showInputDialog("Digite o ID:"));
                        turma = turmaController.findById(turmaId);
                        mostrarTurma(turma);
                        break;
                    case '6': // Exibir todas as turmas
                        listarTurmas(turmaController.findAll());
                        break;
                    case '7': // Voltar
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida.");
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (opcao != '7');
    }

    public static void listarString(List<String> list) {
        StringBuilder listagem = new StringBuilder();
        listagem.append("{ \n");
        for (String str : list) {
            listagem.append(str).append("\n");
        }
        listagem.append(" }");
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nada encontrado." : listagem);
    }

}
