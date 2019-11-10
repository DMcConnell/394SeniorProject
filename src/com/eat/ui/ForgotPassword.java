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

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eat.services.IUser;
import com.eat.support.Support;

public class ForgotPassword extends GridPane {

	
	Text scenetitle;
	Label Prefix;
	Label email;
	TextField emailTextField;
	Button LoginBtn;
	Button RecoverBtn;
	
	
    public ForgotPassword()
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
        this.add(Prefix, 0, 1, 1, 1);
        
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

       

        //log in and register buttons
        LoginBtn = new Button("Log in");
        LoginBtn.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        LoginBtn.setMinWidth(145);
        RecoverBtn = new Button("Reset Password");
        RecoverBtn.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        RecoverBtn.setMinWidth(145);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(LoginBtn);
        hbBtn.getChildren().add(RecoverBtn);
        this.add(hbBtn, 2, 2);
        
        
        
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
        
        RecoverBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	String email = emailTextField.getText();
            	
            	
            	Pattern emailPattern = Pattern.compile("^[\\w!#$%&�*+/=?`{|}~^-]+(?:\\.[\\w!#$%&�*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
                Matcher emailMatcher = emailPattern.matcher(email);
                boolean specialEmail = !emailMatcher.matches();
            	
                
                
               
                if(specialEmail)
                {
                	Prefix.setText("Enter a valid email address");
                	emailTextField.setStyle("-fx-border-color: red");
                }
            	else //all valid information
            	{
            		boolean success = false;
                	try
                	{
                		//Here need to send email to services, see if a user with this email exists
                		String message = "Here is a a temporary password for you to log in: \n";
                		HashMap<String, String> user = LaunchStage.getInstance().getContactService().getUserFromEmail(email);
                		
                    	//here services needs to create a new random password and set it on the database, and return it to apply here
                    	String tmpPword = Support.generatePassword();
                    	LaunchStage.getInstance().getContactService().forgotPassword(user.get(IUser.USERNAME), tmpPword);
                    	
                		LaunchStage.getInstance().getEmailService().sendEmail(email, "EatMyFood Password Recovery", message + tmpPword);
                		success = true;
                			
                	}
                	catch (Exception ex)
                	{
                		Prefix.setText(ex.getMessage());
                		//ex.printStackTrace();
                    	emailTextField.setStyle("-fx-border-color: black");
                    	success = false;
                	}
                	if(success)
                	{
                		Prefix.setText("An email has been sent to the address.");
                    	emailTextField.setStyle("-fx-border-color: black");
                	}
            	}
            	
            }
        });
        
    }
    
    
    public void TEMP_TEST_RECOVER(String email) throws Exception
    {
    	throw new Exception();
    }
        


}