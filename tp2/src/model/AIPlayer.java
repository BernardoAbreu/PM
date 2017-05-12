package model;

/**
 * Created by math on 5/5/17.
 */
public class AIPlayer extends Player{

	public AIPlayer(int id){
        super(id);
    }

    @Override
    public void playCard(Table table) {
        System.out.println("Computer choosing");

        table.addCard(this.getHand().removeCard(0),this.getId(), this.getTeam().getId());

        // table.printTable();

    }

    @Override
    public Option getOption(){
        return Option.ACCEPT;
    }

    @Override
    public Option getOption(boolean b){
        return Option.ACCEPT;
    }
}
