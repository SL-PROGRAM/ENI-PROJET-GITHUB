package fr.eni.same.bll;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import fr.eni.same.bo.Vente;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;
import fr.eni.same.helpers.FonctionGenerique;

public class VenteManager  {
	private final int NOM_LONGUEUR_MAX = 30;
	private final int NOM_LONGUEUR_MIN = 4;
	private final int DESCRIPTION_LONGUEUR_MAX = 300;
	private final int DESCRIPTION_LONGUEUR_MIN = 5;
	private static VenteManager instance;
	private static List<Vente> listVentes = new ArrayList<Vente>();

	

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private VenteManager() {
	}

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     * @throws BllException 
     */
    public static synchronized  VenteManager getVenteManager () throws BllException {
        if(instance == null){
            instance = new VenteManager();
            listVentes = VenteManager.getVenteManager().selectAll();
        }
        return instance;
    }
	
	
	public void insert(Vente t) throws BllException {
		String msgErreur = controleUpdateAndInsert(t);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		try {
			DALFactory.getVenteDAOJdbcImpl().insert(t);
		} catch (DALException e) {
			throw new BllException("Impossible d'inserer en base de donnée la vente");
		}
	}

	
	public void update(Vente t) throws BllException {
		String msgErreur = controleUpdateAndInsert(t);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		try {
			DALFactory.getVenteDAOJdbcImpl().insert(t);
		} catch (DALException e) {
			throw new BllException("Impossible de modifier en base de donnée la vente");
		}
	}
	

	
	public Vente updateAcheteur(Vente t) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void delete(Vente t) throws BllException {
		String msgErreur = controleDelete(t);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		try {
			DALFactory.getVenteDAOJdbcImpl().delete(t);
		} catch (DALException e) {
			throw new BllException("Impossible de supprimer en base de donnée la vente");
		}
		
	}

	
	public Vente select(int id) throws BllException {
		String msgErreur = noVenteNull(id);
		if(!msgErreur.equals("")) {
			throw new BllException(msgErreur);
		};
		Vente vente = null;
		try {
			vente = DALFactory.getVenteDAOJdbcImpl().select(id);
		} catch (DALException e) {
			throw new BllException("Impossible de recuperer en base de donnée la vente");
		}
		return vente;
	}

	
	public List<Vente> selectAll() throws BllException {
		List<Vente> listVentes = new ArrayList<Vente>();
		try {
			listVentes = DALFactory.getVenteDAOJdbcImpl().selectAll();
		} catch (DALException e) {
			throw new BllException("Impossible de recupérer en base de donnée les ventes");
		}
		return listVentes;
	}
	
		//***********************************************************************************************//
		// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
		//***********************************************************************************************//
		
	
	private String controleUpdateAndInsert(Vente t) throws BllException {
		String msgErreur = "";
		msgErreur += venteNull(t);
		msgErreur += noVenteNull(t.getNoVente());
		msgErreur += nomArticleLongeurCorrect(t.getNomArticle());
		msgErreur += descriptionLongeurCorrect(t.getDescription());
		msgErreur += dateFinEnchere(t.getDateFinEncheres());
		msgErreur += prixInitialPositif(t.getPrixVente());
		
		return msgErreur;
	}
	
	private String controleDelete(Vente t) throws BllException {
		String msgErreur = "";
		msgErreur += venteNull(t);
		msgErreur += noVenteNull(t.getNoVente());
		
		return msgErreur;
	}
	
	private String venteNull(Vente t) throws BllException  {
		String msgErreur = "";
		if (t == null) {
			msgErreur = ("Erreur : null");
		}
		return msgErreur;
	}
	
	
	private String noVenteNull(int id) throws BllException  {
		String msgErreur = "";
		if (id <= 0) {
			msgErreur = ("Erreur référence interdite");
		}
		return msgErreur;
		
	}

	

	
	public String nomArticleLongeurCorrect(String libelle) throws BllException {
		String msgErreur = "";
		if(!FonctionGenerique.isLongeurMax(libelle, NOM_LONGUEUR_MAX)) {
			msgErreur = ("Longeur du nom trop importante - Longueur maximum : "+ NOM_LONGUEUR_MAX + "caractères\n");
		}
		if(!FonctionGenerique.isLongeurMax(libelle, NOM_LONGUEUR_MIN)) {
			msgErreur = ("Longeur du nom trop importante - Longueur minimum : "+ NOM_LONGUEUR_MIN + "caractères\n");
		}
		return msgErreur;
	}

	
	public String descriptionLongeurCorrect(String libelle) throws BllException {
		String msgErreur = "";
		if(!FonctionGenerique.isLongeurMax(libelle, DESCRIPTION_LONGUEUR_MAX)) {
			msgErreur = ("Longeur du nom trop importante - Longueur maximum : "+ DESCRIPTION_LONGUEUR_MAX + "caractères\n");
		}
		if(!FonctionGenerique.isLongeurMax(libelle, DESCRIPTION_LONGUEUR_MIN)) {
			msgErreur = ("Longeur du nom trop importante - Longueur minimum : "+ DESCRIPTION_LONGUEUR_MIN + "caractères\n");
		}
		return msgErreur;		
	}

	
	public String dateFinEnchere(Timestamp dateFinEnchere) throws BllException {
		String msgErreur = "";
		Timestamp now =  new Timestamp(System.currentTimeMillis());
		int aDay = 24 * 60 * 60 * 1000;
		Timestamp demain = new Timestamp(now.getTime()+aDay);
		if(dateFinEnchere.before(demain)){
			msgErreur = ("Il faut une durée minimun de 24h pour une enchère");
		}
		return msgErreur;		
	}

	
	public String prixInitialPositif(int prixInitial) throws BllException {
		String msgErreur = "";
		if(prixInitial < 0) {
			msgErreur = ("Le prix de vente minium est de 0");
		}
		return msgErreur;
		
	}


	

}
