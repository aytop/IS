package scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ProfesorScene extends Scene
{
	public ProfesorScene()
	{
		super(new BorderPane(), 800, 600);
		this.setRoot(getPane());
	}
	public ProfesorScene(double width, double hight)
	{
		super(new BorderPane(), width, hight);
		this.setRoot(getPane());
	}
	private Pane getPane()
	{
		BorderPane pane = new BorderPane();
		// DUMMY
		String[] dummy = {"razred 1", "razred 2", "razred 3", "razred 4"};
		// DUMMY

		ListView<String> predmeti = new ListView<String>();
		predmeti.getItems().addAll(dummy);
		pane.setCenter(predmeti);
		BorderPane.setMargin(predmeti, new Insets(75, 50,0,50));
		Button razred = new Button("Pregledaj razred");
		pane.setBottom(razred);
		BorderPane.setMargin(razred, new Insets(25));
		BorderPane.setAlignment(razred, Pos.TOP_CENTER);
		return pane;
	}
	public Pane getRazredPane()
	{
		BorderPane pane = new BorderPane();
		// DUMMY
		String[] dummy = {"ucenik 1", "ucenik 2", "ucenik 3", "ucenik 4", "ucenik 5", "ucenik 6", "ucenik 7", "ucenik 8"};
		// DUMMY

		ListView<String> ucenici = new ListView<String>();
		ucenici.getItems().addAll(dummy);
		pane.setCenter(ucenici);
		BorderPane.setMargin(ucenici, new Insets(75, 50,0,50));
		Button oceni = new Button("Oceni ucenika");
		Button izostanak = new Button("Upisi izostanak"); 
		HBox container = new HBox();
		container.getChildren().addAll(oceni, izostanak);
		HBox.setMargin(oceni, new Insets(0,100,0,150));
		HBox.setMargin(izostanak, new Insets(0,100,0,100));
		pane.setBottom(container);
		BorderPane.setMargin(container, new Insets(25,50,25,50));
		BorderPane.setAlignment(container, Pos.TOP_CENTER);
		return pane;
	}
	
}
