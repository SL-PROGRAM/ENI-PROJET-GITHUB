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
 * 
 * @author Mathieu/Etienne
 */
@WebServlet("/ServletModificationInformationsUtilisateur")
public class ServletModificationInformationsUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) doGet : redirection vers la page permettant à l'utilsiateur de
	 *      modifier ses informations ou de créer son compte
	 *      (modificationInformationsUtilisateur.jsp) Récupération des informations
	 *      de l'utilisateur : 1- Déterminer si l'utilisateur créé un compte 2- Si
	 *      l'utilsiateur est déjà connecté grâce à une session alors la jsp
	 *      affichera la partie de modification des informations du compte existant
	 * 
	 *      doPost : Récupération des informations du formulaire. - Dans le cas de
	 *      la création de compte, l'utilisateur est redirigé vers la page connexion
	 *      - Dans le cas de modification des informations de compte, l'utilisateur
	 *      est redirigé vers cette même page
	 *      (modificationsInformationsUtilisateur.jsp)
	 * 
	 * 
	 *      Cette Servlet et la jsp correspondante prennent en charge les Maquettes
	 *      2 et 3
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modificationInformationsUtilisateur.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		boolean estDeconnecte = getIsUserDeleted(request);
		//Si oui, rediriger vers ServletListeEncheres
		if (estDeconnecte) {
			RequestDispatcher rd = request.getRequestDispatcher("/ServletListeEncheres");
			rd.forward(request, response);
			return;
		}
		
		//motDePasse = nouveau mot de passe lorsqu'un utilisateur connecté veut faire une update
		String motDePasse = request.getParameter("txtMotDePasse");
		String confirmationMotDePasse = request.getParameter("txtConfirmation");
		String erreurSaisie = "";
		if (request.getSession().getAttribute("utilisateur") == null) {
			if (motDePasse.equals(confirmationMotDePasse)) {
				Utilisateur utilisateur = new Utilisateur();
				setUtilisateurInfos(utilisateur, request);
				utilisateur.setMotDePasse(motDePasse);
				try {
					erreurSaisie = UtilisateurManager.getUtilisateurManager().controleUpdateAndInsert(utilisateur);
					System.out.println("Je suis après le controlle");
					System.out.println(erreurSaisie);
					if (erreurSaisie.equals("")) {
						System.out.println("Je suis dans l'insert utilisateur");
						UtilisateurManager.getUtilisateurManager().insert(utilisateur);
					}
//					RequestDispatcher rd = request.getRequestDispatcher("/ServletConnexion");
//					rd.forward(request, response);
				} catch (BllException e) {
					e.printStackTrace();
				}
			} else {
				erreurSaisie += "Les mots de passe ne correspondent pas !";
				request.setAttribute("erreurSaisie", erreurSaisie);
			}
		} else {
			String ancienMotDePasse = request.getParameter("txtAncienMotDePasse");
			String confirmAncienMotDePasse = request.getParameter("txtConfirmAncienMotDePasse");
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
				if (ancienMotDePasse.equals(confirmAncienMotDePasse) 
						&& ancienMotDePasse != null 
						&& ancienMotDePasse.equals(utilisateur.getMotDePasse())) {
					setUtilisateurInfos(utilisateur, request);
					if (motDePasse.equals(confirmationMotDePasse)) {
						utilisateur.setMotDePasse(motDePasse);
					} else {
						System.out.println("Le mot de passe ne correspondait pas. J'ai donc update avec l'ancien mot de passe");
						utilisateur.setMotDePasse(ancienMotDePasse);
					}
					try {
						erreurSaisie = UtilisateurManager.getUtilisateurManager().controleUpdateAndInsert(utilisateur);
						if (erreurSaisie.equals("")) {
//							System.out.println("Je suis dans l'update utilisateur");
							UtilisateurManager.getUtilisateurManager().update(utilisateur);
							request.getSession().setAttribute("utilisateur", utilisateur);
						}
					} catch (BllException e) {
						e.printStackTrace();
					}
				} else {
					erreurSaisie += "Les mots de passe ne correspondent pas !";
					request.setAttribute("erreurSaisie", erreurSaisie);
				}
		//	}

		}
		System.out.println(erreurSaisie);
//		RequestDispatcher rd = request.getRequestDispatcher("/ServletModificationInformationsUtilisateur");
//		rd.forward(request, response);

	}
	
	/**
	 * Méthode permettant l'update ou la création d'un nouvel utilisateur
	 * @param utilisateur l'utilisateur à mettre à jour ou un nouvel utilisateur
	 * @param request 
	 */
	private void setUtilisateurInfos(Utilisateur utilisateur, HttpServletRequest request) {
		String pseudo = request.getParameter("txtPseudo");
		String nom = request.getParameter("txtNom");
		String prenom = request.getParameter("txtPrenom");
		String email = request.getParameter("txtEmail");
		String telephone = request.getParameter("txtTelephone");
		String rue = request.getParameter("txtRue");
		String codePostal = request.getParameter("numCodePostal");
		String ville = request.getParameter("txtVille");
		
		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setVille(ville);
	}
	
	/**
	 * Vérifie que l'utilisateur a cliqué sur "Supprimer mon compte" ou non
	 * @Return true : si l'utilisateur a cliqué sur le bouton
	 * @Return false : si l'utilisateur n'a pas cliqué sur le bouton
	 */
	private boolean getIsUserDeleted(HttpServletRequest request)
			throws ServletException, IOException {
		if (request.getParameter("suppressionCompte") != null) {
			if (request.getSession().getAttribute("utilisateur") != null) {
				try {
					UtilisateurManager.getUtilisateurManager().delete(((Utilisateur) (request.getSession().getAttribute("utilisateur"))));
					request.getSession().invalidate();
					return true;
				} catch (BllException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

}
