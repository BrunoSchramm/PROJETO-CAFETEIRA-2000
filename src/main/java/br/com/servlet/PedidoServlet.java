package br.com.servlet;

import br.com.dao.CafeDAO;
import br.com.dao.CafeteiraDAO;
import br.com.dao.CompradorDAO;
import br.com.dao.PedidoDAO;
import br.com.model.Cafe;
import br.com.model.Cafeteira;
import br.com.model.Comprador;
import br.com.model.Pedido;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/pedido")
public class PedidoServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final CafeDAO cafeDAO = new CafeDAO();
    private final CafeteiraDAO cafeteiraDAO = new CafeteiraDAO();
    private final CompradorDAO compradorDAO = new CompradorDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/plain;charset=UTF-8");

        try {
            int idCafeteira = Integer.parseInt(request.getParameter("idCafeteira"));
            int idCafe = Integer.parseInt(request.getParameter("idCafe"));
            String idCompradorParam = request.getParameter("idComprador");

            // 1. Busca entidades no banco
            Cafeteira cafeteira = cafeteiraDAO.buscarPorId(idCafeteira);
            Cafe cafe = cafeDAO.buscarPorId(idCafe);

            if (cafeteira == null || cafe == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Erro: Cafeteira ou Café não encontrado.");
                return;
            }

            // 2. Valida estoque de insumos na cafeteira
            if (cafeteira.getQtdAgua() < cafe.getAguaNecessaria() || 
                cafeteira.getQtdGraos() < cafe.getGraosNecessarios()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Erro: Recursos insuficientes na cafeteira.");
                return;
            }

            // 3. Processa e valida comprador (se fornecido)
            int idComprador = 0;
            if (idCompradorParam != null && !idCompradorParam.isBlank()) {
                idComprador = Integer.parseInt(idCompradorParam);
                Comprador comprador = compradorDAO.buscarPorId(idComprador);

                if (comprador == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("Erro: Comprador não encontrado.");
                    return;
                }

                if (comprador.getSaldoCredito() < cafe.getPreco()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("Erro: Saldo insuficiente no cartão do comprador.");
                    return;
                }

                // Atualiza saldo do comprador
                comprador.setSaldoCredito(comprador.getSaldoCredito() - cafe.getPreco());
                compradorDAO.atualizar(comprador);
            }

            // 4. Consome os insumos da cafeteira
            cafeteira.setQtdAgua(cafeteira.getQtdAgua() - cafe.getAguaNecessaria());
            cafeteira.setQtdGraos(cafeteira.getQtdGraos() - cafe.getGraosNecessarios());
            cafeteiraDAO.atualizar(cafeteira);

            // 5. Registra o pedido no banco
            Pedido pedido = new Pedido();
            pedido.setIdCafeteira(idCafeteira);
            pedido.setIdCafe(idCafe);
            pedido.setIdComprador(idComprador);

            pedidoDAO.salvar(pedido);

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("Pedido realizado e café preparado com sucesso!");

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Erro: Parâmetros numéricos inválidos.");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro interno: " + e.getMessage());
        }
    }
}