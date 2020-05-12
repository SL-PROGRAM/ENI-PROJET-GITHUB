package fr.eni.same.dal;

import java.time.LocalDateTime;

import fr.eni.same.bo.Vente;

/**
 * Interface contenant les informations spécifiques à implémenter pour les ventes
 * @author etienne
 */
public interface VenteDAO extends DAO<Vente> {
	
	
	
	/**
	 * verif longueur max 30
	 * @param nomArticle
	 * @return
	 */
	public boolean venteVerifNomArticleLongeurMax(String nomArticle);
	
	/**
	 * vérif longeur min 3
	 * @param nomArticle
	 * @return
	 */
	public boolean venteVerifNomArticleLongeurMin(String nomArticle);
	
	/**
	 * vérif longeur max 300
	 * @param description
	 * @return
	 */
	public boolean venteVerifdescriptionLongeurMax(String description);
	
	/**
	 * vérif longeur min 2
	 * @param description
	 * @return
	 */
	public boolean venteVerifdescriptionLongeurMin(String description);

	/**
	 * vérifier que date fin min +24h
	 * @param description
	 * @return
	 */
	public boolean venteVerifdateFinEnchere(LocalDateTime dateFinEnchere);
	
	/**
	 * Veref prixInitial > 0
	 * @param prixInitial
	 * @return
	 */
	public boolean venteVerifPrixInitialPositif(int prixInitial);

	

}
