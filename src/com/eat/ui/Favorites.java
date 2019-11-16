package com.eat.ui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.HashMap;
import java.util.LinkedList;
import javafx.scene.input.MouseEvent;

import com.eat.services.IRecipe;
import com.eat.services.RecipeService;
import com.eat.services.ContactService;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Favorites extends ScrollPane {

	public Favorites() {
		try {
			String resultDivider = "_________________________________________________________________________";
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.TOP_CENTER);
			grid.setVgap(30);

			Label title = new Label("Favorites");
			title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
			grid.add(title, 0, 0);

			RecipeService rs = LaunchStage.getInstance().getRecipeService();
			ContactService cs = LaunchStage.getInstance().getContactService();
			
			//Get list of favorite IDs
			LinkedList<String> favoriteIDs = cs.getFavorites(cs.getSelfID());
			int favoriteNumber = 1; // number for positioning the results
			
			//Text to display when the user has no favorites
			Label noFavorites = new Label("You don't have any favorites!\nClick on the favorite button on a recipe's page to have it show up here.");
			noFavorites.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
			if (favoriteIDs.size() == 0) {
				grid.add(noFavorites, 0, 1);
			}
			//Iterate through favorites list
			for (String id : favoriteIDs) { 
				//Each searchResult will be one HBox
				HBox searchResult = new HBox(20);
				HashMap<String,String> recipe = rs.getRecipe(id);
				/*
				Image recipeImage = new Image(recipe.get(IRecipe.IMAGEPATH));
				ImageView recipeImageView = new ImageView(recipeImage);
				recipeImageView.setFitHeight(150);
				recipeImageView.setFitWidth(200);
				searchResult.getChildren().add(recipeImageView);
				*/
				//Title and Summary will be in a VBox which will be put into the HBox
				VBox recipeText = new VBox(5);

				Label recipeName = new Label(recipe.get(IRecipe.NAME));
				recipeName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
				recipeName.setAlignment(Pos.TOP_CENTER);

				Label recipeSummary = new Label(recipe.get(IRecipe.SUMMARY) + "\n" + resultDivider);
				recipeSummary.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
				recipeSummary.setMaxWidth(800);
				recipeSummary.setWrapText(true);

				recipeText.getChildren().add(recipeName);
				recipeText.getChildren().add(recipeSummary);
				
				
				searchResult.getChildren().add(recipeText);
				searchResult.setOnMouseClicked(new EventHandler<MouseEvent>() { //Can click on entire HBox

					@Override
					public void handle(MouseEvent e) {
						//Transfer user to Recipe Page
						LaunchStage.getInstance().RecipePane(Integer.valueOf(id));
					}
				});
				grid.add(searchResult, 0, favoriteNumber); //add recipe to grid at the current position
				favoriteNumber++; //increment position
			}
			this.setMinWidth(1000);
			this.setContent(grid);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}