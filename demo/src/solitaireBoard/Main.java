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
		table.addToFoundations("1C", 5, foundation);
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
		table.addToFoundations("1D", 2, foundation);
		table.printTable();
		stock.cycleCards();
		stock.cycleCards();

		stock.addToTableau(3, table);
		table.printTable();
		table.movePileCards("3D", 2, 3);
		table.printTable();
		foundation.printFoundations();
		table.addToFoundations("2D", 2, foundation);
		foundation.printFoundations();
		table.printTable();
		stock.cycleCards();
		stock.cycleCards();
		stock.addToTableau(4, table);
		table.printTable();
		table.movePileCards("5D", 3, 4);
		table.printTable();

		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();

		foundation.printFoundations();
		stock.cycleCards();
		table.printTable();

		stock.addToTableau(3, table);
		table.movePileCards("2H", 5, 3);
		table.printTable();

		table.addToFoundations("1H", 5, foundation);
		foundation.printFoundations();
		table.printTable();

		table.movePileCards("13D", 5, 2);
		table.printTable();
		stock.cycleCards();
		stock.cycleCards();
		stock.addToTableau(6, table);

		table.printTable();
		table.movePileCards("4D", 3, 6);
		table.printTable();

		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.addToTableau(3, table);
		table.movePileCards("12H", 3, 7);
		table.printTable();
		table.movePileCards("12H", 7, 3);
		table.printTable();
		table.movePileCards("11H", 7, 1);
		table.printTable();

		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();

		stock.addToTableau(1, table);
		table.printTable();
		stock.addToTableau(7, table);
		table.printTable();
		stock.cycleCards();
		stock.addToTableau(5, table);
		table.movePileCards("10H", 7, 5);
		table.printTable();
		table.movePileCards("9H", 7, 1);

		foundation.printFoundations();
		table.printTable();
		table.addToFoundations("2C", 4, foundation);
		foundation.printFoundations();
		table.printTable();

		table.addToFoundations("2H", 6, foundation);
		table.addToFoundations("3D", 4, foundation);
		table.addToFoundations("3C", 6, foundation);
		table.addToFoundations("4D", 6, foundation);
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.addToFoundations(foundation);
		table.addToFoundations("5C", 6, foundation);
		stock.cycleCards();
		stock.cycleCards();
		stock.cycleCards();
		stock.addToFoundations(foundation);
		stock.addToFoundations(foundation);
		table.addToFoundations("4S", 4, foundation);
		table.addToFoundations("5D", 4, foundation);
		table.addToFoundations("6D", 3, foundation);
		// table.movePileCards("6H", 7, 3); // doesnot crash, good
		table.movePileCards("6H", 6, 3);
		table.movePileCards("5H", 6, 4);
		stock.cycleCards();
		stock.cycleCards();
		stock.addToTableau(2, table);
		foundation.addToTableau(3, 3, table);
		table.movePileCards("4H", 6, 3);
		table.addToFoundations("3H", 6, foundation);
		table.addToFoundations("4H", 3, foundation);
		table.addToFoundations("5C", 3, foundation);
		table.addToFoundations("5H", 4, foundation);
		table.addToFoundations("6H", 3, foundation);
		stock.cycleCards();
		stock.cycleCards();
		stock.addToFoundations(foundation);
		table.addToFoundations("7C", 3, foundation);
		stock.cycleCards();
		stock.cycleCards();
		stock.addToFoundations(foundation);
		table.addToFoundations("6S", 4, foundation);
		table.addToFoundations("7H", 4, foundation);
		stock.cycleCards();
		stock.cycleCards();
		stock.addToFoundations(foundation);
		table.addToFoundations("8H", 3, foundation);
		table.addToFoundations("9H", 1, foundation);
		table.movePileCards("9D", 4, 1);
		table.movePileCards("8D", 4, 5);
		table.addToFoundations("7D", 4, foundation);
		table.addToFoundations("8D", 5, foundation);
		table.addToFoundations("9C", 3, foundation);
		stock.cycleCards();
		stock.addToFoundations(foundation);
		table.addToFoundations("8S", 1, foundation);
		table.addToFoundations("9S", 5, foundation);
		table.addToFoundations("9D", 1, foundation);
		table.addToFoundations("10S", 1, foundation);
		stock.cycleCards();
		stock.cycleCards();
		stock.addToFoundations(foundation);
		stock.cycleCards();
		stock.cycleCards();
		table.addToFoundations("10D", 3, foundation);
		table.addToFoundations("10H", 5, foundation);
		table.addToFoundations("11H", 1, foundation);
		table.addToFoundations("11C", 3, foundation);
		table.addToFoundations("12H", 3, foundation);
		table.addToFoundations("11S", 5, foundation);
		table.addToFoundations("12S", 2, foundation);

		table.movePileCards("13S", 5, 4);
		table.movePileCards("12D", 5, 3);
		table.addToFoundations("11D", 5, foundation);
		table.addToFoundations("12D", 3, foundation);
		table.addToFoundations("12C", 1, foundation);
		table.addToFoundations("13H", 1, foundation);
		table.addToFoundations("13D", 2, foundation);
		table.addToFoundations("13C", 3, foundation);

		foundation.addToTableau(2, 3, table);
		foundation.addToTableau(5, 8, table);
		table.addToFoundations("13C", -1, foundation);
		table.movePileCards("23D", 8, 9);
		stock.addToFoundations(foundation);
		stock.addToFoundations(foundation);
		stock.addToTableau(1, table);

		foundation.printFoundations();
		table.printTable();

		stock.cycleCards();
		stock.cycleCards();

		// Solitaire board = new Solitaire(1);
		// board.printBoard();
		// System.out.println(board.getStock().toString());
		// System.out.println(board.findSignificantMove());

	}
}
