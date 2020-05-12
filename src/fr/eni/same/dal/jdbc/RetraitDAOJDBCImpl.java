package fr.eni.same.dal.jdbc;

import java.util.List;
import fr.eni.same.bo.Vente;
import fr.eni.same.dal.RetraitDAO;
import fr.eni.same.exception.BusinessException;

public class RetraitDAOJDBCImpl implements RetraitDAO {
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	
	private static RetraitDAOJDBCImpl instance;
	private final int RUE_LONGEUR_MAX = 30;
	private final int RUE_LONGEUR_MIN = 4;
	private final int CP_LONGEUR_MAX = 5;
	private final int CP_LONGEUR_MIN = 5;
	private final int VILLE_LONGEUR_MAX = 30;
	private final int VILLE_LONGEUR_MIN = 4;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private RetraitDAOJDBCImpl() {
    }

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized RetraitDAOJDBCImpl getRetraitDAOJDBCImpl() {
        if(instance == null){
            instance = new RetraitDAOJDBCImpl();
        }
        return instance;
    }
    
	@Override
	public void insert(Vente t) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Vente t) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Vente t) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Vente select(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vente> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/**
	 * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD
	 */
	
	@Override
	public boolean venteVerifRueLongeurMax(String libelle) {
		boolean isUnique = true;
		if(libelle.length() > RUE_LONGEUR_MAX) {
			isUnique = false;
		}
		return isUnique;
	}

	@Override
	public boolean venteVerifRueLongeurMin(String libelle) {
		boolean isUnique = true;
		if(libelle.length() < RUE_LONGEUR_MIN) {
			isUnique = false;
		}
		return isUnique;
	}


	@Override
	public boolean venteVerifVilleLongeurMax(String libelle) {
		boolean isUnique = true;
		if(libelle.length() > VILLE_LONGEUR_MAX) {
			isUnique = false;
		}
		return isUnique;
	}

	@Override
	public boolean venteVerifVilleLongeurMin(String libelle) {
		boolean isUnique = true;
		if(libelle.length() < VILLE_LONGEUR_MIN) {
			isUnique = false;
		}
		return isUnique;
	}

	@Override
	public boolean venteVerifCPLongeurMax(String libelle) {
		boolean isUnique = true;
		if(libelle.length() > CP_LONGEUR_MAX) {
			isUnique = false;
		}
		return isUnique;
	}

	@Override
	public boolean venteVerifCPLongeurMin(String libelle) {
		boolean isUnique = true;
		if(libelle.length() < CP_LONGEUR_MIN) {
			isUnique = false;
		}
		return isUnique;
	}
	
	

}
