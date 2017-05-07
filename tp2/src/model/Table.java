package model;

import java.util.List;
import java.util.ArrayList;

public class Table {

    private List<Card> highestCards;

    private List<Card> cards;

    private int numberOfTeams;
    private int teamSize;

    private static final Card emptyCard = new Card(null,'0',0);


    public Table(int numberOfTeams, int teamSize){
        this.numberOfTeams = numberOfTeams;
        this.teamSize = teamSize;
        highestCards = new ArrayList<Card>(numberOfTeams);
        cards = new ArrayList<Card>(numberOfTeams*teamSize);

        for(int i = 0; i < numberOfTeams; i++){
            //empty card
            highestCards.add(emptyCard);
        }
    }

    public void addCard(Card card, int teamIndex){
        cards.add(card);
        System.out.println(highestCards);
        if(card.compareTo(highestCards.get(teamIndex)) > 0){
            highestCards.set(teamIndex,card);
        }
        System.out.println(highestCards);
    }

    public Card getHighestCard(int teamIndex){
        return highestCards.get(teamIndex);
    }

    public List<Card> getCardsOnTable(){
        return cards;
    }

    public void clearTable(){
        for(int i = 0; i < numberOfTeams; i++){
            highestCards.set(i,emptyCard);
        }
        cards.clear();
    }

    public boolean tie(){
        return highestCards.stream().allMatch(e -> (e.compareTo(highestCards.get(0)) == 0));
    }

    public Team getWinnerTeam(){
        return null;
    }

    public Player getWinnerPlayer(){
        return null;
    }

}
