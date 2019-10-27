package com.eat.services.Exceptions;

public class InvalidCombinationException extends Exception {

	/**
	 * default id
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCombinationException()
	{
		super("Incorrect username and password combination");
	}

}
