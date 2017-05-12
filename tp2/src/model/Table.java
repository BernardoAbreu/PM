package model;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Table implements Iterable<Card>{

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
        
        playerCards.put(playerId, card);

        Map<Integer, Card> cards = teamCards.get(teamId);

        //Team does not exists yet
        if (cards == null){
            cards = new LinkedHashMap<Integer, Card>(teamSize);
            teamCards.put(teamId, cards);
        }

        cards.put(playerId, card);
    }


    public Card getHighestCard(int teamId){
        return maxCardInMap(teamCards.get(teamId)).getValue();
    }


    public void clearTable(){
        playerCards.clear();
        teamCards.clear();
    }


    public boolean tie(){

        if(teamCards.size() > 0){

            Iterator<Map.Entry<Integer, Map<Integer, Card>> > teamIter = teamCards.entrySet().iterator();
            Card lastCard = maxCardInTeam(teamIter.next());
            while (teamIter.hasNext()) {
                Card currentCard = maxCardInTeam(teamIter.next());;
                if(currentCard.compareTo(lastCard) != 0){
                    return false;
                }
                lastCard = currentCard;
            }
        }
        return true;
    }


    private Map.Entry<Integer,Card> maxCardInMap(Map<Integer, Card> map){
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue());
    }


    private Card maxCardInTeam(Map.Entry<Integer, Map<Integer, Card>> entry){
        return maxCardInMap(entry.getValue()).getValue();
    }


    public int getWinnerTeamId(){
        return Collections.max(teamCards.entrySet(), Comparator.comparing(e -> maxCardInTeam(e))).getKey();
    }


    public int getWinnerPlayerId(){
        return maxCardInMap(teamCards.get(getWinnerTeamId())).getKey();
    }


    // public void printTable(){
    //     System.out.println("Printing table:");
    //     System.out.println(playerCards);
    //     // System.out.println(teamCards);
        
    //     // Iterator<Map.Entry<Integer, Map<Integer, Card>> > teamIter = teamCards.entrySet().iterator();
    //     // while (teamIter.hasNext()) {
    //     //     Map.Entry<Integer, Map<Integer, Card>> teamEntry = teamIter.next();
    //     //     System.out.println(maxCardInMap(teamEntry.getValue()).getKey());
    //     // }
    //     // System.out.println("Winning Team: " + getWinnerTeamId());
    //     // System.out.println("Winning Player: " + getWinnerPlayerId());

    // }

    private class TableIterator implements Iterator<Card> {
        private Iterator< Map.Entry<Integer, Card>> playerIter;
        private Card card;

        public TableIterator(Table table) {
            this.playerIter = table.playerCards.entrySet().iterator();
        }

        public boolean hasNext() {
            return this.playerIter.hasNext();
        }

        public Card next() {
            if (!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            
            return playerIter.next().getValue();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Card> iterator(){
        return new TableIterator(this);
    }

}
