package fr.eni.same.bll.interfaceManager;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import fr.eni.same.bo.Vente;
import fr.eni.same.exception.BllException;

public interface VenteManagerInterface extends ManagerInterface<Vente> {

	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public void nomArticleLongeurCorrect(String libelle) throws BllException; 
	
	/**
	 * Vérifie longeur max et min correct
	 * @param libelle
	 * @return
	 */
	public  void descriptionLongeurCorrect(String libelle) throws BllException; 
	
	/**
	 * vérifier que date fin min +24h
	 * @param description
	 * @return
	 */
	public void dateFinEnchere(Timestamp dateFinEnchere) throws BllException;
	
	/**
	 * Veref prixInitial > 0
	 * @param prixInitial
	 * @return
	 */
	public void prixInitialPositif(int prixInitial) throws BllException;
}
