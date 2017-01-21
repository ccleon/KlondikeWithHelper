package controllers;

import models.Game;
import models.State;

public class WasteToDeckController extends OperationController{
	
	public WasteToDeckController(Game game){
		super(game);
	}
	
	public void moveWasteToDeck(){
		if (this.getDeck().isEmpty()){
			while(!this.getWaste().isEmpty()){
				this.getDeck().pushCard(this.getWaste().popCard());
			}
		}else{
			errorReport.generalError();
		}
	}
	
	public String checkWasteToDeck(){
		if (this.getDeck().isEmpty()) 
			return "- Mover de descarte a la baraja.\n";
		
		return "";
		
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
