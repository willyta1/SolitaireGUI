package solitaireBoard;
import java.util.ArrayList;

public class Foundations {
/* 4 piles: Hearts, Diamonds, Clubs, Spades
 * Each pile can only hold their color
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
		for(ArrayList<Card> pile: piles) {
			if (pile.size() == 0) {
				foundationCards += String.format("%4s", "");
			} else {
				foundationCards += String.format("%4s", pile.get(pile.size()-1).storageValue);
			}
		}
		System.out.println(foundationCards);
	}
	
	public boolean findValidMoveFromFoundation(int foundationPileNumber, int pileNumber, Tableau tableau) {
		if(foundationPileNumber >= 1 && foundationPileNumber <= 4 && pileNumber >= 1 && pileNumber <= 7) {
			Card foundationCard = piles.get(foundationPileNumber).get(piles.get(foundationPileNumber).size()-1);
			Card tableauPileCard = tableau.getCardPiles().get(pileNumber-1).get(tableau.getCardPiles().get(pileNumber-1).size()-1);
			if (foundationCard.value + 1 == tableauPileCard.value && foundationCard.cardColor != tableauPileCard.cardColor) {
				return true;
			}
		}
		return false;

			
	}
	

	
	public void moveFromFoundations(int foundationPileNumber, int pileNumber, Tableau tableau) {
		// remove topmost card from foundation, readd card to pile in tableau	
		if(findValidMoveFromFoundation(foundationPileNumber, pileNumber, tableau)) {
			Card foundationCard = piles.get(foundationPileNumber-1).get(piles.get(foundationPileNumber-1).size()-1);
			tableau.addCardInOrder(pileNumber-1, foundationCard);
			piles.get(foundationPileNumber).remove(foundationCard);
		}
		// get card string
		// get tableau
		// ensure valid adding to pile
		// 
		
	}
	public ArrayList<ArrayList<Card>> getFoundation() {
		return piles;
	}
}
