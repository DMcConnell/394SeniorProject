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
import com.eat.services.Exceptions.*;

public class Login extends GridPane {

	
	Text scenetitle;
	Label Prefix;
	Label userName;
	TextField userTextField;
	Label pw;
	PasswordField pwBox;
	Button LoginBtn;
	Button RegBtn;
	Button ForgotPW;
	
	
    public Login()
    {
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(20, 20, 20, 20));
        this.getColumnConstraints().add(new ColumnConstraints(150)); // column 0 is 150 wide

        scenetitle = new Text("Please enter your username and password to log in.");
        scenetitle.setTextAlignment(TextAlignment.CENTER);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
        this.add(scenetitle, 0, 0, 3, 1);
        
        Prefix = new Label("");
        Prefix.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Prefix.setWrapText(true);
        Prefix.setTextFill(Color.web("red"));
        this.add(Prefix, 0, 1, 1, 2);

        //username fields
        userName = new Label("User Name:");
        userName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(userName, 1, 1);

        userTextField = new TextField();
        userTextField.setMinWidth(200);
        userTextField.setMaxWidth(300);
        userTextField.setMinHeight(50);
        userTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(userTextField, 2, 1);
        
        //password fields

        pw = new Label("Password:");
        pw.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(pw, 1, 2);

        pwBox = new PasswordField();
        pwBox.setMinWidth(200);
        pwBox.setMaxWidth(300);
        pwBox.setMinHeight(50);
        this.add(pwBox, 2, 2);

        //log in and register buttons
        LoginBtn = new Button("Log in");
        LoginBtn.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        LoginBtn.setMinWidth(145);
        RegBtn = new Button("Register");
        RegBtn.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        RegBtn.setMinWidth(145);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(LoginBtn);
        hbBtn.getChildren().add(RegBtn);
        this.add(hbBtn, 2, 3);
        
        //forgot password button
        ForgotPW = new Button("Forgot your password?");
        ForgotPW.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        ForgotPW.setMinWidth(300);
        HBox hbForgot = new HBox(10);
        hbForgot.setAlignment(Pos.BOTTOM_LEFT);
        hbForgot.getChildren().add(ForgotPW);
        this.add(hbForgot, 2, 4);
        
        
        setActions();
    }
    
    public void setActions()
    {
    	/*------------------------------------------------------ACTIONS------------------------------------------------------*/
        LoginBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	String username = userTextField.getText();
            	String password = pwBox.getText();
            	
                //CHECK IF VALID USERNAME (letters and numbers only, min length)
            	// - if not, say incorrect or invalid username and password
            	//CHECK IF VALID PASSWORD (certain characters only, min length)
            	// - if not, say incorrect or invalid username and password
            	
            	Pattern userPattern = Pattern.compile("[^A-Za-z0-9]");
                Matcher userMatcher = userPattern.matcher(username);
                boolean specialUsername = userMatcher.find();
               
                
            	if(username.length() < 1)
            	{
            		Prefix.setText("Enter a valid username");
            		userTextField.setStyle("-fx-border-color: red");
            		pwBox.setStyle("-fx-border-color: black");
            		pwBox.clear();
            	}
            	else if(username.length() > 30)
            	{
            		Prefix.setText("Username cannot be more than 30 characters long");
            		userTextField.setStyle("-fx-border-color: red");
            		pwBox.setStyle("-fx-border-color: black");
            		pwBox.clear();
            	}
            	else if(specialUsername)
            	{
            		Prefix.setText("Username must only contain letters and digits");
            		userTextField.setStyle("-fx-border-color: red");
            		pwBox.setStyle("-fx-border-color: black");
            		pwBox.clear();
            	}
            	else if(password.length() < 8)
            	{
            		Prefix.setText("Password must be at least 8 characters long.");
            		pwBox.setStyle("-fx-border-color: red");
            		userTextField.setStyle("-fx-border-color: black");
            		pwBox.clear();
            	}
            	else if(password.length() > 30)
            	{
            		Prefix.setText("Password cannot be more than 30 characters long");
            		pwBox.setStyle("-fx-border-color: red");
            		userTextField.setStyle("-fx-border-color: black");
            		pwBox.clear();
            	}
            	else //all valid information
            	{
            		boolean success = false;
                	try
                	{
                		LaunchStage.getInstance().getContactService().login(username, password);
                		//TEMP_TEST_LOGIN(username, password);
                		success = true;
                	}
                	catch (Exception ex)
                	{
                		Prefix.setText(ex.getMessage());
                		userTextField.setStyle("-fx-border-color: red");
                		pwBox.setStyle("-fx-border-color: red");
                		pwBox.clear();
                		success = false;
                	}
                	if(success)
                	{
                		//open profile page, where profile will retrieve the proper information needed.
                		LaunchStage.getInstance().MainScene();
                	}
            	}
            	
            }
        });
        
        RegBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //REDIRECT TO REGISTRATION PAGE
            	LaunchStage.getInstance().RegistrationPane();
            }
        });
        
        ForgotPW.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //REDIRECT TO FORGOT PASSWORD PAGE
            	LaunchStage.getInstance().ForgotPasswordPane();
            }
        });
    }
    
    
    public void TEMP_TEST_LOGIN(String u, String p) throws Exception
    {
    	if(!u.equals("testuser") || !p.equals("password"))
    		throw new InvalidCombinationException();
    }
        


}