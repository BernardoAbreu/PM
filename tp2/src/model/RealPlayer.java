package model;

import view.RealPlayerObserver;

import java.util.Observer;

/**
 * Created by math on 5/5/17.
 */
public class RealPlayer extends Player{

    private int cardOption;
    private int playValueOption;
    private Observer observer;

    public RealPlayer(int id){
        super(id);
        this.observer = RealPlayerObserver.getInstance();
    }

    @Override
    public void playCard(Table table) {

        notifyObservers(1);
        Card c = this.getHand().removeCard(this.getCardOption());
        table.addCard(c, this.getId(), this.getTeam().getId());
    }

    @Override
    public Option getOption(boolean max){
        notifyObservers(null);
        if(getPlayValueOption() == 1 ){
            return Option.ACCEPT;
        }
        else if(getPlayValueOption() == 2 ){
            return Option.FOLD;
        }
        else if((getPlayValueOption() == 3) && !max ){
            return Option.RAISE;
        }
        return Option.ACCEPT;
    }

    public int getCardOption() {
        return cardOption;
    }

    public void setCardOption(int cardOption) {
        this.cardOption = cardOption;
    }

    public int getPlayValueOption() {
        return playValueOption;
    }

    public void setPlayValueOption(int playValueOption) {
        this.playValueOption = playValueOption;
    }
    @Override
    public Option getOption(){
        return this.getOption(false);
    }

    public void notifyObservers(Object arg){
        this.observer.update(this,arg);
    }
}
