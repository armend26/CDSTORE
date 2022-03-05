package App;

import view.Login;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene((new Login()).exec(primaryStage));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
