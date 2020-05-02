package fr.dut.info.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.GameBoard;

public class Deck {
	
	private final String name;
	private ArrayList<Card> deck;

	
	public Deck(String name) {
		this.name = Objects.requireNonNull(name);
		this.deck = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return deck.size();
	}
	
	public Card getCard(String id) {
		for(Card card: deck) {
			if(card.getId() == id) {
				return card;
			}
		}
		return null;
	}

	// Récuper la carte à la position n du deck
	public Card getCard(int n) {
		return deck.get(n);
	}
	
	
	
	//enlève une carte à la position n du deck 
	public void removeCard(String id) {
		 for(int i = 0; i < deck.size(); i++) {
			 if(deck.get(i).getId() == id) {
				 deck.remove(i);
				 break;
			 }
		 }
	}
	
	// Ajouter n fois une carte 
	public void addCard(Card card, int n) {
		for(int i = 0; i < n; i++) {
			deck.add(card);
		}
	}
	
	public void addCardTopOfDeck(Card card, int n) {
		for(int i = 0; i < n; i++) {
			deck.add(0, card);
		}
	}
	
	public void fistAddCard(GameBoard gameBoard, Card card) {
		if(card.getDefense()!=0) {
			firstAddCard(gameBoard, card.copyBase(gameBoard, card), 1);
		}else {
			firstAddCard(gameBoard, card.copyShip(gameBoard, card), 1);
		}
		
	}
	
	// Ajouter n fois une carte 
	public void firstAddCard(GameBoard gameBoard, Card card, int n) {
		for(int i = 0; i < n; i++) {
			if(n > 1) {
				if(card.getDefense()!=0) {
					deck.add(card.copyBase(gameBoard, card));
				}else {
					deck.add(card.copyShip(gameBoard, card));
				}
			}else {
				deck.add(card);
			}
			
		}
	}
	
	//Ajouter une seule carte
	public void addCard(Card card){
		addCard(card,1);
	}
	
	// Retourne vrai si le deck est vide
	public boolean isEmpty() {
		if(deck.size()==0) {
			return true;
		}
		return false;
	}
	
	// Vide le deck
	public void clean() {
		deck.clear();
	}
	
	// Mélange le deck
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		if(deck.size()!=0) {
			str.append("\n-____Cards____-\n");
			for(Card card: deck) {
				str.append(card).append("\n");
			}
		}
		return str.toString();
	}
	
	/*
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		if(deck.size()!=0) {
			
			for(Card card: deck) {
				str.append(card.getName()).append("\n");
			}
		}
		return str.toString();
	}
	*/

	public boolean isCardIdIn(String id) {
		for(Card card: deck) {
			if(card.getId()==id) {
				return true;
			}
		}
		return false;
	}
	
}
