package fr.eni.same.ihm.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.same.bll.UtilisateurManager;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.exception.BllException;

/**
 * Servlet implementation class ServletCreerCompte
 * @author Mathieu/Etienne
 */
@WebServlet("/ServletModificationInformationsUtilisateur")
public class ServletModificationInformationsUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * doGet : redirection vers la page permettant à l'utilsiateur de modifier ses informations ou de créer son compte (modificationInformationsUtilisateur.jsp)
	 *		   Récupération des informations de l'utilisateur :
	 * 			   1- Déterminer si l'utilisateur créé un compte 
	 * 			   2- Si l'utilsiateur est déjà connecté grâce à une session alors la jsp affichera la partie de modification des informations du compte existant
	 * 
	 * doPost : Récupération des informations du formulaire. 
	 * 				- Dans le cas de la création de compte, l'utilisateur est redirigé vers la page connexion
	 * 				- Dans le cas de modification des informations de compte, l'utilisateur est redirigé vers 
	 * 				  cette même page (modificationsInformationsUtilisateur.jsp)
	 * 			
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
		
		String pseudo = request.getParameter("txtPseudo");
		String nom = request.getParameter("txtNom");
		String prenom = request.getParameter("txtPrenom");
		String email = request.getParameter("txtEmail");
		String telephone = request.getParameter("txtTelephone");
		String rue = request.getParameter("txtRue");
		String codePostal = request.getParameter("numCodePostal");
		String ville = request.getParameter("txtVille");
		String motDePasse = request.getParameter("txtMotDePasse");
		String confirmationMotDePasse = request.getParameter("txtConfirmation");
		String erreurSaisie = "";
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		
		
			if (motDePasse.equals(confirmationMotDePasse)) {
				Utilisateur utilisateur = new Utilisateur();  
				utilisateur.setPseudo(pseudo);
				utilisateur.setNom(nom);
				utilisateur.setPrenom(prenom);
				utilisateur.setEmail(email);
				utilisateur.setTelephone(telephone);
				utilisateur.setRue(rue);
				utilisateur.setCodePostal(codePostal);
				utilisateur.setVille(ville);
				utilisateur.setMotDePasse(motDePasse);
				
				try {
					erreurSaisie = UtilisateurManager.getUtilisateurManager().controleUpdateAndInsert(utilisateur);
					System.out.println("Je suis après le controlle");
					System.out.println(erreurSaisie);
					if (erreurSaisie.equals("")) {
						if (request.getSession().getAttribute("utilisateur") != null) {
							UtilisateurManager.getUtilisateurManager().update(utilisateur);
						} else {
							UtilisateurManager.getUtilisateurManager().insert(utilisateur);
						}
						System.out.println("Je suis dans l'insert utilisateur");
//					RequestDispatcher rd = request.getRequestDispatcher("/ServletConnexion");
//					rd.forward(request, response);
					}
				} catch (BllException e) {
					e.printStackTrace();
				}
				
			} else {
				erreurSaisie += "Les mots de passe ne correspondent pas !";
				request.setAttribute("erreurSaisie", erreurSaisie);
				
			
			
		}
		
		
		System.out.println(erreurSaisie);
//		RequestDispatcher rd = request.getRequestDispatcher("/ServletModificationInformationsUtilisateur");
//		rd.forward(request, response);
//		
		

	}

	
	
}
