package fr.eni.same.dal;

import java.util.List;

import fr.eni.same.bo.Categorie;

/**
 * Interface contenant les informations spécifiques à implémenter pour les catégories
 * @author etienne
 */
public interface CategorieDAO extends DAO<Categorie> {
	
	/**
	 * Vérifie si la catégorie existe déja
	 * @param list
	 * @param libelle
	 * @return
	 */
	public boolean isLibelleUnique(List<Categorie> list, String libelle);
	
	
}
