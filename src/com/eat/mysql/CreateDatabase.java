package com.eat.mysql;

public class CreateDatabase {
	public CreateDatabase() {
		new CreateUsersTables();
		new CreateRecipeTables();
	}
	
	private static void dropTables() {
        System.out.println("Attempting to connect...");
        //Main DB
        DBInteractor db = new DBInteractor("jdbc:mysql://34.223.151.87:3306/javabase", "remoteu", "password");
        
        //Backup DB
        //DBInteractor db = new DBInteractor("jdbc:mysql://18.190.142.138:3306/javabase", "remoteu", "password");
        
        System.out.println("Dropping tables... ");
        db.executeStatement("DROP TABLE `allergies`, `favorites`, `pantry`, `recipes`, `emfUsers`;");
        System.out.println("All tables dropped.\n");
	}
	
	public static void main(String [] args) {
		
		//Uncomment this is for some reason the DB gets corrupted and we need to restart
		//This ideally should never need to be used in production so the method is private
		//dropTables();
		new CreateDatabase();
	}
}
