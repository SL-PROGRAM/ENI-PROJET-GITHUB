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

}
