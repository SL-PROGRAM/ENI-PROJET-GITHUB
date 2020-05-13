package fr.eni.same.bll;

import java.util.List;

import fr.eni.same.bll.interfaceManager.CategorieManagerInetrface;
import fr.eni.same.bo.Categorie;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.UniqueException;

public class CategorieManager implements CategorieManagerInetrface {

	private final int LIBELLE_LONGEUR_MAX = 30;
	private final int LIBELLE_LONGEUR_MIN = 4;
	
	
	@Override
	public void insert(Categorie t) throws BllException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(Categorie t) throws BllException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Categorie t) throws BllException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Categorie select(int id) throws BllException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Categorie> selectAll() throws BllException {
		// TODO Auto-generated method stub
		return null;
	}

	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//
	
	
	@Override
	public boolean isLibelleUnique(List<Categorie> list, String libelle) throws UniqueException {
		boolean isUnique = true;
		for (Categorie categorie : list) {
			if(categorie.getLibelle() == libelle) {
				isUnique = false;
				throw new UniqueException("Cette Catégorie existe déja");
			}
		}
		return isUnique;
	}


	@Override
	public boolean isLibelleLongeurCorrect(String libelle) {
		// TODO test longeur min et max avec fonction générique
		return false;
	}


	


	
	
	

}
