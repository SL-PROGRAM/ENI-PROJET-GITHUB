 package fr.eni.same.bll;

import java.util.List;
import fr.eni.same.bo.Categorie;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;
import fr.eni.same.helpers.FonctionGenerique;

public class CategorieManager{

	private final int LIBELLE_LONGUEUR_MAX = 30;
	private final int LIBELLE_LONGUEUR_MIN = 4;
	private static CategorieManager instance;
	private List<Categorie> listeCategories;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 * @throws BllException
	 */
	private CategorieManager() throws BllException {
		if(listeCategories == null) {
			try {
				listeCategories = DALFactory.getCategorieDAOJdbcImpl().selectAll();
			} catch (DALException e) {
				e.printStackTrace();
			}
		} 
	}

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     * @throws DALException 
     */
    public static synchronized CategorieManager getCategorieManager () {
        if(instance == null){
            try {
				instance = new CategorieManager();
			} catch (BllException e) {
				e.printStackTrace();
			}
        }
        return instance;
    }
	
	public void insert(Categorie t) throws BllException {
		String msgErreur = controleUpdateAndInsert(t);
		if(msgErreur.equalsIgnoreCase("")) {
			try{
				DALFactory.getCategorieDAOJdbcImpl().insert(t);
				listeCategories.add(t);
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
			System.out.println("Catégorie : Insertion réalisée.");
	}
	
	public void update(Categorie t) throws BllException {
		String msgErreur = controleUpdateAndInsert(t);
		if(msgErreur.equalsIgnoreCase("")) {
			try {
				DALFactory.getCategorieDAOJdbcImpl().update(t);
				for(int i = 0; i < listeCategories.size(); i++) {
					if(listeCategories.get(i).getNoCategorie() == t.getNoCategorie()) {
						listeCategories.get(i).setLibelle(t.getLibelle());
						System.out.println("Catégorie : Update réalisée : " + t.toString());
					}
				}
			} catch (DALException e) {
				e.printStackTrace();
			}
		} else {
			throw new BllException(msgErreur);
		}
	}

	public void delete(Categorie t) throws BllException {
		try {
			DALFactory.getCategorieDAOJdbcImpl().delete(t);
			listeCategories.remove(t);
		} catch (DALException e) {
			throw new BllException("Impossible de supprimer l'entrée.");
		}
		System.out.println("Catégorie : Delete réalisé.");
	}

	public Categorie select(int id) throws BllException {
		Categorie cat = null;
		for(int i = 0; i < listeCategories.size(); i++) {
			if(listeCategories.get(i).getNoCategorie() == id) {
				cat = listeCategories.get(i);
			}
		}
		if(cat == null) {
			throw new BllException("Aucune catégorie n'a été trouvée avec cet identifiant.");
		}
		System.out.println("Catégorie : Select réalisé : " + cat.toString());
		return cat;
	}


	
	public List<Categorie> selectAll() throws BllException {
		for(Categorie c : listeCategories) {
			System.out.println("Categorie : Select All réalisé : " + c.toString());
		}
		return listeCategories;
	}

	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//
	
	
	private String controleUpdateAndInsert(Categorie t) {
		String msgErreur = "";
		try {
			msgErreur += categorieNull(t);
			msgErreur += libelleLongueurCorrect(t.getLibelle());
			msgErreur += libelleUnique(t.getLibelle());
		} catch (BllException e) {
			e.printStackTrace();
		}
		return msgErreur;
	}
	
	private String categorieNull (Categorie t){
		String msgErreur="";
		if(t == null) {
			msgErreur += "Erreur : null";
		}
		return msgErreur;
	}
	
	public String libelleUnique(String libelle) throws BllException {
		String resultat = "";
		for (int i = 0; i < listeCategories.size(); i++) {
			if(listeCategories.get(i).getLibelle().equalsIgnoreCase(libelle)){
				System.out.println("Compare " + libelle + " avec " + listeCategories.get(i).getLibelle());
			}
		}
		return resultat;
	}


	
	public String libelleLongueurCorrect(String libelle) {
		String resultat = "";
		if(!FonctionGenerique.isLongueurMax(libelle, LIBELLE_LONGUEUR_MAX)) {
			resultat += "Le libellé est trop grand. Longueur maximum : " + LIBELLE_LONGUEUR_MAX + " longueur actuelle " + libelle.length();
		}
		if(!FonctionGenerique.isLongueurMin(libelle, LIBELLE_LONGUEUR_MIN)) {
			resultat += "Le libellé est trop petit. Longueur minimum : " + LIBELLE_LONGUEUR_MIN + " longueur actuelle " + libelle.length();
		}
		return resultat;
	}
}