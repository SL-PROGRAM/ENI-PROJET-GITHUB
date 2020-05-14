package fr.eni.same.bll;

import java.util.List;

import fr.eni.same.bll.interfaceManager.RetraitManagerInterface;
import fr.eni.same.bll.interfaceManager.SelectMAnagerInterface;
import fr.eni.same.bo.Retrait;
import fr.eni.same.bo.Utilisateur;
import fr.eni.same.exception.BllException;

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
		RueLongueurCorrect(t.getRue());
	}

	@Override
	public void update(Retrait t) throws BllException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Retrait t) throws BllException {
		// TODO Auto-generated method stub

	}

	@Override
	public Retrait select(int id) throws BllException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Retrait> selectAll() throws BllException {
		// TODO Auto-generated method stub
		return null;
	}

	private void controleUpdateAndInsert(Utilisateur t) throws BllException {
		RueLongueurCorrect(t.getRue());
		VilleLongueurCorrect(t.getVille());
		CodePostalLongueurCorrect(t.getCodePostal());
	}

	//***********************************************************************************************//
	// * Implementation des méthodes de test avant validation et tentative d'enregistrement en BDD * //
	//***********************************************************************************************//
	


}
