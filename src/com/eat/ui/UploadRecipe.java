package com.eat.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eat.services.IAllergy;
import com.eat.services.IRecipe;
import com.eat.support.*;
import com.eat.services.Exceptions.*;

public class UploadRecipe extends ScrollPane {

	GridPane GRID;
	
	Text scenetitle;
	Label Prefix;
	Label title;
	TextField titleField;
	Label servings;
	TextField servingsField;
	Label time;
	TextField timeField;
	Label image;
	TextField imageField;
	Label author;
	TextField authorField;
	Label blurb;
	TextArea blurbField;
	Label allergy;
	ComboBox<String> allergies;
	Button allergyAdd;
	HBox allergyList;
	Label ingredients;
	Label quantity;
	Label unit;
	Label ingredient;
	TextField quantityField;
	TextField unitField;
	TextField ingredientField;
	Button addIngredient;
	VBox ingredientList;
	Label instructions;
	TextField instructionsField;
	Button addInstruction;
	VBox instructionsList;
	int instructionCounter = 0;
	
	Button Upload;
	
	List<String> addedAllergies;
	List<Ingredient> addedIngredients;
	Map<Integer, String> addedInstructions;
	
	
    public UploadRecipe()
    {
    	GRID = new GridPane();
        GRID.setAlignment(Pos.CENTER);
        GRID.setHgap(10);
        GRID.setVgap(10);
        GRID.setPadding(new Insets(20, 20, 20, 20));
        GRID.getColumnConstraints().add(new ColumnConstraints(150)); // column 0 is 150 wide
        
        int vLevel = 0;

        scenetitle = new Text("Upload your own recipe.");
        scenetitle.setTextAlignment(TextAlignment.CENTER);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
        GRID.add(scenetitle, 0, vLevel, 3, 1);
        vLevel++;
        
        Prefix = new Label("");
        Prefix.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Prefix.setWrapText(true);
        Prefix.setTextFill(Color.web("red"));
        GRID.add(Prefix, 3, 4, 1, 2);

        //title fields
        title = new Label("Title:");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GRID.add(title, 0, vLevel);

        titleField = new TextField();
        titleField.setMinWidth(200);
        titleField.setMaxWidth(300);
        titleField.setMinHeight(50);
        titleField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GRID.add(titleField, 1, vLevel);
        vLevel++;
        /*
        //image fields
        image = new Label("Image URL:");
        image.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GRID.add(image, 0, vLevel);

        imageField = new TextField();
        imageField.setMinWidth(200);
        imageField.setMaxWidth(300);
        imageField.setMinHeight(50);
        GRID.add(imageField, 1, vLevel);
        vLevel++;
        */
        //author fields
        author = new Label("Author:");
        author.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GRID.add(author, 0, vLevel);

        authorField = new TextField();
        authorField.setMinWidth(200);
        authorField.setMaxWidth(300);
        authorField.setMinHeight(50);
        GRID.add(authorField, 1, vLevel);
        vLevel++;
        
     	//servings fields
        servings = new Label("Number of Servings:");
        servings.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GRID.add(servings, 0, vLevel);

        servingsField = new TextField();
        servingsField.setMinWidth(200);
        servingsField.setMaxWidth(300);
        servingsField.setMinHeight(50);
        GRID.add(servingsField, 1, vLevel);
        vLevel++;
        
        //time fields
        time = new Label("Cook Time (minutes):");
        time.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GRID.add(time, 0, vLevel);

        timeField = new TextField();
        timeField.setMinWidth(200);
        timeField.setMaxWidth(300);
        timeField.setMinHeight(50);
        GRID.add(timeField, 1, vLevel);
        vLevel++;
        
        //blurb fields
        blurb = new Label("Summary:");
        blurb.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GRID.add(blurb, 0, vLevel);

        blurbField = new TextArea();
        blurbField.setWrapText(true);
        blurbField.setMinWidth(200);
        blurbField.setMaxWidth(300);
        blurbField.setMinHeight(50);
        GRID.add(blurbField, 1, vLevel);
        vLevel++;
        
        //COMBO BOX FOR ALLERGIES
        ObservableList<String> allergyOptions = 
        	    FXCollections.observableArrayList(
        	        IAllergy.PEANUT,
        	        IAllergy.MILK,
        	        IAllergy.EGG,
        	        IAllergy.SHELLFISH,
        	        IAllergy.FISH,
        	        IAllergy.TREENUTS,
        	        IAllergy.WHEAT,
        	        IAllergy.SOY,
        	        IAllergy.SESAME
        	    );
        
        allergy = new Label("Allergies:");
        allergy.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GRID.add(allergy, 0, vLevel);
        allergies = new ComboBox<String>(allergyOptions);
        //allergies.setEditable(true);
        GRID.add(allergies, 1, vLevel);
        allergyAdd = new Button("Add");
        GRID.add(allergyAdd, 2, vLevel);
        vLevel++;
        
        //HBox to list out the selected allergies
        allergyList = new HBox();
        GRID.add(allergyList, 1, vLevel);
        vLevel++;
        
        addedAllergies = new ArrayList<String>();
        
        ingredients = new Label("Ingredients:");
        ingredients.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GRID.add(ingredients, 0, vLevel);
        quantity = new Label("quantity");
        quantity.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GRID.add(quantity, 1, vLevel);
        unit = new Label("unit");
        unit.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GRID.add(unit, 2, vLevel);
        ingredient = new Label("ingredient");
        ingredient.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GRID.add(ingredient, 3, vLevel);
        vLevel++;
        
        //HBox for ingredients with (num field for quantity, text field for Unit, Combo Box for ingredient name, button for add ingredient)
        quantityField = new TextField();
        GRID.add(quantityField, 1, vLevel);
        unitField = new TextField();
        GRID.add(unitField, 2, vLevel);
        ingredientField = new TextField();
        GRID.add(ingredientField, 3, vLevel);
        addIngredient = new Button("Add");
        GRID.add(addIngredient, 4, vLevel);
        vLevel++;
        
        addedIngredients = new ArrayList<Ingredient>();
        
        //Vbox of text that combines all the above fields into one string line
        ingredientList = new VBox();
        GRID.add(ingredientList, 1, vLevel);
        vLevel++;
        
        
        //HBox for instructions with (text field)
        instructions = new Label("Steps (in order):");
        instructions.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GRID.add(instructions, 0, vLevel, 1, 1);
        instructionsField = new TextField();
        GRID.add(instructionsField, 1, vLevel, 2, 1);
        addInstruction = new Button("Add");
        GRID.add(addInstruction, 3, vLevel);
        vLevel++;
        
        //VBox of text, each row numbered.
        instructionsList = new VBox();
        GRID.add(instructionsList, 1, vLevel);
        vLevel++;
        
        
        addedInstructions = new HashMap<Integer, String>();
        
        
        //upload button
        Upload = new Button("UPLOAD");
        Upload.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Upload.setMinWidth(300);
        GRID.add(Upload, 1, vLevel);
        vLevel++;
        
        this.setContent(GRID);
        
        
        setActions();
    }
    
    public void setActions()
    {
    	/*------------------------------------------------------ACTIONS------------------------------------------------------*/
        Upload.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	String t = titleField.getText();
            	String a = authorField.getText();
            	String b = blurbField.getText();
            	
            	String time = timeField.getText();
            	String servings = servingsField.getText();
            	
            	Pattern pattern = Pattern.compile("[^A-Za-z0-9.,?!' ]");
            	Matcher matcher = pattern.matcher(t);
            	boolean specialT = matcher.find();
            	matcher = pattern.matcher(a);
            	boolean specialA = matcher.find();
            	matcher = pattern.matcher(b);
            	boolean specialB = matcher.find();
            	/*
            	String i = imageField.getText();
            	
            	pattern = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
            	matcher = pattern.matcher(i);
            	boolean specialI = matcher.find();
            	*/
            	boolean specialTime = false;
            	boolean specialServing = false;
            	double dTime = 0;
            	double dServings = 0;
            	try
                {
                	dTime = Float.parseFloat(time);
                }
                catch (NumberFormatException ex) {
                	specialTime = true;
                }
            	try
                {
                	dServings = Float.parseFloat(servings);
                }
                catch (NumberFormatException ex) {
                	specialServing = true;
                }
            	
            	if(t.length() < 1)
            	{
            		Prefix.setText("Enter a title");
            		titleField.setStyle("-fx-border-color: red");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	else if(t.length() > 50)
            	{
            		Prefix.setText("Title max is 50 characters");
            		titleField.setStyle("-fx-border-color: red");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	else if(specialT)
            	{
            		Prefix.setText("Do not use special characters in your title");
            		titleField.setStyle("-fx-border-color: red");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	else if(time.length() < 1)
            	{
            		Prefix.setText("Enter a time");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: red");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	else if(time.length() > 30)
            	{
            		Prefix.setText("Time max is 30 characters");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: red");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	else if(specialTime)
            	{
            		Prefix.setText("Use decimal format for time");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: red");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	else if(servings.length() < 1)
            	{
            		Prefix.setText("Enter number of servings");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: red");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	else if(servings.length() > 30)
            	{
            		Prefix.setText("Servings max is 30 characters");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: red");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	else if(specialServing)
            	{
            		Prefix.setText("Use decimal format for servings");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: red");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	else if(a.length() < 1)
            	{
            		Prefix.setText("Enter an author name");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: red");
            		blurbField.setStyle("-fx-border-color: black");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	else if(a.length() > 50)
            	{
            		Prefix.setText("Author name max is 50 characters");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: red");
            		blurbField.setStyle("-fx-border-color: black");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	else if(specialA)
            	{
            		Prefix.setText("Do not use special characters in your author name");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: red");
            		blurbField.setStyle("-fx-border-color: black");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	else if(b.length() < 1)
            	{
            		Prefix.setText("Enter a summary");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: red");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	else if(b.length() > 250)
            	{
            		Prefix.setText("Summary max length is 250 characters");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: red");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	else if(specialB)
            	{
            		Prefix.setText("Do not use special characters in your summary");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: red");
            		//imageField.setStyle("-fx-border-color: black");
            	}
            	/*else if(i.length() < 1)
            	{
            		Prefix.setText("Enter an image URL");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		imageField.setStyle("-fx-border-color: red");
            	}
            	else if(i.length() > 256)
            	{
            		Prefix.setText("Image URL max length is 256 characters");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		imageField.setStyle("-fx-border-color: red");
            	}
            	else if(specialI)
            	{
            		Prefix.setText("Enter a valid URL");
            		titleField.setStyle("-fx-border-color: black");
            		timeField.setStyle("-fx-border-color: black");
            		servingsField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		imageField.setStyle("-fx-border-color: red");
            	}*/
            	else //all valid information
            	{
            		
            		int id = 0;
            		boolean success = false;
                	try
                	{
                		
                		String recipeID = LaunchStage.getInstance().getRecipeService().addRecipe(t, b, null, a, dServings, dTime).get(IRecipe.RECIPEID);
                		//add ingredients
                		LaunchStage.getInstance().getRecipeService().addIngredients(recipeID, addedIngredients);
                		//add allergies
                		LaunchStage.getInstance().getRecipeService().addRecipeAllergies(recipeID, addedAllergies);
                		//add steps
                		LaunchStage.getInstance().getRecipeService().addSteps(recipeID, addedInstructions);
                		//favorite it
                		LaunchStage.getInstance().getContactService().addFavorite(LaunchStage.getInstance().getContactService().getSelfID(), recipeID);
                		
                		success = true;
                	}
                	catch (Exception ex)
                	{
                		Prefix.setText(ex.getMessage());
                	}
                	if(success)
                	{
                		//open profile page, where profile will retrieve the proper information needed.
                		LaunchStage.getInstance().RecipePane(id);
                	}
                	
            	}
            	
            }
        });
        
        allergyAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	//CHECK ALL FIELDS TO HAVE PROPER FORMAT
            	
            	
                //add selected allergy to list and to HBox as string
            	String s = allergies.getValue();
            	if(s == null)
            	{
            		Prefix.setText("Please select an allergy before adding.");
            		allergies.setStyle("-fx-border-color: red");
            	}
            	else if(addedAllergies.contains(s))
            	{
            		Prefix.setText("Allergy already added.");
            		allergies.setStyle("-fx-border-color: red");
            	}
            	else
            	{
            		Text t = new Text(s + " ");
                    t.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
            		addedAllergies.add(s);
            		allergyList.getChildren().add(t);
            		allergies.setStyle("-fx-border-color: black");
            	}
            }
        });
        
        addIngredient.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	//CHECK ALL FIELDS ESPECIALLY Q TO HAVE PROPER FORMAT
            	String ing = ingredientField.getText();
            	String q = quantityField.getText();
            	String u = unitField.getText();
            	
            	Pattern pattern = Pattern.compile("[^A-Za-z ]");
                Matcher matcher = pattern.matcher(ing);
                boolean specialIng = matcher.find();
                matcher = pattern.matcher(u);
                boolean specialU = matcher.find();
                boolean specialQ = false;
                try
                {
                	Float.parseFloat(q);
                }
                catch (NumberFormatException ex) {
                	specialQ = true;
                }
                
                if(specialQ)
                {
                	Prefix.setText("Please use decimal format for quantity.");
            		quantityField.setStyle("-fx-border-color: red");
            		unitField.setStyle("-fx-border-color: black");
            		ingredientField.setStyle("-fx-border-color: black");
                }
                else if(specialU)
                {
                	Prefix.setText("Please use only letters for the unit.");
            		quantityField.setStyle("-fx-border-color: black");
            		unitField.setStyle("-fx-border-color: red");
            		ingredientField.setStyle("-fx-border-color: black");
                }
                else if(specialIng)
                {
                	Prefix.setText("Please use only letters for the ingredient.");
            		quantityField.setStyle("-fx-border-color: black");
            		unitField.setStyle("-fx-border-color: black");
            		ingredientField.setStyle("-fx-border-color: red");
                }
                else
                {
                	//add all fields to new ingredient and add that to ingredient list and combine strings and add to VBox
            		Text t = new Text(q + " " + u + " " + ing + System.lineSeparator());
                    t.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
                	Ingredient i = new Ingredient(ing, Float.parseFloat(q), u);
                	addedIngredients.add(i);
                	ingredientList.getChildren().add(t);
            		quantityField.setStyle("-fx-border-color: black");
            		unitField.setStyle("-fx-border-color: black");
            		ingredientField.setStyle("-fx-border-color: black");
            		
                }
            	
            	
            }
        });
        
        addInstruction.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	//CHECK ALL FIELDS TO HAVE PROPER FORMAT
            	
            	String s = instructionsField.getText();
            	Pattern pattern = Pattern.compile("[^A-Za-z0-9.,:? ]");
                Matcher matcher = pattern.matcher(s);
                boolean special = matcher.find();
            	if(s.length() < 1)
            	{
            		Prefix.setText("Please enter a valid instruction.");
            		instructionsField.setStyle("-fx-border-color: red");
            	}
            	else if(special)
            	{
            		Prefix.setText("Please do not use special characters in the instructions.");
            		instructionsField.setStyle("-fx-border-color: red");
            	}
            	else
            	{
            		//Add instruction to list as string and to VBox
            		instructionCounter++;
            		Text t = new Text(instructionCounter + ": " + s);
                    t.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
            		addedInstructions.put(instructionCounter, s);
            		instructionsList.getChildren().add(t);
            		instructionsField.setStyle("-fx-border-color: black");
            	}
            }
        });
    }
    
    
    public void TEMP_TEST_LOGIN(String u, String p) throws Exception
    {
    	if(!u.equals("testuser") && !p.equals("password"))
    		throw new InvalidCombinationException();
    }
        


}