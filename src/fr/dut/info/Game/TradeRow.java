package fr.dut.info.Game;

import java.util.ArrayList;
import fr.dut.info.Card.Card;

public class TradeRow extends AbstractCardContainer {
	
	private Card[] list;

	public TradeRow() {
		this.list = new Card[5];
	}
	
	@Override
	public int getSize() { return 5; }

	@Override
	public ArrayList<Card> getCopyList() {
//		ArrayList<Card> end = new ArrayList<>();
//		for (int i = 0; i < 5; i++) { end.add(list[i].copy()); }
//		return end;
		return null;
	}

	@Override
	public Card pickCardAt(int i) { 
		Card end = list[i];
		list[i]=null;
		return end;
	}
	
	public Card getCard(int id) {
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
	
	public int getMissing() {
		int n = 0;
		for (int i = 0; i<5; i++) {
			if (list[i]==null) {
				n++;
			}
		}
		return n;
	}

	@Override
	public boolean isEmpty() {
		return (getMissing() == 0);
	}

	@Override
	public void clear() {
		for (int i = 0; i<5;i++) {
			list[i] = null;
		}
	}

}
