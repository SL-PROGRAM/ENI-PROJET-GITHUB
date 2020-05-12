package fr.eni.same.dal;

import fr.eni.same.bo.Utilisateur;

/**
 * Interface contenant les informations spécifiques à implémenter pour l'utilisateur
 * @author etienne
 *
 */
public interface UtilisateurDAO extends DAO<Utilisateur>{
	
	public boolean isPseudoUnique(String pseudo);
	
	public boolean isEmailValide(String email);
		
	public boolean isCreditPositif(int credit);
	
	
}
