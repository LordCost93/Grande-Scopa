package lordcost.Grande_Scopa.cards;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class SupportDeck {
	
	private Deque<SupportCard> deck;
	public static final int MAINDECKS = 2;
	public static final int ACEVALUE = 1;
	public static final int KINGVALUE = 13;
	public static final boolean ISJOLLY = true;
		
	public SupportDeck(){
		this.deck = new ArrayDeque<SupportCard>();
		int i,j;
		List<SupportCard> arrayCard = new ArrayList<SupportCard>();
		SupportCard card;
		for(i=0;i<MAINDECKS;i++)
			for(j=ACEVALUE;j<=KINGVALUE;j++){
				arrayCard.add(new SupportCard(Suit.CUORI,j));
				arrayCard.add(new SupportCard(Suit.PICCHE,j));
				arrayCard.add(new SupportCard(Suit.FIORI,j));
				arrayCard.add(new SupportCard(Suit.QUADRI,j));
			}
		for(i=0;i<4;i++)
			arrayCard.add(new SupportCard(ISJOLLY));//creo i 4 jolly
				
		Collections.shuffle(arrayCard);
		for(i=arrayCard.size()-1;i>=0;i--){
			  card=arrayCard.get(i);
			  arrayCard.remove(i);
			  deck.push(card);
		}	
	}
	
	public SupportCard pickSingleCard(){
		return deck.pop();
	}


}
