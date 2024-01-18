package solitaireBoard;

import solitaireBoard.EnumCards.*;

public class Card {
	/* Cards have a symbol (diamonds, clubs, etc), a rank (K, Q, J), and value (K > Q > 10 > 1)
	 * 
	 * 
	 * 
	 * 
	 */
	
	/* How do I make a card efficiently
	 * 4 Loops?
	 * 
	 */
	private int numValue;
	private String pngName;
	private String cardName;
	symbol symbol;
	color color;
	char rank;
	boolean revealed;
	
	
	// what if i made symbol a number
	// doing so allows me to more easily 
	
	// use enums for symbol and color



	public Card(int numValue, symbol symbol, String pngName) {
		this.numValue = numValue;
		this.symbol = symbol;
		this.pngName = pngName;
		cardName = "" + numValue + symbol.toString().charAt(0);
		
		this.rank = symbol.toString().charAt(0);
		if (rank == 'D' || rank == 'H') {
			color = color.red;
		} else {
			color = color.black;
		}
		revealed = false;
	}
	
	public int getNumValue() {
		return numValue;
	}

	public void setNumValue(int newValue) {
		numValue = newValue;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public void setCardName(String number) {
		cardName = number;
	}
	
	public symbol getSymbol() {
		return symbol;
	}
	public void setSymbol(symbol newSymbol) {
		symbol = newSymbol;
	}
	
	
	public color getColor() {
		return color;
	}
	public void setColor(color newColor) {
		color = newColor;
	}
	
	public char getCardRank() {
		return rank;
	}
	public void setCardRank(char a) {
		rank = a;
	}
	
	public boolean getCardRevealed() {
		return revealed;
	}
	
	public void setCardRevealed(boolean value) {
		revealed = value;
	}
	
	public String getPngName() {
		return pngName;
	}
	
	@Override
	public String toString() {
		return symbol + ", " + rank + " (" + cardName + ")"; 
	}
	
	
	
}
