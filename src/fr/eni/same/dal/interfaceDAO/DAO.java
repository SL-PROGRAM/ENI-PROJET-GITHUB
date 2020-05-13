package fr.eni.same.dal;


import java.util.List;

import fr.eni.same.exception.BusinessException;

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
	 * @throws BusinessException
	 */
	public void insert(T t) throws BusinessException;


	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws BusinessException
	 */
	public void update(T t) throws BusinessException;


	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws BusinessException
	 */
	public void delete(T t) throws BusinessException;


	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws BusinessException
	 */
	public T select(int id) throws BusinessException;
	
	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws BusinessException
	 */
	public List<T> selectAll() throws BusinessException;
	
	
}
