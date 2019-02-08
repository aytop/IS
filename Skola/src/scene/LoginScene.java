package scene;

import crud.Crud;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import klase.Ucenik;
import klase.UserInfo;

import javax.validation.constraints.Null;

public class LoginScene extends Scene
{
	private static Stage stage;
	private static TextField userBox;
	private static PasswordField passBox;

	public LoginScene()
	{
		super(getPane(), 400, 400);
	}
	public LoginScene(Stage stage)
	{
		super(getPane(), 400, 400);
		this.stage = stage;
	}
	public LoginScene(double width, double hight)
	{
		super(getPane(), width, hight);
	}

	private static class SacuvajHandler implements EventHandler<MouseEvent>
	{
		@Override
		public void handle(MouseEvent event) {
			//To-DO backend
			Crud c = new Crud();
			String username = userBox.getText();
			String password = passBox.getText();
			UserInfo korisnik = c.login(username, password);
			if (korisnik== null){
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Pogresno koriscnicko ime/sifre");
				alert.setHeaderText(null);
				alert.setContentText("Ne postoji ovakav korisnik.");

				alert.showAndWait();
			}
			else{

				//zavisi koje su uloge u bazi
				if(korisnik.getUloga()==1){
					stage.setScene(new ProfesorScene());
				}else if(korisnik.getUloga() == 2){
					Ucenik ucenik = c.findUcenik(username);
					stage.setScene(new UcenikScene(ucenik));
				}else{
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Uneti korisnik nije implementiran");
					alert.setHeaderText(null);
					alert.setContentText("Dolazi u sledecoj verziji dnevnika.");

					alert.showAndWait();
				}
			}
		}
	}


	private static Pane getPane()
	{
		GridPane ret = new GridPane();
		Label user = new Label("Username: ");
		Label pass = new Label("Password: ");
		userBox = new TextField();
		passBox = new PasswordField();
		Button login = new Button("Login");
		login.setOnMouseClicked(new SacuvajHandler());
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
