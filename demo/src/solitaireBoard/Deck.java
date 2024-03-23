package solitaireBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import solitaireBoard.EnumBoardPosition.BoardPosition;
import solitaireBoard.EnumCards.*;

/* Used to build a deck of cards that will be distributed amongst table and stock
 * 
 * 
 */

public class Deck {
	private ArrayList<Card> deckOfCards = new ArrayList<>();
	private HashMap<String, Card> imageAndCard = new HashMap<String, Card>();
	private HashMap<String, Card> cardNameAndCard = new HashMap<>();
	/*
	 * Creates a deck of cards by first creating cards and adding them to
	 * an ArrayList and HashMap
	 */
	public Deck() {
		String templateName = "_of_";
		String pngName = "";
		for (int i = 0; i < 4; i++) {
			String symbolFileString = "";
			symbol symbolName;
			symbolName = symbol.DIAMOND;
			symbolFileString = "diamonds";
			if (i == 1) {
				symbolName = symbol.HEART;
				symbolFileString = "hearts";
			} else if (i == 2) {
				symbolName = symbol.CLUB;
				symbolFileString = "clubs";
			} else if (i == 3) {
				symbolName = symbol.SPADE;
				symbolFileString = "spades";
			}

			for (int j = 1; j < 14; j++) {
				pngName = j + templateName + symbolFileString;
				Card addedCard = new Card(j, symbolName, pngName);
				deckOfCards.add(addedCard);
				imageAndCard.put(pngName, addedCard);
				cardNameAndCard.put(addedCard.getCardName(), addedCard);
			}
		}
	}

	/*
	 * Distributes the cards to tableau and stock, reveals the first
	 * card of tableau and reveals all stock cards
	 * @param tableau The tableau receiving the distributed cards
	 * @param stock The stock receiving the distributed cards
	 */
	public void distributeDeck(Tableau tableau, Stock stock) {
		Collections.shuffle(deckOfCards);

		int count = 0;
		for (int i = 1; i < 8; i++) {
			for (int j = i; j > 0; j--) {
				tableau.getPile(i - 1).add(deckOfCards.get(count));
				if (j == 1) {
					deckOfCards.get(count).setCardRevealed(true);
				}
				count++;
			}

		}
		for (int i = count; i < 52; i++) {
			stock.getPileStock().add(deckOfCards.get(count));
			deckOfCards.get(count).setCardRevealed(true);
			count++;
		}
	}

	/*
	 * For testing, reveals all cards in tableau and stock
	 * @param tableau The tableau receiving the distributed revealed cards
	 * @param stock The stock receiving the distributed revealed cards
	 */
	public void distributeAdminDeck(Tableau tableau, Stock stock) {
		// Collections.shuffle(deckOfCards);

		int count = 0;
		for (int i = 1; i < 8; i++) {
			for (int j = i; j > 0; j--) {
				tableau.getPile(i - 1).add(deckOfCards.get(count));
				if (j == 1) {
					deckOfCards.get(count).setCardRevealed(true);
					
				}
				count++;
			}

		}
		for (int i = count; i < 52; i++) {
			stock.getPileStock().add(deckOfCards.get(count));
			
			deckOfCards.get(count).setCardRevealed(true);
			count++;
		}
	}

	public ArrayList<Card> getDeckOfCards() {
		return deckOfCards;
	}

	public HashMap<String, Card> getImageAndCard() {
		return imageAndCard;
	}

	public HashMap<String,Card> getCardNameAndCard() {
		return cardNameAndCard;
	}
}
