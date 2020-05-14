package fr.eni.same.bll;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.same.bll.interfaceManager.UtilisateurManagerInterface;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.exception.BllException;

public class UtilisateurManager extends AdresseManager implements UtilisateurManagerInterface {
	private final int NOM_LONGUEUR_MAX = 30;
	private final int NOM_LONGUEUR_MIN = 4;
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
	public boolean isPseudoUnique(String pseudo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmailValide(String email) {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches(); //Return true si test Regex ok
	}

	@Override
	public boolean isCreditPositif(int credit) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isMotDePasseValide(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPseudoLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNomLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPrenomLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTelephoneLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		return false;
	}

}
