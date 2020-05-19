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

import fr.eni.same.bll.CategorieManager;
import fr.eni.same.bll.FiltreManager;
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
 * @author Mathieu / Etienne
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
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		System.out.println(utilisateur.toString());
		List<List<Vente>> listes = new ArrayList<List<Vente>>();
		
		
		if (request.getParameterValues("filtres")!=null) {
			String[] valeurs = request.getParameterValues("filtres");
			for (int i = 0; i < valeurs.length; i++) {
				if (valeurs[i].equals("mesVentes")) {
					try {
						listes.add(i, FiltreManager.getFiltreManager().filtreMesVentesPubliées(session));
					} catch (BllException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
//				if (valeurs[i].equals("mesVentesEnregistrees")) {
//					try {
//						listes.add(i, FiltreManager.getFiltreManager().filtreMesVentesEnregistrees(cookie));
//					} catch (BllException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}

//				if (valeurs[i].equals("mesEncheresEnCours")) {
//					try {
//						listes.add(i, FiltreManager.getFiltreManager().filtreMesEncheresEnCours(session));
//					} catch (BllException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}

				if (valeurs[i].equals("mesAcquisitions")) {
					try {
						
						listes.add(i, FiltreManager.getFiltreManager().filtreMesAcquisitions(session));
					} catch (BllException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				
				if (valeurs[i].equals("autresEncheres")) {
					try {
						listes.add(i, FiltreManager.getFiltreManager().filtreAutresEncheres(session));
					} catch (BllException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
			
			List<Vente> listeFinale = new ArrayList<Vente>(listes.get(0));
			listeFinale.addAll(listes.get(1));
			
		}
		
		List<Categorie> listeCategories;
		try {
			listeCategories = CategorieManager.getCategorieManager().selectAll();
			request.setAttribute("listeCategories", listeCategories);
			//	request.setAttribute("listeVentes", listeFinal);
			request.setAttribute("utilisateur", utilisateur);
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		checkMesVentesEnregistrees
//		checkMesEncheresEnCours
//		checkMesAcquisitions
		
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
