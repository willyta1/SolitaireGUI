package solitaireBoard;

import java.util.ArrayList;
import java.util.Collection;

public class Solitaire {

	private Deck cardDeck;
	private Tableau tableau;
	private Stock stock;
	private Foundations foundation;
	
	Solitaire() {
		cardDeck = new Deck();
		tableau = new Tableau();
		stock = new Stock();
		cardDeck.distributeDeck(tableau, stock);
		foundation = new Foundations();
	}
	
	Solitaire(int i) {
		cardDeck = new Deck();
		tableau = new Tableau();
		stock = new Stock();
		cardDeck.distributeAdminDeck(tableau, stock);
		foundation = new Foundations();
	}
	
	
	public void printBoard() {
		stock.printCurrentCard();
		foundation.printFoundations();
		tableau.printTable();
	}
	
//	public boolean findValidMove() {
//		int counter = 0;
//		
//		Tableau copyTableau = new Tableau();
//		
//		for (int i = 0; i < foundation.getFoundation().size(); i++) {
//			for (int j = 0; j < tableau.getCardPiles().size(); j++) {
//				if(foundation.getFoundation().get(i).size() > 1) {
//					Card foundationLargest = foundation.getFoundation().get(i).get(foundation.getFoundation().get(i).size()-1);
//					if (foundation.findValidMoveFromFoundation(i, j, tableau)) {
//						
//					}
//				} else {
//					break;
//				}
//
//			}
//		}
//		
//		
//		return false;
//		
//	}
}
