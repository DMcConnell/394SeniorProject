package com.eat.services.Exceptions;

public class EmailTakenException extends Exception {

	/**
	 * default id
	 */
	private static final long serialVersionUID = 1L;
	
	public EmailTakenException()
	{
		super("There is already an account associated with this email address");
	}
		
}
