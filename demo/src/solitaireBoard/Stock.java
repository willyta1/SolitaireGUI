package solitaireBoard;

import java.util.ArrayList;

public class Stock {
	/*
	 * Card pile that did not go into the Tableau You can cycle through cards
	 * 
	 * 24 cards in Stock
	 */

	private ArrayList<Card> pileStock;
	private int counter;

	public Stock() {
		pileStock = new ArrayList<Card>();
		counter = -1;
	}

	public void printCurrentCard() {
		if (pileStock.size() > 0 && counter <= pileStock.size() - 1 && counter > -1) {
			System.out.println(pileStock.get(counter));
		} else {
			System.out.println("Blank");
			counter = -1;
		}

	}

	public Card getCurrentCard() {
		if (pileStock.size() > -1 && counter <= pileStock.size() - 1 && counter > -1) {
			return pileStock.get(counter);
		}
		return null;

	}

	public void cycleCards() {
		if (counter > pileStock.size() - 1) {
			counter = -1;
		} else {
			counter++;
			printCurrentCard();
		}
		// issues: stock should not show cards until the first call or when it goes past
		// the last card in the stock
	}

	public void addToTableau(int newPileIndex, Tableau tableau) {

		if (newPileIndex >= 1 && newPileIndex <= 7 && counter > -1 && counter <= pileStock.size() - 1) {
			Card currentCard = pileStock.get(counter);
			if (currentCard.getRevealed()) {
				if (tableau.getPile(newPileIndex - 1).size() == 0 && currentCard.getNumValue() == 13) {
					tableau.getPile(newPileIndex - 1).add(currentCard);
					pileStock.remove(counter);
					counter--;
				} else if (tableau.getPile(newPileIndex - 1).size() > 0
						&& tableau.getPile(newPileIndex - 1).get(tableau.getPile(newPileIndex - 1).size() - 1)
								.getNumValue() == currentCard.getNumValue() + 1) {
					if (tableau.getPile(newPileIndex - 1)
							.get(tableau.getPile(newPileIndex - 1).size() - 1).color != currentCard.color) {
						tableau.getPile(newPileIndex - 1).add(currentCard);
						pileStock.remove(counter);
						counter--;
					}
				}
			}

		}

	}

	public void addToFoundations(Foundations foundation) {
		if (findAddToFoundation(getCurrentCard(), foundation)) {
			int getIndex = findFoundationIndex(pileStock.get(counter).getCardName());
			foundation.getFoundationPile(getIndex).add(getCurrentCard());
			pileStock.remove(counter);
		}
	}

	public int findAddToTableau(Card card, Tableau tableau) {
		int pileNum = 0;
		for (ArrayList<Card> pile : tableau.getCardPiles()) {
			if (card.getRevealed()) {
				if (card.getNumValue() == 13 && pile.size() == 0) {
					return pileNum;
				} else if (pile.size() > 0 && card.getNumValue() == pile.get(pile.size() - 1).getNumValue() - 1
						&& card.color != pile.get(pile.size() - 1).color) {
					return pileNum;
				}
			}

			pileNum++;
		}
		return -1;
	}

	public boolean findAddToFoundation(Card card, Foundations foundation) {
		if (counter >= 0 && counter < pileStock.size()) {

			int getIndex = findFoundationIndex(pileStock.get(counter).getCardName());

			// repeated in tableau
			if (card.getRevealed()) {
				if (foundation.getFoundationPile(getIndex).isEmpty() && getCurrentCard().getNumValue() == 1) {
					return true;
				} else if (foundation.getFoundationPile(getIndex).size() > 0 && getCurrentCard() != null
						&& getCurrentCard().getNumValue() - 1 == foundation.getFoundationPile(getIndex)
								.get(foundation.getFoundationPile(getIndex).size() - 1).getNumValue()) {
					return true;

					// repeated, maybe put into method?
				}
			}

		}
		// how do i make this iterable
		return false;
	}

	public int findFoundationIndex(String cardStorageValue) {
		char symbol = cardStorageValue.charAt(cardStorageValue.length() - 1);
		int pileIndex;

		if (symbol == 'D') {
			pileIndex = 0;
		} else if (symbol == 'H') {
			pileIndex = 1;
		} else if (symbol == 'C') {
			pileIndex = 2;
		} else {
			pileIndex = 3;
		}
		return pileIndex;
	}

	@Override
	public String toString() {
		String cardNames = "";
		for (Card card : pileStock) {
			cardNames += card.getCardName() + "\n";
		}
		return cardNames;
	}

	public ArrayList<Card> getStock() {
		return pileStock;
	}
}
