package lordcost.Grande_Scopa.moveController;

import java.util.List;

import lordcost.Grande_Scopa.cards.MainCard;

public class Move {

	private MainCard cardPlayed;
	private List<MainCard> cardTaken;
	private int scope;
	private int thirds;
	private boolean rubamazzetto;

	public Move(MainCard cardPlayed, List<MainCard> cardTaken, int scope,
			int thirds) {
		this.cardPlayed = cardPlayed;
		this.cardTaken = cardTaken;
		this.scope = scope;
		this.thirds = thirds;
		this.rubamazzetto = false;
	}

	public Move(MainCard cardPlayed, List<MainCard> cardTaken, int scope,
			int thirds, boolean rubamazzetto) {
		this.cardPlayed = cardPlayed;
		this.cardTaken = cardTaken;
		this.scope = scope;
		this.thirds = thirds;
		this.rubamazzetto = rubamazzetto;
	}

	public MainCard getCardPlayed() {
		return cardPlayed;
	}

	public void setCardPlayed(MainCard cardPlayed) {
		this.cardPlayed = cardPlayed;
	}

	public List<MainCard> getCardTaken() {
		return cardTaken;
	}

	public void addCardTaken(List<MainCard> cardsToAdd) {
		this.cardTaken.addAll(cardsToAdd);
	}

	public void addCardTaken(MainCard cardToAdd) {
		this.cardTaken.add(cardToAdd);
	}

	/**
	 * It only adds number of scope. If you have to add also thirds you have to use AddThirdsAdjustScope
	 * @param number of scope to add
	 */
	public void addScope(int scopeNew) {
		this.scope = this.scope + scopeNew;
	}

	public void addThirdsAndScope(int thirdsToAdd, int scopeToAdd) {
		int total = this.thirds + thirdsToAdd;
		this.thirds = this.thirds + (total % 3);
		this.scope = this.scope + scopeToAdd + total/3;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public int getThirds() {
		return thirds;
	}

	public void setThirds(int thirds) {
		this.thirds = thirds;
	}

	public boolean isRubamazzetto() {
		return rubamazzetto;
	}

	public void setRubamazzetto(boolean rubamazzetto) {
		this.rubamazzetto = rubamazzetto;
	}

}
