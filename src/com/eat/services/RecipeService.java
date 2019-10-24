package com.eat.services;

import java.io.File;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.eat.IEatMyFood;
import com.eat.mysql.DBInteractor;
import com.eat.support.Ingredient;

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
		
		return getRecipe(i);
	}
	
	//Will return the recipe searched for based on uniqueID, if uniqueID does not exist
	//will return an empty HashMap
	public HashMap<String,String> getRecipe(int uniqueID) throws Exception {
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
	
	//Will add ingredients into the ingredients database table directly tied to
	//a recipe via uniqueID
	public LinkedList<Ingredient> addIngredients(int uniqueID, List<Ingredient> ingredients) throws Exception {
		Map<String,String> recipe = getRecipe(uniqueID);
		if(recipe.size() == 0) {
			throw new Exception("Corrupted recipe passed to addIngredients");
		}
		for(Ingredient ingredient : ingredients) {
			addIngredient(uniqueID, ingredient.getName(), ingredient.getAmount(), ingredient.getUnit());
		}
		return getIngredients(uniqueID);
	}
	
	//Adds each ingredient and it's  related proportion and unit
	private void addIngredient(int uniqueID, String ingredient, Double amount, String units) throws Exception{
		String SQL = "INSERT INTO ingredients(recipeID, ingredient, proportion, units) VALUES (" +
				uniqueID + COMMA + ingredient + COMMA + amount + COMMA + units + ")";
		try {
			db.executeStatement(SQL);
		} catch(Exception e) {
			throw e;
		}
	}
	
	//Gets all ingredients for a given recipe based upon recipe's uniqueID
	public LinkedList<Ingredient> getIngredients(int uniqueID) throws Exception {
		String SQL = "SELECT * FROM ingredients WHERE uniqueID='" + uniqueID + "'";
		ResultSet rs;
		try {
			rs = db.executeQuery(SQL);
			
			LinkedList<Ingredient> ret = new LinkedList<Ingredient>();
			while(rs.next()) {
				Ingredient ing = new Ingredient(rs.getString("ingredient"), rs.getDouble("proportion"), rs.getString("unit"));
				ret.add(ing);
			}
			return ret;
		} catch(Exception e) {
			throw e;
		}
	}
	
	
	//public void editRecipe(int uniqueID, String changedProp, String changedValue) throws Exception {}
	//public LinkedList<Ingredient> editIngredients(int uniqueID, List<Ingredient>) throws Exception {}
	//public Map<Integer, Step> editSteps(int uniqueID, Map<Integer,String> newSteps) throws Exception {}
	
	public HashMap<Integer,String> addSteps(int uniqueID, Map<Integer,String> steps) throws Exception {
		Map<String,String> recipe = getRecipe(uniqueID);
		if(recipe.size() == 0) {
			throw new Exception("Corrupted recipe passed to addIngredients");
		}
		for(Entry<Integer,String> step: steps.entrySet()) {
			addStep(uniqueID, step.getKey(), step.getValue());
		}
		return getSteps(uniqueID);
		
	}
	
	private void addStep(int uniqueID, Integer stepNum, String direction) throws Exception {
		String SQL = "INSERT INTO directions(recipeID, stepNum, direction) VALUES (" +
				uniqueID + COMMA + stepNum + COMMA + direction + ")";
		try {
			db.executeStatement(SQL);
		} catch(Exception e) {
			throw e;
		}
	}
	
	public HashMap<Integer, String> getSteps(int uniqueID) throws Exception {
		String SQL = "SELECT * FROM directions WHERE uniqueID = '" + uniqueID + "' ORDER BY stepNum ASC";
		ResultSet rs;
		try {
			rs = db.executeQuery(SQL);
			
			HashMap<Integer, String> ret = new HashMap<Integer, String>();
			while(rs.next()) {
				ret.put(rs.getInt("stepNum"), rs.getString("direction"));
			}
			return ret;
		} catch(Exception e) {
			throw e;
		}
	}
}
