 package fr.eni.same.ihm.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.same.bo.Vente;

/**
 * @author sl
 * @author andrea
 * Servlet implementation class ServletEnchereGagnee
 */
@WebServlet("/ServletEnchereGagnee")
public class ServletEnchereGagnee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * doGet : redirection vers la page affichant les informations de l'enchère gagnée (enchereGagnee.jsp)
	 *  	   Récupération des informations liées à la vente que l'utilisateur a remporté
	 * 
	 *  Cette Servlet et la jsp correspondante prennent en charge la Maquette 8
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vente vente = (Vente) request.getAttribute("vente");
		request.setAttribute("vente", vente);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereGagnee.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}

