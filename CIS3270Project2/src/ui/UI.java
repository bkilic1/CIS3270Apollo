package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class UI extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		Button button = new Button("Hi There!");
		
		button.setOnAction((e) -> System.out.println("Hello World!"));
		
		StackPane root = new StackPane();
		root.getChildren().add(button);
		Scene scene = new Scene(root, 300, 300);
		primaryStage.setTitle("Test run");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
