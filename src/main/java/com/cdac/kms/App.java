package com.cdac.kms;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/loginPage.fxml"));
	        Scene scene = new Scene(root);
	        
	        primaryStage.initStyle(StageStyle.UNDECORATED);
	        
	  //      String css = this.getClass().getResource("css/style.css").toExternalForm();
	  //      scene.getStylesheets().add(css);
	        scene.getStylesheets().add(getClass().getClassLoader().getResource("css/style.css").toExternalForm());
	        
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Virtual Space Organiser");
	        primaryStage.show();
	    }catch(Exception e) {
	        e.printStackTrace();
	    }		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
