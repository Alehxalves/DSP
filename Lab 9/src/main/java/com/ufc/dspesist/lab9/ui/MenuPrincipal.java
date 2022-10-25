package com.ufc.dspesist.lab9.ui;

import javax.swing.JOptionPane;

import com.ufc.dspesist.lab9.controllers.AlunoController;
import com.ufc.dspesist.lab9.controllers.AlunoTurmaController;
import com.ufc.dspesist.lab9.controllers.TurmaController;
import com.ufc.dspesist.lab9.entity.Aluno;
import com.ufc.dspesist.lab9.entity.AlunoTurma;
import com.ufc.dspesist.lab9.entity.Turma;

public class MenuPrincipal {
    public static AlunoController alunoController = new AlunoController();
    public static TurmaController turmaController = new TurmaController();
    public static AlunoTurmaController alunoTurmaController = new AlunoTurmaController();

    public static void main(String[] args) throws Exception {
        run();
    }

    public static void obterMatricula(AlunoTurma alunoTurma) throws Exception {

        float notaFinal = Float.parseFloat(JOptionPane.showInputDialog("Nota Final: ",
                alunoTurma.getNotaFinal()));
        int qtdFaltas = Integer
                .parseInt(JOptionPane.showInputDialog("Quantidade de Faltas",
                        alunoTurma.getQtdFaltas()));

        alunoTurma.setNotaFinal(notaFinal);
        alunoTurma.setQtdFaltas(qtdFaltas);
    }

    public static void run() {
        String menu = "Escolha uma opção:\n1 - Menu Aluno\n2 - Menu Turma\n3 - Matricular Aluno"
                + "\n4 - Atualizar Matrícula" + "\n5 - Cancelar Matrícula" + "\n6 - Sair";

        char opcao;
        do {
            opcao = JOptionPane.showInputDialog(menu).charAt(0);
            int matricula;
            int turmaId;
            AlunoTurma alunoTurma;
            Aluno aluno;
            Turma turma;
            try {
                switch (opcao) {
                    case '1':
                        AlunoMenu.crudAluno();
                        break;
                    case '2':
                        TurmaMenu.crudTurma();
                        break;
                    case '3':
                        alunoTurma = new AlunoTurma();

                        matricula = Integer
                                .parseInt(JOptionPane.showInputDialog("Matrícula do aluno(a):"));
                        aluno = alunoController.findByMatricula(matricula);

                        turmaId = Integer
                                .parseInt(JOptionPane.showInputDialog("Digite o ID da turma:"));
                        turma = turmaController.findById(turmaId);

                        alunoTurma.setAluno(aluno);
                        alunoTurma.setTurma(turma);

                        obterMatricula(alunoTurma);
                        alunoTurmaController.matricularAluno(alunoTurma);
                        break;
                    case '4':
                        alunoTurma = new AlunoTurma();

                        matricula = Integer
                                .parseInt(JOptionPane.showInputDialog("Matrícula do aluno(a):"));
                        aluno = alunoController.findByMatricula(matricula);

                        turmaId = Integer
                                .parseInt(JOptionPane.showInputDialog("Digite o ID da turma:"));
                        turma = turmaController.findById(turmaId);

                        alunoTurma = alunoTurmaController.findByAlunoTurma(aluno, turma);

                        obterMatricula(alunoTurma);
                        alunoTurmaController.atualizarMatricula(alunoTurma);
                        break;
                    case '5':
                        matricula = Integer
                                .parseInt(JOptionPane.showInputDialog("Digite a matrícula do aluno(a):"));
                        aluno = alunoController.findByMatricula(matricula);

                        turmaId = Integer
                                .parseInt(JOptionPane.showInputDialog("Digite o ID da turma:"));
                        turma = turmaController.findById(turmaId);

                        alunoTurmaController.cancelarMatricula(aluno, turma);
                        break;
                    case '6':
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        } while (opcao != '6');
    }

}
