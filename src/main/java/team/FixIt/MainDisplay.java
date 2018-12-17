package team.FixIt;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;
import team.FixIt.GUI.set;


public class MainDisplay 
{

	static boolean answer;
	static String Account = "";
	private static mysql MySQL;

	private final static Logger LOGGER = Logger.getLogger(MainDisplay.class.getName());

	public static boolean choice(set set) throws SQLException
	{
		LOGGER.setLevel(Level.INFO);
		Stage mainWindow = new Stage();
		mainWindow.initModality(Modality.APPLICATION_MODAL);
		mainWindow.setTitle("Main Menu");
		GridPane grid = new GridPane();
		/////////////////////////////////////////
		Label MainMenuLabel = new Label("Main Menu");
		MainMenuLabel.setId("Title");
		grid.add(MainMenuLabel, 0, 2);
		/////////////////////////////////////////
		Button CreateBtn = new Button("Create Passwords");
		HBox CreateHB_Box = new HBox(10);
		CreateHB_Box.setAlignment(Pos.CENTER);
		CreateHB_Box.getChildren().add(CreateBtn);
		grid.add(CreateHB_Box, 1, 3);
		CreateBtn.setId("MainMenuButton");
		/////////////////////////////////////////
		Button ListBtn = new Button("List passwords");
		HBox ListHB_Box = new HBox(10);
		ListHB_Box.setAlignment(Pos.CENTER);
		ListHB_Box.getChildren().add(ListBtn);
		grid.add(ListHB_Box, 1, 4);
		ListBtn.setId("MainMenuButton");
		/////////////////////////////////////////
		Button DeleteBtn = new Button("Delete Passwords");
		HBox DeleteHB_Box = new HBox(20);
		DeleteHB_Box.setAlignment(Pos.CENTER);
		DeleteHB_Box.getChildren().add(DeleteBtn);
		grid.add(DeleteHB_Box, 1, 5);
		DeleteBtn.setId("MainMenuButton");
		/////////////////////////////////////////
		Button LogOutBtn = new Button("Exit");
		HBox LogOutHB_Box = new HBox(20);
		LogOutHB_Box.setAlignment(Pos.CENTER);
		LogOutHB_Box.getChildren().add(LogOutBtn);
		grid.add(LogOutHB_Box, 1, 6);
		LogOutBtn.setId("MainMenuButton");
		/////////////////////////////////////////
		CreateBtn.setOnAction(e -> 
		{
			answer= false; 
			try 
			{
				answer = createLogin(set);
				if (answer)
				{
					mainWindow.close();
				}
			} 
			catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		ListBtn.setOnAction(e -> 
		{
			answer= false;
			try 
			{
				answer = listPasswords(set);
				if (answer)
				{
					mainWindow.close();
				}
			} 
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		DeleteBtn.setOnAction(e -> 
		{
			answer= false;
			try 
			{
				answer = deletePasswords(set);
				if (answer)
				{
					mainWindow.close();
				}
			} 
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		LogOutBtn.setOnAction(e -> 
		{
			mainWindow.close();
		});
		VBox layout= new VBox(20);
		layout.getChildren().addAll(MainMenuLabel, CreateBtn, ListBtn, DeleteBtn, LogOutBtn);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, common.HEIGHT, common.WIDTH);
		scene.getStylesheets().add(GUI.class.getResource("CSS/Login.css").toExternalForm());
		mainWindow.getIcons().add(GUI.iconImage);
		mainWindow.setScene(scene);
		mainWindow.showAndWait();
		return answer;
	}

	private static TilePane createTile(Button[] Btn, String[] arrayNames)
	{
		LOGGER.info("Running TilePane Creation");
		int size = arrayNames.length;
		TilePane tileButtons = new TilePane();
		tileButtons.setPadding(new Insets(10, 10, 10, 10));
		tileButtons.setPrefColumns(size);
		HBox hori = new HBox(20);
		for(int i = 0; i < size; i++)
		{
			if(Btn[i] == null)
			{
				Btn[i] = new Button();
			}
			LOGGER.info("Label is empty = " + arrayNames[i]);
			Btn[i].setText(arrayNames[i]);
			hori.setMargin(Btn[i], new Insets(20, 20, 20, 20)); 
			hori.getChildren().addAll(Btn[i]);
		}
		tileButtons.getChildren().add(hori);
		tileButtons.setAlignment(Pos.BOTTOM_CENTER);
		return tileButtons;
	}


	private static boolean createLogin(set set) throws SQLException 
	{
		Stage mainWindow = new Stage();
		mainWindow.initModality(Modality.APPLICATION_MODAL);
		mainWindow.setTitle("Create a New Login");
		/////////////////////////////////////////
		TextField accountField = new TextField();
		accountField.setId("Field");
		TextField usernameField = new TextField();
		usernameField.setId("Field");
		PasswordField passwordField = new PasswordField();
		passwordField.setId("Field");
		PasswordField repasswordField = new PasswordField();
		repasswordField.setId("Field");
		AnchorPane anchorpane = new AnchorPane();
		double top = 50.0;
		double topText = 75.0;
		final double left = 60.0;
		final double right = 200.0;
		String[] names = {"Account:", "Username:", "Password:", "Re-enter \n Password:"};
		Label[] Labels = null;
		Labels = new Label[4];
		Text text = new Text();
		text.setId("actiontarget");
		LOGGER.info("Creating labels");
		LOGGER.info("Label is empty = " + names[0]);
		for(int i = 0; i < 4; i++)
		{
			if (Labels[i] == null)
			{
				LOGGER.info("Label is empty = " + names[i]);
				Labels[i] = new Label(names[i]);
			}
			anchorpane.setTopAnchor(Labels[i], top);
			anchorpane.setLeftAnchor(Labels[i], left);
			anchorpane.setRightAnchor(Labels[i], right); 
			LOGGER.info("Label " + Labels[i]);
			top = top + 50; 
		}
		anchorpane.setTopAnchor(accountField, 50.0);
		anchorpane.setLeftAnchor(accountField, 200.0);
		anchorpane.setRightAnchor(accountField, 0.0);
		anchorpane.setTopAnchor(usernameField, 100.0);
		anchorpane.setLeftAnchor(usernameField, 200.0);
		anchorpane.setRightAnchor(usernameField, 0.0);		
		anchorpane.setTopAnchor(passwordField, 150.0);
		anchorpane.setLeftAnchor(passwordField, 200.0);
		anchorpane.setRightAnchor(passwordField, 0.0);		
		anchorpane.setTopAnchor(repasswordField, 200.0);
		anchorpane.setLeftAnchor(repasswordField, 200.0);
		anchorpane.setRightAnchor(repasswordField, 0.0);
		anchorpane.setTopAnchor(text, 225.0);
		anchorpane.setLeftAnchor(text, 200.0);
		anchorpane.setRightAnchor(text, 0.0);
		anchorpane.getChildren().addAll(Labels[0],
										accountField,
										Labels[1], 
										usernameField, 
										Labels[2], 
										passwordField,
										Labels[3],
										repasswordField,
										text);
		/////////////////////////////////////////
		Label SceneTitle = new Label("Create Password");
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
		String[] arrayNames = {"Create", "Return to\nMain Menu", "Logout"};
		Button[] Btn = new Button[3];
		TilePane tileButtons = createTile(Btn, arrayNames);
		/////////////////////////////////////////
		BorderPane border = new BorderPane();
		border.setTop(tileTitle);
		border.setLeft(anchorpane);
		border.setBottom(tileButtons);
		/////////////////////////////////////////
		MySQL = set.MySQL;
		/////////////////////////////////////////
		Btn[0].setOnAction(e -> 
		{
			String accountString = accountField.getText();
			String usernameString = usernameField.getText();
			String passwordString = passwordField.getText();
			String repasswordString = repasswordField.getText();
			try 
			{
				boolean exists = false;
				exists = MySQL.checkIfAccountExists("accounts", accountString);
				text.setText("");
				if(!exists)
				{
					if(usernameString.length() < 2)
					{
						text.setText("Enter a correct username");
					}
					else if(passwordString.length() < 2)
					{
						text.setText("Enter a correct password");
					}
					else if(!passwordString.equals(repasswordString))
					{
						text.setText("Passwords do not match");
					}
					else
					{
						MySQL.CreateNewLogin("accounts", accountString, usernameString, passwordString);
						text.setText("Created New Login");
						accountField.setText("");
						usernameField.setText("");
						passwordField.setText("");
						repasswordField.setText("");
					}
				}
				else
				{
					if(accountString.equals(""))
					{
						text.setText("Please enter a account");
					}
					else 
					{
						text.setText("Account Already Exists");					
					}
				}
			} 
			catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		/////////////////////////////////////////
		Btn[1].setOnAction(e -> 
		{
			answer = false; 
			mainWindow.close();
		});
		Btn[2].setOnAction(e -> 
		{
			answer = true; 
			mainWindow.close();
		});
		Scene mainScene = new Scene(border, common.HEIGHT, common.WIDTH);
		mainScene.getStylesheets().add(GUI.class.getResource("CSS/Login.css").toExternalForm());
		mainWindow.setScene(mainScene);
		mainWindow.getIcons().add(GUI.iconImage);
		mainWindow.showAndWait();
		return answer;
	}

	private static boolean listPasswords(set set) throws SQLException
	{
		Stage mainWindow = new Stage();
		mainWindow.initModality(Modality.APPLICATION_MODAL);
		mainWindow.setTitle("List Passwords");
		/////////////////////////////////////////
		Label SceneTitle = new Label("List Passwords");
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
		Label account = new Label("Account:");
		TextField accountField = new TextField();
		accountField.setId("Field");
		AnchorPane anchorpane = new AnchorPane();
		anchorpane.setTopAnchor(account, 50.0);
		anchorpane.setLeftAnchor(account, 60.0);
		anchorpane.setRightAnchor(account, 200.0);
		/////////////////////////////////////////
		MySQL = set.MySQL;
		/////////////////////////////////////////
		final ComboBox<String> accountComboBox = new ComboBox<String>();
		LinkedList<String> mysqlAccounts = MySQL.PrintDetails("accounts");
		for(int i = 0; i < mysqlAccounts.size(); i++)
		{
			String value = mysqlAccounts.get(i);
			accountComboBox.getItems().add(value);
		}
		anchorpane.setTopAnchor(accountComboBox, 50.0);
		anchorpane.setLeftAnchor(accountComboBox, 200.0);
		anchorpane.setRightAnchor(accountComboBox, 0.0);		
		/////////////////////////////////////////
		Text username = new Text();
		username.setId("Output");
		Text password = new Text();
		password.setId("Output");
		List<TextField> anchorList = new LinkedList<TextField>();
		anchorpane.setTopAnchor(username, 125.0);
		anchorpane.setLeftAnchor(username, 60.0);
		anchorpane.setRightAnchor(username, 200.0);
		anchorpane.setTopAnchor(password, 200.0);
		anchorpane.setLeftAnchor(password, 60.0);
		anchorpane.setRightAnchor(password, 200.0);	
		username.setId("actiontarget");
		password.setId("actiontarget");
		anchorpane.getChildren().addAll(account, accountComboBox, username, password);
		/////////////////////////////////////////
		String[] arrayNames = {"List", "Return to\nMain Menu", "Logout"};
		Button[] Btn = new Button[3];
		TilePane tileButtons = createTile(Btn, arrayNames);
		/////////////////////////////////////////
		BorderPane border = new BorderPane();
		border.setTop(tileTitle);
		border.setLeft(anchorpane);
		border.setBottom(tileButtons);
		/////////////////////////////////////////
		Btn[0].setOnAction(e -> 
		{
			System.out.print("Button List was pressed");
			String thisAccount = accountComboBox.getValue();
			try 
			{
				String usernameText = MySQL.getUsername("accounts", thisAccount);
				username.setText(usernameText);
				String passwordText = MySQL.getPassword("accounts", thisAccount);
				password.setText(passwordText);
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		});
		/////////////////////////////////////////
		Btn[1].setOnAction(e -> 
		{
			answer = false; 
			mainWindow.close();
		});
		Btn[2].setOnAction(e -> 
		{
			answer = true; 
			mainWindow.close();
		});
		Scene mainScene = new Scene(border, common.HEIGHT, common.WIDTH);
		mainScene.getStylesheets().add(GUI.class.getResource("CSS/Login.css").toExternalForm());
		mainWindow.setScene(mainScene);
		mainWindow.getIcons().add(GUI.iconImage);
		mainWindow.showAndWait();
		return answer;
	}

	private static boolean deletePasswords(set set) throws SQLException
	{
		Stage mainWindow = new Stage();
		mainWindow.initModality(Modality.APPLICATION_MODAL);
		mainWindow.setTitle("Delete Passwords");
		/////////////////////////////////////////
		Label SceneTitle = new Label("Delete Passwords");
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
		Label account = new Label("Account:");
		TextField accountField = new TextField();
		accountField.setId("Field");
		AnchorPane anchorpane = new AnchorPane();
		anchorpane.setTopAnchor(account, 50.0);
		anchorpane.setLeftAnchor(account, 60.0);
		anchorpane.setRightAnchor(account, 200.0);
		/////////////////////////////////////////
		MySQL = set.MySQL;
		/////////////////////////////////////////
		final ComboBox<String> accountComboBox = new ComboBox<String>();
		LinkedList<String> mysqlAccounts = MySQL.PrintDetails("accounts");
		for(int i = 0; i < mysqlAccounts.size(); i++)
		{
			String value = mysqlAccounts.get(i);
			accountComboBox.getItems().add(value);
		}
		anchorpane.setTopAnchor(accountComboBox, 50.0);
		anchorpane.setLeftAnchor(accountComboBox, 200.0);
		anchorpane.setRightAnchor(accountComboBox, 0.0);		
		/////////////////////////////////////////
		Text text = new Text();
		text.setId("actiontarget");
		anchorpane.setTopAnchor(text, 150.0);
		anchorpane.setLeftAnchor(text, 200.0);
		anchorpane.setRightAnchor(text, 0.0);
		anchorpane.getChildren().addAll(account, accountComboBox, text);
		/////////////////////////////////////////
		String[] arrayNames = {"Delete", "Return to\nMain Menu", "Logout"}; 
		Button[] Btn = new Button[3];
		TilePane tileButtons = createTile(Btn, arrayNames);
		/////////////////////////////////////////
		BorderPane border = new BorderPane();
		border.setTop(tileTitle);
		border.setLeft(anchorpane);
		border.setBottom(tileButtons);
		/////////////////////////////////////////
		Btn[0].setOnAction(e -> 
		{
			String thisAccount = accountComboBox.getValue();
			if(!thisAccount.equals(null))
			{
				try 
				{
					boolean deleted = MySQL.DeleteLogin(common.TABLE, thisAccount);
					if (deleted)
					{
						text.setText("The login has been removed");
					}
					else
					{
						text.setText("The login was not removed");
					}
				} 
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//DeleteActionTarget.setText("Password Removed");
			} 
		});
		/////////////////////////////////////////
		Btn[1].setOnAction(e -> 
		{
			answer = false; 
			mainWindow.close();
		});
		Btn[2].setOnAction(e -> 
		{
			answer = true; 
			mainWindow.close();
		});
		Scene mainScene = new Scene(border, common.HEIGHT, common.WIDTH);
		mainScene.getStylesheets().add(GUI.class.getResource("CSS/Login.css").toExternalForm());
		mainWindow.setScene(mainScene);
		mainWindow.getIcons().add(GUI.iconImage);
		mainWindow.showAndWait();
		return answer;
	}

}
