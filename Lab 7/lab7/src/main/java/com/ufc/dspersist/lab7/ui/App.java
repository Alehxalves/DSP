package com.ufc.dspersist.lab7.ui;

import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import com.ufc.dspersist.lab7.dao.AlunoDAO;
import com.ufc.dspersist.lab7.entity.Aluno;

@SpringBootApplication
@ComponentScan("com.ufc.dspersist.lab7")
public class App implements CommandLineRunner {
    @Autowired
    private AlunoDAO baseAlunos;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(App.class);
        builder.headless(false).run(args);

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
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum Aluno encontrado" : listagem);
    }

    public static void listAluno(Aluno aluno) {
        JOptionPane.showMessageDialog(null, aluno == null ? "Aluno não encontrado!" : aluno);
    }

    @Override
    public void run(String... args) throws Exception {
        String menu = "Escolha uma opção:\n1 - Inserir\n2 - Atualizar por Matrícula\n3 - Remover por Matrícula\n"
                + "4 - Exibir por Matrícula\n5 - Exibir por id\n6 - Exibir todos\n"
                + "7 - Exibir todos que contem determinado nome\n8 - Sair";

        char opcao;
        do {
            Aluno aluno;
            int matricula;
            opcao = JOptionPane.showInputDialog(menu).charAt(0);
            switch (opcao) {
                case '1': // Inserir
                    aluno = new Aluno();
                    obterAluno(aluno);
                    baseAlunos.save(aluno);
                    break;
                case '2': // Atualizar por matricula
                    matricula = Integer
                            .parseInt(JOptionPane.showInputDialog("Digite a matrícula do aluno a ser alterado"));
                    aluno = baseAlunos.findByMatricula(matricula);
                    if (!Objects.isNull(aluno)) {
                        obterAluno(aluno);
                        baseAlunos.save(aluno);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Não foi possível atualizar, pois o aluno não foi encontrado.");
                    }
                    break;
                case '3': // Remover por matricula
                    matricula = Integer.parseInt(JOptionPane.showInputDialog("Matrícula"));
                    aluno = baseAlunos.findByMatricula(matricula);
                    if (matricula != 0) {
                        baseAlunos.delete(aluno.getId());
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Não foi possível remover, pois o aluno não foi encontrado.");
                    }
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

        } while (opcao != '8');
    }
}
