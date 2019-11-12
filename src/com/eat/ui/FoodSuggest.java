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
	
	//create the objects for the Pane the button the title and label to hold the Recipe

	Button suggestButton;
	Label recipeTitle;//still need to position these in grid pane
	Label recipeSteps;
	List<String> favorites;
	
	Label returnToRecipesL;
	Label returnToRecipesL1;
	Label returnToRecipesL2;

	
	Button returnToRecipes;
	
	

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
				
				
		   // Text scenetitle = new Text("Dont know what creation to make today?");

			//Label sLabel = new Label ("Eat my Food");
			
			GridPane suggestGrid = new GridPane();
		    //Button to initiate suggestions the suggestion button
		    suggestButton = new Button();
		    suggestButton.setText("New Suggestion");
		    suggestButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		    suggestButton.setMinWidth(145);
		    suggestGrid.add(suggestButton, 2, 1);
		    
		    returnToRecipes = new Button();
		    returnToRecipes.setText("Go Back To Recipes");
		    returnToRecipes.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		    returnToRecipes.setMinWidth(145);
		    suggestGrid.add(returnToRecipes, 2, 3);
		    
		    returnToRecipesL = new Label();
		    returnToRecipesL.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		    suggestGrid.add(returnToRecipesL, 4, 7);
		   
		    returnToRecipesL1 = new Label();
		    returnToRecipesL1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		    suggestGrid.add(returnToRecipesL1, 4, 8);
		    
		    returnToRecipesL2 = new Label();   
		    returnToRecipesL2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		    suggestGrid.add(returnToRecipesL2, 4, 9);
		    


		    
			this.setContent(suggestGrid);
		    setActions();
		    
		    
		    
		    
	}
		

	private void setActions() {
		// TODO Auto-generated method stub

		
		returnToRecipes.setOnAction( new EventHandler <ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				LaunchStage.getInstance().SearchPane(); 
				
			}

		});
	
		suggestButton.setOnAction( new EventHandler <ActionEvent>() {
			
			//add error incase the favorites list is //make a text field make label with empty string label.settext(enter string)
			
			
			@Override
			public void handle(ActionEvent e) {
				
				//
				if (favorites.isEmpty()) {
					//return message to user 
					returnToRecipesL.setText("You currently do not have any favorites saved, ");
					returnToRecipesL1.setText("Please add a few so that Eat My Food can Better");
					returnToRecipesL2.setText(" understand you :)  <3 Thank you <3 ");
					
					}
				else {
				
				
				double maxRatio=0;
				String maxID = favorites.get(0);
				
				//go through get a ratio 
			
				//here is where we included our logic to randomly finding the recipe to present
				List<String> PantryItems;
				try { //its not matching any of the pantry items to the ingredients list
					
					PantryItems= LaunchStage.getInstance().getContactService().
						getPantryItems(LaunchStage.getInstance().getContactService().getSelfID()); 
				} catch(Exception e1) {
					//e1.printStackTrace();
					LaunchStage.getInstance().RecipePane(Integer.parseInt(maxID));
					return;
				}
				
				for(int i = 0; i < favorites.size(); i++) {
					List<Ingredient> Ingredients;
					List<String> finalIngredients = new LinkedList<String>();
					try {
						Ingredients = LaunchStage.getInstance().getRecipeService().getIngredients(favorites.get(i));
						for(Ingredient ingredient : Ingredients) {
							finalIngredients.add(ingredient.getName().toUpperCase());
						}
					} catch(Exception e2) {
						//LaunchStage.getInstance().RecipePane(Integer.parseInt(maxID));
						break;
					}
					int score=0;
					double ratio;

					
						for( int a= 0; a< PantryItems.size(); a++) {	
							
							A: for(String ingredient : finalIngredients) {
								
								if(ingredient.contains(PantryItems.get(a).toUpperCase())) {
									score++;
									break A;
								}
							}
						}
					ratio =score/Ingredients.size();
						
					if (ratio> maxRatio) 
						{maxRatio=ratio; maxID = favorites.get(i);}

				}
			
				
				//present this recipe to the user
				LaunchStage.getInstance().RecipePane(Integer.parseInt((maxID)));
			}}
		});
	}
}
	
