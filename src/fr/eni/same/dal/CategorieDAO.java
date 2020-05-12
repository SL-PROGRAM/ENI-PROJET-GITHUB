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
	public boolean categorieUnique(List<Categorie> list, String libelle);
	
	/**
	 * Verifie si le libelle fait moins de 30 caracteres
	 * @param libelle
	 * @return
	 */
	public boolean categorieVerifLibelleLongeurMax(String libelle);
	
	/**
	 * Verifie si le libelle fait plus de 4 caracteres
	 * @param libelle
	 * @return
	 */
	public boolean categorieVerifLibelleLongeurMin(String libelle);

}
