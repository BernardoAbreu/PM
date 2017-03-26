public class RealState extends Position{

	private int owner;

	private int id;

	private int value;

	private int rent;

	public RealState(int value, int rent){
		this.type = 3;
		this.owner = 0;
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
		return this.rent;
	}
}