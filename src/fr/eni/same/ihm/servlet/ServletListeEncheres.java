package fr.eni.same.ihm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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
import fr.eni.same.bll.UtilisateurManager;
import fr.eni.same.bll.VenteManager;
import fr.eni.same.bo.Categorie;
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
		
		
		
		
		//Utilisateur utilisateur = null;
		if (request.getSession().getAttribute("utilisateur") != null) {
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			System.out.println(utilisateur.toString());
		}
		
		List<List<Vente>> listes = new ArrayList<List<Vente>>();
		List<Vente> listeVentesByMotCle = null;
		List<Vente> listeFinale = new ArrayList<Vente>();
		Set set = new HashSet();
		
		if (request.getParameter("supprimmerCompteUtilisateur") != null) {
			if (request.getParameter("noUtilisateurVendeur") != null) {
				int noUtilisateurSuppression = Integer.parseInt(request.getParameter("noUtilisateurVendeur"));
				try {
					Utilisateur utilisateurSuppression = UtilisateurManager.getUtilisateurManager().select(noUtilisateurSuppression);
					UtilisateurManager.getUtilisateurManager().delete(utilisateurSuppression);
				} catch (BllException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		if (request.getParameterValues("filtres")!=null) {
			String[] valeurs = request.getParameterValues("filtres");
			for (int i = 0; i < valeurs.length; i++) {
				if (valeurs[i].equals("mesVentes")) {
					try {
						System.out.println("JE SUIS DANS MES VENTES");
						//listes.add(i, FiltreManager.getFiltreManager().filtreMesVentesPubliées(session));
						List<Vente> listeVentes = FiltreManager.getFiltreManager().filtreMesVentesPubliées(request.getSession());
						set.addAll(listeVentes);
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
//						System.out.println("JE SUIS DANS MES ENCHERES EN COURS");
//						//listes.add(i, FiltreManager.getFiltreManager().filtreMesEncheresEnCours(session));
//						List<Vente> listeVentes = FiltreManager.getFiltreManager().filtreMesVentesEnCours(request.getSession(), null);
//						for (Vente v : listeVentes) {
//							System.out.println(v.toString());
//						}
//						set.addAll(listeVentes);
//					} catch (BllException e) {
//						e.printStackTrace();
//					}
//				}

				if (valeurs[i].equals("mesAcquisitions")) {
					try {
						System.out.println("JE SUIS DANS MES ACQUISITIONS");
						//listes.add(i, FiltreManager.getFiltreManager().filtreMesAcquisitions(session));
						List<Vente> listeVentes = FiltreManager.getFiltreManager().filtreMesAcquisitions(request.getSession());
						set.addAll(listeVentes);
					} catch (BllException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (valeurs[i].equals("autresEncheres")) {
					try {
						System.out.println("JE SUIS DANS AUTRES ENCHERES");
						//listes.add(i, FiltreManager.getFiltreManager().filtreAutresEncheres(session));
						List<Vente> listeVentes = FiltreManager.getFiltreManager().filtreAutresEncheres(request.getSession());
						set.addAll(listeVentes);
					} catch (BllException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
			
			List<Vente> listeFiltres = new ArrayList<Vente>(set);
			/*******************************************************************************************************************************/
						//SELECT PAR CATEGORIE + SELECT PAR MOT CLE
			/*******************************************************************************************************************************/
			
			if (request.getParameter("txtMotCle")!=null) {
				try {
					listeVentesByMotCle = VenteManager.getVenteManager().selectByMotCle(request.getParameter("txtMotCle"));
				} catch (BllException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			for (Vente vente : listeFiltres) {
				for (Vente vente2 : listeVentesByMotCle) {
					if (vente == vente2) {
						listeFinale.add(vente);
					}
				}
			}
				
			System.out.println("COUCOU");
			for (int i = 0; i < listeFinale.size(); i++) {
				System.out.println(listeFinale.get(i).toString());
			}
			request.setAttribute("listeVentes", listeFinale);
		}
		
		List<Categorie> listeCategories;
		try {
			listeCategories = CategorieManager.getCategorieManager().selectAll();
			
			request.setAttribute("listeCategories", listeCategories);
			//	request.setAttribute("listeVentes", listeFinal);
			//request.setAttribute("utilisateur", utilisateur);
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
