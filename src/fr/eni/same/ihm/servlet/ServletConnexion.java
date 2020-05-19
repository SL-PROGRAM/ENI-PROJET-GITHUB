package fr.eni.same.ihm.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.same.bo.Utilisateur;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * doGet : redirection vers la page de connexion (connexion.jsp)
	 * doPost : Auto-appel de la page connexion pour vérification des informations de connexion de l'utilisateur.
	 * 			Si informations incorrectes : affichage d'un message d'erreur
	 * 			Si informations correctes : 
	 * 							- Création d'une session
	 * 							- setAtttribut de l'utilisateur en session
	 * 							- redirection et envoie des informations utilisateur via session vers la page affichant la liste des enchères (listeEncheres.jsp)
	 * 
	 * Cette Servlet et la jsp correspondante prennent en charge la Maquette 1
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String identifiant = request.getParameter("txtIdentifiant");
		String password = request.getParameter("txtPassword");
//		
//		if (condition) {
//			Utilisateur utilisateur = new Utilisateur();
//			
//			HttpSession session = request.getSession();
//			session.setAttribute("utilisateur", utilisateur);
//		} 
		
	}

	
}
