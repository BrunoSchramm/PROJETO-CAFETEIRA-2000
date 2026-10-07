package br.com.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

    public static void inicializarBanco() {
        String sqlCafes = "CREATE TABLE IF NOT EXISTS cafes (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nome_cafe VARCHAR(255) NOT NULL, " +
                "preco DOUBLE NOT NULL, " +
                "agua_necessaria_ml INT NOT NULL, " +
                "graos_necessarios_g INT NOT NULL" +
                ");";

        String sqlCafeteira = "CREATE TABLE IF NOT EXISTS cafeteira (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nivel_agua_ml INT NOT NULL, " +
                "nivel_graos_g INT NOT NULL" +
                ");";

        // Insere a cafeteira com ID 1 se ela ainda não existir
        String sqlPopulaCafeteira = "MERGE INTO cafeteira (id, nivel_agua_ml, nivel_graos_g) " +
                "KEY(id) VALUES (1, 1000, 500);";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlCafes);
            stmt.execute(sqlCafeteira);
            stmt.execute(sqlPopulaCafeteira);
            System.out.println("Tabelas 'cafes' e 'cafeteira' inicializadas com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inicializar o banco de dados: " + e.getMessage());
        }
    }

    public static void limparTabelas() {
        String sql = "DROP TABLE IF EXISTS cafes; DROP TABLE IF EXISTS cafeteira;";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabelas removidas com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao limpar tabelas: " + e.getMessage());
        }
    }
}