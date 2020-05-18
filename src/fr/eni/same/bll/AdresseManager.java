package fr.eni.same.bll;

import fr.eni.same.exception.BllException;
import fr.eni.same.helpers.FonctionGenerique;

public class AdresseManager {
	protected final int RUE_LONGUEUR_MAX = 30;
	protected final int RUE_LONGUEUR_MIN = 4;
	protected final int CP_LONGUEUR_MAX = 5;
	protected final int CP_LONGUEUR_MIN = 5;
	protected final int VILLE_LONGUEUR_MAX = 30;
	protected final int VILLE_LONGUEUR_MIN = 4;
	
	public void rueLongueurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongueurMax(libelle, RUE_LONGUEUR_MAX)) {
			throw new BllException("Longueur du nom trop importante - Longueur maximum : "+ RUE_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongueurMin(libelle, RUE_LONGUEUR_MIN)) {
			throw new BllException("Longueur du nom trop importante - Longueur minimum : "+ RUE_LONGUEUR_MIN);
		}				
	}
	

	public void codePostalLongueurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongueurMax(libelle, CP_LONGUEUR_MAX)) {
			throw new BllException("Longueur du nom trop importante - Longueur maximum : "+ CP_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongueurMin(libelle, CP_LONGUEUR_MIN)) {
			throw new BllException("Longueur du nom trop importante - Longueur minimum : "+ CP_LONGUEUR_MIN);
		}				
	}

	public void villeLongueurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongueurMax(libelle, VILLE_LONGUEUR_MAX)) {
			throw new BllException("Longueur du nom trop importante - Longueur 	maximum : "+ VILLE_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongueurMin(libelle, VILLE_LONGUEUR_MIN)) {
			throw new BllException("Longueur du nom trop importante - Longueur minimum : "+ VILLE_LONGUEUR_MIN);
		}				
	}
	
}
