package lordcost.Grande_Scopa.engine;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import lordcost.Grande_Scopa.cards.MainCard;
import lordcost.Grande_Scopa.moveController.Move;
import lordcost.Grande_Scopa.player.Player;
import lordcost.Grande_Scopa.server.RemoteNotifier;

public class Filter{

	private List<RemoteNotifier> notifiers;
	private RemoteNotifier rno;
		
	public Filter() {
		notifiers = new ArrayList<RemoteNotifier>();
	}
	
	/**Getter of a client with the id written in the parameter
	 * @param id id of the player
	 * @return the client with that id
	 * @throws RemoteException
	 */

	private RemoteNotifier getNotifier(int id) throws RemoteException{
		for(RemoteNotifier rnt: notifiers){
			if(rnt.getId()== id)
				return rnt;
		}
		return null;//TODO ci vuole anche un metodo per prendere il notifier precedente per mandargli a video "scegli le scope" per il premio della ribattuta
	}
	
	public void getNotifierForRibattuta(int id) throws RemoteException{
		try{
			if(id==0){
				int idToRetrieve = notifiers.size() - 1;
				for(RemoteNotifier rnt: notifiers){
					if(rnt.getId()== idToRetrieve)
						rno=rnt;
				}
			}
			if(id!=0){
				for(RemoteNotifier rnt: notifiers){
					if(rnt.getId()== id - 1)
						rno=rnt;
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	//Funzione privata che notifica tutti i giocatori ( Da utilizzare solo per eventi "pubblici")
	/** Method used when a message should be sent to all the players of a game
	 * @param msg string sent to all the players of a game
	 * @throws RemoteException
	 */
	private void announce(String msg) throws RemoteException{
		for(RemoteNotifier rnt: notifiers)
			rnt.notifyMessage(msg);
	}
	
	/**Setter
	 * @param rn a remote notifier who is playing his turn at the moment
	 */
	public void setRn(RemoteNotifier rn) {//quando lo si chiama in brunato costantini?
		this.rno = rn;
	}


	/**Getter
	 * @param rn the remote notifier who is playing his turn at the moment
	 */
	public void addNotifier(RemoteNotifier rn){
		this.notifiers.add(rn);
	}	
	
	public void nuovoGiocatore(Player nato){	
		try {
			//Prendo l'id di quel giocatore, lo sfrutto per trovare il suo corrispondente notifier per dirgli che inizia la partita
			RemoteNotifier r= getNotifier(nato.getPlayerId());
			r.setStatoGCreato();// setta semplicemente il nuovo giocatore in ENDSTATE (da brunato costantini). Vedere se dà problemi!
			r.notifyMessage("E' iniziata la partita");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void changeNotifier(int playerDoingTurnNumber) {
		try{
			rno=this.getNotifier(playerDoingTurnNumber);			
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	public void announceToPlayCard(int numCardsInHand) {
		
		try {
			rno.setStatoAttuale(StatiGioco.STARTCLASSICGAME);
			rno.notifyMessage("It's your turn. Play a card, write a number from 1 to " + numCardsInHand + ". The card played will be the one on the position in your hand correspondent to"
					+ "the number written by you");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	public void assignRankToMadwoman(ArrayList<Integer> madWomanRanks){
		try {
			rno.notifyMessage(FilterUtility.MadWomanRanksToString(madWomanRanks));
			rno.setStatoAttuale(StatiGioco.SETMADWOMANRANK);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}		
	}
	
	public void reAssignRankToMadwoman(ArrayList<Integer> madWomanRanks){
		try {
			rno.notifyMessage("The input is invalid. Type a number from 2 to 13 or write J,Q or K (or j,q or k) without spaces.");
			this.assignRankToMadwoman(madWomanRanks);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void reAssignRankToMadwoman2(ArrayList<Integer> madWomanRanks){
		try {
			rno.notifyMessage("The input is formally correct, but you have chosen an invalid value for the rank of the Madwoman.");
			this.assignRankToMadwoman(madWomanRanks);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
	}	
	
	public void announceImpossibleReply(Player player){
		try{
			announce("The player "+ player.getPlayerId() +" can't reply: cards in the reply-zone are gained by his opponent");
			rno.setStatoAttuale(StatiGioco.PAUSESTATE);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void askReply(Player player){
		try{
			rno.notifyMessage("Do you want to reply to gain all the cards in the reply zone? Answer with Y if you want to reply, otherwise answer with N and your opponent gains those cards");
			rno.setStatoAttuale(StatiGioco.ASKREPLY);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void askPlayerWhichScopaChoose(Move prizeRibattuta) {
		try{
			ArrayList<MainCard> cards = FilterUtility.getCardList(prizeRibattuta);
			String str = FilterUtility.printCards(cards);
			rno.notifyMessage("These are the cards you have gained: " + str + "\n");
			if(prizeRibattuta.getScope() > 0 || prizeRibattuta.getThirds() > 0){
				rno.notifyMessage("Write a number from 1 to " + cards.size() + " to indicate the card in the list counting from left to right. Write S + list of numbers to choose the cards that will be " +
					"your Scopa, from the one you place at the top to the Scopa you place at the bottom, if you have made more than 1 Scopa. Write T + list of numbers for choosing the cards that will form" +
					"your Thirds. Three examples: S-2-4-5   S-1/T-3-4   /T-2-3 ." + "\n" + "You cannot choose MadWoman as Scopa or Third");
				//TODO gestire il caso di scope in arretrato (pensare alla Marianna dichiarata), poi quando faccio punto con più di 5 scope e poi quando faccio ho più s/t che carte e che cerco nel mazzo
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	
}
