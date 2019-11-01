package com.eat.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.eat.services.Exceptions.*;

public class Navigation extends VBox{

	Button Profile;
	Button Favorites;
	Button Search;
	Button UploadRecipe;
	Button Logout;
	Button FoodSuggestion;
	
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
		Logout = new Button("Logout");
		Logout.setPrefWidth(250);
		numpages++;
		

		this.setSpacing(LaunchStage.getInstance().getScreenHeight() / (numpages+1));
		
		this.getChildren().addAll(Profile, Favorites, Search, UploadRecipe, FoodSuggestion, Logout);
		
		setActions();
	}
	
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
		
		Logout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //Log Out
            	//LaunchStage.getInstance().getContactService().logout();
            	LaunchStage.getInstance().StartScene();
            }
        });
	}
	
}
