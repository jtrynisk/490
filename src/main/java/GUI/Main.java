package GUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Login login = new Login();
        login.run(primaryStage);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
