package solitaireBoard;
import java.util.*;

import solitaireBoard.EnumCards.*;
public class Tableau {
/* Tableau holds seven piles of cards
 * Only the top card of the initial piles of cards are revealed
 * As cards are moved off the pile, the card below will be revealed
 * Each pile requires alternating colors in order
 * Each pile is ordered in descending order of rankings (no duplicate rankings)
 * The card at bottom of pile is the highest ranking card
 * You can move stacks of cards onto another if following the above rules
 * 
 * From left to right: 1,2,3,4,5,6,7 cards for each pile
 * 28 cards in Tableau
 * 
 */
	
/* INITIAL PLANS
 * Question 1: How to store each card pile?
 * Question 2: Do I need to make a separate card pile for revealed and not revealed cards?
 * Thoughts on 2: Since we only have to show the top card, we can probably only use the "top" card and substitute hidden cards with placeholder values
 * Thoughts on 1: Store in a resizable data structure so we can call "pop" or something else
 * Thoughts on 1: How do we move a set of items and keep them in order?
 */
	// I guess I make a class
	// arrays are differently implemented from arraylist
	// int[][] != Array<int> 
//	Card[] cardstack = new Card[7];
// a good way to remove items from a datastructure that is resizeable?
// how do i know if a card is revealed or not
// maybe i can add it to my card class so that it is updated if revealed?
	
//	public Deck cardDeck = new Deck();

	//plan: use deck as reference for which card to get
	//store in arraylist numerical values to get card??
	// how about a code
	private ArrayList<ArrayList<Card>> cardPiles = new ArrayList<ArrayList<Card>>();
	
	public Tableau() {
		for (int i = 0; i < 7; i++) {
			cardPiles.add(new ArrayList<Card>());
		}
	}
	
	// go to pile
	// search cardstoragevalue
	// search value in hashmap
	// then search card in cardpile
	public void movePileCards(String storageValue, int pileNumber, int newPileNumber) {
		if (findValidTableMove(storageValue, pileNumber, newPileNumber)) {
			int cardIndex = getCardIndexFromPile( storageValue, pileNumber);
			shiftCards(pileNumber, newPileNumber, cardIndex);
		}
	}
	
	public void shiftCards(int pileNumber, int newPileNumber, int cardIndex) {
		if (cardIndex != 0) {
			cardPiles.get(pileNumber-1).get(cardIndex-1).setCardRevealed(true);
		}
		// "repeat removing and adding while num elementsi n arraylist is greater tha position
		while(cardPiles.get(pileNumber-1).size() > cardIndex) {
			
			Card card = cardPiles.get(pileNumber-1).get(cardIndex);
			cardPiles.get(pileNumber-1).remove(cardIndex);
			cardPiles.get(newPileNumber-1).add(card);
		}
	}
	
	public int getCardIndexFromPile(String storageValue, int pileNumber) {
		int cardIndex = 0;
		if(pileNumber >= 1 && pileNumber <= 7) {
			for(int i = 0; i < cardPiles.get(pileNumber-1).size(); i++) {
				if (cardPiles.get(pileNumber-1).get(i).storageValue.equals(storageValue)) {
					
					cardIndex = i;
					return cardIndex;
					
				}
			}
		}
		return -1;
	}

	public void addCardInOrder(int pileNumber, Card card) {
		if(pileNumber >= 1 && pileNumber <= 7) {
			Card cardCopy = cardPiles.get(pileNumber-1).get(cardPiles.get(pileNumber-1).size()-1);
			if (cardCopy.value == card.value +1) {
				if (cardCopy.cardColor != card.cardColor) {
					cardPiles.get(pileNumber-1).add(cardCopy);
				}
			}
		}
		
	}
	
	public boolean findSignificantBoardMove() {
		int pileNum = 0;
		for(ArrayList<Card> pile: cardPiles) {
			for (Card card: pile) {
				for (int i = 0; i < 7; i++) {
					if (i != pileNum && findValidTableMove(card.getCardStorageValue(), pileNum, i)) {
						if (!pile.get(getCardIndexFromPile(card.getCardStorageValue(), pileNum)).revealed) {
							return true;
						}
					}
				}
			}
		}
		return false;
		
	}

	public boolean findValidTableMove(String storageValue, int pileNumber, int newPileNumber) {
		boolean foundCard = false;
		int cardIndex = 0;
		
		if(pileNumber >= 1 && pileNumber <= 7) {
			for(int i = 0; i < cardPiles.get(pileNumber-1).size(); i++) {
				if (cardPiles.get(pileNumber-1).get(i).storageValue.equals(storageValue)) {
					foundCard= true;
					
					cardIndex = i;
					System.out.println("AA");
					break;
				}
			}
			
			if (foundCard && cardPiles.get(pileNumber-1).get(cardIndex).revealed) {
				// is the card that is being moved in the right pile?
				// if so, copy the index of it
				int cardValue = cardPiles.get(pileNumber-1).get(cardIndex).getCardValue();
				color cardColor;
				cardColor = cardPiles.get(pileNumber-1).get(cardIndex).cardColor;
				
				if (cardPiles.get(newPileNumber-1).size() == 0 && (storageValue.equals("13D") || storageValue.equals("13H") || storageValue.equals("13C") || storageValue.equals("13S")) ) {
					return true;
					
				} else if (cardPiles.get(newPileNumber-1).size() >= 1 && (cardPiles.get(newPileNumber-1).get(cardPiles.get(newPileNumber-1).size()-1)).getCardValue() == cardValue+1) { 
					// is the card in the new pile 1 value larger than the original card?
					if ((cardPiles.get(newPileNumber-1).get(cardPiles.get(newPileNumber-1).size()-1)).cardColor != cardColor) {
						return true;
					}
				} 
				
				printTable();

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
		for (int i = 0; i < 7; i ++) {
			if (cardPiles.get(i).size() > maxColumn) {
				maxColumn = cardPiles.get(i).size();
			}
		}
		
		for (int i = 0; i < maxColumn; i++) {
			for(int j = 0; j < 7; j++) {
				if(i < cardPiles.get(j).size()) {
					printTable += String.format(
							"%4s", cardPiles.get(j).get(i).getCardStorageValue());
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
		for (int i = 0; i < 7; i ++) {
			if (cardPiles.get(i).size() > maxColumn) {
				maxColumn = cardPiles.get(i).size();
			}
		}
		
		for (int i = 0; i < maxColumn; i++) {
			for(int j = 0; j < 7; j++) {
				if(i < cardPiles.get(j).size()) {
					if (cardPiles.get(j).get(i).revealed) {
						printTable += String.format(
								"%4s", cardPiles.get(j).get(i).getCardStorageValue());
					} else {
						printTable += String.format(
								"%4s", "*");
					}
				}  else {
					printTable += String.format("%4s", "");
				}
			}
			System.out.println(printTable);
			printTable = "";
		}
		System.out.println("");
	}
	
	
	//i need to check that the card is at the bottom of the tableau's array
	public void addCardToFoundations(String cardStorageValue, int pileNumber, Foundations foundations) {
		int pileIndex = findFoundationPileIndex(cardStorageValue);
		Card copy = cardPiles.get(pileNumber-1).get(cardPiles.get(pileNumber-1).size()-1);
		if(findValidAddToFoundation(cardStorageValue, pileNumber, foundations )) {
			cardPiles.get(pileNumber-1).remove(copy);
			foundations.getFoundation().get(pileIndex).add(copy);
		}
				

				
				// repeating code, move to separate method?
				
			
		
		
		
		/* 1. Check the symbol via character in string (D, H, C, S)
		 * 2. Check the value of the string
		 * 3. Check if the card can be added
		 * 4. If the card can be added
		 * 
		 */
	}

	public boolean findValidAddToFoundation(String cardStorageValue, int pileNumber, Foundations foundations) {
		
		int pileIndex = findFoundationPileIndex(cardStorageValue);
		if(cardPiles.get(pileNumber-1).get(cardPiles.get(pileNumber-1).size()-1).storageValue.equals(cardStorageValue)) {
			
			
			if(foundations.getFoundation().get(pileIndex).isEmpty() && cardPiles.get(pileNumber-1)
					.get(cardPiles.get(pileNumber-1).size()-1).value == 1) {
						return true;
			} else if (foundations.getFoundation().get(pileIndex).size() > 1 && 
					foundations.getFoundation().get(pileIndex).get(foundations.getFoundation().get(pileIndex).size()-1).value == 
					cardPiles.get(pileNumber-1).get(cardPiles.get(pileNumber-1).size()-1).value -1) { 
						return true;

				
				// repeating code, move to separate method?
				
			}
		}
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
	
	public ArrayList<ArrayList<Card>> getCardPiles() {
		return cardPiles;
	}
	
	public Tableau copyTableau(Deck deck) {
		Tableau newTableau = new Tableau();
		ArrayList<ArrayList<Card>> copyPiles = getCardPiles();
		int pileIndex = 0;
		for (ArrayList<Card> pile: copyPiles) {
			for (Card card: pile) {
				newTableau.getCardPiles().get(pileIndex).add(deck.getImageAndCard().get(card.getPngName()));
			}
			pileIndex++;
			
		}
		return newTableau;

	}
	
}
