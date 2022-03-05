package view;


import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.text.TextAlignment;
import model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class AddEmployee {

    User currentUser;

    public AddEmployee(User cUser) {
        currentUser = cUser;
    }

    public Scene exec(Stage primaryStage) {

        BorderPane mainPane = new BorderPane();
        Button reg = new Button("ADD");
        Button canc = new Button("CANCEL");
        VBox dialog = new VBox();
        HBox hbutton = new HBox();
        Scene scene = new Scene(mainPane,350,350);

        reg.setStyle(" -fx-background-radius: 5;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-insets: 0,1,2;");
        canc.setStyle(" -fx-background-radius: 5;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-insets: 0,1,2;");

        HBox nameGroup = new HBox();
        nameGroup.setAlignment(Pos.CENTER);
        nameGroup.setSpacing(5);
        Text empName = new Text("NAME :");
        empName.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        empName.setFill(Color.WHITE);
        TextField empField = new TextField();
        nameGroup.getChildren().addAll(empName,empField);


        HBox cdCatGroup = new HBox();
        cdCatGroup.setAlignment(Pos.CENTER);
        cdCatGroup.setSpacing(5);
        Text empSurname = new Text("SURNAME :");
        empSurname.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        empSurname.setFill(Color.WHITE);
        TextField surnameField = new TextField();
        cdCatGroup.getChildren().addAll(empSurname,surnameField);

        HBox cdYearGroup = new HBox();
        cdYearGroup.setAlignment(Pos.CENTER);
        cdYearGroup.setSpacing(5);
        Text empSalary = new Text("SALARY:");
        empSalary.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        empSalary.setFill(Color.WHITE);
        TextField salaryField = new TextField();
        cdYearGroup.getChildren().addAll(empSalary,salaryField);



        Text successfulUser = new Text("EMPLOYEE created!");
        successfulUser.setStroke(Color.GREEN);
        successfulUser.setVisible(false);


        canc.setOnAction(e -> {
            primaryStage.setScene((new MainMenu(this.currentUser)).exec(primaryStage));
        });

        reg.setOnAction(e -> {

            String empNamee = empField.getText();
            String empSurnamee = surnameField.getText();
            double salary = Double.parseDouble(salaryField.getText());

            AccessEmployee userFile = new AccessEmployee();
            userFile.addEmp(new Employee(empNamee,empSurnamee,salary));
            successfulUser.setVisible(true);
        });

        hbutton.getChildren().addAll(reg,canc);
        hbutton.setAlignment(Pos.CENTER);
        hbutton.setSpacing(5);
        dialog.setSpacing(10);
        dialog.setAlignment(Pos.CENTER);
        dialog.getChildren().addAll(nameGroup,cdCatGroup,cdYearGroup,hbutton,successfulUser);

        primaryStage.setTitle("Add CD");
        mainPane.setStyle("-fx-background-image: url('/login-left.png');");
        mainPane.setCenter(dialog);
        successfulUser.setTextAlignment(TextAlignment.CENTER);
        return scene;

    }
}




