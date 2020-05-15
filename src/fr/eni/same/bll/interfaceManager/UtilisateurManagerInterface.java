package fr.eni.same.bll.interfaceManager;

import fr.eni.same.bo.Utilisateur;
import fr.eni.same.exception.BllException;

public interface UtilisateurManagerInterface extends ManagerInterface<Utilisateur> {

	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public String pseudoLongueurCorrect(String libelle) throws BllException; 
	/**
	 * Vérifie que le Pseudo n'existe pas déja
	 * @param pseudo
	 * @return
	 */
	public String pseudoUnique(String pseudo) throws BllException;
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public String nomLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public String prenomLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * Vérifie que c'est bien une adresse email
	 * @param email
	 * @return
	 * @throws BllException 
	 */
	public String emailValide(String email) throws BllException;

	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public String telephoneLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param email
	 * @return
	 */
	public String motDePasseValide(String email) throws BllException;
	
	
	/**
	 * Vérifie que l'utilisateur a un crédit suffisant	
	 * @param credit
	 * @return
	 */
	public String creditPositif(int credit) throws BllException;
	
}
