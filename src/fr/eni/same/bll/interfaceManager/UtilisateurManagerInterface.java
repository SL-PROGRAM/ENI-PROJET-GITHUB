package fr.eni.same.bll.interfaceManager;

import fr.eni.same.bo.Utilisateur;
import fr.eni.same.exception.BllException;

public interface UtilisateurManagerInterface extends ManagerInterface<Utilisateur> {

	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void PseudoLongueurCorrect(String libelle) throws BllException; 
	/**
	 * Vérifie que le Pseudo n'existe pas déja
	 * @param pseudo
	 * @return
	 */
	public void PseudoUnique(String pseudo) throws BllException;
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void NomLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void PrenomLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * Vérifie que c'est bien une adresse email
	 * @param email
	 * @return
	 * @throws BllException 
	 */
	public void EmailValide(String email) throws BllException;

	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void TelephoneLongueurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param email
	 * @return
	 */
	public void MotDePasseValide(String email) throws BllException;
	
	
	/**
	 * Vérifie que l'utilisateur a un crédit suffisant	
	 * @param credit
	 * @return
	 */
	public void CreditPositif(int credit) throws BllException;
	
}
