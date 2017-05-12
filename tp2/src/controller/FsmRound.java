package controller;

import model.*;


abstract class RoundState{
    public abstract void choose(FsmRound wrapper);

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
    public void choose(FsmRound wrapper) {
        System.out.println( "Initial" );

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
    public void choose(FsmRound wrapper) {
        Option opt = getNextOption(wrapper);
        takeAction(wrapper, opt);
    }

    public Option getNextOption(FsmRound wrapper){
        wrapper.setValue(wrapper.getValue().next());
        System.out.println(wrapper.getThisPlayer().getId() + " gritando " + wrapper.getValue().next());
        wrapper.swapPlayers();
        return wrapper.getThisPlayer().getOption();
    }

    public abstract void takeAction(FsmRound wrapper, Option opt);


}


class RaiseNormal extends RaiseState {
    
    @Override
    public Option getNextOption(FsmRound wrapper){
        System.out.println(wrapper.getThisPlayer().getId() + " gritando " + wrapper.getValue().next());

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
    public void choose(FsmRound wrapper) {
        wrapper.setState(new End());
        System.out.println("Player " + wrapper.getThisPlayer().getId() + " Folding");
        wrapper.setInFold(true);
    }
}

class End extends RoundState {
    public void choose(FsmRound wrapper) {
        System.out.println("Ending");

    }

    @Override
    public boolean end(){
        return true;
    }
}