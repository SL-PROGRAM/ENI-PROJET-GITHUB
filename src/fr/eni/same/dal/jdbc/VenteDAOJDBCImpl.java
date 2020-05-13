package fr.eni.same.dal.jdbc;


import java.util.List;
import fr.eni.same.bo.Vente;
import fr.eni.same.dal.interfaceDAO.VenteDAO;
import fr.eni.same.exception.BusinessException;

public class VenteDAOJDBCImpl implements VenteDAO{
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	
	private static VenteDAOJDBCImpl instance;
	private static final String INSERT="INSERT INTO "
			+ "`ventes`(`nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) "
			+ "VALUES (?,?,?,?,?,?,?)";
	private static final String UPDATE="UPDATE `ventes` SET "
			+ "`nomarticle`=?,`description`=?,`date_fin_encheres`=?,`prix_initial`=?,`prix_vente`=?,`no_utilisateur`=?,`no_categorie`=? WHERE no_vente=? "; 
	private static final String DELETE="DELETE FORM ventes WHERE id = ?"; 
	private static final String SELECT_BY_ID = "SELECT * FROM `ventes` WHERE no_vente=? ";
	private static final String SELECT_ALL = "SELECT * FROM `ventes`";

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private VenteDAOJDBCImpl() {
    }

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized VenteDAOJDBCImpl getVenteDAOJDBCImpl() {
        if(instance == null){
            instance = new VenteDAOJDBCImpl();
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