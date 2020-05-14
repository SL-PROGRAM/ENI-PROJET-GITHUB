package fr.eni.same.bll;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.same.bll.interfaceManager.SelectMAnagerInterface;
import fr.eni.same.bll.interfaceManager.UtilisateurManagerInterface;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.exception.BllException;

public class UtilisateurManager extends AdresseManager implements UtilisateurManagerInterface, SelectMAnagerInterface<Utilisateur> {
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
	public void RueLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void CodePostalLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void VilleLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PseudoLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PseudoUnique(String pseudo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void NomLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrenomLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		
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
