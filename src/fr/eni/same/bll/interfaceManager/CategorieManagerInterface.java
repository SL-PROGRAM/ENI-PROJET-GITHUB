package fr.eni.same.bll.interfaceManager;

import java.util.List;

import fr.eni.same.bo.Categorie;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.UniqueException;

public interface CategorieManagerInterface extends ManagerInterface<Categorie> {

	
	/**
	 * Vérifie si la catégorie existe déja
	 * @param list
	 * @param libelle
	 * @return
	 * @throws UniqueException 
	 */
	public void libelleUnique(List<Categorie> list, String libelle) throws BllException;
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void libelleLongueurCorrect(String libelle) throws BllException; 
	
	
}
