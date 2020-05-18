package fr.eni.same.helpers;

import fr.eni.same.exception.BllException;

public abstract class AdresseUtils  {
	protected final static int RUE_LONGUEUR_MAX = 30;
	protected final static int RUE_LONGUEUR_MIN = 4;
	protected final static int CP_LONGUEUR_MAX = 5;
	protected final static int CP_LONGUEUR_MIN = 5;
	protected final static int VILLE_LONGUEUR_MAX = 30;
	protected final static int VILLE_LONGUEUR_MIN = 4;
	

	public static String rueLongueurCorrect(String libelle) throws BllException {
		String msgErreur = "";
		if(!FonctionGenerique.isLongueurMax(libelle, RUE_LONGUEUR_MAX)) {
			msgErreur += "Longueur de la rue est trop importante - Longueur maximum : "+ RUE_LONGUEUR_MAX + " longueur actuelle : " + libelle.length();
		}
		if(!FonctionGenerique.isLongueurMin(libelle, RUE_LONGUEUR_MIN)) {
			msgErreur  += "Longueur de la rue est trop courte - Longueur minimum : "+ RUE_LONGUEUR_MIN;
		}
		return msgErreur ;				
	}
	

	public static String codePostalLongueurCorrect(String libelle) throws BllException {
		String msgErreur = "";
		if(!FonctionGenerique.isLongueurMax(libelle, CP_LONGUEUR_MAX)) {
			msgErreur += "Longueur du code postal trop importante - Longueur maximum : "+ CP_LONGUEUR_MAX + " taille actuelle : " + libelle;
		}
		if(!FonctionGenerique.isLongueurMin(libelle, CP_LONGUEUR_MIN)) {
			msgErreur += "Longueur code postal trop courte - Longueur minimum : "+ CP_LONGUEUR_MIN;
		}
		return msgErreur;
	}

	public static String villeLongueurCorrect(String libelle) throws BllException {
		String msgErreur = "";
		if(!FonctionGenerique.isLongueurMax(libelle, VILLE_LONGUEUR_MAX)) {
			msgErreur = "Longueur de la ville trop importante - Longueur 	maximum : "+ VILLE_LONGUEUR_MAX;
		}
		if(!FonctionGenerique.isLongueurMin(libelle, VILLE_LONGUEUR_MIN)) {
			msgErreur += "Longueur de la ville trop courte - Longueur minimum : "+ VILLE_LONGUEUR_MIN;
		}
		return msgErreur ;				
	}
	
}
