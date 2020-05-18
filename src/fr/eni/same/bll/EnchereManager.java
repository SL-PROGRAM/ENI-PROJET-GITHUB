package fr.eni.same.bll;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import fr.eni.same.bo.Enchere;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;

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
    
	public void insert(Enchere t) throws BllException {
		try {
			DALFactory.getEnchereDAOJdbcImpl().insert(t);
			listeEncheres.add(t);
			System.out.println("Enchere : Select réalisé : " + t.toString());
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	public void update(Enchere t) throws BllException {
		try {
			DALFactory.getEnchereDAOJdbcImpl().update(t);
			for(int i = 0; i < listeEncheres.size(); i++) {
				if(listeEncheres.get(i).getUtilisateurEnchere().getNoUtilisateur() == t.getUtilisateurEnchere().getNoUtilisateur()
						&& listeEncheres.get(i).getVenteEnchere().getNoVente() == t.getVenteEnchere().getNoVente()) {
					listeEncheres.get(i).setDateEnchere(t.getDateEnchere());
					listeEncheres.get(i).setUtilisateurEnchere(t.getUtilisateurEnchere());
					listeEncheres.get(i).setVenteEnchere(t.getVenteEnchere());
				}
			}
			System.out.println("Enchere : Update réalisé : " + t.toString());
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Enchere t) throws BllException {
		try {
			DALFactory.getEnchereDAOJdbcImpl().delete(t);
			listeEncheres.remove(t);
			System.out.println("Enchere : Delete réalisé : " + t.toString());
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	public Enchere select(int noVente, int noUtlisateur) throws BllException {
		Enchere e = null;
		for (int i = 0; i < listeEncheres.size(); i++) {
			if(listeEncheres.get(i).getUtilisateurEnchere().getNoUtilisateur() == noUtlisateur 
					&& listeEncheres.get(i).getVenteEnchere().getNoVente() == noVente) {
				e = listeEncheres.get(i);
			}
			System.out.println("Enchere : Select réalisé : " + e.toString());
		}
		if(e == null) {
			throw new BllException("Aucune enchère n'a été trouvée avec ces informations.");
		}
		return e;
	}
	
	public List<Enchere> selectAll() throws BllException {
		for(Enchere e : listeEncheres){
			System.out.println("Enchere : Select ALL réalisé : " + e.toString());
		}
		return listeEncheres;
	}
	
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
}