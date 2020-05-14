package fr.eni.same.bll;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.same.bll.interfaceManager.SelectMAnagerInterface;
import fr.eni.same.bll.interfaceManager.UtilisateurManagerInterface;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.exception.BllException;
import fr.eni.same.helpers.FonctionGenerique;

public class UtilisateurManager extends AdresseManager implements UtilisateurManagerInterface, SelectMAnagerInterface<Utilisateur> {
	private final int NOM_LONGUEUR_MAX = 30;
	private final int NOM_LONGUEUR_MIN = 4;
	private final int PSEUDO_LONGUEUR_MAX = 30;
	private final int PSEUDO_LONGUEUR_MIN = 4;
	private final int PRENOM_LONGUEUR_MAX = 5;
	private final int PRENOM_LONGUEUR_MIN = 5;
	private final int EMAIL_LONGUEUR_MAX = 30;
	private final int EMAIL_LONGUEUR_MIN = 4;
	private final String EMAIL_REGEX = "^(.+)@(.+)$";
	private final int TELEPHONE_LONGUEUR_MAX = 15;
	private final int TELEPHONE_LONGUEUR_MIN = 10;
	private final int MOT_DE_PASSE_LONGUEUR_MAX = 30;
	private final int MOT_DE_PASSE_LONGEUR_MIN = 4;
	
	
	
	
	
	@Override
	public void insert(Utilisateur t) throws BllException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Utilisateur t) throws BllException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Utilisateur t) throws BllException {
		// TODO Auto-generated method stub

	}

	@Override
	public Utilisateur select(int id) throws BllException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> selectAll() throws BllException {
		// TODO Auto-generated method stub
		return null;
	}
	

	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//


	@Override
	public void PseudoLongueurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongeurMax(libelle, PSEUDO_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ PSEUDO_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongeurMax(libelle, PSEUDO_LONGUEUR_MIN)) {
			throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ PSEUDO_LONGUEUR_MIN);
		}				
	}
	@Override
	public void PseudoUnique(String pseudo) throws BllException {
		//PAS DANS LA SPEC MAIS PREVU		
	}

	@Override
	public void NomLongueurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongeurMax(libelle, NOM_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ NOM_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongeurMax(libelle, NOM_LONGUEUR_MIN)) {
			throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ NOM_LONGUEUR_MIN);
		}				
	}

	@Override
	public void PrenomLongueurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongeurMax(libelle, PRENOM_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ PRENOM_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongeurMax(libelle, RUE_LONGUEUR_MIN)) {
			throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ RUE_LONGUEUR_MIN);
		}				
	}

	@Override
	public void EmailValide(String email) throws BllException {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		if(!matcher.matches()) {
			throw new BllException("Ce n'est pas un email valide");
		}; //Return true si test Regex ok
	}
		

	@Override
	public void TelephoneLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void MotDePasseValide(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void CreditPositif(int credit) {
		// TODO Auto-generated method stub
		
	}

}
