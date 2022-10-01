package br.ufc.dspersist;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        StudentController studentController = new StudentController();

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

                    studentController.insertStudent(student);
                } else if (opcao == 2) {
                    List<Student> students = studentController.listAllStudents();
                    for (Student student : students)
                        System.out.println(student.toString());
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
