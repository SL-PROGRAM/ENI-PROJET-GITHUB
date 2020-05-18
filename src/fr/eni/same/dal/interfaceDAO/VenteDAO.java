package fr.eni.same.dal.interfaceDAO;

import fr.eni.same.bo.Vente;
import fr.eni.same.exception.DALException;

/**
 * Interface contenant les informations spécifiques à implémenter pour les ventes
 * @author etienne
 */
public interface VenteDAO extends DAO<Vente>, selectDAO<Vente> {
	
	/**
	 * Update l'acheteur de la vente
	 * @param t
	 * @throws DALException 
	 */
	Vente updateAcheteur(Vente t) throws DALException;
}
