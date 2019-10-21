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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Profile extends Application{
	
	public static void main (String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Update Profile");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		
		//Title
		Label title = new Label("Profile");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 70));
		grid.add(title, 0, 0);
		grid.setVgap(20);
		grid.setHgap(200);
		GridPane.setHalignment(title, HPos.CENTER);
		
		//Allergies
		Label allergyLabel = new Label("Allergies");
		allergyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
		grid.add(allergyLabel, 0, 1);
		
		//Tree nuts
		CheckBox treeNutBox = new CheckBox();
		Label treeNutLabel = new Label("Tree Nuts:");
		treeNutLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		treeNutLabel.setTextFill(Color.web("#4278f5"));
		grid.add(treeNutLabel, 0, 2);
		grid.add(treeNutBox, 1, 2);
		
		//Peanuts
		CheckBox peanutBox = new CheckBox();
		Label peanutLabel = new Label("Peanuts:");
		peanutLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		peanutLabel.setTextFill(Color.web("#429943"));
		grid.add(peanutLabel, 0, 3);
		grid.add(peanutBox, 1, 3);
		
		//Shellfish
		CheckBox shellfishBox = new CheckBox();
		Label shellfishLabel = new Label("Shellfish:");
		shellfishLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		shellfishLabel.setTextFill(Color.web("#4278f5"));
		grid.add(shellfishLabel, 0, 4);
		grid.add(shellfishBox, 1, 4);
		
		//Milk
		CheckBox milkBox = new CheckBox();
		Label milkLabel = new Label("Milk:");
		milkLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		milkLabel.setTextFill(Color.web("#429943"));
		grid.add(milkLabel, 0, 5);
		grid.add(milkBox, 1, 5);
		
		//Fish
		CheckBox fishBox = new CheckBox();
		Label fishLabel = new Label("Fish:");
		fishLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		fishLabel.setTextFill(Color.web("#4278f5"));
		grid.add(fishLabel, 0, 6);
		grid.add(fishBox, 1, 6);
		
		//Wheat
		CheckBox wheatBox = new CheckBox();
		Label wheatLabel = new Label("Wheat:");
		wheatLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		wheatLabel.setTextFill(Color.web("#429943"));
		grid.add(wheatLabel, 2, 2);
		grid.add(wheatBox, 3, 2);
		
		//Eggs
		CheckBox eggBox = new CheckBox();
		Label eggLabel = new Label("Eggs:");
		eggLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		eggLabel.setTextFill(Color.web("#4278f5"));
		grid.add(eggLabel, 2, 3);
		grid.add(eggBox, 3, 3);
		
		//Soy
		CheckBox soyBox = new CheckBox();
		Label soyLabel = new Label("Soy:");
		soyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		soyLabel.setTextFill(Color.web("#429943"));
		grid.add(soyLabel, 2, 4);
		grid.add(soyBox, 3, 4);
		
		//Sesame
		CheckBox sesameBox = new CheckBox();
		Label sesameLabel = new Label("Sesame:");
		sesameLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		sesameLabel.setTextFill(Color.web("#4278f5"));
		grid.add(sesameLabel, 2, 5);
		grid.add(sesameBox, 3, 5);
		
		//Pantry
		Label pantryLabel = new Label("Pantry");
		pantryLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
		grid.add(pantryLabel, 0, 7);
		
		
		//Save changes button
		Button saveButton = new Button("Save Changes");
		saveButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(saveButton, 0, 15);
		
		/*---------------------------------------ACTIONS---------------------------------------------------------------*/
		
        saveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //UPDATE DATABASE TO REFLECT CHANGES MADE
            }
        });
		
        Scene scene = new Scene(grid, 1820, 980);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}