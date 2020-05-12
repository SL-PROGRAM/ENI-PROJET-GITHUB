package fr.eni.same.bo;

import java.time.LocalDate;
/**
 * Enchere permet de faire le lien entre l'utilisateur qui fait l'enchere et le produit vendu
 * sert a enregistrer la date et heure de l'enchere a la ms
 * @author sl
 *
 */
public class Enchere {
	private LocalDate dateEnchere; //doit prendre en compte les ms
	private Utilisateur utilisateurEnchere;
	private Vente venteEnchere;
	
	
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public Utilisateur getUtilisateurEnchere() {
		return utilisateurEnchere;
	}
	public void setUtilisateurEnchere(Utilisateur utilisateurEnchere) {
		this.utilisateurEnchere = utilisateurEnchere;
	}
	public Vente getVenteEnchere() {
		return venteEnchere;
	}
	public void setVenteEnchere(Vente venteEnchere) {
		this.venteEnchere = venteEnchere;
	}
	
	/**
	 * Constructeur
	 * @param dateEnchere
	 * @param utilisateurEnchere
	 * @param venteEnchere
	 */
	public Enchere(LocalDate dateEnchere, Utilisateur utilisateurEnchere, Vente venteEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.utilisateurEnchere = utilisateurEnchere;
		this.venteEnchere = venteEnchere;
	}
	
	public Enchere() {
		super();
	}
	
	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", utilisateurEnchere=" + utilisateurEnchere + ", venteEnchere="
				+ venteEnchere + "]";
	}
	
}

