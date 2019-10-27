package com.eat.services.Exceptions;

public class UsernameTakenException extends Exception {

	/**
	 * default id
	 */
	private static final long serialVersionUID = 1L;
	
	public UsernameTakenException()
	{
		super("There is already an account associated with this username");
	}

}
