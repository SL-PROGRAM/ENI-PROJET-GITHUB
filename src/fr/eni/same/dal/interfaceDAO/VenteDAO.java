package fr.eni.same.dal;

import java.time.LocalDateTime;

import fr.eni.same.bo.Vente;

/**
 * Interface contenant les informations spécifiques à implémenter pour les ventes
 * @author etienne
 */
public interface VenteDAO extends DAO<Vente> {
	
	

	/**
	 * vérifier que date fin min +24h
	 * @param description
	 * @return
	 */
	public boolean isdateFinEnchere(LocalDateTime dateFinEnchere);
	
	/**
	 * Veref prixInitial > 0
	 * @param prixInitial
	 * @return
	 */
	public boolean isPrixInitialPositif(int prixInitial);

	

}
