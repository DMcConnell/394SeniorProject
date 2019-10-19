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
import javafx.stage.Stage;

public class Login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Eat My Food");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.getColumnConstraints().add(new ColumnConstraints(150)); // column 0 is 150 wide

        Text scenetitle = new Text("Please enter your username and password to log in.");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
        grid.add(scenetitle, 0, 0, 3, 1);
        
        //username fields
        Label userNamePrefix = new Label("");
        userNamePrefix.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(userNamePrefix, 0, 1);

        Label userName = new Label("User Name:");
        userName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(userName, 1, 1);

        TextField userTextField = new TextField();
        userTextField.setMinWidth(200);
        userTextField.setMaxWidth(300);
        userTextField.setMinHeight(50);
        userTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(userTextField, 2, 1);
        
        //password fields
        Label pwPrefix = new Label("");
        pwPrefix.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(pwPrefix, 0, 2);

        Label pw = new Label("Password:");
        pw.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(pw, 1, 2);

        PasswordField pwBox = new PasswordField();
        pwBox.setMinWidth(200);
        pwBox.setMaxWidth(300);
        pwBox.setMinHeight(50);
        grid.add(pwBox, 2, 2);

        //log in and register buttons
        Button LoginBtn = new Button("Log in");
        LoginBtn.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        LoginBtn.setMinWidth(145);
        Button RegBtn = new Button("Register");
        RegBtn.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        RegBtn.setMinWidth(145);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(LoginBtn);
        hbBtn.getChildren().add(RegBtn);
        grid.add(hbBtn, 2, 3);
        
        //forgot password button
        Button ForgotPW = new Button("Forgot your password?");
        ForgotPW.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        ForgotPW.setMinWidth(300);
        HBox hbForgot = new HBox(10);
        hbForgot.setAlignment(Pos.BOTTOM_LEFT);
        hbForgot.getChildren().add(ForgotPW);
        grid.add(hbForgot, 2, 4);

        
        /*------------------------------------------------------ACTIONS------------------------------------------------------*/

        LoginBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //CHECK IF VALID USERNAME (letters and numbers only, min length)
            	// - if not, say incorrect or invalid username and password
            	//CHECK IF VALID PASSWORD (certain characters only, min length)
            	// - if not, say incorrect or invalid username and password
            	//IF USERNAME AND PASSWORD VALID:
            	//	- send login request through services.
            	//	- should return log in or failed.
            	//	- if failed, say incorrect or invalid username and password
            	//	- if correct, use returned session info and go to main page...
            }
        });
        
        RegBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //REDIRECT TO REGISTRATION PAGE
            }
        });
        
        ForgotPW.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //REDIRECT TO FORGOT PASSWORD PAGE
            }
        });

        Scene scene = new Scene(grid, 1920, 1080);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}