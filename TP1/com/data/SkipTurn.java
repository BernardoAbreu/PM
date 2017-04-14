package com.data;

import com.utils.Stats;

public class SkipTurn extends Position{

	public SkipTurn(){
		super(2);
	}

	public boolean play(Player player, Stats[] stats){
		stats[player.getId()].incnSkip();
		return true;
	}
}