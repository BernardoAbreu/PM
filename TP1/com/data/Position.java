package com.data;

public abstract class Position{

	private int type;

	public int getType(){
		return type;
	}

	public Position(int type){
		this.type = type;
	}

	public abstract void play(Player p);
}