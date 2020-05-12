package fr.eni.same.dal.jdbc;

import java.util.List;

import fr.eni.same.bo.Retrait;
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
	public void insert(Retrait t) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Retrait t) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Retrait t) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Retrait select(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Retrait> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//
	
	

}
