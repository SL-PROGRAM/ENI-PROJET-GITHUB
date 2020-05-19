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

import fr.eni.same.bll.CategorieManager;
import fr.eni.same.bll.RetraitManager;
import fr.eni.same.bll.UtilisateurManager;
import fr.eni.same.bll.VenteManager;
import fr.eni.same.bo.Categorie;
import fr.eni.same.bo.Retrait;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;

/**
 * Servlet implementation class ServletListeEnchere
 */
@WebServlet("/ServletListeEncheres")
public class ServletListeEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * doGet : redirection vers la page affichant la liste des enchères disponibles (listeEnchere.jsp)
	 * 		   Récupération des identifiants de l'utilisateur, affichage des informations de l'utilisateur.
	 * 		   Récupération de la liste des ventes.
	 * 		   Gestion des filtres
	 * 		   
	 * 
	 *  Cette Servlet et la jsp correspondante prennent en charge la Maquette 5
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			Utilisateur utilisateur = UtilisateurManager.getUtilisateurManager().select(100);
			request.setAttribute("utilisateur", utilisateur);
			List<Vente> listeVentes = VenteManager.getVenteManager().selectAll();
			request.setAttribute("listeVentes", listeVentes);
			List<Categorie> listeCategories = CategorieManager.getCategorieManager().selectAll();
			request.setAttribute("listeCategories", listeCategories);
			List<Retrait> listeRetraits = RetraitManager.getRetraitManager().selectAll();
			request.setAttribute("listeRetraits", listeRetraits);
			Retrait retrait = RetraitManager.getRetraitManager().select(55);
			request.setAttribute("retrait", retrait);
			Utilisateur utilisateurVendeur = UtilisateurManager.getUtilisateurManager().select(75);
			request.setAttribute("utilisateurVendeur", utilisateurVendeur);
			
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
		doGet(request, response);
	}
	

}
