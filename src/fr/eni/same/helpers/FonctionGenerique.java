package fr.eni.same.helpers;

public class FonctionGenerique {

	public static boolean isLongueurMax(String stringAtest, int longueurMax) {
		boolean isUnique = true;
		if(stringAtest.length() < longueurMax) {
			isUnique = false;
		}
		return isUnique;
	}

	public static boolean isLongueurMin(String stringAtest, int longueurMin) {
		boolean isUnique = true;
		if(stringAtest.length() > longueurMin) {
			isUnique = false;
		}
		return isUnique;
	}
}