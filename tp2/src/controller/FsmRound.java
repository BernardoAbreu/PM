package controller;

import model.*;
import view.RoundObsever;

import java.util.Observable;
import java.util.Observer;


abstract class RoundState extends Observable{
    protected Observer observer;
    public abstract void choose(FsmRound wrapper);
    public abstract void notifyObservers();
    public boolean inFold(){
        return false;
    }

    public boolean end(){
        return false;
    }

}

public class FsmRound {
    private RoundState current;
    private PlayValue value;
    private Player thisPlayer;
    private Player otherPlayer;
    private Player currentPlayer;
    private boolean inFold;
    private Table table;

    public FsmRound(Player currentPlayer, Player nextPlayer) {
        this.current = new RoundInitial();

        this.currentPlayer = currentPlayer;
        this.thisPlayer = currentPlayer;
        this.otherPlayer = nextPlayer;
        this.inFold = false;
    }

    public void setTable(Table table){
        this.table = table;
    }

    public Table getTable(){
        return this.table;
    }

    public PlayValue getValue(){
        return value;
    }

    public void setValue(PlayValue value){
        this.value = value;
    }


    public void setState(RoundState state) {
        current = state;
    }

    public void choose() {
        current.choose(this);
    }


    public boolean end(){
        return current.end();
    }


    public Team getTeamThatWonFold(){
        if(this.inFold){
            return otherPlayer.getTeam();
        }
        else{
            return null;
        }
    }


    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }


    Player getThisPlayer(){
        return this.thisPlayer;
    }


    void swapPlayers(){
        Player auxP = this.thisPlayer;
        this.thisPlayer = this.otherPlayer;
        this.otherPlayer = auxP;
    }

    void setInFold(boolean inFold){
        this.inFold = inFold;
    }
}



class RoundInitial extends RoundState {
    RoundInitial(){
        this.observer = RoundObsever.getInstance();
    }
    public void notifyObservers(){
        this.observer.update(this,"Rodada inicial.");
    }
    public void choose(FsmRound wrapper) {
//        System.out.println( "Initial" );
        this.notifyObservers();
        Option opt = wrapper.getThisPlayer().getOption();

        if(opt == Option.RAISE){
            wrapper.setState(new RaiseNormal());
        }
        else if(opt == Option.ACCEPT){
            wrapper.setState(new Accept());
        }
        else if( opt == Option.FOLD){
            wrapper.setState(new Fold());
        }

    }
}

abstract class RaiseState extends RoundState{
    RaiseState(){
        this.observer = RoundObsever.getInstance();
    }
    public void notifyObservers(String message){
        this.observer.update(this,message);
    }
    public void choose(FsmRound wrapper) {
        Option opt = getNextOption(wrapper);
        takeAction(wrapper, opt);
    }

    public Option getNextOption(FsmRound wrapper){
        wrapper.setValue(wrapper.getValue().next());
        this.notifyObservers(wrapper.getThisPlayer().getId() + " gritando " + wrapper.getValue().next());
        wrapper.swapPlayers();
        return wrapper.getThisPlayer().getOption();
    }

    public abstract void takeAction(FsmRound wrapper, Option opt);


}


class RaiseNormal extends RaiseState {
    RaiseNormal(){
        this.observer = RoundObsever.getInstance();
    }
    public void notifyObservers(){
        this.observer.update(this,"ERROR");
    }
    public void notifyObservers(String message){
        this.observer.update(this,message);
    }
    @Override
    public Option getNextOption(FsmRound wrapper){
        this.notifyObservers(wrapper.getThisPlayer().getId() + " gritando " + wrapper.getValue().next());

        wrapper.swapPlayers();

        return wrapper.getThisPlayer().getOption();
    }

    @Override
    public void takeAction(FsmRound wrapper, Option opt){
        if( opt == Option.FOLD){
            wrapper.setState(new Fold());
        }
        else if(opt == Option.ACCEPT){
            wrapper.setState(new AcceptAfterRaise());
        }
        else if(opt == Option.RAISE){
            wrapper.setState(new RaiseTruco());
        }
    }

}



class RaiseTruco extends RaiseState {
    public void notifyObservers(){
        this.observer.update(this,null);
    }
    @Override
    public void takeAction(FsmRound wrapper, Option opt){
        if( opt == Option.FOLD){
            wrapper.setState(new Fold());
        }
        else if(opt == Option.ACCEPT){
            wrapper.setState(new AcceptAfterRaise());    
        }
        else if(opt == Option.RAISE){
            wrapper.setState(new RaiseSeis());
        }
    }

}

class RaiseSeis extends RaiseState {
    public void notifyObservers(){
        this.observer.update(this,null);
    }
    @Override
    public void takeAction(FsmRound wrapper, Option opt){
        if( opt == Option.FOLD){
            wrapper.setState(new Fold());
        }
        else if(opt == Option.ACCEPT){
            wrapper.setState(new AcceptAfterRaise());    
        }
        else if(opt == Option.RAISE){
            wrapper.setState(new RaiseNove());
        }
    }


}


class RaiseNove extends RaiseState {
    public void notifyObservers(){
        this.observer.update(this,null);
    }
    @Override
    public void takeAction(FsmRound wrapper,Option opt){
        if( opt == Option.FOLD){
            wrapper.setState(new Fold());
        }
        else if(opt == Option.ACCEPT){
            wrapper.setState(new AcceptAfterRaise());    
        }
    }

}



class Accept extends RoundState {
    Accept(){
        this.observer = RoundObsever.getInstance();
    }
    public void notifyObservers(){
        this.observer.update(this,"Aceitando o valor da jogada.");
    }
    public void choose(FsmRound wrapper) {
        wrapper.setState(new End());

        this.accept(wrapper);
    }

    protected void accept(FsmRound wrapper){
        wrapper.getCurrentPlayer().playCard(wrapper.getTable());
    }
}

class AcceptAfterRaise extends Accept {
    public void choose(FsmRound wrapper) {
        wrapper.setValue(wrapper.getValue().next());
        wrapper.setState(new End());
        this.accept(wrapper);
    }
}

class Fold extends RoundState {
    Fold(){
        this.observer = RoundObsever.getInstance();
    }
    public void notifyObservers(){
        this.observer.update(this,"Jogador desistiu da jogada.");
    }
    public void choose(FsmRound wrapper) {
        wrapper.setState(new End());
        System.out.println("Player " + wrapper.getThisPlayer().getId() + " Folding");
        wrapper.setInFold(true);
    }
}

class End extends RoundState {
    End(){
        this.observer = RoundObsever.getInstance();
    }
    public void notifyObservers(){
        this.observer.update(this,"Jogada terminou.");
    }
    public void choose(FsmRound wrapper) {
        System.out.println("Ending");

    }

    @Override
    public boolean end(){
        return true;
    }
}