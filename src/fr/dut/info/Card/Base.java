package fr.dut.info.Card;

import java.awt.Image;

import fr.dut.info.Abilitys.*;


public class Base extends AbstractCards{
	
	private int defense;
	private final boolean outpost;
	
	
	public Base(String id, Image image, String name, String faction, int cost, int defense, boolean outpost, String description, Primary primary, Ally ally, Scrap scrap) {
		super(id, image, name, faction, cost, description, primary, ally, scrap);
		this.defense= defense;
		this.outpost=outpost;
	}
	
	public Base(String id, Image image, String name, String faction, int cost, int defense, boolean outpost, Primary primary, Ally ally,
			Scrap scrap) {
		this(id, image, name, faction, cost,defense,outpost, "", primary, ally, scrap);
		this.defense= defense;
	}
	
	public Base(String id, Image image, String name, String faction, int cost, int defense, boolean outpost, Primary primary, Ally ally) {
		super(id, image, name, faction, cost,"", primary, ally, new Scrap(0,0,0,0));
		this.defense= defense;
		this.outpost=outpost;
	}
	
	public Base(String id, Image image, String name, String faction, int cost, int defense, boolean outpost, Primary primary) {
		super(id, image, name, faction, cost,"", primary, new Ally(0,0,0,0), new Scrap(0,0,0,0));
		this.defense= defense;
		this.outpost=outpost;
	}
	
	public Base(String id, Image image, String name, String faction, int cost, int defense, boolean outpost, Primary primary, Scrap scrap) {
		super(id, image, name, faction, cost,"", primary, new Ally(0,0,0,0), scrap);
		this.defense= defense;
		this.outpost=outpost;
	}
	
	public Base(String id, Image image, String name, String faction, int cost, int defense, boolean outpost, Ally ally) {
		super(id, image, name, faction, cost,"", new Primary(0,0,0,0), ally, new Scrap(0,0,0,0));
		this.defense= defense;
		this.outpost=outpost;
	}
	
	public Base(String id, Image image, String name, String faction, int cost, int defense, boolean outpost, Scrap scrap) {
		super(id, image, name, faction, cost,"", new Primary(0,0,0,0), new Ally(0,0,0,0),scrap);
		this.defense= defense;
		this.outpost=outpost;
	}
	
	
	public Base(String id, Image image, String name, String faction, int cost, int defense, boolean outpost) {
		super(id, image, name, faction, cost,"", new Primary(0,0,0,0), new Ally(0,0,0,0), new Scrap(0,0,0,0));
		this.defense= defense;
		this.outpost=outpost;
	}
	
	@Override
	public String cardType() {
		return "Base";
	}
	
	@Override
	public int getDefense() {
		return defense;
	}
	
	@Override
	public boolean getOutpost() {
		return outpost;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(super.describeCard());
		str.append("Defense: ").append(defense).append("\n");
		if(outpost) {
			str.append("Outpost\n");
		}
		str.append(super.actionPhaseString());
		return str.toString();
	}
}
