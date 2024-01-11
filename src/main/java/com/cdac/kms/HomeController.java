package com.cdac.kms;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Field;

public class HomeController {
	
	@FXML
	private Hyperlink portfolioPage;
	@FXML
	AnchorPane root;
	@FXML
	Stage stage;
	@FXML
	private Label firstNameText, lastNameText;
	@FXML
	private Button quitButton;
	
	private String username;
	
	
	public void clickButton(ActionEvent mousEvent) throws Exception {
		if(mousEvent.getSource()==portfolioPage) {
				
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/PortfolioPage.fxml"));
            	root = loader.load();
            	
            	PortfolioController portfolioControl = loader.getController();
	            portfolioControl.getDetails(getUsername());
				
				stage = (Stage) portfolioPage.getScene().getWindow();
			//	root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/PortfolioPage.fxml"));
				
				stage.setScene(new Scene(root));
				stage.show();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(mousEvent.getSource() == quitButton) {
		//	Platform.exit();
			stage = (Stage) quitButton.getScene().getWindow();
			stage.close();
		}
		
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void getDetails(String userName) {
		
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		String query = "select* from Customers where Email_id = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VS_Organizer", "root", "SdF#6N119");
			
			stmt = conn.createStatement();
			ps = conn.prepareStatement(query);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String Firstname = rs.getString("Fname");
				String Lastname = rs.getString("Lname");
		//		firstNameText.setText("First name : "+ Firstname);
		//		lastNameText.setText("Last name : "+ Lastname);
		//		firstNameText = new Label(Fname.toString());
		//		lastNameText = new Label(Lname.toString());
				
				System.out.println(Firstname);
				System.out.println(Lastname);
			}
			
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			// We have to close the connection and release the resources used
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
