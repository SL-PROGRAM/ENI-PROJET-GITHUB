package fr.eni.same.tests;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.same.bo.Categorie;
import fr.eni.same.bo.Enchere;
import fr.eni.same.bo.Retrait;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BusinessException;

/**
 * Servlet de connexion à la base de donnée
 * SERVLET TEST, NE PAS UTILISER EN PRODUCTION
 * @author etienne
 *
 */
@WebServlet("/ServletTestConnexion")
public class ServletTestConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Les tests sont prevu pour ne pas laisser d'entrée en BDD
		testJDBCUtilisateurs();
		testJDBCCategories();
		testJDBCVentes();
		testJDBCEnchere();
		testJDBCretrait();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	//**************************************************************//
	//*** Test des fonctionnalités JDBC de la table Utilisateur  ***//
	//**************************************************************//
	
	
	private void testJDBCUtilisateurs() {
		Utilisateur standardA = new Utilisateur(1, "aArtiste","Artiste","alain","a@laposte.net","0656467616","rue","35000","Rennes","123",155,false);
//		Utilisateur standardB = new Utilisateur("bBoule","Boule","bill","b@wanadoo.fr","0546876548","rue","44000","Nantes","456",200,false);
//		Utilisateur constructeurSansTel = new Utilisateur(21, "cCailloux","Cailloux","console","c@hotmail.fr","rue","75000","Paris","789",845,false);
//		Utilisateur constructeurSansPK = new Utilisateur("dDouble","Double","gras","d@gmail.com","0645789876","avenue des tulipes","88465","Atlantide","4496",800,false);
//		Utilisateur constructeurSansPKSansTel = new Utilisateur("eEtienne","Etoile","plop","etienne@hotmail.fr","rue","35000","Marseille","13000",12000,true);
		
		try {
			DALFactory.getUtilisateurDAOJdbcImpl().insert(standardA);
//			DALFactory.getUtilisateurDAOJdbcImpl().insert(standardB);
//			DALFactory.getUtilisateurDAOJdbcImpl().insert(constructeurSansTel);
//			DALFactory.getUtilisateurDAOJdbcImpl().insert(constructeurSansPK);
//			DALFactory.getUtilisateurDAOJdbcImpl().insert(constructeurSansPKSansTel);
			standardA.setPrenom("Prenom update");
			standardA.setTelephone("0123456789");
			DALFactory.getUtilisateurDAOJdbcImpl().update(standardA);
			DALFactory.getUtilisateurDAOJdbcImpl().select(standardA.getNoUtilisateur());
			DALFactory.getUtilisateurDAOJdbcImpl().selectAll();
			DALFactory.getUtilisateurDAOJdbcImpl().delete(standardA);

		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	//*********************************************************//
	//*** Test des fonctionnalités JDBC de la table Ventes  ***//
	//*********************************************************//
	
	private void testJDBCVentes() {
		try {
			Utilisateur acheteur = DALFactory.getUtilisateurDAOJdbcImpl().select(22);
			Utilisateur vendeur = DALFactory.getUtilisateurDAOJdbcImpl().select(24);
			Categorie categorie = DALFactory.getCategorieDAOJdbcImpl().select(3);
			Timestamp t = Timestamp.valueOf(LocalDateTime.now());
			Vente vente = new Vente("plop","description",t,5000,6000,acheteur,vendeur,categorie);
			DALFactory.getVenteDAOJdbcImpl().insert(vente);
			vente.setDescription("Description modifier");
			DALFactory.getVenteDAOJdbcImpl().update(vente);
			DALFactory.getVenteDAOJdbcImpl().select(vente.getNoVente());
			DALFactory.getVenteDAOJdbcImpl().selectAll();
			DALFactory.getVenteDAOJdbcImpl().delete(vente);

		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
	}
	
		//************************************************************//
		//*** Test des fonctionnalités JDBC de la table Categorie  ***//
		//************************************************************//
	
	private void testJDBCCategories() {
		Categorie categorieSansPK = new Categorie("Cat-1");
		try {
			DALFactory.getCategorieDAOJdbcImpl().insert(categorieSansPK);
			categorieSansPK.setLibelle("modif-test");
			DALFactory.getCategorieDAOJdbcImpl().update(categorieSansPK);
			DALFactory.getCategorieDAOJdbcImpl().select(categorieSansPK.getNoCategorie());
			DALFactory.getCategorieDAOJdbcImpl().selectAll();
			DALFactory.getCategorieDAOJdbcImpl().delete(categorieSansPK);

		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}
	
	
	
	//**********************************************************//
	//*** Test des fonctionnalités JDBC de la table enchere  ***//
	//**********************************************************//
	
	private void testJDBCEnchere() {
		
		Utilisateur acheteur;
		try {
			Utilisateur standardA = new Utilisateur(1, "aArtiste","Artiste","alain","a@laposte.net","0656467616","rue","35000","Rennes","123",155,false);
			Utilisateur standardB = new Utilisateur(1, "aArtiste","Artiste","alain","a@laposte.net","0656467616","rue","35000","Rennes","123",155,false);

			DALFactory.getUtilisateurDAOJdbcImpl().insert(standardA);
			Categorie categorie = DALFactory.getCategorieDAOJdbcImpl().select(3);
			Timestamp t = Timestamp.valueOf(LocalDateTime.now());
			Vente vente = new Vente("plop","description",t,5000,6000,standardA,standardA,categorie);

			DALFactory.getVenteDAOJdbcImpl().insert(vente);
			Enchere enchere1 = new Enchere(t, standardA, vente);
			DALFactory.getEnchereDAOJdbcImpl().insert(enchere1);
			t = Timestamp.valueOf(LocalDateTime.now());
			DALFactory.getEnchereDAOJdbcImpl().update(enchere1);
			DALFactory.getEnchereDAOJdbcImpl().select(enchere1.getUtilisateurEnchere().getNoUtilisateur(), enchere1.getVenteEnchere().getNoVente());
			DALFactory.getEnchereDAOJdbcImpl().selectAll();
			DALFactory.getEnchereDAOJdbcImpl().delete(enchere1);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}
	


	
	//**********************************************************//
	//*** Test des fonctionnalités JDBC de la table retrait  ***//
	//**********************************************************//

	private void testJDBCretrait() {
		try {
			Vente vente = DALFactory.getVenteDAOJdbcImpl().select(5);
			Retrait retrait = new Retrait("rue","codePostal","Ville", vente);
			
			DALFactory.getRetraitDAOJdbcImpl().insert(retrait);
			retrait.setRue("rue-2");
			DALFactory.getRetraitDAOJdbcImpl().update(retrait);
			DALFactory.getRetraitDAOJdbcImpl().select(retrait.getVente().getNoVente());
			DALFactory.getRetraitDAOJdbcImpl().selectAll();
			DALFactory.getRetraitDAOJdbcImpl().delete(retrait);
			
			

			
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
	}


}















