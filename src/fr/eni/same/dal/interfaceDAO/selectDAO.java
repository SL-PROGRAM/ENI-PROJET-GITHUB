package fr.eni.same.dal.interfaceDAO;

import fr.eni.same.exception.BusinessException;

public interface selectDAO<T>  {
	
	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws BusinessException
	 */
	public T select(int id) throws BusinessException;
	
}
