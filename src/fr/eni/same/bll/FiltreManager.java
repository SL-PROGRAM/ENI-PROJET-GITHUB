package fr.eni.same.bll;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import fr.eni.same.bo.Categorie;
import fr.eni.same.bo.Enchere;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;

/**
 * BLL - Class qui contient les filtres de recherche
 * 
 * @author sl
 *
 */
public class FiltreManager {

	private static FiltreManager instance;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de
	 * la classe
	 * 
	 * @throws BllException
	 */
	private FiltreManager() {
	}

	/**
	 * methode Get pour récupérer l'instance et la créer si elle n'existe pas
	 * 
	 * @return
	 */
	public static synchronized FiltreManager getFiltreManager() {
		if (instance == null) {
			instance = new FiltreManager();
		}
		return instance;
	}

	public List<Vente> filtreMesVentesPubliees(HttpSession session, Categorie categorie) throws BllException {
		List<Vente> mesVentesPubliées = new ArrayList<Vente>();
		List<Vente> allVentes = VenteManager.getVenteManager().selectAll();
		Utilisateur utilisateurConnect = (Utilisateur) session.getAttribute("utilisateur");
		for (int i = 0; i < allVentes.size(); i++) {
			if (utilisateurConnect != null) {
				if (allVentes.get(i).getUtilisateurVendeur().getNoUtilisateur() == utilisateurConnect.getNoUtilisateur()) {
					if (categorie == null) {
						mesVentesPubliées.add(allVentes.get(i));
					} else if (allVentes.get(i).getCategorie() == categorie) {
						mesVentesPubliées.add(allVentes.get(i));
					}
				}
			}
		}
//		Collections.sort(mesVentesPubliées, Collections.reverseOrder());

		return mesVentesPubliées;
	}

	public List<Vente> filtreMesVentesPubliees(HttpSession session) throws BllException {
		List<Vente> mesVentesPubliées = filtreMesVentesPubliees(session, null);
		return mesVentesPubliées;
	}

	public List<Vente> filtreMesVentesEnregistrees(Cookie cookie[], Categorie categorie) {
		List<Vente> mesVentesEnregistrees = new ArrayList<Vente>();

		// Recupérer info dans Cookies

//		Collections.sort(mesVentesEnregistrees, Collections.reverseOrder()); 

		return mesVentesEnregistrees;
	}

	public List<Vente> filtreMesVentesEnregistrees(Cookie cookie[]) {
		List<Vente> mesVentesEnregistrees = filtreMesVentesEnregistrees(cookie, null);
		return mesVentesEnregistrees;
	}

	public List<Vente> filtreMesVentesEnCours(HttpSession session, Categorie categorie) throws BllException {
		List<Vente> listeMesVentesEnCours = new ArrayList<Vente>();
		List<Enchere> listeEncheres = EnchereManager.getEnchereManager().selectEnchereEnCours();
		List<Vente> listeVentes = VenteManager.getVenteManager().selectAll();
		Utilisateur utilisateurConnect = (Utilisateur) session.getAttribute("utilisateur");
		
		if (utilisateurConnect != null) {
			for (Enchere enchere : listeEncheres) {
				for (Vente vente : listeVentes) {
					if (enchere.getUtilisateurEnchere().getNoUtilisateur() == utilisateurConnect.getNoUtilisateur()
							&& enchere.getVenteEnchere().getNoVente() == vente.getNoVente()) {
						listeMesVentesEnCours.add(vente);
					}
				}
					
			}
		}
		return listeMesVentesEnCours;
	}

	public List<Enchere> filtreMesEncheresEnCours(HttpSession session, Categorie categorie) throws BllException {
		List<Enchere> mesEncheresEnCours = new ArrayList<Enchere>();
		List<Enchere> allEncheresEnCours = EnchereManager.getEnchereManager().selectEnchereEnCours();
		Utilisateur utilisateurConnect = (Utilisateur) session.getAttribute("utilisateur");
		for (int i = 0; i < allEncheresEnCours.size(); i++) {
			if (allEncheresEnCours.get(i).getUtilisateurEnchere() == utilisateurConnect) {
				if (categorie == null) {
					mesEncheresEnCours.add(allEncheresEnCours.get(i));
				} else if (categorie == allEncheresEnCours.get(i).getVenteEnchere().getCategorie()) {
					mesEncheresEnCours.add(allEncheresEnCours.get(i));
				}
			}
		}
//		Collections.sort(mesEncheresEnCours, Collections.reverseOrder()); 

		return mesEncheresEnCours;
	}

	public List<Enchere> filtreMesEncheresEnCours(HttpSession session) throws BllException {
		List<Enchere> mesEncheresEnCours = filtreMesEncheresEnCours(session, null);
		return mesEncheresEnCours;
	}

	public List<Vente> filtreMesAcquisitions(HttpSession session, Categorie categorie) throws BllException {
		List<Vente> mesAcquisitions = new ArrayList<Vente>();
		List<Enchere> allEncheresFini = EnchereManager.getEnchereManager().selectEnchereFini();
		Utilisateur utilisateurConnect = (Utilisateur) session.getAttribute("utilisateur");
		for (int i = 0; i < allEncheresFini.size(); i++) {
			if (allEncheresFini.get(i).getUtilisateurEnchere() == utilisateurConnect) {
				if (categorie == null) {
					mesAcquisitions.add(allEncheresFini.get(i).getVenteEnchere());
				} else if (categorie == allEncheresFini.get(i).getVenteEnchere().getCategorie()) {
					mesAcquisitions.add(allEncheresFini.get(i).getVenteEnchere());
				}
			}
		}
//		Collections.sort(mesAcquisitions, Collections.reverseOrder()); 

		return mesAcquisitions;
	}

	public List<Vente> filtreMesAcquisitions(HttpSession session) throws BllException {
		List<Vente> mesAcquisitions = filtreMesAcquisitions(session, null);
		return mesAcquisitions;
	}

	public List<Vente> filtreAutresEncheres(HttpSession session, Categorie categorie) throws BllException {
		List<Vente> autresEncheres = new ArrayList<Vente>();
		List<Vente> allVentes = VenteManager.getVenteManager().selectAll();
		Utilisateur utilisateurConnect = (Utilisateur) session.getAttribute("utilisateur");
		
		if (utilisateurConnect != null) {
			int noUtilisateurConnect = utilisateurConnect.getNoUtilisateur();
			for (Vente vente : allVentes) {
				//Si il n'y a pas d'utilisateur acheteur ET que je ne suis pas le vendeur de la vente
				if (vente.getUtilisateurAcheteur() == null 
						&& vente.getUtilisateurVendeur().getNoUtilisateur() != noUtilisateurConnect) {
					autresEncheres.add(vente);
					//Si il y a un utilisateur acheteur 
					//ET que je ne suis pas le vendeur de la vente
					//ET que je ne suis pas l'acheteur de la vente
				} else if (vente.getUtilisateurAcheteur() != null 
						&& vente.getUtilisateurVendeur().getNoUtilisateur() != noUtilisateurConnect 
						&& vente.getUtilisateurAcheteur().getNoUtilisateur() != noUtilisateurConnect) {
					autresEncheres.add(vente);
				}
			}
		} else {
			autresEncheres = VenteManager.getVenteManager().selectAll();
		}
//		Collections.sort(autresEncheres, Collections.reverseOrder()); 
		return autresEncheres;
	}

	public List<Vente> filtreAutresEncheres(HttpSession session) throws BllException {
		List<Vente> autresEncheres = filtreAutresEncheres(session, null);
		return autresEncheres;
	}

}
