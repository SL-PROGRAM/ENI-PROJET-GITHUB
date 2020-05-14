package fr.eni.same.bll.interfaceManager;

public  interface AdresseInterface {

	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public boolean isRueLongeurCorrect(String libelle); 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public boolean isCodePostalLongeurCorrect(String libelle); 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public boolean isVilleLongeurCorrect(String libelle); 
}
