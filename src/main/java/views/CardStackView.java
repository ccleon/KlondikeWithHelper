package views;

import models.CardStack;
import utils.IO;

public class CardStackView {
	
	private CardStack cardStack;
	private IO io;

	public CardStackView(CardStack cardStack) {
		this.io = new IO();
		this.cardStack = cardStack;
	}
	
	public void showStack() {
		io.writeln(this.toString());
	}

	public void showStack(String title) {
		io.writeln(title + this.toString());
	}

	@Override
	public String toString() {
		return this.cardStack.toString();
	}
}
