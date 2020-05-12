package fr.eni.same.dal;

import fr.eni.same.bo.Vente;

/**
 * Interface contenant les informations spécifiques à implémenter pour les retraits
 * @author etienne
 */
public interface RetraitDAO extends DAO<Vente> {

	
	/**
	 * Verifie si le libelle fait moins de 30 caracteres
	 * @param libelle
	 * @return
	 */
	public boolean venteVerifRueLongeurMax(String libelle);
	
	/**
	 * Verifie si le libelle fait plus de 4 caracteres
	 * @param libelle
	 * @return
	 */
	public boolean venteVerifRueLongeurMin(String libelle);
	
	/**
	 * Verifie si le libelle fait 5 caracteres MAX
	 * @param libelle
	 * @return
	 */
	public boolean venteVerifCPLongeurMax(String libelle);
	
	
	/**
	 * Verifie si le libelle fait moins de 5 caracteres
	 * @param libelle
	 * @return
	 */
	public boolean venteVerifCPLongeurMin(String libelle);
	/**
	 * Verifie si le libelle fait moins de 30 caracteres
	 * @param libelle
	 * @return
	 */
	public boolean venteVerifVilleLongeurMax(String libelle);
	
	/**
	 * Verifie si le libelle fait plus de 1 caracteres et different de ""
	 * @param libelle
	 * @return
	 */
	public boolean venteVerifVilleLongeurMin(String libelle);
	
	
}
