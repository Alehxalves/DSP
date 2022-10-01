package br.ufc.dspersist;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public boolean existsTable(String tableName) throws SQLException {
        Connection connection = PostgreSQLConnection.getConnection();
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getTables(null, null, tableName, new String[] { "TABLE" });

            boolean result = resultSet.next();
            resultSet.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void createStudentTable() throws SQLException {

        if (!existsTable("students")) {
            Connection connection = PostgreSQLConnection.getConnection();
            try {

                String createSql = "create table students(id serial primary key, nome varchar(50), cpf varchar(14), "
                        + "matricula int, email varchar(50), telefone varchar(16))";

                PreparedStatement ps = connection.prepareStatement(createSql);

                ps.executeUpdate();
                ps.close();
                connection.commit();

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public List<Student> getAllStudents() throws SQLException {
        Connection connection = PostgreSQLConnection.getConnection();
        try {
            String selectStudents = "select * from students";
            PreparedStatement ps = connection.prepareStatement(selectStudents);

            ResultSet rs = ps.executeQuery();
            List<Student> students = new ArrayList<>();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setNome(rs.getString("nome"));
                student.setCpf(rs.getString("cpf"));
                student.setMatricula(rs.getInt("matricula"));
                student.setEmail(rs.getString("email"));
                student.setTelefone(rs.getString("telefone"));

                students.add(student);
            }
            ps.close();
            rs.close();
            return students;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void insertStudent(Student student) throws SQLException {
        Connection connection = PostgreSQLConnection.getConnection();
        try {
            String insertStudent = "insert into students (nome, cpf, matricula, email, telefone) "
                    + "values (?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(insertStudent);

            ps.setString(1, student.getNome());
            ps.setString(2, student.getCpf());
            ps.setInt(3, student.getMatricula());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getTelefone());

            ps.executeUpdate();

            ps.close();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            connection.close();
        }
    }
}
