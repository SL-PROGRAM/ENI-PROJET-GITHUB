package fr.eni.same.bll;

import java.util.List;

import fr.eni.same.bll.interfaceManager.RetraitManagerInterface;
import fr.eni.same.bll.interfaceManager.SelectMAnagerInterface;
import fr.eni.same.bo.Retrait;
import fr.eni.same.exception.BllException;

public class RetraitManager extends AdresseManager implements RetraitManagerInterface, SelectMAnagerInterface<Retrait> {
	
	@Override
	public void insert(Retrait t) throws BllException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Retrait t) throws BllException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Retrait t) throws BllException {
		// TODO Auto-generated method stub

	}

	@Override
	public Retrait select(int id) throws BllException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Retrait> selectAll() throws BllException {
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

}
