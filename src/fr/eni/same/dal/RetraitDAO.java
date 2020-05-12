package fr.eni.same.dal;

import fr.eni.same.bo.Retrait;

/**
 * Interface contenant les informations spécifiques à implémenter pour les retraits
 * @author etienne
 */
public interface RetraitDAO extends DAO<Retrait> {


	/**
	 * verif max 30
	 * @param rue
	 * @return
	 */
	public boolean retraitVerifRueLongeurMax(String rue);
	
	/**
	 * verif min 4
	 * @param rue
	 * @return
	 */
	public boolean retraitVerifRueLongeurMin(String rue);

	/**
	 * verif max 5
	 * @param codePostal
	 * @return
	 */
	public boolean retraitVerifCPLongeurMax(String codePostal);
	
	/**
	 * verif min 5
	 * @param codePostal
	 * @return
	 */

	public boolean retraitVerifCPLongeurMin(String codePostal);
	
	/**
	 * verif max 30
	 * @param ville
	 * @return
	 */
	public boolean retraitVerifVilleLongeurMax(String ville);
	
	/**
	 * verif min 4
	 * @param ville
	 * @return
	 */
	public boolean retraitVerifVilleLongeurMin(String ville);
	
	
}
