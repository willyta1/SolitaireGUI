package solitaireBoard;

import java.util.ArrayList;

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
	
	public int findSignficantFoundationMove() {
		Tableau copyTableau = new Tableau();
		int counter = 0;
		for (int i = 0; i < foundation.getFoundation().size(); i++) {
			for (int j = 0; j < tableau.getCardPiles().size(); j++) {
				if(foundation.getFoundation().get(i).size() > 0) {
					Card foundationLargest = foundation.getFoundation().get(i).get(foundation.getFoundation().get(i).size()-1);
					if (foundation.findValidMoveFromFoundation(i, j, tableau)) {
						copyTableau = tableau.copyTableau(cardDeck);
						copyTableau.addCardInOrder(j, foundationLargest);
						if (copyTableau.findSignificantBoardMove()) {
							counter++;
						}
					}
					
				} else {
					break;
				}

			}
		}
		return counter;
	}

	public int findSignficantStockMove() {
		Tableau copyTableau = new Tableau();
		int counter = 0;

		for (Card card: stock.getStock()) {
			if (stock.findValidAddToFoundation(card, foundation)) {
				counter++;
			} else if (stock.findValidAddToTableau(card, copyTableau) >= 0 ) {
				copyTableau = tableau.copyTableau(cardDeck);
				copyTableau.addCardInOrder(stock.findValidAddToTableau(card, copyTableau), card);
				if (copyTableau.findSignificantBoardMove()) {
					counter++;
				}
			}
		}
		return counter;
	}

	public int findSignificantTableMove() {
		int counter = 0;
		Tableau copyTableau = new Tableau();
		for(int i = 0; i < tableau.getCardPiles().size(); i++) {
			for(int j = tableau.getCardPiles().get(i).size()-1; j > -1; j--) {
				if(tableau.getCardPiles().get(i).get(j).revealed) {
					for(int k = 0; i < tableau.getCardPiles().size(); k++) {
						if (k != i) {
							if(tableau.findValidTableMove(tableau.getCardPiles().get(i).get(j).storageValue, i, k)) {
								copyTableau = tableau.copyTableau(cardDeck);
								copyTableau.movePileCards(tableau.getCardPiles().get(i).get(j).storageValue, i, k);
								if(copyTableau.findSignificantBoardMove()) {
									counter++;
								}
							}
						}
					}
				}
			}
		}
		
		for(int i = 0; i < tableau.getCardPiles().size(); i++) {
			if(tableau.findValidAddToFoundation(tableau.getCardPiles().get(i).get(tableau.getCardPiles().get(i).size()-1).storageValue, i, foundation)) {
				copyTableau = tableau.copyTableau(cardDeck);
				copyTableau.addCardToFoundations(tableau.getCardPiles().get(i).get(tableau.getCardPiles().get(i).size()-1).storageValue, i, foundation);
				if(copyTableau.findSignificantBoardMove()) {
					counter++;
				}
			}
		}
		return counter;
	}

	public int findSignificantMove() {
		int counter = 0;
		Tableau copyTableau = new Tableau();
		counter += findSignficantFoundationMove();
		counter += findSignficantStockMove();


		// one more loop for after making a valid move to table

		return counter;
		
	}
}
