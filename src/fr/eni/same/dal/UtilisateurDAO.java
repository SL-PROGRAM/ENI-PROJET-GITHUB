package fr.eni.same.dal;

import fr.eni.same.bo.Utilisateur;

/**
 * Interface contenant les informations spécifiques à implémenter pour l'utilisateur
 * @author etienne
 *
 */
public interface UtilisateurDAO extends DAO<Utilisateur>, AdresseDAO{
	
	public boolean isPseudoUnique(String pseudo);
	public boolean isPseudoLongeurMax(String pseudo);
	public boolean isPseudoLongeurMin(String pseudo);

	public boolean isNomLongeurMax(String nom);
	public boolean isNomLongeurMin(String nom);
	
	public boolean isPrenomLongeurMax(String prenom);
	public boolean isPrenomLongeurMin(String prenom);
	
	public boolean isEmailValide(String email);
	public boolean isEmailLongeurMax(String email);
	public boolean isEmailLongeurMin(String email);
	
	public boolean isTelephoneLongeurMax(String telephone);
	public boolean isTelephonelLongeurMin(String telephone);
	
	public boolean isMotDePasseLongeurMax(String telephone);
	public boolean isMotDePasselLongeurMin(String telephone);
	
	
	
	public boolean isCreditPositif(int credit);
	
	
}
