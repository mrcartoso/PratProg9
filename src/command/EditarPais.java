package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Pais;
import service.PaisService;

public class EditarPais implements Command {

	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pId = request.getParameter("id");
		String pNome = request.getParameter("nome");
		String pPopulacao = request.getParameter("populacao");
		String pArea = request.getParameter("area");
		int id = -1;
		int populacao = -1;
		double area = -1.1;
		try {
			id = Integer.parseInt(pId);
			
		} catch (NumberFormatException e) {

		}
		try {
			 populacao = Integer.parseInt(pPopulacao);
			
		} catch (NumberFormatException e) {

		}
		try {
			try {
			 area = Double.parseDouble(pArea);
			}catch(NullPointerException e1) {
				
			}
			
		} catch (NumberFormatException e) {

		}
		
		
		Pais pais = new Pais();
		pais.setId(id);
		pais.setNome(pNome);
		pais.setPopulacao(populacao);
		pais.setArea(area);
		PaisService ps = new PaisService();

		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		ps.atualizar(pais);
		ArrayList<Pais> lista = new ArrayList<>();
		lista.add(pais);
		session.setAttribute("pais", pais);
		view = request.getRequestDispatcher("AlterarPais.jsp");

		view.forward(request, response);

	}

}
