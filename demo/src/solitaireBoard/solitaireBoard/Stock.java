package solitaireBoard;
import java.util.ArrayList;


public class Stock {
/* Card pile that did not go into the Tableau
 * You can cycle through cards
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
		if( pileStock.size() > -1 && counter < pileStock.size()-1) {
			System.out.println(pileStock.get(counter));
		} else {
			System.out.println("Blank");
			counter = -1;
		}
		
	}
	
	public Card getCurrentCard() {
		if(pileStock.size() > -1 && counter < pileStock.size()-1) {
			return pileStock.get(counter);
		}
		return null;
		
	}
	
	public void cycleCards() {
		if(counter > pileStock.size()-1) {
			counter = -1;
		} else {
			counter++;
			printCurrentCard();
		}
		// issues: stock should not show cards until the first call or when it goes past the last card in the stock
	}
	
	public void addToTableau(int newPileNumber, Tableau tableau) {
		Card currentCard = pileStock.get(counter);
		if (tableau.getCardPiles().get(newPileNumber-1).size() == 0 && currentCard.value == 13) {
			tableau.getCardPiles().get(newPileNumber-1).add(currentCard);
			pileStock.remove(counter);
			counter--;
		} else if (tableau.getCardPiles().get(newPileNumber-1).get(tableau.getCardPiles().get(newPileNumber-1).size()-1).value == currentCard.value+1) {
			if(tableau.getCardPiles().get(newPileNumber-1).get(tableau.getCardPiles().get(newPileNumber-1).size()-1).cardColor != currentCard.cardColor) {
				tableau.getCardPiles().get(newPileNumber-1).add(currentCard);
				pileStock.remove(counter);
				counter--;
			}
		}
	}
	
	public void addToFoundations(Foundations foundation) {
		if(findValidAddToFoundation(getCurrentCard(), foundation))
		foundation.getFoundation().get(findFoundationPileIndex(getCurrentCard().storageValue)).add(getCurrentCard());
		pileStock.remove(counter);
		

		
	}
	
	public int findValidAddToTableau(Card card, Tableau tableau) {
		int pileNum = 0;
		for (ArrayList<Card> pile: tableau.getCardPiles()) {
			if(card.value == 13 && pile.size() == 0 ) {
				return pileNum;
			} else if (card.value == pile.get(pile.size()-1).value && card.cardColor != pile.get(pile.size()-1).cardColor) {
				return pileNum;
			}
			pileNum++;
		}
		return -1;
	}
	
	public boolean findValidAddToFoundation(Card card, Foundations foundation) {
		if(counter >= 0 && counter < pileStock.size()) {
			
			
			int getIndex = findFoundationPileIndex(pileStock.get(counter).getCardStorageValue());
			
			// repeated in tableau
			
			if(foundation.getFoundation().get(getIndex).isEmpty() && getCurrentCard().value == 1) {
				return true;
			} else if( getCurrentCard() != null && getCurrentCard().value-1 == 
					foundation.getFoundation().get(getIndex).get(foundation.getFoundation().get(getIndex).size()-1).value) {
				return true;
				
				
				//repeated, maybe put into method?
			}
		}
		// how do i make this iterable
		return false;
	}
	
	public int findFoundationPileIndex(String cardStorageValue) {
		char symbol = cardStorageValue.charAt(cardStorageValue.length()-1);
		int pileIndex;
		
		if(symbol == 'D') {
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
		for(Card card: pileStock) {
			cardNames += card.storageValue + "\n";
		}
		return cardNames;
	}
	
	public ArrayList<Card> getStock() {
		return pileStock;
	}
}
