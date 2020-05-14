package fr.eni.same.bll;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.same.bll.interfaceManager.SelectMAnagerInterface;
import fr.eni.same.bll.interfaceManager.VenteManagerInterface;
import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;

public class VenteManager implements VenteManagerInterface, SelectMAnagerInterface<Vente> {

	@Override
	public void insert(Vente t) throws BllException {
	}

	@Override
	public void update(Vente t) throws BllException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Vente t) throws BllException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vente select(int id) throws BllException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vente> selectAll() throws BllException {
		// TODO Auto-generated method stub
		return null;
	}


	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//
	

	@Override
	public void NomArticleLongeurCorrect(String libelle) throws BllException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DescriptionLongeurCorrect(String libelle) throws BllException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dateFinEnchere(LocalDateTime dateFinEnchere) throws BllException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrixInitialPositif(int prixInitial) throws BllException {
		// TODO Auto-generated method stub
		
	}



	

}
