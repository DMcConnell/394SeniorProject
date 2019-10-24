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
import javafx.scene.layout.HBox;
import com.eat.services.RecipeService;

public class Recipe extends ScrollPane{
	/*
	public static void main (String[] args) {
		launch(args);
	}
	*/
	
	public Recipe() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setVgap(20);
		
		
		
		try {
			//RecipeService rs = new RecipeService();
			//Label recipeName = new Label(rs.getRecipe("123456789012").get("NAME"));
			
			//Recipe Name
			Label recipeName = new Label("Temp Name");
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
			//FileInputStream recipeInputStream = new FileInputStream("C:\\Users\\Thomas\\Pictures\\burger.jpg");
			Image recipeImage = new Image("https://assets3.thrillist.com/v1/image/2797371/size/tmg-article_default_mobile.jpg");
			ImageView recipeImageView = new ImageView(recipeImage);
			recipeImageView.setFitHeight(600);
			recipeImageView.setFitWidth(900);
			grid.add(recipeImageView, 0, 1);
			
			//Rating
			Label rating = new Label("Rating: 3/5");
			rating.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
			
			//Description Title
			Label descriptionTitle = new Label("Summary");
			descriptionTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
			
			HBox descriptionRatingBox = new HBox(580);
			descriptionRatingBox.setMinWidth(900);
			descriptionRatingBox.getChildren().add(descriptionTitle);
			descriptionRatingBox.getChildren().add(rating);
			grid.add(descriptionRatingBox, 0, 2);
			
			//Prep Time
			Label time = new Label("Prep Time: 60 minutes");
			time.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(time, 0, 3);
			
			//Serving Size
			Label serving = new Label("Serves 5 people");
			serving.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(serving, 0, 4);
			
			//Description
			Label description = new Label("Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text ");
			description.setMaxWidth(900);
			description.setWrapText(true);
			description.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(description, 0, 5);
			
			//Ingredients title
			Label ingredientsTitle = new Label("Ingredients");
			ingredientsTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
			grid.add(ingredientsTitle, 0, 6);
			
			//Ingredients
			Label ingredients = new Label("'Ingredient Amount' 'Ingredient Amount' 'Ingredient Amount' 'Ingredient Amount' 'Ingredient Amount'");
			ingredients.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			ingredients.setMaxWidth(200);
			ingredients.setWrapText(true);
			grid.add(ingredients, 0, 7);
			
			//Instructions title
			Label instructionsTitle = new Label("Instructions");
			instructionsTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
			grid.add(instructionsTitle, 0, 8);
			
			this.setContent(grid);
		} 
		catch (Exception e){
			System.out.println(e);
		}
		
	}
}