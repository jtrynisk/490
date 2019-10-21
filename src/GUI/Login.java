package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Login
{
    private Stage loginStage;
    private Scene root;
    private String username, password;

    public void run(Stage loginStage)
    {

        this.loginStage = loginStage;
        //Main pane for everything to be added to.
        GridPane mainPane = new GridPane();
        mainPane.setHgap(5);
        mainPane.setVgap(5);

        //Labels for login
        Label userLabel = new Label("Username");
        Label passwordLabel = new Label("Password");

        //Fields for login
        TextField userField = new TextField();
        PasswordField passwordField = new PasswordField();

        //Button for submit and clear
        Button submitButton = new Button("Submit");
        Button clearButton = new Button("Clear");

        //Add the labels and fields
        mainPane.setConstraints(userLabel, 0, 0);
        mainPane.getChildren().add(userLabel);

        mainPane.setConstraints(userField, 1, 0);
        mainPane.getChildren().add(userField);

        mainPane.setConstraints(clearButton, 0, 2);
        mainPane.getChildren().add(clearButton);

        mainPane.setConstraints(passwordLabel, 0, 1);
        mainPane.getChildren().add(passwordLabel);

        mainPane.setConstraints(passwordField, 1, 1);
        mainPane.getChildren().add(passwordField);

        mainPane.setConstraints(submitButton, 1, 2);
        mainPane.getChildren().add(submitButton);

        mainPane.setAlignment(Pos.CENTER);

        //Set the clear button item
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userField.clear();
                passwordField.clear();
            }
        });

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                username = userField.getText();
                password = passwordField.getText();

                Stage mainStage = new Stage();
                MainScreen ms = new MainScreen();
                Scene mainScene = ms.getPrimaryStage();

                mainStage.setTitle("Lakeview Lanes Pro Shop");
                mainStage.setScene(mainScene);
                mainStage.show();
                loginStage.close();
            }
        });

        root = new Scene(mainPane, 500, 400);
        loginStage.setTitle("Please login");
        loginStage.setScene(root);
        loginStage.show();

    }

    public Scene getRoot() {
        return root;
    }
}
