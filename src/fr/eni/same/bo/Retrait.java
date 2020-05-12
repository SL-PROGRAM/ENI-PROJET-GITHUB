package fr.eni.same.bo;
/**
 * Permet d'enregistrer le lieu de retrait d'une vente
 * une vente n'a pas forcement de lieu de retrait
 * @author sl
 *
 */
public class Retrait {
	private String rue;
	private String codePostal;
	private String ville;
	private Vente vente;
	
	
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public Vente getVente() {
		return vente;
	}
	public void setVente(Vente vente) {
		this.vente = vente;
	}
	
	
	/**
	 * Constructeur complet
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param vente
	 */
	public Retrait(String rue, String codePostal, String ville, Vente vente) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.vente = vente;
	}
	
	public Retrait() {
		super();
	}
	
	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + ", vente=" + vente + "]";
	}
}
