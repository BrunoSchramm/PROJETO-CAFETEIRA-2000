package br.com.servlet;

import br.com.dao.CafeDAO;
import br.com.model.Cafe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/cafes")
public class CafeServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final CafeDAO cafeDAO = new CafeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Cafe> lista = cafeDAO.listarTodos();
        request.setAttribute("listaCafes", lista);
        request.getRequestDispatcher("/listar-cafes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));
        int agua = Integer.parseInt(request.getParameter("agua"));
        int graos = Integer.parseInt(request.getParameter("graos"));

        Cafe cafe = new Cafe();
        cafe.setNome(nome);
        cafe.setPreco(preco);
        cafe.setAguaNecessaria(agua);
        cafe.setGraosNecessarios(graos);

        cafeDAO.salvar(cafe);
        response.sendRedirect(request.getContextPath() + "/cafes");
    }
}