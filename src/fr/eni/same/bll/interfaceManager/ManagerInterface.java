package fr.eni.same.bll.interfaceManager;

import java.util.List;

import fr.eni.same.exception.BllException;
import fr.eni.same.exception.DALException;

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
	 * @throws DALException
	 */
	public void update(T t) throws BllException;


	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws DALException
	 */
	public void delete(T t) throws BllException;


	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws DALException
	 */
	public T select(int id) throws BllException;
	
	/**
	 * En cas d'erreur, le code d'erreur est enregistré dans l'objet businessException.
	 * @param T
	 * @throws DALException
	 */
	public List<T> selectAll() throws BllException;

}
