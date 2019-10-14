package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Create the menu bar instances
        MenuBar mainMenuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        //Exit Button
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e ->{
            System.exit(0);
        });

        //New Customer button
        MenuItem customerMenuItem = new MenuItem("New Customer");
        customerMenuItem.setOnAction(e -> {
            NewCustomer ng = new NewCustomer();
            BorderPane customerPane = new BorderPane();
            customerPane.setCenter(ng.getrootPane());
            Scene newCustomerScene = new Scene(customerPane);
            Stage customerWindow = new Stage();
            customerWindow.setTitle("New Customer Information");
            customerWindow.setScene(newCustomerScene);
            customerWindow.show();
        });


        //Add everything to the menu
        fileMenu.getItems().add(customerMenuItem);
        fileMenu.getItems().add(exitMenuItem);
        mainMenuBar.getMenus().add(fileMenu);

        //Add stuff to the main pane
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(mainMenuBar);


        Scene root = new Scene(mainPane, 1000, 800);

        primaryStage.setTitle("Lakeview Lanes Pro Shop");
        primaryStage.setScene(root);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
