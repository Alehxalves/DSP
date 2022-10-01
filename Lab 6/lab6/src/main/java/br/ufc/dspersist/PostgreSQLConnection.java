package br.ufc.dspersist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection {
    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/dsPersist",
                    "postgres",
                    "masterkey");
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
