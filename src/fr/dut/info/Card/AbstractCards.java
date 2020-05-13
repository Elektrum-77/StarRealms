package fr.dut.info.Card;

import fr.dut.info.Abilitys.*;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

import java.awt.Image;

abstract class AbstractCards implements Card{
	
	final String id;
	final Image image;
	final String name;
	final String faction;
	final int cost;
	
	// Différentes phases d'action
	final Ability primary;
	final Ability ally;
	final Ability scrap;
	
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

	@Override
	public String getId() { return id; }

	@Override
	public Image getImage() { return image; }

	@Override
	public String getName() { return name; }

	@Override
	public int getCost() { return cost;	}

	@Override
	public String getFaction() { return faction; }

	@Override
	public boolean hasPrimary() { return (primary != null); }
	@Override
	public boolean hasAlly() { return (ally != null); }
	@Override
	public boolean hasScrap() { return (scrap != null); }

	@Override
	public void usePrimary(GameBoard gameBoard) { primary.use(gameBoard); }
	@Override
	public void useAlly(GameBoard gameBoard) { ally.use(gameBoard); }
	@Override
	public void useScrap(GameBoard gameBoard) { scrap.use(gameBoard); }


	@Override
	public int getDefense() {
		return 0;
	}
	
	@Override
	public void init(GameBoard gameBoard) {
		primary.init(gameBoard);
		ally.init(gameBoard);
		scrap.init(gameBoard);
	}
	
	// Renvoie true si les faction sont les mêmes
	public boolean isSameFaction(Card card) {
		return card.getFaction().equals(faction);
	}
}
