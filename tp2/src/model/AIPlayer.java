package model;

/**
 * Created by math on 5/5/17.
 */
public class AIPlayer extends Player{

	public AIPlayer(int id){
        super(id);
    }

    @Override
    public void playCard() {
        System.out.println("Computer choosing");

    }

    @Override
    public Option getOption(){
        return Option.RAISE;
    }
}
