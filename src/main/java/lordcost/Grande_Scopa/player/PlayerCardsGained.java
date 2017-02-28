package lordcost.Grande_Scopa.player;

import java.util.ArrayList;
import java.util.List;

import lordcost.Grande_Scopa.cards.MainCard;

public class PlayerCardsGained {
	
	private List<MainCard> deck;
	private MainCard[] thirds;
	private List<MainCard> specialCardsSlot;
	private int pooWeight;
	private int accusoPoints;
	private List<MainCard> wapcg;//watten assolo player cards gained
	private int scopeYetToCount;
	
	public PlayerCardsGained() {
		this.deck = new ArrayList<MainCard>();
		this.thirds = new MainCard [3];
		this.specialCardsSlot = new ArrayList<MainCard>();
		this.wapcg = new ArrayList<MainCard>();
		this.pooWeight = 0;
		this.accusoPoints = 0;
		this.scopeYetToCount = 0;
	}

	public List<MainCard> getDeck() {
		return deck;
	}

	public MainCard[] getThirds() {
		return thirds;
	}

	public List<MainCard> getSpecialCardsSlot() {
		return specialCardsSlot;
	}

	public List<MainCard> getWapcg() {
		return wapcg;
	}

	public int getPooWeight() {
		return pooWeight;
	}

	public int getAccusoPoints() {
		return accusoPoints;
	}
	
	public void addToSpecialCardsSlot(MainCard card){
		this.specialCardsSlot.add(card);
	}
	
	public void addToPersonalDeck(List<MainCard> cards){
		for(MainCard card : cards)
			this.deck.add(card);
	}
	
	public int getScopeYetToCount() {
		return this.scopeYetToCount;
	}
	
}
