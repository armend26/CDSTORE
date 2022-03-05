package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.AccessCd;
import model.CD;
import model.User;

import java.io.*;

public class AddBill {
    User currentUser;
    public AddBill(User cUser) {
        currentUser = cUser;
    }

    public Scene exec(Stage primaryStage){
        File customers = new File("customers.txt");
        BorderPane mainPane = new BorderPane();
        Scene scene = new Scene(mainPane,400,300);

        VBox Center = new VBox();
        Center.setAlignment(Pos.CENTER);
        Center.setSpacing(5);

        HBox CustomerNameGroup = new HBox();
        CustomerNameGroup.setAlignment(Pos.CENTER);
        CustomerNameGroup.setSpacing(5);
        Text CustomerName = new Text("NAME : ");
        CustomerName.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        CustomerName.setFill(Color.WHITE);
        TextField CustomerNameField = new TextField();
        CustomerNameGroup.getChildren().addAll(CustomerName,CustomerNameField);

        HBox CustomerSurnameGroup = new HBox();
        CustomerSurnameGroup.setAlignment(Pos.CENTER);
        CustomerSurnameGroup.setSpacing(5);
        Text CustomerSurname = new Text("SURNAME : ");
        CustomerSurname.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        CustomerSurname.setFill(Color.WHITE);
        TextField CustomerSurnameField = new TextField();
        CustomerSurnameGroup.getChildren().addAll(CustomerSurname,CustomerSurnameField);

        HBox cdQuantityGroup = new HBox();
        cdQuantityGroup.setAlignment(Pos.CENTER);
        cdQuantityGroup.setSpacing(5);
        Text cdQuant = new Text("Quantity :");
        cdQuant.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        cdQuant.setFill(Color.WHITE);
        TextField cdQuantField = new TextField();
        cdQuantityGroup.getChildren().addAll(cdQuant,cdQuantField);

        HBox productGroup = new HBox();
        productGroup.setAlignment(Pos.CENTER);
        productGroup.setSpacing(5);
        Text prd = new Text("PRODUCT ID:");
        prd.setStyle("-fx-font-size:15px;" +
                "-fx-font-weight: bold;");
        prd.setFill(Color.WHITE);
        TextField prdField = new TextField();
        productGroup.getChildren().addAll(prd,prdField);

        HBox Buttons = new HBox();
        Button create = new Button("Create");
        Buttons.setAlignment(Pos.CENTER);
        Buttons.setSpacing(10);
        Button cancel = new Button("Cancel");

        Buttons.getChildren().addAll(create,cancel);

        Text created = new Text("Bill created!");
        Text notCreated = new Text("Something isn't right");
        created.setFill(Color.GREEN);
        notCreated.setFill(Color.RED);
        created.setTextAlignment(TextAlignment.CENTER);
        notCreated.setTextAlignment(TextAlignment.CENTER);
        created.setVisible(false);
        notCreated.setVisible(false);

        cancel.setOnAction(e->{
            primaryStage.setScene((new MainMenu(this.currentUser)).exec(primaryStage));
        });

        create.setOnAction(e->{
            AccessCd ac = new AccessCd();
            ObservableList<CD> cdList = FXCollections.observableArrayList(ac.getCd());
            boolean found = false;
            String name = CustomerNameField.getText();
            String surname = CustomerSurnameField.getText();
            int quant = Integer.parseInt(cdQuantField.getText());
            int productId = Integer.parseInt(prdField.getText());

            double toPay = 0;
            double productPrice = 0;

            MainMenu mn = new MainMenu();

            for(CD c :cdList){
                if(c.getCdId()==productId && c.getCdQuantity()>=quant){
                    int newQuant; 
                    found = true;
                    productPrice = c.getCdPrice();
                    toPay = c.getCdPrice()*quant;
                    mn.setWallet(toPay);
                    newQuant = c.getCdQuantity()-quant; 
                    ac.editCdQuant(newQuant,c);
                }} 
            if(found){
                try{
                    created.setVisible(true);
                    File file = new File(name+surname+"-Bill"+".txt");
                    FileWriter writer = new FileWriter(file);
                    writer.write("\nPRODUCT ID ->"+productId);
                    writer.write("CUSTOMER ->"+name+surname);
                    writer.write("\nORDERED ->"+quant+" pieces");
                    writer.write("\nPRODUCT PRICE ->"+productPrice+"$");
                    writer.write("\nTO PAY ->"+toPay+"$");
                    writer.write("\nTHANK YOU FOR TRUSTING US");
                    writer.close();
                    FileWriter writerCustomer = new FileWriter(customers,true);
                    writerCustomer.write(name + " " + surname + "\n");
                    writerCustomer.close();
                }catch (IOException es){
                    es.printStackTrace();
                }} 
            else{
                notCreated.setVisible(true);
                System.out.println("Something went wrong");
            }
            });
        Center.getChildren().addAll(CustomerNameGroup,CustomerSurnameGroup,cdQuantityGroup,productGroup,Buttons,created,notCreated);
        mainPane.setCenter(Center);
        mainPane.setStyle("-fx-background-image: url('/login-left.png');");
        create.setStyle(" -fx-background-radius: 5;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-insets: 0,1,2;");
        cancel.setStyle(" -fx-background-radius: 5;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-insets: 0,1,2;");
        primaryStage.setTitle("Create Bill");
    return scene;
    }
}