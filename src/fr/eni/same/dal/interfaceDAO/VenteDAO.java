package fr.eni.same.dal.interfaceDAO;

import fr.eni.same.bo.Vente;

/**
 * Interface contenant les informations spécifiques à implémenter pour les ventes
 * @author etienne
 */
public interface VenteDAO extends DAO<Vente> {
	
	public void selectAcheteur(Vente t);

}
