package fr.dut.info.Game;

import java.util.ArrayList;
import java.util.Collections;

import fr.dut.info.Card.Card;

public abstract class AbstractCardContainer implements CardContainer {
	
	ArrayList<Card> list;
	
	public AbstractCardContainer() { list = new ArrayList<>(); }
	
	@Override
	public Card getCardAt(int i) {
		return list.get(i);
	}

	@Override
	public Card getLastCard() {
		return list.get(list.size()-1);
	}
	
	@Override
	public boolean isEmpty() {
		if(list.size()==0) {
			return true;
		}
		return false;
	}
	
	@Override
	public int getSize() { return list.size(); }

	@Override
	public Card pickCardAt(int i) { return list.remove(i); }

	public Card pickLastCard(int i) { return list.remove(list.size()-1); }
	
	@Override
	public Card pickById(int id) { 
		Card card = null; ///////////////////////////////////
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId()==id) {
				card = list.get(i);
				list.remove(i);
			}
		}
		return card;
	}

	@Override
	public boolean isCardIdIn(int id) {
		for(Card c: list) {
			if(c.getId()==id) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void addCard(Card card) { list.add(card); }

	@Override
	public void addCardAt(Card card, int i) { list.add(i, card); }

	@Override
	public void deleteCardAt(Card card, int i) { list.remove(i); }
	
	public void deleteCardById(int id) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId()==id) {
				list.remove(i);
			}
		}
	}

	public void shuffle() { Collections.shuffle(list); }

	@Override
	public void clear() { list.clear(); }

	@Override
	public void firstAddCard(GameBoard gameBoard, Card c, int n) {
		for(int i = 0; i < n; i++) {
			list.add(c.copy(gameBoard));
		}
		
	}
	
	@Override
	public ArrayList<Card> getCopyList(GameBoard gameBoard) { 
		ArrayList<Card> end = new ArrayList<>();
		for (Card card : list) {
			end.add(card.copy(gameBoard));
			
		}
		return end;
	}

}
