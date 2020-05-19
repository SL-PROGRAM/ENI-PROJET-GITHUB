package fr.eni.same.helpers;

/**
 * 
 * @author sl
 *
 */
public class FonctionGenerique {

	public static boolean isLongueurMax(String stringAtest, int longueurMax) {
		boolean isUnique = false;
		if(stringAtest.length() <= longueurMax) {
			isUnique = true;
		}
		return isUnique;
	}

	public static boolean isLongueurMin(String stringAtest, int longueurMin) {
		boolean isUnique = false;
		if(stringAtest.length() >= longueurMin) {
			isUnique = true;
		}
		return isUnique;
	}
}