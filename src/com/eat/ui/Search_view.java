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
import java.util.Comparator;
import javafx.util.Pair;
import com.eat.support.Ingredient;


public class Search_view extends ScrollPane {     
	public Search_view() {
		try {
			String username = LaunchStage.getInstance().getContactService().getSelfID();
			String resultDivider = "_________________________________________________________________________";
			GridPane mainGrid = new GridPane();
			mainGrid.setAlignment(Pos.TOP_CENTER);
			mainGrid.setVgap(30);

			Label title = new Label("Search Recipe");
			title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
			mainGrid.add(title, 0, 0);
			
			//Search bar will be an HBox with both the textfield and the button
			HBox searchBar = new HBox(10);

			TextField searchField = new TextField();
			searchField.setMinWidth(900);
			searchField.setMinHeight(40);
			searchField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
			searchBar.getChildren().add(searchField);
			searchField.requestFocus();

			Button searchButton = new Button("Search");
			searchButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
			searchButton.setMinWidth(110);
			searchButton.setMinHeight(40);
			searchBar.getChildren().add(searchButton);

			RecipeService rs = LaunchStage.getInstance().getRecipeService();

			mainGrid.add(searchBar, 0, 1);
			GridPane resultGrid = new GridPane();
			mainGrid.add(resultGrid, 0, 2);
			//Eventhandler to display results of the search
			EventHandler<ActionEvent> displayResults = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						resultGrid.getChildren().clear();
						
						/*-------------------------------------Sorting Results--------------------------------------------*/
						
						//Initializing lists/variables
						LinkedList<HashMap<String,String>> rawResults = rs.search(searchField.getText()); //Unsorted results from services
						LinkedList<Pair<Double,String>> ratiosList = new LinkedList<Pair<Double,String>>(); // List to store the ratio pairs
						LinkedList<String> pantryItems = LaunchStage.getInstance().getContactService().getPantryItems(username); //List of the user's pantry items
						double pantrySize = LaunchStage.getInstance().getContactService().getPantryItems(username).size(); // size of the user's pantry
						
						LinkedList<String> ingredientStrings = new LinkedList<String>();
						//Nested loops to populate ratiosList
						for (HashMap<String,String> result : rawResults) {
							LinkedList<Ingredient> ingredients = rs.getIngredients(result.get(IRecipe.RECIPEID));
							ingredientStrings.clear();
							for (Ingredient ingredient : ingredients) {
								ingredientStrings.add(ingredient.getName());
							}
							double pantryMatches = 0;
							for( int a= 0; a< pantryItems.size(); a++) {							
								A: for(String ingredient : ingredientStrings) {
									if(ingredient.contains(pantryItems.get(a))) {
										pantryMatches++;
										break A;
									}
								}
							}
							ratiosList.add(new Pair<Double,String>(pantryMatches/pantrySize, result.get(IRecipe.RECIPEID)));
						}
						
						//Sorting ratiosList
						ratiosList.sort(new Comparator<Pair<Double,String>>(){
							@Override
							public int compare(Pair<Double,String> pair1, Pair<Double,String> pair2) {
								//Multiplying by 1000 to ensure they are integers
								double temp1 = pair1.getKey() * 1000;
								double temp2 = pair2.getKey() * 1000;
								int int1 = (int) temp1;
								int int2 = (int) temp2;
								return int2 - int1;
							}
						});
						//System.out.println(ratiosList);
						
						//Populating sortedResults
						LinkedList<HashMap<String,String>> sortedResults = new LinkedList<HashMap<String,String>>();
						for (Pair<Double,String> ratio : ratiosList) {
							sortedResults.add(rs.getRecipe(ratio.getValue()));
						}
						/*--------------------------------------------------------------------------------------------------*/
						
						
						int resultNumber = 0;
						if (sortedResults.size() == 0) {
							Label noResultsLabel = new Label("No results found.");
							noResultsLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
							resultGrid.add(noResultsLabel, 0, 0);
						}
						for (HashMap<String,String> result : sortedResults) {
							HBox searchResult = new HBox(20);
							/*
							Image recipeImage = new Image(result.get(IRecipe.IMAGEPATH));
							ImageView recipeImageView = new ImageView(recipeImage);
							recipeImageView.setFitHeight(150);
							recipeImageView.setFitWidth(200);
							searchResult.getChildren().add(recipeImageView);
							*/
							VBox recipeText = new VBox(5);

							Label recipeName = new Label(result.get(IRecipe.NAME));
							recipeName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
							recipeName.setAlignment(Pos.TOP_CENTER);

							Label recipeSummary = new Label(result.get(IRecipe.SUMMARY) + "\n" + resultDivider);
							recipeSummary.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
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
						ex.printStackTrace();
					}
				}
			};
			//Press enter on searchField or click searchButton to display results
			searchButton.setOnAction(displayResults);
			searchField.setOnAction(displayResults);
			
			this.setContent(mainGrid);
		}
		catch (Exception exc) {
			System.out.println(exc);
		}

	}       
} 

