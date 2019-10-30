package com.eat.services;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.eat.IEatMyFood;
import com.eat.mysql.DBInteractor;

public class ContactService {
	
	DBInteractor db;
	int i = 0;
	private String COMMA = ",";
	private String QUOTE = "'";
	
	public ContactService() throws Exception {
        try {
	        if(IEatMyFood.MAINDB) {
	        		db = new DBInteractor("jdbc:mysql://34.223.151.87:3306/javabase", "remoteu", "password");
	        }
	        else {
	            db = new DBInteractor("jdbc:mysql://18.190.142.138:3306/javabase", "remoteu", "password");
	        }
        } catch(Exception e) {
        		throw e;
        }
	}
	
	public void close() throws Exception {
		try {
			db.closeEverything();
		} catch(Exception e) {
			throw e;
		}
	}
	
	//Registers a user if the user does not already exist
	public HashMap<String,String> registerUser(String username, String password, String name, String email) throws Exception {
		if(username.equals("") || password.equals("") || name.equals("") || email.equals("") || !email.contains("@")) {
			throw new Exception("Something was passed wrong to the Contact Service");
		}
		String SQL = "INSERT INTO emfUsers(username, legalname, password, email) VALUES (" +
				QUOTE + username + QUOTE + COMMA + QUOTE + name + QUOTE + COMMA + 
				QUOTE + password + QUOTE + COMMA + QUOTE + email + QUOTE + ")";
		try {
			HashMap<String,String> userCheck = getUser(username);
			if(userCheck.size() != 0) {
				throw new Exception ("User already exists");
			}
			
			db.executeStatement(SQL);
		} catch(Exception e) {
			throw e;
		}
		return getUser(username);
	}
	
	//Gets all information for a user
	public HashMap<String,String> getUser(String username) throws Exception {
		String SQL = "SELECT * FROM emfUsers where username='" + username + "'";
		try {
			ResultSet rs = db.executeQuery(SQL);
			if(rs.getFetchSize() > 1) {
				throw new Exception("Corrupted database error, more than one user returned");
			}
			HashMap<String,String> retUser = new HashMap<String,String>();
			while(rs.next()) {
				retUser.put(IUser.USERNAME, rs.getString("username"));
				retUser.put(IUser.NAME, rs.getString("legalname"));
				retUser.put(IUser.PASSWORD, rs.getString("password"));
				retUser.put(IUser.EMAIL, rs.getString("email"));
			}
			return retUser;
		} catch(Exception e) {
			throw e;
		}
	}
	
	//Login a user, throws an exception if login fails
	public void login(String username, String password) throws Exception {
		if(username.equals("") || password.equals("")) {
			throw new Exception("Username or password was empty in services");
		}
		try {
			HashMap<String,String> user = getUser(username);
			if(user.containsKey(IUser.PASSWORD) && user.get(IUser.PASSWORD).equals(password)) {
				return;
			}
			else {
				throw new Exception("Invalid username or password");
			}
		} catch(Exception e) {
			throw e;
		}
	}
	
	//Adds a list of allergies for a given user
	public void addAllergies (String username, List<String> allergies) throws Exception {
		try {
			HashMap<String,String> user = getUser(username);
			if(user.size() == 0) {
				throw new Exception("User does not exist");
			}
			List<String> userAllergies = getAllergies(username);
			for(String allergy : allergies) {
				if(!userAllergies.contains(allergy)) {
					addAllergy(username, allergy);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Adds a single allergy for a given user
	private void addAllergy (String username, String allergy) throws Exception {
		String SQL = "INSERT INTO allergies(username, allergies) VALUES (" +
				username + COMMA + allergy + ")";
		try {
			db.executeStatement(SQL);
		} catch(Exception e) {
			throw e;
		}
	}
	
	//Gets all allergies from a given user
	public LinkedList<String> getAllergies(String username) throws Exception {
		String SQL = "SELECT * FROM allergies WHERE username='" + username + "'";
		try {
			ResultSet rs = db.executeQuery(SQL);
			LinkedList<String> allergies = new LinkedList<String>();
			while(rs.next()) {
				allergies.add(rs.getString("allergies"));
			}
			return allergies;
		} catch(Exception e) {
			throw e;
		}
	}
	
	//Removes an allergy for a given user
	public void removeAllergy (String username, String allergy) throws Exception {
		try {
			List<String> allergies = getAllergies(username);
			if(allergies.contains(allergy)) {
				String SQL = "DELETE FROM allergies WHERE username = '" + username + "' AND " +
						"allergies = '" + allergy + "'";
				db.executeStatement(SQL);
			}
		} catch(Exception e) {
			throw e;
		}
	}
}
