package fr.eni.same.bll;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import fr.eni.same.bll.interfaceManager.SelectManagerInterface;
import fr.eni.same.bll.interfaceManager.VenteManagerInterface;
import fr.eni.same.bo.Vente;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;
import fr.eni.same.helpers.FonctionGenerique;

public class VenteManager implements VenteManagerInterface, SelectManagerInterface<Vente> {
	private final int NOM_LONGUEUR_MAX = 30;
	private final int NOM_LONGUEUR_MIN = 4;
	private final int DESCRIPTION_LONGUEUR_MAX = 300;
	private final int DESCRIPTION_LONGUEUR_MIN = 5;
	private static VenteManager instance;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private VenteManager() {
	}

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized  VenteManager getVenteManager () {
        if(instance == null){
            instance = new VenteManager();
        }
        return instance;
    }
	
	@Override
	public void insert(Vente t) throws BllException {
		controleUpdateAndInsert(t);
		try {
			DALFactory.getVenteDAOJdbcImpl().insert(t);
		} catch (DALException e) {
			throw new BllException("Impossible d'inserer en base de donnée la vente");
		}
	}

	@Override
	public void update(Vente t) throws BllException {
		controleUpdateAndInsert(t);
		try {
			DALFactory.getVenteDAOJdbcImpl().insert(t);
		} catch (DALException e) {
			throw new BllException("Impossible de modifier en base de donnée la vente");
		}
	}

	@Override
	public void delete(Vente t) throws BllException {
		controleDelete(t);
		try {
			DALFactory.getVenteDAOJdbcImpl().delete(t);
		} catch (DALException e) {
			throw new BllException("Impossible de supprimer en base de donnée la vente");
		}
		
	}

	@Override
	public Vente select(int id) throws BllException {
		noVenteNull(id);
		Vente vente = null;
		try {
			vente = DALFactory.getVenteDAOJdbcImpl().select(id);
		} catch (DALException e) {
			throw new BllException("Impossible de recuperer en base de donnée la vente");
		}
		return vente;
	}

	@Override
	public List<Vente> selectAll() throws BllException {
		List<Vente> listVentes = new ArrayList<Vente>();
		try {
			listVentes = DALFactory.getVenteDAOJdbcImpl().selectAll();
		} catch (DALException e) {
			throw new BllException("Impossible de recupérer en base de donnée les ventes");
		}
		return listVentes;
	}
	
	private void controleUpdateAndInsert(Vente t) throws BllException {
		venteNull(t);
		noVenteNull(t.getNoVente());
		nomArticleLongeurCorrect(t.getNomArticle());
		descriptionLongeurCorrect(t.getDescription());
		dateFinEnchere(t.getDateFinEncheres());
		prixInitialPositif(t.getPrixVente());
	}
	
	private void controleDelete(Vente t) throws BllException {
		venteNull(t);
		noVenteNull(t.getNoVente());
	}
	
	private void venteNull(Vente t) throws BllException  {
		if (t == null) {
			throw new BllException("Erreur : null");
		}
	}
	
	
	private void noVenteNull(int id) throws BllException  {
		if (id <= 0) {
			throw new BllException("Erreur référence interdite");
		}
	}

	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//
	

	@Override
	public void nomArticleLongeurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongueurMax(libelle, NOM_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ NOM_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongueurMin(libelle, NOM_LONGUEUR_MIN)) {
			throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ NOM_LONGUEUR_MIN);
		}
	}

	@Override
	public void descriptionLongeurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongueurMax(libelle, DESCRIPTION_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ DESCRIPTION_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongueurMin(libelle, DESCRIPTION_LONGUEUR_MIN)) {
			throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ DESCRIPTION_LONGUEUR_MIN);
		}		
	}

	@Override
	public void dateFinEnchere(Timestamp dateFinEnchere) throws BllException {
		Timestamp now =  new Timestamp(System.currentTimeMillis());
		int aDay = 24 * 60 * 60 * 1000;
		Timestamp demain = new Timestamp(now.getTime()+aDay);
		if(dateFinEnchere.before(demain)){
			throw new BllException();
		}		
	}

	@Override
	public void prixInitialPositif(int prixInitial) throws BllException {
		if(prixInitial < 0) {
			throw new BllException();
		}
		// TODO Auto-generated method stub
		
	}



	

}
