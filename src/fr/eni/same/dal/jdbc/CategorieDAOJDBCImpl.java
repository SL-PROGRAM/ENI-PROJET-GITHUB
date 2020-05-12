package fr.eni.same.dal.jdbc;

import java.util.List;

import fr.eni.same.bo.Categorie;
import fr.eni.same.dal.CategorieDAO;
import fr.eni.same.exception.BusinessException;




public class CategorieDAOJDBCImpl implements CategorieDAO {
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	private static CategorieDAOJDBCImpl instance;
	private final int LIBELLE_LONGEUR_MAX = 30;
	private final int LIBELLE_LONGEUR_MIN = 4;

	


	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private CategorieDAOJDBCImpl() {
    }

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized CategorieDAOJDBCImpl getCategorieDAOJDBCImpl() {
        if(instance == null){
            instance = new CategorieDAOJDBCImpl();
        }
        return instance;
    }
	@Override
	public void insert(Categorie t) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Categorie t) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Categorie t) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Categorie select(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categorie> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//
	
	
	@Override
	public boolean isLibelleUnique(List<Categorie> list, String libelle) {
		boolean isUnique = true;
		for (Categorie categorie : list) {
			if(categorie.getLibelle() == libelle) {
				isUnique = false;
				break;
			}
		}
		return isUnique;
	}

	@Override
	public boolean isLibelleLongeurMax(String libelle) {
		boolean isUnique = true;
		if(libelle.length() > LIBELLE_LONGEUR_MAX) {
			isUnique = false;
		}
		return isUnique;
	}

	@Override
	public boolean isLibelleLongeurMin(String libelle) {
		boolean isUnique = true;
		if(libelle.length() <= LIBELLE_LONGEUR_MIN) {
			isUnique = false;
		}
		return isUnique;
	}

	

	
	

}
