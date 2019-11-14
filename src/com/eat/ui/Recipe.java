package com.eat.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import javafx.scene.layout.HBox;

import com.eat.services.RecipeService;
import com.eat.services.ContactService;
import com.eat.services.IRecipe;
import com.eat.support.Ingredient;

public class Recipe extends ScrollPane{

	Label recipeName;
	ImageView recipeImageView;
	Label time;
	Label serving;
	Label summary;
	Label ingredients;
	Label instructions;


	public Recipe(int idInt) {
		try {
			String id = String.valueOf(idInt); //converting parameter to a String so everything else doesn't break
			HashMap<String,String> recipe = LaunchStage.getInstance().getRecipeService().getRecipe(id); //getting the recipe in question
			ContactService cs = LaunchStage.getInstance().getContactService(); //ContactService instance
			String username = cs.getSelfID(); // Current user's username
			
			//Grid for displaying everything
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.TOP_CENTER);
			grid.setVgap(20);

			//Recipe Name
			recipeName = new Label(recipe.get(IRecipe.NAME));
			recipeName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));

			//Favorite Button
			Button favoriteButton = new Button();
			if (cs.getFavorites(cs.getSelfID()).contains(String.valueOf(id))) {
				favoriteButton.setText("<3"); //display a heart if the current recipe is favorited
			}
			else {
				favoriteButton.setText("Favorite"); //otherwise display the text favorite
			}
			favoriteButton.setMinHeight(50);
			favoriteButton.setMinWidth(140);
			favoriteButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			favoriteButton.setAlignment(Pos.CENTER);
			
			//HBox for the Recipe name and Favorite button
			HBox title = new HBox(571);
			title.setMinWidth(900);
			title.getChildren().add(recipeName);
			title.getChildren().add(favoriteButton);
			grid.add(title, 0, 0);

			//Recipe Image
			/*
			Image recipeImage = new Image(recipe.get(IRecipe.IMAGEPATH));
			recipeImageView = new ImageView(recipeImage);
			recipeImageView.setFitHeight(600);
			recipeImageView.setFitWidth(900);
			grid.add(recipeImageView, 0, 1);
			*/

			//HBox for the summary title and the Recipe's author
			HBox summaryAndAuthor = new HBox(571);
			
			Label summaryTitle = new Label("Summary");
			summaryTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
			
			Label author = new Label("By: " + recipe.get(IRecipe.AUTHOR));
			author.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			
			summaryAndAuthor.getChildren().add(summaryTitle);
			summaryAndAuthor.getChildren().add(author);
			grid.add(summaryAndAuthor, 0, 2);

			//Prep Time
			time = new Label("Prep Time: " + String.valueOf(recipe.get(IRecipe.TIMEREQ)) + " minutes");
			time.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(time, 0, 3);

			//Serving Size
			serving = new Label("Makes "+ String.valueOf(recipe.get(IRecipe.SERVING)) + " servings");
			serving.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(serving, 0, 4);
			
			//Allergies
			Label recipeAllergies = new Label();
			String allergyString = "Allergies: ";
			LinkedList<String> recipeAllergiesList = LaunchStage.getInstance().getRecipeService().getRecipeAllergies(id);
			LinkedList<String> userAllergies = cs.getAllergies(username);
			
			//Check if user has any of the allergies, if so display them in red
			for (String allergy : recipeAllergiesList) {
				allergyString += allergy + ", ";
				if (userAllergies.contains(allergy)) {
					recipeAllergies.setStyle("-fx-text-fill: red");
					break;
				}
			}
			if (allergyString.length() <= 11) {
				allergyString = "Allergies: none";
			}
			else {
				allergyString = allergyString.substring(0, allergyString.length() - 2);
			}
			
			recipeAllergies.setText(allergyString);
			recipeAllergies.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(recipeAllergies, 0, 5);

			//Summary
			summary = new Label(recipe.get(IRecipe.SUMMARY));
			summary.setMaxWidth(900);
			summary.setWrapText(true);
			summary.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(summary, 0, 6);

			//Ingredients title
			Label ingredientsTitle = new Label("Ingredients");
			ingredientsTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
			grid.add(ingredientsTitle, 0, 7);

			//Ingredients
			LinkedList<Ingredient> ingredientList = LaunchStage.getInstance().getRecipeService().getIngredients(id); //get ingredient list
			String ingredientString = "";
			String amountString;
			for (int i = 0; i < ingredientList.size(); i++) { //iterate through the list and add each ingredient to a string on seperate line
				amountString = String.valueOf(ingredientList.get(i).getAmount());
				ingredientString += amountString + " " + ingredientList.get(i).getUnit() + " " + ingredientList.get(i).getName() +"\n";
			}
			ingredients = new Label(ingredientString);
			ingredients.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			ingredients.setWrapText(true);
			grid.add(ingredients, 0, 8);

			//Instructions title
			Label instructionsTitle = new Label("Instructions");
			instructionsTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
			grid.add(instructionsTitle, 0, 9);
			
			//Instructions
			HashMap<Integer, String> stepsMap = LaunchStage.getInstance().getRecipeService().getSteps(id);
			String instructionsString = ""; //holds all of the instructions
			String stepNumberString; //keeps track of step number
			for (int j = 1; j < stepsMap.size() + 1; j++) { //for loop to build the instruction string
				stepNumberString = String.valueOf(j);
				instructionsString += stepNumberString + ". " + stepsMap.get(j) + "\n";
			}
			instructions = new Label(instructionsString);
			instructions.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			instructions.setMaxWidth(1000);
			instructions.setWrapText(true);
			grid.add(instructions, 0, 10);
			
			this.setMinWidth(1000);
			this.setContent(grid);
			
			favoriteButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent e) {
					try {
						if (cs.getFavorites(cs.getSelfID()).contains(String.valueOf(id))) {
							//Remove from favorites if it is already in
							LaunchStage.getInstance().getContactService().deleteFavorite(LaunchStage.getInstance().getContactService().getSelfID(), String.valueOf(id));
							favoriteButton.setText("Favorite");
						}
						else {
							//Add to favorites if it is not already in
							LaunchStage.getInstance().getContactService().addFavorite(LaunchStage.getInstance().getContactService().getSelfID(), String.valueOf(id));
							favoriteButton.setText("<3");
						}
					}
					catch (Exception ex) {
						System.out.println(ex);
					}
				}
			});


		}
		catch (Exception e) {
			System.out.println(e);
		}


	}
}