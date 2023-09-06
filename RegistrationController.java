package com.cdac.kms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegistrationController {
	
	@FXML
	private Button signupButton;
	@FXML
	private Hyperlink signinLink;
	
	@FXML
	private Label invalidDetail;

	@FXML
	private TextField fnameEntry, lnameEntry, EmailEntry, passwordEntry, contactnoEntry;
	@FXML
	private TextArea addressEntry;
	
	@FXML
	private DatePicker dobEntry;
	
	@FXML
	private ChoiceBox<String> genderEntry;
	
	@FXML
	AnchorPane root;
	@FXML
	Stage stage;
	
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
	
	public void buttonClick(ActionEvent mousEvent) throws IOException {
		if(mousEvent.getSource()==signupButton) {
			
			if(fnameEntry.getText().isEmpty()) {
				fnameEntry.setStyle(String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 2"));
				showAlert(Alert.AlertType.ERROR, stage, "Form Error!", "First name is required");
				return;
			}else {
				fnameEntry.setStyle(successStyle);
			}
			if(EmailEntry.getText().isEmpty()) {
				EmailEntry.setStyle(errorStyle);
				showAlert(Alert.AlertType.ERROR, stage, "Form Error!", "Email is required");
				return;
			}else {
				EmailEntry.setStyle(successStyle);
			}
			if(passwordEntry.getText().isEmpty()) {
				passwordEntry.setStyle(errorStyle);
				showAlert(Alert.AlertType.ERROR, stage, "Form Error!", "Password is required");
				return;
			}else if(passwordEntry.getText().length() > 10) {
				passwordEntry.setStyle(errorStyle);
				showAlert(Alert.AlertType.ERROR, stage, "Form Error!", "Password cannot have more than 10 characters");
				return;
			}else {
				passwordEntry.setStyle(successStyle);
			}
			if(addressEntry.getText().isEmpty()) {
				addressEntry.setStyle(errorStyle);
				showAlert(Alert.AlertType.ERROR, stage, "Form Error!", "Address is required");
				return;
			}else {
				addressEntry.setStyle(successStyle);
			}
			if(contactnoEntry.getText().isEmpty()) {
				contactnoEntry.setStyle(errorStyle);
				showAlert(Alert.AlertType.ERROR, stage, "Form Error!", "Contact no. is required");
				return;
			}else if(contactnoEntry.getText().length() > 10) {
				contactnoEntry.setStyle(errorStyle);
				showAlert(Alert.AlertType.ERROR, stage, "Form Error!", "Contact no. cannot be more than 10 digits");
				return;
			}else {
				contactnoEntry.setStyle(successStyle);
			}
			if(dobEntry.getValue().isAfter(LocalDate.now())) {
				dobEntry.setStyle(errorStyle);
				showAlert(Alert.AlertType.ERROR, stage, "Form Error!", "Incorrect input");
				return;
			}else {
				dobEntry.setStyle(successStyle);
			}
			
			
			String fname = fnameEntry.getText();
			String lname = lnameEntry.getText();
			String email = EmailEntry.getText();
			String password = passwordEntry.getText();
			String address = addressEntry.getText();
			String contact_no = contactnoEntry.getText();
			LocalDate dob = dobEntry.getValue();
			String gender = genderEntry.getValue();
			
			String query = "insert into Customers(Email_id,Password_c,Fname,Lname,date_of_birth,Gender,Address,contact_no) values(?,?,?,?,?,?,?,?);";
			String query2 = "insert into Login_info(Username, Password_l) values(?,?);";
			String query3 = "select Password_c from Customers where Password_c=? ;"; 			
			Connection conn = null;
			Statement stmt = null;
			PreparedStatement ps = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VS_Organizer", "root", "SdF#6N119");
				
				stmt = conn.createStatement();
				ps = conn.prepareStatement(query3);
				ps.setString(1, password);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					passwordEntry.setStyle(errorStyle);
					showAlert(Alert.AlertType.ERROR, stage, "Form Error!", "Try another password");
					return;
				}
				
				ps = conn.prepareStatement(query);
				ps.setString(1,email);
				ps.setString(2, password);
				ps.setString(3,fname);
				ps.setString(4,lname);
				ps.setDate(5, Date.valueOf(dob));
				ps.setString(6,gender);
				ps.setString(7,address);
				ps.setString(8,contact_no);
				
				ps.executeUpdate();
				
				ps = conn.prepareStatement(query2);
				ps.setString(1, email);
				ps.setString(2, password);
				
				ps.executeUpdate();
				
				
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
			
			stage = (Stage) signupButton.getScene().getWindow();
			 root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/RegistrationConfirmationPage.fxml"));
			
			stage.setScene(new Scene(root));
			stage.show();
		}
		if(mousEvent.getSource()==signinLink) {
			stage = (Stage) signinLink.getScene().getWindow();
			 root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/loginPage.fxml"));
			
			stage.setScene(new Scene(root));
			stage.show();
		}
	}
}
