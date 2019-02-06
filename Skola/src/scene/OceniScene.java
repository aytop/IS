package scene;

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

public class OceniScene extends Scene
{
    private Stage window;
    private class SacuvajHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event) {
            //To-DO backend
            window.close();
        }
    }
    public OceniScene(Stage window)
    {
        super(new BorderPane(), 150,100);
        this.setRoot(getPane());
        this.window = window;
        window.setTitle("Oceni ucenika");
    }
    public OceniScene(Stage window,int width, int height)
    {
        super(new BorderPane(),width,height);
        this.setRoot(getPane());
        this.window = window;
    }
    private Pane getPane()
    {
        ComboBox<Integer> ocene = new ComboBox<>();
        for(int i=5;i>0;i--)
        {
            ocene.getItems().add(i);
        }
        ocene.getSelectionModel().select(0);
        BorderPane pane = new BorderPane(ocene);
        Button sacuvaj = new Button("Sacuvaj");
        sacuvaj.setOnMouseClicked(new SacuvajHandler());
        pane.setBottom(sacuvaj);
        BorderPane.setAlignment(sacuvaj, Pos.CENTER);
        BorderPane.setMargin(sacuvaj, new Insets(0,0,20,0));
        return pane;
    }
}
