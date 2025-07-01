package solitaireBoard;

import java.util.*;

import solitaireBoard.EnumBoardPosition.BoardPosition;
import solitaireBoard.EnumCards.*;

public class Tableau {
	/*
	 * Tableau holds seven piles of cards Only the top card of the initial piles of
	 * cards are revealed As cards are moved off the pile, the card below will be
	 * revealed Each pile requires alternating colors in order Each pile is ordered
	 * in descending order of rankings (no duplicate rankings) The card at bottom of
	 * pile is the highest ranking card You can move stacks of cards onto another if
	 * following the above rules
	 * 
	 * From left to right: 1,2,3,4,5,6,7 cards for each pile 28 cards in Tableau
	 * 
	 */

	/*
	 * INITIAL PLANS Question 1: How to store each card pile? Question 2: Do I need
	 * to make a separate card pile for revealed and not revealed cards? Thoughts on
	 * 2: Since we only have to show the top card, we can probably only use the
	 * "top" card and substitute hidden cards with placeholder values Thoughts on 1:
	 * Store in a resizable data structure so we can call "pop" or something else
	 * Thoughts on 1: How do we move a set of items and keep them in order?
	 */


	// public Deck cardDeck = new Deck();


	private ArrayList<ArrayList<Card>> cardPiles = new ArrayList<ArrayList<Card>>();

	public Tableau() {
		for (int i = 0; i < 7; i++) {
			cardPiles.add(new ArrayList<Card>());
		}
	}


	public void movePileCards(String cardName, int pileIndex, int newPileIndex) {
		if (findTableMove(cardName, pileIndex, newPileIndex)) {

			int cardIndex = getCardIndexFromPile(cardName, pileIndex);
			Card card = getPile(pileIndex - 1).get(cardIndex);
			if (card.getRevealed()) {
				shiftCards(pileIndex, newPileIndex, cardIndex);
			}

		}
	}

	private void shiftCards(int pileIndex, int newPileIndex, int cardIndex) {
		if (cardIndex != 0) {
			getPile(pileIndex - 1).get(cardIndex - 1).setCardRevealed(true);
		}

		while (getPile(pileIndex - 1).size() > cardIndex) {

			Card card = getPile(pileIndex - 1).get(cardIndex);
			getPile(pileIndex - 1).remove(cardIndex);
			getPile(newPileIndex - 1).add(card);
		}
	}

	public int getCardIndexFromPile(String cardName, int pileIndex) {
		int cardIndex = 0;
		if (pileIndex >= 1 && pileIndex <= 7) {
			for (int i = 0; i < getPile(pileIndex - 1).size(); i++) {
				if (getPile(pileIndex - 1).get(i).getCardName().equals(cardName)) {

					cardIndex = i;
					return cardIndex;

				}
			}
		}
		return -1;
	}

	public void addCardInOrder(int pileIndex, Card card) {
		if (pileIndex >= 1 && pileIndex <= 7) {
			if (getPile(pileIndex - 1).size()>= 1) {
				Card cardCopy = getPile(pileIndex - 1).get(getPile(pileIndex - 1).size() - 1);
				if (cardCopy.getNumValue() == card.getNumValue() + 1) {
					if (cardCopy.color != card.color) {
						getPile(pileIndex - 1).add(card);
					}
				}
			}

		}

	}


	public boolean findSignificantBoardMove() {

		for (int j = 0; j < cardPiles.size(); j++) {
			for (int k = getPile(j).size() - 1; k > -1; k--) {
				for (int i = 0; i < 7; i++) {
					Card card = getPile(j).get(k);
					if (j != i && findTableMove(card.getCardName(), j + 1, i + 1)) {
						if (k >= 1 && !getPile(j).get(k - 1).getRevealed()) {
							return true;
						} else if (getPile(j).size() == 1) {
							return true;
						}

					}
				}
			}
		}
		return false;

	}



	public boolean findTableMove(String cardName, int pileIndex, int newPileIndex) {
		boolean foundCard = false;
		int cardIndex = 0;

		if (pileIndex >= 1 && pileIndex <= 7) {
			for (int i = 0; i < getPile(pileIndex - 1).size(); i++) {
				if (getPile(pileIndex - 1).get(i).getCardName().equals(cardName)) {
					foundCard = true;
					cardIndex = i;
					break;
				}
			}

			if (foundCard && getPile(pileIndex - 1).get(cardIndex).getRevealed()) {
				// is the card that is being moved in the right pile?
				// if so, copy the index of it
				int cardValue = getPile(pileIndex - 1).get(cardIndex).getNumValue();
				color cardColor;
				cardColor = getPile(pileIndex - 1).get(cardIndex).color;

				if (getPile(newPileIndex - 1).size() == 0 && (cardName.equals("13D") || cardName.equals("13H")
						|| cardName.equals("13C") || cardName.equals("13S"))) {
					return true;

				} else if (getPile(newPileIndex - 1).size() >= 1
						&& (getPile(newPileIndex - 1).get(getPile(newPileIndex - 1).size() - 1))
								.getNumValue() == cardValue + 1) {
					// is the card in the new pile 1 value larger than the original card?
					if ((getPile(newPileIndex - 1).get(getPile(newPileIndex - 1).size() - 1)).color != cardColor) {
						return true;
					}
				}

				// printTable();

			}
		}
		return false;
	}

	public ArrayList<ArrayList<Card>> getTable() {
		return cardPiles;

	}

	public void printAdminTable() {
		String printTable = "";

		int maxColumn = 0;
		for (int i = 0; i < 7; i++) {
			if (getPile(i).size() > maxColumn) {
				maxColumn = getPile(i).size();
			}
		}

		for (int i = 0; i < maxColumn; i++) {
			for (int j = 0; j < 7; j++) {
				if (i < getPile(j).size()) {
					printTable += String.format("%4s", getPile(j).get(i).getCardName());
				} else {
					printTable += String.format("%4s", "");
				}
			}
			System.out.println(printTable);
			printTable = "";
		}
	}

	public void printTable() {
		String printTable = "";

		int maxColumn = 0;
		for (int i = 0; i < 7; i++) {
			if (getPile(i).size() > maxColumn) {
				maxColumn = getPile(i).size();
			}
		}

		for (int i = 0; i < maxColumn; i++) {
			for (int j = 0; j < 7; j++) {
				if (i < getPile(j).size()) {
					if (getPile(j).get(i).getRevealed()) {
						printTable += String.format("%4s", getPile(j).get(i).getCardName());
					} else {
						printTable += String.format("%4s", "*");
					}
				} else {
					printTable += String.format("%4s", "");
				}
			}
			System.out.println(printTable);
			printTable = "";
		}
		System.out.println("");
	}

	// i need to check that the card is at the bottom of the tableau's array
	public void addToFoundations(String cardName, int pileIndex, Foundations foundations) {
		int foundationIndex = findFoundationIndex(cardName);
		if (pileIndex >= 1 && pileIndex <= 7 && getPile(pileIndex - 1).size() > 0) {
			Card copy = getPile(pileIndex - 1).get(getPile(pileIndex - 1).size() - 1);
			if (findAddToFoundation(cardName, pileIndex, foundations)) {

				getPile(pileIndex - 1).remove(copy);
				foundations.getFoundation().get(foundationIndex).add(copy);
				
				if (getPile(pileIndex - 1).size() > 0
						&& !getPile(pileIndex - 1).get(getPile(pileIndex - 1).size() - 1).getRevealed()) {
					getPile(pileIndex - 1).get(getPile(pileIndex - 1).size() - 1).setCardRevealed(true);
				}

			}
		}
	}

		public void addToFoundations(String cardName, int tableauPileIndex, int foundationIndex, Foundations foundations) {
			
			if (tableauPileIndex >= 1 && tableauPileIndex <= 7 && getPile(tableauPileIndex - 1).size() > 0) {
				Card copy = getPile(tableauPileIndex - 1).get(getPile(tableauPileIndex - 1).size() - 1);
				if (findAddToFoundation(cardName, tableauPileIndex, foundationIndex, foundations)) {
	
					getPile(tableauPileIndex - 1).remove(copy);
					foundations.getFoundation().get(foundationIndex).add(copy);
					
					if (getPile(tableauPileIndex - 1).size() > 0
							&& !getPile(tableauPileIndex - 1).get(getPile(tableauPileIndex - 1).size() - 1).getRevealed()) {
						getPile(tableauPileIndex - 1).get(getPile(tableauPileIndex - 1).size() - 1).setCardRevealed(true);
					}
	
				}
			}
		
		/*
		 * 1. Check the symbol via character in string (D, H, C, S) 2. Check the value
		 * of the string 3. Check if the card can be added 4. If the card can be added
		 * 
		 */
	}

	public boolean findAddToFoundation(String cardName, int pileIndex, Foundations foundations) {

		int foundationIndex = findFoundationIndex(cardName);
		if (getPile(pileIndex - 1).get(getPile(pileIndex - 1).size() - 1).getCardName().equals(cardName)) {

			if (foundations.getFoundation().get(foundationIndex).isEmpty()
					&& getPile(pileIndex - 1).get(getPile(pileIndex - 1).size() - 1).getNumValue() == 1) {
				return true;
			} else if (foundations.getFoundation().get(foundationIndex).size() > 0 && foundations.getFoundation()
					.get(foundationIndex).get(foundations.getFoundation().get(foundationIndex).size() - 1)
					.getNumValue() == getPile(pileIndex - 1).get(getPile(pileIndex - 1).size() - 1).getNumValue() - 1) {
				return true;

			}
		}
		return false;
	}

	public boolean findAddToFoundation(String cardName, int tableauPileIndex, int foundationIndex, Foundations foundations) {

		
		if (getPile(tableauPileIndex - 1).get(getPile(tableauPileIndex - 1).size() - 1).getCardName().equals(cardName)) {
			if(foundationIndex == findFoundationIndex(cardName)) {
				if (foundations.getFoundation().get(foundationIndex).isEmpty()
					&& getPile(tableauPileIndex - 1).get(getPile(tableauPileIndex - 1).size() - 1).getNumValue() == 1) {
					return true;
				} else if (foundations.getFoundation().get(foundationIndex).size() > 0 && foundations.getFoundation()
					.get(foundationIndex).get(foundations.getFoundation().get(foundationIndex).size() - 1)
					.getNumValue() == getPile(tableauPileIndex - 1).get(getPile(tableauPileIndex - 1).size() - 1).getNumValue() - 1) {
					return true;

				}
			}

		}
		return false;
	}



	public int findFoundationIndex(String cardName) {
		char symbol = cardName.charAt(cardName.length() - 1);

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

	public ArrayList<ArrayList<Card>> getCardPiles() {
		return cardPiles;
	}

	public Tableau copyTableau(Deck deck) {
		Tableau newTableau = new Tableau();
		ArrayList<ArrayList<Card>> copyPiles = getCardPiles();
		int pileIndex = 0;
		for (ArrayList<Card> pile : copyPiles) {
			for (Card card : pile) {
				newTableau.getPile(pileIndex).add(deck.getImageAndCard().get(card.getPngName()));
			}
			pileIndex++;

		}
		return newTableau;

	}

	public ArrayList<Card> getPile(int pileIndex) {
		return cardPiles.get(pileIndex);
	}

	public int findPileWithCard(String card) {
		int pileNum = 0;
		for (ArrayList<Card> pile: cardPiles) {
			pileNum++;
			for (Card searchedCard: pile) {
				String searchedCardString = searchedCard.getCardName();
				if(searchedCardString.equals(card)) {
					return pileNum;
				}
			}
			
		}
		return 0;

	}
	
	public void resetTableau() {
		cardPiles = new ArrayList<ArrayList<Card>>();
		for (int i = 0; i < 7; i++) {
			cardPiles.add(new ArrayList<Card>());
		}
	}
}
