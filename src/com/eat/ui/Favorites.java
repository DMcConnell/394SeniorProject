package com.eat.ui;

import javafx.application.Application;
import javafx.stage.Stage;
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
import javafx.scene.input.MouseEvent;

import java.util.LinkedList;

import com.eat.services.IRecipe;
import com.eat.services.RecipeService;
import com.eat.services.ContactService;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Favorites extends ScrollPane {

	public Favorites() {
		try {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.TOP_CENTER);
			grid.setVgap(30);

			Label title = new Label("Favorites");
			title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
			grid.add(title, 0, 0);

			RecipeService rs = LaunchStage.getInstance().getRecipeService();
			ContactService cs = LaunchStage.getInstance().getContactService();

			LinkedList<String> favoriteIDs = cs.getFavorites(cs.getSelfID());
			int favoriteNumber = 1;

			for (String id : favoriteIDs) {
				HBox searchResult = new HBox(20);
				HashMap<String,String> recipe = rs.getRecipe(Integer.parseInt(id));

				Image recipeImage = new Image(recipe.get(IRecipe.IMAGEPATH));
				ImageView recipeImageView = new ImageView(recipeImage);
				recipeImageView.setFitHeight(150);
				recipeImageView.setFitWidth(200);
				searchResult.getChildren().add(recipeImageView);

				VBox recipeText = new VBox(5);

				Label recipeName = new Label(recipe.get(IRecipe.NAME));
				recipeName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
				recipeName.setAlignment(Pos.TOP_CENTER);

				Label recipeSummary = new Label(recipe.get(IRecipe.SUMMARY));
				recipeSummary.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
				recipeSummary.setMaxWidth(800);
				recipeSummary.setWrapText(true);

				recipeText.getChildren().add(recipeName);
				recipeText.getChildren().add(recipeSummary);

				searchResult.getChildren().add(recipeText);
				searchResult.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent e) {
						LaunchStage.getInstance().RecipePane(Integer.valueOf(id));
					}
				});
				favoriteNumber++;
				grid.add(searchResult, 0, favoriteNumber);
			}
			this.setMinWidth(1000);
			this.setContent(grid);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
