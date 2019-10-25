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

public class Registration extends GridPane {

	
	Text scenetitle;
	Label Prefix;
	Label email;
	TextField emailTextField;
	Label userName;
	TextField userTextField;
	Label pw;
	PasswordField pwBox;
	Button LoginBtn;
	Button RegBtn;
	
	
    public Registration()
    {
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(20, 20, 20, 20));
        this.getColumnConstraints().add(new ColumnConstraints(150)); // column 0 is 150 wide

        scenetitle = new Text("Please enter your information to register.");
        scenetitle.setTextAlignment(TextAlignment.CENTER);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
        this.add(scenetitle, 0, 0, 3, 1);
        
        Prefix = new Label("");
        Prefix.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Prefix.setWrapText(true);
        Prefix.setTextFill(Color.web("red"));
        this.add(Prefix, 0, 1, 1, 4);
        
        //email fields
        email = new Label("Email:");
        email.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(email, 1, 1);

        emailTextField = new TextField();
        emailTextField.setMinWidth(200);
        emailTextField.setMaxWidth(300);
        emailTextField.setMinHeight(50);
        emailTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(emailTextField, 2, 1);

        //username fields
        userName = new Label("User Name:");
        userName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(userName, 1, 2);

        userTextField = new TextField();
        userTextField.setMinWidth(200);
        userTextField.setMaxWidth(300);
        userTextField.setMinHeight(50);
        userTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(userTextField, 2, 2);
        
        //password fields

        pw = new Label("Password:");
        pw.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(pw, 1, 3);

        pwBox = new PasswordField();
        pwBox.setMinWidth(200);
        pwBox.setMaxWidth(300);
        pwBox.setMinHeight(50);
        this.add(pwBox, 2, 3);

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
        this.add(hbBtn, 2, 4);
        
        
        
        setActions();
    }
    
    public void setActions()
    {
    	/*------------------------------------------------------ACTIONS------------------------------------------------------*/
        LoginBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	
            	//REDIRECT TO LOGIN PAGE
            	LaunchStage.getInstance().LoginPane();
            }
        });
        
        RegBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	String email = emailTextField.getText();
            	String username = userTextField.getText();
            	String password = pwBox.getText();
            	
                //CHECK IF VALID USERNAME (letters and numbers only, min length)
            	// - if not, say incorrect or invalid username and password
            	//CHECK IF VALID PASSWORD (certain characters only, min length)
            	// - if not, say incorrect or invalid username and password
            	
            	Pattern emailPattern = Pattern.compile("^[\\w!#$%&�*+/=?`{|}~^-]+(?:\\.[\\w!#$%&�*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
                Matcher emailMatcher = emailPattern.matcher(email);
                boolean specialEmail = !emailMatcher.matches();
            	
            	Pattern userPattern = Pattern.compile("[^A-Za-z0-9]");
                Matcher userMatcher = userPattern.matcher(username);
                boolean specialUsername = userMatcher.find();
                
                
               
                if(specialEmail)
                {
                	Prefix.setText("Enter a valid email address");
                }
                else if(username.length() < 1)
            	{
            		Prefix.setText("Enter a valid username");
            	}
            	else if(username.length() > 30)
            	{
            		Prefix.setText("Username cannot be more than 30 characters long");
            	}
            	else if(specialUsername)
            	{
            		Prefix.setText("Username must only contain letters and digits");
            	}
            	else if(password.length() < 8)
            	{
            		Prefix.setText("Password must be at least 8 characters long.");
            	}
            	else if(password.length() > 30)
            	{
            		Prefix.setText("Password cannot be more than 30 characters long");
            	}
            	else //all valid information
            	{
            		boolean success = false;
                	try
                	{
                		//com.eat.services.ContactService.login(username, password);
                		TEMP_TEST_REGISTER(username, password, email);
                		success = true;
                	}
                	catch (Exception ex)
                	{
                		Prefix.setText("There is already an account associated with this email address.");
                	}
                	if(success)
                	{
                		//open profile page, where profile will retrieve the proper information needed. perhaps constructor parameter of username/email.
                	}
            	}
            	
            }
        });
        
    }
    
    
    public void TEMP_TEST_REGISTER(String u, String p, String email) throws Exception
    {
    	throw new Exception();
    }
        


}