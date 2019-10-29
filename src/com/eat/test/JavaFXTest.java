package com.eat.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFXTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new Pane());
		primaryStage.setScene(scene);
		primaryStage.show();
		//This was a pain
	}

	public static void main(String[] args) {
		launch(args);
	}

	
}
