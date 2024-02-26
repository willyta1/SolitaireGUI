package GUI;


import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
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

        ImageView stockPileEmpty = new ImageView();
        Image stockPileEmptyImage = new Image("file:demo/src/CardPNGs/stockspaceempty.png");
        stockPileEmpty.setFitHeight(stockPileEmptyImage.getHeight() *0.9);
        stockPileEmpty.setFitWidth(stockPileEmptyImage.getWidth() *0.87);


        stockPileEmpty.setImage(stockPileEmptyImage);

        ImageView stockPile = new ImageView();
        Image stockPileImage = new Image("file:demo/src/CardPNGs/cardBack.png");
        
        StackPane stockFoundationSpace = new StackPane();
        stockFoundationSpace.setAlignment(Pos.TOP_LEFT);
        stockFoundationSpace.setPadding(new Insets(10, 0, 0, 10));

        stockPile.setImage(stockPileImage);
        stockPile.setFitWidth(stockPileImage.getWidth() * 0.152);
        stockPile.setFitHeight(stockPileImage.getHeight() * 0.152);
        stockPile.setUserData("stock");
        stockPile.setPreserveRatio(true);

        stockPane.getChildren().add(stockPileEmpty);
        stockPane.getChildren().add(stockPile);
        stockPane.setAlignment(Pos.TOP_CENTER);
        stockPane.setPadding(new Insets(10,0,0,0));
        
        stockPileEmpty.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                // TODO Auto-generated method stub
                if(!stockPile.isVisible()) {
                    solitaire.getStock().cycleCards();
                    stockPile.setVisible(true);
                    stockFoundationSpace.getChildren().clear();
                }
            }

        });

        stockPile.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                // TODO Auto-generated method stub
                solitaire.getStock().cycleCards();
                if(solitaire.getStock().getCounter() == solitaire.getStock().getPileStock().size()-1) {
                    stockPile.setVisible(false);
                }
                
                if(solitaire.getStock().getCurrentCard() != null) {
                    Image cardImage = solitaire.getStock().getCurrentCard().getImage();
                    ImageView cardImageView = new ImageView();
                    cardImageView.setImage(cardImage);
                    cardImageView.setFitHeight(cardImage.getHeight()*0.2);
                    cardImageView.setFitWidth(cardImage.getWidth()*0.2);
                    
                    cardImageView.setPreserveRatio(true);
                    stockFoundationSpace.getChildren().add(cardImageView);
                    

                } else {
                    // issue: we need to add an icon under the stock pile ot indicate that it is the last card
                    // stockFoundationSpace.getChildren().clear();
                }
            } 

        });

        HBox.getChildren().add(stockPane);
        
        stockFoundationSpace.setPrefSize(240, 240);
        HBox.getChildren().add(stockFoundationSpace);

        HBox.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        mainPane.setTop(HBox);
        mainPane.setCenter(pane);
        


        Scene scene = new Scene(mainPane, 1440, 768);
        scene.setFill(Color.GREEN);
        
        ArrayList<ArrayList<Card>> piles = solitaire.getTableau().getCardPiles();
        int width = 0;
        int length = 0;
        
        // ImageView foundationPile = new ImageView();
        for (int i = 0; i < 4; i++) {
            StackPane foundationPile = new StackPane();
            foundationPile.setAlignment(Pos.TOP_CENTER);
            foundationPile.setPadding(new Insets(5,0,0,0));
           
            ImageView foundationImage = new ImageView();
            Image image = new Image("file:demo/src/CardPNGs/foundationspace.png");
            
            foundationImage.setImage(image);
            foundationImage.setUserData("foundationpile" + (i+1));
            foundationPile.getChildren().add(foundationImage);
            HBox.getChildren().add(foundationPile);

            HBox internalHBox = new HBox();
            internalHBox.setPrefSize(30,240);
            HBox.getChildren().add(internalHBox);
        }

        for (ArrayList<Card> pile: piles) {
            length = 0;
            ColumnConstraints column1 = new ColumnConstraints(130);
            pane.getColumnConstraints().addAll(column1); 
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
