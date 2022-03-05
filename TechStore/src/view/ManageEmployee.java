package view;



import javafx.scene.layout.HBox;
import model.AccessEmployee;
import model.User;
import model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageEmployee {

    private User currentUser;

    public ManageEmployee(User currentUser) {
        this.currentUser = currentUser;
    }

    public Scene exec(Stage primaryStage) {
        AccessEmployee au = new AccessEmployee();

        ObservableList<Employee> userList = FXCollections.observableArrayList(au.getEmp());

        TableView userTable = new TableView();

        TableColumn<Employee, String> uNameC = new TableColumn("NAME");
        uNameC.setCellValueFactory(new PropertyValueFactory<>("employeeName"));

        TableColumn<Employee, String> sNameC = new TableColumn<>("SURNAME");
        sNameC.setCellValueFactory(new PropertyValueFactory<>("employeeSurname"));

        TableColumn<Employee, String> sSalaryc = new TableColumn<>("SALARY");
        sSalaryc.setCellValueFactory(new PropertyValueFactory<>("employeeSalary"));


        userTable.setItems(userList);
        userTable.getColumns().addAll(uNameC,sNameC,sSalaryc);

        HBox buttons = new HBox();
        Button cancel = new Button("Cancel");

        buttons.getChildren().addAll(cancel);
        buttons.setSpacing(5);
        buttons.setAlignment(Pos.CENTER);

        cancel.setStyle(" -fx-background-radius: 5;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-insets: 0,1,2;");


        cancel.setOnAction(e -> {
            primaryStage.setScene((new MainMenu(this.currentUser)).exec(primaryStage));
        });

        VBox vb = new VBox();
        vb.setSpacing(10);
        vb.getChildren().addAll(userTable, buttons);
        vb.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vb);

        primaryStage.setTitle("List of Employees");

        return scene;
    }

}
