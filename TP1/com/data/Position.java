package com.data;

import com.utils.Stats;

public abstract class Position{

	private int type;

	public int getType(){
		return type;
	}

	public Position(int type){
		this.type = type;
	}

	public abstract boolean play(Player player, Stats[] s);
}