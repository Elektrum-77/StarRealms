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
	public int getSize() { return list.size(); }

	@Override
	public Card pickCardAt(int i) { return list.remove(i); }

	public Card pickLastCard() { return list.remove(list.size()-1); }

	@Override
	public void addCard(Card card) { list.add(card); }

	@Override
	public void addCardAt(Card card, int i) { list.add(i, card); }

	@Override
	public void deleteCardAt(Card card, int i) { list.remove(i); }

	public void shuffle() { Collections.shuffle(list); }

	@Override
	public void clear() { list.clear(); }

	@Override
	public ArrayList<Card> getCopyList() { 
		ArrayList<Card> end = new ArrayList<>();
		for (Card card : list) {
			end.add(card.copy());
		}
		return end;
	}
	
	public boolean isEmpty() {return getSize() == 0;}
	
}
