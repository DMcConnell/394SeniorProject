package com.eat.test;

import java.util.HashMap;

import com.eat.services.ContactService;
import com.eat.services.IUser;

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
	
	public static void main(String [] args) {
		testUsers();
	}
}
