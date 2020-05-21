package fr.eni.same.ihm.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.same.bll.EnchereManager;
import fr.eni.same.bll.UtilisateurManager;
import fr.eni.same.bll.VenteManager;
import fr.eni.same.bo.Enchere;
import fr.eni.same.bo.Retrait;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;

/**
 * Servlet implementation class ServletEnchere
 * @author Mathieu/Etienne
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
	 *  Cette Servlet et la jsp correspondante prennent en charge les Maquettes 6 et 7
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("erreur", "");

		//afficher infos de la vente ->recup id pr avoir les infos? liste?
		//iterartion 2:verif si l utilisateur arrivant sur cette page est le dernier a avoir encheri, si oui possibilité d annuler l enchere
		
		//Je pas oublier de recréditer au précédant acheteur sa mise.

		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/pageEncherir.jsp");
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//check si mon credit ne devient pas négatif, si ok alors encherir
		//debiter les points de mon credit de points
		request.setAttribute("erreur", "");
		System.out.println("DO POST - SERVLET ENCHERIR");
		String errorMsg ="";
		Vente venteEnCours = null;
		Retrait retrait = (Retrait) request.getAttribute("retrait");
		/**
		 * Vérification des crédits en cours de l'utilisateur
		 */
		if(request.getSession().getAttribute("utilisateur") == null) {
			return;
		}
		Utilisateur utilisateurEnCours = (Utilisateur) request.getSession().getAttribute("utilisateur");
		int propositionPrix = Integer.parseInt(request.getParameter("propositionPrix"));
		if(utilisateurEnCours.getCredit() >= propositionPrix) {
			System.out.println("L'utilisateur a suffisamment de crédits pour enchérir");
			try {
				//Récupérer la vente en cours
				venteEnCours = VenteManager.getVenteManager().select(Integer.parseInt(request.getParameter("venteConcernee")));
				System.out.println("Vente concernée : " + venteEnCours.toString());

				if(propositionPrix > venteEnCours.getPrixVente()) {
					//Récupère le timestamp actuel
					Timestamp timeStampNow = new Timestamp(System.currentTimeMillis());
					if(timeStampNow.before(venteEnCours.getDateFinEncheres())) {
						
						//Vérifie qu'il existe au moins une enchère
						if(venteEnCours.getUtilisateurAcheteur() == null) {
							System.out.println(venteEnCours.toString());
							System.out.println("Il n'existe pas d'enchères sur cette vente, j'insert");
							
							insertNouvelleEnchere(utilisateurEnCours, propositionPrix, venteEnCours, timeStampNow);
							
						} else if (venteEnCours.getUtilisateurAcheteur().getNoUtilisateur() == utilisateurEnCours.getNoUtilisateur()){
							errorMsg += "Vous êtes déjà propriétaire de cette vente";
						} else {
							//Récupère les crédits du vendeur et les lui réattribuent
							crediteAncienUtilisateurAcheteur(venteEnCours);
							
							if(EnchereManager.getEnchereManager().chkIfUserExist(utilisateurEnCours.getNoUtilisateur(),venteEnCours.getNoVente())) {
								System.out.println("J'ai déjà fait une offre, j'update");
								updateEnchere(utilisateurEnCours, propositionPrix, venteEnCours, timeStampNow);
								
							} else {
								System.out.println("J'insert");
								insertNouvelleEnchere(utilisateurEnCours, propositionPrix, venteEnCours, timeStampNow);	
							}
						}
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (BllException e) {
				e.printStackTrace();
			} finally {
				request.setAttribute("erreur", errorMsg);
				System.out.println("JUSTE AVANT ENVOI : " + venteEnCours.toString());
				request.setAttribute("vente", venteEnCours);
				request.setAttribute("retrait", retrait);
			}
			
			//SET ATTRIBUTES
			
			System.out.println("Enchère effectuée");
			RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/pageEncherir.jsp");
			rs.forward(request, response);
		}else {
			System.out.println("L'utilisateur n'a pas assez de crédits");
			RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/pageEncherir.jsp");
			rs.forward(request, response);
		}
		
	}

	private void crediteAncienUtilisateurAcheteur(Vente venteEnCours) throws BllException {
		Utilisateur ancienUtilisateurAcheteur = venteEnCours.getUtilisateurAcheteur();
		ancienUtilisateurAcheteur.setCredit(ancienUtilisateurAcheteur.getCredit() + venteEnCours.getPrixVente());
		UtilisateurManager.getUtilisateurManager().updateCreditUtilisateur(ancienUtilisateurAcheteur);
	}

	private void updateEnchere(Utilisateur utilisateurEnCours, int propositionPrix, Vente venteEnCours,
		Timestamp timeStampNow) throws BllException {
		//Retire les crédits de l'utilisateur en cours
		utilisateurEnCours.setCredit(utilisateurEnCours.getCredit() - propositionPrix);
		UtilisateurManager.getUtilisateurManager().updateCreditUtilisateur(utilisateurEnCours);
		
		//Met à jour l'enchère correspondante à la vente et mon numéro d'utilisateur
		Enchere enchereUpdate = EnchereManager.getEnchereManager().select(venteEnCours.getNoVente(), utilisateurEnCours.getNoUtilisateur());
		enchereUpdate.setDateEnchere(timeStampNow);
		EnchereManager.getEnchereManager().update(enchereUpdate);
		
		System.out.println("PROPOSITION PRIX : " + propositionPrix);
		//Met à jour le prix de la vente, et l'utilisateur acheteur de la vente
		venteEnCours.setPrixVente(propositionPrix);
		venteEnCours.setUtilisateurAcheteur(utilisateurEnCours);
		VenteManager.getVenteManager().update(venteEnCours);
		System.out.println("UPDATE VENTE : " + venteEnCours);
	}

	private void insertNouvelleEnchere(Utilisateur utilisateurEnCours, int propositionPrix, Vente venteEnCours,
			Timestamp timeStampNow) throws BllException {
		//Retire les crédits de l'utilisateur en cours
		utilisateurEnCours.setCredit(utilisateurEnCours.getCredit() - propositionPrix);
		UtilisateurManager.getUtilisateurManager().updateCreditUtilisateur(utilisateurEnCours);
		
		//Insère une nouvelle enchère
		Enchere enchereInsert = new Enchere(timeStampNow, utilisateurEnCours, venteEnCours);
		EnchereManager.getEnchereManager().insert(enchereInsert);
		
		//Met à jour le prix de la vente, et l'utilisateur acheteur de la vente
		venteEnCours.setPrixVente(propositionPrix);
		venteEnCours.setUtilisateurAcheteur(utilisateurEnCours);
		System.out.println("vente en cours dans la méthode : " + venteEnCours.toString());
		VenteManager.getVenteManager().update(venteEnCours);
		System.out.println(venteEnCours.toString());
	}
	
}
