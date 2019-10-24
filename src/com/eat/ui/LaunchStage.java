package com.eat.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LaunchStage extends Application {

	
	Login login;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Scene scene = new Scene(login = new Login(), 1920, 1080);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}

	
	public static void main(String[] args) {
		launch(args);
	}

}
