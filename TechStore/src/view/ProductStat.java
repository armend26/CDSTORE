package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AccessCd;
import model.CD;
import model.User;

public class ProductStat {

    private User currentUser;

    public ProductStat(User cu) {
        this.currentUser = cu;
    }

    public Scene exec(Stage primaryStage) {
        AccessCd ac = new AccessCd();
        BorderPane mainPane = new BorderPane();
        Scene scene = new Scene(mainPane,500,500);
        VBox cont = new VBox();

        Button back = new Button("Go back");

        back.setOnAction(e->{
            primaryStage.setScene((new MainMenu(this.currentUser)).exec(primaryStage));
        });

        back.setStyle(" -fx-background-radius: 5;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color:#54428E;" +
                "-fx-text-fill: white;" +
                "-fx-background-insets: 0,1,2;");

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        ObservableList<CD> cdList = FXCollections.observableArrayList(ac.getCd());

        for(CD x:cdList){
            pieChartData.add(new PieChart.Data(x.getCdAuthor(), x.getCdPrice()));
        }

        final PieChart chart = new PieChart(pieChartData);

        chart.setTitle("Price of products based on author");

        cont.setSpacing(10);
        cont.setAlignment(Pos.CENTER);
        cont.getChildren().addAll(chart,back);
        mainPane.setCenter(cont);

        return scene;
    }

    }
