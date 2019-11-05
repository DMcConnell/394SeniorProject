package com.eat.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
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

//import java.awt.ScrollPane;
import java.io.File;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.eat.IEatMyFood;
import com.eat.mysql.DBInteractor;
import com.eat.support.Ingredient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eat.services.IRecipe;
import com.eat.services.Exceptions.*;

public class FoodSuggest extends ScrollPane{

	Button suggestButton;
	Label recipeTitle;//still need to position these in grid pane
	Label recipeSteps;
	List<String> favorites;

	//suggest a recipe from the myfavorites
//here we pass the parameter of the username as a string into the function
	
	public FoodSuggest ()
		// TODO Auto-generated method stub
	{
		try {
				//list refers to a instance of the launch stage at that user provides a list of unique recipe ids
				favorites = LaunchStage.getInstance().getContactService().getFavorites(LaunchStage.getInstance().getContactService().getSelfID());
				
		}
		catch(Exception e)
		{}
		
	
		
			//retype this
			
				
		   // Text scenetitle = new Text("Dont know what creation to make today?");

			//Label sLabel = new Label ("Eat my Food");
			
			GridPane suggestGrid = new GridPane();
		    //Button to initiate suggestions the suggestion button
		    suggestButton = new Button();
		    suggestButton.setText("New Suggestion");
		    suggestButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		    suggestButton.setMinWidth(145);

		    suggestGrid.add(suggestButton, 2, 1);
			this.setContent(suggestGrid);
		    setActions();
	}
		

	private void setActions() {
		// TODO Auto-generated method stub
		// in here we will generate a random query that will get the unique ID of some recipe 
		// use get recipe on the unique id query 
		
	
		suggestButton.setOnAction( new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				int totalRecipes = favorites.size();
				
				Random recipeGen = new Random();
				int id = recipeGen.nextInt(totalRecipes);
				
				LaunchStage.getInstance().RecipePane(id);
		    }
        });
    
}	}
		
	
