package com.eat.services;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.eat.IEatMyFood;
import com.eat.mysql.DBInteractor;
import com.eat.services.Exceptions.InvalidCombinationException;
import com.eat.services.Exceptions.UsernameTakenException;
import com.eat.support.Support;

public class ContactService {
	
	DBInteractor db;
	int i = 0;
	private String COMMA = ",";
	private String QUOTE = "'";
	private String loggedInUser = "No one logged in";
	
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
	
	//
	public String getSelfID() {
		return loggedInUser;
	}
	
	//Registers a user if the user does not already exist
	public HashMap<String,String> registerUser(String username, String password, String name, String email) throws Exception {
		if(username.equals("") || password.equals("") || name.equals("") || email.equals("") || !email.contains("@")) {
			throw new Exception("Something was passed wrong to the Contact Service");
		}
		String SQL = "INSERT INTO emfUsers(username, legalname, password, email) VALUES (" +
				QUOTE + username + QUOTE + COMMA + QUOTE + name + QUOTE + COMMA + 
				QUOTE + Support.encryptPass(password) + QUOTE + COMMA + QUOTE + email + QUOTE + ")";
		try {
			HashMap<String,String> userCheck = getUser(username);
			if(userCheck.size() != 0) {
				throw new UsernameTakenException();
			}
			
			db.executeStatement(SQL);
		} catch(Exception e) {
			throw e;
		}
		HashMap<String,String > regUser = getUser(username);
		loggedInUser = regUser.get(IUser.USERNAME);
		return regUser;
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
	
	public HashMap<String,String> getUserFromEmail(String email) throws Exception {
		String SQL = "SELECT * FROM emfUsers where email='" + email + "'";
		try {
			ResultSet rs = db.executeQuery(SQL);
			try {
				rs.next();
				return getUser(rs.getString("username"));
			} catch (Exception e) {
				throw new Exception("No user registered with this email.");
			}
			
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
			String encryptedPassword = Support.encryptPass(password);
			if(user.containsKey(IUser.PASSWORD) && user.get(IUser.PASSWORD).equals(encryptedPassword)) {
				loggedInUser = user.get(IUser.USERNAME);
				return;
			}
			else {
				throw new InvalidCombinationException();
			}
		} catch(Exception e) {
			throw e;
		}
	}
	
	public void changeUsername(String oldUsername, String newUsername) throws Exception {
		if(oldUsername.equals("") || newUsername.equals("")) {
			throw new Exception("Something went wrong being passed to services");
		}
		try {
			HashMap<String,String> user = getUser(newUsername);
			if(user.size() != 0) {
				throw new Exception("This username has already been taken");
			}
			String SQL = "UPDATE emfUsers SET username = '" + newUsername + "' "
					+ "WHERE username = '"  + oldUsername + "'";
			db.executeStatement(SQL);
			loggedInUser = newUsername;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void changePassword(String username, String oldPassword, String newPassword) throws Exception {
		try {
			HashMap<String,String> user = getUser(username);
			if(user.size() == 0) {
				throw new Exception("User does not exist");
			}
			if(!Support.encryptPass(oldPassword).equals(user.get(IUser.PASSWORD))) {
				throw new Exception("Old password was incorrect");
			}
			String SQL = "UPDATE emfUsers SET password = '" + Support.encryptPass(newPassword) +
					"' WHERE username = '" + username + "'";
			db.executeStatement(SQL);
		} catch(Exception e) {
			throw e;
		}
	}
	
	public void forgotPassword(String username, String newPassword) throws Exception {
		try {
			HashMap<String,String> user = getUser(username);
			if(user.size() == 0) {
				throw new Exception("User does not exist");
			}
			String SQL = "UPDATE emfUsers SET password = '" + Support.encryptPass(newPassword) +
					"' WHERE username = '" + username + "'";
			db.executeStatement(SQL);
		} catch(Exception e) {
			throw e;
		}
	}
	
	public void addPantryItems (String username, List<String> pantryItems) throws Exception {
		try {
			HashMap<String,String> user = getUser(username);
			if(user.size() == 0) {
				throw new Exception("User does not exist");
			}
			List<String> pantry = getPantryItems(username);
			for(String pantryItem : pantryItems) {
				if(!pantry.contains(pantryItem)) {
					addPantryItem(username, pantryItem);
				}
			}
		} catch(Exception e) {
			throw e;
		}
	}
	
	private void addPantryItem (String username, String pantryItem) throws Exception {
		try {
			String SQL = "INSERT INTO pantry(username, pantryItem) VALUES (" +
						QUOTE + username + QUOTE + COMMA + QUOTE + pantryItem + QUOTE + ")";
			db.executeStatement(SQL);
		} catch(Exception e) {
			throw e;
		}
	}
	
	public LinkedList<String> getPantryItems (String username) throws Exception {
		String SQL = "SELECT * FROM pantry WHERE username = '" + username + "'";
		try {
			ResultSet rs = db.executeQuery(SQL);
			LinkedList<String> pantryItems = new LinkedList<String>();
			while(rs.next()) {
				pantryItems.add(rs.getString("pantryItem"));
			}
			return pantryItems;
		} catch(Exception e) {
			throw e;
		}
	}
	
	public void deletePantryItem(String username, String pantryItem) throws Exception {
		List<String> pantryItems = getPantryItems(username);
		try {
			if(pantryItems.contains(pantryItem)) {
				String SQL = "DELETE FROM pantry WHERE username = '" + username + "' AND " +
							"pantryItem = '" + pantryItem + "'";
				db.executeStatement(SQL);
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
			throw e;
		}
		
	}
	
	//Adds a single allergy for a given user
	private void addAllergy (String username, String allergy) throws Exception {
		String SQL = "INSERT INTO allergies(username, allergies) VALUES (" +
				QUOTE + username +QUOTE +  COMMA + QUOTE + allergy + QUOTE + ")";
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
	
	//Saves a favorite recipeID into favorites table for later recall
	public void addFavorite(String username, String recipeID) throws Exception {
		if(username.equals("") || recipeID.equals("")) {
			throw new Exception("Something was passed wrong to ContactService");
		}
		
		String SQL = "INSERT INTO favorites(username, recipeID) VALUES (" + QUOTE +
				username + QUOTE + COMMA + QUOTE + recipeID + QUOTE + ")";
		try {
			db.executeStatement(SQL);
		} catch(Exception e) {
			throw e;
		}
	}
	
	//Returns a list of favorited recipeIDs
	public LinkedList<String> getFavorites(String username) throws Exception {
		try {
			String SQL = "SELECT * FROM favorites WHERE username = '" + username + "'";
			ResultSet rs = db.executeQuery(SQL);
			LinkedList<String> retFavorites = new LinkedList<String>();
			while(rs.next()) {
				retFavorites.add(rs.getString("recipeID"));
			}
			return retFavorites;
		} catch(Exception e) {
			throw e;
		}
	}
	
	//Removes a favorite from the favorites table
	public void deleteFavorite(String username, String recipeID) throws Exception {
		try {
			List<String> favorites = getFavorites(username);
			if(!favorites.contains(recipeID)) {
				return;
			}
			String SQL = "DELETE FROM favorites WHERE username='" + username + "' AND " +
					"recipeID='" + recipeID + "'";
			db.executeStatement(SQL);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

