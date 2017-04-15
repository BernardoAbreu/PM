package com.data;

import com.utils.Stats;

public class SkipTurn implements Position{


	public boolean play(Player player, Stats[] stats){
		stats[player.getId()].incnSkip();
		return true;
	}
}