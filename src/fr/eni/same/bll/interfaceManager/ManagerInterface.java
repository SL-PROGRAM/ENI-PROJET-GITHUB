package fr.eni.same.bll.interfaceManager;

import java.util.List;

import fr.eni.same.exception.BllException;
import fr.eni.same.exception.BusinessException;

/**
 * Interface de gestion des fonctions a ajouter au manager
 * @author sl
 *
 */
public interface ManagerInterface<T> {
	
	public void insert(T t) throws BllException;


	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws BusinessException
	 */
	public void update(T t) throws BllException;


	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws BusinessException
	 */
	public void delete(T t) throws BllException;


	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws BusinessException
	 */
	public T select(int id) throws BllException;
	
	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws BusinessException
	 */
	public List<T> selectAll() throws BllException;

}
