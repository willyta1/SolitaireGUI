package solitaireBoard;

public class Main {

	
	public static void main(String[] args) {
		Deck cardDeck = new Deck();
		Tableau table = new Tableau();
		Stock stock = new Stock();
		Foundations foundation = new Foundations();
		cardDeck.distributeAdminDeck(table, stock);

		table.movePileCards("1D", 1, 7);

		table.movePileCards("1D", 1, 7);
		table.movePileCards("1D", 7, 1);
	
		table.movePileCards("2C", 7, 2);

		table.movePileCards("8H", 6, 2);

		table.movePileCards("1C", 7, 5);

		
		table.movePileCards("8H", 6, 4);
		stock.cycleCards();
		
	
		table.movePileCards("13H", 7, 1);
		
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		
		stock.addToTableau(1, table);
		stock.printCurrentCard();
		table.printTable();
		stock.addToTableau(7, table);
		table.printTable();
		table.movePileCards("10D", 4, 7);
		table.movePileCards("8H", 6, 4);
		stock.printCurrentCard();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		foundation.printFoundations();
		table.addCardToFoundations("1C", 5, foundation);
		foundation.printFoundations();
		table.printTable();
		
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		
		stock.addToTableau(4, table);
		table.printTable();
		stock.cycleCards();
		
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		
		stock.addToTableau(6, table);
		table.printTable();
		table.movePileCards("6D", 3, 6);
		stock.cycleCards();
		stock.cycleCards();
		
		stock.addToTableau(7, table);
		table.movePileCards("8H", 6, 7);
		table.movePileCards("7H", 6, 4);
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		
		foundation.printFoundations();
		stock.addToFoundations(foundation);
		foundation.printFoundations();
		
		table.printTable();
		
		Solitaire board = new Solitaire();
//		board.findValidMove();
		
	}
}
