package fr.eni.same.bll.interfaceManager;

import fr.eni.same.exception.BllException;

public  interface AdresseInterface {

	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void rueLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void codePostalLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void villeLongueurCorrect(String libelle) throws BllException; 
}
