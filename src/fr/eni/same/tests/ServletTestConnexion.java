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
		 * Test de remplissage de la table utilisateurs 
		 */
		Utilisateur a = new Utilisateur(1,"aArtiste","Artiste","alain","a@laposte.net","0656467616","rue","35000","Rennes","123",155,false);
		Utilisateur b = new Utilisateur(2,"bBoule","Boule","bill","b@wanadoo.fr","0546876548","rue","44000","Nantes","456",200,false);
		Utilisateur c = new Utilisateur(3,"cCailloux","Cailloux","console","c@hotmail.fr","0645879546","rue","75000","Paris","789",845,false);

		try {
			DALFactory.getUtilisateurDAOJdbcImpl().insert(a);
			DALFactory.getUtilisateurDAOJdbcImpl().insert(b);
			DALFactory.getUtilisateurDAOJdbcImpl().insert(c);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}