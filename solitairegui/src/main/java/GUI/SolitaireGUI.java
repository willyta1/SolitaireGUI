package GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import solitaireBoard.*;
import solitaireBoard.EnumBoardPosition.BoardPosition;

public class SolitaireGUI extends Application {
    private Solitaire solitaire = new Solitaire();
    private Map<String, BoardPosition> cardAndBoardPositionMap = new HashMap<>();
    private Map<String, Integer> boardAndBoardPositionMap = new HashMap<>();
    private ArrayList<String> twoClicksArray = new ArrayList<>();

    private ArrayList<StackPane> foundationGUIPiles = new ArrayList<>();
    private ArrayList<StackPane> tableauGUIPiles = new ArrayList<>();
    private Stage stage;
    private HBox HBox;
    private FlowPane flowPane;
    private StackPane stockDrawnSpace;
    private int len; 
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMinHeight(750);
        stage.setMinWidth(950);
        this.stage = stage;
        Scene scene = createSolitaireBoard(stage);
        stage.setScene(scene);
        stage.show();
    }

    public Scene createSolitaireBoard(Stage stage) {
        Scene scene = refresh(stage);
        return scene;
    }

    public ImageView buildStockGUICard(Image cardImage) {
        ImageView cardImageView = new ImageView();
        cardImageView.setImage(cardImage);
        cardImageView.setFitHeight(cardImage.getHeight() * 0.2);
        cardImageView.setFitWidth(cardImage.getWidth() * 0.2);

        cardImageView.setPreserveRatio(true);
        cardImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("AAAAAA");
                if (solitaire.getStock().getCurrentCard() != null) {
                    String cardName = solitaire.getStock().getCurrentCard().getCardName();
                    twoClicksArray.clear();
                    if (twoClicksArray.size() == 0) {
                        twoClicksArray.add(cardName);
                    }
                }
            }
        });
        return cardImageView;
    }

    public ImageView buildTableauGUICard(Card card, int len) {
        Image image = card.getImage();
        ImageView imageView = new ImageView(card.getImage());
        if (card.getRevealed()) {
            imageView.setFitWidth(image.getWidth() * 0.2);
            imageView.setFitHeight(image.getHeight() * 0.2);
            imageView.setPreserveRatio(true);
            cardAndBoardPositionMap.put(card.getCardName(), BoardPosition.TABLEAU);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                            
                    if (twoClicksArray.size() <= 1) {
                        twoClicksArray.add(card.getCardName());
                    }
                    if (twoClicksArray.size() == 2) {
                        handleTwoClicks(stage);
                    }
                    System.out.println("B");
                }
            });
        } else {
            image = new Image("file:" + Card.relativePath + "/cardBack.png");
            imageView = new ImageView(image);
            imageView.setFitWidth(image.getWidth() * 0.152);
            imageView.setFitHeight(image.getHeight() * 0.152);
        }

        imageView.setPreserveRatio(true);
        return imageView;
    }

    public void addTableauMouseClickEvent(int num) {
        if (tableauGUIPiles.get(num).getChildren().size() == 0) {
            EventHandler event = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    if (twoClicksArray.size() == 1) {
                        twoClicksArray.add("tableaupile" + (num+1));
                    }
                    if (twoClicksArray.size() == 2) {
                        handleTwoClicks(stage);
                    }
                    System.out.println("A");
                }
            };
            tableauGUIPiles.get(num).setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
            tableauGUIPiles.get(num).setOnMouseClicked(event);
        } else {
            tableauGUIPiles.get(num).setOnMouseClicked(null);
            tableauGUIPiles.get(num).setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        }
    }

    public void updateTableauPiles(int pileNum) {
        int pileIndex = pileNum-1;
        ArrayList<Card> pile = solitaire.getTableau().getCardPiles().get(pileIndex);
        tableauGUIPiles.get(pileIndex).getChildren().clear();
        len = 10;
        tableauGUIPiles.get(pileIndex).getChildren().clear();
        for (Card card : pile) {
            
            ImageView imageView = buildTableauGUICard(card, len);
            if (card.getRevealed()) {
                StackPane.setMargin(imageView, new Insets(len, 0, 0, 0));
                len += 25;
            } else {
                StackPane.setMargin(imageView, new Insets(len, 0, 0, 0));
                len += 15;
            }
            tableauGUIPiles.get(pileIndex).getChildren().addAll(imageView);
        }
        addTableauMouseClickEvent(pileIndex);
        
        tableauGUIPiles.get(pileIndex).setAlignment(Pos.TOP_CENTER);
        if(flowPane.getChildren().size() < 7) {
            flowPane.getChildren().add(tableauGUIPiles.get(pileIndex));
        }
    }

    public void updateTableau() {
        for (int i = 0; i < solitaire.getTableau().getCardPiles().size(); i++) {
            ArrayList<Card> pile = solitaire.getTableau().getCardPiles().get(i);
            len = 10;
            int numPile = i + 1;

            if(tableauGUIPiles.size() < 7) {
                boardAndBoardPositionMap.put("tableaupile" + (numPile), i + 8);
                StackPane tableauPile = new StackPane();
                tableauGUIPiles.add(tableauPile);
                tableauPile.setMinWidth(solitaire.getDeck().getCardNameAndCard().get("1D").getImage().getWidth() * 0.2);
            }

            tableauGUIPiles.get(i).getChildren().clear();
            for (Card card : pile) {
                ImageView imageView = buildTableauGUICard(card, len);
                if (card.getRevealed()) {
                    StackPane.setMargin(imageView, new Insets(len, 0, 0, 0));
                    len += 25;
                } else {
                    StackPane.setMargin(imageView, new Insets(len, 0, 0, 0));
                    len += 15;
                }
                tableauGUIPiles.get(i).getChildren().addAll(imageView);
            }
            addTableauMouseClickEvent(i);
            
            tableauGUIPiles.get(i).setAlignment(Pos.TOP_CENTER);
            if(flowPane.getChildren().size() < 7) {
                flowPane.getChildren().add(tableauGUIPiles.get(i));
            }
        }
    }

    public void updateFoundations() {
        for (int i = 0; i < 4; i++) {
            if(foundationGUIPiles.size() < 4 || !HBox.getChildren().contains(foundationGUIPiles.get(i))) {
                StackPane foundationPile = new StackPane();
                foundationPile.setAlignment(Pos.TOP_CENTER);
                foundationPile.setPadding(new Insets(5, 0, 0, 0));
                foundationGUIPiles.add(foundationPile);
                HBox.getChildren().add(foundationGUIPiles.get(i));
                HBox internalHBox = new HBox();
                internalHBox.setPrefSize(30, 240);
                HBox.getChildren().add(internalHBox);
            }
            
            foundationGUIPiles.get(i).getChildren().clear();
            ImageView foundationImage = new ImageView();
            Image image = new Image("file:" + Card.relativePath + "/foundationspace.png");
            foundationImage.setImage(image);

            String foundationPileString = "foundationpile" + (i + 1);
            boardAndBoardPositionMap.put(foundationPileString, i);
            foundationGUIPiles.get(i).getChildren().add(foundationImage);

            for (Card card : solitaire.getFoundations().getFoundationPile(i)) {
                Image cardImage = card.getImage();
                ImageView imageView = new ImageView();
                imageView.setImage(cardImage);
                imageView.setFitHeight(cardImage.getHeight() * 0.2);
                imageView.setFitWidth(cardImage.getWidth() * 0.2);

                cardAndBoardPositionMap.put(card.getCardName(), BoardPosition.FOUNDATIONS);
                imageView.setPreserveRatio(true);
                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                        System.out.println("Foundation");
                        Card cardCopy = card;

                        if (twoClicksArray.size() <= 1) {
                            twoClicksArray.add(cardCopy.getCardName());
                        }
                        if (twoClicksArray.size() == 2) {
                            handleTwoClicks(stage);
                        }
                    }
                });
                StackPane.setMargin(imageView, new Insets(8, 0, 0, 0));
                foundationGUIPiles.get(i).getChildren().add(imageView);
            }
            int num = i;
            foundationGUIPiles.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    if ( foundationGUIPiles.get(num).getChildren().size() == 1) {
                        String foundationPileName = foundationPileString;
                        if (twoClicksArray.size() == 1) {
                            twoClicksArray.add(foundationPileName);
                        }
                        if (twoClicksArray.size() >= 2) {
                            handleTwoClicks(stage);
                        }
                        System.out.println(foundationPileName);
                    }
                }
            });

        }
    }

    public void updateStockDrawnPile() {
        if (solitaire.getStock().getDrawnPileStock() != null) {
            stockDrawnSpace.getChildren().clear();
            for (Card card : solitaire.getStock().getDrawnPileStock()) {
                Image cardImage = card.getImage();
                System.out.println(solitaire.getStock().getCounter());
                if (solitaire.getStock().getCurrentCard() != null) {
                    cardAndBoardPositionMap.put(solitaire.getStock().getCurrentCard().getCardName(),
                            BoardPosition.STOCK);
                    ImageView cardImageView = buildStockGUICard(cardImage);
                    stockDrawnSpace.getChildren().add(cardImageView);
                }
            }
        }
    }

    public void addStockMouseclickEvent(ImageView stockPile) {
        if (solitaire.getStock().getPileStock().size() != 0) {
            stockPile.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    // TODO Auto-generated method stub
                    solitaire.getStock().cycleCards();
                    System.out.println("Current card: " + solitaire.getStock().getCurrentCard());
                    System.out.println("Current counter: " + solitaire.getStock().getCounter());
                    if (solitaire.getStock().getCounter() == solitaire.getStock().getPileStock().size() - 1) {
                        stockPile.setVisible(false);
                    }

                    if (solitaire.getStock().getCurrentCard() != null) {
                        Image cardImage = solitaire.getStock().getCurrentCard().getImage();
                        cardAndBoardPositionMap.put(solitaire.getStock().getCurrentCard().getCardName(),
                                BoardPosition.STOCK);
                        ImageView cardImageView = new ImageView();
                        cardImageView.setImage(cardImage);
                        cardImageView.setFitHeight(cardImage.getHeight() * 0.2);
                        cardImageView.setFitWidth(cardImage.getWidth() * 0.2);
                        cardImageView.setPreserveRatio(true);
                        cardImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                            @Override
                            public void handle(MouseEvent arg0) {
                                // TODO Auto-generated method stub
                                System.out.println("AAAAAA");
                                System.out.println(solitaire.getStock().getCurrentCard().getCardName());
                                String cardName = solitaire.getStock().getCurrentCard().getCardName();
                                twoClicksArray.clear();
                                if (twoClicksArray.size() == 0) {
                                    twoClicksArray.add(cardName);
                                }
                            }
                        });
                        stockDrawnSpace.getChildren().add(cardImageView);
                    }
                }
            });
        } else {
            stockPile.setVisible(false);
        }
    }

    public Scene refresh(Stage stage) {
        // flowPane holds the Tableau
        FlowPane flowPane = new FlowPane();
        this.flowPane = flowPane;
        flowPane.setHgap(30);
        flowPane.setAlignment(Pos.TOP_CENTER);
        BorderPane mainPane = new BorderPane();
        mainPane.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        HBox HBox = new HBox();
        HBox.setAlignment(Pos.TOP_CENTER);
        HBox.setPrefSize(650, 160);
        this.HBox = HBox;
        StackPane stockPane = new StackPane();

        ImageView stockPileEmpty = new ImageView();
        Image stockPileEmptyImage = new Image("file:" + Card.relativePath + "/stockspaceempty.png");
        stockPileEmpty.setFitHeight(stockPileEmptyImage.getHeight() * 0.9);
        stockPileEmpty.setFitWidth(stockPileEmptyImage.getWidth() * 0.87);
        stockPileEmpty.setImage(stockPileEmptyImage);

        ImageView stockPile = new ImageView();
        Image stockPileImage = new Image("file:" + Card.relativePath +"/cardBack.png");

        StackPane stockDrawnSpace = new StackPane();
        this.stockDrawnSpace = stockDrawnSpace;
        stockDrawnSpace.setAlignment(Pos.TOP_LEFT);
        stockDrawnSpace.setPadding(new Insets(10, 0, 0, 10));
        
        updateStockDrawnPile( );

        stockPile.setImage(stockPileImage);
        stockPile.setFitWidth(stockPileImage.getWidth() * 0.152);
        stockPile.setFitHeight(stockPileImage.getHeight() * 0.152);
        stockPile.setPreserveRatio(true);

        stockPane.getChildren().add(stockPileEmpty);
        stockPane.getChildren().add(stockPile);
        stockPane.setAlignment(Pos.TOP_CENTER);
        stockPane.setPadding(new Insets(10, 0, 0, 15));
        stockPileEmpty.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                // TODO Auto-generated method stub
                if (!stockPile.isVisible()) {
                    solitaire.getStock().cycleCards();
                    stockPile.setVisible(true);
                    stockDrawnSpace.getChildren().clear();
                }
            }
        });
        if (solitaire.getStock().getCounter() == solitaire.getStock().getPileStock().size() - 1) {
            stockPile.setVisible(false);
        }
        addStockMouseclickEvent(stockPile);

        HBox.getChildren().add(stockPane);
        stockDrawnSpace.setPrefSize(240, 160);
        HBox.getChildren().add(stockDrawnSpace);
        HBox.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        mainPane.setTop(HBox);
        mainPane.setCenter(flowPane);

        Button restartButton = new Button("Restart");
        restartButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    restartGame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        });
        mainPane.setBottom(restartButton);

        Scene scene = new Scene(mainPane, 1440, 768);
        scene.setFill(Color.GREEN);

        updateFoundations();
        updateTableau();
        
        stage.setScene(scene);
        return scene;

    }

    public void handleTwoClicks(Stage stage) {
        double initTime = System.nanoTime();
        if (twoClicksArray.size() == 2) {
            Card copyMovedCard = (Card) solitaire.getDeck().getCardNameAndCard().get(twoClicksArray.get(0));
            if (cardAndBoardPositionMap.get(twoClicksArray.get(0)).equals(BoardPosition.STOCK)) {
                // System.out.println(twoClicksArray.get(1));
                handleStockMove(copyMovedCard);
            } else if (cardAndBoardPositionMap.get(twoClicksArray.get(0)).equals(BoardPosition.TABLEAU)) {
                handleTableauMove(copyMovedCard);
            } else if (cardAndBoardPositionMap.get(twoClicksArray.get(0)).equals(BoardPosition.FOUNDATIONS)) {
                handleFoundationMove(copyMovedCard);
            }
        }
        twoClicksArray.clear();
        double endTime = System.nanoTime();
        System.out.println((endTime - initTime) / 1000000000);
        winScreen(stage);
    }
    public void winScreen(Stage stage) {
        if(solitaire.checkWin()) {
            
            BorderPane pane = new BorderPane();
            
            FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
            flowPane.setVgap(10);
            
            flowPane.setAlignment(Pos.CENTER);
            Scene winScene = new Scene(pane, 1440, 768);

            Label winLabel = new Label("You Win!");
            winLabel.setAlignment(Pos.CENTER);
            winLabel.setMinSize(200, 200);
            winLabel.setFont(new Font(150.0));

            HBox restartGameBox = new HBox();
            restartGameBox.setAlignment(Pos.CENTER);
            Button restartGame = new Button("Restart");
            restartGame.setMinSize(100, 50);
            restartGameBox.getChildren().add(restartGame);
            restartGame.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                    try {
                        restartGame();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                
            });

            flowPane.getChildren().add(winLabel);
            flowPane.getChildren().add(restartGameBox);
            pane.setCenter(flowPane);
            
            pane.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            restartGame.setVisible(true);

            stage.setScene(winScene);

        }
    }

    public void restartGame() throws Exception {
        solitaire = new Solitaire();
        stage.setScene(createSolitaireBoard(stage));

    }

    public void handleStockMove(Card movedCard) {
                    if (boardAndBoardPositionMap.get(twoClicksArray.get(1)) != null &&
                boardAndBoardPositionMap.get(twoClicksArray.get(1)) <= 4) {
            int foundationIndex = boardAndBoardPositionMap.get(twoClicksArray.get(1));
            if (solitaire.getStock().findAddToFoundation(movedCard, foundationIndex,
                    solitaire.getFoundations())) {
                solitaire.getStock().addToFoundations(solitaire.getFoundations(), foundationIndex);
            }
            updateStockDrawnPile();
            updateFoundations();

        } else if (cardAndBoardPositionMap.get(twoClicksArray.get(1)) != null &&
                cardAndBoardPositionMap.get(twoClicksArray.get(1)).equals(BoardPosition.TABLEAU)) {
            System.out.println("adgh");
            int pileNum = solitaire.getTableau().findPileWithCard(twoClicksArray.get(1));
            if (solitaire.getStock().findIndexAddToTableau(movedCard, pileNum, solitaire.getTableau())) {
                solitaire.getStock().addToTableau(pileNum, solitaire.getTableau());
            }
            updateStockDrawnPile();
            updateTableauPiles(pileNum);

        } else if (cardAndBoardPositionMap.get(twoClicksArray.get(1)) != null &&
                cardAndBoardPositionMap.get(twoClicksArray.get(1)).equals(BoardPosition.FOUNDATIONS)) {
            if (solitaire.getStock().findAddToFoundation(movedCard, solitaire.getFoundations())) {
                Card destinationCard = (Card) solitaire.getDeck().getCardNameAndCard()
                        .get(twoClicksArray.get(1));
                int foundationIndex = destinationCard.findFoundationIndex();
                solitaire.getStock().addToFoundations(solitaire.getFoundations(), foundationIndex);
            }
            updateStockDrawnPile();
            updateFoundations();
        } else if (boardAndBoardPositionMap.get(twoClicksArray.get(1)) != null
                && boardAndBoardPositionMap.get(twoClicksArray.get(1)) >= 8) {
            int tableauPile = boardAndBoardPositionMap.get(twoClicksArray.get(1));
            solitaire.getStock().addToTableau(tableauPile - 7, solitaire.getTableau());
            updateTableauPiles(tableauPile - 7);
            updateStockDrawnPile();
        }
    }

    public void handleTableauMove(Card movedCard) {
        if (boardAndBoardPositionMap.get(twoClicksArray.get(1)) != null &&
                boardAndBoardPositionMap.get(twoClicksArray.get(1)) <= 4) {
            int foundationIndex = boardAndBoardPositionMap.get(twoClicksArray.get(1));
            int tableauPile = solitaire.getTableau().findPileWithCard(movedCard.getCardName());
            if (solitaire.getTableau().findAddToFoundation(movedCard.getCardName(), tableauPile,
                    foundationIndex, solitaire.getFoundations())) {
                solitaire.getTableau().addToFoundations(movedCard.getCardName(), tableauPile,
                        foundationIndex, solitaire.getFoundations());
            }
            updateFoundations();
            updateTableauPiles(tableauPile);
        } else if (cardAndBoardPositionMap.get(twoClicksArray.get(1)) != null
                && cardAndBoardPositionMap.get(twoClicksArray.get(1)).equals(BoardPosition.FOUNDATIONS)) {
            Card copyDestinationCard = (Card) solitaire.getDeck().getCardNameAndCard()
                    .get(twoClicksArray.get(1));
            int tableauPileIndex = solitaire.getTableau().findPileWithCard(movedCard.getCardName());
            int foundationIndex = copyDestinationCard.findFoundationIndex();
            solitaire.getTableau().addToFoundations(movedCard.getCardName(), tableauPileIndex,
                    foundationIndex, solitaire.getFoundations());
            updateFoundations();
            updateTableauPiles(tableauPileIndex);

        } else if (cardAndBoardPositionMap.get(twoClicksArray.get(1)) != null
                && cardAndBoardPositionMap.get(twoClicksArray.get(1)).equals(BoardPosition.TABLEAU)) {
            Card copyDestinationCard = (Card) solitaire.getDeck().getCardNameAndCard()
                    .get(twoClicksArray.get(1));
            int destPileIndex = solitaire.getTableau().findPileWithCard(copyDestinationCard.getCardName());
            int movePileIndex = solitaire.getTableau().findPileWithCard(movedCard.getCardName());

            if (solitaire.getTableau().findTableMove(movedCard.getCardName(), movePileIndex,
                    destPileIndex)) {
                solitaire.getTableau().movePileCards(movedCard.getCardName(), movePileIndex, destPileIndex);
                updateTableauPiles(destPileIndex);
                updateTableauPiles(movePileIndex);
            }
        } else if (boardAndBoardPositionMap.get(twoClicksArray.get(1)) != null &&
                boardAndBoardPositionMap.get(twoClicksArray.get(1)) >= 8) {
            int tableauPile = boardAndBoardPositionMap.get(twoClicksArray.get(1));
            int movePileIndex = solitaire.getTableau().findPileWithCard(movedCard.getCardName());
            solitaire.getTableau().movePileCards(movedCard.getCardName(), movePileIndex, tableauPile - 7);
            updateTableauPiles(tableauPile - 7);
            updateTableauPiles(movePileIndex);
        }
    }

    public void handleFoundationMove(Card movedCard) {
        if(cardAndBoardPositionMap.get(twoClicksArray.get(1)).equals(BoardPosition.TABLEAU)) {
            // Card foundationCard = (Card) solitaire.getDeck().getCardNameAndCard().get(twoClicksArray.get(0));
            Card tableauCard = (Card) solitaire.getDeck().getCardNameAndCard().get(twoClicksArray.get(1));
            int tableauPileIndex = solitaire.getTableau().findPileWithCard(tableauCard.getCardName());
            int foundationIndex = movedCard.findFoundationIndex();

            if(solitaire.getFoundations().findAddToTableau(foundationIndex, tableauPileIndex, solitaire.getTableau())) {
                solitaire.getFoundations().addToTableau(foundationIndex, tableauPileIndex, solitaire.getTableau());
            }
            updateTableauPiles(tableauPileIndex);
            updateFoundations();

        } 
    }

}
