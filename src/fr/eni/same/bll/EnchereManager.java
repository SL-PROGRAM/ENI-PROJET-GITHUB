package fr.eni.same.bll;

import java.util.List;

import fr.eni.same.bll.interfaceManager.EnchereManagerInterface;
import fr.eni.same.bll.interfaceManager.SelectMAnagerInterface;
import fr.eni.same.bo.Enchere;
import fr.eni.same.exception.BllException;

public class EnchereManager implements EnchereManagerInterface {

	private static EnchereManager instance;

	/**
	 * constructeur privé pour ne pas permettre la création d'une autre instance de la classe
	 */
    private EnchereManager() {
	}

    /**
     * methode Get pour récupérer l'instance et la créer si elle n'existe pas
     * @return
     */
    public static synchronized  EnchereManager getEnchereManager () {
        if(instance == null){
            instance = new EnchereManager();
        }
        return instance;
    }
    
    
	@Override
	public void insert(Enchere t) throws BllException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Enchere t) throws BllException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Enchere t) throws BllException {
		// TODO Auto-generated method stub

	}

	@Override
	public Enchere select(int noVente, int noUtlisateur) throws BllException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> selectAll() throws BllException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
