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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PortfolioController {
	
	@FXML
	private Label firstnameLabel, lastnameLabel, birthdayLabel, genderLabel, contactLabel;
	@FXML
	private Button quitButton;
	@FXML
	private Hyperlink home;
	@FXML 
	AnchorPane root;
	@FXML
	Stage stage;
	
	public void clickButton(ActionEvent mousEvent) throws Exception{
		if(mousEvent.getSource() == quitButton) {
		//	Platform.exit();
			Stage stage = (Stage) quitButton.getScene().getWindow();
			stage.close();
		}
		
		if(mousEvent.getSource() == home) {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/HomePage.fxml"));
        	root = loader.load();
        	
        	stage = (Stage) home.getScene().getWindow();
	   		
			stage.setScene(new Scene(root));
		 	stage.setTitle("Virtual Space Organiser");
		 	stage.show();
        	
        	
		}
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
				String Gender = rs.getString("Gender");
				String birthday = rs.getString("date_of_birth");
				String contact = rs.getString("contact_no");
				firstnameLabel.setText(" "+ Firstname);
				lastnameLabel.setText(" "+ Lastname);
				genderLabel.setText(" "+ Gender);
				birthdayLabel.setText(" "+ birthday.toString());
				contactLabel.setText(" "+ contact.toString());
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
