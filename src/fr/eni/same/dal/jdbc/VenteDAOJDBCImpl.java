package fr.eni.same.dal.jdbc;


import java.time.LocalDateTime;
import java.util.List;
import fr.eni.same.bo.Vente;
import fr.eni.same.dal.VenteDAO;
import fr.eni.same.exception.BusinessException;

public class VenteDAOJDBCImpl implements VenteDAO{
	/**
	 * mise en place d'un singleton pour garantir la cohérence des données
	 */
	
	private static VenteDAOJDBCImpl instance;

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


	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//
	
	
	@Override
	public boolean venteVerifNomArticleLongeurMax(String nomArticle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean venteVerifNomArticleLongeurMin(String nomArticle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean venteVerifdescriptionLongeurMax(String description) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean venteVerifdescriptionLongeurMin(String description) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean venteVerifdateFinEnchere(LocalDateTime dateFinEnchere) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean venteVerifPrixInitialPositif(int prixInitial) {
		// TODO Auto-generated method stub
		return false;
	}
}
