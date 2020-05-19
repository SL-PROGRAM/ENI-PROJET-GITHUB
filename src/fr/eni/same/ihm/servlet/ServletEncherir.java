package fr.eni.same.ihm.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEnchere
 */
@WebServlet("/ServletEncherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * doGet : redirection vers la page permettant d'enchérir sur une vente (pageEncherir.jsp)
	 * 		   Récupération des informations liées à la vente sur laquelle je veux enchérir.	
	 * 		   Vérification que l'utilisateur qui accède à la vente n'est pas l'utilisateur vendant l'article
	 * 		   Récupération de la dernière enchère de l'utilisateur voulant encherir sur la vente. Si pas de dernière enchère, 
	 * 		   annuler ma dernière enchère n'apparait pas.
	 * 
	 *  Cette Servlet et la jsp correspondante prennent en charge les Maquettes 6 et 7
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/pageEncherir.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
