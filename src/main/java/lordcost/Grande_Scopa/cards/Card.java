package lordcost.Grande_Scopa.cards;

public class Card{
	
	private Suit suit;
	private int rank;
	private boolean faceUp;
	private boolean scopa;
	protected boolean isMadWoman;
	protected boolean isJolly;
	public static final int NORANK=-1;
		
	public Card(Suit suit, int rank) {
		this.suit = suit;
		this.rank = rank;
		this.faceUp = false;
		this.scopa = false;
		this.isJolly = false;
		this.isMadWoman = false;
		
	}
	
	public Card(boolean isJolly, boolean isMadWoman) {
		this.suit = Suit.NOTHING;
		this.rank = NORANK;
		this.faceUp = false;
		this.scopa = false;
		this.isJolly = isJolly;
		this.isMadWoman = isMadWoman;
		if((this.isJolly == false && this.isMadWoman == false) || (this.isJolly == true && this.isMadWoman == true)){
			System.err.println("A card has been created being both a Jolly or Madwoman or neither a Jolly, a Madwoman or a normal card");
		}	
	}
		
	public boolean isScopa() {
		return scopa;
	}

	public void setScopa(boolean scopa) {
		this.scopa = scopa;
	}

	public void turnUpCard(){
		if(this.faceUp == false)
			this.faceUp = true;
	}
	
	public void turnDownCard(){
		if(this.faceUp == true)
			this.faceUp = false;
	}

	public Suit getSuit() {
		return suit;
	}

	public int getRank() {
		return rank;
	}

	public boolean isFaceUp() {
		return faceUp;
	}

	public boolean isMadwoman() {
		return isMadWoman;
	}
	
	public boolean isJolly() {
		return isJolly;
	}
}
