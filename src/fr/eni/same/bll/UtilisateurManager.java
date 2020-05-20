package fr.eni.same.bll;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.same.bo.Utilisateur;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;
import fr.eni.same.helpers.AdresseUtils;
import fr.eni.same.helpers.FonctionGenerique;
/**
 * @author sl
 * @author etienne
 *
 */
public class UtilisateurManager {
	private final int NOM_LONGUEUR_MAX = 30;
	private final int NOM_LONGUEUR_MIN = 4;
	private final int PSEUDO_LONGUEUR_MAX = 30;
	private final int PSEUDO_LONGUEUR_MIN = 4;
	private final int PRENOM_LONGUEUR_MAX = 30;
	private final int PRENOM_LONGUEUR_MIN = 5;
	private final int EMAIL_LONGUEUR_MAX = 30;
	private final int EMAIL_LONGUEUR_MIN = 4;
	private final int TELEPHONE_LONGUEUR_MAX = 15;
	private final int TELEPHONE_LONGUEUR_MIN = 10;
	private final int MOT_DE_PASSE_LONGUEUR_MAX = 30;
	private final int MOT_DE_PASSE_LONGEUR_MIN = 4;
	private final String EMAIL_REGEX = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)";
	private static UtilisateurManager instance;
	private List<Utilisateur> listeUtilisateurs;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 * @throws BllException 
	 */
    private UtilisateurManager() throws BllException {
    	try {
			listeUtilisateurs = DALFactory.getUtilisateurDAOJdbcImpl().selectAll();
		} catch (DALException e) {
			throw new BllException("Impossible de récupérer les données en BDD");
		}
	}

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     * @throws BllException 
     */
    public static synchronized  UtilisateurManager getUtilisateurManager() throws BllException {
        if(instance == null){
            instance = new UtilisateurManager();
        }
        return instance;
    }
	
	
	public void insert(Utilisateur t) throws BllException {
		String msgErreur = controleUpdateAndInsert(t);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		try {
			DALFactory.getUtilisateurDAOJdbcImpl().insert(t);
			listeUtilisateurs.add(t);
			System.out.println("Utilisateur : Insert réalisé.");
		} catch (DALException e) {
			throw new BllException("Impossible d'inserer en base de donnée l'utilisateur");
		}
		
	}

	
	public void update(Utilisateur t) throws BllException {
		String msgErreur = controleUpdateAndInsert(t);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		try {
			DALFactory.getUtilisateurDAOJdbcImpl().update(t);
			for (int i = 0; i < listeUtilisateurs.size(); i++) {
				//Pas de mise a jour Admin et mot de passe
				if(listeUtilisateurs.get(i).getNoUtilisateur() == t.getNoUtilisateur()) {
					listeUtilisateurs.get(i).setCodePostal(t.getCodePostal());
					listeUtilisateurs.get(i).setCredit(t.getCredit());
					listeUtilisateurs.get(i).setEmail(t.getEmail());
					listeUtilisateurs.get(i).setNom(t.getNom());
					listeUtilisateurs.get(i).setPrenom(t.getPrenom());
					listeUtilisateurs.get(i).setPseudo(t.getPseudo());
					listeUtilisateurs.get(i).setRue(t.getRue());
					listeUtilisateurs.get(i).setVille(t.getVille());
					listeUtilisateurs.get(i).setTelephone(t.getTelephone());
				}
			}
			System.out.println("Utilisateur : Update réalisé.");
		} catch (DALException e) {
			throw new BllException("Impossible de modifier en base de donnée l'utilisateur");
		}
	}

	public void updateMotDePasse(Utilisateur t) throws BllException {
		String msgErreur = controleUpdateAndInsert(t);
		String MotDePasse = securisationMotDePass(t.getMotDePasse());
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		try {
			DALFactory.getUtilisateurDAOJdbcImpl().update(t);
			for (int i = 0; i < listeUtilisateurs.size(); i++) {
				//Pas de mise a jour Admin et mot de passe
				if(listeUtilisateurs.get(i).getNoUtilisateur() == t.getNoUtilisateur()) {
					listeUtilisateurs.get(i).setMotDePasse(MotDePasse);
				}
			}
		} catch (DALException e) {
			throw new BllException("Impossible de modifier le mot de passe");
		}
	}
	
	
	
	public void delete(Utilisateur t) throws BllException {
		String msgErreur = controleDelete(t);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		
		if (t.getNoUtilisateur() <= 0) {
			throw new BllException("Erreur référence interdite");
		}
		try {
			DALFactory.getUtilisateurDAOJdbcImpl().delete(t);
			listeUtilisateurs.remove(t);
			System.out.println("Utilisateur Delete réalisé.");
		} catch (DALException e) {
			throw new BllException("Impossible de supprimer en base de donnée l'utilisateur");
		}
	}


	
	public Utilisateur select(int id) throws BllException {
		String msgErreur = noUtilisateurNull(id);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		Utilisateur utilisateur;
		try {
			utilisateur = DALFactory.getUtilisateurDAOJdbcImpl().select(id);
			for(int i = 0; i < listeUtilisateurs.size(); i++) {
				if(listeUtilisateurs.get(i).getNoUtilisateur() == utilisateur.getNoUtilisateur()) {
					System.out.println("Utilisateur : Select réalisé : " + listeUtilisateurs.get(i).toString());					
				}
			}
		} catch (DALException e) {
			throw new BllException("l'utilisateur que vous demandez n'est pas référencé");
		}
		return utilisateur;
	}

	
	public List<Utilisateur> selectAll() throws BllException {
		return listeUtilisateurs;
	}
	
	public String controleUpdateAndInsert(Utilisateur t) throws BllException {
		String msgErreur = "";
		msgErreur += utilisateurNull(t);
		//msgErreur += noUtilisateurNull(t.getNoUtilisateur());
		msgErreur += pseudoLongueurCorrect(t.getPseudo());
		msgErreur += nomLongueurCorrect(t.getNom());
		msgErreur += prenomLongueurCorrect(t.getPrenom());
		msgErreur += emailValide(t.getEmail());
		msgErreur += telephoneLongueurCorrect(t.getTelephone());
		msgErreur += motDePasseValide(t.getMotDePasse());
		msgErreur += creditPositif(t.getCredit());
		msgErreur += AdresseUtils.rueLongueurCorrect(t.getRue());
		msgErreur += AdresseUtils.villeLongueurCorrect(t.getVille());
		msgErreur += AdresseUtils.codePostalLongueurCorrect(t.getCodePostal());		
		return msgErreur; 
	}

	private String controleDelete(Utilisateur t) throws BllException {
		String msgErreur = "";
		msgErreur += utilisateurNull(t);
		msgErreur += noUtilisateurNull(t.getNoUtilisateur());
		return msgErreur;
	}
	
	private String utilisateurNull(Utilisateur utilisateur) throws BllException  {
		String msgErreur = "";
		if (utilisateur == null) {
			msgErreur = ("Erreur : null");
		}
		return msgErreur;
	}
	
	
	private String noUtilisateurNull(int id) throws BllException  {
		String msgErreur = "";
		if (id <= 0) {
			msgErreur = ("Erreur référence interdite");
		}
		return msgErreur;
	}
	
	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//


	
	public String pseudoLongueurCorrect(String libelle) throws BllException {
		String msgErreur = "";
		if(!FonctionGenerique.isLongueurMax(libelle, PSEUDO_LONGUEUR_MAX)) {
			msgErreur = ("Longueur du pseudo trop importante - Longueur maximum : "+ PSEUDO_LONGUEUR_MAX + "caractères\n");
		}
		if(!FonctionGenerique.isLongueurMin(libelle, PSEUDO_LONGUEUR_MIN)) {
			msgErreur = ("Longueur du pseudo trop courte - Longueur minimum : "+ PSEUDO_LONGUEUR_MIN + "caractères\n");
		}
		return msgErreur;				
	}
	
	public String pseudoUnique(String pseudo) throws BllException {
		return pseudo;
		//PAS DANS LA SPEC MAIS PREVU		
	}

	
	public String nomLongueurCorrect(String libelle) throws BllException {
		String msgErreur = "";
		if(libelle != null) {
			if(!FonctionGenerique.isLongueurMax(libelle, NOM_LONGUEUR_MAX)) {
				msgErreur = ("Longeur du nom trop importante - Longueur maximum : "+ NOM_LONGUEUR_MAX + "caractères\n");
			}
			if(!FonctionGenerique.isLongueurMin(libelle, NOM_LONGUEUR_MIN)) {
				msgErreur = ("Longeur du nom trop courte - Longueur minimum : "+ NOM_LONGUEUR_MIN + "caractères\n");
			}			
		}
		return msgErreur;
			
	}

	
	public String prenomLongueurCorrect(String libelle) throws BllException {
		String msgErreur = "";
		if(!FonctionGenerique.isLongueurMax(libelle, PRENOM_LONGUEUR_MAX)) {
			msgErreur = ("Longueur du prenom trop importante - Longueur maximum : "+ PRENOM_LONGUEUR_MAX + "caractères\n");
		}
		if(!FonctionGenerique.isLongueurMin(libelle, PRENOM_LONGUEUR_MIN)) {
			msgErreur = ("Longueur du prenom trop courte - Longueur minimum : "+ PRENOM_LONGUEUR_MIN + "caractères\n");
		}
		return msgErreur;				
	}

	
	public String emailValide(String email) throws BllException {
		String msgErreur = "";
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		if(!FonctionGenerique.isLongueurMax(email, EMAIL_LONGUEUR_MAX)) {
			msgErreur = ("Longueur de l'email trop importante - Longueur maximum : "+ EMAIL_LONGUEUR_MAX + "caractères\n");
		}
		if(!FonctionGenerique.isLongueurMin(email, EMAIL_LONGUEUR_MIN)) {
			msgErreur = ("Longueur du l'email trop courte - Longueur minimum : "+ EMAIL_LONGUEUR_MIN+ "caractères\n");
		}			
		if(!matcher.matches()) {
			msgErreur = ("Ce n'est pas un email valide");
		}
		return msgErreur;
	}
		

	
	public String telephoneLongueurCorrect(String libelle) throws BllException {
		String msgErreur = "";
		if(!FonctionGenerique.isLongueurMax(libelle, TELEPHONE_LONGUEUR_MAX)) {
			msgErreur = ("Longueur du téléphone trop importante - Longueur maximum : "+ TELEPHONE_LONGUEUR_MAX+ "caractères\n");
		}
		if(!FonctionGenerique.isLongueurMin(libelle, TELEPHONE_LONGUEUR_MIN)) {
			msgErreur = ("Longueur du téléphone trop courte - Longueur minimum : "+ TELEPHONE_LONGUEUR_MIN + "caractères\n");
		}
		return msgErreur;				
	}

	
	public String motDePasseValide(String motDePasse) throws BllException {
		String msgErreur = "";
		if(!FonctionGenerique.isLongueurMax(motDePasse, MOT_DE_PASSE_LONGUEUR_MAX)) {
			msgErreur = ("Longueur du mot de passe trop importante - Longueur maximum : "+ MOT_DE_PASSE_LONGUEUR_MAX+ "caractères\n");
		}
		if(!FonctionGenerique.isLongueurMin(motDePasse, MOT_DE_PASSE_LONGEUR_MIN)) {
			msgErreur = ("Longueur du mot de passe trop courte - Longueur minimum : "+ MOT_DE_PASSE_LONGEUR_MIN+ "caractères\n");
		}
		return msgErreur;			
	}
	
	public String motDePasseIdentique(String motDePasse, String motDePasseVerif) {
		String msgErreur = "";
		if(!motDePasse.equals(motDePasseVerif)) {
			msgErreur = "Les mots de passe ne sont pas identiques";
		}
		return msgErreur;
	}

	
	public String creditPositif(int credit) throws BllException {
		String msgErreur = "";
		if (credit < 0) {
			msgErreur = ("credit insuffisant");
		}
		return msgErreur;
	}
	
	

	//*************************************************************************************************//
	// * Implementation des méthodes de Connexion et deconnection l'utilisateur						 * //
	//*************************************************************************************************//


	//Vérification du nom de compte et du mot de passe de l'utilisateur
	
	public String connexion(String identifiant, String motDePasse, HttpSession session) throws BllException {
		boolean isAuthentified= false;
		String msgErreur = "";
		motDePasse = securisationMotDePass(motDePasse);
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(identifiant);
		Utilisateur utilisateur = null;
		if(matcher.matches()) {
			for (int i = 0; i < listeUtilisateurs.size(); i++) {
				if(listeUtilisateurs.get(i).getEmail().equals(identifiant) 
						&& listeUtilisateurs.get(i).getMotDePasse().equals(motDePasse)) {
					isAuthentified = true;
					utilisateur = listeUtilisateurs.get(i);
				}
			}
		}
		else {
			for (int j = 0; j < listeUtilisateurs.size(); j++) {
				if(listeUtilisateurs.get(j).getPseudo().equals(identifiant) 
						&& listeUtilisateurs.get(j).getMotDePasse().equals(motDePasse)) {
					isAuthentified = true;
					utilisateur = listeUtilisateurs.get(j);
				}
			}
			
		}
		if(!isAuthentified) {
			msgErreur = ("L'identifiant et le  mot de passe sont incorrect");
			return msgErreur;
		}else {	
			session.setAttribute("utilisateur", utilisateur);
			return msgErreur;
		}
	}
	
	public String verificationSessionActive(HttpServletRequest request, HttpServletResponse response, HttpSession session, String path) throws ServletException, IOException {
		String msgErreur = "";
		if(request.getParameter("utilisateur") == null) {
			session.setAttribute("path", path);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp");
			rd.forward(request, response);
		}
		return msgErreur;
	}
	
	public boolean deconnexion(HttpSession session) {
		session.invalidate();
		return true;
	}
	
	
	private String securisationMotDePass(String motDePasse) {
		return motDePasse;
	}

	
}
