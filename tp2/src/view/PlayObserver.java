package view;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by math on 5/13/17.
 */
public class PlayObserver implements Observer {

    private PlayObserver(){ }

    private static class PlayObserverHolder{
        private static final PlayObserver INSTANCE = new PlayObserver();
    }

    public static PlayObserver getInstance(){
        return PlayObserver.PlayObserverHolder.INSTANCE;
    }
    @Override
    public void update(Observable observable, Object o) {
        TerminalDisplay.getInstance().printString(String.valueOf(o));
    }
}
