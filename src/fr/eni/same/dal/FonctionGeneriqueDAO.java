package fr.eni.same.dal;

public class FonctionGeneriqueDAO {


	public static boolean isLongeurMax(String stingAtest, int longeurMax) {
		boolean isUnique = true;
		if(stingAtest.length() < longeurMax) {
			isUnique = false;
		}
		return isUnique;
	}
	

	public static boolean isLongeurMin(String stingAtest, int longeurMin) {
		boolean isUnique = true;
		if(stingAtest.length() > longeurMin) {
			isUnique = false;
		}
		return isUnique;
	}

}
