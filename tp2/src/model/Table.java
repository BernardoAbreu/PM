package model;

import view.TableObserver;
import java.util.*;

public class Table extends Observable implements Iterable< Map.Entry<Integer, Card>>{

    private Map<Integer, Map<Integer, Card> > teamCards;

    private Map<Integer, Card> playerCards;

    private int numberOfTeams;
    private int teamSize;

    private static final Card emptyCard = new Card(null,'0',0);

    private Observer observer;

    public Table(int numberOfTeams, int teamSize){
        this.numberOfTeams = numberOfTeams;
        this.teamSize = teamSize;
        this.teamCards = new HashMap<Integer, Map<Integer, Card>>(numberOfTeams);
        this.playerCards = new HashMap<Integer, Card>(numberOfTeams*teamSize);
        this.observer = TableObserver.getInstance();
    }


    public void addCard(Card card, int playerId, int teamId){
        
        this.playerCards.put(playerId, card);

        Map<Integer, Card> cards = this.teamCards.get(teamId);

        //Team does not exists yet
        if (cards == null){
            cards = new LinkedHashMap<Integer, Card>(this.teamSize);
            this.teamCards.put(teamId, cards);
        }

        cards.put(playerId, card);

        notifyObservers();
    }


    public Card getHighestCard(int teamId){
        Map.Entry<Integer,Card> maxEntry = maxCardInMap(this.teamCards.get(teamId));
        if(maxEntry == null){
            return this.emptyCard;
        }
        else{
            return maxEntry.getValue();
        }
    }


    public void clearTable(){
        this.playerCards.clear();
        this.teamCards.clear();
    }


    public boolean isEmpty(){
        return this.playerCards.isEmpty();
    }


    public boolean tie(){

        if(this.teamCards.size() > 0){

            Iterator<Map.Entry<Integer, Map<Integer, Card>> > teamIter = this.teamCards.entrySet().iterator();
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
        if(map == null){
            return null;
        }
        else{
            return Collections.max(map.entrySet(), Map.Entry.comparingByValue());
        }
    }


    private Card maxCardInTeam(Map.Entry<Integer, Map<Integer, Card>> entry){
        Map.Entry<Integer,Card> maxEntry = maxCardInMap(entry.getValue());
        if(maxEntry == null){
            return this.emptyCard;
        }
        else{
            return maxEntry.getValue();
        }
    }


    public int getWinnerTeamId(){
        return Collections.max(this.teamCards.entrySet(), Comparator.comparing(e -> maxCardInTeam(e))).getKey();
    }


    public Card getMaxCardInTable(){
        if(this.playerCards.isEmpty()){
            return this.emptyCard;
        }
        else{
            return Collections.max(this.playerCards.entrySet(), Map.Entry.comparingByValue()).getValue();
        }
    }


    public int getWinnerPlayerId(){
        Map.Entry<Integer,Card> entry = maxCardInMap(teamCards.get(getWinnerTeamId()));
        if (entry == null){
            return -1;
        }
        else{
            return entry.getKey();
        }
    }

    private class TableIterator implements Iterator<Map.Entry<Integer, Card>> {

        private Iterator< Map.Entry<Integer, Card>> playerIter;
        private Card card;

        public TableIterator(Table table) {
            this.playerIter = table.playerCards.entrySet().iterator();
        }

        public boolean hasNext() {
            return this.playerIter.hasNext();
        }

        public Map.Entry<Integer, Card> next() {
            if (!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            
            return playerIter.next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    @Override
    public Iterator<Map.Entry<Integer, Card>> iterator(){
        return new TableIterator(this);
    }

    @Override
    public void notifyObservers(){
        this.observer.update(this, null);
    }
}
