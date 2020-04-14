package fr.dut.info.Card;

import java.awt.Image;

import fr.dut.info.Abilitys.*;

public class Ship extends AbstractCards{
	
	public Ship(String id, Image image, String name, String faction, int cost, String description, Primary primary, Ally ally, Scrap scrap) {
		super(id, image, name, faction, cost, description, primary, ally, scrap);
	}
	
	public Ship(String id, Image image, String name, String faction, int cost, Primary primary, Ally ally, Scrap scrap) {
		super(id, image, name, faction, cost,"", primary, ally, scrap);
	}
	
	public Ship(String id, Image image, String name, String faction, int cost, Primary primary, Ally ally) {
		super(id, image, name, faction, cost,"", primary, ally, new Scrap(0,0,0,0));
	}
	
	public Ship(String id, Image image, String name, String faction, int cost, Primary primary) {
		super(id, image, name, faction, cost,"", primary, new Ally(0,0,0,0), new Scrap(0,0,0,0));
	}
	
	public Ship(String id, Image image, String name, String faction, int cost, Primary primary, Scrap scrap) {
		super(id, image, name, faction, cost,"", primary, new Ally(0,0,0,0), scrap);
	}
	
	public Ship(String id, Image image, String name, String faction, int cost, Ally ally) {
		super(id, image, name, faction, cost,"", new Primary(0,0,0,0), ally, new Scrap(0,0,0,0));
	}
	
	public Ship(String id, Image image, String name, String faction, int cost, Scrap scrap) {
		super(id, image, name, faction, cost,"", new Primary(0,0,0,0), new Ally(0,0,0,0),scrap);
	}
	
	public Ship(String id, Image image, String name, String faction, int cost) {
		super(id, image, name, faction, cost,"", new Primary(0,0,0,0), new Ally(0,0,0,0), new Scrap(0,0,0,0));
	}
	
	@Override
	public String cardType() {
		return "Ship";
	}

	@Override
	public int getDefense() {
		return 0;
	}

	@Override
	public boolean getOutpost() {
		return false;
	}
	
	
	

	

}
