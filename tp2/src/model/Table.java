package model;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Collections;

public class Table {

    private Map<Integer, Map<Integer, Card> > teamCards;

    private Map<Integer, Card> playerCards;

    private int numberOfTeams;
    private int teamSize;

    private static final Card emptyCard = new Card(null,'0',0);


    public Table(int numberOfTeams, int teamSize){
        this.numberOfTeams = numberOfTeams;
        this.teamSize = teamSize;
        teamCards = new HashMap<Integer, Map<Integer, Card>>(numberOfTeams);
        playerCards = new HashMap<Integer, Card>(numberOfTeams*teamSize);


    }

    public void addCard(Card card, int playerId, int teamId){
        System.out.println("Entering addCard");
        playerCards.put(playerId, card);

        System.out.println(teamCards);
        boolean exists = false;
        Iterator<Map.Entry<Integer, Map<Integer, Card>> > teamIter = teamCards.entrySet().iterator();
        while (teamIter.hasNext()) {
            Map.Entry<Integer, Map<Integer, Card>> teamEntry = teamIter.next();
            if(teamEntry.getKey() != teamId){
                Iterator<Map.Entry<Integer,Card>> iter = teamEntry.getValue().entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<Integer,Card> entry = iter.next();
                    if(card.compareTo(entry.getValue()) == 0){
                        iter.remove();
                        exists = true;
                        break;
                    }
                }
            }
            if(teamEntry.getValue().isEmpty()){
                teamIter.remove();
            }
        }

        if(!exists){
            Map<Integer, Card> cards = teamCards.get(teamId);
            if (cards == null){
                cards = new LinkedHashMap<Integer, Card>(teamSize);
                teamCards.put(teamId, cards);
            }
            cards.put(playerId, card);
        }

        System.out.println("Exiting addCard");
        System.out.println(teamCards);
    }

    public Card getHighestCard(int teamId){
        return teamCards.get(teamId).get(0);
    }


    public void clearTable(){
        playerCards.clear();
        teamCards.clear();
    }

    public boolean tie(){
        return teamCards.isEmpty();

    }

    public int getWinnerTeamId(){
        return Collections.max(teamCards.entrySet(), (entry1, entry2) -> 
            maxCardInMap(entry1.getValue()).getValue().compareTo(maxCardInMap(entry2.getValue()).getValue())).getKey();
    }

    public int getWinnerPlayerId(){
        return maxCardInMap(teamCards.get(getWinnerTeamId())).getKey();
    }

    public void printTable(){
        System.out.println(playerCards);
        System.out.println(teamCards);
        
        Iterator<Map.Entry<Integer, Map<Integer, Card>> > teamIter = teamCards.entrySet().iterator();
        while (teamIter.hasNext()) {
            Map.Entry<Integer, Map<Integer, Card>> teamEntry = teamIter.next();
            System.out.println(maxCardInMap(teamEntry.getValue()).getKey());
        }
        System.out.println("Winning Team: " + getWinnerTeamId());
        System.out.println("Winning Player: " + getWinnerPlayerId());

    }

    private Map.Entry<Integer,Card> maxCardInMap(Map<Integer, Card> map){
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue());
    }
}
