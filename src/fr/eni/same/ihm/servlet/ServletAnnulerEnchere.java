package fr.eni.same.ihm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.same.bll.EnchereManager;
import fr.eni.same.bll.VenteManager;
import fr.eni.same.bo.Enchere;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;

/**
 * Servlet implementation class ServletAnnulerEnchere
 */
@WebServlet("/ServletAnnulerEnchere")
public class ServletAnnulerEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("erreur", "");
		int noVente = Integer.valueOf(request.getParameter("noVente"));
		int noUtilisateur = Integer.valueOf(request.getParameter("noAcheteur"));
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		String msgErreur = "";
		
		try {
			List<Enchere> listEncheres = EnchereManager.getEnchereManager().selectAll();
			Enchere enchereToDelete = null;
			Vente vente = VenteManager.getVenteManager().select(noVente);
			
			for (Enchere enchere : listEncheres) {
				if(enchere.getUtilisateurEnchere().getNoUtilisateur() == noUtilisateur
						&& enchere.getVenteEnchere().getNoVente() == noVente) {
					enchereToDelete = enchere;
				}
			}if(vente.getUtilisateurAcheteur().getNoUtilisateur() == utilisateur.getNoUtilisateur()) {
				msgErreur += "Vous etes actuellement l'acheteur principal, vous ne pouvez pas annuler votre offre";
			}
			else if(enchereToDelete == null) {
				msgErreur += "Vous n'avez pas fait d'enchère sur ce produit";
			}else {
				if(vente.getUtilisateurAcheteur().getNoUtilisateur() == enchereToDelete.getUtilisateurEnchere().getNoUtilisateur()) {
					int prixDeVente = vente.getPrixVente();
					int creditActuel = enchereToDelete.getUtilisateurEnchere().getCredit();
					enchereToDelete.getUtilisateurEnchere().setCredit(prixDeVente + creditActuel);
				}
				EnchereManager.getEnchereManager().delete(enchereToDelete);
				msgErreur += "Votre enchère a bien été supprimée";
			}	
		} catch (BllException e) {
			msgErreur += e;
		} finally {
			request.setAttribute("erreur", msgErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
			rd.forward(request, response);
		}
		
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
