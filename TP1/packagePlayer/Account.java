package packagePlayer;

public interface Account {

	public void deposit(int amount);

	public int withdraw(int amount);

	//TODO
	//Is it needed?
	public int getStatement();
}