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

import com.sun.tools.classfile.Annotation.element_value;

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
 * 
 * @author Mathieu / Etienne
 */
@WebServlet("/ServletListeEncheres")
public class ServletListeEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) doGet : redirection vers la page affichant la liste des
	 *      enchères disponibles (listeEnchere.jsp) Récupération des identifiants de
	 *      l'utilisateur, affichage des informations de l'utilisateur. Récupération
	 *      de la liste des ventes. Gestion des filtres
	 * 
	 * 
	 *      Cette Servlet et la jsp correspondante prennent en charge la Maquette 5
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("erreur", "");
//		HttpSession session = request.getSession();

		// Utilisateur utilisateur = null;
		if (request.getSession().getAttribute("utilisateur") != null) {
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			System.out.println(utilisateur.toString());
		}

		List<Vente> listeVentesByMotCle = null;
		List<Vente> listeVentesByCategorie = null;
		List<Vente> listeFinale = new ArrayList<Vente>();
		String[] valeurs = null;
		Set set = new HashSet();

		if (request.getParameter("supprimmerCompteUtilisateur") != null) {
			if (request.getParameter("noUtilisateurVendeur") != null) {
				int noUtilisateurSuppression = Integer.parseInt(request.getParameter("noUtilisateurVendeur"));
				try {
					Utilisateur utilisateurSuppression = UtilisateurManager.getUtilisateurManager()
							.select(noUtilisateurSuppression);
					UtilisateurManager.getUtilisateurManager().delete(utilisateurSuppression);
				} catch (BllException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (request.getParameterValues("filtres") != null) {
			valeurs = request.getParameterValues("filtres");
			for (int i = 0; i < valeurs.length; i++) {
				if (valeurs[i].equals("mesVentes")) {
					try {
						List<Vente> listeVentes = FiltreManager.getFiltreManager()
								.filtreMesVentesPubliees(request.getSession());
						set.addAll(listeVentes);
						request.setAttribute("mesVentesIsSelected", true);
					} catch (BllException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (valeurs[i].equals("mesVentesEnregistrees")) {
//					try {
//						listes.add(i, FiltreManager.getFiltreManager().filtreMesVentesEnregistrees(cookie));
					request.setAttribute("mesVentesEnregistreesIsSelected", true);
//					} catch (BllException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}

				if (valeurs[i].equals("mesEncheresEnCours")) {
					try {
						System.out.println("JE SUIS DANS MES ENCHERES EN COURS");
						List<Vente> listeVentes = FiltreManager.getFiltreManager()
								.filtreMesVentesEnCours(request.getSession(), null);
						System.out.println("filtre 1 je suis sortie");
						for (Vente v : listeVentes) {
							System.out.println(v.toString());
						}
						set.addAll(listeVentes);
						request.setAttribute("mesEncheresEnCoursIsSelected", true);
					} catch (BllException e) {
						e.printStackTrace();
					}
				}

				if (valeurs[i].equals("mesAcquisitions")) {
					try {
						System.out.println("JE SUIS DANS MES ACQUISITIONS");
						// listes.add(i,
						// FiltreManager.getFiltreManager().filtreMesAcquisitions(session));
						List<Vente> listeVentes = FiltreManager.getFiltreManager()
								.filtreMesAcquisitions(request.getSession());
						set.addAll(listeVentes);
						request.setAttribute("mesAcquisitionsIsSelected", true);
					} catch (BllException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (valeurs[i].equals("autresEncheres")) {
					try {
						System.out.println("JE SUIS DANS AUTRES ENCHERES");
						// listes.add(i,
						// FiltreManager.getFiltreManager().filtreAutresEncheres(session));
						List<Vente> listeVentes = FiltreManager.getFiltreManager()
								.filtreAutresEncheres(request.getSession());
						set.addAll(listeVentes);
						request.setAttribute("autresEncheresIsSelected", true);
					} catch (BllException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}

			listeFinale = new ArrayList<Vente>(set);

		} else {
			// Gestion affichage listeEnchere lors de la premiere arrivée sur la page

			try {
				listeFinale = VenteManager.getVenteManager().selectAll();
				set.addAll(listeFinale);
			} catch (BllException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		/*******************************************************************************************************************************/
		// SELECT PAR CATEGORIE + SELECT PAR MOT CLE
		/*******************************************************************************************************************************/

		
		if (request.getParameter("selectCategorie") == null) {
			System.out.println("JE SUIS SELECT CATEGORIE = NULL");
		} else {
			List<Vente> nouvelleListe = new ArrayList<Vente>();
			for (int i = 0; i < listeFinale.size(); i++) {
				if (listeFinale.get(i).getCategorie().getLibelle().equals((request.getParameter("selectCategorie"))) 
						|| request.getParameter("selectCategorie").equals("Toutes")) {
					nouvelleListe.add(listeFinale.get(i));
				}
			}
			listeFinale = nouvelleListe;
			
			String libelle = request.getParameter("selectCategorie");
			
			List<Categorie> listeCategoriesLibelle = new ArrayList<Categorie>();
			try {
				listeCategoriesLibelle = CategorieManager.getCategorieManager().selectAll();
				for (Categorie categorie : listeCategoriesLibelle) {
					if (categorie.getLibelle().equals(libelle)) {
						request.setAttribute("categorieSelectionnee", categorie.getLibelle());
					}
					
				}
			} catch (BllException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (request.getParameter("txtMotCle") == null) {
			System.out.println("MOT CLE NULL");
		} else {
			List<Vente> nouvelleListe = new ArrayList<Vente>();
			for (int i = 0; i < listeFinale.size(); i++) {
				try {
					nouvelleListe = VenteManager.getVenteManager().selectByMotCle(listeFinale, request.getParameter("txtMotCle"));
				} catch (BllException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			listeFinale = nouvelleListe;
		}
		
			request.setAttribute("listeVentes", listeFinale);

			List<Categorie> listeCategories;
			try {
				listeCategories = CategorieManager.getCategorieManager().selectAll();

				request.setAttribute("listeCategories", listeCategories);
				// request.setAttribute("listeVentes", listeFinal);
				// request.setAttribute("utilisateur", utilisateur);
			} catch (BllException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
