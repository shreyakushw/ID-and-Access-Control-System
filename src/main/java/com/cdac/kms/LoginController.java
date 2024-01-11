package com.cdac.kms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	private Button loginButton, quitButton;
	@FXML
	private Hyperlink signupclick;
	@FXML
	Stage stage;
	@FXML
	AnchorPane root;
	
	@FXML
	private TextField usernameEntry;
	@FXML
	private PasswordField password_lEntry;
	@FXML
	private Label firstNameText, lastNameText;
	
	
	
	private static void showAlert(Alert.AlertType alertType, Stage stage, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(stage);
        alert.show();
    }
	String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 2;");
	String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 2;");
	
	
	
	@FXML
	public void clickButton(ActionEvent mousEvent) throws IOException {
				
		if(mousEvent.getSource()==loginButton) {
			
			if(usernameEntry.getText().isEmpty()) {
				usernameEntry.setStyle(errorStyle);
				showAlert(Alert.AlertType.ERROR, stage, "Form Error!", "Login field is empty");
				return;
			}else {
				usernameEntry.setStyle(successStyle);
			}
			if(password_lEntry.getText().isEmpty()) {
				password_lEntry.setStyle(errorStyle);
				showAlert(Alert.AlertType.ERROR, stage, "Form Error!", "Login field is empty");
				return;
			}else {
				password_lEntry.setStyle(successStyle);
			}
			
						
			String username = usernameEntry.getText();
			String password = password_lEntry.getText();
			
			Connection conn = null;
			ResultSet rs = null;
			PreparedStatement ps = null;
			
			String query = "select * from Login_Info where Username = ? and password_l = ?";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VS_Organizer", "root", "SdF#6N119");
				
				ps = conn.prepareStatement(query);
	            ps.setString(1, username);
	            ps.setString(2, password);
	            rs = ps.executeQuery();
	            if (!rs.next()) {
	            	showAlert(Alert.AlertType.ERROR, stage, "Login Error!", "Incorrect login info");
					return;
	            }else {
	            	
	            	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/HomePage.fxml"));
	            	root = loader.load();
	            	
	            	HomeController homeControl = loader.getController();
	            	homeControl.getDetails(username);
	            	homeControl.setUsername(username);
	            	
	            	
	            	stage = (Stage) loginButton.getScene().getWindow();
	   		
	   			 	stage.setScene(new Scene(root));
	   			 	stage.setTitle("Virtual Space Organiser");
	   			 	stage.show();
	            }
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if (ps != null) {
						ps.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
		}
		if(mousEvent.getSource()==signupclick) {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/RegistrationPage.fxml"));
        	root = loader.load();
	//		root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/RegistrationPage.fxml"));
			
			stage = (Stage) signupclick.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();
		}
		
		if(mousEvent.getSource() == quitButton) {
		//	Platform.exit();
			stage = (Stage) quitButton.getScene().getWindow();
			stage.close();
			
		//	stage.setTitle("Web View");
	    /*    Scene scene = new Scene(new Browser(),750,500, Color.web("#666970"));
	        stage.setScene(scene);
	        scene.getStylesheets().add("style.css");        
	        stage.show();
			*/
		}
	}	
}

//class Browser extends Region {
//	 
//    final WebView browser = new WebView();
//    final WebEngine webEngine = browser.getEngine();
//     
//    public Browser() {
//        //apply the styles
//        getStyleClass().add("browser");
//        // load the web page
//        webEngine.load("index.html");
//        //add the web view to the scene
//        getChildren().add(browser);
// 
//    }
//    private Node createSpacer() {
//        Region spacer = new Region();
//        HBox.setHgrow(spacer, Priority.ALWAYS);
//        return spacer;
//    }
// 
//    @Override protected void layoutChildren() {
//        double w = getWidth();
//        double h = getHeight();
//        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
//    }
// 
//    @Override protected double computePrefWidth(double height) {
//        return 750;
//    }
// 
//    @Override protected double computePrefHeight(double width) {
//        return 500;
//    }
//}
