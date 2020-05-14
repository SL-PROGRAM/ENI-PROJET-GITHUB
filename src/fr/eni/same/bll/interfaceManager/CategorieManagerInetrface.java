package fr.eni.same.bll.interfaceManager;

import java.util.List;

import fr.eni.same.bo.Categorie;
import fr.eni.same.exception.UniqueException;

public interface CategorieManagerInetrface extends ManagerInterface<Categorie> {

	
	/**
	 * Vérifie si la catégorie existe déja
	 * @param list
	 * @param libelle
	 * @return
	 * @throws UniqueException 
	 */
	public boolean isLibelleUnique(List<Categorie> list, String libelle) throws UniqueException;
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public boolean isLibelleLongueurCorrect(String libelle); 
	
	
}
