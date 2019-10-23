package com.eat.mysql;

import com.eat.IEatMyFood;

public class CreateUsersTables {
	
	public static String createMainTable() {
        return "CREATE TABLE emfUsers" + 
        		"(username VARCHAR(25), legalname VARCHAR(25), pssword VARCHAR(40),  email VARCHAR(50), " + 
        		"PRIMARY KEY (username))";
	}
	
	public static String createAllergyTable() {
		return "CREATE TABLE allergies" + 
				"(username VARCHAR(25), " + 
				"peanut INTEGER(1), " + 
				"milk INTEGER(1)," + 
				"egg INTEGER(1), " + 
				"shellfish INTEGER(1), " + 
				"fish INTEGER(1), " + 
				"treenuts INTEGER(1), " + 
				"wheat INTEGER(1), " + 
				"soy INTEGER(1), " + 
				"sesame INTEGER(1)," + 
				"PRIMARY KEY (username), FOREIGN KEY (username) REFERENCES emfUsers(username))";
	}
	
	public CreateUsersTables () {
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
        
        System.out.print("Creating users table... ");
        try {
        	db.executeStatement(CreateUsersTables.createMainTable());
        	System.out.println("Success");
        } catch(Exception e) {
        	System.out.println("Failed");
        }
        
        System.out.print("Creating allergy table... ");
        try {
        	db.executeStatement(CreateUsersTables.createAllergyTable());
        	System.out.println("Success");
        } catch(Exception e) {
        	System.out.println("Failed");
        }
        
        try {
        	db.closeEverything();
        } catch(Exception e) {
        	e.printStackTrace();
        }
        System.out.println("All tables created");
	}
}
