package fr.eni.same.ihm.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
		
		System.out.println("DO POST - SERVLET ENCHERIR");
		
		//Récupère l'utilisateur qui essaye d'enchérir
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		System.out.println("Utilisateur connecté : " + utilisateur.getPseudo());
		
		if(request.getSession().getAttribute("utilisateur") == null) {
			System.out.println("Vous n'êtes pas connecté !");
			return;
		}
		
		
		Vente venteConcernee = null;
		//Récupère le numéro de la vente concernée
		int noVente = Integer.parseInt(request.getParameter("venteConcernee"));
		System.out.println("Vente concernée : " + noVente);
		try {
			venteConcernee = VenteManager.getVenteManager().select(noVente);
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Contient la nouvelle proposition 
		String propositionPrix= request.getParameter("propositionPrix");
		int propositionPrixInt = Integer.parseInt(propositionPrix);
		System.out.println("proposition de : " + utilisateur.getPseudo() + " : " + propositionPrixInt);
		
		
		//Récupère l'utilisateur qui vend l'article
		Utilisateur utilisateurVendeur = null;
		int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateurMeilleurOffre"));
		try {
			utilisateurVendeur = UtilisateurManager.getUtilisateurManager().select(noUtilisateur);
		} catch (BllException e1) {
			e1.printStackTrace();
		}
		System.out.println("L'utilisateur qui a la meilleure offre actuelle : " + utilisateurVendeur.getPseudo());
		
			if(utilisateur.getNoUtilisateur() == utilisateurVendeur.getNoUtilisateur()) {
			System.out.println("Vous ne pouvez pas enchérir sur votre propre enchère");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
			rd.forward(request, response);
			return;
		}
		
		//SI J'AI ASSEZ DE CREDIT
		if(utilisateur.getCredit() > propositionPrixInt) {
			//Retirer les crédits de l'utilisateur qui propose l'enchere
			utilisateur.setCredit(utilisateur.getCredit()-propositionPrixInt);
			try {
				if(EnchereManager.getEnchereManager().chkIfUserExist(utilisateur.getNoUtilisateur())) {
					//Si c'est le cas, UPDATER la date de l'enchere qui contient son idutilisateur
					Enchere enchereActuelle = EnchereManager.getEnchereManager().select(noVente, utilisateurVendeur.getNoUtilisateur());
					//Modifie le prix de vente
					System.out.println("VENTE ACTUELLE : " + venteConcernee.toString());
					venteConcernee.setPrixVente(propositionPrixInt);
					venteConcernee.setUtilisateurAcheteur(utilisateur);
					VenteManager.getVenteManager().update(venteConcernee);
					System.out.println("VENTE MODIFIEE : " + VenteManager.getVenteManager().select(venteConcernee.getNoVente()));
					EnchereManager.getEnchereManager().update(enchereActuelle);
				} else {
					Timestamp ts = new Timestamp(System.currentTimeMillis());
					Enchere nouvelleEnchere = new Enchere(ts, utilisateur, venteConcernee);
					EnchereManager.getEnchereManager().insert(nouvelleEnchere);
				}
				
				
				//Sinon insérer en base de donnée une nouvelle enchere avec la date now
				
				
				
				//Modifie les crédits de l'utilisateur qui enchérit
				UtilisateurManager.getUtilisateurManager().updateCreditUtilisateur(utilisateur);
				
				
				//Il devient ensuite l'enchérisseur (l'utilisateurAcheteur) sur cette vente
//				venteConcernee.setUtilisateurAcheteur(utilisateur);
//				venteConcernee.setPrixVente(propositionPrixInt);
//				enchereAModifier.setUtilisateurEnchere(utilisateur);
//				EnchereManager.getEnchereManager().updateEnchereur(enchereAModifier, utilisateurVendeur.getNoUtilisateur(), utilisateur.getNoUtilisateur());
//				System.out.println("Sur la vente n°" + venteConcernee.getNoVente() + " l'utilisateur acheteur est maintenant : " + venteConcernee.getUtilisateurAcheteur().getPseudo());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
				rd.forward(request, response);
			} catch (BllException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("PAS ASSEZ DE CREDIT");
			//Vérifier que l'utilisateur a assez points pour enchérir, sinon renvoyer sur la page avec une erreur
			//TODO
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
			rd.forward(request, response);
		}
	}
}
