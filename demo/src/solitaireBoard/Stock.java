package solitaireBoard;

import java.util.ArrayList;

import solitaireBoard.EnumBoardPosition.BoardPosition;

public class Stock {
	/*
	 * Card pile that did not go into the Tableau You can cycle through cards
	 * 
	 * 24 cards in Stock
	 */

	private ArrayList<Card> pileStock;
	private int counter;
	private ArrayList<Card> drawnPileStock;
	public Stock() {
		pileStock = new ArrayList<Card>();
		counter = -1;
		drawnPileStock = new ArrayList<Card>();
	}

	/*
	 * prints the information of the current card that was drawn from the stock
	 */
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
			drawnPileStock = new ArrayList<>();
		} else {
			counter++;
			if(getCurrentCard() != null) {
				drawnPileStock.add(getCurrentCard());
			}
			
			printCurrentCard();
		}

	}

	/*
	 * Adds a card from the stock to the tableau
	 */
	public void addToTableau(int pileIndex, Tableau tableau) {

		if (pileIndex >= 1 && pileIndex <= 7 && counter > -1 && counter <= pileStock.size() - 1) {
			Card currentCard = pileStock.get(counter);
			if (currentCard.getRevealed()) {
				if (tableau.getPile(pileIndex - 1).size() == 0 && currentCard.getNumValue() == 13) {
					tableau.getPile(pileIndex - 1).add(currentCard);
					
					pileStock.remove(counter);
					drawnPileStock.remove(drawnPileStock.size()-1);
					if(counter != 0) {
						counter--;
					}
				} else if (tableau.getPile(pileIndex - 1).size() > 0
						&& tableau.getPile(pileIndex - 1).get(tableau.getPile(pileIndex - 1).size() - 1)
								.getNumValue() == currentCard.getNumValue() + 1) {
					if (tableau.getPile(pileIndex - 1)
							.get(tableau.getPile(pileIndex - 1).size() - 1).color != currentCard.color) {
						tableau.getPile(pileIndex - 1).add(currentCard);
						
						pileStock.remove(counter);
						drawnPileStock.remove(drawnPileStock.size()-1);
						if(counter != 0) {
							counter--;
						}
						
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
			drawnPileStock.remove(drawnPileStock.size()-1);
			counter = counter-1;
		}
	}

	public void addToFoundations(Foundations foundation, int foundationIndex) {
		if (findAddToFoundation(getCurrentCard(), foundation)) {
			// int getIndex = findFoundationIndex(pileStock.get(counter).getCardName());
			
			if(findFoundationIndex(getCurrentCard().getCardName()) == foundationIndex) {
				foundation.getFoundationPile(foundationIndex).add(getCurrentCard());
				pileStock.remove(counter);
				drawnPileStock.remove(drawnPileStock.size()-1);
				counter = counter-1;
			}

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

	public boolean findIndexAddToTableau(Card card, int pileIndex, Tableau tableau) {
		Card tableauCard = tableau.getPile(pileIndex-1).get(tableau.getPile(pileIndex-1).size()-1);
		if(card.getColor() != tableauCard.getColor() && tableauCard.getNumValue() == card.getNumValue()+1) {
			return true;
		}
		return false;
	}

	public boolean findAddToFoundation(Card card, Foundations foundation) {
		if (counter >= 0 && counter < pileStock.size()) {

			int getIndex = findFoundationIndex(pileStock.get(counter).getCardName());


			if (card.getRevealed()) {
				if (foundation.getFoundationPile(getIndex).isEmpty() && getCurrentCard().getNumValue() == 1) {
					return true;
				} else if (foundation.getFoundationPile(getIndex).size() > 0 && getCurrentCard() != null
						&& getCurrentCard().getNumValue() - 1 == foundation.getFoundationPile(getIndex)
								.get(foundation.getFoundationPile(getIndex).size() - 1).getNumValue()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean findAddToFoundation(Card card, int foundationIndex, Foundations foundation) {
		if (counter >= 0 && counter < pileStock.size()) {

			int getIndex = findFoundationIndex(pileStock.get(counter).getCardName());
			if(getIndex == foundationIndex) {
				if (card.getRevealed()) {
					if (foundation.getFoundationPile(getIndex).isEmpty() && getCurrentCard().getNumValue() == 1) {
						return true;
					} else if (foundation.getFoundationPile(getIndex).size() > 0 && getCurrentCard() != null
							&& getCurrentCard().getNumValue() - 1 == foundation.getFoundationPile(getIndex)
									.get(foundation.getFoundationPile(getIndex).size() - 1).getNumValue()) {
						return true;
					}
				}
			}


		}
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

	public ArrayList<Card> getPileStock() {
		return pileStock;
	}

	public int getCounter() {
		return counter;
	}

	public ArrayList<Card> getDrawnPileStock() {
		return drawnPileStock;
		
	}
}
