package fr.dut.info.Card;

import java.awt.Image;

import fr.dut.info.Abilitys.*;


public class Base extends AbstractCards{
	
	private int defense;
	private final boolean outpost;
	
	
	public Base(String id, Image image, String name, String faction, int cost, int defense, boolean outpost, Ability primary, Ability ally, Ability scrap) {
		super(id, image, name, faction, cost, primary, ally, scrap);
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
	
}
