package team.FixIt;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application
{
	private static final String Empty = "";
	private int height = 640;
    private int width  = 480;
	private static boolean closed;
	static String Username;
	static String Password;
	private static int Attempts = 0;
	private static boolean Locked = false;
    public static Image iconImage = new Image(GUI.class.getResourceAsStream("/images/icon.png"));

	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Password Manager");
        Text LoginActionTarget = new Text();
        LoginActionTarget.setId("actiontarget");
        /////////////////////////////////////////
		Label username = new Label("Username:");
		TextField textField = new TextField();
		textField.setId("Field");
		Label password = new Label("Password:");
		PasswordField passwordField = new PasswordField();
		passwordField.setId("Field");
		AnchorPane anchorpane = new AnchorPane();
		anchorpane.setTopAnchor(username, 50.0);
		anchorpane.setLeftAnchor(username, 60.0);
		anchorpane.setRightAnchor(username, 200.0);

		anchorpane.setTopAnchor(textField, 50.0);
		anchorpane.setLeftAnchor(textField, 200.0);
		anchorpane.setRightAnchor(textField, 0.0);

		anchorpane.setTopAnchor(password, 150.0);
		anchorpane.setLeftAnchor(password, 60.0);
		anchorpane.setRightAnchor(password, 200.0);

		anchorpane.setTopAnchor(passwordField, 150.0);
		anchorpane.setLeftAnchor(passwordField, 200.0);
		anchorpane.setRightAnchor(passwordField, 0.0);

		anchorpane.getChildren().addAll(username, textField, password, passwordField);
		/////////////////////////////////////////
        Label SceneTitle = new Label("Login Screen");
        SceneTitle.setId("Title");
		TilePane tileTitle = new TilePane();
		tileTitle.setPadding(new Insets(10, 10, 10, 10));
		tileTitle.setPrefColumns(1);
		HBox hbox = new HBox(20);
        hbox.setMargin(SceneTitle, new Insets(10, 10, 10, 10));
		hbox.getChildren().addAll(SceneTitle);
		tileTitle.getChildren().add(hbox);
		tileTitle.setAlignment(Pos.CENTER);
		/////////////////////////////////////////
		TilePane tileButtons = new TilePane();
		tileButtons.setPadding(new Insets(10, 10, 10, 10));
		tileButtons.setPrefColumns(2);
        Button LoginBtn = new Button("Sign in");
        Button RegisterBtn = new Button("Register");
		HBox hbox1 = new HBox(20);
        hbox1.setMargin(LoginBtn, new Insets(20, 20, 20, 20));
        hbox1.setMargin(RegisterBtn, new Insets(20, 20, 20, 20));
		hbox1.getChildren().addAll(LoginBtn, RegisterBtn);
		tileButtons.getChildren().add(hbox1);
		tileButtons.setAlignment(Pos.BOTTOM_CENTER);
        /////////////////////////////////////////
		BorderPane border = new BorderPane();
		border.setTop(tileTitle);
		border.setLeft(anchorpane);
		border.setBottom(tileButtons);
		/////////////////////////////////////////
		LoginBtn.setOnAction(e ->
		{
			if (Attempts < 3)
			{
				set Set = new set();
		    	Username = textField.getText();
		    	Password = passwordField.getText();
		    	textField.setText(Empty);
		    	passwordField.setText(Empty);
				boolean correct = Set.checkConnection(Username, Password);
				if(correct)
				{
					LoginActionTarget.setText("Login Successful");
					try
					{
						TimeUnit.SECONDS.sleep(1);
					}
					catch (InterruptedException e2)
					{
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try
					{
						closed = MainDisplay.choice(Set);
					}
					catch (SQLException e1)
					{
						e1.printStackTrace();
					}
					if (closed)
					{
						LoginActionTarget.setText("");
						Username = "";
						Password = "";
						correct = false;
					}
				}
				else
				{
					LoginActionTarget.setText("Login Unsuccessful");
					Attempts += 1;
				}
			}
			else
			{
				Locked = true;
			}
		});
		//BorderPane border = setup();
		Scene sceneLogin = new Scene(border, height, width);
		sceneLogin.getStylesheets().add(GUI.class.getResource("/CSS/Login.css").toExternalForm());
        primaryStage.getIcons().add(iconImage);
		primaryStage.setScene(sceneLogin);
		primaryStage.setMaxHeight(480);
		primaryStage.setMaxWidth(640);
		primaryStage.show();

		if (Locked == true)
		{

		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	public class set
	{
		public boolean connection = false;
		public final mysql MySQL = new mysql();
		String Username = "";
		String Password = "";

		public boolean checkConnection(String Username, String Password)
		{
			this.Username = Username;
			this.Password = Password;
	    	connection = MySQL.StartConnection(Username, Password);
			return connection;
		}
	}

}
