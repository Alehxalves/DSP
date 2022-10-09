package com.ufc.dspersist.lab7.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ufc.dspersist.lab7.entity.Aluno;

@Repository
@Primary
public class AlunoJDBCDAO implements AlunoDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public AlunoJDBCDAO() {
    }

    @Override
    public void save(Aluno aluno) {
        String insertSql = "insert into alunos (nome, cpf, matricula, email, telefone)"
                + "values (:nome, :cpf, :matricula, :email, :telefone)";
        String updateSql = "update alunos set nome = :nome, cpf = :cpf, matricula = :matricula, telefone = :telefone where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("nome", aluno.getNome())
                .addValue("cpf", aluno.getCpf())
                .addValue("matricula", aluno.getMatricula())
                .addValue("email", aluno.getEmail())
                .addValue("telefone", aluno.getTelefone());

        if (aluno.getId() == null) {
            jdbcTemplate.update(insertSql, params);
        } else {
            params.addValue("id", aluno.getId());
            jdbcTemplate.update(updateSql, params);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from alunos where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    private Aluno map(ResultSet rs) throws SQLException {
        Aluno aluno = new Aluno();
        aluno.setId(rs.getInt("id"));
        aluno.setNome(rs.getString("nome"));
        aluno.setCpf(rs.getString("cpf"));
        aluno.setMatricula(rs.getInt("matricula"));
        aluno.setEmail(rs.getString("email"));
        aluno.setTelefone(rs.getString("telefone"));

        return aluno;
    }

    @Override
    public Aluno find(int id) {
        String sql = "select id, nome, cpf, matricula, email, telefone from alunos where id = :id_";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id_", id);
        return jdbcTemplate.queryForObject(sql, namedParameters, (rs, rowNum) -> map(rs));
    }

    @Override
    public List<Aluno> find() {
        String sql = "select id, nome, cpf, matricula, email, telefone from alunos";
        return jdbcTemplate.query(sql, (rs, rowNum) -> map(rs));
    }

    @Override
    public List<Aluno> findByNome(String str) {
        String sql = "select id, nome, cpf, matricula, email, telefone from alunos where upper(nome) like :nome_";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("nome_",
                "%" + str.toUpperCase() + "%");
        return jdbcTemplate.query(sql, namedParameters, (rs, rowNum) -> map(rs));
    }

    @Override
    public Aluno findByMatricula(int matricula) {
        String sql = "select id, nome, cpf, matricula, email, telefone from alunos where matricula = :matricula_";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("matricula_", matricula);
        List<Aluno> result = jdbcTemplate.query(sql, namedParameters, (rs, rowNum) -> map(rs));
        return result.isEmpty() ? null : result.get(0);
    }

}
