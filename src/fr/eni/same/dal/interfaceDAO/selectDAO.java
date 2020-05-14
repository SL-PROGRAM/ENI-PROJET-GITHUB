package fr.eni.same.dal.interfaceDAO;

import fr.eni.same.exception.DALException;

public interface selectDAO<T>  {
	
	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws DALException
	 */
	public T select(int id) throws DALException;
	
}
