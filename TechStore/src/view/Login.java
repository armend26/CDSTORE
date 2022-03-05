package view;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.User;
import model.AccessUsers;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;


public class Login{
	public Scene exec(Stage primaryStage){

		BorderPane mainPane = new BorderPane();
		Scene scene = new Scene(mainPane,800,530);
		VBox vertical = new VBox();
		vertical.setSpacing(100);
		vertical.setAlignment(Pos.CENTER);
		Text Logo = new Text("LOGO");
		Logo.setFill(Color.web("#D64045"));
		Logo.setFont(Font.font("roboto", FontWeight.THIN, FontPosture.REGULAR,48));
		HBox NameBox = new HBox();
		Text usernamee = new Text("username : ");
		usernamee.setFill(Color.web("#D64045"));
		usernamee.setFont(Font.font("roboto", FontWeight.THIN, FontPosture.REGULAR,20));
		TextField nameField = new TextField();
		NameBox.getChildren().addAll(usernamee,nameField);
		NameBox.setAlignment(Pos.CENTER);
		HBox PassBox = new HBox();
		Text passwordd = new Text("password : ");
		passwordd.setFill(Color.web("#D64045"));
		passwordd.setFont(Font.font("roboto", FontWeight.THIN, FontPosture.REGULAR,20));
		PasswordField passField = new PasswordField();
	    PassBox.getChildren().addAll(passwordd,passField);
		PassBox.setAlignment(Pos.CENTER);
		Button login = new Button("LOGIN");
        login.setStyle("-fx-font-size:15px;" +
				"-fx-font-weight: bold;" +
				"-fx-background-color:#D64045;" +
				"-fx-text-fill: white;" +
				"-fx-background-insets: 0,1,2;");
		Text err = new Text("Please try again!");
		err.setStroke(Color.WHITE);
		err.getStyleClass().add("error");
		err.setVisible(false);
		Button cancel = new Button("Cancel");
		cancel.getStyleClass().addAll("cancel","login-but");
		vertical.setAlignment(Pos.CENTER);
		vertical.setSpacing(10);
	    vertical.getChildren().addAll(Logo,NameBox,PassBox,login,err);
		login.setOnAction(e -> {
			String username = nameField.getText();
			String password = passField.getText();
			File file = new File(AccessUsers.filename);
			boolean found = false;
			if(file.exists() && !file.isDirectory())
			{
				AccessUsers fileUsers = new AccessUsers();
				User user = fileUsers.checkUser(username, password);

				if(user != null)
				{
					primaryStage.close();
					primaryStage.setScene((new MainMenu(user)).exec(primaryStage));
					primaryStage.show();
				}
			}
			if(username.equals("admin") && password.equals("admin"))
			{
				User overrideUser = new User(0, "admin", "admin", 1);
				primaryStage.setScene((new MainMenu(overrideUser)).exec(primaryStage));
				//primaryStage.show();
			} else {
				nameField.setText("");
				passField.setText("");
				err.setVisible(true);
			}
		});
		cancel.setOnAction(e -> {
			primaryStage.close();
		});
		VBox leftR = new VBox();
		Rectangle left = new Rectangle();
		left.setHeight(800);
		left.setWidth(250);
		left.setFill(Color.web("#D64045"));
		leftR.setPadding(new Insets(10, 50, 50, 50));
		leftR.setStyle("-fx-background-image: url('/login-left.png');" +
				"-fx-background-repeat: stretch;" +
				"-fx-background-size: 300 800;" +
				"-fx-background-position: center center;");
		mainPane.setLeft(leftR);
		mainPane.setCenter(vertical);
	    mainPane.setStyle("-fx-background-color: #E6E7E3;");
		primaryStage.setTitle("LOGIN");
		primaryStage.setResizable(false);
		return scene;
	}
}