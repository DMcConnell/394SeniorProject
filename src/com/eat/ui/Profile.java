package com.eat.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.LinkedList;

import com.eat.services.ContactService;
import com.eat.services.IAllergy;
import com.eat.services.IPantry;

public class Profile extends ScrollPane{

	//	public static void main (String[] args) {
	//		launch(args);
	//	}


	public Profile () {
		try {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.TOP_CENTER);

			//Title
			Label title = new Label("Profile");
			title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 70));
			grid.add(title, 2, 0);
			grid.setVgap(20);
			grid.setHgap(40);
			GridPane.setHalignment(title, HPos.CENTER);

			/*----------------Allergies-------------*/
			ContactService cs = LaunchStage.getInstance().getContactService();
			String username = LaunchStage.getInstance().getContactService().getSelfID();
			LinkedList<String> allergies = cs.getAllergies(username);

			Label allergyLabel = new Label("Allergies");
			allergyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
			grid.add(allergyLabel, 2, 1);

			//Tree nuts
			CheckBox treeNutBox = new CheckBox();
			Label treeNutLabel = new Label("Tree Nuts:");
			treeNutLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			treeNutLabel.setTextFill(Color.web("#4278f5"));
			if (allergies.contains(IAllergy.TREENUTS)) treeNutBox.setSelected(true);
			grid.add(treeNutLabel, 0, 2);
			grid.add(treeNutBox, 1, 2);


			//Peanuts
			CheckBox peanutBox = new CheckBox();
			Label peanutLabel = new Label("Peanuts:");
			peanutLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			peanutLabel.setTextFill(Color.web("#429943"));
			if (allergies.contains(IAllergy.PEANUT)) peanutBox.setSelected(true);
			grid.add(peanutLabel, 0, 3);
			grid.add(peanutBox, 1, 3);

			//Shellfish
			CheckBox shellfishBox = new CheckBox();
			Label shellfishLabel = new Label("Shellfish:");
			shellfishLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			shellfishLabel.setTextFill(Color.web("#4278f5"));
			if (allergies.contains(IAllergy.SHELLFISH)) shellfishBox.setSelected(true);
			grid.add(shellfishLabel, 0, 4);
			grid.add(shellfishBox, 1, 4);

			//Milk
			CheckBox milkBox = new CheckBox();
			Label milkLabel = new Label("Milk:");
			milkLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			milkLabel.setTextFill(Color.web("#429943"));
			if (allergies.contains(IAllergy.MILK)) milkBox.setSelected(true);
			grid.add(milkLabel, 4, 2);
			grid.add(milkBox, 5, 2);

			//Fish
			CheckBox fishBox = new CheckBox();
			Label fishLabel = new Label("Fish:");
			fishLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			fishLabel.setTextFill(Color.web("#4278f5"));
			if (allergies.contains(IAllergy.FISH)) fishBox.setSelected(true);
			grid.add(fishLabel, 4, 3);
			grid.add(fishBox, 5, 3);

			//Wheat
			CheckBox wheatBox = new CheckBox();
			Label wheatLabel = new Label("Wheat:");
			wheatLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			wheatLabel.setTextFill(Color.web("#429943"));
			if (allergies.contains(IAllergy.WHEAT)) wheatBox.setSelected(true);
			grid.add(wheatLabel, 2, 2);
			grid.add(wheatBox, 3, 2);

			//Eggs
			CheckBox eggBox = new CheckBox();
			Label eggLabel = new Label("Eggs:");
			eggLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			eggLabel.setTextFill(Color.web("#4278f5"));
			if (allergies.contains(IAllergy.EGG)) eggBox.setSelected(true);
			grid.add(eggLabel, 2, 3);
			grid.add(eggBox, 3, 3);

			//Soy
			CheckBox soyBox = new CheckBox();
			Label soyLabel = new Label("Soy:");
			soyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			soyLabel.setTextFill(Color.web("#429943"));
			if (allergies.contains(IAllergy.SOY)) soyBox.setSelected(true);
			grid.add(soyLabel, 2, 4);
			grid.add(soyBox, 3, 4);

			//Sesame
			CheckBox sesameBox = new CheckBox();
			Label sesameLabel = new Label("Sesame:");
			sesameLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			sesameLabel.setTextFill(Color.web("#4278f5"));
			if (allergies.contains(IAllergy.SESAME)) sesameBox.setSelected(true);
			grid.add(sesameLabel, 4, 4);
			grid.add(sesameBox, 5, 4);

			/*---------------Pantry---------------------*/
			LinkedList<String> userPantryItems = cs.getPantryItems(username);
			
			Label pantryLabel = new Label("Pantry");
			pantryLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
			grid.add(pantryLabel, 2, 7);

			//Steak
			CheckBox steakBox = new CheckBox();
			Label steakLabel = new Label("Steak:");
			steakLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.STEAK)) steakBox.setSelected(true);
			grid.add(steakLabel, 0, 8);
			grid.add(steakBox, 1, 8);

			//Ground beef
			CheckBox beefBox = new CheckBox();
			Label beefLabel = new Label("Ground Beef:");
			beefLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.GROUNDBEEF)) beefBox.setSelected(true);
			grid.add(beefLabel, 2, 8);
			grid.add(beefBox, 3, 8);

			//Turkey
			CheckBox turkeyBox = new CheckBox();
			Label turkeyLabel = new Label("Turkey:");
			turkeyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.TURKEY)) turkeyBox.setSelected(true);
			grid.add(turkeyLabel, 4, 8);
			grid.add(turkeyBox, 5, 8);

			//Ground Turkey
			CheckBox grndTurkeyBox = new CheckBox();
			Label grndTurkeyLabel = new Label("Ground Turkey:");
			grndTurkeyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.GROUNDTURKEY)) grndTurkeyBox.setSelected(true);
			grid.add(grndTurkeyLabel, 0, 9);
			grid.add(grndTurkeyBox, 1, 9);

			//Chicken
			CheckBox chickenBox = new CheckBox();
			Label chickenLabel = new Label("Chicken:");
			chickenLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.CHICKEN)) chickenBox.setSelected(true);
			grid.add(chickenLabel, 2, 9);
			grid.add(chickenBox, 3, 9);

			//Eggs
			CheckBox eggPantryBox = new CheckBox();
			Label eggPantryLabel = new Label("Eggs:");
			eggPantryLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.EGGS)) eggPantryBox.setSelected(true);
			grid.add(eggPantryLabel, 4, 9);
			grid.add(eggPantryBox, 5, 9);

			//Salmon
			CheckBox salmonBox = new CheckBox();
			Label salmonLabel = new Label("Salmon:");
			salmonLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.SALMON)) salmonBox.setSelected(true);
			grid.add(salmonLabel, 0, 10);
			grid.add(salmonBox, 1, 10);

			//Tuna
			CheckBox tunaBox = new CheckBox();
			Label tunaLabel = new Label("Tuna:");
			tunaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.TUNA)) tunaBox.setSelected(true);
			grid.add(tunaLabel, 2, 10);
			grid.add(tunaBox, 3, 10);

			//Catfish
			CheckBox catfishBox = new CheckBox();
			Label catfishLabel = new Label("Catfish:");
			catfishLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.CATFISH)) catfishBox.setSelected(true);
			grid.add(catfishLabel, 4, 10);
			grid.add(catfishBox, 5, 10);

			//Lobster
			CheckBox lobsterBox = new CheckBox();
			Label lobsterLabel = new Label("Lobster:");
			lobsterLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.LOBSTER)) lobsterBox.setSelected(true);
			grid.add(lobsterLabel, 0, 11);
			grid.add(lobsterBox, 1, 11);

			//Crablegs
			CheckBox crabBox = new CheckBox();
			Label crabLabel = new Label("Crab Legs:");
			crabLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.CRABLEGS)) crabBox.setSelected(true);
			grid.add(crabLabel, 2, 11);
			grid.add(crabBox, 3, 11);

			//Veel
			CheckBox veelBox = new CheckBox();
			Label veelLabel = new Label("Veel:");
			veelLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.VEEL)) veelBox.setSelected(true);
			grid.add(veelLabel, 4, 11);
			grid.add(veelBox, 5, 11);

			//Ground Chicken
			CheckBox grndChickenBox = new CheckBox();
			Label grndChickenLabel = new Label("Ground Chicken:");
			grndChickenLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.GROUNDCHICKEN)) grndChickenBox.setSelected(true);
			grid.add(grndChickenLabel, 0, 12);
			grid.add(grndChickenBox, 1, 12);

			//Calbot
			CheckBox calbotBox = new CheckBox();
			Label calbotLabel = new Label("Calbot:");
			calbotLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.CALBOT)) calbotBox.setSelected(true);
			grid.add(calbotLabel, 2, 12);
			grid.add(calbotBox, 3, 12);

			//Lamb
			CheckBox lambBox = new CheckBox();
			Label lambLabel = new Label("Lamb:");
			lambLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.LAMB)) lambBox.setSelected(true);
			grid.add(lambLabel, 4, 12);
			grid.add(lambBox, 5, 12);

			//Pork
			CheckBox porkBox = new CheckBox();
			Label porkLabel = new Label("Pork:");
			porkLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.PORK)) porkBox.setSelected(true);
			grid.add(porkLabel, 0, 13);
			grid.add(porkBox, 1, 13);

			//Tofu
			CheckBox tofuBox = new CheckBox();
			Label tofuLabel = new Label("Tofu:");
			tofuLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.TOFU)) tofuBox.setSelected(true);
			grid.add(tofuLabel, 2, 13);
			grid.add(tofuBox, 3, 13);

			//Deer
			CheckBox deerBox = new CheckBox();
			Label deerLabel = new Label("Deer:");
			deerLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.DEER)) deerBox.setSelected(true);
			grid.add(deerLabel, 4, 13);
			grid.add(deerBox, 5, 13);

			//Elk
			CheckBox elkBox = new CheckBox();
			Label elkLabel = new Label("Elk:");
			elkLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.ELK)) elkBox.setSelected(true);
			grid.add(elkLabel, 0, 14);
			grid.add(elkBox, 1, 14);

			//Ostrich
			CheckBox ostrichBox = new CheckBox();
			Label ostrichLabel = new Label("Ostrich:");
			ostrichLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.OSTRICH)) ostrichBox.setSelected(true);
			grid.add(ostrichLabel, 2, 14);
			grid.add(ostrichBox, 3, 14);

			//Shellfish
			CheckBox shellfishPantryBox = new CheckBox();
			Label shellfishPantryLabel = new Label("Shellfish:");
			shellfishPantryLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.SHELLFISH)) shellfishBox.setSelected(true);
			grid.add(shellfishPantryLabel, 4, 14);
			grid.add(shellfishPantryBox, 5, 14);

			//Plant-based Meat
			CheckBox plantBasedMeatBox = new CheckBox();
			Label plantBasedMeatLabel = new Label("Plant-based Meat:");
			plantBasedMeatLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.PLANTBASEDMEAT)) plantBasedMeatBox.setSelected(true);
			grid.add(plantBasedMeatLabel, 0, 15);
			grid.add(plantBasedMeatBox, 1, 15);

			//Tomatoes
			CheckBox tomatoBox = new CheckBox();
			Label tomatoLabel = new Label("Tomatoes:");
			tomatoLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.TOMATOES)) tomatoBox.setSelected(true);
			grid.add(tomatoLabel, 2, 15);
			grid.add(tomatoBox, 3, 15);

			//Peas
			CheckBox peaBox = new CheckBox();
			Label peaLabel = new Label("Pea:");
			peaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.PEAS)) peaBox.setSelected(true);
			grid.add(peaLabel, 4, 15);
			grid.add(peaBox, 5, 15);

			//Green Peppers
			CheckBox greenPepperBox = new CheckBox();
			Label greenPepperLabel = new Label("Green Peppers:");
			greenPepperLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.GREENPEPPERS)) greenPepperBox.setSelected(true);
			grid.add(greenPepperLabel, 0, 16);
			grid.add(greenPepperBox, 1, 16);

			//Red Peppers
			CheckBox redPepperBox = new CheckBox();
			Label redPepperLabel = new Label("Red Peppers:");
			redPepperLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.REDPEPPERS)) redPepperBox.setSelected(true);
			grid.add(redPepperLabel, 2, 16);
			grid.add(redPepperBox, 3, 16);

			//Orange Pepeprs
			CheckBox orangePepperBox = new CheckBox();
			Label orangePepperLabel = new Label("Orange Peppers:");
			orangePepperLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.ORANGEPEPPERS)) orangePepperBox.setSelected(true);
			grid.add(orangePepperLabel, 4, 16);
			grid.add(orangePepperBox, 5, 16);

			//Banana Peppers
			CheckBox bananaPepperBox = new CheckBox();
			Label bananaPepperLabel = new Label("Banana Peppers:");
			bananaPepperLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.BANANAPEPPERS)) bananaPepperBox.setSelected(true);
			grid.add(bananaPepperLabel, 0, 17);
			grid.add(bananaPepperBox, 1, 17);

			//Kidney Beans
			CheckBox kidneyBox = new CheckBox();
			Label kidneyLabel = new Label("Kidney Beans:");
			kidneyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.KIDNEYBEANS)) kidneyBox.setSelected(true);
			grid.add(kidneyLabel, 2, 17);
			grid.add(kidneyBox, 3, 17);

			//Lentils
			CheckBox lentilBox = new CheckBox();
			Label lentilLabel = new Label("Lentil Beans:");
			lentilLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.LENTILBEANS)) lentilBox.setSelected(true);
			grid.add(lentilLabel, 4, 17);
			grid.add(lentilBox, 5, 17);

			//Blackbeans
			CheckBox blackBeanBox = new CheckBox();
			Label blackBeanLabel = new Label("Black Beans:");
			blackBeanLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.BLACKBEANS)) blackBeanBox.setSelected(true);
			grid.add(blackBeanLabel, 0, 18);
			grid.add(blackBeanBox, 1, 18);

			//Chickpeas
			CheckBox chickPeaBox = new CheckBox();
			Label chickPeaLabel = new Label("Chickpeas:");
			chickPeaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.CHICKPEAS)) chickPeaBox.setSelected(true);
			grid.add(chickPeaLabel, 2, 18);
			grid.add(chickPeaBox, 3, 18);

			//Pinto beans
			CheckBox pintoBeanBox = new CheckBox();
			Label pintoBeanLabel = new Label("Pinto Beans:");
			pintoBeanLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.PINTOBEANS)) pintoBeanBox.setSelected(true);
			grid.add(pintoBeanLabel, 4, 18);
			grid.add(pintoBeanBox, 5, 18);

			//Cabbage
			CheckBox cabbageBox = new CheckBox();
			Label cabbageLabel = new Label("Cabbage:");
			cabbageLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.CABBAGE)) cabbageBox.setSelected(true);
			grid.add(cabbageLabel, 0, 19);
			grid.add(cabbageBox, 1, 19);

			//Cauliflower
			CheckBox cauliflowerBox = new CheckBox();
			Label cauliflowerLabel = new Label("Cauliflower:");
			cauliflowerLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.CAULIFLOWER)) cauliflowerBox.setSelected(true);
			grid.add(cauliflowerLabel, 2, 19);
			grid.add(cauliflowerBox, 3, 19);

			//Spinach
			CheckBox spinachBox = new CheckBox();
			Label spinachLabel = new Label("Spinach:");
			spinachLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.SPINACH)) spinachBox.setSelected(true);
			grid.add(spinachLabel, 4, 19);
			grid.add(spinachBox, 5, 19);

			//Kale
			CheckBox kaleBox = new CheckBox();
			Label kaleLabel = new Label("Kale:");
			kaleLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.KALE)) kaleBox.setSelected(true);
			grid.add(kaleLabel, 0, 20);
			grid.add(kaleBox, 1, 20);

			//Celery
			CheckBox celeryBox = new CheckBox();
			Label celeryLabel = new Label("Celery:");
			celeryLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.CELERY)) celeryBox.setSelected(true);
			grid.add(celeryLabel, 2, 20);
			grid.add(celeryBox, 3, 20);

			//Broccoli
			CheckBox broccoliBox = new CheckBox();
			Label broccoliLabel = new Label("Broccoli:");
			broccoliLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.BROCOLLI)) broccoliBox.setSelected(true);
			grid.add(broccoliLabel, 4, 20);
			grid.add(broccoliBox, 5, 20);

			//Carrots
			CheckBox carrotBox = new CheckBox();
			Label carrotLabel = new Label("Carrot:");
			carrotLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.CARROTS)) carrotBox.setSelected(true);
			grid.add(carrotLabel, 0, 21);
			grid.add(carrotBox, 1, 21);

			//Anise
			CheckBox aniseBox = new CheckBox();
			Label aniseLabel = new Label("Anise:");
			aniseLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.ANISE)) aniseBox.setSelected(true);
			grid.add(aniseLabel, 2, 21);
			grid.add(aniseBox, 3, 21);

			//Basil
			CheckBox basilBox = new CheckBox();
			Label basilLabel = new Label("Basil:");
			basilLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.BASIL)) basilBox.setSelected(true);
			grid.add(basilLabel, 4, 21);
			grid.add(basilBox, 5, 21);

			//Chamomile
			CheckBox chamomileBox = new CheckBox();
			Label chamomileLabel = new Label("Chamomile:");
			chamomileLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.CHAMOMILE)) chamomileBox.setSelected(true);
			grid.add(chamomileLabel, 0, 22);
			grid.add(chamomileBox, 1, 22);

			//Dill
			CheckBox dillBox = new CheckBox();
			Label dillLabel = new Label("Dill:");
			dillLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.DILL)) dillBox.setSelected(true);
			grid.add(dillLabel, 2, 22);
			grid.add(dillBox, 3, 22);

			//Oregano
			CheckBox oreganoBox = new CheckBox();
			Label oreganoLabel = new Label("Oregano:");
			oreganoLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.OREGANO)) oreganoBox.setSelected(true);
			grid.add(oreganoLabel, 4, 22);
			grid.add(oreganoBox, 5, 22);

			//Parsley
			CheckBox parsleyBox = new CheckBox();
			Label parsleyLabel = new Label("Parsley:");
			parsleyLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.PARSLEY)) parsleyBox.setSelected(true);
			grid.add(parsleyLabel, 0, 23);
			grid.add(parsleyBox, 1, 23);

			//Rosemary
			CheckBox rosemaryBox = new CheckBox();
			Label rosemaryLabel = new Label("Rosemary:");
			rosemaryLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.ROSEMARY)) rosemaryBox.setSelected(true);
			grid.add(rosemaryLabel, 2, 23);
			grid.add(rosemaryBox, 3, 23);

			//Sage
			CheckBox sageBox = new CheckBox();
			Label sageLabel = new Label("Sage:");
			sageLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.SAGE)) sageBox.setSelected(true);
			grid.add(sageLabel, 4, 23);
			grid.add(sageBox, 5, 23);

			//Lettuce
			CheckBox lettuceBox = new CheckBox();
			Label lettuceLabel = new Label("Lettuce:");
			lettuceLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.LETTUCE)) lettuceBox.setSelected(true);
			grid.add(lettuceLabel, 0, 24);
			grid.add(lettuceBox, 1, 24);

			//Arugula
			CheckBox arugulaBox = new CheckBox();
			Label arugulaLabel = new Label("Arugula:");
			arugulaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.ARUGULA)) arugulaBox.setSelected(true);
			grid.add(arugulaLabel, 2, 24);
			grid.add(arugulaBox, 3, 24);

			//Mushrooms
			CheckBox mushroomBox = new CheckBox();
			Label mushroomLabel = new Label("Mushrooms:");
			mushroomLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.MUSHROOMS)) mushroomBox.setSelected(true);
			grid.add(mushroomLabel, 4, 24);
			grid.add(mushroomBox, 5, 24);

			//Onions
			CheckBox onionBox = new CheckBox();
			Label onionLabel = new Label("Onions:");
			onionLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.ONIONS)) onionBox.setSelected(true);
			grid.add(onionLabel, 0, 25);
			grid.add(onionBox, 1, 25);

			//Garlic
			CheckBox garlicBox = new CheckBox();
			Label garlicLabel = new Label("Garlic:");
			garlicLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.GARLIC)) garlicBox.setSelected(true);
			grid.add(garlicLabel, 2, 25);
			grid.add(garlicBox, 3, 25);

			//Leeks
			CheckBox leekBox = new CheckBox();
			Label leekLabel = new Label("Leeks:");
			leekLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.LEEKS)) leekBox.setSelected(true);
			grid.add(leekLabel, 4, 25);
			grid.add(leekBox, 5, 25);

			//Beets
			CheckBox beetBox = new CheckBox();
			Label beetLabel = new Label("Beets:");
			beetLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.BEETS)) beetBox.setSelected(true);
			grid.add(beetLabel, 0, 26);
			grid.add(beetBox, 1, 26);

			//Jalapeno
			CheckBox jalapenoBox = new CheckBox();
			Label jalapenoLabel = new Label("Jalapeno:");
			jalapenoLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.JALAPENOS)) jalapenoBox.setSelected(true);
			grid.add(jalapenoLabel, 2, 26);
			grid.add(jalapenoBox, 3, 26);

			//Paprika
			CheckBox paprikaBox = new CheckBox();
			Label paprikaLabel = new Label("Paprika:");
			paprikaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.PAPRIKA)) paprikaBox.setSelected(true);
			grid.add(paprikaLabel, 4, 26);
			grid.add(paprikaBox, 5, 26);

			//Tabasco Pepper
			CheckBox tabascoBox = new CheckBox();
			Label tabascoLabel = new Label("Tabasco Pepper:");
			tabascoLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.TABASCOPEPPER)) tabascoBox.setSelected(true);
			grid.add(tabascoLabel, 0, 27);
			grid.add(tabascoBox, 1, 27);

			//Ginger
			CheckBox gingerBox = new CheckBox();
			Label gingerLabel = new Label("Ginger:");
			gingerLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.GINGER)) gingerBox.setSelected(true);
			grid.add(gingerLabel, 2, 27);
			grid.add(gingerBox, 3, 27);

			//Cayenne Pepper
			CheckBox cayenneBox = new CheckBox();
			Label cayenneLabel = new Label("Cayenne Peppers:");
			cayenneLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.CAYENNEPEPPERS)) cayenneBox.setSelected(true);
			grid.add(cayenneLabel, 4, 27);
			grid.add(cayenneBox, 5, 27);

			//Radish
			CheckBox radishBox = new CheckBox();
			Label radishLabel = new Label("Radishes:");
			radishLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.RADISHES)) radishBox.setSelected(true);
			grid.add(radishLabel, 0, 28);
			grid.add(radishBox, 1, 28);

			//Sweet Potatoes
			CheckBox sweetPotatoBox = new CheckBox();
			Label sweetPotatoLabel = new Label("Sweet Potatoes:");
			sweetPotatoLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.SWEETPOTATOES)) sweetPotatoBox.setSelected(true);
			grid.add(sweetPotatoLabel, 2, 28);
			grid.add(sweetPotatoBox, 3, 28);

			//Potatoes
			CheckBox potatoBox = new CheckBox();
			Label potatoLabel = new Label("Potatoes:");
			potatoLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.POTATOES)) potatoBox.setSelected(true);
			grid.add(potatoLabel, 4, 28);
			grid.add(potatoBox, 5, 28);

			//Butternut Squash
			CheckBox butternutSquashBox = new CheckBox();
			Label butternutSquashLabel = new Label("Butternut Squash:");
			butternutSquashLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.BUTTERNUTSQUASH)) butternutSquashBox.setSelected(true);
			grid.add(butternutSquashLabel, 0, 29);
			grid.add(butternutSquashBox, 1, 29);

			//Cucumber
			CheckBox cucumberBox = new CheckBox();
			Label cucumberLabel = new Label("Cucumbers:");
			cucumberLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.CUCUMBERS)) cucumberBox.setSelected(true);
			grid.add(cucumberLabel, 2, 29);
			grid.add(cucumberBox, 3, 29);

			//Black pepper
			CheckBox blackPepperBox = new CheckBox();
			Label blackPepperLabel = new Label("Black Peppers:");
			blackPepperLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.BLACKPEPPER)) blackPepperBox.setSelected(true);
			grid.add(blackPepperLabel, 4, 29);
			grid.add(blackPepperBox, 5, 29);

			//Table salt
			CheckBox tableSaltBox = new CheckBox();
			Label tableSaltLabel = new Label("Table Salt:");
			tableSaltLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.TABLESALT)) tableSaltBox.setSelected(true);
			grid.add(tableSaltLabel, 0, 30);
			grid.add(tableSaltBox, 1, 30);

			//Sea salt
			CheckBox seaSaltBox = new CheckBox();
			Label seaSaltLabel = new Label("Sea Salt:");
			seaSaltLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.SEASALT)) seaSaltBox.setSelected(true);
			grid.add(seaSaltLabel, 2, 30);
			grid.add(seaSaltBox, 3, 30);

			//Nutmeg
			CheckBox nutmegBox = new CheckBox();
			Label nutmegLabel = new Label("Nutmeg:");
			nutmegLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.NUTMEG)) nutmegBox.setSelected(true);
			grid.add(nutmegLabel, 4, 30);
			grid.add(nutmegBox, 5, 30);

			//Rotini Noodles
			CheckBox rotiniBox = new CheckBox();
			Label rotiniLabel = new Label("Rotini Noodles:");
			rotiniLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.ROTININOODLES)) rotiniBox.setSelected(true);
			grid.add(rotiniLabel, 0, 31);
			grid.add(rotiniBox, 1, 31);

			//Spaghetti
			CheckBox spaghettiBox = new CheckBox();
			Label spaghettiLabel = new Label("Spaghetti Noodles:");
			spaghettiLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.SPAGHETTINOODLES)) spaghettiBox.setSelected(true);
			grid.add(spaghettiLabel, 2, 31);
			grid.add(spaghettiBox, 3, 31);

			//Ravioli
			CheckBox ravioliBox = new CheckBox();
			Label ravioliLabel = new Label("Ravioli Noodles:");
			ravioliLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.RAVIOLINOODLES)) ravioliBox.setSelected(true);
			grid.add(ravioliLabel, 4, 31);
			grid.add(ravioliBox, 5, 31);

			//Angel Hair Noodles
			CheckBox angelHairBox = new CheckBox();
			Label angelHairLabel = new Label("Angel Hair Noodles:");
			angelHairLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.ANGELHAIRNOODLES)) angelHairBox.setSelected(true);
			grid.add(angelHairLabel, 0, 32);
			grid.add(angelHairBox, 1, 32);

			//Lasagne
			CheckBox lasagneBox = new CheckBox();
			Label lasagneLabel = new Label("Lasagne:");
			lasagneLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.LASAGNE)) lasagneBox.setSelected(true);
			grid.add(lasagneLabel, 2, 32);
			grid.add(lasagneBox, 3, 32);

			//Cavatappi noodles
			CheckBox cavatappiBox = new CheckBox();
			Label cavatappiLabel = new Label("Cavatappi Noodles:");
			cavatappiLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.CAVATAPPINOODLES)) cavatappiBox.setSelected(true);
			grid.add(cavatappiLabel, 4, 32);
			grid.add(cavatappiBox, 5, 32);

			//Rigatoni noodles
			CheckBox rigatoniBox = new CheckBox();
			Label rigatoniLabel = new Label("Rigatoni Noodles:");
			rigatoniLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.RIGATONINOODLES)) rigatoniBox.setSelected(true);
			grid.add(rigatoniLabel, 0, 33);
			grid.add(rigatoniBox, 1, 33);

			//Linguine noodles
			CheckBox linguineBox = new CheckBox();
			Label linguineLabel = new Label("Linguine Noodles:");
			linguineLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.LINGUINENOODLES)) linguineBox.setSelected(true);
			grid.add(linguineLabel, 2, 33);
			grid.add(linguineBox, 3, 33);

			//Penne noodles
			CheckBox penneBox = new CheckBox();
			Label penneLabel = new Label("Penne Noodles:");
			penneLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			if (userPantryItems.contains(IPantry.PENNENOODLES)) penneBox.setSelected(true);
			grid.add(penneLabel, 4, 33);
			grid.add(penneBox, 5, 33);


			//Save changes button
			Button saveButton = new Button("Save Changes");
			saveButton.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			saveButton.setMinHeight(80);
			grid.add(saveButton, 2, 34);



			saveButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					try {
						/*--------------------------ALLERGIES---------------------------------------*/
						LinkedList<String> newAllergies = new LinkedList<String>();
						
						if (treeNutBox.isSelected()) {
							newAllergies.add(IAllergy.TREENUTS);
						}
						else {
							if (allergies.contains(IAllergy.TREENUTS)) cs.removeAllergy(username, IAllergy.TREENUTS);
						}
						if (peanutBox.isSelected()) {
							newAllergies.add(IAllergy.PEANUT);
						}
						else {
							if (allergies.contains(IAllergy.PEANUT)) cs.removeAllergy(username, IAllergy.PEANUT);
						}
						if (shellfishBox.isSelected()) {
							newAllergies.add(IAllergy.SHELLFISH);
						}
						else {
							if (allergies.contains(IAllergy.SHELLFISH)) cs.removeAllergy(username, IAllergy.SHELLFISH);
						}
						if (milkBox.isSelected()) {
							newAllergies.add(IAllergy.MILK);
						}
						else {
							if (allergies.contains(IAllergy.MILK)) cs.removeAllergy(username, IAllergy.MILK);
						}
						if (fishBox.isSelected()) {
							newAllergies.add(IAllergy.FISH);
						}
						else {
							if (allergies.contains(IAllergy.FISH)) cs.removeAllergy(username, IAllergy.FISH);
						}
						if (wheatBox.isSelected()) {
							newAllergies.add(IAllergy.WHEAT);
						}
						else {
							if (allergies.contains(IAllergy.WHEAT)) cs.removeAllergy(username, IAllergy.WHEAT);
						}
						if (eggBox.isSelected()) {
							newAllergies.add(IAllergy.EGG);
						}
						else {
							if (allergies.contains(IAllergy.EGG)) cs.removeAllergy(username, IAllergy.EGG);
						}
						if (soyBox.isSelected()) {
							newAllergies.add(IAllergy.SOY);
						}
						else {
							if (allergies.contains(IAllergy.SOY)) cs.removeAllergy(username, IAllergy.SOY);
						}
						if (sesameBox.isSelected()) {
							newAllergies.add(IAllergy.SESAME);
						}
						else {
							if (allergies.contains(IAllergy.SESAME)) cs.removeAllergy(username, IAllergy.SESAME);
						}
						
						cs.addAllergies(username, newAllergies);
						/*-------------------------------------------------------------------------*/
						
						/*----------------------------------PANTRY--------------------------------*/
						
						LinkedList<String> newPantry = new LinkedList<String>();
						
						if (steakBox.isSelected()) {
							newPantry.add(IPantry.STEAK);
						}
						else {
							if (userPantryItems.contains(IPantry.STEAK)) cs.deletePantryItem(username, IPantry.STEAK);
						}
						
						if (beefBox.isSelected()) {
							newPantry.add(IPantry.GROUNDBEEF);
						}
						else {
							if (userPantryItems.contains(IPantry.GROUNDBEEF)) cs.deletePantryItem(username, IPantry.GROUNDBEEF);
						}
						if (turkeyBox.isSelected()) {
							newPantry.add(IPantry.TURKEY);
						}
						else {
							if (userPantryItems.contains(IPantry.TURKEY)) cs.deletePantryItem(username, IPantry.TURKEY);
						}
						if (grndTurkeyBox.isSelected()) {
							newPantry.add(IPantry.GROUNDTURKEY);
						}
						else {
							if (userPantryItems.contains(IPantry.GROUNDTURKEY)) cs.deletePantryItem(username, IPantry.GROUNDTURKEY);
						}
						if (chickenBox.isSelected()) {
							newPantry.add(IPantry.CHICKEN);
						}
						else {
							if (userPantryItems.contains(IPantry.CHICKEN)) cs.deletePantryItem(username, IPantry.CHICKEN);
						}
						if (eggPantryBox.isSelected()) {
							newPantry.add(IPantry.EGGS);
						}
						else {
							if (userPantryItems.contains(IPantry.EGGS)) cs.deletePantryItem(username, IPantry.EGGS);
						}
						if (salmonBox.isSelected()) {
							newPantry.add(IPantry.SALMON);
						}
						else {
							if (userPantryItems.contains(IPantry.SALMON)) cs.deletePantryItem(username, IPantry.SALMON);
						}
						if (tunaBox.isSelected()) {
							newPantry.add(IPantry.TUNA);
						}
						else {
							if (userPantryItems.contains(IPantry.TUNA)) cs.deletePantryItem(username, IPantry.TUNA);
						}
						if (catfishBox.isSelected()) {
							newPantry.add(IPantry.CATFISH);
						}
						else {
							if (userPantryItems.contains(IPantry.CATFISH)) cs.deletePantryItem(username, IPantry.CATFISH);
						}
						if (lobsterBox.isSelected()) {
							newPantry.add(IPantry.LOBSTER);
						}
						else {
							if (userPantryItems.contains(IPantry.LOBSTER)) cs.deletePantryItem(username, IPantry.LOBSTER);
						}
						if (crabBox.isSelected()) {
							newPantry.add(IPantry.CRABLEGS);
						}
						else {
							if (userPantryItems.contains(IPantry.CRABLEGS)) cs.deletePantryItem(username, IPantry.CRABLEGS);
						}
						if (veelBox.isSelected()) {
							newPantry.add(IPantry.VEEL);
						}
						else {
							if (userPantryItems.contains(IPantry.VEEL)) cs.deletePantryItem(username, IPantry.VEEL);
						}
						if (grndChickenBox.isSelected()) {
							newPantry.add(IPantry.GROUNDCHICKEN);
						}
						else {
							if (userPantryItems.contains(IPantry.GROUNDCHICKEN)) cs.deletePantryItem(username, IPantry.GROUNDCHICKEN);
						}
						if (calbotBox.isSelected()) {
							newPantry.add(IPantry.CALBOT);
						}
						else {
							if (userPantryItems.contains(IPantry.CALBOT)) cs.deletePantryItem(username, IPantry.CALBOT);
						}
						if (lambBox.isSelected()) {
							newPantry.add(IPantry.LAMB);
						}
						else {
							if (userPantryItems.contains(IPantry.LAMB)) cs.deletePantryItem(username, IPantry.LAMB);
						}
						if (porkBox.isSelected()) {
							newPantry.add(IPantry.PORK);
						}
						else {
							if (userPantryItems.contains(IPantry.PORK)) cs.deletePantryItem(username, IPantry.PORK);
						}
						if (tofuBox.isSelected()) {
							newPantry.add(IPantry.TOFU);
						}
						else {
							if (userPantryItems.contains(IPantry.TOFU)) cs.deletePantryItem(username, IPantry.TOFU);
						}
						if (deerBox.isSelected()) {
							newPantry.add(IPantry.DEER);
						}
						else {
							if (userPantryItems.contains(IPantry.DEER)) cs.deletePantryItem(username, IPantry.DEER);
						}
						if (elkBox.isSelected()) {
							newPantry.add(IPantry.ELK);
						}
						else {
							if (userPantryItems.contains(IPantry.ELK)) cs.deletePantryItem(username, IPantry.ELK);
						}
						if (ostrichBox.isSelected()) {
							newPantry.add(IPantry.OSTRICH);
						}
						else {
							if (userPantryItems.contains(IPantry.OSTRICH)) cs.deletePantryItem(username, IPantry.OSTRICH);
						}
						if (shellfishPantryBox.isSelected()) {
							newPantry.add(IPantry.SHELLFISH);
						}
						else {
							if (userPantryItems.contains(IPantry.SHELLFISH)) cs.deletePantryItem(username, IPantry.SHELLFISH);
						}
						if (plantBasedMeatBox.isSelected()) {
							newPantry.add(IPantry.PLANTBASEDMEAT);
						}
						else {
							if (userPantryItems.contains(IPantry.PLANTBASEDMEAT))cs.deletePantryItem(username, IPantry.PLANTBASEDMEAT);
						}
						if (tomatoBox.isSelected()) {
							newPantry.add(IPantry.TOMATOES);
						}
						else {
							if (userPantryItems.contains(IPantry.TOMATOES)) cs.deletePantryItem(username, IPantry.TOMATOES);
						}
						if (peaBox.isSelected()) {
							newPantry.add(IPantry.PEAS);
						}
						else {
							if (userPantryItems.contains(IPantry.PEAS)) cs.deletePantryItem(username, IPantry.PEAS);
						}
						if (greenPepperBox.isSelected()) {
							newPantry.add(IPantry.GREENPEPPERS);
						}
						else {
							if (userPantryItems.contains(IPantry.GREENPEPPERS)) cs.deletePantryItem(username, IPantry.GREENPEPPERS);
						}
						if (redPepperBox.isSelected()) {
							newPantry.add(IPantry.REDPEPPERS);
						}
						else {
							if (userPantryItems.contains(IPantry.REDPEPPERS)) cs.deletePantryItem(username, IPantry.REDPEPPERS);
						}
						if (orangePepperBox.isSelected()) {
							newPantry.add(IPantry.ORANGEPEPPERS);
						}
						else {
							if (userPantryItems.contains(IPantry.ORANGEPEPPERS)) cs.deletePantryItem(username, IPantry.ORANGEPEPPERS);
						}
						if (bananaPepperBox.isSelected()) {
							newPantry.add(IPantry.BANANAPEPPERS);
						}
						else {
							if (userPantryItems.contains(IPantry.BANANAPEPPERS)) cs.deletePantryItem(username, IPantry.BANANAPEPPERS);
						}
						if (kidneyBox.isSelected()) {
							newPantry.add(IPantry.KIDNEYBEANS);
						}
						else {
							if (userPantryItems.contains(IPantry.KIDNEYBEANS)) cs.deletePantryItem(username, IPantry.KIDNEYBEANS);
						}
						if (lentilBox.isSelected()) {
							newPantry.add(IPantry.LENTILBEANS);
						}
						else {
							if (userPantryItems.contains(IPantry.LENTILBEANS)) cs.deletePantryItem(username, IPantry.LENTILBEANS);
						}
						if (blackBeanBox.isSelected()) {
							newPantry.add(IPantry.BLACKBEANS);
						}
						else {
							if (userPantryItems.contains(IPantry.BLACKBEANS)) cs.deletePantryItem(username, IPantry.BLACKBEANS);
						}
						if (chickPeaBox.isSelected()) {
							newPantry.add(IPantry.CHICKPEAS);
						}
						else {
							if (userPantryItems.contains(IPantry.CHICKPEAS)) cs.deletePantryItem(username, IPantry.CHICKPEAS);
						}
						if (pintoBeanBox.isSelected()) {
							newPantry.add(IPantry.PINTOBEANS);
						}
						else {
							if (userPantryItems.contains(IPantry.PINTOBEANS)) cs.deletePantryItem(username, IPantry.PINTOBEANS);
						}
						if (cabbageBox.isSelected()) {
							newPantry.add(IPantry.CABBAGE);
						}
						else {
							if (userPantryItems.contains(IPantry.CABBAGE)) cs.deletePantryItem(username, IPantry.CABBAGE);
						}
						if (cauliflowerBox.isSelected()) {
							newPantry.add(IPantry.CAULIFLOWER);
						}
						else {
							if (userPantryItems.contains(IPantry.CAULIFLOWER)) cs.deletePantryItem(username, IPantry.CAULIFLOWER);
						}
						if (spinachBox.isSelected()) {
							newPantry.add(IPantry.SPINACH);
						}
						else {
							if (userPantryItems.contains(IPantry.SPINACH)) cs.deletePantryItem(username, IPantry.SPINACH);
						}
						if (kaleBox.isSelected()) {
							newPantry.add(IPantry.KALE);
						}
						else {
							if (userPantryItems.contains(IPantry.KALE)) cs.deletePantryItem(username, IPantry.KALE);
						}
						if (celeryBox.isSelected()) {
							newPantry.add(IPantry.CELERY);
						}
						else {
							if (userPantryItems.contains(IPantry.CELERY)) cs.deletePantryItem(username, IPantry.CELERY);
						}
						if (broccoliBox.isSelected()) {
							newPantry.add(IPantry.BROCOLLI);
						}
						else {
							if (userPantryItems.contains(IPantry.BROCOLLI)) cs.deletePantryItem(username, IPantry.BROCOLLI);
						}
						if (carrotBox.isSelected()) {
							newPantry.add(IPantry.CARROTS);
						}
						else {
							if (userPantryItems.contains(IPantry.CARROTS)) cs.deletePantryItem(username, IPantry.CARROTS);
						}
						if (aniseBox.isSelected()) {
							newPantry.add(IPantry.ANISE);
						}
						else {
							if (userPantryItems.contains(IPantry.ANISE)) cs.deletePantryItem(username, IPantry.ANISE);
						}
						if (basilBox.isSelected()) {
							newPantry.add(IPantry.BASIL);
						}
						else {
							if (userPantryItems.contains(IPantry.BASIL)) cs.deletePantryItem(username, IPantry.BASIL);
						}
						if (chamomileBox.isSelected()) {
							newPantry.add(IPantry.CHAMOMILE);
						}
						else {
							if (userPantryItems.contains(IPantry.CHAMOMILE)) cs.deletePantryItem(username, IPantry.CHAMOMILE);
						}
						if (dillBox.isSelected()) {
							newPantry.add(IPantry.DILL);
						}
						else {
							if (userPantryItems.contains(IPantry.DILL)) cs.deletePantryItem(username, IPantry.DILL);
						}
						if (oreganoBox.isSelected()) {
							newPantry.add(IPantry.OREGANO);
						}
						else {
							if (userPantryItems.contains(IPantry.OREGANO)) cs.deletePantryItem(username, IPantry.OREGANO);
						}
						if (parsleyBox.isSelected()) {
							newPantry.add(IPantry.PARSLEY);
						}
						else {
							if (userPantryItems.contains(IPantry.PARSLEY)) cs.deletePantryItem(username, IPantry.PARSLEY);
						}
						if (rosemaryBox.isSelected()) {
							newPantry.add(IPantry.ROSEMARY);
						}
						else {
							if (userPantryItems.contains(IPantry.ROSEMARY)) cs.deletePantryItem(username, IPantry.ROSEMARY);
						}
						if (sageBox.isSelected()) {
							newPantry.add(IPantry.SAGE);
						}
						else {
							if (userPantryItems.contains(IPantry.SAGE)) cs.deletePantryItem(username, IPantry.SAGE);
						}
						if (lettuceBox.isSelected()) {
							newPantry.add(IPantry.LETTUCE);
						}
						else {
							if (userPantryItems.contains(IPantry.LETTUCE)) cs.deletePantryItem(username, IPantry.LETTUCE);
						}
						if (arugulaBox.isSelected()) {
							newPantry.add(IPantry.ARUGULA);
						}
						else {
							if (userPantryItems.contains(IPantry.ARUGULA)) cs.deletePantryItem(username, IPantry.ARUGULA);
						}
						if (mushroomBox.isSelected()) {
							newPantry.add(IPantry.MUSHROOMS);
						}
						else {
							if (userPantryItems.contains(IPantry.MUSHROOMS)) cs.deletePantryItem(username, IPantry.MUSHROOMS);
						}
						if (onionBox.isSelected()) {
							newPantry.add(IPantry.ONIONS);
						}
						else {
							if (userPantryItems.contains(IPantry.ONIONS)) cs.deletePantryItem(username, IPantry.ONIONS);
						}
						if (garlicBox.isSelected()) {
							newPantry.add(IPantry.GARLIC);
						}
						else {
							if (userPantryItems.contains(IPantry.GARLIC)) cs.deletePantryItem(username, IPantry.GARLIC);
						}
						if (leekBox.isSelected()) {
							newPantry.add(IPantry.LEEKS);
						}
						else {
							if (userPantryItems.contains(IPantry.LEEKS)) cs.deletePantryItem(username, IPantry.LEEKS);
						}
						if (beetBox.isSelected()) {
							newPantry.add(IPantry.BEETS);
						}
						else {
							if (userPantryItems.contains(IPantry.BEETS)) cs.deletePantryItem(username, IPantry.BEETS);
						}
						if (jalapenoBox.isSelected()) {
							newPantry.add(IPantry.JALAPENOS);
						}
						else {
							if (userPantryItems.contains(IPantry.JALAPENOS)) cs.deletePantryItem(username, IPantry.JALAPENOS);
						}
						if (paprikaBox.isSelected()) {
							newPantry.add(IPantry.PAPRIKA);
						}
						else {
							if (userPantryItems.contains(IPantry.PAPRIKA)) cs.deletePantryItem(username, IPantry.PAPRIKA);
						}
						if (tabascoBox.isSelected()) {
							newPantry.add(IPantry.TABASCOPEPPER);
						}
						else {
							if (userPantryItems.contains(IPantry.TABASCOPEPPER)) cs.deletePantryItem(username, IPantry.TABASCOPEPPER);
						}
						if (gingerBox.isSelected()) {
							newPantry.add(IPantry.GINGER);
						}
						else {
							if (userPantryItems.contains(IPantry.GINGER)) cs.deletePantryItem(username, IPantry.GINGER);
						}
						if (cayenneBox.isSelected()) {
							newPantry.add(IPantry.CAYENNEPEPPERS);
						}
						else {
							if (userPantryItems.contains(IPantry.CAYENNEPEPPERS)) cs.deletePantryItem(username, IPantry.CAYENNEPEPPERS);
						}
						if (radishBox.isSelected()) {
							newPantry.add(IPantry.RADISHES);
						}
						else {
							if (userPantryItems.contains(IPantry.RADISHES)) cs.deletePantryItem(username, IPantry.RADISHES);
						}
						if (sweetPotatoBox.isSelected()) {
							newPantry.add(IPantry.SWEETPOTATOES);
						}
						else {
							if (userPantryItems.contains(IPantry.SWEETPOTATOES)) cs.deletePantryItem(username, IPantry.SWEETPOTATOES);
						}
						if (potatoBox.isSelected()) {
							newPantry.add(IPantry.POTATOES);
						}
						else {
							if (userPantryItems.contains(IPantry.POTATOES)) cs.deletePantryItem(username, IPantry.POTATOES);
						}
						if (butternutSquashBox.isSelected()) {
							newPantry.add(IPantry.BUTTERNUTSQUASH);
						}
						else {
							if (userPantryItems.contains(IPantry.BUTTERNUTSQUASH)) cs.deletePantryItem(username, IPantry.BUTTERNUTSQUASH);
						}
						if (cucumberBox.isSelected()) {
							newPantry.add(IPantry.CUCUMBERS);
						}
						else {
							if (userPantryItems.contains(IPantry.CUCUMBERS)) cs.deletePantryItem(username, IPantry.CUCUMBERS);
						}
						if (blackPepperBox.isSelected()) {
							newPantry.add(IPantry.BLACKPEPPER);
						}
						else {
							if (userPantryItems.contains(IPantry.BLACKPEPPER)) cs.deletePantryItem(username, IPantry.BLACKPEPPER);
						}
						if (tableSaltBox.isSelected()) {
							newPantry.add(IPantry.TABLESALT);
						}
						else {
							if (userPantryItems.contains(IPantry.TABLESALT)) cs.deletePantryItem(username, IPantry.TABLESALT);
						}
						if (seaSaltBox.isSelected()) {
							newPantry.add(IPantry.SEASALT);
						}
						else {
							if (userPantryItems.contains(IPantry.SEASALT)) cs.deletePantryItem(username, IPantry.SEASALT);
						}
						if (nutmegBox.isSelected()) {
							newPantry.add(IPantry.NUTMEG);
						}
						else {
							if (userPantryItems.contains(IPantry.NUTMEG)) cs.deletePantryItem(username, IPantry.NUTMEG);
						}
						if (rotiniBox.isSelected()) {
							newPantry.add(IPantry.ROTININOODLES);
						}
						else {
							if (userPantryItems.contains(IPantry.ROTININOODLES)) cs.deletePantryItem(username, IPantry.ROTININOODLES);
						}
						if (spaghettiBox.isSelected()) {
							newPantry.add(IPantry.SPAGHETTINOODLES);
						}
						else {
							if (userPantryItems.contains(IPantry.SPAGHETTINOODLES)) cs.deletePantryItem(username, IPantry.SPAGHETTINOODLES);
						}
						if (ravioliBox.isSelected()) {
							newPantry.add(IPantry.RAVIOLINOODLES);
						}
						else {
							if (userPantryItems.contains(IPantry.RAVIOLINOODLES)) cs.deletePantryItem(username, IPantry.RAVIOLINOODLES);
						}
						if (angelHairBox.isSelected()) {
							newPantry.add(IPantry.ANGELHAIRNOODLES);
						}
						else {
							if (userPantryItems.contains(IPantry.ANGELHAIRNOODLES)) cs.deletePantryItem(username, IPantry.ANGELHAIRNOODLES);
						}
						if (lasagneBox.isSelected()) {
							newPantry.add(IPantry.LASAGNE);
						}
						else {
							if (userPantryItems.contains(IPantry.LASAGNE)) cs.deletePantryItem(username, IPantry.LASAGNE);
						}
						if (cavatappiBox.isSelected()) {
							newPantry.add(IPantry.CAVATAPPINOODLES);
						}
						else {
							if (userPantryItems.contains(IPantry.CAVATAPPINOODLES)) cs.deletePantryItem(username, IPantry.CAVATAPPINOODLES);
						}
						if (rigatoniBox.isSelected()) {
							newPantry.add(IPantry.RIGATONINOODLES);
						}
						else {
							if (userPantryItems.contains(IPantry.RIGATONINOODLES)) cs.deletePantryItem(username, IPantry.RIGATONINOODLES);
						}
						if (linguineBox.isSelected()) {
							newPantry.add(IPantry.LINGUINENOODLES);
						}
						else {
							if (userPantryItems.contains(IPantry.LINGUINENOODLES)) cs.deletePantryItem(username, IPantry.LINGUINENOODLES);
						}
						if (penneBox.isSelected()) {
							newPantry.add(IPantry.PENNENOODLES);
						}
						else {
							if (userPantryItems.contains(IPantry.PENNENOODLES)) cs.deletePantryItem(username, IPantry.PENNENOODLES);
						}
						
						cs.addPantryItems(username, newPantry);
						
						
					}
					catch (Exception ex) {
						System.out.println(ex);
					}
				}
			});

			//this.setMinWidth(1700);
			this.setContent(grid);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}