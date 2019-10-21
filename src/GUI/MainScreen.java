package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainScreen {

    private Scene root;

    public MainScreen()

    {
        //Create the menu bar instances
        MenuBar mainMenuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        //Exit Button
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);

            }
        });

        //New Customer button
        MenuItem customerMenuItem = new MenuItem("New Customer");
        customerMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("New customer stuff");
                NewCustomer ng = new NewCustomer();
                BorderPane customerPane = new BorderPane();
                customerPane.setCenter(ng.getrootPane());
                Scene newCustomerScene = new Scene(customerPane);
                Stage customerWindow = new Stage();
                customerWindow.setTitle("New Customer Information");
                customerWindow.setScene(newCustomerScene);
                customerWindow.show();
            }
        });

        //Search through the customers.
        MenuItem searchMenuItem = new MenuItem("Search Customers");
        searchMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //logic to search through the customers.
                System.out.println("search");
            }
        });


        //Add everything to the menu
        fileMenu.getItems().add(customerMenuItem);
        fileMenu.getItems().add(searchMenuItem);
        fileMenu.getItems().add(exitMenuItem);
        mainMenuBar.getMenus().add(fileMenu);

        //Add stuff to the main pane
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(mainMenuBar);
        mainPane.setCenter(new Label("Please Select a Customer."));

        root = new Scene(mainPane, 1000, 800);

    }

    public Scene getPrimaryStage() {
        return root;
    }
}

