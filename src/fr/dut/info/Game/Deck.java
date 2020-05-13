package fr.dut.info.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import fr.dut.info.Card.Card;

public class Deck extends AbstractCardContainer {
		
	public Deck() { super(); }
	
	public int getSize() {
		return list.size();
	}

	public ArrayList<Card> getList() {
		return list;
	}


	public Card getCard(String id) {
		for(Card card: list) {
			if(card.getId() == id) {
				return card;
			}
		}
		return null;
	}
}
