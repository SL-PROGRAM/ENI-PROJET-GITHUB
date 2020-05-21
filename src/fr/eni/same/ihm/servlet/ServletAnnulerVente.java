package fr.eni.same.ihm.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
				VenteManager.getVenteManager().delete(vente);
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listeEnchere.jsp");
				rd.forward(request, response);
			} catch (BllException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

	private void delete(Vente vente) throws BllException {
						
		
		;
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
