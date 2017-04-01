package packagePosition;

import packagePlayer.Player;

public class Start extends Position{

	public Start(){
		super(1);
	}

	public void play(Player p){
		p.deposit(500.0);
	}
}