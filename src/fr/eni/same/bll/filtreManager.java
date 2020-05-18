package fr.eni.same.bll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import fr.eni.same.bo.Categorie;
import fr.eni.same.bo.Enchere;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;

public class filtreManager {
	
	
	public List<Vente> filtreMesVentesPubliées(HttpSession session, Categorie categorie) throws BllException{
		List<Vente> mesVentesPubliées = new ArrayList<Vente>();
		List<Vente> allVentes = VenteManager.getVenteManager().selectAll();
		Utilisateur utilisateurConnect = (Utilisateur) session.getAttribute("ATT_SESSION_USER");
		for (int i = 0; i < allVentes.size(); i++) {
			if(allVentes.get(i).getUtilisateurVendeur()== utilisateurConnect){
				if(categorie == null) {
					mesVentesPubliées.add(allVentes.get(i));
				}else if (allVentes.get(i).getCategorie() == categorie) {
					mesVentesPubliées.add(allVentes.get(i));
				}
			}
		}
		Collections.sort(mesVentesPubliées, Collections.reverseOrder());
		
		return mesVentesPubliées;
	}
	
	public List<Vente> filtreMesVentesPubliées(HttpSession session) throws BllException{
		List<Vente> mesVentesPubliées = filtreMesVentesPubliées(session, null);
		return mesVentesPubliées;
	}
	
	public List<Vente> filtreMesVentesEnregistrees(Cookie cookie[], Categorie categorie){
		List<Vente> mesVentesEnregistrees = new ArrayList<Vente>();
		
		//Recupérer info dans Cookies
		
		Collections.sort(mesVentesEnregistrees, Collections.reverseOrder()); 
		
		return mesVentesEnregistrees;
	}
	
	
	public List<Vente> filtreMesVentesEnregistrees(Cookie cookie[]){
		List<Vente> mesVentesEnregistrees = filtreMesVentesEnregistrees(cookie, null);
		return mesVentesEnregistrees;
	}
	
	public List<Enchere> filtreMesEncheresEnCours(HttpSession session, Categorie categorie) throws BllException{
		List<Enchere> mesEncheresEnCours = new ArrayList<Enchere>();
		List<Enchere> allEncheresEnCours = EnchereManager.getEnchereManager().selectEnchereEnCours();
		Utilisateur utilisateurConnect = (Utilisateur) session.getAttribute("ATT_SESSION_USER");
		for (int i = 0; i < allEncheresEnCours.size(); i++) {
			if(allEncheresEnCours.get(i).getUtilisateurEnchere() == utilisateurConnect) {
				if(categorie == null) {
					mesEncheresEnCours.add(allEncheresEnCours.get(i));
				}else if(categorie == allEncheresEnCours.get(i).getVenteEnchere().getCategorie()) {
					mesEncheresEnCours.add(allEncheresEnCours.get(i));
				}
			}
		}
		Collections.sort(mesEncheresEnCours, Collections.reverseOrder()); 
		
		return mesEncheresEnCours;
	}
	
	public List<Enchere> filtreMesEncheresEnCours1(HttpSession session, Categorie categorie) throws BllException{
		List<Enchere> mesEncheresEnCours = filtreMesEncheresEnCours(session, null);
		for (Enchere enchere : mesEncheresEnCours) {
			if(categorie == null) {
				mesEncheresEnCours.add(enchere);
			}
			if(enchere.getVenteEnchere().getCategorie() == categorie) {
				mesEncheresEnCours.add(enchere);
			}
		}
		return mesEncheresEnCours;
	}
	
	
	public List<Enchere> filtreMesEncheresEnCours(HttpSession session) throws BllException{
		List<Enchere> mesEncheresEnCours = filtreMesEncheresEnCours(session, null);
		return mesEncheresEnCours;
	}
	
	
	public List<Vente> filtreMesAcquisitions(HttpSession session, Categorie categorie) throws BllException{
		List<Vente> mesAcquisitions = new ArrayList<Vente>();
		List<Enchere> allEncheresFini = EnchereManager.getEnchereManager().selectEnchereFini();
		Utilisateur utilisateurConnect = (Utilisateur) session.getAttribute("ATT_SESSION_USER");
		for (int i = 0; i < allEncheresFini.size(); i++) {
			if(allEncheresFini.get(i).getUtilisateurEnchere() == utilisateurConnect) {
				if(categorie == null) {
					mesAcquisitions.add(allEncheresFini.get(i).getVenteEnchere());
				}else if(categorie == allEncheresFini.get(i).getVenteEnchere().getCategorie()) {
					mesAcquisitions.add(allEncheresFini.get(i).getVenteEnchere());
				}
			}
		}
		Collections.sort(mesAcquisitions, Collections.reverseOrder()); 
		
		return mesAcquisitions;
	}
	
	public List<Vente> filtreMesAcquisitions(HttpSession session) throws BllException{
		List<Vente> mesAcquisitions = filtreMesAcquisitions(session, null);
		return mesAcquisitions;
	}
	
	public List<Vente> filtreAutresEncheres(HttpSession session, Categorie categorie) throws BllException{
		List<Vente> autresEncheres = new ArrayList<Vente>();
		List<Vente> allVentes = VenteManager.getVenteManager().selectAll();
		Utilisateur utilisateurConnect = (Utilisateur) session.getAttribute("ATT_SESSION_USER");

		for ( Vente vente : allVentes) {
			if (vente.getUtilisateurVendeur() != utilisateurConnect && vente.getUtilisateurAcheteur() != utilisateurConnect) {
				if(categorie == null) {
					autresEncheres.add(vente);
				}
				else if (vente.getCategorie() == categorie) {
					autresEncheres.add(vente);
				}
			}
		}
		Collections.sort(autresEncheres, Collections.reverseOrder()); 
		
		return autresEncheres;
	}

	public List<Vente> filtreAutresEncheres(HttpSession session) throws BllException{
		List<Vente> autresEncheres = filtreAutresEncheres(session, null);
		
		return autresEncheres;
	}
	
}
