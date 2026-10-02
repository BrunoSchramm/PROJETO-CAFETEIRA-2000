package br.com.servlet;

import br.com.dao.CafeDAO;
import br.com.model.Cafe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/cafes")
public class CafeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final CafeDAO cafeDAO = new CafeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        List<Cafe> lista = cafeDAO.listarTodos();

        // Converte a lista de cafés para um array JSON
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < lista.size(); i++) {
            Cafe c = lista.get(i);
            json.append("{")
                    .append("\"id\":").append(c.getId()).append(",")
                    .append("\"nome\":\"").append(c.getNome()).append("\",")
                    .append("\"preco\":").append(c.getPreco()).append(",")
                    .append("\"agua\":").append(c.getAguaNecessaria()).append(",")
                    .append("\"graos\":").append(c.getGraosNecessarios())
                    .append("}");
            if (i < lista.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();
    }
}