package fr.eni.same.bll;

import java.util.List;

import fr.eni.same.bll.interfaceManager.UtilisateurManagerInterface;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.exception.BllException;

public class UtilisateurManager implements UtilisateurManagerInterface {

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCreditPositif(int credit) {
		// TODO Auto-generated method stub
		return false;
	}

}
