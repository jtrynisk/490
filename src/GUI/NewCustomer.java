package GUI;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class NewCustomer
{
    private final GridPane rootPane;

    public NewCustomer()
    {
        rootPane = new GridPane();

        //Setup for the labels
        Label firstName = new Label("First Name: ");
        Label lastName = new Label("Last Name: ");
    }
}
