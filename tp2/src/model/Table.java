package model;

import java.util.List;
import java.util.ArrayList;

public class Table {

    private List<Card> highestCards;

    private List<Card> cards;

    private int numberOfTeams;
    private int teamSize;

    public Table(int numberOfTeams, int teamSize){
        this.numberOfTeams = numberOfTeams;
        this.teamSize = teamSize;
        highestCards = new ArrayList<Card>(numberOfTeams);
        cards = new ArrayList<Card>(numberOfTeams*teamSize);

        for(int i = 0; i < numberOfTeams; i++){
            highestCards.add(null);
        }
    }

    public void addCard(Card card, int teamIndex){
        cards.add(card);
        if(highestCards.get(teamIndex) == null){
            highestCards.set(teamIndex,card);
        }
        else if(card.compareTo(highestCards.get(teamIndex)) > 0){
            highestCards.set(teamIndex,card);
        }
    }

    public Card getHighestCard(int teamIndex){
        return highestCards.get(teamIndex);
    }

    public List<Card> getCardsOnTable(){
        return cards;
    }

    public void clearTable(){
        for(int i = 0; i < numberOfTeams; i++){
            highestCards.set(i,null);
        }
        cards.clear();
    }

}
