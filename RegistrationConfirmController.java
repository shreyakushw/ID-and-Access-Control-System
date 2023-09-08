package com.cdac.kms;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegistrationConfirmController {
	
	@FXML
	private Button backToLoginButton;
	
	public void backButtonClick(ActionEvent mousEvent) throws IOException {
		if(mousEvent.getSource()==backToLoginButton) {
			Stage stage = (Stage)backToLoginButton.getScene().getWindow();
			AnchorPane root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/loginPage.fxml"));
			
		//	Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		}
	}

}
