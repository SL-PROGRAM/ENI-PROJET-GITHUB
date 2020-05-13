package fr.eni.same.dal.jdbc;

import java.util.ArrayList;
import java.util.List;

import fr.eni.same.bo.Categorie;
import fr.eni.same.dal.interfaceDAO.CategorieDAO;
import fr.eni.same.exception.BusinessException;




public class CategorieDAOJDBCImpl implements CategorieDAO {
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	private static CategorieDAOJDBCImpl instance;
	
	private static final String INSERT="INSERT INTO categories(libelle) VALUES(?);";
	private static final String UPDATE="UPDATE categories SET (libelle = ?) WHERE id = ?"; 
	private static final String DELETE="DELETE FORM categories WHERE id = ?"; 
	private static final String SELECT_BY_ID = "SELECT * FROM categories WHERE no_categorie=?";
	private static final String SELECT_ALL = "SELECT * FROM categories";
	
	
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
		List<Categorie> listCategories = new ArrayList<Categorie>();
		
		
		
		
		return listCategories;
	}

	
	
	
	

}
