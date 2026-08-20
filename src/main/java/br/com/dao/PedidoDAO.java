package br.com.dao;

import br.com.factory.ConnectionFactory;
import br.com.model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void salvar(Pedido pedido) {
        String sql = "INSERT INTO pedidos (id_cafeteira, id_cafe, id_comprador) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedido.getIdCafeteira());
            stmt.setInt(2, pedido.getIdCafe());

            // Trata o comprador opcional (pode ser NULL para uso doméstico)
            if (pedido.getIdComprador() > 0) {
                stmt.setInt(3, pedido.getIdComprador());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }

            stmt.executeUpdate();
            System.out.println("Pedido registrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar pedido: " + e.getMessage());
        }
    }

    public List<Pedido> listarTodos() {
        String sql = "SELECT * FROM pedidos";
        List<Pedido> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt("id"));
                p.setIdCafeteira(rs.getInt("id_cafeteira"));
                p.setIdCafe(rs.getInt("id_cafe"));
                p.setIdComprador(rs.getInt("id_comprador"));

                Timestamp timestamp = rs.getTimestamp("data_hora");
                if (timestamp != null) {
                    p.setDataHora(timestamp.toLocalDateTime());
                }

                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar pedidos: " + e.getMessage());
        }

        return lista;
    }
}