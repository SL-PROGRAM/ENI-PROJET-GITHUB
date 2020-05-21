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