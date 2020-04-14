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
	private String description; // description de la carte
	
	// Différentes phases d'action
	private final Primary primary;
	private final Ally ally;
	private final Scrap scrap;
	
	public abstract String cardType(); // Renvoie le type de carte
	
	public AbstractCards(String id, Image image, String name, String faction, int cost, String description, Primary primary, Ally ally,
			Scrap scrap) {
		// Mettre un require non null
		this.id = id;
		
		this.image = image;
		this.name = name;
		this.faction = faction;
		this.cost = cost;
		this.description = description;
		this.primary = primary;
		this.ally = ally;
		this.scrap = scrap;
	}

	
	public String getId() {
		return id;
	}
	
	// Ajouter une description à la carte
	public void addDescription(String description) {
		this.description= description;
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
	
	public String getDescription() {
		return description;
	}
	
	public String getFaction() {
		return faction;
	}
	
	
	@Override
	public String toString() {
		return describeCard() + actionPhaseString();
	}
	
	public String describeCard() {
		StringBuilder str = new StringBuilder();
		
		str.append("\n----------------------------\nFaction: ");
		str.append(id+"\n");
		str.append(cardType()).append(" | ");
		str.append(name);
		str.append(faction).append("\nCost: ");
		str.append(cost).append("\n");
		if(description != "") {
			str.append("\nDescription: ");
			str.append(description).append("\n");
		}
		return str.toString();
	}
	
	// Print les actions selons les phases
	public String actionPhaseString() {
		StringBuilder str = new StringBuilder();
		str.append(primary.toString());
		str.append(ally.toString());
		str.append(scrap.toString());
		str.append("----------------------------\n");
		return str.toString();
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
				card.getDescription(),
				card.getPrimary().copyPrimary(card.getPrimary()),
				card.getAlly().copyAlly(card.getAlly()),
				card.getScrap().copyScrap(card.getScrap())
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
				card.getDescription(),
				card.getPrimary().copyPrimary(card.getPrimary()),
				card.getAlly().copyAlly(card.getAlly()),
				card.getScrap().copyScrap(card.getScrap())
				);	
	}
	
	
}
