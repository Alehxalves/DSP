package com.ufc.dspesist.lab8;

import java.util.List;

import javax.swing.JOptionPane;

import com.ufc.dspesist.lab8.dao.AlunoDAO;
import com.ufc.dspesist.lab8.dao.AlunoJPADAO;
import com.ufc.dspesist.lab8.entity.Aluno;

public class App {
    public static AlunoDAO baseAlunos = new AlunoJPADAO();

    public static void main(String[] args) throws Exception {
        run();
    }

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

    public static void listAlunos(List<Aluno> alunos) {
        StringBuilder listagem = new StringBuilder();
        for (Aluno aluno : alunos) {
            listagem.append(aluno).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum Aluno(a) encontrado" : listagem);
    }

    public static void listAluno(Aluno aluno) {
        JOptionPane.showMessageDialog(null, aluno == null ? "Aluno(a) não encontrado!" : aluno);
    }

    public static void run() throws Exception {
        String menu = "Escolha uma opção:\n1 - Inserir\n2 - Atualizar por Matrícula\n3 - Remover por Matrícula\n"
                + "4 - Exibir por Matrícula\n5 - Exibir por id\n6 - Exibir todos\n"
                + "7 - Exibir todos que contem determinado nome\n8 - Sair";

        char opcao;
        do {
            Aluno aluno;
            int matricula;
            opcao = JOptionPane.showInputDialog(menu).charAt(0);
            try {

                switch (opcao) {
                    case '1': // Inserir
                        aluno = new Aluno();
                        obterAluno(aluno);
                        baseAlunos.save(aluno);
                        break;
                    case '2': // Atualizar por matricula
                        matricula = Integer
                                .parseInt(JOptionPane.showInputDialog("Digite a matrícula do aluno(a) a ser alterado"));
                        aluno = baseAlunos.findByMatricula(matricula);
                        obterAluno(aluno);
                        baseAlunos.save(aluno);
                        break;
                    case '3': // Remover por matricula
                        matricula = Integer.parseInt(JOptionPane.showInputDialog("Matrícula"));
                        aluno = baseAlunos.findByMatricula(matricula);
                        baseAlunos.delete(aluno.getId());
                        break;
                    case '4': // Exibir por matrícula
                        matricula = Integer.parseInt(JOptionPane.showInputDialog("Matrícula"));
                        aluno = baseAlunos.findByMatricula(matricula);
                        listAluno(aluno);
                        break;
                    case '5': // Exibir por id
                        int id = Integer.parseInt(JOptionPane.showInputDialog("Id"));
                        aluno = baseAlunos.find(id);
                        listAluno(aluno);
                        break;
                    case '6': // Exibir todos
                        listAlunos(baseAlunos.find());
                        break;
                    case '7': // Exibir todos que contem determinado nome
                        String nome = JOptionPane.showInputDialog("Nome");
                        listAlunos(baseAlunos.findByNome(nome));
                        break;
                    case '8': // Sair
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida");
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        } while (opcao != '8');
    }
}
