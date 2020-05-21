package fr.eni.same.dal.interfaceDAO;


import fr.eni.same.bo.Enchere;
import fr.eni.same.exception.DALException;


/**
 * Interface contenant les informations spécifiques à implémenter pour les enchères
 * @author etienne
 */
public interface EnchereDAO extends DAO<Enchere> {

	public Enchere select(int noUtlisateur, int noVente) throws DALException;
	public void updateEnchereur(Enchere t,int ancienEnchereur ,int nouvelEnchereur) throws DALException;
}
