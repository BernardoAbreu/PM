package packagePlayer;

public class Player implements Account{
	
	private int account;

	private boolean playing;

	private int id;

	public Player(int id){
		this.id = id;
		this.playing = true;
	}

	public void deposit(int amount){
		this.account += amount;
	}

	public int withdraw(int amount){
		//TODO - Check if withdrawing more than total
		// (Raise Exception ?)
		this.account -= amount;

		if (account < 0){
			this.playing = false;
			return 0;
		}

		return amount;
	}

	public int getStatement(){
		return this.account;
	}

	public int getId(){
		return this.id;
	}

	public boolean isPlaying(){
		return this.playing;
	}
}