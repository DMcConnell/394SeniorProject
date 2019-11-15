package com.eat.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Navigation extends VBox{

	//Visual Elements
	Button Profile;
	Button Favorites;
	Button Search;
	Button UploadRecipe;
	Button Logout;
	Button FoodSuggestion;
	Button Settings;
	
	//In constructor, apply all visual elements to the Pane (this)
	public Navigation()
	{
		int numpages = 0;
		Profile = new Button("Profile");
		Profile.setPrefWidth(250);
		numpages++;
		Favorites = new Button("My Favorites");
		Favorites.setPrefWidth(250);
		numpages++;
		Search = new Button("Search Recipes");
		Search.setPrefWidth(250);
		numpages++;
		UploadRecipe = new Button("Upload Recipe");
		UploadRecipe.setPrefWidth(250);
		numpages++;
		FoodSuggestion = new Button("Suggestion");
		FoodSuggestion.setPrefWidth(250);
		numpages++;
		Settings = new Button("User Settings");
		Settings.setPrefWidth(250);
		numpages++;
		Logout = new Button("Logout");
		Logout.setPrefWidth(250);
		numpages++;
		
		//set spacing based off of window/screen size
		this.setSpacing(LaunchStage.getInstance().getScreenHeight() / (numpages+1));
		
		this.getChildren().addAll(Profile, Favorites, Search, UploadRecipe, FoodSuggestion, Settings, Logout);
		
		setActions();
	}
	
	//Set action for each button
	void setActions()
	{
		Profile.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //REDIRECT TO Profile PAGE
            	LaunchStage.getInstance().ProfilePane();
            }
        });
		
		Favorites.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //REDIRECT TO Favorites PAGE
            	LaunchStage.getInstance().FavoritesPane();
            }
        });
		
		FoodSuggestion.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //REDIRECT TO Favorites PAGE
            	LaunchStage.getInstance().foodSuggest();
            }
        });
		
		Search.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //REDIRECT TO Search PAGE
            	LaunchStage.getInstance().SearchPane();
            }
        });
		
		UploadRecipe.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //REDIRECT TO UploadRecipe PAGE
            	LaunchStage.getInstance().UploadRecipePane();
            }
        });
		
		Settings.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //REDIRECT TO UploadRecipe PAGE
            	LaunchStage.getInstance().UserSettingsPane();
            }
        });
		
		Logout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //Log Out
            	LaunchStage.getInstance().StartScene();
            }
        });
	}
	
}
