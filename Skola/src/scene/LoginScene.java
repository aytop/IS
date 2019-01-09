package scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class LoginScene extends Scene
{
	public LoginScene()
	{
		super(getPane(), 400, 400);
	}
	public LoginScene(double width, double hight)
	{
		super(getPane(), width, hight);
	}
	private static Pane getPane()
	{
		GridPane ret = new GridPane();
		Label user = new Label("Username: ");
		Label pass = new Label("Password: ");
		TextField userBox = new TextField();
		PasswordField passBox = new PasswordField();
		Button login = new Button("Login");
		//user.setPadding(new Insets(10));
		//pass.setPadding(new Insets(10,10, 50,10));
		ret.add(user, 0, 0);
		ret.add(userBox, 1, 0);
		ret.add(pass, 0, 1);
		ret.add(passBox, 1, 1);
		ret.add(login, 0, 2, 2, 1);
		login.setAlignment(Pos.CENTER);
		login.setPadding(new Insets(10, 120, 10, 120));
		ret.setAlignment(Pos.CENTER);
		ret.setVgap(20);
		return ret;		
	}
}
