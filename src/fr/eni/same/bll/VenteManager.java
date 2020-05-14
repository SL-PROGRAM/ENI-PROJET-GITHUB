package fr.eni.same.bll;

import java.sql.Timestamp;
import java.util.List;
import fr.eni.same.bll.interfaceManager.SelectMAnagerInterface;
import fr.eni.same.bll.interfaceManager.VenteManagerInterface;
import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;
import fr.eni.same.helpers.FonctionGenerique;

public class VenteManager implements VenteManagerInterface, SelectMAnagerInterface<Vente> {
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
	}

	@Override
	public void update(Vente t) throws BllException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Vente t) throws BllException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vente select(int id) throws BllException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vente> selectAll() throws BllException {
		// TODO Auto-generated method stub
		return null;
	}


	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//
	

	@Override
	public void NomArticleLongeurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongeurMax(libelle, NOM_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ NOM_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongeurMax(libelle, NOM_LONGUEUR_MIN)) {
			throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ NOM_LONGUEUR_MIN);
		}
	}

	@Override
	public void DescriptionLongeurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongeurMax(libelle, DESCRIPTION_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ DESCRIPTION_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongeurMax(libelle, DESCRIPTION_LONGUEUR_MIN)) {
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
	public void PrixInitialPositif(int prixInitial) throws BllException {
		if(prixInitial < 0) {
			throw new BllException();
		}
		// TODO Auto-generated method stub
		
	}



	

}
