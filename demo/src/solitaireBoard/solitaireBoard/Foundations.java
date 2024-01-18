package solitaireBoard;

import java.util.ArrayList;

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

	public boolean findFoundationMove(int foundationIndex, int pileIndex, Tableau tableau) {
		if (foundationIndex >= 1 && foundationIndex <= 4 && pileIndex >= 1 && pileIndex <= 7) {
			Card foundationCard = piles.get(foundationIndex-1).get(piles.get(foundationIndex-1).size() - 1);
			Card tableauPileCard = tableau.getPile(pileIndex - 1).get(tableau.getPile(pileIndex - 1).size() - 1);
			if (foundationCard.getNumValue() + 1 == tableauPileCard.getNumValue()
					&& foundationCard.color != tableauPileCard.color) {
				return true;
			}
		}
		return false;

	}

	public void FoundationMove(int foundationIndex, int pileIndex, Tableau tableau) {
		// remove topmost card from foundation, readd card to pile in tableau
		if (foundationIndex >= 1 && foundationIndex <= 4 && piles.get(foundationIndex-1).size() > 0)  {
			if (pileIndex >= 1 && pileIndex <= 7 && findFoundationMove(foundationIndex, pileIndex, tableau)) {
				Card foundationCard = piles.get(foundationIndex - 1).get(piles.get(foundationIndex - 1).size() - 1);
				tableau.addCardInOrder(pileIndex, foundationCard);
				piles.get(foundationIndex-1).remove(foundationCard);
			}
		}

		// get card string
		// get tableau
		// ensure valid adding to pile
		//

	}

	public ArrayList<ArrayList<Card>> getFoundation() {
		return piles;
	}
	public ArrayList<Card> getFoundationPile(int index) {
		return piles.get(index);
	}
}
