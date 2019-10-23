package com.eat.services;

import java.io.File;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.eat.IEatMyFood;
import com.eat.mysql.DBInteractor;

public class RecipeService {
	
	DBInteractor db;
	int i = 0;
	private String COMMA = ",";
	
	public RecipeService() throws Exception {
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
	
	//Will add a recipe to the database and return the newly added recipe
	//Throws exceptions when name or description are not present or if DBInteractor
	//fails
	public Map<String,String> addRecipe(String name, String descrip, File image, double rating, double serving, double timeReq) throws Exception {
		if(name == null || descrip == null) {
			throw new Exception("Attempting to add corrupted recipe to database");
		}
		//need to get uniqueID here
		//for now a global session counter as placeholder
		String SQL = "INSERT INTO recipes(recipeID, name, rating, summary, imagepath, serving, timeReq) VALUES (" + ++i + COMMA + name + COMMA + rating + COMMA + descrip + COMMA + ((image == null) ? "null" : image.toPath()) + COMMA + serving + COMMA + timeReq + ")";
		
		try {
			db.executeStatement(SQL);
		} catch(Exception e) {
			throw e;
		}
		
		return getRecipe(Integer.toString(i));
	}
	
	//Will return the recipe searched for based on uniqueID, if uniqueID does not exist
	//will return an empty HashMap
	public Map<String,String> getRecipe(String uniqueID) throws Exception {
		String SQL = "SELECT * FROM recipes WHERE recipeID='" + uniqueID + "'";
		try {
			ResultSet rs = db.executeQuery(SQL);
			HashMap<String,String> returnMap = new HashMap<String,String>();
			if(rs != null) {
				if(rs.getFetchSize() > 1) {
					throw new Exception("Results set too large");
				}
				while(rs.next()) {
					returnMap.put(IRecipe.RECIPEID, rs.getString("recipeID"));
					returnMap.put(IRecipe.NAME, rs.getString("name"));
					returnMap.put(IRecipe.RATING, rs.getString("rating"));
					returnMap.put(IRecipe.SUMMARY, rs.getString("summary"));
					returnMap.put(IRecipe.IMAGEPATH, rs.getString("imagepath"));
					returnMap.put(IRecipe.SERVING, rs.getString("serving"));
					returnMap.put(IRecipe.TIMEREQ, rs.getString("timeReq"));
				}
				return returnMap;
			} else {
				return new HashMap<String,String>();
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public void addIngredients(int uniqueID, Map<String,Double> ingredients) throws Exception {}
	public void addSteps(int uniqueID, Map<Integer,String> directions) throws Exception {}
	
	private void addIngredient(int uniqueID, String ingredient, Double amount) {}
	private void addStep(int uniqueID, Integer stepNum, String direction) {}
	private int generateUniqueID() {return 0;}
	
	public void setRating(int uniqueID, double rating) throws Exception{}
	
	public Map<String, Double> getIngredients(int uniqueID) throws Exception {return null;}
	public Map<Integer, String> getSteps(int uniqueID) throws Exception {return null;}
}
