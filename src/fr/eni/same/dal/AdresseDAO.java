package fr.eni.same.dal;

public interface AdresseDAO {

	/**
	 * verif max 30
	 * @param rue
	 * @return
	 */
	public boolean isRueLongeurMax(String rue);
	
	/**
	 * verif min 4
	 * @param rue
	 * @return
	 */
	public boolean isRueLongeurMin(String rue);

	/**
	 * verif max 5
	 * @param codePostal
	 * @return
	 */
	public boolean isCPLongeurMax(String codePostal);
	
	/**
	 * verif min 5
	 * @param codePostal
	 * @return
	 */

	public boolean isCPLongeurMin(String codePostal);
	
	/**
	 * verif max 30
	 * @param ville
	 * @return
	 */
	public boolean isVilleLongeurMax(String ville);
	
	/**
	 * verif min 4
	 * @param ville
	 * @return
	 */
	public boolean isVilleLongeurMin(String ville);
}
