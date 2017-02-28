package lordcost.Grande_Scopa.cards;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class MainDeck {

	private Deque<MainCard> deck;
	public static final int MAINDECKS = 2;
	public static final int ACEVALUE = 1;
	public static final int JACKVALUE = 11;
	public static final int QUEENVALUE = 12;
	public static final int KINGVALUE = 13;
	public static final boolean ISMADWOMAN = true;
	public static final boolean ISJOLLY = true;
		
	public MainDeck(){
		this.deck = new ArrayDeque<MainCard>();
		int i,j;
		List<MainCard> arrayCard = new ArrayList<MainCard>();
		MainCard card;
		for(i=0;i<MAINDECKS;i++){
			for(j=ACEVALUE;j<=JACKVALUE;j++){
				arrayCard.add(new MainCard(Suit.CUORI,j));
				arrayCard.add(new MainCard(Suit.PICCHE,j));
				arrayCard.add(new MainCard(Suit.FIORI,j));
				arrayCard.add(new MainCard(Suit.QUADRI,j));
			}
			arrayCard.add(new MainCard(Suit.CUORI,KINGVALUE));
			arrayCard.add(new MainCard(Suit.PICCHE,KINGVALUE));
			arrayCard.add(new MainCard(Suit.FIORI,KINGVALUE));
			arrayCard.add(new MainCard(Suit.QUADRI,KINGVALUE));
			
			arrayCard.add(new MainCard(Suit.CUORI,QUEENVALUE));
			arrayCard.add(new MainCard(Suit.FIORI,QUEENVALUE));
			arrayCard.add(new MainCard(Suit.QUADRI,QUEENVALUE));
		}
		arrayCard.add(new MainCard(Suit.PICCHE,QUEENVALUE));//creo l'unica peppa nel mazzo
		arrayCard.add(new MainCard(!ISJOLLY,ISMADWOMAN));//creo l'unica matta nel mazzo
		for(i=0;i<4;i++)
			arrayCard.add(new MainCard(ISJOLLY,!ISMADWOMAN));//creo i 4 jolly
				
		Collections.shuffle(arrayCard);
		for(i=arrayCard.size()-1;i>=0;i--){
			  card=arrayCard.get(i);
			  arrayCard.remove(i);
			  deck.push(card);
		}
	}
	
	public MainCard pickSingleCard(){
		return deck.pop();
	}

	public void putBottomDeck(MainCard card) {
		this.deck.addLast(card);//verificare che il metodo sia giusto!!		
	}
		
}
