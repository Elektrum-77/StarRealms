package fr.dut.info.starRealms;

import java.util.Objects;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.Deck;
import fr.dut.info.Player.Player;
import fr.dut.info.Views.GameView;



public class ClickableObject {
	private final int x1;
	private final int y1;
	private final int x2;
	private final int y2;
	private final int width;
	private final int length;
	private final Card card;
	private final Deck deck;
	private final int positionInList;
	private final String label;
	private final Deck linkedDeck;
	private final Card linkedCard;
	private final Player linkedPlayer;
	
	
	private ClickableObject(String label, int x1, int y1, int x2, int y2, Card card, Deck deck, int positionInList, Deck linkedDeck, Card linkedCard, Player linkedPlayer) {
		this.label = label;
		this.deck = deck;
		this.card = card;
		this.linkedDeck = linkedDeck;
		this.linkedCard = linkedCard;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		width = x2-x1;
		length = y2-y1;
		this.positionInList = positionInList;
		this.linkedPlayer = linkedPlayer;
		
	}
	

	public ClickableObject(String label, int x1, int y1, int x2, int y2, Card card, int positionInList) {
		this(label, x1,  y1,  x2,  y2, card,null, positionInList, null, null, null);
	}
	
	public ClickableObject(int x1, int y1, int x2, int y2, Card card, int positionInList) {
		this("", x1,  y1,  x2,  y2, card,null, positionInList, null, null, null);
	}
	
	public ClickableObject(String label, int x1, int y1, int x2, int y2, Deck deck, int positionInList) {
		this(label, x1, y1, x2, y2, null, deck, positionInList, null, null, null);
	}
	
	public ClickableObject(int x1, int y1, int x2, int y2, Deck deck, int positionInList) {
		this("", x1, y1, x2, y2, null, deck, positionInList, null, null, null);
	}
	
	public ClickableObject(String label, int x1, int y1, int x2, int y2, Deck linkedDeck) {
		this(label, x1, y1, x2, y2, null, null, 0, linkedDeck, null, null);
	}
	
	public ClickableObject(String label, int x1, int y1, int x2, int y2, Card linkedCard) {
		this(label, x1, y1, x2, y2, null, null, 0, null, linkedCard, null);
	}
	
	public ClickableObject(String label, int x1, int y1, int x2, int y2, Player linkedPlayer) {
		this(label, x1, y1, x2, y2, null, null, 0, null, null, linkedPlayer);
	}
	
	public ClickableObject(int x1, int y1, int x2, int y2) {
		this("", x1, y1, x2, y2, null, null, 0, null, null, null);
	}
	
	public ClickableObject(String label, int x1, int y1, int x2, int y2) {
		this(label, x1, y1, x2, y2, null, null, 0, null, null, null);
	}
	
	
	
	public Card getLinkedCard() {
		return linkedCard;
	}
	
	public Deck getLinkedDeck() {
		return linkedDeck;
	}
	
	public Player getLinkedPlayer() {
		return linkedPlayer;
	}
	
	public String getLabel() {
		return label;
	}
	
	public int getX1(){
		return x1;
	}
	
	public int getY1(){
		return y1;
	}
	
	public int getX2(){
		return x2;
	}
	
	public int getY2(){
		return y2;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getLength() {
		return length;
	}
	
	
	public Card getCard() {
		return card;
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public void destroy(GameView view, int i) {
		view.removeClickableObject(i);
	}

	@Override
	public int hashCode() {
		return Objects.hash(length, positionInList, width, x1, x2, y1, y2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ClickableObject)) {
			return false;
		}
		ClickableObject other = (ClickableObject) obj;
		return length == other.length && positionInList == other.positionInList && width == other.width
				&& x1 == other.x1 && x2 == other.x2 && y1 == other.y1 && y2 == other.y2;
	}
	
	
	
}
