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

public class SignInController {
    @FXML
    private TextField emailText;
    @FXML
    private TextField passwordInput;
    @FXML
    private Label errorLabel;
    @FXML
    private Button signInButtonSignPage;
    @FXML
    private Button registerButtonSignInPage;

    @FXML
    void initialize() {
        AccessDataViewModel accessDataViewModel = new AccessDataViewModel();
        emailText.textProperty().bindBidirectional(accessDataViewModel.userNameProperty());
        passwordInput.textProperty().bindBidirectional(accessDataViewModel.userMajorProperty());
    }

    @FXML
    private void signInButtonSignPageClicked(ActionEvent event) throws IOException {
        if (emailText.getText().equals("") || passwordInput.getText().equals("")) {
            showError("You are missing fields! Try again!");
        }

        else {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(emailText.getText())
                    .setPassword(passwordInput.getText());

            UserRecord userRecord;
            try {
                userRecord = App.fauth.getUserByEmail(emailText.getText());
                User.getUser().setName(userRecord.getDisplayName());
                App.setRoot("AccessFBView.fxml");
            } catch (FirebaseAuthException ex) {
                showError("Unable to sign in! Make sure your information is correct and try Again!");
                System.out.println(ex);
            }
        }
    }

    @FXML
    private void registerButtonSignInPageClicked(ActionEvent event) throws IOException {
        App.setRoot("register.fxml");
    }

    private void showError(String errorMessage) {
        errorLabel.setText(errorMessage);
    }
}
