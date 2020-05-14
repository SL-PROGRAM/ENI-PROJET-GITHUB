package fr.eni.same.dal.interfaceDAO;

import fr.eni.same.bo.Vente;

/**
 * Interface contenant les informations spécifiques à implémenter pour les ventes
 * @author etienne
 */
public interface VenteDAO extends DAO<Vente>, selectDAO<Vente> {
	
	/**
	 * Update l'acheteur de la vente
	 * @param t
	 */
	Vente updateAcheteur(Vente t);
}
