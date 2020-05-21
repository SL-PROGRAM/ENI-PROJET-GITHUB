package fr.eni.same.ihm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xml.internal.serializer.utils.Utils;

import fr.eni.same.bll.EnchereManager;
import fr.eni.same.bll.RetraitManager;
import fr.eni.same.bll.UtilisateurManager;
import fr.eni.same.bll.VenteManager;
import fr.eni.same.bo.Enchere;
import fr.eni.same.bo.Retrait;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;

/**
 * Servlet implementation class ServletRetraitEffectue
 */
@WebServlet("/ServletRetraitEffectue")
public class ServletRetraitEffectue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("erreur", "");
		if (request.getSession().getAttribute("utilisateur") == null){
			response.sendRedirect("ServletConnexion");
		}else {
			int noVente = 0;
			if(request.getParameter("noVente") != null) {
				noVente = Integer.valueOf(request.getParameter("noVente"));
			}
			try {
				Vente vente = VenteManager.getVenteManager().select(noVente);				
				//1 - supprimer le Retrait
				supprimerRetraitLier(vente);				
				//2 - annuler les encheres
				supprimerEnchereLier(vente);
			
				//3 - supprimer la vente
				VenteManager.getVenteManager().delete(vente);
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
				rd.forward(request, response);
			} catch (BllException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	private void supprimerEnchereLier(Vente vente) throws BllException {
		List<Enchere> listEncheres = EnchereManager.getEnchereManager().selectAll();
		List<Enchere> listEncheresToDelete = new ArrayList<Enchere>();
		
		for (Enchere enchere : listEncheres) {
			if(enchere.getVenteEnchere().getNoVente() == vente.getNoVente()) {
				listEncheresToDelete.add(enchere);
			}
		}
		for (Enchere enchere : listEncheresToDelete) {
			Utilisateur encherisseur = enchere.getUtilisateurEnchere();
            int prixDeVente = vente.getPrixVente();
            int crditActuelEncherisseur = encherisseur.getCredit();
            encherisseur.setCredit(prixDeVente - crditActuelEncherisseur);
            UtilisateurManager.getUtilisateurManager().updateCreditUtilisateur(encherisseur);
            // todo crediter le vendeur:
         //   Utilisateur moi=  
            
          
			EnchereManager.getEnchereManager().delete(enchere);
		}
	}
	
	private void supprimerRetraitLier(Vente vente) throws BllException {
		List<Retrait> listRetrait = RetraitManager.getRetraitManager().selectAll();
		Retrait retraitToDelete = null;
		
		for (Retrait retrait : listRetrait) {
			if (retrait.getVente().getNoVente() == vente.getNoVente()) {
		    	retraitToDelete = retrait;
		    }
		}
		
		RetraitManager.getRetraitManager().delete(retraitToDelete);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
