package com.eat.mysql;

import com.eat.IEatMyFood;

public class CreateRecipeTables {
	
	public static String createRecipeTable() {
		return "CREATE TABLE recipes" + 
				"( recipeID INTEGER(12), name VARCHAR(255), author VARCHAR(32), summary VARCHAR(255), imagepath VARCHAR(255)," + 
				"servings INTEGER(4), timeReq INTEGER(4), PRIMARY KEY (recipeID) )";
	}
	
	public static String createDirectionsTable() {
		return "CREATE TABLE directions" +
				"( recipeID INTEGER(12), stepNum INTEGER(2), direction VARCHAR(255),"
				+ "FOREIGN KEY (recipeID) REFERENCES recipes(recipeID))";
	}
	
	public static String createIngredientsTable() {
		return "CREATE TABLE ingredients" +
				"( recipeID INTEGER(12), ingredient VARCHAR(64), proportion DECIMAL(8,3),"
				+ "units VARCHAR(32), FOREIGN KEY (recipeID) REFERENCES recipes(recipeID))";
	}
	
	public static String createRecipeAllergyTable() {
		return "CREATE TABLE recipeAllergies" +
				"( recipeID INTEGER(12), allergy VARCHAR(32), FOREIGN KEY (recipeID) " +
				"REFERENCES recipes(recipeID))";
	}
	
	public static String createPantryTable() {
		return "CREATE TABLE pantry (username VARCHAR(25), pantryItem VARCHAR(64), "
				+ "PRIMARY KEY (username, pantryItem))";
	}
	
	public static String createFavoritesTable() {
		return "CREATE TABLE favorites " + 
				"(entryID INTEGER(20), " + 
				"username VARCHAR(25), " + 
				"recipeID INTEGER(12)," + 
				"FOREIGN KEY (username) REFERENCES emfUsers(username), " + 
				"FOREIGN KEY (recipeID) REFERENCES recipes(recipeID))";
	}
	
	public static String createPantryTable1() {
		return "CREATE TABLE pantry (" + 
				"username VARCHAR(25)," + 
				"steak INTEGER(1)," + 
				"groundbeef INTEGER(1)," + 
				"turkey INTEGER(1)," + 
				"grndturkey INTEGER(1)," + 
				"chicken INTEGER(1)," + 
				"eggs INTEGER(1)," + 
				"salmon INTEGER(1)," + 
				"tuna INTEGER(1)," + 
				"catfish INTEGER(1)," + 
				"lobster INTEGER(1)," + 
				"crablegs INTEGER(1)," + 
				"veel INTEGER(1)," + 
				"groundchicken INTEGER(1)," + 
				"calbot INTEGER(1)," + 
				"lamb INTEGER(1)," + 
				"pork INTEGER(1)," + 
				"tofu INTEGER(1)," + 
				"deer INTEGER(1)," + 
				"elk INTEGER(1)," + 
				"ostrich INTEGER(1)," + 
				"shellfish INTEGER(1)," + 
				"plantbasedMeat INTEGER(1)," + 
				"tomatoes INTEGER(1)," + 
				"peas INTEGER(1)," + 
				"greenpeppers INTEGER(1)," + 
				"redpeppers INTEGER(1)," + 
				"orangepeppers INTEGER(1)," + 
				"bananapeppers INTEGER(1)," + 
				"kidneybeans INTEGER(1)," + 
				"lentils INTEGER(1)," + 
				"blackbeans INTEGER(1)," + 
				"chickpeas INTEGER(1)," + 
				"pintobeans INTEGER(1)," + 
				"cabbage INTEGER(1)," + 
				"cauliflower INTEGER(1)," + 
				"spinach INTEGER(1)," + 
				"kale INTEGER(1)," + 
				"celery INTEGER(1)," + 
				"broccoli INTEGER(1)," + 
				"carrots INTEGER(1)," + 
				"anise INTEGER(1)," + 
				"basil INTEGER(1)," + 
				"chamomile INTEGER(1)," + 
				"dill INTEGER(1)," + 
				"oregano INTEGER(1)," + 
				"parsley INTEGER(1)," + 
				"rosemary INTEGER(1)," + 
				"sage INTEGER(1)," + 
				"lettuce INTEGER(1)," + 
				"arugula INTEGER(1)," + 
				"mushrooms INTEGER(1)," + 
				"onions INTEGER(1)," + 
				"garlic INTEGER(1)," + 
				"leeks INTEGER(1)," + 
				"beets INTEGER(1)," + 
				"jalapeno INTEGER(1)," + 
				"paprika INTEGER(1)," + 
				"tabascopepper INTEGER(1)," + 
				"ginger INTEGER(1)," + 
				"cayennepepper INTEGER(1)," + 
				"radish INTEGER(1)," + 
				"sweetPotatoe INTEGER(1)," + 
				"potatoes INTEGER(1)," + 
				"butternutSquash INTEGER(1)," + 
				"cucumber INTEGER(1)," + 
				"blackpepper INTEGER(1)," + 
				"tablesalt INTEGER(1)," + 
				"seasalt INTEGER(1)," + 
				"nutmeg INTEGER(1)," + 
				"RotiniNoodle INTEGER(1)," + 
				"spaghettiNoodle INTEGER(1)," + 
				"ravioliNoodle INTEGER(1)," + 
				"angelhairNoodle INTEGER(1)," + 
				"lasagneNoodle INTEGER(1)," + 
				"cavatappiNoodle INTEGER(1)," + 
				"rigatoniNoodle INTEGER(1)," + 
				"linguineNoodle INTEGER(1)," + 
				"penneNoodle INTEGER(1)," + 
				"PRIMARY KEY (username), FOREIGN KEY (username) REFERENCES emfUsers(username))";
	}
	
	public CreateRecipeTables () {
        System.out.println("Attempting to connect...");
        DBInteractor db;

        try {
	        if(IEatMyFood.MAINDB) {
	        	db = new DBInteractor("jdbc:mysql://34.223.151.87:3306/javabase", "remoteu", "password");
	        }
	        else {
	            db = new DBInteractor("jdbc:mysql://18.190.142.138:3306/javabase", "remoteu", "password");
	        }
        } catch(Exception e) {
        	e.printStackTrace();
        	return;
        }
        
        System.out.print("Creating recipe table... ");
        try {
        	db.executeStatement(CreateRecipeTables.createRecipeTable());
        	System.out.println("Success");
        } catch(Exception e) {
        	System.out.println("Failed");
        	e.printStackTrace();
        }
        
        System.out.print("Creating directions table... ");
        try {
        	db.executeStatement(CreateRecipeTables.createDirectionsTable());
        	System.out.println("Success");
        } catch(Exception e) {
        	System.out.println("Failed");
        	e.printStackTrace();
        }
        
        System.out.print("Creating ingredients table... ");
        try {
        	db.executeStatement(CreateRecipeTables.createIngredientsTable());
        	System.out.println("Success");
        } catch(Exception e) {
        	System.out.println("Failed");
        	e.printStackTrace();
        }
        
        System.out.print("Creating favorites table... ");
        try {
        	db.executeStatement(CreateRecipeTables.createFavoritesTable());
        	System.out.println("Success");
        } catch(Exception e) {
        	System.out.println("Failed");
        	e.printStackTrace();
        }
        
        System.out.print("Creating recipeAllergies table... ");
        try {
        	db.executeStatement(CreateRecipeTables.createRecipeAllergyTable());
        	System.out.println("Success");
        } catch(Exception e) {
        	System.out.println("Failed");
        	e.printStackTrace();
        }
        
        System.out.print("Creating pantry table... ");
        try {
        	db.executeStatement(CreateRecipeTables.createPantryTable());
        	System.out.println("Success");
        } catch(Exception e) {
        	System.out.println("Failed");
        	e.printStackTrace();
        }
        
        try {
        	db.closeEverything();
        } catch(Exception e) {
        	e.printStackTrace();
        }
        System.out.println("All tables created");
	}
}
