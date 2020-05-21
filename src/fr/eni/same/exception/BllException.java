package fr.eni.same.exception;

import fr.eni.same.helpers.FonctionGenerique;

/**
 * 
 * @author sl
 *
 */
public class BllException extends Exception {

	private static final long serialVersionUID = 1L;

	public BllException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BllException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BllException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BllException(String message) {
//		FonctionGenerique.gestionErreur(message);
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BllException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
