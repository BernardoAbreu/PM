package view;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by math on 5/13/17.
 */
public class RoundObsever implements Observer{

    private RoundObsever(){ }

    private static class RoundObserverHolder{
        private static final RoundObsever INSTANCE = new RoundObsever();
    }
    public static RoundObsever getInstance(){
        return RoundObserverHolder.INSTANCE;
    }
    @Override
    public void update(Observable observable, Object o) {
        Display display = TerminalDisplay.getInstance();
        if(o != null){
            display.printString(String.valueOf(o));
        }
    }
}
