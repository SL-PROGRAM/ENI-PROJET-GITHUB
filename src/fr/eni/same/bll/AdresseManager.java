package fr.eni.same.bll;

import fr.eni.same.bll.interfaceManager.AdresseInterface;

public abstract class AdresseManager implements AdresseInterface {
	private final int RUE_LONGUEUR_MAX = 30;
	private final int RUE_LONGUEUR_MIN = 4;
	private final int CP_LONGUEUR_MAX = 5;
	private final int CP_LONGUEUR_MIN = 5;
	private final int VILLE_LONGUEUR_MAX = 30;
	private final int VILLE_LONGUEUR_MIN = 4;
	
	@Override
	public boolean isRueLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCodePostalLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVilleLongueurCorrect(String libelle) {
		// TODO Auto-generated method stub
		return false;
	}

}
