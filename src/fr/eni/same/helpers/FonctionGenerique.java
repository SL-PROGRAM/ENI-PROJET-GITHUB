package fr.eni.same.helpers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author sl
 *
 */
public class FonctionGenerique {
	static String msgErreur = "";

	public static boolean isLongueurMax(String stringAtest, int longueurMax) {
		boolean resultat = false;
		if(stringAtest.length() <= longueurMax) {
			resultat = true;
		}
		return resultat;
	}

	public static boolean isLongueurMin(String stringAtest, int longueurMin) {
		boolean resultat = false;
		if(stringAtest.length() >= longueurMin) {
			resultat = true;
		}
		return resultat;
	}
	
	public static Timestamp dateToTimestamp(String dateFinEnchere) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
			 Date date =  formatter.parse(dateFinEnchere);
			 Timestamp timestamp = new Timestamp(date.getTime());
			 //TODO Gérer l'heure
		     return timestamp;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public static String gestionErreur(String message) {
		msgErreur += message;
		return msgErreur;
	}
}