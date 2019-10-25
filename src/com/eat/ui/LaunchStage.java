package com.eat.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LaunchStage extends Application {

	/*
	 * awkward handling of making this application a singleton controller
	 * have to keep constructor public for Application to call, but am preventing
	 * the constructor being called more than once with synchronized
	**/
	private static LaunchStage instance;
	public static LaunchStage getInstance() { return instance; }
	public LaunchStage(){
	    super();
	    synchronized(LaunchStage.class){
	        if(instance != null) throw new UnsupportedOperationException(
	                getClass()+" is singleton but constructor called more than once");
	        instance = this;
	    }
	}
	
	
	Scene currentScene;
	Scene startScene;
	Scene connectedScene;

	GridPane currentPane;
	
	Login login;
	Registration registration;
	ForgotPassword forgotPassword;
	Profile profile;
	Recipe recipe;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		currentPane = new GridPane();
		currentPane.setAlignment(Pos.CENTER);
		
		
		LoginPane();
		
		instance.startScene = new Scene(instance.currentPane, 1920, 1080);
		instance.currentScene = instance.startScene;
		
		
		primaryStage.setScene(currentScene);
		
		primaryStage.show();
		
	}

	
	public static void main(String[] args) {
		launch(args);
	}

	
	
	protected void LoginPane()
	{
		currentPane.getChildren().clear();
		currentPane.getChildren().add(new Login());
	}
	protected void RegistrationPane()
	{
		currentPane.getChildren().clear();
		currentPane.getChildren().add(new Registration());
	}
	
	protected void ForgotPasswordPane()
	{
		currentPane.getChildren().clear();
		currentPane.getChildren().add(new ForgotPassword());
	}
	
}
