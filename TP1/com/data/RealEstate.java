package com.data;

import com.utils.Stats;

public class RealEstate extends Position{

	static final int RESIDENCE = 1;
	static final int COMMERCE = 2;
	static final int INDUSTRY = 3;
	static final int HOTEL = 4;
	static final int HOSPITAL = 5;

	private int realEstateType;

	private Player owner;

	private int id;

	private int value;

	private double rent;

	public RealEstate(int id, int value, double rent, int realEstateType){
		super(3);
		this.id = id;
		this.owner = null;
		this.value = value;
		this.rent = rent;
		this.realEstateType = realEstateType;
	}

	public int getId(){
		return this.id;
	}

	public int getValue(){
		return this.value;
	}

	public double getRent(){
		return this.rent;
	}

	public void setOwner(Player p){
		this.owner = p;
	}

	public Player getOwner(){
		return this.owner;
	}

	public boolean play(Player player, Stats[] stats){
		if(this.getOwner() == null) {
			//BUY FROM BANK
			if(player.acquireProperty(this)) {//Nothing happens if player can't afford the property, they just stay there
				stats[player.getId()].incBoughtValue(this.getValue());
			}
		}
		else {
			//PAY RENT
			if(this.getOwner().getId() != player.getId()) {
				Player owner = this.getOwner();
				if(!player.payRent(owner,this)) { player.leaveGame(); return false;}
				else{
					stats[player.getId()].incRentPaid(this.getValue()*this.getRent());
					stats[owner.getId()].incRentReceived(this.getValue()*this.getRent());
				}
			}
		}
		return true;
	}

}