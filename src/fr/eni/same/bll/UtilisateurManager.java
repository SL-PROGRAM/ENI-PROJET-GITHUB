package fr.eni.same.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.same.bo.Utilisateur;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;
import fr.eni.same.helpers.AdresseUtils;
import fr.eni.same.helpers.FonctionGenerique;

public class UtilisateurManager extends AdresseUtils  {
	private final int NOM_LONGUEUR_MAX = 30;
	private final int NOM_LONGUEUR_MIN = 4;
	private final int PSEUDO_LONGUEUR_MAX = 30;
	private final int PSEUDO_LONGUEUR_MIN = 4;
	private final int PRENOM_LONGUEUR_MAX = 5;
	private final int PRENOM_LONGUEUR_MIN = 5;
	private final int EMAIL_LONGUEUR_MAX = 30;
	private final int EMAIL_LONGUEUR_MIN = 4;
	private final int TELEPHONE_LONGUEUR_MAX = 15;
	private final int TELEPHONE_LONGUEUR_MIN = 10;
	private final int MOT_DE_PASSE_LONGUEUR_MAX = 30;
	private final int MOT_DE_PASSE_LONGEUR_MIN = 4;
	private final String EMAIL_REGEX = "^(.+)@(.+)$";
	private static UtilisateurManager instance;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private UtilisateurManager() {
	}

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized  UtilisateurManager getUtilisateurManager() {
        if(instance == null){
            instance = new UtilisateurManager();
        }
        return instance;
    }
	
	
	public void insert(Utilisateur t) throws BllException {
		String msgErreur =controleUpdateAndInsert(t);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		try {
			DALFactory.getUtilisateurDAOJdbcImpl().insert(t);
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
		} catch (DALException e) {
			throw new BllException("Impossible de modifier en base de donnée l'utilisateur");
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
			
		} catch (DALException e) {
			throw new BllException("l'utilisateur que vous demandez n'est pas référencé");
		}
		return utilisateur;
	}

	
	public List<Utilisateur> selectAll() throws BllException {
		List<Utilisateur> listUtilisateurs = new ArrayList<Utilisateur>();
		try {
			listUtilisateurs = DALFactory.getUtilisateurDAOJdbcImpl().selectAll();
			
		} catch (DALException e) {
			throw new BllException("erreur de récupération des données");
		}		
		return listUtilisateurs;
	}
	
	private String controleUpdateAndInsert(Utilisateur t) throws BllException {
		String msgErreur = "";
		msgErreur += utilisateurNull(t);
		msgErreur += noUtilisateurNull(t.getNoUtilisateur());
		msgErreur += pseudoLongueurCorrect(t.getPseudo());
		msgErreur += nomLongueurCorrect(t.getNom());
		msgErreur += prenomLongueurCorrect(t.getPrenom());
		msgErreur += emailValide(t.getEmail());
		msgErreur += telephoneLongueurCorrect(t.getTelephone());
		msgErreur += motDePasseValide(t.getEmail());
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
			msgErreur = ("Longeur du nom trop importante - Longueur maximum : "+ PSEUDO_LONGUEUR_MAX + "caractères\n");
		}
		if(!FonctionGenerique.isLongueurMin(libelle, PSEUDO_LONGUEUR_MIN)) {
			msgErreur = ("Longeur du nom trop importante - Longueur minimum : "+ PSEUDO_LONGUEUR_MIN + "caractères\n");
		}
		return libelle;				
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
				msgErreur = ("Longeur du nom trop importante - Longueur minimum : "+ NOM_LONGUEUR_MIN + "caractères\n");
			}			
		}
		return libelle;
			
	}

	
	public String prenomLongueurCorrect(String libelle) throws BllException {
		String msgErreur = "";
		if(!FonctionGenerique.isLongueurMax(libelle, PRENOM_LONGUEUR_MAX)) {
			msgErreur = ("Longeur du nom trop importante - Longueur maximum : "+ PRENOM_LONGUEUR_MAX + "caractères\n");
		}
		if(!FonctionGenerique.isLongueurMin(libelle, PRENOM_LONGUEUR_MIN)) {
			msgErreur = ("Longeur du nom trop importante - Longueur minimum : "+ PRENOM_LONGUEUR_MIN + "caractères\n");
		}
		return libelle;				
	}

	
	public String emailValide(String email) throws BllException {
		String msgErreur = "";
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		if(!FonctionGenerique.isLongueurMax(email, EMAIL_LONGUEUR_MAX)) {
			msgErreur = ("Longeur du nom trop importante - Longueur maximum : "+ EMAIL_LONGUEUR_MAX + "caractères\n");
		}
		if(!FonctionGenerique.isLongueurMin(email, EMAIL_LONGUEUR_MIN)) {
			msgErreur = ("Longeur du nom trop importante - Longueur minimum : "+ EMAIL_LONGUEUR_MIN+ "caractères\n");
		}			
		if(!matcher.matches()) {
			msgErreur = ("Ce n'est pas un email valide");
		};
		return email;
	}
		

	
	public String telephoneLongueurCorrect(String libelle) throws BllException {
		String msgErreur = "";
		if(!FonctionGenerique.isLongueurMax(libelle, TELEPHONE_LONGUEUR_MAX)) {
			msgErreur = ("Longeur du nom trop importante - Longueur maximum : "+ TELEPHONE_LONGUEUR_MAX+ "caractères\n");
		}
		if(!FonctionGenerique.isLongueurMin(libelle, TELEPHONE_LONGUEUR_MIN)) {
			msgErreur = ("Longeur du nom trop importante - Longueur minimum : "+ TELEPHONE_LONGUEUR_MIN + "caractères\n");
		}
		return libelle;				
	}

	
	public String motDePasseValide(String email) throws BllException {
		String msgErreur = "";
		if(!FonctionGenerique.isLongueurMax(email, MOT_DE_PASSE_LONGUEUR_MAX)) {
			msgErreur = ("Longeur du nom trop importante - Longueur maximum : "+ MOT_DE_PASSE_LONGUEUR_MAX+ "caractères\n");
		}
		if(!FonctionGenerique.isLongueurMin(email, MOT_DE_PASSE_LONGEUR_MIN)) {
			msgErreur = ("Longeur du nom trop importante - Longueur minimum : "+ MOT_DE_PASSE_LONGEUR_MIN+ "caractères\n");
		}
		return email;			
	}

	
	public String creditPositif(int credit) throws BllException {
		String msgErreur = "";
		if (credit < 0) {
			msgErreur = ("credit insufissant");
		}
		return msgErreur;
	}

	//Vérification du nom de compte et du mot de passe de l'utilisateur
	public String verificationConnexion() {
		//TODO
		return null;
	}
	
}
