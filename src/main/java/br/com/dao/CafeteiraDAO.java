package br.com.dao;

import br.com.factory.ConnectionFactory;
import br.com.model.Cafeteira;
import br.com.model.StatusMaquina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CafeteiraDAO {

    public void salvar(Cafeteira cafeteira) {
        String sql = "INSERT INTO cafeteira (id_dono, qtdAgua_ml, qtdGraos_g, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cafeteira.getIdDono());
            stmt.setInt(2, cafeteira.getQtdAgua());
            stmt.setInt(3, cafeteira.getQtdGraos());
            stmt.setString(4, cafeteira.getStatus().name().toLowerCase());

            stmt.executeUpdate();
            System.out.println("Cafeteira cadastrada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar cafeteira: " + e.getMessage());
        }
    }

    public List<Cafeteira> listarTodas() {
        String sql = "SELECT * FROM cafeteira";
        List<Cafeteira> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cafeteira c = new Cafeteira();
                c.setId(rs.getInt("id"));
                c.setIdDono(rs.getInt("id_dono"));
                c.setQtdAgua(rs.getInt("qtdAgua_ml"));
                c.setQtdGraos(rs.getInt("qtdGraos_g"));
                
                String statusDb = rs.getString("status").toUpperCase();
                c.setStatus(StatusMaquina.valueOf(statusDb));

                lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar cafeteiras: " + e.getMessage());
        }

        return lista;
    }

    public Cafeteira buscarPorId(int id) {
        String sql = "SELECT * FROM cafeteira WHERE id = ?";
        Cafeteira cafeteira = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cafeteira = new Cafeteira();
                    cafeteira.setId(rs.getInt("id"));
                    cafeteira.setIdDono(rs.getInt("id_dono"));
                    cafeteira.setQtdAgua(rs.getInt("qtdAgua_ml"));
                    cafeteira.setQtdGraos(rs.getInt("qtdGraos_g"));
                    
                    String statusDb = rs.getString("status").toUpperCase();
                    cafeteira.setStatus(StatusMaquina.valueOf(statusDb));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar cafeteira: " + e.getMessage());
        }

        return cafeteira;
    }

    public void atualizar(Cafeteira cafeteira) {
        String sql = "UPDATE cafeteira SET id_dono = ?, qtdAgua_ml = ?, qtdGraos_g = ?, status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cafeteira.getIdDono());
            stmt.setInt(2, cafeteira.getQtdAgua());
            stmt.setInt(3, cafeteira.getQtdGraos());
            stmt.setString(4, cafeteira.getStatus().name().toLowerCase());
            stmt.setInt(5, cafeteira.getId());

            stmt.executeUpdate();
            System.out.println("Cafeteira atualizada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cafeteira: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM cafeteira WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Cafeteira removida com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar cafeteira: " + e.getMessage());
        }
    }
}