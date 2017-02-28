package lordcost.Grande_Scopa.server;

import java.rmi.RemoteException;
import java.util.List;

import lordcost.Grande_Scopa.engine.Controller;
import lordcost.Grande_Scopa.engine.Filter;
import lordcost.Grande_Scopa.engine.MainEngine;
import lordcost.Grande_Scopa.player.Player;

/**
 * @author Lollo
 * The instances of this class are server rooms, i.e. single matches running and controlled by only one server at the same time. This class contains
 * Gioco, Filter, Controller, CommandHandler as attributes, and methods for the creation of a server room (matches) and for receiving players before the match starts
 * 
 */
public class ServerRoom {
	private boolean started;
	private MainEngine partita;
	private Filter filter;
	private Controller controller;
	private CommandHandler commandHandler;
		
	/**Constructor
	 * @param numRoom the number that identifies the server room which will be created
	 */
	
	public ServerRoom() {
		filter= new Filter();
		controller=new Controller(partita);
		started=false;
		partita= new MainEngine(2, filter);// va passato number of Players: per ora metto 2, ma c'è già implementato qualcosa per tenere conto del numero di giocatori anche superiore
	}
	
	//Aggiunge un giocatore alla stanza e restituisce l'intero che rappresenta l'id giocatore
	//Fa opportuni controlli riguardo a quando iniziare la partita, deve chiamare StartGame()
	/** Adds the player to the list of notifiers, an attribute of the class Filter. Then
	 * @param rn a remote notifier, that will be added to the list of notifiers in the class Filter
	 * @return the number of players in the match 
	 * @throws RemoteException
	 */
	public int addPlayer(RemoteNotifier rn) throws RemoteException{
		
		filter.addNotifier(rn);//passo a filter il giocatore aggiunto
		return partita.incrementPlayersNumber();//getter del numero di giocatori di una partita (nel suo codice c'è un incremento numgioc++)
	}

	/**
	 * This method starts the match, calls creagiocatori() of the class Gioco to create the right number of aliens and humans and calls the method eseguipartita(), which controls all the match from beginning to end at an high level of abstraction
	 */
	public void start(){
			started=true;
			List<Player> players= partita.getPlayers();//aggiungo filter come observer del giocatore dopo aver ottenuto tutti i giocatori della partita
			for(Player g: players){
				filter.nuovoGiocatore(g);
			}
			partita.gameEngineInitializer();
	}
	
	
	

	/**Getter
	 * @return the command handler of the server room
	 */
	public CommandHandler getCommandHandler() {
		return commandHandler;
	}
	/**Getter
	 * @return a true boolean if the server room has been created and started
	 */
	public boolean isStarted() {
		return started;
	}



	/**Getter
	 * @return the controller correspondent to the server room
	 */
	public Controller getController() {
		return controller;
	}



}
