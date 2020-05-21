package fr.eni.same.bll;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.same.bo.Enchere;
import fr.eni.same.bo.Vente;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;

/**
 * BLL - Classe qui contient les méthodes de gestion des enchères
 * @author etienne
 * @author sl
 */
public class EnchereManager {

	private static EnchereManager instance;
	private List<Enchere> listeEncheres;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 * @throws BllException 
	 */
    private EnchereManager() throws BllException {
    	if(listeEncheres == null) {
			try {
				listeEncheres = DALFactory.getEnchereDAOJdbcImpl().selectAll();
			} catch (DALException e) {
				throw new BllException("Impossible de générer la liste des enchères.");
			}
    	}
    }

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized EnchereManager getEnchereManager () {
        if(instance == null) {
            try {
				instance = new EnchereManager();
			} catch (BllException e) {
				e.printStackTrace();
			}
        }
        return instance;
    }
    
    /**
     * Méthode d'ajout d'une enchère en base de donnée
     * Ajoute l'entrée dans la base de donnée, puis dans la liste locale
     * @param enchere : l'enchère à tester
     * @throws BllException
     */
	public void insert(Enchere enchere) throws BllException {
		try {
			DALFactory.getEnchereDAOJdbcImpl().insert(enchere);
			listeEncheres.add(enchere);
			Vente vente = VenteManager.getVenteManager().select(enchere.getVenteEnchere().getNoVente());
			VenteManager.getVenteManager().updateAcheteur(vente);
			System.out.println("Enchere : insert réalisé : " + enchere.toString());
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

    /**
     * Méthode d'update d'une enchère en base de donnée
     * Update l'entrée dans la base de donnée, puis dans la liste locale
     * @param enchere : l'enchère à tester
     * @throws BllException
     */
	public void update(Enchere enchere) throws BllException {
		try {
			DALFactory.getEnchereDAOJdbcImpl().update(enchere);
			for(int i = 0; i < listeEncheres.size(); i++) {
				if(listeEncheres.get(i).getUtilisateurEnchere().getNoUtilisateur() == enchere.getUtilisateurEnchere().getNoUtilisateur()
						&& listeEncheres.get(i).getVenteEnchere().getNoVente() == enchere.getVenteEnchere().getNoVente()) {
					listeEncheres.get(i).setDateEnchere(enchere.getDateEnchere());
					listeEncheres.get(i).setUtilisateurEnchere(enchere.getUtilisateurEnchere());
					listeEncheres.get(i).setVenteEnchere(enchere.getVenteEnchere());
				}
			}
			Vente vente = VenteManager.getVenteManager().select(enchere.getVenteEnchere().getNoVente());
			VenteManager.getVenteManager().updateAcheteur(vente);
			System.out.println("Enchere : Update réalisé : " + enchere.toString());
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Méthode de suppression d'une Enchère dans la base de donnée
	 * @param enchere : L'enchère à supprimer
	 * @throws BllException
	 */
	public void delete(Enchere enchere) throws BllException {
		try {
			DALFactory.getEnchereDAOJdbcImpl().delete(enchere);
			listeEncheres.remove(enchere);
			System.out.println("Enchere : Delete réalisé : " + enchere.toString());
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode de sélection d'une enchère
	 * @param noVente : le numéro de vente de l'enchère à sélectionner
	 * @param noUtlisateur : le numéro d'utilisateur de l'enchère à sélectionner
	 * @return enchere : l'enchère sélectionnée
	 * @throws BllException
	 */
	public Enchere select(int noVente, int noUtlisateur) throws BllException {
		Enchere enchere = null;
		for (int i = 0; i < listeEncheres.size(); i++) {
			if(listeEncheres.get(i).getUtilisateurEnchere().getNoUtilisateur() == noUtlisateur 
					&& listeEncheres.get(i).getVenteEnchere().getNoVente() == noVente) {
				enchere = listeEncheres.get(i);
				System.out.println("Enchere : Select réalisé : " + enchere.toString());
			}
		}
		if(enchere == null) {
			throw new BllException("Aucune enchère n'a été trouvée avec ces informations.");
		}
		return enchere;
	}

	public boolean chkIfUserExist(int noUtilisateur, int noVente) {
		try {
			listeEncheres = selectAll();
		} catch (BllException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < listeEncheres.size(); i++) {
			if(listeEncheres.get(i).getUtilisateurEnchere().getNoUtilisateur() == noUtilisateur
			&& listeEncheres.get(i).getVenteEnchere().getNoVente() == noVente){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Méthode de sélectionner de la liste des enchères
	 * @return listeEncheres : la liste locale des enchères
	 * @throws BllException
	 */
	public List<Enchere> selectAll() throws BllException {
		try {
			listeEncheres = DALFactory.getEnchereDAOJdbcImpl().selectAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return listeEncheres;
	}
	
	/**
	 * Méthode de sélection des enchères en cours
	 * @return enchereEnCours : La liste des enchères en cours qui se terminent plus tard que maintenant
	 * @throws BllException
	 */
	public List<Enchere> selectEnchereEnCours() throws BllException {
		List<Enchere> enchereEnCours = new ArrayList<Enchere>();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		for(Enchere e : listeEncheres){
			if(e.getVenteEnchere().getDateFinEncheres().after(now)) {
				enchereEnCours.add(e);
				System.out.println("Enchere en cours : Select ALL réalisé : " + e.toString());
			}
		}
		return enchereEnCours;
	}
	
	/**
	 * Méthode de sélection des enchères finies
	 * @return enchereFini : La liste des enchères finies
	 * @throws BllException
	 */
	public List<Enchere> selectEnchereFini() throws BllException {
		List<Enchere> enchereFini = new ArrayList<Enchere>();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		for(Enchere e : listeEncheres){
			if(e.getVenteEnchere().getDateFinEncheres().before(now)) {
				enchereFini.add(e);
				System.out.println("Enchere en cours : Select ALL réalisé : " + e.toString());
			}
		}
		return enchereFini;
	}
}