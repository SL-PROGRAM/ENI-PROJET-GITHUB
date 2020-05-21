package fr.eni.same.bll;

import java.util.List;
import fr.eni.same.bo.Retrait;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;
import fr.eni.same.helpers.AdresseUtils;

/**
 * 
 * @author sl
 * @author etienne
 */
public class RetraitManager{
	
	private static RetraitManager instance;
	private List<Retrait> listeRetraits;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private RetraitManager() {
    	if(listeRetraits == null) {
    		try {
				listeRetraits = DALFactory.getRetraitDAOJdbcImpl().selectAll();
			} catch (DALException e) {
				e.printStackTrace();
			}
    	}
	}

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized  RetraitManager getRetraitManager () {
        if(instance == null){
            instance = new RetraitManager();
        }
        return instance;
    }
	
	public void insert(Retrait t) throws BllException {
		String msgErreur = controleUpdateAndInsert(t);
		if (msgErreur.equals("")){
		} try {
			DALFactory.getRetraitDAOJdbcImpl().insert(t);
			listeRetraits.add(t);
		} catch (DALException e) {
			throw new BllException("Impossible d'insérer en base de donnée le lieu de retrait");
		}
		System.out.println("Retrait : Insertion réalisée");
	}

	
	public void update(Retrait t) throws BllException {
		String msgErreur = controleUpdateAndInsert(t);
		if (msgErreur.equals("")){
		} try {
			DALFactory.getRetraitDAOJdbcImpl().update(t);
			for (int i = 0; i < listeRetraits.size(); i++) {
				if(listeRetraits.get(i).getVente().getNoVente() == t.getVente().getNoVente()) {
					listeRetraits.get(i).setVille(t.getVille());
					listeRetraits.get(i).setCodePostal(t.getCodePostal());
					listeRetraits.get(i).setRue(t.getRue());
				}
			}
		} catch (DALException e) {
			throw new BllException("Impossible de modifier en base de donnée le lieu de retrait");
		}
		System.out.println("Retrait : Update réalisé.");
	}

	
	public void delete(Retrait t) throws BllException {
		String msgErreur = retraitNull(t);
		if (msgErreur.equals("")){
			try {
				DALFactory.getRetraitDAOJdbcImpl().delete(t);
				listeRetraits.remove(t);
			} catch (DALException e) {
				throw new BllException("Impossible de supprimer en base de donnée le lieu de retrait");
			}
		} 
		System.out.println("Retrait : Delete réalisé.");
	}
		

	
	public Retrait select(int id) throws BllException {
		Retrait retrait = null;
		String msgErreur = noRetraitNull(id);
		if (msgErreur.equals("")){
			for (int i = 0; i < listeRetraits.size(); i++) {
				if(listeRetraits.get(i).getVente().getNoVente() == id) {
					retrait = listeRetraits.get(i);
				}
			}
		}
		if(retrait == null) {
			throw new BllException("Aucun retrait n'a été trouvé avec cet identifiant.");
		}
		System.out.println("Retrait : Select réalisé.");
		return retrait;
	}

	
	public List<Retrait> selectAll() throws BllException {
		try {
			listeRetraits = DALFactory.getRetraitDAOJdbcImpl().selectAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return listeRetraits;
	}

	private String controleUpdateAndInsert(Retrait t) throws BllException {
		String msgErreur = "";
		msgErreur += retraitNull(t);
		msgErreur += AdresseUtils.rueLongueurCorrect(t.getRue());
		msgErreur += AdresseUtils.villeLongueurCorrect(t.getVille());
		msgErreur += AdresseUtils.codePostalLongueurCorrect(t.getCodePostal());
		return msgErreur;
	}
	
	private String retraitNull(Retrait t) throws BllException  {
		String msgErreur = "";
		if (t == null) {
			msgErreur += ("Erreur : null");
		}
		return msgErreur;
	}
	
	
	private String noRetraitNull(int id) throws BllException  {
		String msgErreur = "";
		if (id <= 0) {
			msgErreur += ("Erreur référence interdite");
		}
		return msgErreur;
	}


	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//
	


}
