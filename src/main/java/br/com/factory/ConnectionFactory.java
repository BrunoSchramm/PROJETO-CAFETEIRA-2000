package br.com.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Cria/utiliza um arquivo de banco dentro de uma pasta "banco" na raiz do projeto
    private static final String URL = "jdbc:h2:./banco/cafeteira_db;MODE=MySQL;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            // Carrega o driver JDBC do H2
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do H2 não encontrado! Verifique o pom.xml.", e);
        }
    }
}