package com.data;

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

	private int rent;

	public RealEstate(int id, int value, int rent, int realEstateType){
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

	public int getRent(){
		return this.rent;
	}

	public void setOwner(Player p){
		this.owner = p;
	}

	public Player getOwner(){
		return this.owner;
	}

	public void play(Player p){
		if (this.getOwner() == null){
			if(p.getStatement() >= this.getValue()){
				p.withdraw(this.getValue());
				this.setOwner(p);
			}
		}
		else if (p.getId() != this.getOwner().getId()){
			this.getOwner().deposit(p.withdraw(this.getRent()));
		}
	}

	public int getRealEstateType() {
		return realEstateType;
	}

	public void setRealEstateType(int realEstateType) {
		this.realEstateType = realEstateType;
	}

}