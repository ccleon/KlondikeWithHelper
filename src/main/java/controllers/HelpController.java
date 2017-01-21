package controllers;

import models.Game;
import models.State;
import utils.IO;

public class HelpController extends OperationController{
	
	private IO io;
	
	public HelpController(Game game){
		super(game);
		io = new IO();
	}
	
	public void help(){
		io.write("###################################\n** Puedes hacer los siguientes movimientos:\n");
		
		io.write(new DeckToWasteController(game).checkDeckToWaste());
		
		io.write(new WasteToDeckController(game).checkWasteToDeck());
	
		io.write(new WasteToFoundationController(game).checkWasteToFoundation());
		
		io.write(new WasteToPileController(game).checkWasteToPile());
		
		io.write(new FoundationToPileController(game).checkFoundationToPile());
		
		io.write(new TurnUpCardInPileController(game).checkTurnUpCardInPile());
		
		io.write(new PileToFoundationController(game).checkPileToFoundation());
		
		io.write(new PileToPileController(game).checkPileToPile());
		
	}

	@Override
	public void accept(OperationControllerVisitor operationControllerVisitor) {
		operationControllerVisitor.visit(this);
	}

	@Override
	public void setState() {
		this.setState(State.SELECT_OPTION);
	}

}
