package ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainMethod extends Application { // DO NOT CHANGE ANYTHIGN IN THIS CLASS, EVERYTHING NEEDS TO BE DONE IN CONTROLLER
	
	//uses 
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("UI.fxml")); //get FMXL file
		//primaryStage.getIcons().add(new Image("@../../../styles/solar-system.png"));
		primaryStage.setTitle("Welcome to Apollo Airlines");
		primaryStage.setScene(new Scene(root)); 
		primaryStage.show();
	}

}
