package fr.eni.same.ihm.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.same.bll.VenteManager;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;

/**
 * @author Andrea
 * @author sl
 * Servlet implementation class ServletDetailVente
 */
@WebServlet("/ServletDetailVente")
public class ServletDetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * doGet : redirection vers la page affichant les informations de la vente côté vendeur (detailVente.jsp)
	 * 			1- récupération des informations de la vente
	 * 			2- Gestion de la fin d'une vente afin d'afficher l'acheteur ayant remporté la vente et les boutons associés
	 * 
	 *  Cette Servlet et la jsp correspondante prennent en charge les Maquettes 9 et 10
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("utilisateur") == null){
			response.sendRedirect("ServletConnexion");
	    	return;
		}else{
			HttpSession session = request.getSession();
			int noVente = (int) request.getAttribute("noVente");
			Vente vente;
			
			
			
			try {
				vente = VenteManager.getVenteManager().select(noVente);
				
				Timestamp dateFinEnchere = vente.getDateFinEncheres();
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String heureServer = format.format(GregorianCalendar.getInstance().getTime());
				
				request.setAttribute("vente", vente);
				request.setAttribute("heureServer", heureServer);
				request.setAttribute("dateFinEnchere", dateFinEnchere);
				
				
				if(vente.getUtilisateurVendeur() == (Utilisateur) session.getAttribute("utilisateur")) {
			
					
			
			
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp");
					rd.forward(request, response);
				}else {
					
					
					
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/pageEncherir.jsp");
					rd.forward(request, response);
				}
				
				
			} catch (BllException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
