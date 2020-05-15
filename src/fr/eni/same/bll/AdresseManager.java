package fr.eni.same.bll;

import fr.eni.same.bll.interfaceManager.AdresseInterface;
import fr.eni.same.exception.BllException;
import fr.eni.same.helpers.FonctionGenerique;

public abstract class AdresseManager implements AdresseInterface {
	protected final int RUE_LONGUEUR_MAX = 30;
	protected final int RUE_LONGUEUR_MIN = 4;
	protected final int CP_LONGUEUR_MAX = 5;
	protected final int CP_LONGUEUR_MIN = 5;
	protected final int VILLE_LONGUEUR_MAX = 30;
	protected final int VILLE_LONGUEUR_MIN = 4;
	
	@Override
	public void rueLongueurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongeurMax(libelle, RUE_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ RUE_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongeurMax(libelle, RUE_LONGUEUR_MIN)) {
			throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ RUE_LONGUEUR_MIN);
		}				
	}
	

	@Override
	public void codePostalLongueurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongeurMax(libelle, CP_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur maximum : "+ CP_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongeurMax(libelle, CP_LONGUEUR_MIN)) {
			throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ CP_LONGUEUR_MIN);
		}				
	}

	@Override
	public void villeLongueurCorrect(String libelle) throws BllException {
		if(!FonctionGenerique.isLongeurMax(libelle, VILLE_LONGUEUR_MAX)) {
			throw new BllException("Longeur du nom trop importante - Longueur 	maximum : "+ VILLE_LONGUEUR_MAX);
		}
		if(!FonctionGenerique.isLongeurMax(libelle, VILLE_LONGUEUR_MIN)) {
			throw new BllException("Longeur du nom trop importante - Longueur minimum : "+ VILLE_LONGUEUR_MIN);
		}				
	}
	
}
