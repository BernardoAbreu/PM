package packagePosition;

import packagePlayer.Player;

public abstract class Position{

	private int type;

	public int getType(){
		return type;
	}

	public Position(int type){
		this.type = type;
	}

	public void play(Player);
}