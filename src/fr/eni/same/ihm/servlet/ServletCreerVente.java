package fr.eni.same.ihm.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	    }else{//recup info adresse de l utilisateur pour placer par defaut l adresse a l emplacement retrait
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
		Vente newVente= new Vente();
		newVente.setNomArticle(request.getParameter("article"));
		newVente.setDescription(request.getParameter("articleDescription"));
		//newVente.setCategorie(request.getParameterValues("")); a chercher comment faire pour le select
		//newVente.setDateFinEncheres(LocalDate.parse(request.getParameter("dateFinEnchere")));
		//newVente.setMiseAPrix.parseInt(request.getParameter("miseAPrix"));
		//newVente.setUtilisateurVendeur.(request.getParameter("rue"));
		
		if(request.getParameter("publierVente") != null) {
			//vente.ajouterVente(newVente);
		}else if(request.getParameter("enregistrerVente") != null) {
			//cookie
		}else if(request.getParameter("annuler") != null) {
			//annuler enregistrement
		}
	}

	
}

