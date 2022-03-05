package view;


import javafx.scene.control.*;
import model.AccessUsers;
import model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class AddUser {

    User currentUser;

    public AddUser(User cUser) {
        currentUser = cUser;
    }

    public Scene exec(Stage primaryStage) {
        BorderPane mainPane = new BorderPane();

        Button reg = new Button("REGISTER");
        Button canc = new Button("CANCEL");

        reg.setStyle(" -fx-background-radius: 5;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-insets: 0,1,2;");
        canc.setStyle(" -fx-background-radius: 5;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-insets: 0,1,2;");

        VBox dialog = new VBox();
        HBox hbutton = new HBox();
        Scene scene = new Scene(mainPane,500,300);

        HBox nameGroup = new HBox();
        nameGroup.setAlignment(Pos.CENTER);
        nameGroup.setSpacing(5);
        Text username = new Text("Username :");
        username.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        username.setFill(Color.WHITE);
        TextField usernameField = new TextField();
        nameGroup.getChildren().addAll(username,usernameField);

        HBox passGroup = new HBox();
        passGroup.setAlignment(Pos.CENTER);
        passGroup.setSpacing(5);
        Text pass = new Text("Password :");
        pass.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        pass.setFill(Color.WHITE);
        PasswordField passField = new PasswordField();
        passGroup.getChildren().addAll(pass,passField);

        ToggleGroup position = new ToggleGroup();
        RadioButton normal = new RadioButton("Cashier");
        RadioButton editor = new RadioButton("Manager");
        RadioButton admin = new RadioButton("Admin");
        normal.setStyle("-fx-font-size:15px;" +
                        "-fx-font-weight: bold;" +
                "-fx-text-fill: white;");
        editor.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;");
        admin.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;");

        position.getToggles().addAll(normal, editor, admin);
        position.selectToggle(normal);

        Text successfulUser = new Text("User successfuly created!");
        successfulUser.setStroke(Color.GREEN);
        successfulUser.setVisible(false);

        HBox radios = new HBox(normal, editor, admin);
        radios.setMargin(editor, new Insets(0, 10, 0, 10));
        radios.setAlignment(Pos.CENTER);

        canc.setOnAction(e -> {
            primaryStage.setScene((new MainMenu(this.currentUser)).exec(primaryStage));
        });

        reg.setOnAction(e -> {

            String user = usernameField.getText();
            String password = passField.getText();

            int type = 3;
            String posit = ((RadioButton) position.getSelectedToggle()).getText();
            if(posit == "Admin") type = 1;
            else if(posit == "Manager") type = 2;
            else if(posit == "Cashier") type = 3;

            AccessUsers userFile = new AccessUsers();
            userFile.addUser(new User(User.x++,user,password, type));
            successfulUser.setVisible(true);
        });

        hbutton.getChildren().addAll(reg,canc);
        hbutton.setAlignment(Pos.CENTER);
        hbutton.setSpacing(5);
        dialog.setSpacing(10);
        dialog.setAlignment(Pos.CENTER);
        dialog.getChildren().addAll(nameGroup,passGroup,radios,hbutton,successfulUser);

        primaryStage.setTitle("Add new user");
        mainPane.setCenter(dialog);
        mainPane.setStyle("-fx-background-image: url('/login-left.png');");
        return scene;
    }
}





