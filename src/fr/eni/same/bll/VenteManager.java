package fr.eni.same.bll;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.same.bo.Categorie;
import fr.eni.same.bo.Enchere;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.bo.Vente;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;
import fr.eni.same.helpers.FonctionGenerique;

/**
 * BLL - Classe qui contient les méthodes de gestion des Ventes
 * @author sl
 * @author etienne
 */
public class VenteManager  {
	private final int NOM_LONGUEUR_MAX = 30;
	private final int NOM_LONGUEUR_MIN = 4;
	private final int DESCRIPTION_LONGUEUR_MAX = 300;
	private final int DESCRIPTION_LONGUEUR_MIN = 5;
	private static VenteManager instance;
	private List<Vente> listVentes;

	

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 * @throws BllException 
	 */
    private VenteManager() throws BllException {
    	if(listVentes == null) {
    		try {
				listVentes = DALFactory.getVenteDAOJdbcImpl().selectAll();
				listVentes = setVenteUtilisateursAcheteurs();
			} catch (DALException e) {
				throw new BllException("selectAll");
			}
    	}			
	}

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     * @throws BllException 
     */
    public static synchronized  VenteManager getVenteManager () throws BllException {
        if(instance == null){
            instance = new VenteManager();      
        }
        return instance;
    }
	
	
	public void insert(Vente t) throws BllException {
		String msgErreur = controleUpdateAndInsert(t);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		else {
			try {
				DALFactory.getVenteDAOJdbcImpl().insert(t);
				listVentes.add(t);
			} catch (DALException e) {
				throw new BllException("Impossible d'inserer en base de donnée la vente");
			}
		}
		
	}

	
	public void update(Vente t) throws BllException {
		String msgErreur = controleUpdateAndInsert(t);
		msgErreur += noVenteNull(t.getNoVente());
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		else {
			try {
				DALFactory.getVenteDAOJdbcImpl().update(t);
				for (int i = 0; i < listVentes.size(); i++) {
					if(listVentes.get(i).getNoVente() == t.getNoVente()) {
						listVentes.get(i).setCategorie(t.getCategorie());
						listVentes.get(i).setDateFinEncheres(t.getDateFinEncheres());
						listVentes.get(i).setDescription(t.getDescription());
						listVentes.get(i).setMiseAPrix(t.getMiseAPrix());
						listVentes.get(i).setNomArticle(t.getNomArticle());
						listVentes.get(i).setPrixVente(t.getMiseAPrix());
						listVentes.get(i).setUtilisateurAcheteur(t.getUtilisateurAcheteur());
						listVentes.get(i).setUtilisateurVendeur(t.getUtilisateurVendeur());
					}
				}
			} catch (DALException e) {
				throw new BllException("Impossible de modifier en base de donnée la vente");
			}
		}
		
	}
	
	

	
	public Vente updateAcheteur(Vente t) throws BllException {
		String msgErreur = controleUpdateAndInsert(t);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		else {
			try {
				DALFactory.getVenteDAOJdbcImpl().updateAcheteur(t);
				for (int i = 0; i < listVentes.size(); i++) {
					if(listVentes.get(i).getNoVente() == t.getNoVente()) {
						listVentes.get(i).setUtilisateurAcheteur(t.getUtilisateurAcheteur());
					}
				}
			} catch (DALException e) {
				throw new BllException("Impossible de modifier l'acheteur en base de donnée la vente");
			}
		}		
		return t;
	}

	
	public void delete(Vente t) throws BllException {
		String msgErreur = controleDelete(t);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		else {
			try {
				DALFactory.getVenteDAOJdbcImpl().delete(t);
				for (int i = 0; i < listVentes.size(); i++) {
					if(listVentes.get(i).getNoVente() == t.getNoVente()) {
						listVentes.remove(i);
					}
				}
			}
			catch (DALException e) {
				throw new BllException("Impossible de supprimer en base de donnée la vente");
			}
		}
		
		
	}

	
	public Vente select(int id) throws BllException {
		String msgErreur = noVenteNull(id);
		Vente vente = null;
		if(!msgErreur.equals("")) {
			throw new BllException(msgErreur);
		}
		else {		
			for (int i = 0; i < listVentes.size(); i++) {
				if(listVentes.get(i).getNoVente() == id) {
					vente = listVentes.get(i);
				}
			}
			if(vente == null) {
				throw new BllException("La vente ciblée n'existe pas");
			}
		}
		return vente;
	}
	
	public List<Vente> selectAll() throws BllException {
		return listVentes;
	}
	
	public List<Vente> setVenteUtilisateursAcheteurs(){
		try {
			List<Enchere> listeEncheres = EnchereManager.getEnchereManager().selectAll();
			for(int i = 0; i < listeEncheres.size(); i++) {
				for(int j = 0; j < listVentes.size(); j++) {
					if(listVentes.get(j).getNoVente() == listeEncheres.get(i).getVenteEnchere().getNoVente()) {
						listVentes.get(j).setUtilisateurAcheteur(listeEncheres.get(i).getUtilisateurEnchere());
					}
				}
			}
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listVentes;
	}
	public List<Vente> selectByMotCle(String motCle) throws BllException{
		List<Vente> aRetourner = new ArrayList<Vente>();
		
		List<Vente> listeVentes = VenteManager.getVenteManager().selectAll();
		for (Vente vente : listeVentes) {
			if (vente.getDescription().toLowerCase().contains(motCle.toLowerCase())
					|| vente.getNomArticle().toLowerCase().contains(motCle.toLowerCase())) {
				aRetourner.add(vente);
			}
		}
		return aRetourner;
	}
	
	public List<Vente> selectByCategorie(Categorie categorie) throws BllException{
		List<Vente> aRetourner = new ArrayList<Vente>();
		
		List<Vente> listeVentes = VenteManager.getVenteManager().selectAll();
		for (Vente vente : listeVentes) {
			if (vente.getCategorie().getNoCategorie() == categorie.getNoCategorie()) {
				System.out.println(vente.toString());
				aRetourner.add(vente);
			}
		}
		return aRetourner;
	}
	
	
		//***********************************************************************************************//
		// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
		//***********************************************************************************************//
		
	
	private String controleUpdateAndInsert(Vente t) throws BllException {
		String msgErreur = "";
		msgErreur += venteNull(t);
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
		if(!FonctionGenerique.isLongueurMax(libelle, NOM_LONGUEUR_MAX)) {
			msgErreur = ("Longueur de l'article trop importante - Longueur maximum : "+ NOM_LONGUEUR_MAX + "caractères\n");
		}
		if(!FonctionGenerique.isLongueurMin(libelle, NOM_LONGUEUR_MIN)) {
			msgErreur = ("Longueur de l'article trop courte - Longueur minimum : "+ NOM_LONGUEUR_MIN + "caractères\n");
		}
		return msgErreur;
	}

	
	public String descriptionLongeurCorrect(String libelle) throws BllException {
		String msgErreur = "";
		if(!FonctionGenerique.isLongueurMax(libelle, DESCRIPTION_LONGUEUR_MAX)) {
			msgErreur = ("Longeur de la description trop importante - Longueur maximum : "+ DESCRIPTION_LONGUEUR_MAX + "caractères\n");
		}
		if(!FonctionGenerique.isLongueurMin(libelle, DESCRIPTION_LONGUEUR_MIN)) {
			msgErreur = ("Longeur de la description trop courte - Longueur minimum : "+ DESCRIPTION_LONGUEUR_MIN + "caractères\n");
		}
		return msgErreur;		
	}

	
	public String dateFinEnchere(Timestamp dateFinEnchere) throws BllException {
		String msgErreur = "";
		Timestamp now =  new Timestamp(System.currentTimeMillis());
		int aDay = 24 * 60 * 60 * 1000;
		Timestamp demain = new Timestamp(now.getTime()+aDay);
		if(dateFinEnchere.before(demain)){
			msgErreur = ("Il faut une durée minimum de 24h pour une enchère");
		}
		return msgErreur;		
	}

	
	public String prixInitialPositif(int prixInitial) throws BllException {
		String msgErreur = "";
		if(prixInitial < 0) {
			msgErreur = ("Le prix de vente minimum est de 0");
		}
		return msgErreur;
		
	}

	
	

}
