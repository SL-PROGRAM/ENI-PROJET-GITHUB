package fr.eni.same.bll.interfaceManager;

import fr.eni.same.exception.BllException;

public  interface AdresseInterface {

	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void RueLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void CodePostalLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void VilleLongueurCorrect(String libelle) throws BllException; 
}
