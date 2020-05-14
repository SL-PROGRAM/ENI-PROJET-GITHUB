package fr.eni.same.bll.interfaceManager;

public  interface AdresseInterface {

	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public boolean isRueLongueurCorrect(String libelle); 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public boolean isCodePostalLongueurCorrect(String libelle); 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public boolean isVilleLongueurCorrect(String libelle); 
}
