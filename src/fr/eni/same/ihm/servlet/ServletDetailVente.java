package fr.eni.same.ihm.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.same.bll.RetraitManager;
import fr.eni.same.bll.UtilisateurManager;
import fr.eni.same.bll.VenteManager;
import fr.eni.same.bo.Retrait;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;

/**
 * @author Andrea
 * @author sl
 * Servlet implementation class ServletDetailVente
 */
@WebServlet("/ServletDetailVente")
public class ServletDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * doGet : redirection vers la page affichant les informations de la vente côté vendeur (detailVente.jsp)
	 * 			1- récupération des informations de la vente
	 * 			2- Gestion de la fin d'une vente afin d'afficher l'acheteur ayant remporté la vente et les boutons associés
	 * 
	 *  Cette Servlet et la jsp correspondante prennent en charge les Maquettes 9 et 10
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//vérification si session active
		request.setAttribute("erreur", "");
		HttpSession session =request.getSession();
		session.setMaxInactiveInterval(60*60*60);
		
		if (request.getSession().getAttribute("utilisateur") == null){
			response.sendRedirect("ServletConnexion");
	    	return;
		}else{
			
			int noVente = Integer.parseInt(request.getParameter("noVente"));			
			try {
				Retrait retrait =null;
				Vente vente = VenteManager.getVenteManager().select(noVente);
				List<Retrait> listeRetrait  = RetraitManager.getRetraitManager().selectAll();
				for(Retrait retrait2 : listeRetrait) {
					if(retrait2.getVente()== vente) {
						 retrait = RetraitManager.getRetraitManager().select(vente.getNoVente());
						 request.setAttribute("retrait", retrait);
					}
				}

				Timestamp heureServer = new Timestamp(System.currentTimeMillis());
				request.setAttribute("vente", vente);
				request.setAttribute("heureServer", heureServer);

				Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
				
//				if(vente.getDateFinEncheres().before(heureServer) ) {
//					if(vente.getUtilisateurAcheteur() != null 
//							&& vente.getUtilisateurAcheteur().getNoUtilisateur() == utilisateur.getNoUtilisateur()) {
//						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/enchereGagnee.jsp");
//					rd.forward(request, response);
//					}
//					String msgErreur = "Cette vente est terminée, vous n'êtes pas le grand gagnant désolé";
//					request.setAttribute("erreur", msgErreur);
//					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
//					rd.forward(request, response);
//				}
//				else 
					if(vente.getUtilisateurVendeur().getNoUtilisateur()==(utilisateur).getNoUtilisateur()) {
					//UtilisateurManager.getUtilisateurManager().verificationSessionActive(request, response, session, "/WEB-INF/jsp/listeEnchere.jsp");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp");
					rd.forward(request, response);
				}else {		
					//UtilisateurManager.getUtilisateurManager().verificationSessionActive(request, response, session, "/WEB-INF/jsp/listeEnchere.jsp");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/pageEncherir.jsp");
					rd.forward(request, response);
				}
				
			} catch (BllException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("erreur", "");
//		if(request.getParameter("retraitEffectue").equals("Retrait effectué")) {
//			
//		
//			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
//			rd.forward(request, response);	
//			}
			 	
			//iteration 2: possibilité d'  annuler la vente tant que retrait pas effectué
			//recup novente et delete la vente, remettre credit a l utilisateur
			// if (retrait pas effectué) delete Vente, utilsateur.credit = credit+vente.prixVente
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
			rd.forward(request, response);
		}
	


}
