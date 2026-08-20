package br.com.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Configurações do seu banco local
	private static final String URL = "jdbc:mysql://localhost:3306/cafeteira_db?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "cafeteira2000";     // Substitua pelo seu usuário do MySQL
    private static final String PASSWORD = "7889"; // Substitua pela sua senha do MySQL

    public static Connection getConnection() throws SQLException {
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do MySQL não encontrado! Verifique se adicionou o arquivo .jar nas bibliotecas do projeto.", e);
        }
    }
}