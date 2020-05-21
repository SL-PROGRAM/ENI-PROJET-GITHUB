package fr.eni.same.ihm.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.eni.same.bll.CategorieManager;
import fr.eni.same.bll.PublierVente;
import fr.eni.same.bo.Categorie;
import fr.eni.same.exception.BllException;

/**
 * @author Andrea
 * @author sl
 * 
 * Servlet implementation class ServletCreerVente
 */
@WebServlet("/ServletCreerVente")
@MultipartConfig(fileSizeThreshold=	1024*1024*2, // 2MB
									maxFileSize=1024*1024*10,      // 10MB
									maxRequestSize=1024*1024*50)   // 50MB
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
		request.setAttribute("erreur", "");
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
		request.setAttribute("erreur", "");
		//todo: verif si tous les champs remplis
		//verif date fin enchere si superieur au jour actuel
		
		

		
		
		//recup info dans string d abbord car je sais pas comment coder le parse avec getParamete
		
		if(request.getParameter("publierVente").equals("Publier")) {
			HttpSession session = request.getSession();
//			try {
//				UtilisateurManager.getUtilisateurManager().verificationSessionActive(request, response, session, "/WEB-INF/jsp/creerVentes.jsp");
//			} catch (BllException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			String msgErreur = null;
			try {
				msgErreur = PublierVente.enregistrerVente(session, request );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!msgErreur.equals("")) {
				request.setAttribute("erreur", msgErreur);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/creerVentes.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
				rd.forward(request, response);
			}
			
			
			
		}else if(request.getParameter("enregistrerVente").equals("Enregistrer")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
			rd.forward(request, response);
			
		}else if(request.getParameter("annuler").equals("Annuler")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
			rd.forward(request, response);
		}
		
	}

	


 
	
}

