package com.eat.test;

import java.util.HashMap;
import java.util.Map;

// test for contactService
import com.eat.services.ContactService;
import com.eat.services.RecipeService;
import com.eat.services.IUser;
import com.eat.services.IRecipe;
import com.eat.ui.FoodSuggest;

public class Test_services {
	public static void testUsers() {
		ContactService cs;
		try {
			cs = new ContactService();
		} catch(Exception e) { e.printStackTrace(); return; }
		
		try {
			cs.registerUser("", "", "", "");
		} catch(Exception e) {
			//Expected to print an error
			e.printStackTrace();
		}
		
		try {
			//User could already exist if running more than once, thus it's own try/catch
			cs.registerUser("chad", "steph", "roger", "matt@gmail.com");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			HashMap<String,String> user = cs.getUser("chad");
			System.out.println("Full user: " + user);
			
			//Expected value: matt@gmail.com
			System.out.println("Email: " + user.get(IUser.EMAIL));
		} catch(Exception e) { 
			e.printStackTrace();
		}
		
		try {
			cs.registerUser("chad", "sd", "sd", "sd");
		} catch(Exception e) {
			//Expected to print an error
			e.printStackTrace();
		}
		
		try {
			cs.login("chad", "steph");
			System.out.println("Login returned without an error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testRecipes() {
		RecipeService rs;
		try {
			rs = new RecipeService();
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
		try {
			Map<String,String> recipe = rs.addRecipe("Dunks Dinner Surprise", "A meal to change your day.",
					null, "Duncan", 6, 30);
			System.out.println(recipe);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		//testUsers();
		testRecipes();
	}

// test food suggest
	public static void favorites() {
		FoodSuggest fs;
		try {
			fs = new FoodSuggest();
		} catch(Exception e) { e.printStackTrace(); return; }
	}
	
}

// test recipeservice
