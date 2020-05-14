package fr.eni.same.dal.interfaceDAO;

import fr.eni.same.bo.Vente;

/**
 * Interface contenant les informations spécifiques à implémenter pour les ventes
 * @author etienne
 */
public interface VenteDAO extends DAO<Vente>, selectDAO<Vente> {
	
	/**
	 * Sélectionne le gagnant de l'enchère depuis une vente
	 * @param t
	 */
	public void selectAcheteur(Vente t);

}
