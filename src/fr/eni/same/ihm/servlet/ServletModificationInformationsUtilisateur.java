package fr.eni.same.ihm.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.same.bo.Utilisateur;

/**
 * Servlet implementation class ServletCreerCompte
 */
@WebServlet("/ServletModificationInformationsUtilisateur")
public class ServletModificationInformationsUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * doGet : redirection vers la page permettant à l'utilsiateur de modifier ses informations ou de créer son compte (modificationInformationsUtilisateur.jsp)
	 * doPost : Récupération desi nformations de l'utilisateur :
	 * 			1- Déterminer si l'utilisateur créé un compte 
	 * 			2- Si l'utilsiateur est déjà connecté grâce à une session alors la jsp affichera la partie de modification des informations du compte existant
	 * 
	 *  Cette Servlet et la jsp correspondante prennent en charge les Maquettes 2 et 3
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modificationInformationsUtilisateur.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recup infos champs pour création compte:
		
		String pseudo = request.getParameter("txtPseudo");
		String nom = request.getParameter("txtNom");
		String prenom = request.getParameter("txtPrenom");
		String email = request.getParameter("txtEmail");
		String telephone = request.getParameter("txtTelephone");
		String rue = request.getParameter("txtRue");
		String codPostal = request.getParameter("txtCodePostal");
		String ville = request.getParameter("txtVille");
		String mdp = request.getParameter("txtMotDePasse");
		String confirmation = request.getParameter("txtConfirmation");
		
		
		//creation utilisateur
//		Utilisateur newUtilisateur = new Utilisateur();
//		
//		if(condition: not empty & pseudoUnique) {
//			newUtilisateur.setPseudo(pseudo);
//		}
//		if(condition: not empty ) {
//			newUtilisateur.setPrenom(prenom);
//		}
//		if(condition: not empty ) {
//			newUtilisateur.setNom(nom);
//		}
	}//fin doPost

	
	
}
