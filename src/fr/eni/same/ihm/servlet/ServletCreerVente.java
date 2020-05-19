package fr.eni.same.ihm.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import fr.eni.same.bll.VenteManager;
import fr.eni.same.bo.Categorie;
import fr.eni.same.bo.Retrait;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;
import fr.eni.same.helpers.FonctionGenerique;

/**
 * Servlet implementation class ServletCreerVente
 */
@WebServlet("/ServletCreerVente")
public class ServletCreerVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * doGet : redirection vers la page permettant de créer une nouvelle vente (vendreArticle.jsp)
	 *  	   Récupération des informations de l'utilisateur, notamment l'adresse afin de l'afficher automatiquement comme point de retrait
	 * 	
	 * Cette Servlet et la jsp correspondante prennent en charge la Maquette 11
	 */
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("utilisateur") == null){
		response.sendRedirect("ServletConnexion");
    	return;
	    }else{//recup info adresse de l utilisateurVendeur pour placer par defaut l adresse a l emplacement retrait
	    	HttpSession session = request.getSession();
	    	try {
				List<Categorie> listCategorie = CategorieManager.getCategorieManager().selectAll();
				session.setAttribute("categories", listCategorie);
			} catch (BllException e) {
				e.printStackTrace();
			}
	    	
	    }
			
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/creerVentes.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//todo: verif si tous les champs remplis
		//verif date fin enchere si superieur au jour actuel
		
		
		
		//recup info dans string d abbord car je sais pas comment coder le parse avec getParameter
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
		System.out.println(request.getParameter("publierVente"));
		
		if(request.getParameter("publierVente").equals("Publier")) {
			HttpSession session = request.getSession();
			String dateFinEnchere = request.getParameter("dateFinEnchere");
			String categorieLibelle = request.getParameter("selectCategorie");
			
			Timestamp date = FonctionGenerique.dateToTimestamp(dateFinEnchere);
			
			Categorie categorie = creerCategorie(categorieLibelle);		
			Vente newVente = creerVente(request, session, date, categorie);
			creerRetrait(request, newVente);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
			rd.forward(request, response);
			
			
		}else if(request.getParameter("enregistrerVente") != null) {
			//cookie
		}else if(request.getParameter("annuler") != null) {
			//annuler enregistrement
		}
		
		
	}

	private void creerRetrait(HttpServletRequest request, Vente newVente) {
		Retrait retrait = new Retrait();
		retrait.setRue(request.getParameter("rue"));
		retrait.setCodePostal(request.getParameter("codePostal"));
		retrait.setVille(request.getParameter("ville"));
		retrait.setVente(newVente);
		try {
			RetraitManager.getRetraitManager().insert(retrait);
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Vente creerVente(HttpServletRequest request, HttpSession session, Timestamp date, Categorie categorie) {
		Vente newVente= new Vente();
		newVente.setNomArticle(request.getParameter("article"));
		newVente.setDescription(request.getParameter("articleDescription"));
		newVente.setMiseAPrix(Integer.parseInt(request.getParameter("miseAPrix")));	
		newVente.setDateFinEncheres(date);//(request.getParameterValues("")); a chercher comment faire pour le select class Categorie??
		newVente.setCategorie(categorie);
		newVente.setUtilisateurVendeur((Utilisateur) session.getAttribute("utilisateur"));
		try {
			VenteManager.getVenteManager().insert(newVente);
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newVente;
	}

	private Categorie creerCategorie(String categorieLibelle) {
		Categorie categorie = new Categorie();
		categorie.setLibelle(categorieLibelle);
		try {
			CategorieManager.getCategorieManager().insert(categorie);
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorie;
	}
 
	
}

