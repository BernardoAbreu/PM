package com.data;

public class Player implements Account {
	
	private double account;

	private boolean playing;

	private int id;

	public Player(int id){
		this.id = id;
		this.playing = true;
	}

	@Override
	public void deposit(double amount){
		this.account += amount;
	}

	@Override
	public double withdraw(double amount){
		//TODO - Check if withdrawing more than total
		// (Raise Exception ?)
		this.account -= amount;

		if (account < 0){
			this.playing = false;
			return 0;
		}

		return amount;
	}

	@Override
	public double getStatement(){
		return this.account;
	}

	public int getId(){
		return this.id;
	}

	public boolean isPlaying(){
		return this.playing;
	}
}