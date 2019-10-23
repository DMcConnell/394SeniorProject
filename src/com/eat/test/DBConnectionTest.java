package com.eat.test;

import com.eat.IEatMyFood;
import com.eat.mysql.DBInteractor;

public class DBConnectionTest {

    public static void main(String[] args) {
        System.out.println("Attempting to connect...");

        try {
	        DBInteractor db;
	        if(IEatMyFood.MAINDB) {
	        	db = new DBInteractor("jdbc:mysql://34.223.151.87:3306/javabase", "remoteu", "password");
	        }
	        else {
	            db = new DBInteractor("jdbc:mysql://18.190.142.138:3306/javabase", "remoteu", "password");
	        }
	        
	        db.executeStatement("CREATE TABLE ARTICLES(LINK VARCHAR(255), TITLE VARCHAR(255), DESCRIPTION VARCHAR(255))");
	        db.closeEverything();
	        System.out.println("Success");
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
}