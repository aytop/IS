package scene;


import java.io.FileInputStream;
import java.io.IOException;

import crud.Crud;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import klase.Ocene;
import klase.Predmeti;
import klase.Ucenik;

public class UcenikScene extends Scene
{
	private Ucenik ucenik;

	public UcenikScene()
	{
		super(new BorderPane(), 800, 600);
		this.setRoot(getPane());
	}

	public UcenikScene(Ucenik ucenik)
	{
		super(new BorderPane(), 800, 600);
		this.setRoot(getPane());
		this.ucenik= ucenik;
	}

	public UcenikScene(double width, double hight)
	{
		super(new BorderPane(), width, hight);
		this.setRoot(getPane());
	}
	private TabPane getPane()
	{
		TabPane tabPane = new TabPane();
		Tab profil = new Tab("profil");
		profil.setClosable(false);
		profil.setContent(getProfilPane());
		Tab ocene =  new Tab("ocene");
		ocene.setClosable(false);
		ocene.setContent(getOcenePane());
		Tab raspored = new Tab("raspored");
		raspored.setClosable(false);
		raspored.setContent(getRasporedPane());
		tabPane.getTabs().addAll(profil, ocene, raspored);
		tabPane.setTabMinWidth(230);
		return tabPane;
	}
	
	protected Pane getProfilPane()
	{
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.setVgap(5);
		pane.setHgap(5);

		Label imeLabel = new Label("ime:");
		Label imeOcaLabel = new Label("srednje ime:");
		Label prezimeLabel = new Label("prezime:");
		Label datumRodjenjaLabel = new Label("datum rodjenja:");
		Label mestoLabel = new Label("mesto prebavalista:");
		Label brojTelefonaLabel = new Label("broj telefona:");
		Label razredLabel = new Label("razred:");

		Label imeInfo = new Label(ucenik.getIme());
		Label imeOcaInfo = new Label(ucenik.getImeOca());
		Label prezimeInfo = new Label(ucenik.getPrezime());
		Label datumRodjenjaInfo = new Label(ucenik.getDatumRodjenja().toString());
		Label mestoInfo = new Label(ucenik.getMestoPrebivalista());
		Label brojTelefonaInfo = new Label(ucenik.getBrojTelefona());
		Label razredInfo = new Label(ucenik.getRazred());

		pane.add(imeLabel, 0, 0);
		pane.add(imeInfo, 0, 1);
		pane.add(prezimeLabel,1,0);
		pane.add(prezimeInfo,1,1);
		pane.add(imeOcaLabel,2,0);
		pane.add(imeOcaInfo,2,1);
		pane.add(razredLabel,3,0);
		pane.add(razredInfo,3,1);
		pane.add(datumRodjenjaLabel,0,2);
		pane.add(datumRodjenjaInfo,0,3);
		pane.add(mestoLabel,1,2);
		pane.add(mestoInfo,1,3);
		pane.add(brojTelefonaLabel,2,2);
		pane.add(brojTelefonaInfo,2,3);
		// To-DO
		return pane;
	}
	private Pane getRasporedPane()
	{
		try(FileInputStream ioStream = new FileInputStream("Raspored-casova-3.jpg"))
		{
			ImageView imageView = new ImageView(new Image(ioStream));
			imageView.setFitWidth(760);
			imageView.setPreserveRatio(true);
			BorderPane pane = new BorderPane(imageView);
			pane.setPadding(new Insets(20));
			return pane;
		}
		catch(IOException e)
		{
			Alert ioError = new Alert(AlertType.ERROR);
			ioError.setTitle("Input Output Error");
			ioError.setHeaderText("File Not Found!");
			ioError.setContentText("Error opening file at : Raspored-casova-3.jpg");
			ioError.showAndWait();
			return null;
		}
	}
	private Pane getOcenePane()
	{
		BorderPane pane = new BorderPane();
				
		// DUMMY
		Crud c = new Crud();

		//sortirana u crudu
		Predmeti[] predmeti = (Predmeti[]) c.listPredmeti().toArray();
		String[] predmetiIme = new String[predmeti.length];
		for(int i = 0; i< predmeti.length; i++){
			predmetiIme[i]=predmeti[i].getNazivPredmeta();
		}
		//String[] predmeti = {"Predmet1","Predmet2","Predmet3","Predmet4"};
//		int[][] ocene = {{3,4,5,5,4},
//						 {5,5,5,5},
//						 {3,2,4,5},
//						 {2,2,3,2}
//		};
		long[][] ocene = new long[predmeti.length][20];
		//idp ide od 0-x
		for(int i = 0; i< predmeti.length; i++){
			Ocene[] oceneIzPredmeta = (Ocene[]) c.oceneIzPredmeta(i, ucenik).toArray();
			for(int j = 0; j < oceneIzPredmeta.length;j++){
				ocene[i][j] = oceneIzPredmeta[j].getOcena();
			}
		}

		// DUMMY

		double[] avarage = racunajProsek(ocene);
		GridPane container = new GridPane();
		for(int i =0; i < predmeti.length;i++)
		{
			Label predmet = new Label(predmeti[i]+": ");
			predmet.setFont(new Font(30));
			TextField ocena = MyText(oceneToString(ocene[i]));
			TextField prosecna = MyText(avarage[i]+"");
			prosecna.setPrefWidth(120);
			container.addRow(i, predmet, ocena, prosecna);
			
			Insets margina = new Insets(25);
			GridPane.setMargin(predmet, margina);
			GridPane.setMargin(ocena, margina);
			GridPane.setMargin(prosecna, margina);
			
		}
		container.setAlignment(Pos.CENTER);
		pane.setCenter(container);
		BorderPane.setAlignment(container, Pos.CENTER);
		
		TextField prosek = MyText("Prosecna ocena: " + prosekSvih(avarage));
		prosek.setPrefSize(300, 50);
		pane.setBottom(prosek);
		BorderPane.setAlignment(prosek, Pos.TOP_CENTER);
		BorderPane.setMargin(prosek, new Insets(25,25,100,25));
		
		return pane;
	}
	private static double prosekSvih(double[] prosecne)
	{
		double sum = 0.0;
		int broj = 0;
		for(double ocena : prosecne)
		{
			sum+=ocena;
			broj++;
		}
		return sum/broj;
	}
	private static String oceneToString(long[] ocene)
	{
		StringBuilder sb = new StringBuilder();
		for(long ocena:ocene)
		{
			sb.append(ocena);
			sb.append(", ");
		}
		sb.deleteCharAt(sb.length()-2);
		return sb.toString();
	}
	private static double[] racunajProsek(long[][] ocene)
	{
		double[] prosek = new double[ocene.length];
		for(int i =0;i < ocene.length;i++)
		{
			double sum =0;
			int broj =0;
			for(long ocena: ocene[i])
			{
				sum+=ocena;
				broj++;
			}
			prosek[i] = sum/broj;
		}
		return prosek;
	}
	
	private TextField MyText(String text)
	{
		TextField txtInput = new TextField(text);
		txtInput.setFont(new Font(30));
		txtInput.setEditable(false);
		txtInput.setMouseTransparent(true);
		txtInput.setFocusTraversable(false);
		txtInput.setAlignment(Pos.BOTTOM_CENTER);
		return txtInput;
	}
}
