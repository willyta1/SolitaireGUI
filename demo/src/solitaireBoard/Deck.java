package solitaireBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import solitaireBoard.EnumCards.*;

/* Used to build a deck of cards that will be distributed amongst table and stock
 * 
 * 
 */

public class Deck {
	private ArrayList<Card> deckOfCards = new ArrayList<>();
	private HashMap<String, Card> imageAndCard = new HashMap<String, Card>();

	public Deck() {
		String templateName = "_of_";
		String pngName = "";
		for (int i = 0; i < 4; i++) {
			String symbolFileString = "";
			symbol symbolName;
			symbolName = symbol.Diamond;
			symbolFileString = "diamonds";
			if (i == 1) {
				symbolName = symbol.Heart;
				symbolFileString = "hearts";
			} else if (i == 2) {
				symbolName = symbol.Club;
				symbolFileString = "clubs";
			} else if (i == 3) {
				symbolName = symbol.Spade;
				symbolFileString = "spades";
			}

			for (int j = 1; j < 14; j++) {
				pngName = j + templateName + symbolFileString;
				Card addedCard = new Card(j, symbolName, pngName);
				deckOfCards.add(addedCard);
				imageAndCard.put(pngName, addedCard);
			}
		}
	}

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
			stock.getStock().add(deckOfCards.get(count));
			deckOfCards.get(count).setCardRevealed(true);
			count++;
		}
	}

	// hmm maybe i could combine both distributions?
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
			stock.getStock().add(deckOfCards.get(count));
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

}
