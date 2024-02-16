package solitaireBoard;

public class Solitaire {

	private Deck deck;
	private Tableau tableau;
	private Stock stock;
	private Foundations foundation;

	public Solitaire() {
		deck = new Deck();
		tableau = new Tableau();
		stock = new Stock();
		deck.distributeDeck(tableau, stock);
		foundation = new Foundations();
	}

	/*
	 * Overloaded constructor that creates admin version that reveals
	 * all cards for testing
	 * @param i Entering any number will create a revealed version of Solitaire
	 */
	public Solitaire(int i) {
		deck = new Deck();
		tableau = new Tableau();
		stock = new Stock();
		deck.distributeAdminDeck(tableau, stock);
		foundation = new Foundations();
	}

	
	public void printBoard() {
		stock.printCurrentCard();
		foundation.printFoundations();
		tableau.printTable();
	}

	/*
	 * Checks if adding a card to the foundation will result in
	 * a productive move within the tableau, counting every possible
	 * combination that a foundation move could give to advance the board state 
	 * @return counter The total number of significant moves advancing board state
	 */
	public int findSignficantFoundationMove() {
		Tableau copyTableau = new Tableau();
		int counter = 0;
		for (int i = 0; i < foundation.getFoundation().size(); i++) {
			for (int j = 0; j < tableau.getCardPiles().size(); j++) {
				if (foundation.getFoundation().get(i).size() > 0) {
					Card foundationLargest = foundation.getFoundation().get(i)
							.get(foundation.getFoundation().get(i).size() - 1);
					if (foundation.findAddToTableau(i, j, tableau)) {
						copyTableau = tableau.copyTableau(deck);
						copyTableau.addCardInOrder(j, foundationLargest);
						// note: i did not remove from the foundation
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


	/*
	 * Checks if adding any card from stock to foundation is possible,
	 * which advances board state
	 * Also checks if adding any card to the tableau will contribute to advancement
	 * of board state such as revealing an unrevealed card
	 * @return counter The total number of significant moves advancing board state
	 */
	public int findSignficantStockMove() {
		Tableau copyTableau = tableau.copyTableau(deck);
		int counter = 0;

		for (Card card : stock.getStock()) {
			if (stock.findAddToFoundation(card, foundation)) {
				counter++;
			} else if (stock.findAddToTableau(card, copyTableau) >= 0) {
				copyTableau = tableau.copyTableau(deck);
				copyTableau.addCardInOrder(stock.findAddToTableau(card, copyTableau) + 1, card);
				if (copyTableau.findSignificantBoardMove()) {
					counter++;
				}
			}
		}
		return counter;
	}

	/*
	 * Checks all possible combinations of moving pile(s) of cards to see
	 * if it reveals a card or adds a card to the foundation, and increments
	 * a counter if it is determined to be a signifcant move
	 * @return counter The total number of significant moves advancing board state
	 */
	public int findSignificantTableMove() {
		int counter = 0;
		Tableau copyTableau = new Tableau();
		for (int i = 0; i < tableau.getCardPiles().size(); i++) {
			for (int j = tableau.getPile(i).size() - 1; j > -1; j--) {
				if (tableau.getPile(i).get(j).getRevealed()) {
					for (int k = 0; k < tableau.getCardPiles().size(); k++) {
						if (k != i && tableau.getPile(i).get(j).getRevealed()) {
							if (tableau.findTableMove(tableau.getPile(i).get(j).getCardName(), i, k)) {
								copyTableau = tableau.copyTableau(deck);
								copyTableau.movePileCards(tableau.getPile(i).get(j).getCardName(), i, k);
								if (copyTableau.findSignificantBoardMove()) {
									counter++;
								}
							}
						}
					}
				}
			}
		}

		for (int i = 1; i < tableau.getCardPiles().size() + 1; i++) {
			if (tableau.findAddToFoundation(tableau.getPile(i - 1).get(tableau.getPile(i - 1).size() - 1).getCardName(),
					i, foundation)) {
				copyTableau = tableau.copyTableau(deck);
				copyTableau.addToFoundations(
						tableau.getPile(i - 1).get(tableau.getPile(i - 1).size() - 1).getCardName(), i, foundation);
				if (copyTableau.findSignificantBoardMove()) {
					counter++;
				}
			}
		}
		return counter;
	}

	/*
	 * Counts up the total number of significant moves from the 
	 * three findSignificant moves methods
	 * @return counter The total number of significant moves from stock,
	 * tableau, and foundations
	 */
	public int findSignificantMove() {
		int counter = 0;

		counter += findSignficantFoundationMove();
		counter += findSignficantStockMove();
		counter += findSignificantTableMove();



		return counter;

	}

	public Stock getStock() {
		return stock;
	}

	public Tableau getTableau() {
		return tableau;
	}

	public Foundations getFoundations() {
		return foundation;
		
	}

	public Deck getDeck() {
		return deck;
	}

}
