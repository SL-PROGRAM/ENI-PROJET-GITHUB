package fr.eni.same.bll;

import java.util.List;

import fr.eni.same.bo.Categorie;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;
import fr.eni.same.helpers.FonctionGenerique;

public class CategorieManager{

	private final int LIBELLE_LONGUEUR_MAX = 30;
	private final int LIBELLE_LONGUEUR_MIN = 4;
	private static CategorieManager instance;
	private List<Categorie> listeCategorie;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
	private CategorieManager() {
		try {
			listeCategorie = selectAll();
		} catch (BllException e) {
			e.printStackTrace();
		}
	}

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized  CategorieManager getCategorieManager () {
        if(instance == null){
            instance = new CategorieManager();
        }
        return instance;
    }
    
	
	public void insert(Categorie t) throws BllException {
		String errorMsg = "";
		errorMsg += libelleLongueurCorrect(t.getLibelle());
		errorMsg += libelleUnique(listeCategorie, t.getLibelle());
		if(errorMsg.equalsIgnoreCase("")) {
			try {
				DALFactory.getCategorieDAOJdbcImpl().insert(t);
				listeCategorie = DALFactory.getCategorieDAOJdbcImpl().selectAll();
			} catch (DALException e) {
				e.printStackTrace();
			}
		}else {
			throw new BllException(errorMsg);
		}
	}

	
	public void update(Categorie t) throws BllException {
		String errorMsg = "";
		errorMsg += libelleLongueurCorrect(t.getLibelle());
		errorMsg += libelleUnique(listeCategorie, t.getLibelle());
		if(errorMsg.equalsIgnoreCase("")) {
			try {
				DALFactory.getCategorieDAOJdbcImpl().update(t);
				listeCategorie = DALFactory.getCategorieDAOJdbcImpl().selectAll();
			} catch (DALException e) {
				e.printStackTrace();
			}
		}else {
			throw new BllException(errorMsg);
		}
	}

	
	public void delete(Categorie t) throws BllException {
		try {
			DALFactory.getCategorieDAOJdbcImpl().delete(t);
			listeCategorie = DALFactory.getCategorieDAOJdbcImpl().selectAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}


	
	public Categorie select(int id) throws BllException {
		try {
			return DALFactory.getCategorieDAOJdbcImpl().select(id);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return null;
	}


	
	public List<Categorie> selectAll() throws BllException {
		try {
			List<Categorie> listeCategories = DALFactory.getCategorieDAOJdbcImpl().selectAll();
			return listeCategories;
		} catch (DALException e) {
			e.printStackTrace();
		}
		return null;
	}

	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//
	
	
	
	public String libelleUnique(List<Categorie> list, String libelle) throws BllException {
		String resultat = "";
		boolean isUnique = true;
		for (Categorie categorie : list) {
			if (categorie.getLibelle() == libelle) {
				resultat = "Cette catégorie existe déjà";
			}
		}
		return resultat;
	}


	
	public String libelleLongueurCorrect(String libelle) {
		String resultat = "";
		return resultat;
	}
}