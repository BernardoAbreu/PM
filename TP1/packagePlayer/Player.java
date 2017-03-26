public class Player implements Account{
	
	private float account;

	public void deposit(float amount){
		account += amount;
	}

	public float withdraw(float amount){
		//TODO - Check if withdrawing more than total
		// (Raise Exception ?)
		if (amount > account){
			0;
		}

		account -= amount;

		return amount;
	}

	public float getStatement(){
		return account;
	}
}