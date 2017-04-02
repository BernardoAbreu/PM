package packagePosition;

import packagePlayer.Player;

public class RealState extends Position{

	private Player owner;

	private int id;

	private int value;

	private int rent;

	public RealState(int id, int value, int rent){
		super(3);
		this.id = id;
		this.owner = null;
		this.value = value;
		this.rent = rent;
	}

	public int getId(){
		return this.id;
	}

	public int getValue(){
		return this.value;
	}

	public int getRent(){
		return this.rent*this.value/100;
	}

	public void setOwner(Player p){
		this.owner = p;
	}

	public Player getOwner(){
		return this.owner;
	}

	public void play(Player p){
		if (this.getOwner() == null){
			if(p.getStatement() >= this.getValue()){
				p.withdraw(this.getValue());
				this.setOwner(p);
			}
		}
		else if (p.getId() != this.getOwner().getId()){
			this.getOwner().deposit(p.withdraw(this.getRent()));
		}
	}

}