package fr.eni.same.dal.interfaceDAO;


import fr.eni.same.bo.Enchere;
import fr.eni.same.exception.BusinessException;


/**
 * Interface contenant les informations spécifiques à implémenter pour les enchères
 * @author etienne
 */
public interface EnchereDAO extends DAO<Enchere> {

	Enchere select(int noUtlisateur, int noVente) throws BusinessException;

}
