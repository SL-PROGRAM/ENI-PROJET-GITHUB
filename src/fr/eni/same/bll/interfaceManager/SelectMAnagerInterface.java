package fr.eni.same.bll.interfaceManager;

import fr.eni.same.exception.BllException;

public interface SelectManagerInterface<T> {

	T select(int id) throws BllException;
}
