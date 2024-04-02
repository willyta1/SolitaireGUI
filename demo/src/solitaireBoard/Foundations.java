package solitaireBoard;

import java.util.ArrayList;

import solitaireBoard.EnumBoardPosition.BoardPosition;

public class Foundations {
	/*
	 * 4 piles: Hearts, Diamonds, Clubs, Spades Each pile can only hold their color
	 * Each pile is built in-order from Ace to King
	 * 
	 */
	private ArrayList<ArrayList<Card>> piles = new ArrayList<ArrayList<Card>>();

	public Foundations() {
		for (int i = 0; i < 4; i++) {
			piles.add(new ArrayList<Card>());
		}

	}

	public void printFoundations() {
		String foundationCards = "F:";
		for (ArrayList<Card> pile : piles) {
			if (pile.size() == 0) {
				foundationCards += String.format("%4s", "");
			} else {
				foundationCards += String.format("%4s", pile.get(pile.size() - 1).getCardName());
			}
		}
		System.out.println(foundationCards);
	}

	/*
	 * Taking the topmost card from a foundation pile, method determines if it can place the card
	 * in a pile in the tableau
	 * @param foundationIndex A number from 1-4 choosing the foundation pile
	 * @param pileIndex A number from 1-7 choosing a pile in the tableau
	 * @param tableau The tableau the card would be added to for one of its pile
	 */
	public boolean findAddToTableau(int foundationIndex, int pileIndex, Tableau tableau) {
		if (foundationIndex >= 0 && foundationIndex <= 3 && pileIndex >= 1 && pileIndex <= 7) {
			Card foundationCard = piles.get(foundationIndex).get(piles.get(foundationIndex).size() - 1);
			if(tableau.getPile(pileIndex - 1).size() >= 1) {
				Card tableauPileCard = tableau.getPile(pileIndex - 1).get(tableau.getPile(pileIndex - 1).size() - 1);
				if (foundationCard.getNumValue() + 1 == tableauPileCard.getNumValue()
						&& foundationCard.color != tableauPileCard.color) {
					return true;
				}
			}

		}
		return false;

	}

	/*
	 * Removes topmost card from the foundation and moves the card to the
	 * tableau pile chosen, if possible
	 * @param foundationIndex A number from 1-4 choosing the foundation pile
	 * @param pileIndex A number from 1-7 choosing a pile in the tableau
	 * @param tableau The tableau receiving the card for its pile
	 */
	public void addToTableau(int foundationIndex, int pileIndex, Tableau tableau) {
		// remove topmost card from foundation, readd card to pile in tableau
		if (foundationIndex >= 0 && foundationIndex <= 3 && piles.get(foundationIndex).size() > 0) {
			if (pileIndex >= 1 && pileIndex <= 7 && findAddToTableau(foundationIndex, pileIndex, tableau)) {
				Card foundationCard = piles.get(foundationIndex).get(piles.get(foundationIndex).size() - 1);
				
				tableau.addCardInOrder(pileIndex, foundationCard);
				piles.get(foundationIndex).remove(foundationCard);
			}
		}



	}

	public ArrayList<ArrayList<Card>> getFoundation() {
		return piles;
	}

	public ArrayList<Card> getFoundationPile(int index) {
		return piles.get(index);
	}

	/**
	 * Creates a copy of a foundation
	 * @param deck The deck of cards will all the card data
	 * @return newFoundations the copy of the Foundations
	 */
	public Foundations copyFoundations(Deck deck) {
		Foundations newFoundations = new Foundations();
		ArrayList<ArrayList<Card>> copyPiles = getFoundation();
		int pileIndex = 0;
		for (ArrayList<Card> pile : copyPiles) {
			for (Card card : pile) {
				newFoundations.getFoundationPile(pileIndex).add(deck.getImageAndCard().get(card.getPngName()));
			}
			pileIndex++;

		}
		return newFoundations;

	}
}
