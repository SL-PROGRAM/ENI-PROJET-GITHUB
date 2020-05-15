package fr.eni.same.bll;

import java.util.List;

import fr.eni.same.bll.interfaceManager.CategorieManagerInterface;
import fr.eni.same.bll.interfaceManager.SelectManagerInterface;
import fr.eni.same.bo.Categorie;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;
import fr.eni.same.helpers.FonctionGenerique;

public class CategorieManager implements CategorieManagerInterface, SelectManagerInterface<Categorie> {

	private final int LIBELLE_LONGUEUR_MAX = 30;
	private final int LIBELLE_LONGUEUR_MIN = 4;
	private static CategorieManager instance;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de
	 * la classe
	 */
	private CategorieManager() {
	}

	/**
	 * methode Get pour récupérer l'instance et la créer si elle n'existe pas
	 * 
	 * @return
	 */
	public static synchronized CategorieManager getCategorieManager() {
		if (instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}

	@Override
	public void insert(Categorie t) throws BllException {
		if (libelleLongueurCorrect(t.getLibelle()).equalsIgnoreCase("")) {
			throw new BllException(libelleLongueurCorrect(t.getLibelle()));
		} else {
			try {
				DALFactory.getCategorieDAOJdbcImpl().insert(t);
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Categorie t) throws BllException {
		if (libelleLongueurCorrect(t.getLibelle()).equalsIgnoreCase("")) {
			throw new BllException(libelleLongueurCorrect(t.getLibelle()));
		} else {
			try {
				DALFactory.getCategorieDAOJdbcImpl().update(t);
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Categorie t) throws BllException {
		try {
			DALFactory.getCategorieDAOJdbcImpl().delete(t);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Categorie select(int id) throws BllException {
		try {
			return DALFactory.getCategorieDAOJdbcImpl().select(id);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Categorie> selectAll() throws BllException {
		try {
			List<Categorie> listeCategories = DALFactory.getCategorieDAOJdbcImpl().selectAll();
			return listeCategories;
		} catch (DALException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative
	// d'enregistrement en BDD * //
	// ***********************************************************************************************//

	@Override
	public String libelleUnique(List<Categorie> list, String libelle) {
		String resultat = "";
		boolean isUnique = true;
		for (Categorie categorie : list) {
			if (categorie.getLibelle() == libelle) {
				isUnique = false;
				resultat = "Cette catégorie existe déjà";
			}
		}
		return resultat;
	}

	@Override
	public String libelleLongueurCorrect(String libelle) {
		String resultat = "";
		if (!FonctionGenerique.isLongueurMax(libelle, LIBELLE_LONGUEUR_MAX)) {
			resultat = "Le libellé de la catégorie est trop long";
		} else if (!FonctionGenerique.isLongueurMin(libelle, LIBELLE_LONGUEUR_MIN)) {
			resultat = "Le libellé de la catégorie est trop court";
		}
		return resultat;
	}
}