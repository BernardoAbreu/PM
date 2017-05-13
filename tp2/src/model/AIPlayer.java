package model;


import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
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
        int removeIndex = 0;
        if(table.isEmpty()){
            // Play at random
            removeIndex = 0;
        }
        else{
            Card thisTeamCard = table.getHighestCard(this.getTeam().getId());
            System.out.println("thisTeamCard: " + thisTeamCard);
            Card maxInTable = table.getMaxCardInTable();
            System.out.println("maxInTable: " + maxInTable);
            System.out.println();

            List<Card> myCards = new ArrayList<Card>();
            Card toRemove;

            for(Card c : this.getHand()){
                myCards.add(c);
            }

            Collections.sort(myCards);

            //Get smallest card to remove
            toRemove = myCards.get(0);

            //Check if it is losing or in a tie
            if(thisTeamCard.compareTo(maxInTable) <= 0){
                for(Card c : myCards){
                    // Get Smallest Card that wins
                    if(c.compareTo(maxInTable) > 0){
                        toRemove = c;
                        break;
                    }
                }
            }
            int i = 0;
            for(Card c : this.getHand()){
                if(c.compareTo(toRemove) == 0){
                    removeIndex = i;
                    break;
                }
                i++;
            }
        }
        table.addCard(this.getHand().removeCard(removeIndex),this.getId(), this.getTeam().getId());

    }

    @Override
    public Option getOption(){
        return Option.ACCEPT;
    }

    @Override
    public Option getOption(boolean max){
        Option maxPlay = ((max) ? Option.ACCEPT : Option.RAISE);

        List<Card> myCards = new ArrayList<Card>();
        Card maxCard;

        for(Card c : this.getHand()){
            myCards.add(c);
        }

        maxCard = Collections.max(myCards);

        if(maxCard.getTrucoValue() > 10){
            return maxPlay;
        }
        else if(maxCard.getTrucoValue() > 6){
            return Option.ACCEPT;
        }
        else {
            return Option.FOLD;
        }
    }
}
