package com.data;

import com.utils.Stats;

import java.util.ArrayList;
import java.util.Collection;

public class Player implements Account {
	
	private double statement;

	private boolean playing;

	private int position;

	private int id;

	private Collection<RealEstate> properties;

	public Player(int id){
		this.id = id;
		this.playing = true;
		this.position = 1;
		properties = new ArrayList<RealEstate>();
	}

	public int getPosition() {
		return position;
	}

	public boolean walk(int dice, int boardSize) {
		int oldpos = this.position;
		int newpos = oldpos + dice;
		this.position = newpos % boardSize;
		if(newpos >= boardSize) return true;
		else return false;
	}

	@Override
	public void deposit(double amount){
		this.statement += amount;
	}

	@Override
	public double withdraw(double amount){
		this.statement -= amount;
		if (statement < 0){
			this.playing = false;
			return 0;
		}
		return amount;
	}

	@Override
	public double getStatement(){
		return this.statement;
	}

	public int getId(){
		return this.id;
	}

	public boolean isPlaying(){
		return this.playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public boolean acquireProperty(RealEstate property){
		if(this.getStatement() > property.getValue()){
			this.withdraw(property.getValue());
			property.setOwner(this);
			this.properties.add(property);
			System.out.println("Player: " + String.valueOf(this.id) + "  Owns property: " + property.getId());
			return true;
		}

		return false;
	}

	public void leaveGame() {
		System.out.println("Player: " + String.valueOf(this.id) + "  has left the game.");
		this.withdraw(this.getStatement());
		this.setPlaying(false);
		for(RealEstate p : this.properties){
            p.setOwner(null);
            this.properties.remove(p);
        }
	}

	public boolean payRent(Player p, RealEstate property){
		if(this.getStatement() > property.getValue() * property.getRent()){
			//pay rent
			this.withdraw(property.getValue() * property.getRent());
			p.deposit(property.getValue() * property.getRent());
			System.out.println("Player: " + String.valueOf(this.getId()) + "  pays rent to:  " + String.valueOf(p.getId()));
			return true;
		}

		return false;
	}

}