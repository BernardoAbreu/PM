package controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.*;
import view.PlayObserver;

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

        }

        return null;
    }

}


abstract class State extends Observable{

    protected Observer observer;

    public void change(FsmPlay wrapper) {
//        printCurrent();
        notifyObservers();
        Team winnerTeam = wrapper.round(wrapper.getFirstIndex());
                    
        if(winnerTeam != null){
            wrapper.setState(winnerTeam.getId()+3);
        }
        else{
            nextState(wrapper);
        }
    }

    public abstract void notifyObservers();

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
    public Initial() {
        this.observer = PlayObserver.getInstance();
    }

    public void notifyObservers(){
        super.observer.update(this, "Rodada Inicial");
    }
}

class FirstTeamWonFirst extends State {
    public FirstTeamWonFirst() {
        this.observer = PlayObserver.getInstance();
    }

    public void notifyObservers(){
        super.observer.update(this, "Primeiro time venceu a primeira rodada.");
    }
}


class SecondTeamWonFirst extends State {
    public SecondTeamWonFirst() {
        this.observer = PlayObserver.getInstance();
    }

    public void notifyObservers(){
        super.observer.update(this, "Segundo time venceu a primeira rodada.");
    }

}


class PlayTieFirstWinner extends State{
    public PlayTieFirstWinner() {
        this.observer = PlayObserver.getInstance();
    }

    public void notifyObservers(){
        super.observer.update(this, "Segundo time ganhou a segunda rodada. Os times estão empatados na jogada.");
    }
}

class PlayTieSecondWinner extends State{
    public PlayTieSecondWinner() {
        this.observer = PlayObserver.getInstance();
    }

    public void notifyObservers(){
        super.observer.update(this, "Primeiro time ganhou a segunda rodada. Os times estão empatados na jogada.");
    }
}


class FirstRoundTie extends State {
    public FirstRoundTie() {
        this.observer = PlayObserver.getInstance();
    }

    public void notifyObservers(){
        super.observer.update(this, "Empate na primeira rodada");
    }
    @Override
    public void change(FsmPlay wrapper) {
        Table table = wrapper.getTable();

        table.clearTable();

        for(Player p : wrapper.getPlayers()){
            Card c = p.getHand().removeMaxCard();
            table.addCard(c, p.getId(), p.getTeam().getId());
        }
        nextState(wrapper);
    }
}

class SecondRoundTie extends State {
    public SecondRoundTie() {
        this.observer = PlayObserver.getInstance();
    }

    public void notifyObservers(){
        super.observer.update(this, "Empate na segunda rodada, sendo que ocorreu empate também na primeira.");
    }
    @Override
    public void change(FsmPlay wrapper) {
        Table table = wrapper.getTable();

        table.clearTable();

        for(Player p : wrapper.getPlayers()){
            Card c = p.getHand().removeMaxCard();
            table.addCard(c, p.getId(), p.getTeam().getId());
        }

        nextState(wrapper);

    }
}


class FirstTeamWon extends State {
    public void notifyObservers(){
        super.observer.update(this, "Primeiro time venceu!!");
    }
    @Override
    public void change(FsmPlay wrapper) {
        wrapper.setWinner(0);
        wrapper.setEnd();
    }

}

class SecondTeamWon extends State {
    public void notifyObservers(){
        super.observer.update(this, "Segundo time venceu!");
    }
    @Override
    public void change(FsmPlay wrapper) {
        wrapper.setWinner(1);
        wrapper.setEnd();
    }
}

class NoWinner extends State {
    public void notifyObservers(){
        super.observer.update(this, "Não houve vencedou na jogada.");
    }
    @Override
    public void change(FsmPlay wrapper) {
        wrapper.setNoWinner();
        wrapper.setEnd();
    }
}
