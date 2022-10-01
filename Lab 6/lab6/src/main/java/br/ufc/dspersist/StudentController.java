package br.ufc.dspersist;

import java.sql.SQLException;
import java.util.List;

public class StudentController {
    StudentDAO studentDAO;

    public StudentController() throws SQLException {
        studentDAO = new StudentDAO();

        if (!studentDAO.existsTable("students")) {
            // Caso não exista a tabela de students no banco relacional este metodo cria a
            // tabela
            studentDAO.createStudentTable();
        }
    }

    public void insertStudent(Student student) throws SQLException {
        studentDAO.insertStudent(student);
        System.out.println("Aluno cadastrado!");
    }

    public List<Student> listAllStudents() throws SQLException {
        List<Student> students = studentDAO.getAllStudents();
        return students;
    }
}
