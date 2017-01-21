package controllers;

import java.util.List;
import java.util.Stack;

import models.Card;
import models.Game;
import models.Pile;
import models.Rank;
import models.State;
import utils.LimitedIntDialog;

public class PileToPileController extends OperationController{
	
	public PileToPileController(Game game){
		super(game);
	}
	
	public void movePileToPile(){
		int origin = new LimitedIntDialog("De que escalera?", 1, 7).read() -1;
		int dest = new LimitedIntDialog("A que escalera?", 1, 7).read() -1;
		Pile pileOrigin = this.getPiles().get(origin);
		Pile pileDest = this.getPiles().get(dest);
		int quantity = new LimitedIntDialog("Cuantas?", 1, pileOrigin.getVisibleCards().size()).read();
		
		if (!pileOrigin.isEmpty()){
			if (quantity == 1){
				if (suitablePile(pileDest, pileOrigin.peekCard())) {
					pileDest.pushCard(pileOrigin.popCard());
				}else { errorReport.generalError(); }
			}else{
				Stack<Card> visibleCards = pileOrigin.getVisibleCards();
				Card temp = visibleCards.elementAt(quantity-1);
				Pile tempPile = new Pile(22);
				
				if (suitablePile(pileDest, temp)){
					for (int i=0; i<quantity; i++){
						tempPile.pushCard(pileOrigin.popCard());
						System.out.println("TempPile " + tempPile);
					}
					for (int j=0; j<quantity; j++){
						System.out.println("SIZE "+tempPile.size());
						pileDest.pushCard(tempPile.popCard());
					}
				}else{ errorReport.generalError(); }
			}
		}else{ errorReport.specificError("ERROR: la escalera origen está vacía"); }
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
	
	
	public String checkPileToPile(){
		List<Pile> piles = this.getPiles();
		List<Pile> piles2 = this.getPiles();
		String result = "";
		
		for (Pile pile: piles){
			if (!pile.isEmpty()){
				for (Card card : pile.getVisibleCards()){
					for (Pile pile2: piles2){	
						if (suitablePile(pile2, card))
							result += "- Mover " + (pile.getVisibleCards().indexOf(card)+1) + " carta(s) de escalera " + pile.whichPile() + " a escalera " + pile2.whichPile() + "\n";
					}
				}
			}
		}
		return result;
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
