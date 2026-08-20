package br.com.dao;

import br.com.factory.ConnectionFactory;
import br.com.model.Comprador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompradorDAO {

    public void salvar(Comprador comprador) {
        String sql = "INSERT INTO comprador (nome, saldo_credito) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comprador.getNome());
            stmt.setDouble(2, comprador.getSaldoCredito());

            stmt.executeUpdate();
            System.out.println("Comprador cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar comprador: " + e.getMessage());
        }
    }

    public List<Comprador> listarTodos() {
        String sql = "SELECT * FROM comprador";
        List<Comprador> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Comprador c = new Comprador();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setSaldoCredito(rs.getDouble("saldo_credito"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar compradores: " + e.getMessage());
        }

        return lista;
    }

    public Comprador buscarPorId(int id) {
        String sql = "SELECT * FROM comprador WHERE id = ?";
        Comprador comprador = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    comprador = new Comprador();
                    comprador.setId(rs.getInt("id"));
                    comprador.setNome(rs.getString("nome"));
                    comprador.setSaldoCredito(rs.getDouble("saldo_credito"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar comprador: " + e.getMessage());
        }

        return comprador;
    }

    public void atualizar(Comprador comprador) {
        String sql = "UPDATE comprador SET nome = ?, saldo_credito = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comprador.getNome());
            stmt.setDouble(2, comprador.getSaldoCredito());
            stmt.setInt(3, comprador.getId());

            stmt.executeUpdate();
            System.out.println("Comprador atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar comprador: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM comprador WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Comprador removido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar comprador: " + e.getMessage());
        }
    }
}