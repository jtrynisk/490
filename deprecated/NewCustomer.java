package GUI;

import Customer.Customer;
import Database.DBconnector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class NewCustomer
{
    private final GridPane rootPane = new GridPane();

    public NewCustomer()
    {
        rootPane.setVgap(5);
        rootPane.setHgap(5);

        //Setup for the labels
        Label firstNameLabel = new Label("First Name: ");
        Label lastNameLabel = new Label("Last Name: ");
        Label addressLabel = new Label("Address: ");
        Label emailLabel = new Label("Email: ");
        Label cityLabel = new Label("City: ");
        Label stateLabel = new Label("State: ");
        Label zipLabel = new Label("Zip Code: ");
        Label phoneLabel = new Label("Phone: ");

        //Setup for the fields
        final TextField firstNameField = new TextField();
        final TextField lastNameField = new TextField();
        TextField addressField = new TextField();
        final TextField emailField = new TextField();
        TextField cityField = new TextField();
        TextField stateField = new TextField();
        TextField zipField = new TextField();
        TextField phoneField = new TextField();

        ArrayList<TextField> fieldCollection = new ArrayList<>();
        fieldCollection.add(firstNameField);
        fieldCollection.add(lastNameField);
        fieldCollection.add(addressField);
        fieldCollection.add(emailField);
        fieldCollection.add(cityField);
        fieldCollection.add(stateField);
        fieldCollection.add(zipField);
        fieldCollection.add(phoneField);

        //Submit and clear button
        Button submitButton = new Button("Submit");
        Button clearButton = new Button("Clear");

        //Add everything to the grid pane now
        rootPane.setConstraints(firstNameLabel, 0, 0);
        rootPane.getChildren().add(firstNameLabel);
        rootPane.setHalignment(firstNameLabel, HPos.CENTER);
        rootPane.setConstraints(firstNameField, 1, 0);
        rootPane.getChildren().add(firstNameField);
        rootPane.setHalignment(firstNameField, HPos.CENTER);
        rootPane.setConstraints(lastNameLabel, 0, 1);
        rootPane.getChildren().add(lastNameLabel);
        rootPane.setHalignment(lastNameLabel, HPos.CENTER);
        rootPane.setConstraints(lastNameField, 1, 1);
        rootPane.getChildren().add(lastNameField);
        rootPane.setHalignment(lastNameField, HPos.CENTER);
        rootPane.setConstraints(addressLabel, 0, 2);
        rootPane.getChildren().add(addressLabel);
        rootPane.setHalignment(addressLabel, HPos.CENTER);
        rootPane.setConstraints(addressField, 1, 2);
        rootPane.getChildren().add(addressField);
        rootPane.setHalignment(addressField, HPos.CENTER);
        rootPane.setConstraints(emailLabel, 0, 3);
        rootPane.getChildren().add(emailLabel);
        rootPane.setHalignment(emailLabel, HPos.CENTER);
        rootPane.setConstraints(emailField, 1, 3);
        rootPane.getChildren().add(emailField);
        rootPane.setHalignment(emailField, HPos.CENTER);
        rootPane.setConstraints(cityLabel, 0, 4);
        rootPane.getChildren().add(cityLabel);
        rootPane.setHalignment(cityLabel, HPos.CENTER);
        rootPane.setConstraints(cityField, 1, 4);
        rootPane.getChildren().add(cityField);
        rootPane.setHalignment(cityField, HPos.CENTER);
        rootPane.setConstraints(stateLabel, 0, 5);
        rootPane.getChildren().add(stateLabel);
        rootPane.setHalignment(stateLabel, HPos.CENTER);
        rootPane.setConstraints(stateField, 1, 5);
        rootPane.getChildren().add(stateField);
        rootPane.setHalignment(stateField, HPos.CENTER);
        rootPane.setConstraints(zipLabel, 0, 6);
        rootPane.getChildren().add(zipLabel);
        rootPane.setHalignment(zipLabel, HPos.CENTER);
        rootPane.setConstraints(zipField, 1, 6);
        rootPane.getChildren().add(zipField);
        rootPane.setHalignment(zipField, HPos.CENTER);
        rootPane.setConstraints(phoneLabel, 0, 7);
        rootPane.getChildren().add(phoneLabel);
        rootPane.setHalignment(phoneLabel, HPos.CENTER);
        rootPane.setConstraints(phoneField, 1, 7);
        rootPane.getChildren().add(phoneField);
        rootPane.setHalignment(phoneField, HPos.CENTER);
        rootPane.setConstraints(submitButton, 0, 8);
        rootPane.getChildren().add(submitButton);
        rootPane.setHalignment(submitButton, HPos.CENTER);
        rootPane.setConstraints(clearButton, 1, 8);
        rootPane.getChildren().add(clearButton);
        rootPane.setHalignment(clearButton, HPos.CENTER);

        //Add the event handlers
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Customer customer = new Customer(firstNameField.getText(), lastNameField.getText(), emailField.getText());
                DBconnector db = new DBconnector();
                db.writeDocument(customer.createDocument());
                db.closeConnection();
            }
        });

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(TextField e: fieldCollection)
                {
                    e.clear();
                }
            }
        });

    }

    public Pane getrootPane()
    {
        return rootPane;
    }
}
