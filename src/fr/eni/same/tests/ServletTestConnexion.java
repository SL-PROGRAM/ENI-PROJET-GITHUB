package fr.eni.same.tests;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.same.bo.Categorie;
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
//		testJDBCUtilisateurs();
//		testJDBCVentes();
//		testJDBCCategories();
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
			DALFactory.getUtilisateurDAOJdbcImpl().insert(standardB);
			DALFactory.getUtilisateurDAOJdbcImpl().insert(constructeurSansTel);
			DALFactory.getUtilisateurDAOJdbcImpl().insert(constructeurSansPK);
			DALFactory.getUtilisateurDAOJdbcImpl().insert(constructeurSansPKSansTel);
			standardA.setPrenom("Prenom update");
			standardA.setTelephone("0123456789");
			DALFactory.getUtilisateurDAOJdbcImpl().update(standardA);
			DALFactory.getUtilisateurDAOJdbcImpl().delete(standardB);
			Utilisateur u = DALFactory.getUtilisateurDAOJdbcImpl().select(4);
			List<Utilisateur> userList = DALFactory.getUtilisateurDAOJdbcImpl().selectAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	private void testJDBCVentes() {
		Date date = Date.valueOf(LocalDate.now());
		Utilisateur acheteur = new Utilisateur("bBoule","Boule","bill","b@wanadoo.fr","0546876548","rue","44000","Nantes","456",200,false);
		Utilisateur vendeur = new Utilisateur(21, "cCailloux","Cailloux","console","c@hotmail.fr","rue","75000","Paris","789",845,false);
		Categorie categorie = new Categorie(1,"première catégorie");
		Vente v = new Vente(1,"chaussure","Une superbe chaussure",date,1000,2000,acheteur,vendeur,categorie);
		try {
			DALFactory.getVenteDAOJdbcImpl().insert(v);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	private void testJDBCCategories() {
		Categorie c = new Categorie(1,"première catégorie");
		try {
			DALFactory.getCategorieDAOJdbcImpl().insert(c);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}