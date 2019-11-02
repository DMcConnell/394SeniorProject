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
	private String COMMA = ",";
	private String QUOTE = "'";
	
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
	
	private int getIndex() throws Exception {
		String SQL = "SELECT recipeID from recipes ORDER BY recipeID DESC";
		try {
			ResultSet rs = db.executeQuery(SQL);
			if(!rs.next()) return 0;
			return rs.getInt("recipeID") + 1;
		} catch(Exception e) {
			throw e;
		}
	}
	
	//Will add a recipe to the database and return the newly added recipe
	//Throws exceptions when name or description are not present or if DBInteractor
	//fails
	public Map<String,String> addRecipe(String name, String descrip, File image, String author, double serving, double timeReq) throws Exception {
		if(name == null || descrip == null) {
			throw new Exception("Attempting to add corrupted recipe to database");
		}
		String recipeID = Integer.toString(getIndex());
		String SQL = "INSERT INTO recipes(recipeID, name, author, summary, imagepath, servings, timeReq) VALUES ("
		+ QUOTE + recipeID + QUOTE + COMMA + QUOTE + name + QUOTE + COMMA + QUOTE + author + QUOTE + COMMA + QUOTE + descrip + QUOTE 
		+ COMMA + QUOTE + ((image == null) ? "null" : image.toPath()) + QUOTE + COMMA + serving + COMMA + timeReq + ")";
		
		try {
			db.executeStatement(SQL);
		} catch(Exception e) {
			throw e;
		}
		
		return getRecipe(recipeID);
	}
	
	//Will return the recipe searched for based on uniqueID, if uniqueID does not exist
	//will return an empty HashMap
	public HashMap<String,String> getRecipe(String uniqueID) throws Exception {
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
					returnMap.put(IRecipe.AUTHOR, rs.getString("author"));
					returnMap.put(IRecipe.SUMMARY, rs.getString("summary"));
					returnMap.put(IRecipe.IMAGEPATH, rs.getString("imagepath"));
					returnMap.put(IRecipe.SERVING, rs.getString("servings"));
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
	public LinkedList<Ingredient> addIngredients(String uniqueID, List<Ingredient> ingredients) throws Exception {
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
	private void addIngredient(String uniqueID, String ingredient, Double amount, String units) throws Exception{
		String SQL = "INSERT INTO ingredients(recipeID, ingredient, proportion, units) VALUES (" +
				QUOTE + uniqueID + QUOTE + COMMA + QUOTE + ingredient + QUOTE + COMMA + amount + COMMA + QUOTE + units + QUOTE + ")";
		try {
			db.executeStatement(SQL);
		} catch(Exception e) {
			throw e;
		}
	}
	
	//Gets all ingredients for a given recipe based upon recipe's uniqueID
	public LinkedList<Ingredient> getIngredients(String uniqueID) throws Exception {
		String SQL = "SELECT * FROM ingredients WHERE recipeID='" + uniqueID + "'";
		ResultSet rs;
		try {
			rs = db.executeQuery(SQL);
			
			LinkedList<Ingredient> ret = new LinkedList<Ingredient>();
			while(rs.next()) {
				Ingredient ing = new Ingredient(rs.getString("ingredient"), rs.getDouble("proportion"), rs.getString("units"));
				ret.add(ing);
			}
			return ret;
		} catch(Exception e) {
			throw e;
		}
	}
	
	
	public void addRecipeAllergies(String uniqueID, List<String> allergies) throws Exception {
		Map<String,String> recipe = getRecipe(uniqueID);
		if(recipe.size() == 0) {
			throw new Exception("Recipe does not exist");
		}
		
		try {
			for(String allergy : allergies) {
				addRecipeAllergy(uniqueID, allergy);
			}
		} catch(Exception e) {
			throw e;
		}
	}
	
	private void addRecipeAllergy(String uniqueID, String allergy) throws Exception {
		String SQL = "INSERT INTO recipeAllergies(recipeID, allergy) VALUES ('" + uniqueID +
						QUOTE + COMMA + QUOTE + allergy + QUOTE + ")";
		try {
			db.executeStatement(SQL);
		} catch(Exception e) {
			throw e;
		}
	}
	
	public LinkedList<String> getRecipeAllergies(String uniqueID) throws Exception {
		String SQL = "SELECT * FROM recipeAllergies WHERE recipeID = '" + uniqueID + "'";
		try {
			ResultSet rs = db.executeQuery(SQL);
			LinkedList<String> recipeAllergies = new LinkedList<String>();
			while(rs.next()) {
				recipeAllergies.add(rs.getString("allergy"));
			}
			return recipeAllergies;
		} catch(Exception e) {
			throw e;
		}
	}
	
	
	//public void editRecipe(int uniqueID, String changedProp, String changedValue) throws Exception {}
	//public LinkedList<Ingredient> editIngredients(int uniqueID, List<Ingredient>) throws Exception {}
	//public Map<Integer, Step> editSteps(int uniqueID, Map<Integer,String> newSteps) throws Exception {}
	
	public HashMap<Integer,String> addSteps(String uniqueID, Map<Integer,String> steps) throws Exception {
		Map<String,String> recipe = getRecipe(uniqueID);
		if(recipe.size() == 0) {
			throw new Exception("Corrupted recipe passed to addIngredients");
		}
		for(Entry<Integer,String> step: steps.entrySet()) {
			addStep(uniqueID, step.getKey(), step.getValue());
		}
		return getSteps(uniqueID);
		
	}
	
	private void addStep(String uniqueID, Integer stepNum, String direction) throws Exception {
		String SQL = "INSERT INTO directions(recipeID, stepNum, direction) VALUES (" +
				QUOTE + uniqueID + QUOTE + COMMA + stepNum + COMMA + QUOTE + direction + QUOTE + ")";
		try {
			db.executeStatement(SQL);
		} catch(Exception e) {
			throw e;
		}
	}
	
	public HashMap<Integer, String> getSteps(String uniqueID) throws Exception {
		String SQL = "SELECT * FROM directions WHERE recipeID = '" + uniqueID + "' ORDER BY stepNum ASC";
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
	
	public LinkedList<HashMap<String,String>> search(String searchText) throws Exception {
		String SQL = "Select recipes.recipeID, recipes.name, ingredients.ingredient from recipes inner join ingredients on (recipes.recipeID = ingredients.recipeID)" +
						"where recipes.name like '%" + searchText + "%' OR ingredients.ingredient LIKE '%" + searchText + "%'"; 
		try {
			ResultSet rs = db .executeQuery(SQL);
			LinkedList<HashMap<String,String>> retSearch = new LinkedList<HashMap<String,String>>();
			List<String> ids = new LinkedList<String>();
			while(rs.next()) {
				//System.out.println(rs.getString("recipes.recipeID") + "\t" + rs.getString("recipes.name") + "\t" + rs.getString("ingredients.ingredient"));
				ids.add(rs.getString("recipes.recipeID"));
				//System.out.println(ids);
			}
			for(String id : ids) { retSearch.add(getRecipe(id)); }
			return retSearch;
		} catch(Exception e) {
			throw e;
		}
	}
}
