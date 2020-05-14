package fr.eni.same.bll.interfaceManager;

import fr.eni.same.bo.Categorie;
import fr.eni.same.bo.Enchere;
import fr.eni.same.exception.BllException;
/**
 * 
 * @author sl
 *
 */
public interface EnchereManagerInterface extends ManagerInterface<Enchere> {

	public Enchere select(int noVente, int noUtlisateur ) throws BllException; 
}
