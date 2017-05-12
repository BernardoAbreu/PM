package view;

import model.Match;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by math on 5/12/17.
 */
public class MatchObserver implements Observer {

    private MatchObserver(){ }

    private static class MatchObserverHolder{
        private static final MatchObserver INSTANCE = new MatchObserver();
    }

    public static MatchObserver getInstance(){
        return MatchObserverHolder.INSTANCE;
    }
    @Override
    public void update(Observable observable, Object o) {
        System.out.println("update called on match observer!");
        Match m = (Match) observable;
        System.out.println("Team: " + String.valueOf(m.getWinningTeam().getId()) + " won with score: " + String.valueOf(m.getWinningTeam().getScore()));
    }
}
