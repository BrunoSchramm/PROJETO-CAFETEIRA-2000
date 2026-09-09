package br.com.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

    public static void limparTabelas() {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            // Desativa a checagem de chave estrangeira temporariamente
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0");

            // Apaga todos os registros e reseta o AUTO_INCREMENT para 1
            stmt.execute("TRUNCATE TABLE pedidos");
            stmt.execute("TRUNCATE TABLE cafeteira");
            stmt.execute("TRUNCATE TABLE cafes");
            stmt.execute("TRUNCATE TABLE comprador");
            stmt.execute("TRUNCATE TABLE donoDeMaquina");

            // Reativa a checagem de chave estrangeira
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1");

            System.out.println("--- Banco de dados limpo com sucesso! ---\n");

        } catch (SQLException e) {
            System.err.println("Erro ao limpar banco de dados: " + e.getMessage());
        }
    }
}