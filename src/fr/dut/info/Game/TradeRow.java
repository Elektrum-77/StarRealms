package fr.dut.info.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import fr.dut.info.Card.Card;

public class TradeRow extends AbstractCardContainer {
	
	private Card[] list;

	public TradeRow() {
		this.list = new Card[5];
	}
	
	@Override
	public int getSize() { return 5; }

	@Override
	public ArrayList<Card> getList() {
		ArrayList<Card> end = new ArrayList<>();
		for (int i = 0; i < 5; i++) { end.add(list[i]); }
		return end;
	}

	@Override
	public Card pickCardAt(int i) { 
		Card end = list[i];
		list[i]=null;
		return end;
	}

	/* DO NOT USE THIS ON TRADEROW */
	@Override
	public Card pickLastCard(int i) {
		return null;
	}

	@Override	
	public Card getCard(String id) {
		for(Card card: list) {
			if(card.getId() == id) {
				return card;
			}
		}
		return null;
	}

	@Override
	public void addCard(Card card) {
		for (int i = 0; i<5;i++) {
			if (list[i]==null) {
				list[i] = card;
				return;
			}
		}
	}

	@Override
	public void clear() {
		for (int i = 0; i<5;i++) {
			list[i] = null;
		}
	}
}
