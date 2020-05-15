package fr.eni.same.bll.interfaceManager;

import fr.eni.same.exception.BllException;

public  interface AdresseInterface {

	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public String rueLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public String codePostalLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public String villeLongueurCorrect(String libelle) throws BllException; 
}
