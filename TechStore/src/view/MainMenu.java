package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;


public class MainMenu {
    User currentUser;
    Stage primaryStage;
    public static double wallet = 0;

    public MainMenu(){}

    public void setWallet(double profit){
        wallet += profit;
    }

    public MainMenu(User currentUser) {
        this.currentUser = currentUser;
    }

    public Scene exec(Stage ps) {
        primaryStage = ps;
        VBox vert = new VBox();
        VBox vert2 = new VBox();


        vert2.setPadding(new Insets(10, 50, 50, 50));


        BorderPane mainPane = new BorderPane();

        vert2.setStyle("-fx-background-image: url('/login-left.png');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 300 800;" +
                "-fx-background-position: center center;");


        mainPane.setLeft(vert2);
        vert.setSpacing(10);
        vert.setAlignment(Pos.CENTER);
        mainPane.setCenter(vert);

        Scene scene = new Scene(mainPane, 900, 520);
        vert2.setSpacing(10);
        //ADMINISTRATOR_MENU
        if (currentUser.getLevel() == 1) {
            ImageView admin = new ImageView("admin.png");
            admin.setFitWidth(150);
            admin.setFitHeight(150);
            final String hover = "-fx-background-color:white; -fx-font-size:15px; -fx-text-fill: #D64045; -fx-background-insets: 0,1,2;";
            final String exitt = "-fx-background-color:#D64045; -fx-font-size:15px; -fx-text-fill:white; -fx-background-insets: 0,1,2;";
            Button createUser = new Button("CREATE USER");
            createUser.setStyle(exitt);
            createUser.setOnMouseEntered(e->createUser.setStyle(hover));
            createUser.setOnMouseExited(e->createUser.setStyle(exitt));
            createUser.setOnAction(e->{
                primaryStage.setScene((new AddUser(this.currentUser)).exec(primaryStage));
            });
            Button createCd = new Button("CREATE CD");
            createCd.setStyle(exitt);
            createCd.setOnMouseEntered(e-> createCd.setStyle(hover));
            createCd.setOnMouseExited(e->createCd.setStyle(exitt));
            createCd.setOnAction(e->{
                primaryStage.setScene((new AddCD(this.currentUser)).exec(primaryStage));
            });
            Button addEmployee = new Button("ADD EMPLOYEE");
            addEmployee.setStyle(exitt);
            addEmployee.setOnMouseEntered(e-> addEmployee.setStyle(hover));
            addEmployee.setOnMouseExited(e-> addEmployee.setStyle(exitt));
            addEmployee.setOnAction(e->{
                primaryStage.setScene((new AddEmployee(this.currentUser)).exec(primaryStage));
            });
            Button editUserr = new Button("EDIT USER");
            editUserr.setStyle(exitt);
            editUserr.setOnMouseEntered(e-> editUserr.setStyle(hover));
            editUserr.setOnMouseExited(e-> editUserr.setStyle(exitt));
            editUserr.setOnAction(e->{
                primaryStage.setScene((new ManageUsers(this.currentUser)).exec(primaryStage));
            });
            Button editCdd = new Button("EDIT CD");
            editCdd.setStyle(exitt);
            editCdd.setOnMouseEntered(e-> editCdd.setStyle(hover));
            editCdd.setOnMouseExited(e-> editCdd.setStyle(exitt));
            editCdd.setOnAction(e->{
                primaryStage.setScene((new EditCd(this.currentUser)).exec(primaryStage));
            });
            Button employeeList = new Button("EMPLOYEE");
            employeeList.setStyle(exitt);
            employeeList.setOnMouseEntered(e-> employeeList.setStyle(hover));
            employeeList.setOnMouseExited(e-> employeeList.setStyle(exitt));
            employeeList.setOnAction(e->{
                primaryStage.setScene((new ManageEmployee(this.currentUser)).exec(primaryStage));
            });

            Button exit = new Button("LOG OUT");
            exit.setStyle(exitt);
            exit.setOnMouseEntered(e-> exit.setStyle(hover));
            exit.setOnMouseExited(e-> exit.setStyle(exitt));
            exit.setOnAction(e -> {
                primaryStage.setScene((new Login()).exec(primaryStage));
            });
            vert2.getChildren().addAll(admin,createUser,createCd,addEmployee,editUserr,editCdd,employeeList,exit);

            HBox TopGraph = new HBox();
            HBox overDiagrams = new HBox();
            VBox centerCont = new VBox();

            AccessCd ac = new AccessCd();
            AccessEmployee au = new AccessEmployee();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList();
            ObservableList<CD> cdList = FXCollections.observableArrayList(ac.getCd());
            ObservableList<Employee> userList = FXCollections.observableArrayList(au.getEmp());

            for(CD x:cdList){
                pieChartData.add(new PieChart.Data(x.getCdName(),x.getCdQuantity()));
            }
            final PieChart chart = new PieChart(pieChartData);

            for(Employee u:userList){
                pieChartData2.add(new PieChart.Data(u.getEmployeeName(), u.getEmployeeSalary()));
            }
            final PieChart chart2 = new PieChart(pieChartData2);

            chart.setMinWidth(300);
            chart.setMaxWidth(300);
            chart.setMaxHeight(300);
            chart.setMinHeight(300);
            chart.setLegendVisible(false);

            chart2.setMinWidth(300);
            chart2.setMaxWidth(300);
            chart2.setMaxHeight(300);
            chart2.setMinHeight(300);
            chart2.setLegendVisible(false);


            overDiagrams.setSpacing(15);
            centerCont.setSpacing(50);
            overDiagrams.setAlignment(Pos.CENTER);

            StackPane stack1 = new StackPane();
            StackPane stack2 = new StackPane();
            StackPane stack3 = new StackPane();

            Rectangle rec1 = new Rectangle();
            rec1.setFill(Color.web("#BD3B6E"));
            rec1.setWidth(180);
            rec1.setHeight(120);

            Rectangle rec2 = new Rectangle();
            rec2.setFill(Color.web("#924785"));
            rec2.setWidth(180);
            rec2.setHeight(120);

            Rectangle rec3 = new Rectangle();
            rec3.setFill(Color.web("#604F86"));
            rec3.setWidth(180);
            rec3.setHeight(120);

            Text balance = new Text();
            balance.setText(String.valueOf(wallet)+"$");
            balance.setFill(Color.WHITE);
            balance.setStyle("-fx-font-size:40px;" +
                    "-fx-font-weight: bold;");
            stack3.getChildren().addAll(rec3,balance);

            Text totQuant = new Text();
            int totalQuantity=0;
            for(CD x:cdList){
                totalQuantity += x.getCdQuantity();
            }
            totQuant.setText(String.valueOf(totalQuantity));
            totQuant.setFill(Color.WHITE);
            totQuant.setStyle("-fx-font-size:40px;" +
                    "-fx-font-weight: bold;");
            stack2.getChildren().addAll(rec2,totQuant);

            Text items = new Text();
            int totalItems=0;
            for(CD x:cdList){
                totalItems++;
            }
            items.setText(String.valueOf(totalItems));
            items.setFill(Color.WHITE);
            items.setStyle("-fx-font-size:40px;" +
                    "-fx-font-weight: bold;");

            stack1.getChildren().addAll(rec1,items);

            TopGraph.getChildren().addAll(chart,chart2);
            overDiagrams.getChildren().addAll(stack1,stack2,stack3);
            centerCont.getChildren().addAll(TopGraph,overDiagrams);
            mainPane.setCenter(centerCont);

        }

        //MANAGER_MENU
        else if (currentUser.getLevel() == 2) {
            final String hover = "-fx-background-color:white; -fx-font-size:15px; -fx-text-fill: #D64045; -fx-background-insets: 0,1,2;";
            final String exitt = "-fx-background-color:#D64045; -fx-font-size:15px; -fx-text-fill:white; -fx-background-insets: 0,1,2;";

            ImageView manager = new ImageView("manager.png");
            manager.setFitWidth(150);
            manager.setFitHeight(150);
            Button createBill = new Button("CREATE BILL");
            createBill.setStyle(exitt);
            createBill.setOnMouseEntered(e-> createBill.setStyle(hover));
            createBill.setOnMouseExited(e-> createBill.setStyle(exitt));

            Button createCd = new Button("CREATE CD");
            createCd.setStyle(exitt);
            createCd.setOnMouseEntered(e-> createCd.setStyle(hover));
            createCd.setOnMouseExited(e-> createCd.setStyle(exitt));

            Button stock = new Button("STOCK");
            stock.setStyle(exitt);
            stock.setOnMouseEntered(e-> stock.setStyle(hover));
            stock.setOnMouseExited(e-> stock.setStyle(exitt));

            Button exit = new Button("LOG OUT");
            exit.setStyle(exitt);
            exit.setOnMouseEntered(e-> exit.setStyle(hover));
            exit.setOnMouseExited(e-> exit.setStyle(exitt));

            vert2.getChildren().addAll(manager,createBill,createCd,stock,exit);
            createBill.setOnAction(e->{
                primaryStage.setScene((new AddBill(this.currentUser)).exec(primaryStage));
            });

            createCd.setOnAction(e->{
                primaryStage.setScene((new AddCD(this.currentUser)).exec(primaryStage));
            });

            exit.setOnAction(e -> {
                primaryStage.setScene((new Login()).exec(primaryStage));
            });

            stock.setOnAction(e->{
                primaryStage.setScene((new ManagerStock(this.currentUser)).exec(primaryStage));
            });


            AccessCd ac = new AccessCd();
            ObservableList<CD> cdList = FXCollections.observableArrayList(ac.getCd());
            int items=0;
            Text alert = new Text();
            alert.setVisible(false);

            for(CD c: cdList) {
               if(c.getCdQuantity()<10){
                   items++;
                   alert.setVisible(true);
               }
            }

            alert.setText("You have "+items+" items that need to moderate! Please check the stock!");
            alert.setFill(Color.RED);
            Text welc = new Text("Welcome " + currentUser.getUser());
            welc.setStyle("-fx-font-size:20px;" +
                    "-fx-font-weight: bold;");
            vert.getChildren().addAll(welc,alert);
        }


        //CASHIER MENU
        else if (currentUser.getLevel() == 3) {
            final String hover = "-fx-background-color:white; -fx-font-size:15px; -fx-text-fill: #D64045; -fx-background-insets: 0,1,2;";
            final String exitt = "-fx-background-color:#D64045; -fx-font-size:15px; -fx-text-fill:white; -fx-background-insets: 0,1,2;";


            ImageView cashier = new ImageView("cashier.png");
            cashier.setFitWidth(150);
            cashier.setFitHeight(150);

            Button createBill = new Button("CREATE BILL");
            createBill.setStyle(exitt);
            createBill.setOnMouseEntered(e-> createBill.setStyle(hover));
            createBill.setOnMouseExited(e-> createBill.setStyle(exitt));

            Button stock = new Button("STOCK");
            stock.setStyle(exitt);
            stock.setOnMouseEntered(e-> stock.setStyle(hover));
            stock.setOnMouseExited(e-> stock.setStyle(exitt));

            Button exit = new Button("LOG OUT");
            exit.setStyle(exitt);
            exit.setOnMouseEntered(e-> exit.setStyle(hover));
            exit.setOnMouseExited(e-> exit.setStyle(exitt));

            stock.setOnAction(e->{
                primaryStage.setScene((new Stock(this.currentUser)).exec(primaryStage));
            });

            createBill.setOnAction(e->{
                primaryStage.setScene((new AddBill(this.currentUser)).exec(primaryStage));
            });

            exit.setOnAction(e -> {
                primaryStage.setScene((new Login()).exec(primaryStage));
            });

            vert2.getChildren().addAll(cashier,createBill,stock,exit);


            Text welc = new Text("Welcome " + currentUser.getUser());
            welc.setStyle("-fx-font-size:20px;" +
                    "-fx-font-weight: bold;");
            vert.getChildren().add(welc);
        }

        ps.setTitle("Menu");
        return scene;
    }

}


