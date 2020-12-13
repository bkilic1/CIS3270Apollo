package ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMethod extends Application { // DO NOT CHANGE ANYTHIGN IN THIS CLASS, EVERYTHING NEEDS TO BE DONE IN CONTROLLER

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("UI.fxml")); //get FMXL file
		primaryStage.setTitle("Test run");
		primaryStage.setScene(new Scene(root)); 
		primaryStage.show();
	}

}
