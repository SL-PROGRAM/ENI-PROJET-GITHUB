package fr.eni.same.tests;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.same.bo.Utilisateur;
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

		/**
		 * Test de remplissage de la table utilisateurs avec tous les constructeurs différents
		 */
		Utilisateur standardA = new Utilisateur(19, "aArtiste","Artiste","alain","a@laposte.net","0656467616","rue","35000","Rennes","123",155,false);
		Utilisateur standardB = new Utilisateur(20, "bBoule","Boule","bill","b@wanadoo.fr","0546876548","rue","44000","Nantes","456",200,false);
		Utilisateur constructeurSansTel = new Utilisateur(21, "cCailloux","Cailloux","console","c@hotmail.fr","rue","75000","Paris","789",845,false);
		Utilisateur constructeurSansPK = new Utilisateur(22,"dDouble","Double","gras","d@gmail.com","0645789876","avenue des tulipes","88465","Atlantide","4496",800,false);
		Utilisateur constructeurSansPKSansTel = new Utilisateur(23,"eEtienne","Etoile","plop","etienne@hotmail.fr","rue","35000","Marseille","13000",12000,true);
		
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
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}