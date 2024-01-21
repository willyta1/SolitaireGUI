package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import solitaireBoard.Solitaire;

public class SolitaireGUI extends Application {
    private Solitaire solitaire = new Solitaire();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
        GridPane gridPane = new GridPane();
        Scene boardScene = new Scene(gridPane);
        boardScene.setFill(Color.GREEN);

        
        // solitaire.getStock().cycleCards();
		// ImageView imageView = new ImageView(solitaire.getStock().getCurrentCard().cardImage);
		// GridPane pane = new GridPane();
		// Scene scene = new Scene(pane);
		// pane.add(imageView, 50, 50);
		// stage.setScene(scene);
		// // stage.show();

        // stage.setScene(boardScene);
        stage.show();
        
    }

    // public Scene createSolitaireBoard() {
       
    // }
}
