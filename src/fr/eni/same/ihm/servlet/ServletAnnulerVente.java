package fr.eni.same.ihm.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.same.bll.EnchereManager;
import fr.eni.same.bll.RetraitManager;
import fr.eni.same.bll.VenteManager;
import fr.eni.same.bo.Enchere;
import fr.eni.same.bo.Retrait;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;

/**
 * Servlet implementation class ServletAnnulerVente
 */
@WebServlet("/ServletAnnulerVente")
public class ServletAnnulerVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("utilisateur") == null){
			response.sendRedirect("ServletConnexion");
	    	return;
		}
		
		int noVente = 0;
		if(request.getParameter("noVente") != null) {
			noVente = Integer.valueOf(request.getParameter("noVente"));
		}
		
		
		
		try {
			//1 - supprimer le Retrait
			Vente vente = VenteManager.getVenteManager().select(noVente);
			System.out.println(vente.toString());
			List<Retrait> listRetrait = RetraitManager.getRetraitManager().selectAll();
			
			Iterator<Retrait> iteratorRetrait = listRetrait.iterator();
			while ( iteratorRetrait.hasNext() ) {
				Retrait retrait = iteratorRetrait.next();
			    if(retrait.getVente().getNoVente() == vente.getNoVente()) {
					RetraitManager.getRetraitManager().delete(retrait);
				}
			}
				
			//2 - annuler les encheres
			List<Enchere> listEncheres = EnchereManager.getEnchereManager().selectAll();
			Iterator<Enchere> iteratorEncheres = listEncheres.iterator();
			while ( iteratorEncheres.hasNext() ) {
				Enchere enchere = iteratorEncheres.next();
			    if(enchere.getVenteEnchere().getNoVente() == vente.getNoVente()) {
			    	// 1 - recrediter personne
					Utilisateur encherisseur = enchere.getUtilisateurEnchere();
					int prixDeVente = vente.getPrixVente();
					int creditActuelEncherisseur = encherisseur.getCredit();
					encherisseur.setCredit(prixDeVente + creditActuelEncherisseur);
					
					//2 - supprimer enchere
			    	
			    	EnchereManager.getEnchereManager().delete(enchere);
				}
			}
		
			//3 - supprimer la vente
			VenteManager.getVenteManager().delete(vente);
			
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
