package com.ufc.dspesist.lab9.ui;

import java.util.List;

import javax.swing.JOptionPane;

import com.ufc.dspesist.lab9.controllers.AlunoController;
import com.ufc.dspesist.lab9.controllers.AlunoTurmaController;
import com.ufc.dspesist.lab9.entity.Aluno;

public class AlunoMenu {
    public static AlunoController alunoController = new AlunoController();
    public static AlunoTurmaController alunoTurmaController = new AlunoTurmaController();

    public static void obterAluno(Aluno aluno) {
        String nome = JOptionPane.showInputDialog("Nome", aluno.getNome());
        String cpf = JOptionPane.showInputDialog("CPF", aluno.getCpf());
        int matricula = Integer.parseInt(JOptionPane.showInputDialog("Matricula", aluno.getMatricula()));
        String email = JOptionPane.showInputDialog("Email", aluno.getEmail());
        String fone = JOptionPane.showInputDialog("Telefone", aluno.getTelefone());

        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setMatricula(matricula);
        aluno.setEmail(email);
        aluno.setTelefone(fone);
    }

    public static void listarAlunos(List<Aluno> alunos) {
        StringBuilder listagem = new StringBuilder();
        for (Aluno aluno : alunos) {
            listagem.append(aluno).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum Aluno(a) encontrado." : listagem);
    }

    public static void mostrarAluno(Aluno aluno) {
        JOptionPane.showMessageDialog(null, aluno);
    }

    public static void crudAluno() {
        String menuAluno = "Escolha uma opção:\n1 - Novo Aluno\n2 - Turmas do Aluno\n3 - Atualizar Aluno\n"
                + "4 - Remover Aluno\n"
                + "5 - Mostrar Aluno\n6 - Mostrar Todos os Alunos\n"
                + "7 - Filtrar Alunos por Nome\n8 - Voltar";
        char opcao;
        do {
            Aluno aluno;
            int matricula;
            opcao = JOptionPane.showInputDialog(menuAluno).charAt(0);
            try {
                switch (opcao) {
                    case '1': // Inserir Aluno
                        aluno = new Aluno();
                        obterAluno(aluno);
                        alunoController.saveAluno(aluno);
                        break;
                    case '2': // Turmas do Aluno
                        matricula = Integer
                                .parseInt(JOptionPane.showInputDialog("Digite a matrícula do aluno(a):"));
                        aluno = new Aluno();
                        aluno = alunoController.findByMatricula(matricula);

                        listarString(alunoTurmaController.turmasDoAluno(aluno));
                        break;
                    case '3': // Atualizar por matricula
                        matricula = Integer
                                .parseInt(
                                        JOptionPane.showInputDialog("Digite a matrícula do aluno(a) a ser alterado:"));
                        aluno = alunoController.findByMatricula(matricula);
                        obterAluno(aluno);
                        alunoController.updateAluno(aluno);
                        break;
                    case '4': // Remover por matricula
                        matricula = Integer
                                .parseInt(
                                        JOptionPane.showInputDialog("Digite a matrícula do aluno(a) a ser deletado:"));
                        alunoController.deleteAlunoByMatricula(matricula);
                        break;
                    case '5': // Exibir por matrícula
                        matricula = Integer.parseInt(JOptionPane.showInputDialog("Digite a Matrícula:"));
                        aluno = alunoController.findByMatricula(matricula);
                        mostrarAluno(aluno);
                        break;
                    case '6': // Exibir todos
                        listarAlunos(alunoController.findAll());
                        break;
                    case '7': // Exibir alunos que contem determinado nome
                        String nome = JOptionPane.showInputDialog("Digite o Nome:");
                        listarAlunos(alunoController.findByNome(nome));
                        break;
                    case '8': // Voltar
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida.");
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (opcao != '8');
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
