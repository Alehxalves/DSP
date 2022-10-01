package br.ufc.dspersist;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQLConnection {
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/dsPersist",
                    "postgres", "masterkey");
            connection.setAutoCommit(false);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
