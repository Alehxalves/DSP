package br.ufc.dspersist;

import java.util.List;
import java.util.Scanner;

public class App {

    private static StudentDAO studentDAO = new StudentDAO();

    public static void listStudents() throws Exception {
        List<Student> students = studentDAO.getAllStudents();

        for (Student st : students) {
            System.out.println(st.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        if (!studentDAO.existsTable("students")) {
            // Caso não exista a tabela de students no banco relacional este metodo cria a
            // tabela
            studentDAO.createStudentTable();
        }

        while (true) {
            try {
                System.out.println("===================");
                System.out.println("(1) Inserir alunos\n(2) Listar alunos\n(3) Sair");
                System.out.println("===================");

                System.out.print(">: ");

                int opcao = Integer.parseInt(scanner.nextLine());
                CLS.clearConsole();

                if (opcao == 1) {
                    Student student = new Student();
                    System.out.print("Nome do aluno: ");
                    student.setNome(scanner.nextLine());

                    System.out.print("CPF do aluno: ");
                    student.setCpf(scanner.nextLine());

                    System.out.print("Matricula do aluno: ");
                    student.setMatricula(Integer.parseInt(scanner.nextLine()));

                    System.out.print("Email do aluno: ");
                    student.setEmail(scanner.nextLine());

                    System.out.print("Telefone do aluno: ");
                    student.setTelefone(scanner.nextLine());

                    studentDAO.insertStudent(student);
                    System.out.println("Aluno cadastrado!");
                } else if (opcao == 2) {
                    listStudents();
                } else if (opcao == 3) {
                    break;
                } else {
                    System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}
