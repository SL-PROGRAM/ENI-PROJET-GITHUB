package fr.eni.same.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;

//Class liée aux filtres
public class FiltreManager {
	
	private static FiltreManager instance;
	
	private FiltreManager() {
	}
	
	public static synchronized FiltreManager getFiltreManager() {
		if(instance == null) {
			instance = new FiltreManager();
		}
		return instance;
	}
	
	public void getMesVentes() {
		
	}
	public void getMesEncheresEnCours() {
		
	}
	public void getMesAcquisitions() {
		
	}
	public void getAutresEncheres() {
		
	}
	public void getMesVentesEnregistrees() {
		
	}
	/**
	 * Recherche le nom d'un article correspondant aux X premières lettres dans le champ de recherche
	 * @param input : le mot du champ de recherche
	 */
	public void getRechercheParMotCles() {
		
	}
}
