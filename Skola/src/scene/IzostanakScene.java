package scene;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class IzostanakScene extends Scene {

    private Stage window;
    private class SacuvajHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event) {
            //To-DO backend
            window.close();
        }
    }
    public IzostanakScene(Stage s)
    {
        super(new BorderPane(), 300,350);
        this.setRoot(getPane());
        this.window = s;
        window.setTitle("Upisivanje izostanka");
    }
    public IzostanakScene(Stage s,int width, int height)
    {
        super(new BorderPane(), width, height);
        this.setRoot(getPane());
        this.window = s;
    }
    private Pane getPane()
    {
        BorderPane pane = new BorderPane();
        DatePicker date = new DatePicker();
        pane.setCenter(date);
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
