package fr.dut.info.Game;

import java.util.ArrayList;
import java.util.Random;

import fr.dut.info.Card.Card;
import fr.dut.info.Player.Player;

public class GameBoard {

	private final ArrayList<Player> players;
	private Player playingPlayer;
	private final Deck explorers;
	private  Deck tradeDeck;
	private final Deck tradeRow;
	private Deck scrapHeap; // Tas de ferraille devient le tradeDeck quand ce dernier est vide
	private int tradePool; // points de commerce durant la phase principale
	private int combatPool; // points de combat durant la phase principale
	private int cardIdAi; //compteur d'id
	private int ScrapTradeRowPool;
	private int freeShipPool;
	private int freeDestroyBasePool;
	private final ArrayList<String> logsPlayedCardTurn;
	

	public GameBoard() {
		players = new ArrayList<>();
		playingPlayer = null;
		explorers = new Deck();
		tradeDeck = new Deck();
		tradeRow = new Deck();
		scrapHeap = new Deck();
		tradePool = 0;
		combatPool = 0;
		cardIdAi = 0;
		ScrapTradeRowPool = 0;
		freeShipPool = 0;
		freeDestroyBasePool=0;
		logsPlayedCardTurn = new ArrayList<>();
	}
	
	public ArrayList<String> getPlayedCardTurn() {
		return logsPlayedCardTurn;
	}
	
	public int getFreeDestroyBasePool() {
		return freeDestroyBasePool;
	}
	
	public void updateFreeDestroyBasePool(int i) {
		freeDestroyBasePool+=i;
	}
	
	public int getFreeShipPool() {
		return freeShipPool;
	}
	
	public void updateFreeShipPool(int i) {
		freeShipPool+=i;
	}
	
	public int getScrapTradeRowPool() {
		return ScrapTradeRowPool;
	}
	
	public void updateScrapTradeRowPool(int i) {
		ScrapTradeRowPool+=i;
	}
	
	public int getTradePool() {
		return tradePool;
	}
	
	
	public int getCombatPool() {
		return combatPool;
	}
	
	// Obtenir un id;
	public int getAnId() {
		cardIdAi+=1;
		int id = cardIdAi;
		return id;
	}
	
	// Renvoie le tas de feraille
	public Deck getScarpHeap() {
		return scrapHeap;
	}
	
	// Renvoie le Deck d'Explorers
	public Deck getExplorers() {
		return explorers;
	}
	
	// Renvoie le Deck du jeu
	public Deck getTradeDeck() {
		return tradeDeck;
	}
	
	// Renvoie true si les pts d'infulence d'un joueur son <= 0
	public boolean isEndOfGame() {
		for(Player player: players) {
			if(player.getAuthority() <= 0) {
				return true;
			}
		}
		return false;
	}
	
	// Renvoie le deck de la liste d'achat
	public Deck getTradeRow() {
		return tradeRow;
	}
	
	// Envoie la liste des joueurs
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	// Ajouter un joueur au plateau
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	// Initialiser la pile Explorers 
	public void initialiseExplorers(Card c, int n) {
		explorers.firstAddCard(this, c, n);
	}
	
	// Ajouter - des - cartes au Deck d'achat
	public void addCardToTradeDeck(Card card,int n) {
		tradeDeck.firstAddCard(this, card, n);
	}
	
	// Ajouter - une - carte au Deck d'achat
	public void addCardToTradeDeck(Card card) {
		addCardToTradeDeck(card,1);
	}
	
	// Ajouter des cartes � tous les joueurs
	public void addCardToAllPlayersDeck(Card card, int n) {
		for(Player player: players) {
			 player.getDeck().firstAddCard(this, card, n);
		}
	}
	
	
	// Renvoi le joueur du tour actuel
	public Player getPlayingPlayer() {
		///// dois provoquer une erreur si aucun joueur n'est en train de jouer
		return playingPlayer;
	}
	
	// D�signer al�atoirement un joueur parmis ceux pr�sent (sa position dans la liste)
	public int randomPlayer() { 
		Random random = new Random();
		int pos = random.nextInt(players.size());
		return pos;
	}
	
	// D�signe le nouveau joueur du tour
	public void setThePlayer(int n) {	
		int x = n % players.size();
		try {
			playingPlayer = players.get(x);
		}catch(IndexOutOfBoundsException e) {
			System.out.println("The indicated position exceeds (or is less than 0) the size of the player list.");
		}
	}
	
	// Chaque joueur Pioche n cartes de son deck
	public void everybodyDrawCards(int n) {
		for(Player player: players) {
			player.drawCards(n);
		}
	}
	
	// Chaque joueur sauf le joueur entr�e en param�tre tire n cartes de leur deck
	public void everybodyDrawCardsExcept(Player player, int n) {
		for(Player p: players) {
			if(!(p.equals(player))) {
				p.drawCards(n);
			}
		}
	}
	
	// Le joueur du tour tire n cartes de son deck
	public void playingPlayerDrawCards(int n) {
		playingPlayer.drawCards(n);
	}
	
	// rempli la ligne d'achat s'il manque une carte
	public void updateTradeRow() {
		int missing = 5 - tradeRow.getSize();
		for(@SuppressWarnings("unused")int i = missing; missing >= 0; missing--) {
			// Tas de feraille -> deck d'achat
			if (tradeDeck.isEmpty()) {
				scrapHeapGoesTradeDeck();
			}
			if(tradeRow.getSize() < 5 && !(tradeDeck.isEmpty())) {
				addCardToTradeRow();
			}
		}
	}
	
	// Prend une carte du deck d'achat et la met sur la ligne d'achat
	private void addCardToTradeRow() {
		tradeRow.addCard(tradeDeck.pickCardAt(0));
	}
	
	// M�lange chaque deck des joueurs
	public void shuffleAllPlayersDeck() {
		for(Player player: players) {
			player.shuffleDeck();
		}
	}
	
	// ! A potentiellement supprimer !
	public String stringTradeDeck() {
		return tradeDeck.toString();
	}
	
	// MMh quelques doute sur cette fonction (carte -> scrapHeap mais n'est suppr nulle part ?)
	public void addCardToScrapHeap(Card card) {
		scrapHeap.addCard(card);
	}
	// Tas de feraille -> deck d'achat | tas de feraille vid�
	public void scrapHeapGoesTradeDeck() {
		if(!(scrapHeap.isEmpty())) {
			tradeDeck=scrapHeap;
			scrapHeap=new Deck();
		}
	}
	
	// retourne les infos utiles au joueur du tour
	public String turnInfo() {
		return playingPlayer+" |Trade : "+ tradePool+ " |Combat :"+combatPool+"\n_________________________________________";
	}
	
	// Met � jour les pts de combat du tour courant
	public void updateCombatPool(int i) {
		combatPool+=i;
	}
	
	// Maj les pts de trade du tour courant
	public void updateTradePool(int i) {
		tradePool+=i;
	}
	
	// Maj l'influence du joeur du tour courant
	public void updateAuthority(int i) {
		playingPlayer.updateAuthority(i);
	}

	// Renvoie la liste de tous les joueurs qui ne sont pas joueur du tour courant
	public ArrayList<Player> otherPlayer() {
		ArrayList<Player> list = new ArrayList<>();
		for(Player player: players) {
			if(!(player.equals(playingPlayer))){
				list.add(player);
			}
		}
		return list;
	}

	public boolean cardIsInAHand(int id) {
		for(Player player: players) {
			if(player.getHand().isCardIdIn(id)){
				return true;
			}
		}
		return false;
	}
	
	public boolean cardIsInTradeRow(int id) {
		return tradeRow.isCardIdIn(id);
	}
	
	public void endOfTurn(int i) {
		tradePool=0;
		combatPool=0;
		logsPlayedCardTurn.clear();
		playingPlayer.endHisTurn();
		setThePlayer(i);
	}
}
