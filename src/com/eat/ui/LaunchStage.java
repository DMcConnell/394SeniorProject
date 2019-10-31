package com.eat.ui;

import com.eat.services.ContactService;
import com.eat.services.RecipeService;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LaunchStage extends Application {

	/*
	 * awkward handling of making this application a singleton controller
	 * have to keep constructor public for Application to call, but am preventing
	 * the constructor being called more than once with synchronized
	**/
	private static LaunchStage instance;
	public static LaunchStage getInstance() { return instance; }
	public LaunchStage(){
	    super();
	    synchronized(LaunchStage.class){
	        if(instance != null) throw new UnsupportedOperationException(
	                getClass()+" is singleton but constructor called more than once");
	        instance = this;
	        try {
	        	rs = new RecipeService();
	        	cs = new ContactService();
	        } catch(Exception e) {
	        	e.printStackTrace();
	        }
	    }
	}
	
	public RecipeService getRecipeService() {
		return rs;
	}
	
	public ContactService getContactService() {
		return cs;
	}
	
	private RecipeService rs;
	private ContactService cs;
	
	Scene currentScene;
	Scene startScene;
	Scene mainScene;

	GridPane currentPane;
	GridPane startPane;
	GridPane mainPane;
	
	Login login;
	Registration registration;
	ForgotPassword forgotPassword;
	
	Profile profile;
	Recipe recipe;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		startPane = new GridPane();
		startPane.setAlignment(Pos.CENTER);
		
		currentPane = startPane;
		
		
		LoginPane();
		
		startScene = new Scene(currentPane, 1920, 1080);
		currentScene = startScene;
		
		
		primaryStage.setScene(currentScene);
		
		primaryStage.show();
		
	}

	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	//This is for logging out and switching back to login page, disconnected from database.
	protected void StartScene()
	{
		startPane = new GridPane();
		startPane.setAlignment(Pos.CENTER);
		
		currentPane = startPane;
		
		LoginPane();

		Stage stage = (Stage) currentScene.getWindow();
		
		startScene = new Scene(currentPane, 1920, 1080);
		currentScene = startScene;
		
		stage.setScene(currentScene);
		
		stage.show();
	}
	
	//This is for switching from successful login to the main scene, starting at the profile page
	protected void MainScene()
	{
		mainPane = new GridPane();
		mainPane.setAlignment(Pos.CENTER);
		
		
		currentPane = mainPane;
		//set current pane to be new pane with navigation window and profile page
		ProfilePane();
		
		Stage stage = (Stage) currentScene.getWindow();
		
		mainScene = new Scene(mainPane, 1920, 1080);
		currentScene = mainScene;
		
		stage.setScene(currentScene);
		stage.show();
		
	}
	
	
	protected void LoginPane()
	{
		currentPane.getChildren().clear();
		currentPane.getChildren().add(new Login());
	}
	protected void RegistrationPane()
	{
		currentPane.getChildren().clear();
		currentPane.getChildren().add(new Registration());
	}
	
	protected void ForgotPasswordPane()
	{
		currentPane.getChildren().clear();
		currentPane.getChildren().add(new ForgotPassword());
	}
	//yes inefficient to re-make the nav pane every time, will fix later
	protected void NavPane()
	{
		//	currentPane.getChildren().add(new NavPane());
	}
	
	protected void ProfilePane()
	{
		currentPane.getChildren().clear();
		NavPane();
		currentPane.getChildren().add(new Profile());
	}
	
	protected void RecipePane(int id) {
		currentPane.getChildren().clear();
		NavPane();
		currentPane.getChildren().add(new Recipe(id));
	}
	
}
