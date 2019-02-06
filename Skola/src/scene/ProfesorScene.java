package scene;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProfesorScene extends Scene
{
	private class PogledajRazredHandler implements EventHandler<MouseEvent> {
		ListView<String> razredi;
		ProfesorScene parent;

		public PogledajRazredHandler(ListView<String> razredi, ProfesorScene parent) {
			this.razredi = razredi;
			this.parent = parent;
		}

		public void handle(MouseEvent e)
		{
			if((e.getSource() instanceof Button ) || (e.getSource() instanceof ListView && e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2))

			{
				String razred = razredi.getSelectionModel().getSelectedItem();
				//to do search base by razred;
				parent.setRoot(getRazredPane());
			}
		}
	}
	private class ProfilUcenikaHandler implements EventHandler<MouseEvent>
	{
		@Override
		public void handle(MouseEvent event)
		{
			if((event.getSource() instanceof  Button) || (event.getSource() instanceof ListView && event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2))
			{
				Scene ucenik = new UcenikScene();
				Stage ucenikStage = new Stage();
				ucenikStage.setScene(ucenik);
				ucenikStage.initModality(Modality.APPLICATION_MODAL);
				ucenikStage.showAndWait();
			}
		}
	}
	private class OceniUcenikaHandler implements EventHandler<MouseEvent>
	{
		@Override
		public void handle(MouseEvent event) {
			Stage oceneStage = new Stage();
			Scene upisOcene = new OceniScene(oceneStage);
			oceneStage.setScene(upisOcene);
			oceneStage.initModality(Modality.APPLICATION_MODAL);
			oceneStage.showAndWait();
		}
	}
	private class UpisiIzostanakHandler implements EventHandler<MouseEvent>
	{
		@Override
		public void handle(MouseEvent event) {
			Stage oceneStage = new Stage();
			Scene upisOcene = new IzostanakScene(oceneStage);
			oceneStage.setScene(upisOcene);
			oceneStage.initModality(Modality.APPLICATION_MODAL);
			oceneStage.showAndWait();
		}
	}
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
		EventHandler<MouseEvent> handler = new PogledajRazredHandler(predmeti, this);
		predmeti.setOnMouseClicked(handler);
		predmeti.getItems().addAll(dummy);
		pane.setCenter(predmeti);
		BorderPane.setMargin(predmeti, new Insets(75, 50,0,50));
		Button razred = new Button("Pregledaj razred");
		razred.setOnMouseClicked(handler);
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
		ucenici.setOnMouseClicked(new ProfilUcenikaHandler());
		ucenici.getItems().addAll(dummy);
		pane.setCenter(ucenici);
		BorderPane.setMargin(ucenici, new Insets(75, 50,0,50));
		Button pregledaj = new Button("Profil ucenika");
		Button oceni = new Button("Oceni ucenika");
		Button izostanak = new Button("Upisi izostanak");
		pregledaj.setOnMouseClicked(new ProfilUcenikaHandler());
		oceni.setOnMouseClicked(new OceniUcenikaHandler());
		izostanak.setOnMouseClicked(new UpisiIzostanakHandler());
		HBox container = new HBox();
		container.getChildren().addAll(oceni, pregledaj, izostanak);
		HBox.setMargin(oceni, new Insets(0,20,0,80));
		HBox.setMargin(izostanak, new Insets(0,20,0,100));
		HBox.setMargin(pregledaj, new Insets(0, 30,0,120));
		pane.setBottom(container);
		BorderPane.setMargin(container, new Insets(25,50,25,50));
		BorderPane.setAlignment(container, Pos.TOP_CENTER);
		return pane;
	}
	
}
