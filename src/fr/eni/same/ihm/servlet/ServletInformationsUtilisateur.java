package fr.eni.same.ihm.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletInformationsAcheteur
 * @author Mathieu/Etienne
 */
@WebServlet("/ServletInformationsUtilisateur")
public class ServletInformationsUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * doGet : Redirection vers la page affichant les informations d'un utilisateur (acheteur ou vendeur) (informationsUtilisateur.jsp)
	 * 		   Récupération des informations de l'utilisateur et affichage différencié si 
	 * 			- l'utilisateur à afficher est un acheteur OU  
	 * 			- l'utilisateur à afficher est un vendeur OU
	 * 
	 *  Cette Servlet et la jsp correspondante prennent en charge les Maquettes 4 et 12
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		if (request.getSession().getAttribute("utilisateur") == null){
//		response.sendRedirect("connexion");
//    	return;
//    }else recuperer les infos du vendeur par rapport  à la vente
//	
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/informationsUtilisateur.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
