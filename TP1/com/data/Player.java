package com.data;

import com.utils.Stats;

public class Player implements Account {
	
	private double statement;

	private boolean playing;

	private int position;

	private int id;

	private Stats s;

	public Player(int id){
		this.id = id;
		this.playing = true;
		this.position = 0;
		this.s = new Stats();
	}

	public int getPosition() {
		return position;
	}

	public void walk(int dice, int boardSize) {
		this.position += dice;
		if(this.position > boardSize) this.position = this.position % boardSize;
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

}