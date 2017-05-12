package controller;

import java.util.List;
import java.util.ArrayList;

import model.*;

public class FsmPlay {

    private State[] states = {   
                                new Initial(),             //0
                                new FirstRoundTie(),       //1
                                new SecondRoundTie(),      //2
                                new FirstTeamWonFirst(),   //3
                                new SecondTeamWonFirst(),  //4
                                new PlayTieFirstWinner(),  //5
                                new PlayTieSecondWinner(), //6
                                new FirstTeamWon(),        //7
                                new SecondTeamWon(),       //8
                                new NoWinner(),            //9
                            };

    private int transitions[][] = {
                                    {1, 3, 4, 7, 8},
                                    {2, 7, 8, 7, 8},
                                    {9, 7, 8, 7, 8},
                                    {7, 5, 7, 7, 8},
                                    {8, 6, 8, 7, 8},
                                    {7, 8, 7, 7, 8},
                                    {8, 7, 8, 7, 8},
                                };

    private int teamIds[] = {0,0,0,1,0,1,0};

    private int current;
    private boolean end;

    private List<Player> players;
    private int firstIndex;
    private int currentPlayerIndex;
    private int winner;

    private PlayValue playValue;

    private Table table;

    public FsmPlay() {
        this.current = 0;
        this.end = false;
        this.players = new ArrayList<Player>();
        this.winner = -1;
    }

    public void setState(int state) {
        current = transitions[current][state];
    }

    public void change() {
        states[current].change(this);
    }

    public boolean end(){
        return this.end;
    }

    public int getWinnerTeamId(){
        return this.winner;
    }

    public void addPlayer(Player p){
        this.players.add(p);
    }

    public void setFirstIndex(int index){
        this.firstIndex = index;
    }

    public void setTable(Table table){
        this.table = table;
    }

    public void setPlayValue(PlayValue value){
        this.playValue = value;
    }

    public PlayValue getPlayValue(){
        return this.playValue;
    }

    List<Player> getPlayers(){
        return this.players;
    }

    Table getTable(){
        return this.table;
    }

    int getFirstIndex(){
        return this.firstIndex;
    }

    int getTeamId(){
        return teamIds[current];
    }

    void setEnd(){
        this.end = true;
    }

    void setWinner(int teamIndex){
        this.winner = teamIndex;
    }

    void setNoWinner(){
        this.winner = -1;
    }

    int getIndexOfPlayer(int id){
        int playerIndex = 0;
        for (Player p : this.players){
            if(p.getId() == id){
                break;
            }
            playerIndex++;
        }
        return playerIndex;
    }

    //Return winning team if fold, otherwise null
    Team round(int firstPlayerIndex){
        this.table.clearTable();

        Player currentPlayer, nextPlayer;

        currentPlayer = this.players.get(firstPlayerIndex);
        firstPlayerIndex = (firstPlayerIndex+1) % this.players.size();
        nextPlayer = this.players.get(firstPlayerIndex);

        for(int i = 0; i < players.size(); i++){

            FsmRound chain = new FsmRound(currentPlayer, nextPlayer);
            chain.setTable(this.table);
            chain.setValue(this.playValue);

            while (!chain.end()) {
                chain.choose();
            }

            Team t = chain.getTeamThatWonFold();
            if(t != null){
                return t;
            }
            
            this.setPlayValue(chain.getValue());

            currentPlayer = this.players.get(firstPlayerIndex);
            firstPlayerIndex = (firstPlayerIndex+1) % this.players.size();
            nextPlayer = this.players.get(firstPlayerIndex);

            System.out.print("Table: ");
            for(Card c: table){
                System.out.print(c + " ");
            }
            System.out.println();

        }

        return null;
    }

}


abstract class State {

    public void change(FsmPlay wrapper) {
        printCurrent();

        Team winnerTeam = wrapper.round(wrapper.getFirstIndex());
                    
        if(winnerTeam != null){
            wrapper.setState(winnerTeam.getId()+3);
        }
        else{
            nextState(wrapper);
        }
    }

    public abstract void printCurrent();

    public void nextState(FsmPlay wrapper){
        Table table = wrapper.getTable();
        if(table.tie()){
            wrapper.setState(0);
        }
        else{
            int winnerTeamId = table.getWinnerTeamId();
            wrapper.setFirstIndex(wrapper.getIndexOfPlayer(table.getWinnerPlayerId()));

            if(winnerTeamId == wrapper.getTeamId()){
                wrapper.setState(1);
            }
            else{
                wrapper.setState(2);
            }
        }
    }

}

class Initial extends State {

    public void printCurrent(){
        System.out.println( "Initial" );
    }
}

class FirstTeamWonFirst extends State {

    public void printCurrent(){
        System.out.println( "FirstTeamWonFirst" );
    }
}


class SecondTeamWonFirst extends State {

    public void printCurrent(){
        System.out.println("SecondTeamWonFirst");
    }

}


class PlayTieFirstWinner extends State{

    public void printCurrent(){
        System.out.println("PlayTieFirstWinner");
    }


}

class PlayTieSecondWinner extends State{

    public void printCurrent(){
        System.out.println("PlayTieSecondWinner");
    }

}


class FirstRoundTie extends State {

    @Override
    public void change(FsmPlay wrapper) {
        printCurrent();
        Table table = wrapper.getTable();

        table.clearTable();

        //TODO
        // getMaxCards();
        for(Player p : wrapper.getPlayers()){
            Card c = p.getHand().removeMaxCard();
            table.addCard(c, p.getId(), p.getTeam().getId());
        }

        for(Card c : table){
            System.out.println(c + " ");
        }
        System.out.println();
        nextState(wrapper);

    }

    public void printCurrent(){
        System.out.println("FirstRoundTie");
    }

}

class SecondRoundTie extends State {

    @Override
    public void change(FsmPlay wrapper) {
        printCurrent();
        Table table = wrapper.getTable();

        table.clearTable();

        //TODO
        for(Player p : wrapper.getPlayers()){
            Card c = p.getHand().removeMaxCard();
            table.addCard(c, p.getId(), p.getTeam().getId());
        }

        for(Card c : table){
            System.out.println(c + " ");
        }
        System.out.println();

        nextState(wrapper);

    }

    public void printCurrent(){
        System.out.println("SecondRoundTie");
    }


}


class FirstTeamWon extends State {
    @Override
    public void change(FsmPlay wrapper) {
        System.out.println("FirstTeamWon");
        wrapper.setWinner(0);
        wrapper.setEnd();
    }

    public void printCurrent(){
        System.out.println("FirstTeamWon");
    }

}

class SecondTeamWon extends State {
    @Override
    public void change(FsmPlay wrapper) {
        System.out.println("SecondTeamWon");
        wrapper.setWinner(1);
        wrapper.setEnd();
    }

    public void printCurrent(){
        System.out.println("SecondTeamWon");
    }

}

class NoWinner extends State {
    @Override
    public void change(FsmPlay wrapper) {
        System.out.println("NoWinner");
        wrapper.setNoWinner();
        wrapper.setEnd();
    }

    public void printCurrent(){
        System.out.println("NoWinner");
    }

}
