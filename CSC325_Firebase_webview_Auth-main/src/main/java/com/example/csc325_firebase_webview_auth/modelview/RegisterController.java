package com.example.csc325_firebase_webview_auth.modelview;

import com.example.csc325_firebase_webview_auth.App;
import com.example.csc325_firebase_webview_auth.models.User;
import com.example.csc325_firebase_webview_auth.viewmodel.AccessDataViewModel;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField emailText;
    @FXML
    private TextField fullNameText;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField passwordText;
    @FXML
    private Button registerButton;
    @FXML
    private Button signInButton;

    @FXML
    void initialize() {
        AccessDataViewModel accessDataViewModel = new AccessDataViewModel();
        emailText.textProperty().bindBidirectional(accessDataViewModel.userNameProperty());
    }

    @FXML
    private void registerButtonClicked(ActionEvent event) throws IOException {

        if (emailText.getText().equals("") || fullNameText.getText().equals("") || passwordText.getText().equals("")) {
            showError("You are missing fields! Try again!");
        }

        else {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(emailText.getText())
                    .setEmailVerified(false)
                    .setPassword(passwordText.getText())
                    .setDisplayName(fullNameText.getText())
                    .setDisabled(false);

            UserRecord userRecord;
            try {
                userRecord = App.fauth.createUser(request);
                User.getUser().setName(fullNameText.getText());
                System.out.println("Successfully created new user: " + userRecord.getUid());
                App.setRoot("AccessFBView.fxml");
            } catch (FirebaseAuthException ex) {
                showError("Unable to make a new account! Try Again!");
                System.out.println(ex);
            }
        }
    }

    @FXML
    private void signInButtonClicked(ActionEvent event) throws IOException {
        App.setRoot("signin.fxml");
    }

    private void showError(String errorMessage) {
        errorLabel.setText(errorMessage);
    }
}
