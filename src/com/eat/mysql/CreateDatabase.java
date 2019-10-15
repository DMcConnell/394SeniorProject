package com.eat.mysql;

public class CreateDatabase {
	public CreateDatabase() {
		new CreateUsersTables();
		new CreateRecipeTables();
	}
	
	public static void main(String [] args) {
		new CreateDatabase();
	}
}
