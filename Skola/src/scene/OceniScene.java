package scene;

import crud.Crud;
import klase.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OceniScene extends Scene
{
    private Stage window;
    private ComboBox<Integer> ocene;
    private ComboBox<String> predmet;
    private Ucenik ucenik;
    private class SacuvajHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event) {
            //To-DO backend
            Crud c = new Crud();
            c.insertOcena(new Ocene(1,Long.parseLong(ucenik.getRazred()), ucenik.getRedniBroj(),
                    ocene.getSelectionModel().getSelectedItem(),
                    c.findIdp(predmet.getSelectionModel().getSelectedItem()),
                    Date.valueOf(LocalDate.now())));
            window.close();
        }
    }
    public OceniScene(Stage window, Ucenik ucenik)
    {
        super(new BorderPane(), 150,100);
        this.setRoot(getPane());
        this.window = window;
        window.setTitle("Oceni ucenika");
        this.ucenik = ucenik;
    }
    public OceniScene(Stage window,int width, int height, Ucenik ucenik)
    {
        super(new BorderPane(),width,height);
        this.setRoot(getPane());
        this.window = window;
        this.ucenik = ucenik;
    }
    private Pane getPane()
    {
        ocene = new ComboBox<Integer>();
        for(int i=5;i>0;i--)
        {
            ocene.getItems().add(i);
        }
        ocene.getSelectionModel().select(0);
        predmet = new ComboBox<String>();
        Crud c = new Crud();
        Predmeti[] listaPredmeta= (Predmeti[]) c.listPredmeti().toArray();
        for(int i=0; i<listaPredmeta.length;i++){
            predmet.getItems().add(listaPredmeta[i].getNazivPredmeta());
        }
        BorderPane pane = new BorderPane();
        Button sacuvaj = new Button("Sacuvaj");
        sacuvaj.setOnMouseClicked(new SacuvajHandler());
        pane.setTop(ocene);
        pane.setCenter(predmet);
        pane.setBottom(sacuvaj);
        BorderPane.setAlignment(sacuvaj, Pos.CENTER);
        BorderPane.setMargin(sacuvaj, new Insets(0,0,20,0));
        return pane;
    }
}
