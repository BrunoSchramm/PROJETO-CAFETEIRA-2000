package br.com.factory;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.tools.RunScript;

public class DatabaseUtils {

    /**
     * Inicializa a estrutura do banco de dados H2 executando o script SQL.
     * Cria as tabelas automaticamente se elas ainda não existirem no projeto.
     */
    public static void inicializarBanco() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            Reader reader = null;

            // Tenta carregar o arquivo .sql do classpath ou da raiz do projeto
            InputStream is = DatabaseUtils.class.getClassLoader().getResourceAsStream("projeto-cafeteira-2000.sql");
            if (is != null) {
                reader = new InputStreamReader(is);
            } else {
                reader = new FileReader("projeto-cafeteira-2000.sql");
            }

            // Executa o script SQL para criar as tabelas
            RunScript.execute(conn, reader);
            System.out.println("--- Banco de dados e tabelas prontos para uso! ---\n");

        } catch (Exception e) {
            System.err.println("Erro ao inicializar o banco de dados: " + e.getMessage());
        }
    }

    /**
     * Limpa os dados de todas as tabelas mantendo a estrutura original intacta.
     */
    public static void limparTabelas() {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {

            // Desativa a checagem de chave estrangeira no H2
            stmt.execute("SET REFERENTIAL_INTEGRITY FALSE");

            // Apaga todos os registros e reseta os IDs
            stmt.execute("TRUNCATE TABLE pedidos");
            stmt.execute("TRUNCATE TABLE comprador");
            stmt.execute("TRUNCATE TABLE cafes");
            stmt.execute("TRUNCATE TABLE cafeteira");
            stmt.execute("TRUNCATE TABLE donoDeMaquina");

            // Reativa a checagem de chave estrangeira
            stmt.execute("SET REFERENTIAL_INTEGRITY TRUE");

            System.out.println("--- Banco de dados limpo com sucesso! ---\n");

        } catch (SQLException e) {
            System.err.println("Erro ao limpar banco de dados: " + e.getMessage());
        }
    }
}