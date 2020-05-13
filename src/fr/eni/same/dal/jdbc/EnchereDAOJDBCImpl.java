package fr.eni.same.dal.jdbc;

import java.util.List;

import fr.eni.same.bo.Enchere;
import fr.eni.same.dal.interfaceDAO.EnchereDAO;
import fr.eni.same.exception.BusinessException;

public class EnchereDAOJDBCImpl implements EnchereDAO {
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	
	private static EnchereDAOJDBCImpl instance;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private EnchereDAOJDBCImpl() {
    }

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized EnchereDAOJDBCImpl getEnchereDAOJDBCImpl() {
        if(instance == null){
            instance = new EnchereDAOJDBCImpl();
        }
        return instance;
    }
	
	@Override
	public void insert(Enchere t) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Enchere t) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Enchere t) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Enchere select(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//
	

}
