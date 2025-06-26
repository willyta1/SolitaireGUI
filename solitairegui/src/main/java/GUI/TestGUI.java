package GUI;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import solitaireBoard.*;
import solitaireBoard.EnumBoardPosition.BoardPosition;

public class TestGUI extends Application {
    private Solitaire solitaire = new Solitaire();
    private Map<String, BoardPosition> cardAndBoardPositionMap = new HashMap<>();
    private Map<String, Integer> boardAndBoardPositionMap = new HashMap<>();
    private ArrayList<String> twoClicksArray = new ArrayList<>();
    private ArrayList<StackPane> tableauGUIPiles = new ArrayList<>();
    private ArrayList<StackPane> foundationGUIPiles = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMinHeight(750);
        stage.setMinWidth(950);
        Scene scene = createSolitaireBoard();
        stage.setScene(scene);
        stage.show();
        
    }

    public Scene createSolitaireBoard() {
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.TOP_CENTER);
        BorderPane mainPane = new BorderPane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        
        HBox HBox = new HBox();
        HBox.setAlignment(Pos.TOP_CENTER);
        HBox.setPrefSize(650, 160);

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
        stockPile.setPreserveRatio(true);

        stockPane.getChildren().add(stockPileEmpty);
        stockPane.getChildren().add(stockPile);
        stockPane.setAlignment(Pos.TOP_CENTER);
        stockPane.setPadding(new Insets(10,0,0,15));
        
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
                    cardAndBoardPositionMap.put(solitaire.getStock().getCurrentCard().getCardName(), BoardPosition.STOCK);
                    ImageView cardImageView = new ImageView();
                    cardImageView.setImage(cardImage);
                    cardImageView.setFitHeight(cardImage.getHeight()*0.2);
                    cardImageView.setFitWidth(cardImage.getWidth()*0.2);
                    
                    cardImageView.setPreserveRatio(true);
                    cardImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent arg0) {
                            // TODO Auto-generated method stub
                            System.out.println("AAAAAA");
                            String cardName = solitaire.getStock().getCurrentCard().getCardName();
                            if (twoClicksArray.size()==0) {
                                twoClicksArray.add(cardName);
                            }
                        }
                        
                    });
                    stockFoundationSpace.getChildren().add(cardImageView);
                    

                } 
            } 

        });

        HBox.getChildren().add(stockPane);
        
        stockFoundationSpace.setPrefSize(240, 160);
        HBox.getChildren().add(stockFoundationSpace);

        HBox.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        mainPane.setTop(HBox);
        mainPane.setCenter(flowPane);
        


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
            String foundationPileString = "foundationpile" + (i+1);
            boardAndBoardPositionMap.put(foundationPileString, i);
            foundationGUIPiles.add(foundationPile);
            foundationPile.getChildren().add(foundationImage);
            foundationPile.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    
                    String foundationPileName = foundationPileString;
                    
                    if(twoClicksArray.size() == 1) {
                        twoClicksArray.add(foundationPileName);
                    }
                    handleTwoClicks();
                    System.out.println(foundationPileName);

                }
                
            });
            HBox.getChildren().add(foundationPile);

            HBox internalHBox = new HBox();
            internalHBox.setPrefSize(30,240);
            HBox.getChildren().add(internalHBox);
        }

        
        int tableauPileNumber = 8;
        for (ArrayList<Card> pile: piles) {
            int len = 10;
            StackPane tableauPile = new StackPane();
            tableauPile.setAlignment(Pos.TOP_CENTER);
            for (Card card: pile) {
                Image image = card.getImage();
                ImageView imageView = new ImageView(card.getImage());
                if(card.getRevealed()) {
                    
                    imageView.setFitWidth(image.getWidth()*0.2);
                    imageView.setFitHeight(image.getHeight()*0.2);
                    imageView.setPreserveRatio(true);
                    cardAndBoardPositionMap.put(card.getCardName(), BoardPosition.TABLEAU);
                    imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    

                        @Override
                        public void handle(MouseEvent arg0) {
                            //plan: use image to compare cards and strings
                            Card cardCopy = card;
                            if (twoClicksArray.size() <= 1) {
                                twoClicksArray.add(card.getCardName());
                            }
                            handleTwoClicks();
                            
                            System.out.println("B");
                            
                        }
                        
                    });
                } else {
                    image = new Image("file:demo/src/CardPNGs/cardBack.png");
                    imageView = new ImageView(image);
                    imageView.setFitWidth(image.getWidth()*0.152);
                    imageView.setFitHeight(image.getHeight()*0.152);
                }
                
                imageView.setPreserveRatio(true);
                if(card.getRevealed()) {
                    StackPane.setMargin(imageView, new Insets(len,15,0,15));
                    len+= 25;
                } else {
                    StackPane.setMargin(imageView, new Insets(len,15,0,15));
                    len+= 15;
                }
                
                tableauPile.getChildren().addAll(imageView);
            
                
            }
            boardAndBoardPositionMap.put("tableaupile" + tableauPileNumber, tableauPileNumber);
            
            tableauPileNumber++;
            tableauPile.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    
                    System.out.println("A");
                }
                
            });
            tableauGUIPiles.add(tableauPile);
            flowPane.getChildren().add(tableauPile);

        }
        

        return scene;
    }

    public void handleTwoClicks() {
        if (twoClicksArray.size() == 2) {
            if (cardAndBoardPositionMap.get(twoClicksArray.get(0)).equals(BoardPosition.STOCK)) {
                if (boardAndBoardPositionMap.get(twoClicksArray.get(1)) != null && 
                boardAndBoardPositionMap.get(twoClicksArray.get(1)) <= 4) {
                    solitaire.getStock().addToFoundations(solitaire.getFoundations(),  
                    boardAndBoardPositionMap.get(twoClicksArray.get(1)));

                    tableauGUIPiles.get(6).getChildren().remove(6);
                    System.out.println("EEEE");
                    // here i was gonna try implementing updating the stack panes 2/28
                    // idea: put the stack panes and foundations in an array, update the respective
                    // one that got updated 
                    

                } else if(boardAndBoardPositionMap.get(twoClicksArray.get(1)) != null && 
                boardAndBoardPositionMap.get(twoClicksArray.get(1)) >= 8) {
                    solitaire.getStock().addToTableau(boardAndBoardPositionMap.get(twoClicksArray.get(1))-7, solitaire.getTableau());
                } else if (cardAndBoardPositionMap.get(twoClicksArray.get(1)) != null && cardAndBoardPositionMap.get(twoClicksArray.get(1)).equals(BoardPosition.TABLEAU)) {
                    System.out.print("adgh");
                    solitaire.getStock().addToTableau(boardAndBoardPositionMap.get(twoClicksArray.get(1))-7, solitaire.getTableau());

                }
            }
        }
    }

}
