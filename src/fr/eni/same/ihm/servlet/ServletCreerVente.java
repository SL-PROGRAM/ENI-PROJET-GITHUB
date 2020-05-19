package fr.eni.same.ihm.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.same.bo.Retrait;
import fr.eni.same.bo.Vente;

/**
 * Servlet implementation class ServletCreerVente
 */
@WebServlet("/ServletCreerVente")
public class ServletCreerVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * doGet : redirection vers la page permettant de créer une nouvelle vente (vendreArticle.jsp)
	 * doPost : Récupération des informations de l'utilisateur, notemment l'adresse afin de l'afficher automatiquement comme point de retrait
	 * 	
	 * Cette Servlet et la jsp correspondante prennent en charge la Maquette 11
	 */
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("utilisateur") == null){
		response.sendRedirect("connexion");
    	return;
	    }else{//recup info adresse de l utilisateurVendeur pour placer par defaut l adresse a l emplacement retrait
	    	HttpSession session = request.getSession();
			
			String rue = (String) session.getAttribute("rue");
			String codePostal = (String)session.getAttribute("codePostal");
			String ville = (String)session.getAttribute("ville");
			
			
			request.setAttribute("rue", rue);
			request.setAttribute("codePostal", codePostal);
			request.setAttribute("ville", ville);
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
		String dateFinEnchere = request.getParameter("dateFinEnchere");
		String miseAPrix= request.getParameter("miseAPrix");
		
		Retrait retrait = new Retrait();
		Vente newVente= new Vente();
		
		newVente.setNomArticle(request.getParameter("article"));
		newVente.setDescription(request.getParameter("articleDescription"));
		//(request.getParameterValues("")); a chercher comment faire pour le select class Categorie??
		
		newVente.setMiseAPrix(Integer.parseInt("miseAPrix"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        try {
				newVente.setDateFinEncheres((Timestamp) formatter.parse(dateFinEnchere));
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		
		
		retrait.setRue(request.getParameter("rue"));
		retrait.setCodePostal(request.getParameter("codePostal"));
		retrait.setVille(request.getParameter("ville"));
		//retrait.setVente(?);
		
		if(request.getParameter("publierVente") != null) {
			//vente.ajouterVente(newVente);
			//retrait.ajouterRetrait(retrait);
		}else if(request.getParameter("enregistrerVente") != null) {
			//cookie
		}else if(request.getParameter("annuler") != null) {
			//annuler enregistrement
		}
	}
 
	
}

