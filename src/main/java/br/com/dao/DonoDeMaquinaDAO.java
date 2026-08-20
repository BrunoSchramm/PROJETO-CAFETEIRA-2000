package br.com.dao;

import br.com.factory.ConnectionFactory;
import br.com.model.DonoDeMaquina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonoDeMaquinaDAO {

    public void salvar(DonoDeMaquina dono) {
        String sql = "INSERT INTO donoDeMaquina (nome, usoParaVendas) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dono.getNome());
            stmt.setBoolean(2, dono.isUsoParaVendas());

            stmt.executeUpdate();
            System.out.println("Dono cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar dono: " + e.getMessage());
        }
    }

    public List<DonoDeMaquina> listarTodos() {
        String sql = "SELECT * FROM donoDeMaquina";
        List<DonoDeMaquina> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DonoDeMaquina dono = new DonoDeMaquina();
                dono.setId(rs.getInt("id"));
                dono.setNome(rs.getString("nome"));
                dono.setUsoParaVendas(rs.getBoolean("usoParaVendas"));
                lista.add(dono);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar donos: " + e.getMessage());
        }

        return lista;
    }

    public DonoDeMaquina buscarPorId(int id) {
        String sql = "SELECT * FROM donoDeMaquina WHERE id = ?";
        DonoDeMaquina dono = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    dono = new DonoDeMaquina();
                    dono.setId(rs.getInt("id"));
                    dono.setNome(rs.getString("nome"));
                    dono.setUsoParaVendas(rs.getBoolean("usoParaVendas"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar dono: " + e.getMessage());
        }

        return dono;
    }

    public void atualizar(DonoDeMaquina dono) {
        String sql = "UPDATE donoDeMaquina SET nome = ?, usoParaVendas = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dono.getNome());
            stmt.setBoolean(2, dono.isUsoParaVendas());
            stmt.setInt(3, dono.getId());

            stmt.executeUpdate();
            System.out.println("Dono atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar dono: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM donoDeMaquina WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Dono removido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar dono: " + e.getMessage());
        }
    }
}