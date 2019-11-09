package com.eat.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eat.services.ContactService;
import com.eat.services.IUser;
import com.eat.services.Exceptions.*;

public class UserSettings extends GridPane {

	
	Text scenetitle;
	Label Prefix;
	Label email;
	Label emailTextField;
	Label realName;
	Label nameTextField;
	Label userName;
	TextField userTextField;
	Label pw;
	PasswordField pwBox;
	Button changeUsername;
	Button changePWord;
	
	String EMAIL = "";
	String USERNAME = "";
	String NAME = "";
	
	ContactService cs;
	
    public UserSettings()
    {
    	this.setHgap(10);
    	this.setVgap(10);
        this.setPadding(new Insets(20, 20, 20, 20));
        this.getColumnConstraints().add(new ColumnConstraints(150)); // column 0 is 150 wide
        
        try
		{
        	cs = LaunchStage.getInstance().getContactService();
        	USERNAME = cs.getSelfID();
        	EMAIL = cs.getUser(USERNAME).get((IUser.EMAIL));
        	NAME = cs.getUser(USERNAME).get((IUser.NAME));
		}
        catch(Exception e)
        {
        	LaunchStage.getInstance().ProfilePane();
        }
        scenetitle = new Text("User Information");
        scenetitle.setTextAlignment(TextAlignment.CENTER);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
        this.add(scenetitle, 0, 0, 3, 1);
        
        Prefix = new Label("");
        Prefix.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Prefix.setWrapText(true);
        Prefix.setTextFill(Color.web("red"));
        this.add(Prefix, 0, 1, 1, 2);
        
        //email fields
        email = new Label("Email:");
        email.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(email, 1, 1);
        
        emailTextField = new Label(EMAIL);
        emailTextField.setMinWidth(200);
        emailTextField.setMaxWidth(300);
        emailTextField.setMinHeight(50);
        emailTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(emailTextField, 2, 1);
        
        //realname fields
        realName = new Label("First Name:");
        realName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(realName, 1, 2);
        
        nameTextField = new Label(NAME);
        nameTextField.setMinWidth(200);
        nameTextField.setMaxWidth(300);
        nameTextField.setMinHeight(50);
        nameTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(nameTextField, 2, 2);
        
        //username fields
        userName = new Label("User Name:");
        userName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(userName, 1, 3);
        
        userTextField = new TextField(USERNAME);
        userTextField.setMinWidth(200);
        userTextField.setMaxWidth(300);
        userTextField.setMinHeight(50);
        userTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(userTextField, 2, 3);
        
        changeUsername = new Button("Change");
        changeUsername.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        changeUsername.setMinWidth(145);
        this.add(changeUsername, 3, 3);
        
        //password fields
        pw = new Label("Password:");
        pw.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(pw, 1, 4);
        
        pwBox = new PasswordField();
        pwBox.setMinWidth(200);
        pwBox.setMaxWidth(300);
        pwBox.setMinHeight(50);
        this.add(pwBox, 2, 4);
        
        changePWord = new Button("Change");
        changePWord.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        changePWord.setMinWidth(145);
        this.add(changePWord, 3, 4);
        
        setActions();
    }
    
    public void setActions()
    {
    	/*------------------------------------------------------ACTIONS------------------------------------------------------*/
        changeUsername.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	String username = userTextField.getText();
            	
            	Pattern userPattern = Pattern.compile("[^A-Za-z0-9/-/_]");
                Matcher userMatcher = userPattern.matcher(username);
                boolean specialUsername = userMatcher.find();
            	
                if(username.equals(cs.getSelfID()))
                {
                	Prefix.setText("That is your current username");
            		pwBox.setStyle("-fx-border-color: black");
            		userTextField.setStyle("-fx-border-color: red");
            		pwBox.clear();
            		userTextField.requestFocus();
                }
                else if(username.length() < 1)
            	{
            		Prefix.setText("Enter a valid username");
            		pwBox.setStyle("-fx-border-color: black");
            		userTextField.setStyle("-fx-border-color: red");
            		pwBox.clear();
            		userTextField.requestFocus();
            	}
            	else if(username.length() > 30)
            	{
            		Prefix.setText("Username cannot be more than 30 characters long");
            		pwBox.setStyle("-fx-border-color: black");
            		userTextField.setStyle("-fx-border-color: red");
            		pwBox.clear();
            		userTextField.requestFocus();
            	}
                else if(specialUsername)
            	{
            		Prefix.setText("Username must only contain letters and digits");
            		pwBox.setStyle("-fx-border-color: black");
            		userTextField.setStyle("-fx-border-color: red");
            		pwBox.clear();
            		userTextField.requestFocus();
            	}
                else
                {
                	//CHANGE USERNAME
                	boolean success = false;
                	try
                	{
                		success = true;
                		cs.changeUsername(cs.getSelfID(), username);
                	}
                	catch (Exception ex)
                	{
                		Prefix.setText(ex.getMessage());
                		pwBox.setStyle("-fx-border-color: black");
                		userTextField.setStyle("-fx-border-color: red");
                		pwBox.clear();
                		userTextField.requestFocus();
                		success = false;
                	}
                	if(success)
                	{
                		Prefix.setText("Username successfully changed.");
                		pwBox.setStyle("-fx-border-color: black");
                		userTextField.setStyle("-fx-border-color: black");
                		pwBox.clear();
                	}
                }
                
                
            }
        });
        
        changePWord.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	String password = pwBox.getText();
            	
                if(password.length() < 8)
            	{
            		Prefix.setText("Password must be at least 8 characters long.");
            		pwBox.setStyle("-fx-border-color: red");
            		userTextField.setStyle("-fx-border-color: black");
            		pwBox.clear();
            		pwBox.requestFocus();
            	}
            	else if(password.length() > 30)
            	{
            		Prefix.setText("Password cannot be more than 30 characters long");
            		pwBox.setStyle("-fx-border-color: red");
            		userTextField.setStyle("-fx-border-color: black");
            		pwBox.clear();
            		pwBox.requestFocus();
            	}
            	else //all valid information
            	{
            		boolean success = false;
                	try
                	{
                		//Change Password
                		success = true;
                		cs.forgotPassword(cs.getSelfID(), password);
                	}
                	catch (Exception ex)
                	{
                		Prefix.setText(ex.getMessage());
                		pwBox.setStyle("-fx-border-color: red");
                		userTextField.setStyle("-fx-border-color: black");
                		pwBox.clear();
                		success = false;
                		pwBox.requestFocus();
                	}
                	if(success)
                	{
                		Prefix.setText("Password successfully changed");
                		pwBox.setStyle("-fx-border-color: black");
                		userTextField.setStyle("-fx-border-color: black");
                		pwBox.clear();
                	}
            	}
            	
            }
        });
        
    }
    
}