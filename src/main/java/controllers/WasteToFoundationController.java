package controllers;

import models.Card;
import models.Foundation;
import models.Game;
import models.Rank;
import models.State;

public class WasteToFoundationController extends OperationController{
	
	public WasteToFoundationController(Game game){
		super(game);
	}
	
	public void moveWasteToFoundation(){
		if (!this.getWaste().isEmpty()){
			Card card = this.getWaste().peekCard();
			if (this.suitableSuit(card)){
				this.getFoundation(card.getSuit()).pushCard(this.getWaste().popCard());
			}else{
				errorReport.generalError();	
			}
		}else{
			errorReport.specificError("ERROR: Los descartes están vacíos");
		}
	}
	
	
	public boolean suitableSuit(Card card){
		Foundation foundation = this.getFoundation(card.getSuit());
		if (card.getRank() == Rank.ACE && foundation.isEmpty()){
			return true;
		}else{
			if (!foundation.isEmpty()){
				Rank previous = card.getRank().previousRank();
				if (foundation.peekCard().getRank() == previous){
					return true;
				}
			}
		}
		return false;
	}
	
	public String checkWasteToFoundation(){
		if (!this.getWaste().isEmpty()){
			Card card = this.getWaste().peekCard();
			
			if (this.suitableSuit(card)) return "- Mover desde descarte a palo.\n";
		}
		return "";
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
