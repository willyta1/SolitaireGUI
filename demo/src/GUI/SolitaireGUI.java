package GUI;


import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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


        stage.setMinHeight(650);
        stage.setMinWidth(950);

        Scene scene = createSolitaireBoard();
        stage.setScene(scene);
        stage.show();
        
    }

    public Scene createSolitaireBoard() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);

        BorderPane mainPane = new BorderPane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        
        
        HBox HBox = new HBox();
        HBox.setAlignment(Pos.TOP_CENTER);
        HBox.setPrefSize(650, 240);
        
        VBox VBox = new VBox();
        StackPane stockPane = new StackPane();
        
        ImageView stockPile = new ImageView();
        
        Image stockPileImage = new Image("file:demo/src/CardPNGs/cardBack.png");
        
        // imageView.maxHeight(100);
        // stockPile.
        stockPile.setImage(stockPileImage);
        stockPile.setFitWidth(stockPileImage.getWidth() * 0.154);
        stockPile.setFitHeight(stockPileImage.getHeight() * 0.154);
        stockPile.setUserData("stock");
        stockPile.setPreserveRatio(true);
        stockPane.getChildren().add(stockPile);
        stockPane.setAlignment(Pos.TOP_CENTER);
        stockPane.setPadding(new Insets(10,0,0,0));
        HBox.getChildren().add(stockPane);

        HBox.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        mainPane.setTop(HBox);
        mainPane.setCenter(pane);
        
        HBox stockFoundationSpace = new HBox();
        stockFoundationSpace.setPrefSize(240, 240);
        HBox.getChildren().add(stockFoundationSpace);

        Scene scene = new Scene(mainPane, 1440, 768);
        
        scene.setFill(Color.GREEN);
        
        ArrayList<ArrayList<Card>> piles = solitaire.getTableau().getCardPiles();
        int width = 0;
        int length = 0;
        
        // ImageView foundationPile = new ImageView();
        for (int i = 0; i < 4; i++) {
            ImageView foundationPile = new ImageView();
            Image image = new Image("file:demo/src/CardPNGs/foundationspace.png");
            foundationPile.setImage(image);
            foundationPile.setUserData("foundationpile" + (i+1));
            
            HBox.getChildren().add(foundationPile);

            HBox internalHBox = new HBox();
            internalHBox.setPrefSize(30,240);
            HBox.getChildren().add(internalHBox);
        }


        for (ArrayList<Card> pile: piles) {
            length = 0;
            ColumnConstraints column1 = new ColumnConstraints(130);
            pane.getColumnConstraints().addAll(column1); // each get 50% of width
            for (Card card: pile) {

                Image image = card.getImage();
                
                ImageView imageView = new ImageView(card.getImage());
                imageView.setUserData(card.getCardName());
                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        throw new UnsupportedOperationException("Unimplemented method 'handle'");
                    }


                    
                });
                imageView.setScaleX(0.2);
                imageView.setScaleY(0.2);

                // imageView.maxHeight(100);
                // imageView.maxWidth(70);
                imageView.setPreserveRatio(true);

                RowConstraints row2 = new RowConstraints();

                row2.setMinHeight(25);
                row2.setMaxHeight(50);
                pane.getRowConstraints().addAll(row2);

                pane.setHalignment(imageView, HPos.CENTER); 
                pane.setValignment(imageView, VPos.CENTER); 
                pane.setConstraints(imageView, width, length);


                pane.getChildren().add(imageView);

                length += 1;
            }
            width += 1;
        }

        return scene;
    }
}
