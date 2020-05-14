package fr.eni.same.bll;

import java.util.List;

import fr.eni.same.bll.interfaceManager.CategorieManagerInetrface;
import fr.eni.same.bll.interfaceManager.SelectMAnagerInterface;
import fr.eni.same.bo.Categorie;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;
import fr.eni.same.exception.UniqueException;
import fr.eni.same.helpers.FonctionGenerique;

public class CategorieManager implements CategorieManagerInetrface, SelectMAnagerInterface<Categorie> {

	private final int LIBELLE_LONGUEUR_MAX = 30;
	private final int LIBELLE_LONGUEUR_MIN = 4;
	
	@Override
	public void insert(Categorie t) throws BllException {
		if(!FonctionGenerique.isLongeurMax(t.getLibelle(), LIBELLE_LONGUEUR_MAX)) {
			throw new BllException("Le libellé est trop court");
		} else if(!FonctionGenerique.isLongeurMin(t.getLibelle(), LIBELLE_LONGUEUR_MIN)){
			throw new BllException("Le libellé est trop long");
		} else {
			try {
				DALFactory.getCategorieDAOJdbcImpl().insert(t);
			} catch (DALException e) {
				throw new BllException("Erreur d'enregistrement dans la base de donnée");
			}			
		}
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
	public void LibelleUnique(List<Categorie> list, String libelle) throws BllException {
		boolean isUnique = true;
		for (Categorie categorie : list) {
			if(categorie.getLibelle() == libelle) {
				isUnique = false;
				throw new BllException("Cette Catégorie existe déja");
			}
		}
	}


	@Override
	public void LibelleLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		
	}



	

}
