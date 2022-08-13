package dev.lroman.easychileanrutvalidator.Exception;

/**
 * general exception to rut problems
 * 
 * @author lroman
 *
 */
public class RutException extends Exception{

	private static final long serialVersionUID = 1L;

	public RutException(String error) {
		super(error);
	}

}
