package fr.eni.same.ihm.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletVentesEnregistrees
 */
@WebServlet("/ServletVentesEnregistrees")
public class ServletVentesEnregistrees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * doGet : redirection vers la page permettant d'afficher la liste des ventes enregistrées (ventesEnregistrer.jsp)
	 * doPost : Récupération des informations de l'utilisateur, notamment la liste des ventes enegistrées et non finalisées de l'utilsiateur
	 * 
	 *  Cette Servlet et la jsp correspondante prennent en charge une maquette supplémentaire non présente dans les maquettes proposées.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ventesEnregistrees.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}

