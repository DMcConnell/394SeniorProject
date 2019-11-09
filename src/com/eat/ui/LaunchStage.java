package com.eat.ui;

import com.eat.services.ContactService;
import com.eat.services.EmailService;
import com.eat.services.RecipeService;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
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
	        	es = new EmailService();
	        	es.init();
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
	public EmailService getEmailService()
	{
		return es;
	}
	
	public double getScreenWidth() {
		return ScreenWidth;
	}
	
	public double getScreenHeight() {
		return ScreenHeight;
	}
	
	private RecipeService rs;
	private ContactService cs;
	private EmailService es;
	
	private double ScreenWidth;
	private double ScreenHeight;
	
	
	Scene currentScene;
	Scene startScene;
	Scene mainScene;

	BorderPane currentPane;
	BorderPane startPane;
	BorderPane mainPane;
	
	BackgroundImage logo;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("EMF1.png")));
		primaryStage.setTitle("Eat My Food! - V 1.0.0");
		
		ScreenWidth = Screen.getPrimary().getBounds().getWidth() * .85;
		ScreenHeight = Screen.getPrimary().getBounds().getHeight() * .85;
		
		//logo = new BackgroundImage(new Image("file:EMF1.jpeg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		
		startPane = new BorderPane();
		
		currentPane = startPane;
		
		LoginPane();
		
		//currentPane.setBackground(new Background(logo));
		
		startScene = new Scene(currentPane, ScreenWidth, ScreenHeight);
		currentScene = startScene;
		
		primaryStage.setScene(currentScene);
		primaryStage.show();
		
	}

	
	public static void main(String[] args) {
		launch(args);
	}
	
	protected BackgroundImage getBackgroundImage()
	{
		return logo;
	}
	
	
	private void resetServices() {
		try {
			rs.close();
			cs.close();
			rs = new RecipeService();
			cs = new ContactService();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//This is for logging out and switching back to login page, disconnected from database.
	protected void StartScene()
	{
		resetServices();
		startPane = new BorderPane();
		//startPane.setAlignment(Pos.CENTER);
		
		currentPane = startPane;
		
		LoginPane();

		Stage stage = (Stage) currentScene.getWindow();
		
		startScene = new Scene(currentPane, ScreenWidth, ScreenHeight);
		currentScene = startScene;
		
		stage.setScene(currentScene);
		
		stage.show();
	}
	
	//This is for switching from successful login to the main scene, starting at the profile page
	protected void MainScene()
	{
		mainPane = new BorderPane();
		//mainPane.setAlignment(Pos.CENTER);
		
		
		currentPane = mainPane;
		//set current pane to be new pane with navigation window and profile page
		NavPane();
		ProfilePane();
		
		Stage stage = (Stage) currentScene.getWindow();
		
		mainScene = new Scene(mainPane, ScreenWidth, ScreenHeight);
		currentScene = mainScene;
		
		
		stage.setScene(currentScene);
		stage.show();
		
		//stage.setFullScreen(true);
	}
	
	
	protected void LoginPane()
	{
		currentPane.setCenter(new Login());
	}
	protected void RegistrationPane()
	{
		currentPane.setCenter(new Registration());
	}
	
	protected void ForgotPasswordPane()
	{
		currentPane.setCenter(new ForgotPassword());
	}
	
	protected void NavPane()
	{
		currentPane.setLeft(new Navigation());
	}
	
	protected void ProfilePane()
	{
		currentPane.setCenter(new Profile());
	}
	
	protected void RecipePane(int id) {
		currentPane.setCenter(new Recipe(id));
	}
	
	protected void FavoritesPane()
	{
		currentPane.setCenter(new Favorites());
	}
	
	protected void SearchPane()
	{
		currentPane.setCenter(new Search_view());
	}
	
	protected void UploadRecipePane()
	{
		currentPane.setCenter(new UploadRecipe());
	}
	protected void UserSettingsPane()
	{
		currentPane.setCenter(new UserSettings());
	}
	protected void foodSuggest() {
		currentPane.setCenter(new FoodSuggest());
	}
}
