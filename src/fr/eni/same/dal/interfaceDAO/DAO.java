package fr.eni.same.dal.interfaceDAO;


import java.util.List;

import fr.eni.same.exception.DALException;

/**
 * DAO générique d'un CRUD, toutes les boDAO devront extends cette interface
 * 
 * @author sl
 *
 * @param <T>
 */

public interface DAO<T> {
	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws DALException
	 */
	public void insert(T t) throws DALException;


	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws DALException
	 */
	public void update(T t) throws DALException;


	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws DALException
	 */
	public void delete(T t) throws DALException;

	
	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws DALException
	 */
	public List<T> selectAll() throws DALException;
	  
	
}
