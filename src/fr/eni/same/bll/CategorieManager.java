 package fr.eni.same.bll;

import java.util.List;
import fr.eni.same.bo.Categorie;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;
import fr.eni.same.helpers.FonctionGenerique;
/**
 * BLL - Classe contenant les méthodes de gestion des catégories
 * @author etienne
 */
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
	
    /**
     * Méthode de vérification avant insertion d'une catégorie en base de donnée
     * Vérifie si la catégorie n'est pas nulle, si la taille du libellé est correcte, et si il est unique.
     * @param categorie : la catégorie à tester
     * @throws BllException
     */
	public void insert(Categorie categorie) throws BllException {
		String msgErreur = controleUpdateAndInsert(categorie);
		if(msgErreur.equalsIgnoreCase("")) {
			try{
				DALFactory.getCategorieDAOJdbcImpl().insert(categorie);
				listeCategories.add(categorie);
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
			System.out.println("Catégorie : Insertion réalisée.");
	}
	
    /**
     * Méthode de vérification avant update d'une catégorie en base de donnée
     * Vérifie si la catégorie n'est pas nulle, si la taille du libellé est correcte, et si il est unique.
     * @param categorie : la catégorie à tester
     * @throws BllException
     */
	public void update(Categorie categorie) throws BllException {
		String msgErreur = controleUpdateAndInsert(categorie);
		if(msgErreur.equalsIgnoreCase("")) {
			try {
				DALFactory.getCategorieDAOJdbcImpl().update(categorie);
				for(int i = 0; i < listeCategories.size(); i++) {
					if(listeCategories.get(i).getNoCategorie() == categorie.getNoCategorie()) {
						listeCategories.get(i).setLibelle(categorie.getLibelle());
						System.out.println("Catégorie : Update réalisée : " + categorie.toString());
					}
				}
			} catch (DALException e) {
				e.printStackTrace();
			}
		} else {
			throw new BllException(msgErreur);
		}
	}

	/**
	 * Méthode de suppression d'une entrée dans la base de donnée
	 * @param categorie : la catégorie à tester
	 * @throws BllException
	 */
	public void delete(Categorie categorie) throws BllException {
		String msgErreur = "";
		msgErreur = categorieNull(categorie);
		try {
			if(msgErreur.equalsIgnoreCase("")) {
				DALFactory.getCategorieDAOJdbcImpl().delete(categorie);
				listeCategories.remove(categorie);				
			}
		} catch (DALException e) {
			throw new BllException("Impossible de supprimer l'entrée.");
		}
		System.out.println("Catégorie : Delete réalisé.");
	}

	/**
	 * Méthode de sélection d'une entrée dans la liste des catégories
	 * @param id : NoCategorie de la catégorie
	 * @return cat : Catégorie sélectionnée par id
	 * @throws BllException
	 */
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

	/**
	 * Méthode de sélection de la liste des catégories 
	 * @return listeCategories : la liste locale des catégories
	 * @throws BllException
	 */
	public List<Categorie> selectAll() throws BllException {
		for(Categorie c : listeCategories) {
			System.out.println("Categorie : Select All réalisé : " + c.toString());
		}
		return listeCategories;
	}

	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//
	
	
	/**
	 * Méthode de vérification standard d'une catégorie
	 * Vérifie que la catégorie n'est pas nulle, que son nombre de lettre soit correct, et que son libellé soit unique
	 * @param categorie : La catégorie à tester
	 * @return msgErreur : String contenant un éventuel message d'erreur, sinon renvoie ""
	 */
	private String controleUpdateAndInsert(Categorie categorie) {
		String msgErreur = "";
		try {
			msgErreur += categorieNull(categorie);
			msgErreur += libelleLongueurCorrect(categorie.getLibelle());
			msgErreur += libelleUnique(categorie.getLibelle());
		} catch (BllException e) {
			e.printStackTrace();
		}
		return msgErreur;
	}
	
	/**
	 * Permet de savoir si la Catégorie est nulle 
	 * @param categorie
	 * @return
	 */
	private String categorieNull (Categorie categorie){
		String msgErreur="";
		if(categorie == null) {
			msgErreur += "Erreur : null";
		}
		return msgErreur;
	}
	
	/**
	 * Permet de vérifier si le libellé renseigné est unique ou non pour éviter les doublons en base de donnée
	 * @param libelle : le libellé de la catégorie à tester
	 * @return msgErreur : String contenant un éventuel message d'erreur, sinon renvoie ""
	 * @throws BllException
	 */
	public String libelleUnique(String libelle) throws BllException {
		String resultat = "";
		for (int i = 0; i < listeCategories.size(); i++) {
			if(listeCategories.get(i).getLibelle().equalsIgnoreCase(libelle)){
				System.out.println("Compare " + libelle + " avec " + listeCategories.get(i).getLibelle());
			}
		}
		return resultat;
	}


	/**
	 * Permet de vérifier que la taille du libellé correspond aux specs (taille max : 30, taille min = 5)
	 * @param libelle : Le libellé de la catégorie à tester
	 * @return msgErreur : String contenant un éventuel message d'erreur, sinon renvoie ""
	 */
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