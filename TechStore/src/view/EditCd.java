package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import model.AccessCd;
import model.AccessUsers;
import model.CD;
import model.User;

public class EditCd {
    User currentUser;
    public EditCd(User cUser) {
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

        HBox addquant = new HBox();
        addquant.setAlignment(Pos.CENTER);
        Text productID = new Text("Product Id : ");
        TextField productIDField = new TextField();
        Button add = new Button("DELETE");
        addquant.setSpacing(10);


        Text work = new Text("Items deleted.");
        work.setFill(Color.GREEN);
        work.setVisible(false);
        addquant.getChildren().addAll(productID,productIDField,add,work);
        add.setOnAction(e->{
            AccessCd ac = new AccessCd();
            ObservableList<CD> cdList = FXCollections.observableArrayList(ac.getCd());
            int id = Integer.parseInt(productIDField.getText());

            for(CD c :cdList){
                if(c.getCdId()==id){
                    ac.rm(id);
                    work.setVisible(true);
                }}

            primaryStage.setScene((new EditCd(this.currentUser)).exec(primaryStage));
        });

        ObservableList<CD> cdList = FXCollections.observableArrayList(au.getCd());

        cdStore.setEditable(true);
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

        content.getChildren().addAll(addquant,cdStore,goBack);
        mainPane.setCenter(content);
        primaryStage.setTitle("STOCK");
        return scene;
    }
}
