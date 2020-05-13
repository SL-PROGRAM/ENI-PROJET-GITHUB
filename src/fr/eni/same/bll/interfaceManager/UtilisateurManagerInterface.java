package fr.eni.same.bll.interfaceManager;

import fr.eni.same.bo.Utilisateur;

public interface UtilisateurManagerInterface extends ManagerInterface<Utilisateur> {

	public boolean isPseudoUnique(String pseudo);
	
	public boolean isEmailValide(String email);
		
	public boolean isCreditPositif(int credit);
	
}
