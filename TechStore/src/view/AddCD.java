package view;


import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.text.TextAlignment;
import model.AccessCd;
import model.AccessUsers;
import model.CD;
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

import java.util.Random;


public class AddCD {

    User currentUser;

    public AddCD(User cUser) {
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
        Text cdName = new Text("CD NAME :");
        cdName.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        cdName.setFill(Color.WHITE);
        TextField cdField = new TextField();
        nameGroup.getChildren().addAll(cdName,cdField);


        HBox cdCatGroup = new HBox();
        cdCatGroup.setAlignment(Pos.CENTER);
        cdCatGroup.setSpacing(5);
        Text cdCatText = new Text("CD CATEGORY :");
        cdCatText.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        cdCatText.setFill(Color.WHITE);
        ChoiceBox cdCat = new ChoiceBox(FXCollections.observableArrayList(
                "MUSIC", "GAME", "SOFTWARE","MOVIE")
        );
        cdCatGroup.getChildren().addAll(cdCatText,cdCat);

        HBox cdYearGroup = new HBox();
        cdYearGroup.setAlignment(Pos.CENTER);
        cdYearGroup.setSpacing(5);
        Text cdYear = new Text("PUBLISHED :");
        cdYear.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        cdYear.setFill(Color.WHITE);
        TextField cdYearField = new TextField();
        cdYearGroup.getChildren().addAll(cdYear,cdYearField);

        HBox cdPriceGroup = new HBox();
        cdPriceGroup.setAlignment(Pos.CENTER);
        cdPriceGroup.setSpacing(5);
        Text cdPrice = new Text("PRICE :");
        cdPrice.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        cdPrice.setFill(Color.WHITE);
        TextField cdPriceField = new TextField();
        cdPriceGroup.getChildren().addAll(cdPrice,cdPriceField);

        HBox cdQuantityGroup = new HBox();
        cdQuantityGroup.setAlignment(Pos.CENTER);
        cdQuantityGroup.setSpacing(5);
        Text cdQuant = new Text("Quantity :");
        cdQuant.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        cdQuant.setFill(Color.WHITE);
        TextField cdQuantField = new TextField();
        cdQuantityGroup.getChildren().addAll(cdQuant,cdQuantField);

        HBox AuthorGroup = new HBox();
        AuthorGroup.setAlignment(Pos.CENTER);
        AuthorGroup.setSpacing(5);
        Text Author = new Text("Author:");
        Author.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        Author.setFill(Color.WHITE);
        TextField AuthorField = new TextField();
        AuthorGroup.getChildren().addAll(Author,AuthorField);

        Text successfulUser = new Text("CD created!");
        successfulUser.setStroke(Color.GREEN);
        successfulUser.setVisible(false);


        canc.setOnAction(e -> {
            primaryStage.setScene((new MainMenu(this.currentUser)).exec(primaryStage));
        });

        Random rand = new Random();

        reg.setOnAction(e -> {
            String cd_Name = cdField.getText();
            String cd_Author = AuthorField.getText();
            int cd_Year = Integer.parseInt(cdYearField.getText());
            String cd_Cat = cdCat.getValue().toString();
            double cd_price = Double.parseDouble(cdPriceField.getText());
            int cd_quant = Integer.parseInt(cdQuantField.getText());


            AccessCd userFile = new AccessCd();
            userFile.addCd(new CD(cd_Name,cd_Cat,cd_price,cd_Year,cd_quant,cd_Author,CD.x++));
            successfulUser.setVisible(true);
        });

        hbutton.getChildren().addAll(reg,canc);
        hbutton.setAlignment(Pos.CENTER);
        hbutton.setSpacing(5);
        dialog.setSpacing(10);
        dialog.setAlignment(Pos.CENTER);
        dialog.getChildren().addAll(nameGroup,AuthorGroup,cdCatGroup,cdYearGroup,cdPriceGroup,cdQuantityGroup,hbutton,successfulUser);

        primaryStage.setTitle("Add CD");
        mainPane.setStyle("-fx-background-image: url('/login-left.png');");
        mainPane.setCenter(dialog);
        successfulUser.setTextAlignment(TextAlignment.CENTER);
        return scene;

    }
}




