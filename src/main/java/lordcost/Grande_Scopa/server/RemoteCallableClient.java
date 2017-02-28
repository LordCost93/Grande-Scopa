package lordcost.Grande_Scopa.server;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**	
 *
 * Class which implements the CallableClient interface.
 * It allows the server to call the remote client.
 */
public class RemoteCallableClient implements CallableClient {
	public static final int NUMMAXGIOCATORI=2;// andrà bene per le partite multigiocatore
	public static final int NUMMINGIOCATORI=2;
	
	@Override
	public void setClientPort(int port) throws RemoteException {
		//TODO se il booleano started==true non possono essere aggiunti nuovi giocatori
		
		Registry registry;
		try {
			//Locate the registry and get the remote notifier
			registry = LocateRegistry.getRegistry(port);
			RemoteNotifier rn = (RemoteNotifier)registry.lookup("Notifier");
			//Aggiungo il client alla prima stanza disponibile e gli restituisco il suo idGiocatore
			int idGioc;
			idGioc=Server.getServer().addNotifier(rn);//guardare incrementPlayersNumber su main engine, lì si setta l'id del giocatore che ora parte da 0
			String msg= "Ti sei collegato con successo: sei il giocatore numero "+(idGioc+1)+"\n";//qua ho modificato la view! Così non stampa "sei il giocatore 0"
			rn.setId(idGioc);
			rn.notifyMessage(msg);
			//imposto dei controlli che fanno iniziare la partita se ho raggiunto il massimo numero di giocatori
			//oppure per il timer
			/*if(idGioc==NUMMINGIOCATORI){
				Timer t= new Timer();
				t.schedule(new GameInitializier(), 120000);//quando scade il timer la partita inizia, anche se non siamo arrivati a MaxGiocatori
			}*/
			if(idGioc==NUMMINGIOCATORI-1)// metto -1 perchè si parte da player 0
				Server.getServer().startServerRoom();
			//imposto il numero della stanza a cui il client è collegato
			int numRoom= Server.getServer().getNumServerRoom();//se il cliente non è nè il primo nè l'ultimo aggiunto vengono fatte solo questa istruzione e la seguente
			rn.setServerRoomNumber(numRoom);
			
		} catch (Exception ex){
			ex.printStackTrace();
		}

	}
	
	/**
	 * @author Lollo
	 * Inner class of Remote Callable Client that extends TimerTask. It has the "run()" method that calls the method startServerRoom on the class Server. The timer
	 * let the match start only if it hasn't started yet, only if the number of players has not reached in time the maximum number (this control is done on the method setClientPort which initialize the timer).
	 */
	/*private class GameInitializier extends TimerTask{

		@Override
		public void run() {
			//Il timer fa partire la partita se e soltanto se non è ancora iniziata, non la fa incominciare una seconda volta!
			Server.getServer().startServerRoom();			
		}
		*/
	}

		
	
	

		
		
		

