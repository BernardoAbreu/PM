public class Player implements Account{
	
	private float account;

	private boolean active;

	public Player(){
		this.active = true;
	}

	public void deposit(float amount){
		this.account += amount;
	}

	public float withdraw(float amount){
		//TODO - Check if withdrawing more than total
		// (Raise Exception ?)
		if (amount > account){
			this.active = false;
		}

		this.account -= amount;

		return amount;
	}

	public float getStatement(){
		return this.account;
	}

	public boolean isActive(){
		return this.active;
	}
}