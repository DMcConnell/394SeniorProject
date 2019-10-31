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
import javafx.scene.control.TextField;
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

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eat.services.IAllergy;
import com.eat.support.*;
import com.eat.services.Exceptions.*;

public class UploadRecipe extends GridPane {

	
	Text scenetitle;
	Label Prefix;
	Label title;
	TextField titleField;
	Label image;
	TextField imageField;
	Label author;
	TextField authorField;
	Label blurb;
	TextField blurbField;
	Label allergy;
	ComboBox<String> allergies;
	Button allergyAdd;
	HBox allergyList;
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
	
	ArrayList<String> addedAllergies;
	ArrayList<Ingredient> addedIngredients;
	ArrayList<String> addedInstructions;
	
	
    public UploadRecipe()
    {
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(20, 20, 20, 20));
        this.getColumnConstraints().add(new ColumnConstraints(150)); // column 0 is 150 wide

        scenetitle = new Text("Upload your own recipe.");
        scenetitle.setTextAlignment(TextAlignment.CENTER);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
        this.add(scenetitle, 0, 0, 3, 1);
        
        Prefix = new Label("");
        Prefix.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Prefix.setWrapText(true);
        Prefix.setTextFill(Color.web("red"));
        this.add(Prefix, 0, 1, 1, 2);

        //title fields
        title = new Label("Title:");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(title, 1, 1);

        titleField = new TextField();
        titleField.setMinWidth(200);
        titleField.setMaxWidth(300);
        titleField.setMinHeight(50);
        titleField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(titleField, 2, 1);
        
        //image fields
        image = new Label("Image URL:");
        image.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(image, 1, 2);

        imageField = new TextField();
        imageField.setMinWidth(200);
        imageField.setMaxWidth(300);
        imageField.setMinHeight(50);
        this.add(imageField, 2, 2);
        
        //author fields
        author = new Label("Author:");
        author.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(author, 1, 3);

        authorField = new TextField();
        authorField.setMinWidth(200);
        authorField.setMaxWidth(300);
        authorField.setMinHeight(50);
        this.add(authorField, 2, 3);
        
        //blurb fields
        blurb = new Label("Summary:");
        blurb.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(blurb, 1, 4);

        blurbField = new TextField();
        blurbField.setMinWidth(200);
        blurbField.setMaxWidth(300);
        blurbField.setMinHeight(50);
        this.add(blurbField, 2, 4);
        
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
        this.add(allergy, 2, 5);
        allergies = new ComboBox<String>(allergyOptions);
        //allergies.setEditable(true);
        this.add(allergies, 3, 5);
        allergyAdd = new Button("Add");
        this.add(allergyAdd, 4, 5);
        
        //HBox to list out the selected allergies
        allergyList = new HBox();
        this.add(allergyList, 2, 6);
        addedAllergies = new ArrayList<String>();
        
        quantity = new Label("quantity");
        this.add(quantity, 2, 7);
        unit = new Label("unit");
        this.add(unit, 3, 7);
        ingredient = new Label("ingredient");
        this.add(ingredient, 4, 7);
        //HBox for ingredients with (num field for quantity, text field for Unit, Combo Box for ingredient name, button for add ingredient)
        quantityField = new TextField();
        this.add(quantityField, 2, 8);
        unitField = new TextField();
        this.add(unitField, 3, 8);
        ingredientField = new TextField();
        this.add(ingredientField, 4, 8);
        addIngredient = new Button("Add");
        this.add(addIngredient, 5, 8);
        
        addedIngredients = new ArrayList<Ingredient>();
        
        //Vbox of text that combines all the above fields into one string line
        ingredientList = new VBox();
        this.add(ingredientList, 2, 9);
        
        
        //HBox for instructions with (text field)
        instructions = new Label("Steps (in order):");
        this.add(instructions, 2, 10, 1, 1);
        instructionsField = new TextField();
        this.add(instructionsField, 3, 10, 2, 1);
        addInstruction = new Button("add");
        this.add(addInstruction, 5, 10, 1, 1);
        //VBox of text, each row numbered.
        instructionsList = new VBox();
        this.add(instructionsList, 2, 11, 1, 1);
        addedInstructions = new ArrayList<String>();
        
        
        //upload button
        Upload = new Button("UPLOAD");
        Upload.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Upload.setMinWidth(300);
        this.add(Upload, 3, 12);
        
        
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
            	
            	Pattern pattern = Pattern.compile("[^A-Za-z0-9.,?!-]");
            	Matcher matcher = pattern.matcher(t);
            	boolean specialT = matcher.find();
            	matcher = pattern.matcher(a);
            	boolean specialA = matcher.find();
            	matcher = pattern.matcher(b);
            	boolean specialB = matcher.find();
            	
            	String i = imageField.getText();
            	
            	pattern = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
            	matcher = pattern.matcher(i);
            	boolean specialI = matcher.find();
            	
            	if(t.length() < 1)
            	{
            		Prefix.setText("Enter a title");
            		titleField.setStyle("-fx-border-color: red");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		imageField.setStyle("-fx-border-color: black");
            	}
            	else if(t.length() > 30)
            	{
            		Prefix.setText("Title max is 30 characters");
            		titleField.setStyle("-fx-border-color: red");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		imageField.setStyle("-fx-border-color: black");
            	}
            	else if(specialT)
            	{
            		Prefix.setText("Do not use special characters in your title");
            		titleField.setStyle("-fx-border-color: red");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		imageField.setStyle("-fx-border-color: black");
            	}
            	else if(a.length() < 1)
            	{
            		Prefix.setText("Enter an author name");
            		titleField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: red");
            		blurbField.setStyle("-fx-border-color: black");
            		imageField.setStyle("-fx-border-color: black");
            	}
            	else if(a.length() > 30)
            	{
            		Prefix.setText("Author name max is 30 characters");
            		titleField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: red");
            		blurbField.setStyle("-fx-border-color: black");
            		imageField.setStyle("-fx-border-color: black");
            	}
            	else if(specialA)
            	{
            		Prefix.setText("Do not use special characters in your author name");
            		titleField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: red");
            		blurbField.setStyle("-fx-border-color: black");
            		imageField.setStyle("-fx-border-color: black");
            	}
            	else if(b.length() < 1)
            	{
            		Prefix.setText("Enter a summary");
            		titleField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: red");
            		imageField.setStyle("-fx-border-color: black");
            	}
            	else if(b.length() > 250)
            	{
            		Prefix.setText("Summary max length is 250 characters");
            		titleField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: red");
            		imageField.setStyle("-fx-border-color: black");
            	}
            	else if(specialB)
            	{
            		Prefix.setText("Do not use special characters in your summary");
            		titleField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: red");
            		imageField.setStyle("-fx-border-color: black");
            	}
            	else if(i.length() < 1)
            	{
            		Prefix.setText("Enter an image URL");
            		titleField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		imageField.setStyle("-fx-border-color: red");
            	}
            	else if(i.length() > 256)
            	{
            		Prefix.setText("Image URL max length is 256 characters");
            		titleField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		imageField.setStyle("-fx-border-color: red");
            	}
            	else if(specialI)
            	{
            		Prefix.setText("Enter a valid URL");
            		titleField.setStyle("-fx-border-color: black");
            		authorField.setStyle("-fx-border-color: black");
            		blurbField.setStyle("-fx-border-color: black");
            		imageField.setStyle("-fx-border-color: red");
            	}
            	else //all valid information
            	{
            		int id = 0;
            		boolean success = false;
                	try
                	{
                		
                		//Recipe r = uploadRecipe();
                		//id = r.getID();
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
            		addedAllergies.add(s);
            		allergyList.getChildren().add(new Text(s + " "));
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
            	
            	Pattern pattern = Pattern.compile("[^A-Za-z]");
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
                	Ingredient i = new Ingredient(ing, Float.parseFloat(q), u);
                	addedIngredients.add(i);
                	ingredientList.getChildren().add(new Text(q + " " + u + " " + ing + System.lineSeparator()));
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
            	Pattern pattern = Pattern.compile("[^A-Za-z0-9.,:?]");
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
            		addedInstructions.add(s);
            		instructionsList.getChildren().add(new Text(instructionCounter + ": " + s));
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