package fr.eni.same.tests;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.same.bo.Categorie;
import fr.eni.same.bo.Enchere;
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
		testJDBCVentes();
		testJDBCUtilisateurs();
		testJDBCCategories();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * Test des fonctionnalités JDBC de la table utilisateurs
	 */
	private void testJDBCUtilisateurs() {
		Utilisateur standardA = new Utilisateur(1, "aArtiste","Artiste","alain","a@laposte.net","0656467616","rue","35000","Rennes","123",155,false);
		Utilisateur standardB = new Utilisateur("bBoule","Boule","bill","b@wanadoo.fr","0546876548","rue","44000","Nantes","456",200,false);
		Utilisateur constructeurSansTel = new Utilisateur(21, "cCailloux","Cailloux","console","c@hotmail.fr","rue","75000","Paris","789",845,false);
		Utilisateur constructeurSansPK = new Utilisateur("dDouble","Double","gras","d@gmail.com","0645789876","avenue des tulipes","88465","Atlantide","4496",800,false);
		Utilisateur constructeurSansPKSansTel = new Utilisateur("eEtienne","Etoile","plop","etienne@hotmail.fr","rue","35000","Marseille","13000",12000,true);
		
		try {
			DALFactory.getUtilisateurDAOJdbcImpl().insert(standardA);
//			DALFactory.getUtilisateurDAOJdbcImpl().insert(standardB);
//			DALFactory.getUtilisateurDAOJdbcImpl().insert(constructeurSansTel);
//			DALFactory.getUtilisateurDAOJdbcImpl().insert(constructeurSansPK);
//			DALFactory.getUtilisateurDAOJdbcImpl().insert(constructeurSansPKSansTel);
//			standardA.setPrenom("Prenom update");
//			standardA.setTelephone("0123456789");
//			DALFactory.getUtilisateurDAOJdbcImpl().update(standardA);
//			DALFactory.getUtilisateurDAOJdbcImpl().delete(standardB);
//			Utilisateur u = DALFactory.getUtilisateurDAOJdbcImpl().select(4);
//			List<Utilisateur> userList = DALFactory.getUtilisateurDAOJdbcImpl().selectAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void testJDBCVentes() {
		try {
			Utilisateur acheteur = DALFactory.getUtilisateurDAOJdbcImpl().select(22);
			Utilisateur vendeur = DALFactory.getUtilisateurDAOJdbcImpl().select(24);
			Categorie categorie = DALFactory.getCategorieDAOJdbcImpl().select(3);
			Timestamp t = Timestamp.valueOf(LocalDateTime.now());
			Vente vente = new Vente("plop","description",t,5000,6000,acheteur,vendeur,categorie);
			DALFactory.getVenteDAOJdbcImpl().insert(vente);
			DALFactory.getVenteDAOJdbcImpl().delete(vente);
			DALFactory.getVenteDAOJdbcImpl().select(9);
			DALFactory.getVenteDAOJdbcImpl().selectAll();
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Test des fonctionnalités JDBC de la table Categorie
	 */
	private void testJDBCCategories() {
		Categorie categorieSansPK = new Categorie("Cat-1");
		Categorie categorieAvecPK = new Categorie(1, "Cat-2");
		
		
		
		try {
			DALFactory.getCategorieDAOJdbcImpl().insert(categorieSansPK);
			DALFactory.getCategorieDAOJdbcImpl().insert(categorieAvecPK);
			System.out.println(categorieAvecPK);
			System.out.println(categorieSansPK);
			categorieAvecPK.setLibelle("modif-test");
			System.out.println(categorieAvecPK);
			DALFactory.getCategorieDAOJdbcImpl().update(categorieAvecPK);
			DALFactory.getCategorieDAOJdbcImpl().delete(categorieSansPK);
			Categorie test = DALFactory.getCategorieDAOJdbcImpl().select(2);
			List<Categorie> categoriesList = DALFactory.getCategorieDAOJdbcImpl().selectAll();
		} catch (BusinessException e) {
			// TODO: handle exception
		}

	}
	
	
	
	/**
	 * Test des fonctionnalités JDBC de la table Enchere
	 */
	
	private void testJDBCEnchere() {
		Utilisateur standardA = new Utilisateur("aArtiste","Artiste","alain","a@laposte.net","0656467616","rue","35000","Rennes","123",155,false);
//		Vente vente = new Vente(1, "1", "vente", Timestamp.valueOf(LocalDateTime.now()), );
//		Enchere enchere1 = new Enchere(Timestamp.valueOf(LocalDateTime.now()), standardA, );
	}
	
}