package views;

import controllers.*;

public class BoardView {
	private OperationController controller;
	
	public BoardView(OperationController controller) {
		assert controller != null;
		this.controller = controller;
	}

	private void showDeck() {
		controller.getDeck().toString();
		new CardStackView(controller.getDeck()).showStack("###################################\nBaraja: ");
	}
	
	private void showFoundations() {
		for (int i = 0; i < controller.getFoundations().size(); i++) {
			new CardStackView(controller.getFoundations().get(i)).showStack();
		}
	}
	
	private void showPiles() {
		for (int i = 0; i < controller.getPiles().size(); i++) {
			new CardStackView(controller.getPiles().get(i)).showStack();
		}
	}
	
	private void showWaste() {
		new CardStackView(controller.getWaste()).showStack("Descarte: ");
	}
	
	public void showAllParts() {
		showDeck();
		showWaste();
		showFoundations();
		showPiles();
	}
}
