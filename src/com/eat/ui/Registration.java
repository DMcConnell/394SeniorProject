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

public class Registration extends GridPane {

	
	Text scenetitle;
	Label Prefix;
	Label email;
	TextField emailTextField;
	Label userName;
	TextField userTextField;
	Label realName;
	TextField nameTextField;
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
        this.add(Prefix, 0, 1, 1, 2);
        
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
        
        //realname fields
        realName = new Label("First Name:");
        realName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(realName, 1, 3);

        nameTextField = new TextField();
        nameTextField.setMinWidth(200);
        nameTextField.setMaxWidth(300);
        nameTextField.setMinHeight(50);
        nameTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(nameTextField, 2, 3);
        
        //password fields

        pw = new Label("Password:");
        pw.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(pw, 1, 4);

        pwBox = new PasswordField();
        pwBox.setMinWidth(200);
        pwBox.setMaxWidth(300);
        pwBox.setMinHeight(50);
        this.add(pwBox, 2, 4);

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
        this.add(hbBtn, 2, 5);
        
        
        
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
            	String realname = nameTextField.getText();
            	String password = pwBox.getText();
            	
                //CHECK IF VALID USERNAME (letters and numbers only, min length)
            	// - if not, say incorrect or invalid username and password
            	//CHECK IF VALID PASSWORD (certain characters only, min length)
            	// - if not, say incorrect or invalid username and password
            	
            	Pattern emailPattern = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
                Matcher emailMatcher = emailPattern.matcher(email);
                boolean specialEmail = !emailMatcher.matches();
            	
            	Pattern userPattern = Pattern.compile("[^A-Za-z0-9/-/_]");
                Matcher userMatcher = userPattern.matcher(username);
                boolean specialUsername = userMatcher.find();
                
                Matcher nameMatcher = userPattern.matcher(realname);
                boolean specialName = nameMatcher.find();
                
                
               
                if(specialEmail)
                {
                	Prefix.setText("Enter a valid email address");
                	emailTextField.setStyle("-fx-border-color: red");
            		nameTextField.setStyle("-fx-border-color: black");
            		userTextField.setStyle("-fx-border-color: black");
            		pwBox.setStyle("-fx-border-color: black");
            		pwBox.clear();
                }
                else if(username.length() < 1)
            	{
            		Prefix.setText("Enter a valid username");
            		userTextField.setStyle("-fx-border-color: red");
            		nameTextField.setStyle("-fx-border-color: black");
            		pwBox.setStyle("-fx-border-color: black");
                	emailTextField.setStyle("-fx-border-color: black");
            		pwBox.clear();
            	}
            	else if(username.length() > 30)
            	{
            		Prefix.setText("Username cannot be more than 30 characters long");
            		userTextField.setStyle("-fx-border-color: red");
            		nameTextField.setStyle("-fx-border-color: black");
            		pwBox.setStyle("-fx-border-color: black");
                	emailTextField.setStyle("-fx-border-color: black");
            		pwBox.clear();
            	}
            	else if(specialUsername)
            	{
            		Prefix.setText("Username must only contain letters and digits");
            		userTextField.setStyle("-fx-border-color: red");
            		nameTextField.setStyle("-fx-border-color: black");
            		pwBox.setStyle("-fx-border-color: black");
                	emailTextField.setStyle("-fx-border-color: black");
            		pwBox.clear();
            	}
                else if(realname.length() < 1)
            	{
            		Prefix.setText("Enter a valid name");
            		nameTextField.setStyle("-fx-border-color: red");
            		userTextField.setStyle("-fx-border-color: black");
            		pwBox.setStyle("-fx-border-color: black");
                	emailTextField.setStyle("-fx-border-color: black");
            		pwBox.clear();
            	}
            	else if(realname.length() > 30)
            	{
            		Prefix.setText("Name cannot be more than 30 characters long");
            		nameTextField.setStyle("-fx-border-color: red");
            		userTextField.setStyle("-fx-border-color: black");
            		pwBox.setStyle("-fx-border-color: black");
                	emailTextField.setStyle("-fx-border-color: black");
            		pwBox.clear();
            	}
            	else if(specialName)
            	{
            		Prefix.setText("Name must only contain letters and digits");
            		nameTextField.setStyle("-fx-border-color: red");
            		userTextField.setStyle("-fx-border-color: black");
            		pwBox.setStyle("-fx-border-color: black");
                	emailTextField.setStyle("-fx-border-color: black");
            		pwBox.clear();
            	}
            	else if(password.length() < 8)
            	{
            		Prefix.setText("Password must be at least 8 characters long.");
            		pwBox.setStyle("-fx-border-color: red");
            		userTextField.setStyle("-fx-border-color: black");
            		nameTextField.setStyle("-fx-border-color: black");
                	emailTextField.setStyle("-fx-border-color: black");
            		pwBox.clear();
            	}
            	else if(password.length() > 30)
            	{
            		Prefix.setText("Password cannot be more than 30 characters long");
            		pwBox.setStyle("-fx-border-color: red");
            		userTextField.setStyle("-fx-border-color: black");
            		nameTextField.setStyle("-fx-border-color: black");
                	emailTextField.setStyle("-fx-border-color: black");
            		pwBox.clear();
            	}
            	else //all valid information
            	{
            		boolean success = false;
                	try
                	{
                		//need an instance of contact service to call
                		LaunchStage.getInstance().getContactService().registerUser(username, password, realname, email);
                		
                		//TEMP_TEST_REGISTER(username, password, email);
                		success = true;
                	}
                	catch (Exception ex)
                	{
                		Prefix.setText(ex.getMessage());
                		userTextField.setStyle("-fx-border-color: black");
                		nameTextField.setStyle("-fx-border-color: black");
                		pwBox.setStyle("-fx-border-color: black");
                    	emailTextField.setStyle("-fx-border-color: red");
                		pwBox.clear();
                	}
                	if(success)
                	{
                		//open profile page, where profile will retrieve the proper information needed.
                		LaunchStage.getInstance().MainScene();
                	}
            	}
            	
            }
        });
        
    }
    
    
    public void TEMP_TEST_REGISTER(String u, String p, String email) throws Exception
    {
    	if(email.equals("testuser@email.com"))
    		throw new EmailTakenException();
    	if(u.equals("testuser"))
    		throw new UsernameTakenException();
    }
        


}