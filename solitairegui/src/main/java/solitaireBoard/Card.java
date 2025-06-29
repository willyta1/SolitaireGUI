package solitaireBoard;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
// import java.nio.file.Paths;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import solitaireBoard.EnumBoardPosition.BoardPosition;
import solitaireBoard.EnumCards.*;

public class Card {
	/*
	 * Cards have a symbol (diamonds, clubs, etc), a rank (K, Q, J), and value (K >
	 * Q > 10 > 1)
	 * 
	 * 
	 */


	private int numValue;
	private String pngName;
	private String cardName;
	symbol symbol;
	color color;
	private char rank;
	private boolean revealed;
	private Image cardImage;
	public final static String relativePath = "src/main/resources/CardPNGs";
	
	/*
	 * Creates Card object, assigns their data such as number value, symbol,
	 * Additionally assigns a card image reflecting their card represented
	 */
	public Card(int numValue, symbol symbol, String pngName) {
		this.numValue = numValue;
		this.symbol = symbol;
		this.pngName = pngName;
		cardName = "" + numValue + symbol.toString().charAt(0);

		this.rank = symbol.toString().charAt(0);
		if (rank == 'D' || rank == 'H') {
			color = color.RED;
		} else {
			color = color.BLACK;
		}
		revealed = false;
		assignCardImage(relativePath, pngName);
        
		
		
	}

	/*
	 * Helper function for constructor, used to assign image within CardPNGs to the 
	 * Card object
	 * @param relativePath The file path to the folder for the Card PNGs
	 * @param pngName the name of the image stored in the card
	 */

	private void assignCardImage(String relativePath, String pngName) {
		// try {
		// 	String cardPath = "file:demo/src/CardPNGs/";
		// 	cardPath = cardPath + pngName + ".png";
		// 	Image image = new Image(cardPath);
		// 	cardImage = image;
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }

			// running above is simple and effective for assigning card image,
			// Issue: causes an error for running without gui components in a
			// non-gui environment
		
		
		try {
			// String copyRelativePath = "demo/src/CardPNGs/";

			InputStream stream = getClass().getResourceAsStream("/CardPNGs/" + pngName + ".png");
			BufferedImage bufferedImage = ImageIO.read(stream);
			
			
			cardImage = SwingFXUtils.toFXImage(bufferedImage, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Asdfgg");
			e.printStackTrace();
		}
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

	public char getRank() {
		return rank;
	}

	public void setCardRank(char a) {
		rank = a;
	}

	public boolean getRevealed() {
		return revealed;
	}

	public void setCardRevealed(boolean value) {
		revealed = value;
	}

	public String getPngName() {
		return pngName;
	}

	public Image getImage() {
		return cardImage;
		
	}

	@Override
	public String toString() {
		return symbol + ", " + rank + " (" + cardName + ")";
	}


	/**
	 * Finds the index where the card should be placed
	 * @return foundationPileIndex the ArrayList index of the pile
	 */
	public int findFoundationIndex() {
		char symbol = cardName.charAt(cardName.length() - 1);
		int foundationPileIndex;

		if (symbol == 'D') {
			foundationPileIndex = 0;
		} else if (symbol == 'H') {
			foundationPileIndex = 1;
		} else if (symbol == 'C') {
			foundationPileIndex = 2;
		} else {
			foundationPileIndex = 3;
		}
		return foundationPileIndex;
	}

}
