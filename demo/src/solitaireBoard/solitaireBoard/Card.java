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
	int value;
	String pngName;
	String storageValue;
	symbol symbolType;
	color cardColor;
	char rank;
	boolean revealed;
	
	
	// what if i made symbol a number
	// doing so allows me to more easily 
	
	// use enums for symbol and color



	public Card(int value, symbol symbol, String pngName) {
		this.value = value;
		symbolType = symbol;
		this.pngName = pngName;
		storageValue = "" + value + symbol.toString().charAt(0);
		
		this.rank = symbol.toString().charAt(0);
		if (rank == 'D' || rank == 'H') {
			cardColor = color.red;
		} else {
			cardColor = color.black;
		}
		revealed = false;
	}
	
	public int getCardValue() {
		return value;
	}

	public void setCardValue(int newValue) {
		value = newValue;
	}
	
	public String getCardStorageValue() {
		return storageValue;
	}
	
	public void setCardStorageValue(String number) {
		storageValue = number;
	}
	
	public symbol getCardSymbol() {
		return symbolType;
	}
	public void setCardSymbol(symbol newSymbol) {
		symbolType = newSymbol;
	}
	
	
	public color getCardColor() {
		return cardColor;
	}
	public void setCardColor(color newColor) {
		cardColor = newColor;
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
		return symbolType + ", " + rank + " (" + storageValue + ")"; 
	}
	
	
	
}
