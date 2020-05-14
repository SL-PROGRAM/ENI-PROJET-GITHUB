package fr.eni.same.bll;

import fr.eni.same.bll.interfaceManager.AdresseInterface;

public abstract class AdresseManager implements AdresseInterface {
	private final int RUE_LONGEUR_MAX = 30;
	private final int RUE_LONGEUR_MIN = 4;
	private final int CP_LONGEUR_MAX = 5;
	private final int CP_LONGEUR_MIN = 5;
	private final int VILLE_LONGEUR_MAX = 30;
	private final int VILLE_LONGEUR_MIN = 4;
	
	@Override
	public boolean isRueLongeurCorrect(String libelle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCodePostalLongeurCorrect(String libelle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVilleLongeurCorrect(String libelle) {
		// TODO Auto-generated method stub
		return false;
	}

}
