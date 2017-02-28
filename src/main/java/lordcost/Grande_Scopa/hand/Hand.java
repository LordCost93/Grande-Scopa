package lordcost.Grande_Scopa.hand;

import java.util.ArrayList;
import java.util.List;

import lordcost.Grande_Scopa.cards.MainCard;
import lordcost.Grande_Scopa.cards.MainDeck;
import lordcost.Grande_Scopa.moveController.ScopaLikeMoveController;
import lordcost.Grande_Scopa.player.Player;

public class Hand{

	private List<MainCard> hand;
	private MainCard cucuCard;
			
	public List<MainCard> getHand() {
		return hand;
	}
	
	public Hand(ArrayList<MainCard> hand, ScopaLikeMoveController cgController) {
		this.hand= hand;
		this.cucuCard= hand.get(hand.size()-1);
	}
	
	public void substituteJolly(MainDeck mainDeck, Player player){//metodo comune a quasi ogni mano
		int i;
		MainCard jolly, card;
		for(i=0;i<hand.size();i++)
			if(hand.get(i).isJolly()==true){
				jolly=hand.get(i);
				player.getPcg().addToSpecialCardsSlot(jolly);//la rimozione da Hand la faccio nel metodo interno. Unico problema è il cucù: si potrebbe decidere di tenere il jolly e scambiarlo col giocatore.
				card=mainDeck.pickSingleCard();//la pop nel metodo interno fa già la rimozione dal deck
				hand.add(card);
			}				
	}
	
}
