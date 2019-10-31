package com.eat.ui;

import com.eat.services.IRecipe;
import com.eat.services.RecipeService;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.LinkedList;
import java.util.HashMap;


public class Search_view extends ScrollPane {     
	public Search_view() {
		try {
			GridPane mainGrid = new GridPane();
			mainGrid.setAlignment(Pos.TOP_CENTER);
			mainGrid.setVgap(30);

			Label title = new Label("Search Recipe");
			title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
			mainGrid.add(title, 0, 0);

			HBox searchBar = new HBox(10);

			TextField searchField = new TextField();
			searchField.setMinWidth(900);
			searchField.setMinHeight(40);
			searchField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
			searchBar.getChildren().add(searchField);

			Button searchButton = new Button("Search");
			searchButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
			searchButton.setMinWidth(110);
			searchButton.setMinHeight(40);
			searchBar.getChildren().add(searchButton);

			RecipeService rs = LaunchStage.getInstance().getRecipeService();

			mainGrid.add(searchBar, 0, 1);
			GridPane resultGrid = new GridPane();
			mainGrid.add(resultGrid, 0, 2);

			EventHandler<ActionEvent> fetchResults = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						resultGrid.getChildren().clear();
						LinkedList<HashMap<String,String>> results = rs.search(searchField.getText());
						int resultNumber = 0;
						for (HashMap<String,String> result : results) {
							HBox searchResult = new HBox(20);

							Image recipeImage = new Image(result.get(IRecipe.IMAGEPATH));
							ImageView recipeImageView = new ImageView(recipeImage);
							recipeImageView.setFitHeight(150);
							recipeImageView.setFitWidth(200);
							searchResult.getChildren().add(recipeImageView);

							VBox recipeText = new VBox(5);

							Label recipeName = new Label(result.get(IRecipe.NAME));
							recipeName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
							recipeName.setAlignment(Pos.TOP_CENTER);

							Label recipeSummary = new Label(result.get(IRecipe.SUMMARY));
							recipeSummary.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
							recipeSummary.setMaxWidth(800);
							recipeSummary.setWrapText(true);

							recipeText.getChildren().add(recipeName);
							recipeText.getChildren().add(recipeSummary);

							searchResult.getChildren().add(recipeText);
							searchResult.setOnMouseClicked(new EventHandler<MouseEvent>() {

								@Override
								public void handle(MouseEvent e) {
									LaunchStage.getInstance().RecipePane(Integer.valueOf(result.get(IRecipe.RECIPEID)));
								}
							});
							resultGrid.add(searchResult, 0, resultNumber);
							resultNumber++;
						}
					}
					catch (Exception ex) {
						System.out.println(ex);
					}
				}
			};
			searchButton.setOnAction(fetchResults);
			searchField.setOnAction(fetchResults);
			this.setContent(mainGrid);
		}
		catch (Exception exc) {
			System.out.println(exc);
		}

	}       
} 

