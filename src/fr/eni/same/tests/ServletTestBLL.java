package fr.eni.same.tests;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.same.bll.CategorieManager;
import fr.eni.same.bll.EnchereManager;
import fr.eni.same.bll.FiltreManager;
import fr.eni.same.bll.RetraitManager;
import fr.eni.same.bll.UtilisateurManager;
import fr.eni.same.bll.VenteManager;
import fr.eni.same.bo.Categorie;
import fr.eni.same.bo.Enchere;
import fr.eni.same.bo.Retrait;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;


/**
 * Servlet implementation class ServletTestBLL
 */
@WebServlet("/ServletTestBLL")
public class ServletTestBLL extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		System.out.println("----------UTILISATEURS----------");
		testUtilisateurs();
		System.out.println("----------CATEGORIES----------");
		testCategories();
		System.out.println("----------ENCHERES----------");
		testEncheres();
		System.out.println("----------RETRAIT----------");
		testRetrait();
		System.out.println("----------VENTE----------");
		testVente();
		System.out.println("----------FILTRE----------");
		try {
			testFiltre(session);
			System.out.println("----------TEST SELECT BY MOTCLE----------");
			testSelectByMotCle();
			System.out.println("----------TEST SELECT BY CATEGORIE----------");
			testSelectByCategorie();
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void testUtilisateurs() {
		Utilisateur standardA = new Utilisateur(1,"pseudo","nomnom","prenom","email@plop.fr","0606060606","rue des fougères","35000","Rennes","motdepasseSecurise",155,false);
		try {
			UtilisateurManager.getUtilisateurManager().insert(standardA);
			standardA.setAdministrateur(true);
			UtilisateurManager.getUtilisateurManager().update(standardA);
			UtilisateurManager.getUtilisateurManager().select(standardA.getNoUtilisateur());
			UtilisateurManager.getUtilisateurManager().selectAll();
			UtilisateurManager.getUtilisateurManager().delete(standardA);
		} catch (BllException e) {
			e.printStackTrace();
		}
	}
	private void testCategories() {
		Categorie categorieSansPK = new Categorie("AZDZF");
		try {
			CategorieManager.getCategorieManager().insert(categorieSansPK);
			categorieSansPK.setLibelle("GHIJKL");
			CategorieManager.getCategorieManager().update(categorieSansPK);
			CategorieManager.getCategorieManager().select(categorieSansPK.getNoCategorie());
			CategorieManager.getCategorieManager().selectAll();
			CategorieManager.getCategorieManager().delete(categorieSansPK);
		} catch (BllException e) {
			e.printStackTrace();
		}
	}
	private void testEncheres() {
		Utilisateur standardA = new Utilisateur(1,"pseudo","nomnom","prenom","email@plop.fr","0606060606","rue des fougères","35000","Rennes","motdepasseSecurise",155,false);
			try {
				int idCategorie = CategorieManager.getCategorieManager().selectAll().get(0).getNoCategorie();
				UtilisateurManager.getUtilisateurManager().insert(standardA);
				Categorie categorie = CategorieManager.getCategorieManager().select(idCategorie);
				
				Timestamp now =  new Timestamp(System.currentTimeMillis());
				int aDay = 24 * 60 * 60 * 1000;
				Timestamp t = new Timestamp(now.getTime()+aDay*2);
				
				Vente vente = new Vente(1,"plop","description",t,5000,6000,standardA,standardA,categorie);
				VenteManager.getVenteManager().insert(vente);
				Enchere enchere1 = new Enchere(t,standardA,vente);
				EnchereManager.getEnchereManager().insert(enchere1);
				EnchereManager.getEnchereManager().update(enchere1);				
				EnchereManager.getEnchereManager().select(enchere1.getVenteEnchere().getNoVente(), enchere1.getUtilisateurEnchere().getNoUtilisateur());
				EnchereManager.getEnchereManager().selectAll();
				EnchereManager.getEnchereManager().delete(enchere1);
			} catch (BllException e) {
				e.printStackTrace();
			}

	}
	private void testRetrait() {
			Vente vente;
			try {
				int idVente = VenteManager.getVenteManager().selectAll().get(0).getNoVente();
				vente = VenteManager.getVenteManager().select(idVente);
				Retrait retrait = new Retrait("Rue de laposte","12345","Ville", vente);
				RetraitManager.getRetraitManager().insert(retrait);
				retrait.setRue("plop");
				RetraitManager.getRetraitManager().update(retrait);
				RetraitManager.getRetraitManager().select(retrait.getVente().getNoVente());
				RetraitManager.getRetraitManager().selectAll();
				RetraitManager.getRetraitManager().delete(retrait);
				
			} catch (BllException e) {
				e.printStackTrace();
		}
	}
	private void testVente() {
			Utilisateur acheteur;
			try {
				acheteur = UtilisateurManager.getUtilisateurManager().select(24);
				Utilisateur vendeur = UtilisateurManager.getUtilisateurManager().select(26);
				Categorie categorie = CategorieManager.getCategorieManager().select(4);
				
				Timestamp now =  new Timestamp(System.currentTimeMillis());
				int aDay = 24 * 60 * 60 * 1000;
				Timestamp timestamp = new Timestamp(now.getTime()+aDay*2);
				
				Vente vente = new Vente(4,"uneee","deuxxx",timestamp,2,6000,null,vendeur,categorie);
				VenteManager.getVenteManager().insert(vente);
				vente.setDescription("Description modifiée");
				VenteManager.getVenteManager().update(vente);
				VenteManager.getVenteManager().select(vente.getNoVente());
				VenteManager.getVenteManager().selectAll();
				Enchere enchere = new Enchere(timestamp,acheteur,vente);
				EnchereManager.getEnchereManager().insert(enchere);
				VenteManager.getVenteManager().updateAcheteur(vente);
				EnchereManager.getEnchereManager().delete(enchere);
				VenteManager.getVenteManager().delete(vente);
			} catch (BllException e) {
				e.printStackTrace();
		}
	}
	
	public void testFiltre(HttpSession session) throws BllException {
		session.setAttribute("ATT_SESSION_USER", UtilisateurManager.getUtilisateurManager().select(24));
		Categorie categorie = null;
		
		FiltreManager.getFiltreManager().filtreAutresEncheres(session, categorie);
		FiltreManager.getFiltreManager().filtreMesAcquisitions(session, categorie);
		FiltreManager.getFiltreManager().filtreMesEncheresEnCours(session, categorie);
		FiltreManager.getFiltreManager().filtreMesVentesPubliées(session, categorie);
		
		categorie = CategorieManager.getCategorieManager().select(22);
		
		FiltreManager.getFiltreManager().filtreAutresEncheres(session, categorie);
		FiltreManager.getFiltreManager().filtreMesAcquisitions(session, categorie);
		FiltreManager.getFiltreManager().filtreMesEncheresEnCours(session, categorie);
		FiltreManager.getFiltreManager().filtreMesVentesPubliées(session, categorie);
		
	}
	
	public void testSelectByMotCle() throws BllException {
		String motCleDescription1 = "modifier";
		String motCleNomArticle1 = "une";
		String motCleDescription2 = "crip";
		String motCleNomArticle2 = "lo";
		String motCleDescriptionNomArticle = "pc";
		String motCleTestCaseSensitive = "Une";
		
		System.out.println("----------DESCRIPTION MOTCLE COMPLET----------");
		List<Vente> listeByMotCleDescription1 = VenteManager.getVenteManager().selectByMotCle(motCleDescription1);
		System.out.println("Voici la liste de votre recherche par mot clé");
		for (Vente vente : listeByMotCleDescription1) {
			System.out.println(vente.toString());
		}
		
		System.out.println("----------ARTICLE MOTCCLE COMPLET----------");
		List<Vente> listeByMotCleNomArticle1 = VenteManager.getVenteManager().selectByMotCle(motCleNomArticle1);
		System.out.println("Voici la liste de votre recherche par mot clé");
		for (Vente vente : listeByMotCleNomArticle1) {
			System.out.println(vente.toString());
		}
		
		System.out.println("----------DESCRIPTION MOTCLE INCOMPLET----------");
		List<Vente> listeByMotCleDescription2 = VenteManager.getVenteManager().selectByMotCle(motCleDescription2);
		System.out.println("Voici la liste de votre recherche par mot clé");
		for (Vente vente : listeByMotCleDescription2) {
			System.out.println(vente.toString());
		}
		
		System.out.println("----------ARTICLE MOTCCLE INCOMPLET----------");
		List<Vente> listeByMotCleNomArticle2 = VenteManager.getVenteManager().selectByMotCle(motCleNomArticle2);
		System.out.println("Voici la liste de votre recherche par mot clé");
		for (Vente vente : listeByMotCleNomArticle2) {
			System.out.println(vente.toString());
		}
		
		System.out.println("----------MOTCLE DANS DESCRIPTION + ARTICLE----------");
		List<Vente> listeByMotCleDescriptionNomArticle = VenteManager.getVenteManager().selectByMotCle(motCleDescriptionNomArticle);
		System.out.println("Voici la liste de votre recherche par mot clé");
		for (Vente vente : listeByMotCleDescriptionNomArticle) {
			System.out.println(vente.toString());
		}
		
		System.out.println("----------TEST CASE SENSITIVE NOMARTICLE = Une A LA PLACE DE une----------");
		List<Vente> listeByMotCleTestCaseSensitive = VenteManager.getVenteManager().selectByMotCle(motCleTestCaseSensitive);
		System.out.println("Voici la liste de votre recherche par mot clé");
		for (Vente vente : listeByMotCleTestCaseSensitive) {
			System.out.println(vente.toString());
		}
	}
	
	public void testSelectByCategorie() throws BllException {
		
		Categorie categorie = CategorieManager.getCategorieManager().select(4);
		
		List<Vente> listeByCategorie = VenteManager.getVenteManager().selectByCategorie(categorie);
		System.out.println("Voici la liste de votre recherhce par catégorie");
		for (Vente vente : listeByCategorie) {
			System.out.println(vente.toString());
		}
	}
	
}