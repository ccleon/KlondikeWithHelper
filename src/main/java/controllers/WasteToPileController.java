package controllers;

import java.util.List;
import models.Card;
import models.Game;
import models.Pile;
import models.Rank;
import models.State;
import utils.LimitedIntDialog;

public class WasteToPileController extends OperationController{
	
	public WasteToPileController(Game game){
		super(game);
	}
	
	public void moveWasteToPile(){
		if (!this.getWaste().isEmpty()){
			int dest = new LimitedIntDialog("A que escalera?", 1, 7).read() - 1;
			Card card = this.getWaste().peekCard();
			Pile pile = this.getPiles().get(dest);
			
			if (suitablePile(pile, card)){
				pile.pushCard(this.getWaste().popCard());
			}else{
				errorReport.generalError();
			}
		}else{
			errorReport.specificError("ERROR: Los descartes están vacíos");
		}
	}
	
	public boolean suitablePile(Pile pile, Card card){
		if (pile.isEmpty()){
			if (card.getRank() == Rank.KING)
				return true;
		}else{
			if (card.getRank() != Rank.KING){
				Card destCard = pile.peekCard();
				if (card.isNextRank(destCard) && !card.sameColor(destCard))
					return true;
			}
		}
		return false;
	}
	
	public String checkWasteToPile(){
		String result = "";
		if (!this.getWaste().isEmpty()){
			Card card = this.getWaste().peekCard();
			List<Pile> piles = this.getPiles();
			
			for (Pile pile : piles){
				if (suitablePile(pile, card)) 
					result+="- Mover de descarte a escalera " + pile.whichPile() + "\n";
			}
		}
		return result;
	}
	
	@Override
	public void setState() {
		this.setState(State.SELECT_OPTION);
	}

	@Override
	public void accept(OperationControllerVisitor operationControllerVisitor) {
		operationControllerVisitor.visit(this);
	}

}
