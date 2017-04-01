package com.packagePosition;

import com.packagePlayer.Player;

public class SkipTurn extends Position{

	public SkipTurn(){
		super(2);
	}

	public void play(Player p){
		return;
	}
}