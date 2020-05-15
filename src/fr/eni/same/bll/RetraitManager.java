package fr.eni.same.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.same.bll.interfaceManager.RetraitManagerInterface;
import fr.eni.same.bll.interfaceManager.SelectMAnagerInterface;
import fr.eni.same.bo.Retrait;
import fr.eni.same.dal.DALFactory;
import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;

public class RetraitManager extends AdresseManager implements RetraitManagerInterface, SelectMAnagerInterface<Retrait> {
	
	private static RetraitManager instance;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private RetraitManager() {
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
	@Override
	public void insert(Retrait t) throws BllException {
		String msgErreur = controleUpdateAndInsert(t);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		try {
			DALFactory.getRetraitDAOJdbcImpl().insert(t);
		} catch (DALException e) {
			throw new BllException("Impossible d'inserer en base de donnée le lieu de retrait");
		}
	}

	@Override
	public void update(Retrait t) throws BllException {
		String msgErreur = controleUpdateAndInsert(t);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		try {
			DALFactory.getRetraitDAOJdbcImpl().insert(t);
		} catch (DALException e) {
			throw new BllException("Impossible de modifier en base de donnée le lieu de retrait");
		}
	}

	@Override
	public void delete(Retrait t) throws BllException {
		String msgErreur = retraitNull(t);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		
		try {
			DALFactory.getRetraitDAOJdbcImpl().delete(t);
		} catch (DALException e) {
			throw new BllException("Impossible de supprimer en base de donnée le lieu de retrait");
		}
	}

	@Override
	public Retrait select(int id) throws BllException {
		String msgErreur = noRetraitNull(id);
		if (!msgErreur.equals("")){
			throw new BllException(msgErreur);
		}
		
		Retrait retrait = null;
		try {
			retrait = DALFactory.getRetraitDAOJdbcImpl().select(id);
		} catch (DALException e) {
			throw new BllException("Impossible de recupérer en base de donnée le lieu de retrait");
		}
		return retrait;
	}

	@Override
	public List<Retrait> selectAll() throws BllException {
		List<Retrait> listRetraits = new ArrayList<Retrait>();
		try {
			listRetraits = DALFactory.getRetraitDAOJdbcImpl().selectAll();
		} catch (DALException e) {
			throw new BllException("Impossible de recupérer en base de donnée le lieu de retrait");
		}
		return listRetraits;
	}

	private String controleUpdateAndInsert(Retrait t) throws BllException {
		String msgErreur = "";
		msgErreur += retraitNull(t);
		msgErreur += rueLongueurCorrect(t.getRue());
		msgErreur += villeLongueurCorrect(t.getVille());
		msgErreur += codePostalLongueurCorrect(t.getCodePostal());
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
