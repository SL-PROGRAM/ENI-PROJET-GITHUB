package fr.eni.same.bll.interfaceManager;

import fr.eni.same.bo.Utilisateur;

public interface UtilisateurManagerInterface extends ManagerInterface<Utilisateur> {

	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public boolean isPseudoLongueurCorrect(String libelle); 
	/**
	 * Vérifie que le Pseudo n'existe pas déja
	 * @param pseudo
	 * @return
	 */
	public boolean isPseudoUnique(String pseudo);
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public boolean isNomLongueurCorrect(String libelle); 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public boolean isPrenomLongueurCorrect(String libelle); 
	
	/**
	 * Vérifie longeur max et min correct
	 * Vérifie que c'est bien une adresse email
	 * @param email
	 * @return
	 */
	public boolean isEmailValide(String email);

	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public boolean isTelephoneLongueurCorrect(String libelle); 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param email
	 * @return
	 */
	public boolean isMotDePasseValide(String email);
	
	
	/**
	 * Vérifie que l'utilisateur a un crédit suffisant	
	 * @param credit
	 * @return
	 */
	public boolean isCreditPositif(int credit);
	
}
