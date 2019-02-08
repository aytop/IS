package scene;

import crud.Crud;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import klase.Izostanak;
import klase.Predmeti;
import klase.Ucenik;

import java.sql.Date;
import java.time.LocalDate;

public class IzostanakScene extends Scene {

    private Stage window;
    private DatePicker date;
    private ComboBox<String> predmet;
    private Ucenik ucenik;
    private class SacuvajHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event) {
            //To-DO backend
            Crud c = new Crud();
            c.insertIzostanak(new Izostanak(1,Long.parseLong(ucenik.getRazred()), ucenik.getRedniBroj(),
                    "", c.findIdp(predmet.getSelectionModel().getSelectedItem()),
                    Date.valueOf(LocalDate.now())));
            window.close();
        }
    }
    public IzostanakScene(Stage s, Ucenik ucenik)
    {
        super(new BorderPane(), 300,350);
        this.setRoot(getPane());
        this.window = s;
        window.setTitle("Upisivanje izostanka");
        this.ucenik=ucenik;
    }
    public IzostanakScene(Stage s,int width, int height, Ucenik ucenik)
    {
        super(new BorderPane(), width, height);
        this.setRoot(getPane());
        this.window = s;
        this.ucenik = ucenik;
    }
    private Pane getPane()
    {
        BorderPane pane = new BorderPane();
        date = new DatePicker();
        pane.setTop(date);
        predmet = new ComboBox<String>();
        Crud c = new Crud();
        Predmeti[] listaPredmeta= (Predmeti[]) c.listPredmeti().toArray();
        for(int i=0; i<listaPredmeta.length;i++){
            predmet.getItems().add(listaPredmeta[i].getNazivPredmeta());
        }
        pane.setCenter(predmet);
        Button sacuvaj = new Button("Sacuvaj");
        pane.setBottom(sacuvaj);
        BorderPane.setAlignment(sacuvaj, Pos.CENTER);
        BorderPane.setAlignment(date,Pos.TOP_CENTER);
        BorderPane.setMargin(date,new Insets(20));
        BorderPane.setMargin(sacuvaj,new Insets(20));
        sacuvaj.setOnMouseClicked(new SacuvajHandler());
        return pane;
    }
}
