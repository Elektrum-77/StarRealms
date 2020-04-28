package fr.dut.info.Card;

import java.awt.Image;

import fr.dut.info.Abilitys.*;

public class Ship extends AbstractCards{
	
	public Ship(String id, Image image, String name, String faction, int cost, Ability primary, Ability ally, Ability scrap) {
		super(id, image, name, faction, cost, primary, ally, scrap);
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
