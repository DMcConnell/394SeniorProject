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
		grid.add(title, 2, 0);
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
		grid.add(milkLabel, 4, 2);
		grid.add(milkBox, 5, 2);
		
		//Fish
		CheckBox fishBox = new CheckBox();
		Label fishLabel = new Label("Fish:");
		fishLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		fishLabel.setTextFill(Color.web("#4278f5"));
		grid.add(fishLabel, 4, 3);
		grid.add(fishBox, 5, 3);
		
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
		grid.add(sesameLabel, 4, 4);
		grid.add(sesameBox, 5, 4);
		
		//Pantry
		Label pantryLabel = new Label("Pantry");
		pantryLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
		grid.add(pantryLabel, 0, 7);
		
		//Steak
		CheckBox steakBox = new CheckBox();
		Label steakLabel = new Label("Steak:");
		steakLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(steakLabel, 0, 8);
		grid.add(steakBox, 1, 8);
		
		//Ground beef
		CheckBox beefBox = new CheckBox();
		Label beefLabel = new Label("Ground Beef:");
		beefLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(beefLabel, 2, 8);
		grid.add(beefBox, 3, 8);
		
		//Turkey
		CheckBox turkeyBox = new CheckBox();
		Label turkeyLabel = new Label("Turkey:");
		turkeyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(turkeyLabel, 4, 8);
		grid.add(turkeyBox, 5, 8);
		
		//Ground Turkey
		CheckBox grndTurkeyBox = new CheckBox();
		Label grndTurkeyLabel = new Label("Ground Turkey:");
		grndTurkeyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(grndTurkeyLabel, 0, 9);
		grid.add(grndTurkeyBox, 1, 9);
		
		//Chicken
		CheckBox chickenBox = new CheckBox();
		Label chickenLabel = new Label("Chicken:");
		chickenLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(chickenLabel, 2, 9);
		grid.add(chickenBox, 3, 9);
		
		//Eggs
		CheckBox eggPantryBox = new CheckBox();
		Label eggPantryLabel = new Label("Eggs:");
		eggPantryLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(eggPantryLabel, 4, 9);
		grid.add(eggPantryBox, 5, 9);
		
		//Salmon
		CheckBox salmonBox = new CheckBox();
		Label salmonLabel = new Label("Salmon:");
		salmonLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(salmonLabel, 0, 10);
		grid.add(salmonBox, 1, 10);
		
		//Tuna
		CheckBox tunaBox = new CheckBox();
		Label tunaLabel = new Label("Tuna:");
		tunaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(tunaLabel, 2, 10);
		grid.add(tunaBox, 3, 10);
		
		//Catfish
		CheckBox catfishBox = new CheckBox();
		Label catfishLabel = new Label("Catfish:");
		catfishLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(catfishLabel, 4, 10);
		grid.add(catfishBox, 5, 10);
		
		//Lobster
		CheckBox lobsterBox = new CheckBox();
		Label lobsterLabel = new Label("Lobster:");
		lobsterLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(lobsterLabel, 0, 11);
		grid.add(lobsterBox, 1, 11);
		
		//Crablegs
		CheckBox crabBox = new CheckBox();
		Label crabLabel = new Label("Crab Legs:");
		crabLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(crabLabel, 2, 11);
		grid.add(crabBox, 3, 11);
		
		//Veel
		CheckBox veelBox = new CheckBox();
		Label veelLabel = new Label("Veel:");
		veelLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(veelLabel, 4, 11);
		grid.add(veelBox, 5, 11);
		
		//Ground Chicken
		CheckBox grndChickenBox = new CheckBox();
		Label grndChickenLabel = new Label("Ground Chicken:");
		grndChickenLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(grndChickenLabel, 0, 12);
		grid.add(grndChickenBox, 1, 12);
		
		//Calbot
		CheckBox calbotBox = new CheckBox();
		Label calbotLabel = new Label("Calbot:");
		calbotLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(calbotLabel, 2, 12);
		grid.add(calbotBox, 3, 12);
		
		//Lamb
		CheckBox lambBox = new CheckBox();
		Label lambLabel = new Label("Lamb:");
		lambLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(lambLabel, 4, 12);
		grid.add(lambBox, 5, 12);
		
		//Pork
		CheckBox porkBox = new CheckBox();
		Label porkLabel = new Label("Pork:");
		porkLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(porkLabel, 0, 13);
		grid.add(porkBox, 1, 13);
		
		//Tofu
		CheckBox tofuBox = new CheckBox();
		Label tofuLabel = new Label("Tofu:");
		tofuLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(tofuLabel, 2, 13);
		grid.add(tofuBox, 3, 13);
		
		//Deer
		CheckBox deerBox = new CheckBox();
		Label deerLabel = new Label("Deer:");
		deerLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(deerLabel, 4, 13);
		grid.add(deerBox, 5, 13);
		
		//Elk
		CheckBox elkBox = new CheckBox();
		Label elkLabel = new Label("Elk:");
		elkLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(elkLabel, 0, 14);
		grid.add(elkBox, 1, 14);
		
		//Ostrich
		CheckBox ostrichBox = new CheckBox();
		Label ostrichLabel = new Label("Ostrich:");
		ostrichLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(ostrichLabel, 2, 14);
		grid.add(ostrichBox, 3, 14);
		
		//Shellfish
		CheckBox shellfishPantryBox = new CheckBox();
		Label shellfishPantryLabel = new Label("Shellfish:");
		shellfishPantryLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(shellfishPantryLabel, 4, 14);
		grid.add(shellfishPantryBox, 5, 14);
		
		//Plant-based Meat
		CheckBox plantBasedMeatBox = new CheckBox();
		Label plantBasedMeatLabel = new Label("Plant-based Meat:");
		plantBasedMeatLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(plantBasedMeatLabel, 0, 15);
		grid.add(plantBasedMeatBox, 1, 15);
		
		//Tomatoes
		CheckBox tomatoBox = new CheckBox();
		Label tomatoLabel = new Label("Tomatoes:");
		tomatoLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(tomatoLabel, 2, 15);
		grid.add(tomatoBox, 3, 15);
		
		//Peas
		CheckBox peaBox = new CheckBox();
		Label peaLabel = new Label("Pea:");
		peaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(peaLabel, 4, 15);
		grid.add(peaBox, 5, 15);
		
		//Green Peppers
		CheckBox greenPepperBox = new CheckBox();
		Label greenPepperLabel = new Label("Green Peppers:");
		greenPepperLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(greenPepperLabel, 0, 16);
		grid.add(greenPepperBox, 1, 16);
		
		//Red Peppers
		CheckBox redPepperBox = new CheckBox();
		Label redPepperLabel = new Label("Red Peppers:");
		redPepperLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(redPepperLabel, 2, 16);
		grid.add(redPepperBox, 3, 16);
		
		//Orange Pepeprs
		CheckBox orangePepperBox = new CheckBox();
		Label orangePepperLabel = new Label("Orange Peppers:");
		orangePepperLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(orangePepperLabel, 4, 16);
		grid.add(orangePepperBox, 5, 16);
		
		//Banana Peppers
		CheckBox bananaPepperBox = new CheckBox();
		Label bananaPepperLabel = new Label("Banana Peppers:");
		bananaPepperLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(bananaPepperLabel, 0, 17);
		grid.add(bananaPepperBox, 1, 17);
		
		//Kidney Beans
		CheckBox kidneyBox = new CheckBox();
		Label kidneyLabel = new Label("Kidney Beans:");
		kidneyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(kidneyLabel, 2, 17);
		grid.add(kidneyBox, 3, 17);
		
		//Lentils
		CheckBox lentilBox = new CheckBox();
		Label lentilLabel = new Label("Lentil Beans:");
		lentilLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(lentilLabel, 4, 17);
		grid.add(lentilBox, 5, 17);
		
		//Blackbeans
		CheckBox blackBeanBox = new CheckBox();
		Label blackBeanLabel = new Label("Black Beans:");
		blackBeanLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(blackBeanLabel, 0, 18);
		grid.add(blackBeanBox, 1, 18);
		
		//Chickpeas
		CheckBox chickPeaBox = new CheckBox();
		Label chickPeaLabel = new Label("Chickpeas:");
		chickPeaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(chickPeaLabel, 2, 18);
		grid.add(chickPeaBox, 3, 18);
		
		//Pinto beans
		CheckBox pintoBeanBox = new CheckBox();
		Label pintoBeanLabel = new Label("Pinto Beans:");
		pintoBeanLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(pintoBeanLabel, 4, 18);
		grid.add(pintoBeanBox, 5, 18);
		
		//Cabbage
		CheckBox cabbageBox = new CheckBox();
		Label cabbageLabel = new Label("Cabbage:");
		cabbageLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(cabbageLabel, 0, 19);
		grid.add(cabbageBox, 1, 19);
		
		//Cauliflower
		CheckBox cauliflowerBox = new CheckBox();
		Label cauliflowerLabel = new Label("Elk:");
		cauliflowerLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(cauliflowerLabel, 2, 19);
		grid.add(cauliflowerBox, 3, 19);
		
		//Spinach
		CheckBox spinachBox = new CheckBox();
		Label spinachLabel = new Label("Spinach:");
		spinachLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(spinachLabel, 4, 19);
		grid.add(spinachBox, 5, 19);
		
		//Kale
		CheckBox kaleBox = new CheckBox();
		Label kaleLabel = new Label("Kale:");
		kaleLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(kaleLabel, 0, 20);
		grid.add(kaleBox, 1, 20);
		
		//Celery
		CheckBox celeryBox = new CheckBox();
		Label celeryLabel = new Label("Celery:");
		celeryLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(celeryLabel, 2, 20);
		grid.add(celeryBox, 3, 20);
		
		//Broccoli
		CheckBox broccoliBox = new CheckBox();
		Label broccoliLabel = new Label("Broccoli:");
		broccoliLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(broccoliLabel, 4, 20);
		grid.add(broccoliBox, 5, 20);
		
		//Carrots
		CheckBox carrotBox = new CheckBox();
		Label carrotLabel = new Label("Carrot:");
		carrotLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(carrotLabel, 0, 21);
		grid.add(carrotBox, 1, 21);
		
		//Anise
		CheckBox aniseBox = new CheckBox();
		Label aniseLabel = new Label("Anise:");
		aniseLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(aniseLabel, 2, 21);
		grid.add(aniseBox, 3, 21);
		
		//Basil
		CheckBox basilBox = new CheckBox();
		Label basilLabel = new Label("Basil:");
		basilLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(basilLabel, 4, 21);
		grid.add(basilBox, 5, 21);
		
		//Chamomile
		CheckBox chamomileBox = new CheckBox();
		Label chamomileLabel = new Label("Chamomile:");
		chamomileLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(chamomileLabel, 0, 22);
		grid.add(chamomileBox, 1, 22);
		
		//Dill
		CheckBox dillBox = new CheckBox();
		Label dillLabel = new Label("Dill:");
		dillLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(dillLabel, 2, 22);
		grid.add(dillBox, 3, 22);
		
		//Oregano
		CheckBox oreganoBox = new CheckBox();
		Label oreganoLabel = new Label("Oregano:");
		oreganoLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(oreganoLabel, 4, 22);
		grid.add(oreganoBox, 5, 22);
		
		//Parsley
		CheckBox parsleyBox = new CheckBox();
		Label parsleyLabel = new Label("Parsley:");
		parsleyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(parsleyLabel, 0, 23);
		grid.add(parsleyBox, 1, 23);
		
		//Rosemary
		CheckBox rosemaryBox = new CheckBox();
		Label rosemaryLabel = new Label("Rosemary:");
		rosemaryLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(rosemaryLabel, 2, 23);
		grid.add(rosemaryBox, 3, 23);
		
		//Sage
		CheckBox sageBox = new CheckBox();
		Label sageLabel = new Label("Sage:");
		sageLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(sageLabel, 4, 23);
		grid.add(sageBox, 5, 23);
		
		//Lettuce
		CheckBox lettuceBox = new CheckBox();
		Label lettuceLabel = new Label("Lettuce:");
		lettuceLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(lettuceLabel, 0, 24);
		grid.add(lettuceBox, 1, 24);
		
		//Arugula
		CheckBox arugulaBox = new CheckBox();
		Label arugulaLabel = new Label("Arugula:");
		arugulaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(arugulaLabel, 2, 24);
		grid.add(arugulaBox, 3, 24);
		
		//Mushrooms
		CheckBox mushroomBox = new CheckBox();
		Label mushroomLabel = new Label("Mushrooms:");
		mushroomLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(mushroomLabel, 4, 24);
		grid.add(mushroomBox, 5, 24);
		
		//Onions
		CheckBox onionBox = new CheckBox();
		Label onionLabel = new Label("Onions:");
		onionLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(onionLabel, 0, 25);
		grid.add(onionBox, 1, 25);
		
		//Garlic
		CheckBox garlicBox = new CheckBox();
		Label garlicLabel = new Label("Garlic:");
		garlicLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(garlicLabel, 2, 25);
		grid.add(garlicBox, 3, 25);
		
		//Leeks
		CheckBox leekBox = new CheckBox();
		Label leekLabel = new Label("Leeks:");
		leekLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(leekLabel, 4, 25);
		grid.add(leekBox, 5, 25);
		
		//Beets
		CheckBox beetBox = new CheckBox();
		Label beetLabel = new Label("Beets:");
		beetLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(beetLabel, 0, 26);
		grid.add(beetBox, 1, 26);
		
		//Jalapeno
		CheckBox jalapenoBox = new CheckBox();
		Label jalapenoLabel = new Label("Jalapeno:");
		jalapenoLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(jalapenoLabel, 2, 26);
		grid.add(jalapenoBox, 3, 26);
		
		//Paprika
		CheckBox paprikaBox = new CheckBox();
		Label paprikaLabel = new Label("Paprika:");
		paprikaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(paprikaLabel, 4, 26);
		grid.add(paprikaBox, 5, 26);
		
		//Tabasco Pepper
		CheckBox tabascoBox = new CheckBox();
		Label tabascoLabel = new Label("Tabasco Pepper:");
		tabascoLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(tabascoLabel, 0, 27);
		grid.add(tabascoBox, 1, 27);
		
		//Ginger
		CheckBox gingerBox = new CheckBox();
		Label gingerLabel = new Label("Ginger:");
		gingerLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(gingerLabel, 2, 27);
		grid.add(gingerBox, 3, 27);
		
		//Cayenne Pepper
		CheckBox cayenneBox = new CheckBox();
		Label cayenneLabel = new Label("Cayenne Peppers:");
		cayenneLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(cayenneLabel, 4, 27);
		grid.add(cayenneBox, 5, 27);
		
		//Radish
		CheckBox radishBox = new CheckBox();
		Label radishLabel = new Label("Radishes:");
		radishLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(radishLabel, 0, 28);
		grid.add(radishBox, 1, 28);
		
		//Sweet Potatoes
		CheckBox sweetPotatoBox = new CheckBox();
		Label sweetPotatoLabel = new Label("Sweet Potatoes:");
		sweetPotatoLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(sweetPotatoLabel, 2, 28);
		grid.add(sweetPotatoBox, 3, 28);
		
		//Potatoes
		CheckBox potatoBox = new CheckBox();
		Label potatoLabel = new Label("Potatoes:");
		potatoLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(potatoLabel, 4, 28);
		grid.add(potatoBox, 5, 28);
		
		//Butternut Squash
		CheckBox butternutSquashBox = new CheckBox();
		Label butternutSquashLabel = new Label("Butternut Squash:");
		butternutSquashLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(butternutSquashLabel, 0, 29);
		grid.add(butternutSquashBox, 1, 29);
		
		//Cucumber
		CheckBox cucumberBox = new CheckBox();
		Label cucumberLabel = new Label("Cucumbers:");
		cucumberLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(cucumberLabel, 2, 29);
		grid.add(cucumberBox, 3, 29);
		
		//Black pepper
		CheckBox blackPepperBox = new CheckBox();
		Label blackPepperLabel = new Label("Black Peppers:");
		blackPepperLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(blackPepperLabel, 4, 29);
		grid.add(blackPepperBox, 5, 29);
		
		//Table salt
		CheckBox tableSaltBox = new CheckBox();
		Label tableSaltLabel = new Label("Table Salt:");
		tableSaltLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(tableSaltLabel, 0, 30);
		grid.add(tableSaltBox, 1, 30);
		
		//Sea salt
		CheckBox seaSaltBox = new CheckBox();
		Label seaSaltLabel = new Label("Sea Salt:");
		seaSaltLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(seaSaltLabel, 2, 30);
		grid.add(seaSaltBox, 3, 30);
		
		//Nutmeg
		CheckBox nutmegBox = new CheckBox();
		Label nutmegLabel = new Label("Nutmeg:");
		nutmegLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(nutmegLabel, 4, 30);
		grid.add(nutmegBox, 5, 30);
		
		//Rotini Noodles
		CheckBox rotiniBox = new CheckBox();
		Label rotiniLabel = new Label("Rotini Noodles:");
		rotiniLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(rotiniLabel, 0, 31);
		grid.add(rotiniBox, 1, 31);
		
		//Spaghetti
		CheckBox spaghettiBox = new CheckBox();
		Label spaghettiLabel = new Label("Spaghetti Noodles:");
		spaghettiLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(spaghettiLabel, 2, 31);
		grid.add(spaghettiBox, 3, 31);
		
		//Ravioli
		CheckBox ravioliBox = new CheckBox();
		Label ravioliLabel = new Label("Ravioli Noodles:");
		ravioliLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(ravioliLabel, 4, 31);
		grid.add(ravioliBox, 5, 31);
		
		//Angel Hair Noodles
		CheckBox angelHairBox = new CheckBox();
		Label angelHairLabel = new Label("Angel Hair Noodles:");
		angelHairLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(angelHairLabel, 0, 32);
		grid.add(angelHairBox, 1, 32);
		
		//Lasagne
		CheckBox lasagneBox = new CheckBox();
		Label lasagneLabel = new Label("Lasagne:");
		lasagneLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(lasagneLabel, 2, 32);
		grid.add(lasagneBox, 3, 32);
		
		//Cavatappi noodles
		CheckBox cavatappiBox = new CheckBox();
		Label cavatappiLabel = new Label("Cavatappi Noodles:");
		cavatappiLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(cavatappiLabel, 4, 32);
		grid.add(cavatappiBox, 5, 32);
		
		//Rigatoni noodles
		CheckBox rigatoniBox = new CheckBox();
		Label rigatoniLabel = new Label("Rigatoni Noodles:");
		rigatoniLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(rigatoniLabel, 0, 33);
		grid.add(rigatoniBox, 1, 33);
		
		//Linguine noodles
		CheckBox linguineBox = new CheckBox();
		Label linguineLabel = new Label("Linguine Noodles:");
		linguineLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(linguineLabel, 2, 33);
		grid.add(linguineBox, 3, 33);
		
		//Penne noodles
		CheckBox penneBox = new CheckBox();
		Label penneLabel = new Label("Penne Noodles:");
		penneLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(penneLabel, 4, 33);
		grid.add(penneBox, 5, 33);
		
		
		//Save changes button
		Button saveButton = new Button("Save Changes");
		saveButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(saveButton, 0, 34);
		
		/*---------------------------------------ACTIONS---------------------------------------------------------------*/
		
        saveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //UPDATE DATABASE TO REFLECT CHANGES MADE, AND THEN DISPLAY THE MESSAGE "CHANGES SAVED"
            }
        });
        
        //TODO NEED A FUNCTION(S) TO HIDE ALLERGENS FROM PANTRY WHEN CERTAIN ALLERGIES ARE SELECTED
        
        /*-------------------------------------------------------------------------------------------------------------*/
        
		ScrollPane sp = new ScrollPane();
		sp.setContent(grid);
        Scene scene = new Scene(sp, 1820, 980);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}