package com.eat.services;

import java.io.File;
import java.util.Map;

public class RecipeService {
	
	//DM 10/13/19: Do we make a recipe a Map<String,Object> and return whole recipe after creation?
	public int addRecipe(String name, String descrip, File image, double rating, double timeReq, double servings, int directionCount) throws Exception {return 0;}
	public void addIngredients(int uniqueID, Map<String,Double> ingredients) throws Exception {}
	public void addSteps(int uniqueID, Map<Integer,String> directions) throws Exception {}
	
	private void addIngredient(int uniqueID, String ingredient, Double amount) {}
	private void addStep(int uniqueID, Integer stepNum, String direction) {}
	private int generateUniqueID() {return 0;}
	
	public void setRating(int uniqueID, double rating) throws Exception{}
	
	public String getName(int uniqueID) throws Exception {return null;}
	public String getDescription(int uniqueID) throws Exception {return null;}
	public File getImage(int uniqueID) throws Exception {return null;}
	public double getRating(int uniqueID) throws Exception {return 0;}
	public Map<String, Double> getIngredients(int uniqueID) throws Exception {return null;}
	public Map<Integer, String> getSteps(int uniqueID) throws Exception {return null;}
}
