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
	public final String relativePath = "demo/src/CardPNGs/";
	
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
		
		InputStream stream;
		try {
			// String copyRelativePath = "demo/src/CardPNGs/";
			Path imagePath = Paths.get(relativePath +pngName + ".png");
			System.out.println(Files.exists(Paths.get(relativePath +pngName + ".png")));
			imagePath = Paths.get( relativePath +pngName + ".png");
			stream = Files.newInputStream(Paths.get(relativePath + "/" + pngName + ".png"));
			BufferedImage bufferedImage = ImageIO.read(stream);
			cardImage = SwingFXUtils.toFXImage(bufferedImage, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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



	public int findFoundationIndex() {
		char symbol = cardName.charAt(cardName.length() - 1);
		int pileIndex;

		if (symbol == 'D') {
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

}
