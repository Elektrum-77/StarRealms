package fr.dut.info.Card;

import fr.dut.info.Abilitys.*;
import fr.dut.info.Game.GameBoard;
import java.awt.Image;

abstract class AbstractCards implements Card{
	
	private final String id;
	private final Image image;
	private final String name;
	private final String faction;
	private final int cost;
	
	// Différentes phases d'action
	private final Ability primary;
	private final Ability ally;
	private final Ability scrap;
	
	public abstract String cardType(); // Renvoie le type de carte
	
	public AbstractCards(String id, Image image, String name, String faction, int cost,  Ability primary, Ability ally, Ability scrap) {
		// Mettre un require non null
		this.id = id;
		
		this.image = image;
		this.name = name;
		this.faction = faction;
		this.cost = cost;
		this.primary = primary;
		this.ally = ally;
		this.scrap = scrap;
	}

	
	public String getId() {
		return id;
	}
	
	
	// Renvoie l'image de la carte
	public Image getImage() {
		return image;
	}
	
	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}
	
	
	public String getFaction() {
		return faction;
	}
	
	
	public Ability getPrimary() {
		return primary;
	}
	public Ability getAlly() {
		return ally;
	}
	public Ability getScrap() {
		return scrap;
	}
	
	public void resetAbilitys() {
		primary.setEnable();
		ally.setEnable();
		scrap.setEnable();
	}
	
	public void usePrimary(GameBoard gameBoard) {
		primary.use(gameBoard);
	}
	
	public void useAlly(GameBoard gameBoard) {
		ally.use(gameBoard);
	}
	
	public void useScrap(GameBoard gameBoard) {
		scrap.use(gameBoard);
		
	}
	
	// Renvoie true si les faction sont les mêmes
	public boolean isSameFaction(Card card) {
		return card.getFaction() == faction;
	}

	// Renvoie un nouveau vaisseau avec les même caractéristiques mais avec un nouvel id
	public Ship copyShip(GameBoard gameBoard, Card card) {
		return new Ship(
				gameBoard.getAnId(),
				card.getImage(),
				card.getName(),
				card.getFaction(),
				card.getCost(),
				card.getPrimary().copy(card.getPrimary()),
				card.getAlly().copy(card.getAlly()),
				card.getScrap().copy(card.getScrap())
				);
	}
	
	// Renvoie une nouvelle base avec les même caractéristiques mais avec un nouvel id
	public Base copyBase(GameBoard gameBoard, Card card) {
		return new Base(
				gameBoard.getAnId(),
				card.getImage(),
				card.getName(),
				card.getFaction(),
				card.getCost(),
				card.getDefense(),
				card.getOutpost(),
				card.getPrimary().copy(card.getPrimary()),
				card.getAlly().copy(card.getAlly()),
				card.getScrap().copy(card.getScrap())
				);	
	}
	
	
}
