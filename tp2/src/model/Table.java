package model;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;

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


    private boolean cardExistsOnAnotherTeam(Card card, int currentTeamId){
        boolean exists = false;

        Iterator<Map.Entry<Integer, Map<Integer, Card>> > teamIter = teamCards.entrySet().iterator();
        
        while (teamIter.hasNext()) {
        
            Map.Entry<Integer, Map<Integer, Card>> teamEntry = teamIter.next();
        
            if(teamEntry.getKey() != currentTeamId){
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
        return exists;
    }

    
    public void addCard(Card card, int playerId, int teamId){
        // System.out.println("Entering addCard");
        // System.out.println(teamCards);
        
        playerCards.put(playerId, card);


        if(!cardExistsOnAnotherTeam(card, teamId)){
            Map<Integer, Card> cards = teamCards.get(teamId);

            //Team does not exists yet
            if (cards == null){
                cards = new LinkedHashMap<Integer, Card>(teamSize);
                teamCards.put(teamId, cards);
            }

            cards.put(playerId, card);
        }

        // System.out.println("Exiting addCard");
        // System.out.println(teamCards);
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

    private Card maxCardInTeam(Map.Entry<Integer, Map<Integer, Card>> entry){
        return maxCardInMap(entry.getValue()).getValue();
    }

    public int getWinnerTeamId(){
        return Collections.max(teamCards.entrySet(), Comparator.comparing(e -> maxCardInTeam(e))).getKey();
        // return Collections.max(teamCards.entrySet(), (entry1, entry2) -> 
        //     maxCardInTeam(entry1).compareTo(maxCardInTeam(entry2))).getKey();
    }

    public int getWinnerPlayerId(){
        // System.out.println(teamCards);
        // System.out.println(getWinnerTeamId());
        // System.out.println(teamCards.get(getWinnerTeamId()));
        // System.out.println(maxCardInMap(teamCards.get(getWinnerTeamId())).getKey());
        return maxCardInMap(teamCards.get(getWinnerTeamId())).getKey();
    }

    private Map.Entry<Integer,Card> maxCardInMap(Map<Integer, Card> map){
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue());
    }

    public void printTable(){
        System.out.println("Printing table:");
        System.out.println(playerCards);
        // System.out.println(teamCards);
        
        // Iterator<Map.Entry<Integer, Map<Integer, Card>> > teamIter = teamCards.entrySet().iterator();
        // while (teamIter.hasNext()) {
        //     Map.Entry<Integer, Map<Integer, Card>> teamEntry = teamIter.next();
        //     System.out.println(maxCardInMap(teamEntry.getValue()).getKey());
        // }
        // System.out.println("Winning Team: " + getWinnerTeamId());
        // System.out.println("Winning Player: " + getWinnerPlayerId());

    }

}
