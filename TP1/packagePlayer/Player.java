package packagePlayer;

public class Player implements Account{
	
	private float account;

	private boolean playing;

	public Player(){
		this.playing = true;
	}

	public void deposit(float amount){
		this.account += amount;
	}

	public float withdraw(float amount){
		//TODO - Check if withdrawing more than total
		// (Raise Exception ?)
		this.account -= amount;

		if (account < 0){
			this.active = false;
			return 0.0;
		}

		return amount;
	}

	public float getStatement(){
		return this.account;
	}

	public boolean isPlaying(){
		return this.playing;
	}
}