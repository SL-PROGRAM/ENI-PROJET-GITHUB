package fr.eni.same.bll.interfaceManager;

import fr.eni.same.bo.Utilisateur;
import fr.eni.same.exception.BllException;

public interface UtilisateurManagerInterface extends ManagerInterface<Utilisateur> {

	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void pseudoLongueurCorrect(String libelle) throws BllException; 
	/**
	 * Vérifie que le Pseudo n'existe pas déja
	 * @param pseudo
	 * @return
	 */
	public void pseudoUnique(String pseudo) throws BllException;
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void nomLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void prenomLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * Vérifie que c'est bien une adresse email
	 * @param email
	 * @return
	 * @throws BllException 
	 */
	public void emailValide(String email) throws BllException;

	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void telephoneLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param email
	 * @return
	 */
	public void motDePasseValide(String email) throws BllException;
	
	
	/**
	 * Vérifie que l'utilisateur a un crédit suffisant	
	 * @param credit
	 * @return
	 */
	public void creditPositif(int credit) throws BllException;
	
}
