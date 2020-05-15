package fr.eni.same.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.same.bll.interfaceManager.SelectManagerInterface;
import fr.eni.same.bll.interfaceManager.UtilisateurManagerInterface;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;
import fr.eni.same.helpers.FonctionGenerique;

public class UtilisateurManager extends AdresseManager implements UtilisateurManagerInterface, SelectManagerInterface<Utilisateur> {
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
	
	@Override
	public void insert(Utilisateur t) throws BllException {
		controleUpdateAndInsert(t);
		try {
			DALFactory.getUtilisateurDAOJdbcImpl().insert(t);
		} catch (DALException e) {
			throw new BllException("Impossible d'inserer en base de donnée l'utilisateur");
		}
		
	}

	@Override
	public void update(Utilisateur t) throws BllException {
		controleUpdateAndInsert(t);
		try {
			DALFactory.getUtilisateurDAOJdbcImpl().update(t);
		} catch (DALException e) {
			throw new BllException("Impossible de modifier en base de donnée l'utilisateur");
		}
	}

	@Override
	public void delete(Utilisateur t) throws BllException {
		controleDelete(t);
		if (t.getNoUtilisateur() <= 0) {
			throw new BllException("Erreur référence interdite");
		}
		try {
			DALFactory.getUtilisateurDAOJdbcImpl().delete(t);
		} catch (DALException e) {
			throw new BllException("Impossible de supprimer en base de donnée l'utilisateur");
		}
	}


	@Override
	public Utilisateur select(int id) throws BllException {
		noUtilisateurNull(id);
		Utilisateur utilisateur;
		try {
			utilisateur = DALFactory.getUtilisateurDAOJdbcImpl().select(id);
			
		} catch (DALException e) {
			throw new BllException("l'utilisateur que vous demandez n'est pas référencé");
		}
		return utilisateur;
	}

	@Override
	public List<Utilisateur> selectAll() throws BllException {
		List<Utilisateur> listUtilisateurs = new ArrayList<Utilisateur>();
		try {
			listUtilisateurs = DALFactory.getUtilisateurDAOJdbcImpl().selectAll();
			
		} catch (DALException e) {
			throw new BllException("erreur de récupération des données");
		}		
		return listUtilisateurs;
	}
	
	private void controleUpdateAndInsert(Utilisateur t) throws BllException {
		utilisateurNull(t);
		noUtilisateurNull(t.getNoUtilisateur());
		pseudoLongueurCorrect(t.getPseudo());
		nomLongueurCorrect(t.getNom());
		prenomLongueurCorrect(t.getPrenom());
		emailValide(t.getEmail());
		telephoneLongueurCorrect(t.getTelephone());
		motDePasseValide(t.getEmail());
		creditPositif(t.getCredit());
		rueLongueurCorrect(t.getRue());
		villeLongueurCorrect(t.getVille());
		codePostalLongueurCorrect(t.getCodePostal());
	}

	private void controleDelete(Utilisateur t) throws BllException {
		utilisateurNull(t);
		noUtilisateurNull(t.getNoUtilisateur());
	}
	
	private void utilisateurNull(Utilisateur utilisateur) throws BllException  {
		if (utilisateur == null) {
			throw new BllException("Erreur : null");
		}
	}
	
	
	private void noUtilisateurNull(int id) throws BllException  {
		if (id <= 0) {
			throw new BllException("Erreur référence interdite");
		}
	}
	
	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//


	@Override
	public void pseudoLongueurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongueurMax(libelle, PSEUDO_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ PSEUDO_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongueurMin(libelle, PSEUDO_LONGUEUR_MIN)) {
			throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ PSEUDO_LONGUEUR_MIN);
		}				
	}
	@Override
	public void pseudoUnique(String pseudo) throws BllException {
		//PAS DANS LA SPEC MAIS PREVU		
	}

	@Override
	public void nomLongueurCorrect(String libelle) throws BllException {
		if(libelle != null) {
			if(!FonctionGenerique.isLongueurMax(libelle, NOM_LONGUEUR_MAX)) {
				throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ NOM_LONGUEUR_MAX);
			}
			if(!FonctionGenerique.isLongueurMin(libelle, NOM_LONGUEUR_MIN)) {
				throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ NOM_LONGUEUR_MIN);
			}			
		}
			
	}

	@Override
	public void prenomLongueurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongueurMax(libelle, PRENOM_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ PRENOM_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongueurMin(libelle, PRENOM_LONGUEUR_MIN)) {
			throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ PRENOM_LONGUEUR_MIN);
		}				
	}

	@Override
	public void emailValide(String email) throws BllException {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		if(!FonctionGenerique.isLongueurMax(email, EMAIL_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ EMAIL_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongueurMin(email, EMAIL_LONGUEUR_MIN)) {
			throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ EMAIL_LONGUEUR_MIN);
		}			
		if(!matcher.matches()) {
			throw new BllException("Ce n'est pas un email valide");
		};
	}
		

	@Override
	public void telephoneLongueurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongueurMax(libelle, TELEPHONE_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ TELEPHONE_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongueurMin(libelle, TELEPHONE_LONGUEUR_MIN)) {
			throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ TELEPHONE_LONGUEUR_MIN);
		}				
	}

	@Override
	public void motDePasseValide(String email) throws BllException {
		if(!FonctionGenerique.isLongueurMax(email, MOT_DE_PASSE_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ MOT_DE_PASSE_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongueurMin(email, MOT_DE_PASSE_LONGEUR_MIN)) {
			throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ MOT_DE_PASSE_LONGEUR_MIN);
		}			
	}

	@Override
	public void creditPositif(int credit) throws BllException {
		if (credit < 0) {
			throw new BllException("credit insufissant");
		}
	}

}
