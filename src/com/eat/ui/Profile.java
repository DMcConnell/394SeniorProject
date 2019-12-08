package com.eat.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.LinkedList;

import com.eat.services.ContactService;
import com.eat.services.IAllergy;
import com.eat.services.IPantry;
import com.eat.support.FxUtilTest;

public class Profile extends ScrollPane{
	
	GridPane mainGrid;
	GridPane allergyGrid;
	GridPane pantryGrid;
	LinkedList<String> userAllergies;
	LinkedList<String> newAllergies;
	LinkedList<String> userPantryItems;
	LinkedList<String> newPantryItems;

	public Profile () {
		try {
			mainGrid = new GridPane();
			mainGrid.setAlignment(Pos.TOP_CENTER);

			//Title
			Label title = new Label("Profile");
			title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 70));
			mainGrid.add(title, 1, 0);
			mainGrid.setVgap(20);
			mainGrid.setHgap(40);
			GridPane.setHalignment(title, HPos.CENTER);
			

			/*----------------Allergies-------------*/
			ContactService cs = LaunchStage.getInstance().getContactService(); //ContactService instance
			String username = LaunchStage.getInstance().getContactService().getSelfID(); //User's username
			userAllergies = cs.getAllergies(username); // Get a list of the user's current allergies
			newAllergies = new LinkedList<String>(); //This list will be used to store the user's new allergies after they made their changes
			allergyGrid = new GridPane(); //A separate grid for displaying the allergies
			allergyGrid.setHgap(20); 

			Label allergyTitleLabel = new Label("Allergies");
			allergyTitleLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
			mainGrid.add(allergyTitleLabel, 2, 1);
			
			//ComboBox for users to select which allergy they want to add
			ComboBox<String> allergyCB = new ComboBox<String>();
			allergyCB.setPromptText("Select or type an allergy");
			allergyCB.setEditable(true); //allows the user to type into the box
			
			//Button to add whatever is in the ComboBox
			Button allergyAddButton = new Button("Add Allergy");
			mainGrid.add(allergyAddButton, 3, 2);
			
			//Putting allergies in the ComboBox
			allergyCB.getItems().addAll(
					IAllergy.EGG,
					IAllergy.FISH,
					IAllergy.MILK,
					IAllergy.PEANUT,
					IAllergy.SESAME,
					IAllergy.SHELLFISH,
					IAllergy.SOY,
					IAllergy.TREENUTS,
					IAllergy.WHEAT
			);
			
			//This line makes the ComboBox filterable by typing
			FxUtilTest.autoCompleteComboBoxPlus(allergyCB, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.toString().equals(typedText));
			
			//HBox to contain the ComboBox and the Add Button
			HBox allergyButtonBox = new HBox(20);
			allergyButtonBox.getChildren().addAll(allergyCB, allergyAddButton);
			
			
			mainGrid.add(allergyButtonBox, 2, 2);
			mainGrid.add(allergyGrid, 2, 3); //allergyGrid is added to the mainGrid
			
			//newAllergies list is initialized with the user's current allergies
			for (String allergy : userAllergies) {
				newAllergies.add(allergy);
			}
			
			//Fuction to display the user's allergies in the allergyGrid
			displayAllergies();

			/*---------------Pantry---------------------*/
			userPantryItems = cs.getPantryItems(username); //getting the user's pantry items
			newPantryItems = new LinkedList<String>(); //This list will be used to store the user's new pantry after they made their changes
			pantryGrid = new GridPane(); //A separate grid for displaying the user's pantry
			pantryGrid.setHgap(20);
			
			Label pantryLabel = new Label("Pantry");
			pantryLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
			mainGrid.add(pantryLabel, 2, 4);
			
			//A ComboBox for the user to pick their allergies from
			ComboBox<String> pantryCB = new ComboBox<String>();
			pantryCB.setPromptText("Select or type an item");
			pantryCB.setEditable(true);
			
			//A button that adds whatever is in the ComboBox to the pantry
			Button pantryAddButton = new Button("Add to pantry");
			mainGrid.add(pantryAddButton, 3, 5);
			
			//Adding all of the pantry items to the ComboBox
			pantryCB.getItems().addAll(
					IPantry.ANGELHAIRNOODLES,
					IPantry.ANISE,
					IPantry.ARUGULA,
					IPantry.BANANAPEPPERS,
					IPantry.BASIL,
					IPantry.BEETS,
					IPantry.BLACKBEANS,
					IPantry.BLACKPEPPER,
					IPantry.BROCOLLI,
					IPantry.BUTTERNUTSQUASH,
					IPantry.CABBAGE,
					IPantry.CALBOT,
					IPantry.CARROTS,
					IPantry.CATFISH,
					IPantry.CAULIFLOWER,
					IPantry.CAVATAPPINOODLES,
					IPantry.CAYENNEPEPPERS,
					IPantry.CELERY,
					IPantry.CHAMOMILE,
					IPantry.CHICKEN,
					IPantry.CHICKPEAS,
					IPantry.CRABLEGS,
					IPantry.CUCUMBERS,
					IPantry.DEER,
					IPantry.DILL,
					IPantry.EGGS,
					IPantry.ELK,
					IPantry.GARLIC,
					IPantry.GINGER,
					IPantry.GREENPEPPERS,
					IPantry.GROUNDBEEF,
					IPantry.GROUNDCHICKEN,
					IPantry.GROUNDTURKEY,
					IPantry.JALAPENOS,
					IPantry.KALE,
					IPantry.KIDNEYBEANS,
					IPantry.LAMB,
					IPantry.LASAGNE,
					IPantry.LEEKS,
					IPantry.LENTILBEANS,
					IPantry.LETTUCE,
					IPantry.LINGUINENOODLES,
					IPantry.LOBSTER,
					IPantry.MUSHROOMS,
					IPantry.NUTMEG,
					IPantry.ONIONS,
					IPantry.ORANGEPEPPERS,
					IPantry.OREGANO,
					IPantry.OSTRICH,
					IPantry.PAPRIKA,
					IPantry.PARSLEY,
					IPantry.PEAS,
					IPantry.PENNENOODLES,
					IPantry.PINTOBEANS,
					IPantry.PLANTBASEDMEAT,
					IPantry.PORK,
					IPantry.POTATOES,
					IPantry.RADISHES,
					IPantry.RAVIOLINOODLES,
					IPantry.REDPEPPERS,
					IPantry.RIGATONINOODLES,
					IPantry.ROSEMARY,
					IPantry.ROTININOODLES,
					IPantry.SAGE,
					IPantry.SALMON,
					IPantry.SEASALT,
					IPantry.SHELLFISH,
					IPantry.SPAGHETTINOODLES,
					IPantry.SPINACH,
					IPantry.STEAK,
					IPantry.SWEETPOTATOES,
					IPantry.TABASCOPEPPER,
					IPantry.TABLESALT,
					IPantry.TOFU,
					IPantry.TOMATOES,
					IPantry.TUNA,
					IPantry.TURKEY,
					IPantry.VEAL
			);
			
			//This line makes the ComboBox filterable by typing
			FxUtilTest.autoCompleteComboBoxPlus(pantryCB, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.toString().equals(typedText));
			
			//HBox to hold the ComboBox and the Add button
			HBox pantryButtonBox = new HBox(20);
			pantryButtonBox.getChildren().addAll(pantryCB, pantryAddButton);
			
			
			mainGrid.add(pantryButtonBox, 2, 5);
			mainGrid.add(pantryGrid, 2, 6); //pantryGrid is added to the mainGrid under the ComboBox
			
			//newPantryItems is initialized to the user's current pantry items
			for (String item : userPantryItems) {
				newPantryItems.add(item);
			}
			
			//A function that populates the pantryGrid with the items from newPantryItems
			displayPantry();


			//Save changes button
			HBox saveBox = new HBox(20);
			
			//The button itself
			Button saveButton = new Button("Save Changes");
			saveButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			saveButton.setMinHeight(80);
			saveBox.getChildren().add(saveButton);
			
			//Text to display when changes are saved
			Label saveLabel = new Label();
			saveLabel.setStyle("-fx-text-fill: green");
			saveBox.getChildren().add(saveLabel);

			mainGrid.add(saveBox, 2, 7);
			


			//this.setMinWidth(1700);
			this.setContent(mainGrid);
			
			/*------------------------------------ACTIONS------------------------------------------*/
			
			//Saves the user's changes to the database
			EventHandler<ActionEvent> saveChanges = new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent e) {
					try {
						saveLabel.setText("");
						/*-------------------Update Allergies------------------*/
						for (String allergy : userAllergies) {
							//Removing the allergies that aren't in the new list
							if (!newAllergies.contains(allergy)) {
								cs.removeAllergy(username, allergy); 
							}
						}
						cs.addAllergies(username, newAllergies); //Add the new allergies to the DB
						
						/*-------------------Update Pantry---------------------*/
						for (String item : userPantryItems) {
							//Removing the pantry items that aren't in the new list
							if (!newPantryItems.contains(item)) {
								cs.deletePantryItem(username, item);
							}
						}
						cs.addPantryItems(username, newPantryItems); //Add the new pantry items to the DB
						
						saveLabel.setText("Changes Saved!");
					}
						catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					
			};
			
			saveButton.setOnAction(saveChanges);
			
			//Adds a new allergy to the newAllergies list
			EventHandler<KeyEvent> addAllergy = new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent e) {
					//if the user hits enter
					if (e.getCode() == KeyCode.ENTER) { 
						//if the allergy isn't already in the new list and if it is one of the options in the ComboBox
						if (!newAllergies.contains(allergyCB.getValue()) && allergyCB.getItems().contains(allergyCB.getValue())) { 
							newAllergies.add(allergyCB.getValue()); //add it to the new list
							displayAllergies(); // re-display the allergies to reflect the change
						}
					}
				}
			};
			allergyCB.setOnKeyPressed(addAllergy);
			
			//Adds a pantry item to the newPantryItems list
			EventHandler<KeyEvent> addPantryItem = new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent e) {
					//If the user hits enter
					if (e.getCode() == KeyCode.ENTER) {
						//if the item isn't already in the new list and if it is one of the options in the ComboBox
						if (!newPantryItems.contains(pantryCB.getValue()) && pantryCB.getItems().contains(pantryCB.getValue())){
							newPantryItems.add(pantryCB.getValue()); //add it to the new list
							displayPantry(); // re-display the pantry to reflect the change
						}
					}
				}
			};
			pantryCB.setOnKeyPressed(addPantryItem);
			
			//Same as add allergy but for the button instead of the ComboBox
			allergyAddButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if (!newAllergies.contains(allergyCB.getValue()) && allergyCB.getItems().contains(allergyCB.getValue())) {
						newAllergies.add(allergyCB.getValue());
						displayAllergies();
					}
				}
			});
			
			//Same as addPantryItem but for the button instead of the ComboBox
			pantryAddButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if (!newPantryItems.contains(pantryCB.getValue()) && pantryCB.getItems().contains(pantryCB.getValue())){
						newPantryItems.add(pantryCB.getValue());
						displayPantry();
					}
				}
			});
		
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		

		

	}
	
	//Function for displaying the new allergies in the allergyGrid
	private void displayAllergies() {
		int allergyRow = 0;
		int allergyColumn = 0;
		for (String allergy: newAllergies) {
			HBox allergyBox = new HBox(3);
			Label allergyLabel = new Label(allergy);
			allergyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			Button deleteAllergyButton = new Button("X");
			
			allergyBox.getChildren().addAll(allergyLabel, deleteAllergyButton);
			
			allergyGrid.add(allergyBox, allergyColumn, allergyRow);
			if (allergyColumn == 3) {
				allergyColumn = 0;
				allergyRow++;
			}
			else allergyColumn++;
			EventHandler<ActionEvent> deleteAllergy = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					newAllergies.remove(allergy);
					allergyGrid.getChildren().clear();
					displayAllergies();
				}
			};
			deleteAllergyButton.setOnAction(deleteAllergy);
		}
	}
	
	//Function for displaying the new pantry items in the pantryGrid
	private void displayPantry() {
		int itemRow = 0;
		int itemColumn = 0;
		for (String item: newPantryItems) {
			HBox itemBox = new HBox(3);
			Label itemLabel = new Label(item);
			itemLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			Button deleteItemButton = new Button("X");
			
			itemBox.getChildren().addAll(itemLabel, deleteItemButton);
			
			pantryGrid.add(itemBox, itemColumn, itemRow);
			if (itemColumn == 3) {
				itemColumn = 0;
				itemRow++;
			}
			else itemColumn++;
			EventHandler<ActionEvent> deletePantryItem = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					newPantryItems.remove(item);
					pantryGrid.getChildren().clear();
					displayPantry();
				}
			};
			deleteItemButton.setOnAction(deletePantryItem);
		}
	}
	
}