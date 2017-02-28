package lordcost.Grande_Scopa.player;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import lordcost.Grande_Scopa.cards.MainCard;
import lordcost.Grande_Scopa.hand.ClassicGameHand;
import lordcost.Grande_Scopa.hand.Hand;

public class Player extends Observable {

	private ClassicGameHand cghand;
	private MainCard personalJolly;
	private PlayerCardsGained pcg;
	private boolean sputacchioWinner;
	private int playerId;
	private List<Observer> observers;
	private SputacchioPersonalBoard spb;
		
	public boolean isSputacchioWinner() {
		return sputacchioWinner;
	}
	public void setSputacchioWinner(boolean sputacchioWinner) {
		this.sputacchioWinner = sputacchioWinner;
	}
	public ClassicGameHand getCGHand() {
		return cghand;
	}
	public MainCard getPersonalJolly() {
		return personalJolly;
	}
	public PlayerCardsGained getPcg() {
		return pcg;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setCGHand(Hand hand) {
		this.cghand = cghand;
	}
	public void setPersonalJolly(MainCard personalJolly) {
		this.personalJolly = personalJolly;
	}
	public Player(int idPlayer) {
		this.sputacchioWinner = false;
		this.playerId = idPlayer;
		this.spb = new SputacchioPersonalBoard();
		this.pcg = new PlayerCardsGained();
	}	
	public SputacchioPersonalBoard getSpb() {
		return spb;
	}
	
}
