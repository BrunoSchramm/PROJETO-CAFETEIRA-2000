package br.com.dao;

import br.com.factory.ConnectionFactory;
import br.com.factory.DatabaseUtils;
import br.com.model.Cafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CafeDAO {

    public CafeDAO() {
        DatabaseUtils.inicializarBanco();
    }

    private void popularDadosIniciais() {
        Cafe expresso = new Cafe();
        expresso.setNome("Expresso Tradicional");
        expresso.setPreco(6.50);
        expresso.setAguaNecessaria(50);
        expresso.setGraosNecessarios(10);
        salvar(expresso);

        Cafe cappuccino = new Cafe();
        cappuccino.setNome("Cappuccino Cremoso");
        cappuccino.setPreco(9.90);
        cappuccino.setAguaNecessaria(100);
        cappuccino.setGraosNecessarios(15);
        salvar(cappuccino);

        Cafe latte = new Cafe();
        latte.setNome("Latte Macchiato");
        latte.setPreco(11.50);
        latte.setAguaNecessaria(150);
        latte.setGraosNecessarios(12);
        salvar(latte);
    }

    public List<Cafe> listarTodos() {
        String sql = "SELECT * FROM cafes";
        List<Cafe> listaCafes = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cafe cafe = new Cafe();
                cafe.setId(rs.getInt("id"));
                cafe.setNome(rs.getString("nome_cafe"));
                cafe.setPreco(rs.getDouble("preco"));
                cafe.setAguaNecessaria(rs.getInt("agua_necessaria_ml"));
                cafe.setGraosNecessarios(rs.getInt("graos_necessarios_g"));
                listaCafes.add(cafe);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar cafés: " + e.getMessage());
        }

        // Se o banco estiver vazio, insere os cafés e busca novamente
        if (listaCafes.isEmpty()) {
            popularDadosIniciais();
            return buscarTodosSemPopular();
        }

        return listaCafes;
    }

    private List<Cafe> buscarTodosSemPopular() {
        String sql = "SELECT * FROM cafes";
        List<Cafe> listaCafes = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cafe cafe = new Cafe();
                cafe.setId(rs.getInt("id"));
                cafe.setNome(rs.getString("nome_cafe"));
                cafe.setPreco(rs.getDouble("preco"));
                cafe.setAguaNecessaria(rs.getInt("agua_necessaria_ml"));
                cafe.setGraosNecessarios(rs.getInt("graos_necessarios_g"));
                listaCafes.add(cafe);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao relistar cafés: " + e.getMessage());
        }

        return listaCafes;
    }

    public void salvar(Cafe cafe) {
        String sql = "INSERT INTO cafes (nome_cafe, preco, agua_necessaria_ml, graos_necessarios_g) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cafe.getNome());
            stmt.setDouble(2, cafe.getPreco());
            stmt.setInt(3, cafe.getAguaNecessaria());
            stmt.setInt(4, cafe.getGraosNecessarios());

            stmt.executeUpdate();
            System.out.println("Café cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar café: " + e.getMessage());
        }
    }

    public Cafe buscarPorId(int id) {
        String sql = "SELECT * FROM cafes WHERE id = ?";
        Cafe cafe = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cafe = new Cafe();
                    cafe.setId(rs.getInt("id"));
                    cafe.setNome(rs.getString("nome_cafe"));
                    cafe.setPreco(rs.getDouble("preco"));
                    cafe.setAguaNecessaria(rs.getInt("agua_necessaria_ml"));
                    cafe.setGraosNecessarios(rs.getInt("graos_necessarios_g"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar café por ID: " + e.getMessage());
        }

        return cafe;
    }

    public void atualizar(Cafe cafe) {
        String sql = "UPDATE cafes SET nome_cafe = ?, preco = ?, agua_necessaria_ml = ?, graos_necessarios_g = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cafe.getNome());
            stmt.setDouble(2, cafe.getPreco());
            stmt.setInt(3, cafe.getAguaNecessaria());
            stmt.setInt(4, cafe.getGraosNecessarios());
            stmt.setInt(5, cafe.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar café: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM cafes WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar café: " + e.getMessage());
        }
    }
}