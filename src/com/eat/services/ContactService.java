package com.eat.services;

import java.util.List;

public class ContactService {
	
	public void registerUser(String username, String password, String name) {}
	private String hashPassword(String unhashed) {return null;}
	public void login(String username, String password) throws Exception {}
	
	public void addAllergy (String username, String allergy) throws Exception {}
	public void removeAllergy (String username, String allergy) throws Exception {}
	
	public String getName(String username) throws Exception {return null;}
	public List<String> getAllergies(String username) throws Exception {return null;}
	
}
