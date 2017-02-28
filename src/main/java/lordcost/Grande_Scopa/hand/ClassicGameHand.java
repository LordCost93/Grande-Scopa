package lordcost.Grande_Scopa.hand;

import java.util.ArrayList;

import lordcost.Grande_Scopa.cards.Card;
import lordcost.Grande_Scopa.cards.MainCard;
import lordcost.Grande_Scopa.constants.Constants;
import lordcost.Grande_Scopa.engine.Filter;
import lordcost.Grande_Scopa.moveController.ClassicGameMoveController;
import lordcost.Grande_Scopa.moveController.ScopaLikeMoveController;
import lordcost.Grande_Scopa.player.Player;

public class ClassicGameHand extends Hand {
	
	private MainCard cardPlayed;
	private ClassicGameMoveController cgController;
	private Filter filter;
	
	public Card getCardPlayed() {
		return cardPlayed;
	}

	public void setCardPlayed(MainCard cardPlayed) {
		this.cardPlayed = cardPlayed;
	}
	
	public ClassicGameHand(ArrayList<MainCard> hand, ScopaLikeMoveController cgController) {
		super(hand,(ClassicGameMoveController)(cgController));
		this.cardPlayed=null;
	}
	
	public Constants playCardCGRibattuta(MainCard card, Player player){

		//if(this.getHand().contains(card)) non va fatto, il controllo dell'input si fa a livello di rete: l'utente scrive un numero
		//da 1 a 6, e si fa il controllo sul numero che deve essere più piccolo del numero di carte in mano: poi ci sarà un traduttore che prende il numero e
		//ci associa la carta da giocare. playCard avrà questa carta come parametro.
								
		this.setCardPlayed(card);
		this.getHand().remove(card);//si può fare solo alla fine se si vuole permettere l'undo, dopo aver mostrato le mosse possibili
		card.turnUpCard();//per la bugiarda no
		Constants constant = verifyRibattuta(card, player);
		return constant;
	}
	
	private Constants verifyRibattuta(Card card, Player player){
		
		if(cgController.getMainBoard().getPrizeRibattuta().getCardPlayed()!=null){//faccio l'annuncio solo se la reply zone ha delle carte, altrimenti non annuncio nulla.
			if(card.getRank()>3 && card.isMadwoman()==false){
				filter.announceImpossibleReply(player);
				return Constants.IMPOSSIBLEREPLY;
			}
			filter.askReply(player);//si può non ribattere anche se è possibile farlo e si gioca un A,2,3,Matta
			return Constants.ASKREPLY;
		}
		return Constants.NOREPLY;
	}
	
	public void playCardCGMadWomanRank(MainCard card, Player player){

		if(card.isMadwoman()){
			ArrayList<Integer> madWomanRanks = cgController.getMainBoard().calculateMadwomanRanksClassicGame();
			filter.assignRankToMadwoman(madWomanRanks);//che chiama notifyObservers con AssignMadwomanRank
		}	
		return;
	}
	
	
}
