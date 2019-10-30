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


	public Recipe(int id) {
		try {
			HashMap<String,String> recipe = LaunchStage.getInstance().getRecipeService().getRecipe(id);

			GridPane grid = new GridPane();
			grid.setAlignment(Pos.TOP_CENTER);
			grid.setVgap(20);

			//Recipe Name
			recipeName = new Label(recipe.get(IRecipe.NAME));
			recipeName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));

			//Favorite Button
			Button favoriteButton = new Button("Favorite");
			favoriteButton.setMinHeight(50);
			favoriteButton.setMinWidth(140);
			favoriteButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			favoriteButton.setAlignment(Pos.CENTER);

			HBox title = new HBox(571);
			title.setMinWidth(900);
			title.getChildren().add(recipeName);
			title.getChildren().add(favoriteButton);
			grid.add(title, 0, 0);

			//Recipe Image
			Image recipeImage = new Image(recipe.get(IRecipe.IMAGEPATH));
			recipeImageView = new ImageView(recipeImage);
			recipeImageView.setFitHeight(600);
			recipeImageView.setFitWidth(900);
			grid.add(recipeImageView, 0, 1);

			//Summary Title
			Label summaryTitle = new Label("Summary");
			summaryTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
			grid.add(summaryTitle, 0, 2);

			//Prep Time
			time = new Label("Prep Time: " + String.valueOf(recipe.get(IRecipe.TIMEREQ)) + " minutes");
			time.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(time, 0, 3);

			//Serving Size
			serving = new Label("Serves "+ String.valueOf(recipe.get(IRecipe.SERVING)) + " people");
			serving.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(serving, 0, 4);

			//Summary
			summary = new Label(IRecipe.SUMMARY);
			summary.setMaxWidth(900);
			summary.setWrapText(true);
			summary.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(summary, 0, 5);

			//Ingredients title
			Label ingredientsTitle = new Label("Ingredients");
			ingredientsTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
			grid.add(ingredientsTitle, 0, 6);

			//Ingredients
			LinkedList<Ingredient> ingredientList = LaunchStage.getInstance().getRecipeService().getIngredients(id); //get ingredient list
			String ingredientString = "";
			String amountString;
			for (int i = 0; i < ingredientList.size(); i++) { //iterate through the list and add each ingredient to a string on seperate line
				amountString = String.valueOf(ingredientList.get(i).getAmount());
				ingredientString += amountString + ingredientList.get(i).getUnit() + " " + ingredientList.get(i).getName() +"\n";
			}
			ingredients = new Label(ingredientString);
			ingredients.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			ingredients.setWrapText(true);
			grid.add(ingredients, 0, 7);

			//Instructions title
			Label instructionsTitle = new Label("Instructions");
			instructionsTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
			grid.add(instructionsTitle, 0, 8);
			
			//Instructions
			HashMap<Integer, String> stepsMap = LaunchStage.getInstance().getRecipeService().getSteps(id);
			String instructionsString = "";
			String stepNumberString;
			for (int j = 0; j < stepsMap.size(); j++) {
				stepNumberString = String.valueOf(j+1);
				instructionsString += stepNumberString + ". " + stepsMap.get(j) + "\n";
			}
			
			instructions = new Label(instructionsString);
			instructions.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			instructions.setWrapText(true);
			grid.add(instructions, 0, 9);
			
			this.setMinWidth(1000);
			this.setContent(grid);
			
			


		}
		catch (Exception e) {
			System.out.println(e);
		}


	}
}