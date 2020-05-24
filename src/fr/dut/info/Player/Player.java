package fr.dut.info.Player;

import java.util.ArrayList;
import java.util.Objects;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.Deck;
import fr.dut.info.Game.GameBoard;

public class Player {
	
	private final String name;
	private int authorityP;
	private Deck discardPile;
	private Deck deck; // Deck personnel
	private Deck hand; // cartes en mains
	private Deck inPlayCards; // cartes en jeu
	
	
	public Player(String name) {
		this.name = name;
		authorityP=50;
		discardPile = new Deck();
		deck= new Deck();
		hand = new Deck();
		inPlayCards = new Deck();
	}
	
	
	public int getAuthority() {
		return authorityP;
	}
	
	// Le joueur doit s'en occuper
	public boolean isPossibleAlly(Card card) {
			return sameFactionInGame(card);
	}
	
	// Renvoie le pile de d�fausse du joueur
	public Deck getDiscard() {
		return discardPile;
	}
	
	// Renvoie le deck du joueur
	public Deck getDeck() {
		return deck;
	}
	
	// Ajouter n cartes au deck du joueur
	public void addCardToDeck(Card card, int n) {
		for(int i = 0; i < n; i++) {
			deck.addCard(card);
		}
	}
	
	// Ajouter n cartes au deck du joueur
	public void addCardToTopOfDeck(Card card, int n) {
		for(int i = 0; i < n; i++) {
			deck.addCardAt(card, 0);
		}
			
	}
	
	
	// Renvoie la main du joueur
	public Deck getHand() {
		return hand;
	}
	
	// Renvoie les cartes en jeu
	public Deck getInPlayCards() {
		return inPlayCards;
	}
	
	// Joueur tire n carte de son deck
	public void drawCards(int n) {
		
		for(int i = 0; i < n; i++) {
			if(deck.isEmpty()) {
				discardPileGoesDeck();
			}
			try {
				Card c = deck.getCardAt(0);
				/*
				c.getPrimary();
				c.getAlly();
				c.getScrap();
				*/
				hand.addCard(c);
				
				deck.deleteCardById(c.getId());
				
			}catch(IndexOutOfBoundsException e){
				System.out.println("Number of cards to draw is higher than the amount of cards in deck's player.");
			}
		}
	}
	
	// Ajoute au cartes en jeux du joueur la carte de sa main d�sign� par sa position n dans la main
	public void addInPlayCard(int id) {
		inPlayCards.addCard(hand.pickById(id));
		;
	}
	
	// La d�fausse du joueur est m�lang�e et devient son nouveau deck
	public void discardPileGoesDeck() {
		discardPile.shuffle();
		for(int i = 0; i < discardPile.getSize(); i++) {
			deck.addCard(discardPile.getCard(i));
		}
		discardPile.clear();
	}
	
	
	public void addCardtoDiscardPile(Card card) {
		discardPile.addCard(card);
	}
	
	
	@Override
	public String toString() {
		return name+" | "+ authorityP;
	}

	// M�lange le deck du joueur
	public void shuffleDeck() {
		deck.shuffle();
	}
	
	// Maj l'influence du joueur
	public void updateAuthority(int i) {
			authorityP+=i;
	}
	
	
	public void endHisTurn() {
		// les cartes jou� qui ne sont pas des bases sont mise dans la d�fausse du joueur
		ArrayList<Card> bases = new ArrayList<>();
		for(int i = 0; i < inPlayCards.getSize(); i++) {
			if(inPlayCards.getCard(i).type()=="Base") {
				bases.add(inPlayCards.getCard(i));
			}else {
				discardPile.addCard(inPlayCards.pickCardAt(i));
			}
		}
		inPlayCards.clear();
		for(Card card: bases) {
			/*
			card.getAlly();
			card.getPrimary();
			*/
			inPlayCards.addCard(card);
			
		}
		for(int j = 0; j < hand.getSize(); j++) {
			discardPile.addCard(hand.getCard(j));
		}
		hand.clear();
		drawCards(5);
	}
		
	// Retourne true si le joueur poss�de une carte en jeu de la m�me faction
	public boolean sameFactionInGame(Card card) {
		int n = inPlayCards.getSize();
		for(int i = 0; i < n; i++) {
			if(inPlayCards.getCard(i).getFaction().equals(card.getFaction()) &&(!(inPlayCards.getCardAt(i).getId()==card.getId()))) {
				return true;
			}
		}
		return false;
	}
		
	// La carte en jeu position i va dans le tas de ferraille
	public void cardInPlayToScrapHeap(GameBoard gameBoard, int i) {
		gameBoard.addCardToScrapHeap(inPlayCards.pickCardAt(i));
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Player)) {
			return false;
		}
		Player other = (Player) obj;
		return Objects.equals(name, other.name);
	}

	
	public boolean hasNoOutPostBase() {
		Boolean thereIsOutPost = false;
		for(int i = 0; i < inPlayCards.getSize(); i++) {
			if(inPlayCards.getCard(i).isOutpost()) {
				thereIsOutPost=true;
			}
		}
		return !thereIsOutPost;
	}

	
	public String getName() {
		return name;
	}
	
}
