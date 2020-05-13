package fr.eni.same.dal.jdbc;

import java.util.List;

import fr.eni.same.bo.Retrait;
import fr.eni.same.dal.interfaceDAO.RetraitDAO;
import fr.eni.same.exception.BusinessException;

public class RetraitDAOJDBCImpl implements RetraitDAO {
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	
	private static RetraitDAOJDBCImpl instance;
	private static final String INSERT="INSERT INTO retraits(rue, code_postal, ville) VALUES(?, ?,?);";
	private static final String UPDATE="UPDATE `retraits` SET `rue`=?,`code_postal`=?,`ville`=? WHERE ?"; 
	private static final String DELETE="DELETE FORM retraits WHERE id = ?"; 
	private static final String SELECT_BY_ID = "SELECT * FROM retraits WHERE `no_vente`=?";
	private static final String SELECT_ALL = "SELECT * FROM retraits";

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


}
