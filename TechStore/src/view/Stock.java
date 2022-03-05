package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AccessCd;
import model.CD;
import model.User;

public class Stock {
    User currentUser;
    public Stock(User cUser) {
        currentUser = cUser;
    }

    public Scene exec(Stage primaryStage) {
        BorderPane mainPane = new BorderPane();
        Scene scene = new Scene(mainPane,700,500);

        VBox content = new VBox();
        content.setSpacing(10);
        content.setAlignment(Pos.CENTER);

        AccessCd au = new AccessCd();
        TableView cdStore = new TableView();
        cdStore.setEditable(true);
        ObservableList<CD> cdList = FXCollections.observableArrayList(au.getCd());

        TableColumn<CD, Integer> CDID = new TableColumn("ID");
        CDID.setCellValueFactory(new PropertyValueFactory<>("cdId"));

        TableColumn<CD, String> CDNAME = new TableColumn("CD NAME");
        CDNAME.setCellValueFactory(new PropertyValueFactory<>("cdName"));

        TableColumn<CD, String> CDAUTHOR = new TableColumn("AUTHOR");
        CDAUTHOR.setCellValueFactory(new PropertyValueFactory<>("cdAuthor"));

        TableColumn<CD, String> CDCATEGORY = new TableColumn("CD CATEGORY");
        CDCATEGORY.setCellValueFactory(new PropertyValueFactory<>("cdCategory"));

        TableColumn<CD, Integer> PUBLISHED = new TableColumn("PUBLISHED");
        PUBLISHED.setCellValueFactory(new PropertyValueFactory<>("cdYear"));

        TableColumn<CD, Integer> QUANTITY = new TableColumn("QUANTITY");
        QUANTITY.setCellValueFactory(new PropertyValueFactory<>("cdQuantity"));

        TableColumn<CD, Double> PRICE = new TableColumn("PRICE");
        PRICE.setCellValueFactory(new PropertyValueFactory<>("cdPrice"));


        cdStore.setItems(cdList);
        cdStore.getColumns().addAll(CDID, CDNAME, CDAUTHOR, CDCATEGORY, PUBLISHED, QUANTITY, PRICE);

        Button goBack = new Button("Return");
        goBack.setOnAction(e->{
            primaryStage.setScene((new MainMenu(this.currentUser)).exec(primaryStage));
        });

        goBack.setStyle(" -fx-background-radius: 5;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-insets: 0,1,2;");

        content.getChildren().addAll(cdStore,goBack);
        mainPane.setCenter(content);
        primaryStage.setTitle("STOCK");
        return scene;
    }
}
