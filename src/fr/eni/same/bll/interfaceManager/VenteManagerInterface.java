package fr.eni.same.bll.interfaceManager;

import java.time.LocalDateTime;

import fr.eni.same.bo.Vente;

public interface VenteManagerInterface extends ManagerInterface<Vente> {

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
