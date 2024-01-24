package GUI;


import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import solitaireBoard.*;

public class SolitaireGUI extends Application {
    private Solitaire solitaire = new Solitaire();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub



        
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

    public Scene createSolitaireBoard() {
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane);
        scene.setFill(Color.GREEN);
        ArrayList<ArrayList<Card>> piles = solitaire.getTableau().getCardPiles();
        for (ArrayList<Card> pile: piles) {
            for (Card card: pile) {
                // Image cardImage = card.get
            }
        }
        return scene;
    }
}
