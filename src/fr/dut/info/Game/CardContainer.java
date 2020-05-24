package fr.dut.info.Game;

import java.util.ArrayList;

import fr.dut.info.Card.Card;

public interface CardContainer {

	public Card getCardAt(int i);
	public Card getLastCard();
	public int getSize();

	public Card pickCardAt(int i);
	public void addCard(Card card);
	public void addCardAt(Card card, int i);
	public void deleteCardAt(Card card, int i);
	public void deleteCardById(int id);
	public void firstAddCard(GameBoard gameBoard, Card c, int n);
	public boolean isEmpty();
	public Card pickById(int id);
	public boolean isCardIdIn(int id);
	
	
	public void clear();
	
	public ArrayList<Card> getCopyList();
	ArrayList<Card> getCopyList(GameBoard gameBoard);
	
	
}
